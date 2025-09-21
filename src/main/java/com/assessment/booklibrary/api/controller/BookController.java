package com.assessment.booklibrary.api.controller;

import com.assessment.booklibrary.api.error.ErrorReason;
import com.assessment.booklibrary.api.model.Book;
import com.assessment.booklibrary.api.model.BookDetail;
import com.assessment.booklibrary.exception.DuplicateIsbnException;
import com.assessment.booklibrary.service.BookService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/books")
@RestController
@RequiredArgsConstructor
public class BookController {
    @Nonnull
    private final BookService bookService;

    @PostMapping("/register")
    public Book register(@RequestBody @Validated Book book) {
        List<Book> bookCopies = bookService.findBook(book.getIsbn());
        if (!bookCopies.isEmpty()) {
            Book reference = bookCopies.get(0);
            if (!reference.getTitle().equalsIgnoreCase(book.getTitle())
                    || !reference.getAuthor().equalsIgnoreCase(book.getAuthor())) {
                throw DuplicateIsbnException.notAllowed(ErrorReason.DUPLICATE_ISBN_NOT_ALLOWED);
            }
        }
        return bookService.registerBook(book);
    }

    @GetMapping
    public List<BookDetail> getAllBooks() {
        return bookService.getAllBooks();
    }
}
