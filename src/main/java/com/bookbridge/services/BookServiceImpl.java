package com.bookbridge.services;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.repo.BookRepo;
import com.bookbridge.data.request.BookRequest;
import com.bookbridge.data.response.Response;
import com.bookbridge.services.contract.BookService;
import com.bookbridge.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bookbridge.util.Util.successfulResponse;

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
    public Response<Book> create(BookRequest request) {
        return null;
    }

    @Override
    public Response<Book> update(Long id, BookRequest request) {
        return null;
    }

    @Override
    public Response<?> delete(Long id) {
        return successfulResponse(null);
    }
}