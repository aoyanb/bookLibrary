package com.assessment.booklibrary;

import com.assessment.booklibrary.api.error.ApiError;
import com.assessment.booklibrary.api.model.Borrower;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
public class BorrowerControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Transactional
    void testGetAllBorrowers() {
        ResponseEntity<Borrower[]> response = restTemplate.getForEntity("/api/borrowers", Borrower[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isEqualTo(2); // seeded 2 borrowers
    }

    @Test
    @Transactional
    void testRegisterNewBorrower() {
        Borrower newBorrower = new Borrower();
        newBorrower.setName("Charlie Brown");
        newBorrower.setEmail("charlie@example.com");

        Borrower response = restTemplate.postForObject("/api/borrowers/register", newBorrower, Borrower.class);

        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo("charlie@example.com");

        ResponseEntity<List<Borrower>> responseList = restTemplate.exchange(
                "/api/borrowers",
                HttpMethod.GET,
                null, // no request body
                new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertNotNull(responseList.getBody());
        assertThat(responseList.getBody().size()).isEqualTo(3);
    }

    @Test
    @Transactional
    void testRegisterDuplicateEmail_shouldFail() {
        Borrower duplicateBorrower = new Borrower();
        duplicateBorrower.setName("Alice Clone");
        duplicateBorrower.setEmail("alice@example.com"); // already exists

        ResponseEntity<ApiError> response = restTemplate.postForEntity(
                "/api/borrowers/register", duplicateBorrower, ApiError.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getReason().getMessage()).isEqualTo("Borrower with provided email already exist");
    }
}
