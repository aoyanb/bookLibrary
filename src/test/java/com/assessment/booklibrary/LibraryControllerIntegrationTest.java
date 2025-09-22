package com.assessment.booklibrary;

import com.assessment.booklibrary.api.error.ApiError;
import com.assessment.booklibrary.api.model.Book;
import com.assessment.booklibrary.api.model.BorrowRequest;
import com.assessment.booklibrary.api.model.ReturnRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
class LibraryControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private Long borrowerId;
    private Long bookId;

    @BeforeEach
    void setup() {
        // assume seeded data from init.sql
        borrowerId = 1L; // Alice
        bookId = 3L;     // Effective Java
    }

    @Test
    @Transactional
    void testBorrowBook_success() {
        BorrowRequest request = new BorrowRequest();
        request.setBorrowerId(borrowerId);
        request.setBookId(bookId);

        Book borrowedBook = restTemplate.postForObject("/api/library/borrow", request, Book.class);

        assertThat(borrowedBook).isNotNull();
        assertThat(borrowedBook.getTitle()).isEqualTo("Head First Java");
    }

    @Test
    @Transactional
    void testBorrowBook_borrowerNotFound() {
        BorrowRequest request = new BorrowRequest();
        request.setBorrowerId(999L);
        request.setBookId(bookId);

        ResponseEntity<ApiError> response = restTemplate.postForEntity("/api/library/borrow", request, ApiError.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getReason().getMessage()).isEqualTo("Borrower not found for provided id");
    }

    @Test
    @Transactional
    void testBorrowBook_bookNotFound() {
        BorrowRequest request = new BorrowRequest();
        request.setBorrowerId(borrowerId);
        request.setBookId(999L);

        ResponseEntity<ApiError> response = restTemplate.postForEntity("/api/library/borrow", request, ApiError.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getBody().getReason().getMessage()).isEqualTo("Book not found for provided id");
    }

    @Test
    @Transactional
    void testReturnBook_success() {
        // First borrow the book
        BorrowRequest borrowRequest = new BorrowRequest();
        borrowRequest.setBorrowerId(borrowerId);
        borrowRequest.setBookId(bookId);
        restTemplate.postForObject("/api/library/borrow", borrowRequest, Book.class);

        // Now return it
        ReturnRequest returnRequest = new ReturnRequest();
        returnRequest.setBorrowerId(borrowerId);
        returnRequest.setIsbn("9780134685991"); // Effective Java ISBN

        restTemplate.postForObject("/api/library/return", returnRequest, Void.class);

        // optionally, try borrowing again to confirm it's available
        Book borrowedAgain = restTemplate.postForObject("/api/library/borrow", borrowRequest, Book.class);
        assertThat(borrowedAgain).isNotNull();
        assertThat(borrowedAgain.getTitle()).isEqualTo("Effective Java");
    }

    @Test
    @Transactional
    void testReturnBook_recordNotFound() {
        ReturnRequest request = new ReturnRequest();
        request.setBorrowerId(borrowerId);
        request.setIsbn("NON_EXISTENT_ISBN");

        ResponseEntity<ApiError> response = restTemplate.postForEntity("/api/library/return", request, ApiError.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getBody().getReason().getMessage()).isEqualTo("Borrow record not found");
    }
}
