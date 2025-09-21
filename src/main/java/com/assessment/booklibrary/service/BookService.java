package com.assessment.booklibrary.service;

import com.assessment.booklibrary.api.model.Book;
import com.assessment.booklibrary.api.model.BookDetail;
import com.assessment.booklibrary.api.model.PageDetail;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findBook(Long id);
    Book registerBook(Book book);
    List<BookDetail> getAllBooks();
    List<Book> findBook(String isbn);
}
