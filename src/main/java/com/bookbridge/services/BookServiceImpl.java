package com.bookbridge.services;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.repo.BookRepo;
import com.bookbridge.data.response.Response;
import com.bookbridge.services.contract.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    @Override
    public Response<Book> getAll() {
        return null;
    }

    @Override
    public Response<Book> getById(Long id) {
        return null;
    }

    @Override
    public Response<Book> create(Book book) {
        return null;
    }

    @Override
    public Response<Book> update(Long id, Book book) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}