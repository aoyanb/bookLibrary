package com.assessment.booklibrary;

import com.assessment.booklibrary.api.error.ApiError;
import com.assessment.booklibrary.api.model.Book;
import com.assessment.booklibrary.api.model.BookDetail;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
class BookControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Transactional
    void testGetBooks() {
        ResponseEntity<List<BookDetail>> response = restTemplate.exchange(
                "/api/books",
                HttpMethod.GET,
                null, // no request body
                new ParameterizedTypeReference<>() {
                }
        );

        List<BookDetail> books = response.getBody();

        System.out.println("Response: " + response.getBody());

        assertThat(response).isNotNull();
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(3); // seeded 3 in init.sql
        assertThat(books.get(0).getIsbn()).isEqualTo("9780134685991");
    }

    @Test
    @Transactional
    void testRegisterNewBook() {
        Book newBook = new Book("9781492072508", "Java Cookbook", "Ian Darwin");

        Book response = restTemplate.postForObject("/api/books/register", newBook, Book.class);

        assertThat(response).isNotNull();
        assertThat(response.getIsbn()).isEqualTo("9781492072508");

        BookDetail[] allBooks = restTemplate.getForObject("/api/books", BookDetail[].class);
        assertThat(allBooks.length).isEqualTo(4);
    }

    @Test
    @Transactional
    void testRegisterDuplicateBookWithDifferentTitle_shouldFail() {
        Book badBook = new Book("9780134685991", "Wrong Title", "Joshua Bloch");

        ApiError response = restTemplate.postForObject("/api/books/register", badBook, ApiError.class);

        assertThat(response).isNotNull();
        assertThat(response.getReason().getMessage()).isEqualTo("ISBN should be unique for different title and author");
    }
}