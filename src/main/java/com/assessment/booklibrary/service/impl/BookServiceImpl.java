package com.assessment.booklibrary.service.impl;

import com.assessment.booklibrary.api.mapper.EntityMappers;
import com.assessment.booklibrary.api.model.Book;
import com.assessment.booklibrary.api.model.BookDetail;
import com.assessment.booklibrary.api.model.PageDetail;
import com.assessment.booklibrary.persistence.entity.BookEntity;
import com.assessment.booklibrary.persistence.repository.BookRepository;
import com.assessment.booklibrary.service.BookService;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    @Nonnull
    private final BookRepository bookRepository;
    @Nonnull
    private final EntityMappers mappers;

    @Override
    public Optional<Book> findBook(Long id) {
        return bookRepository.findById(id).map(mappers::fromEntity);
    }

    @Transactional
    @Override
    public Book registerBook(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());

        BookEntity savedBook = bookRepository.save(bookEntity);

        return Book.builder()
                .isbn(savedBook.getIsbn())
                .title(savedBook.getTitle())
                .author(savedBook.getAuthor())
                .build();
    }

    @Override
    public List<BookDetail> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(mappers::detailFromEntity)
                .toList();
    }

    @Override
    public List<Book> findBook(String isbn) {
        return bookRepository.findByIsbn(isbn).stream()
                .map(mappers::fromEntity)
                .toList();
    }
}
