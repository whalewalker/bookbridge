package com.bookbridge.services.contract;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.request.BookRequest;
import com.bookbridge.data.response.Response;

public interface BookService {
    Response<Book> getAll();

    Response<Book> getById(Long id);

    Response<Book> create(BookRequest request);

    Response<Book> update(Long id, BookRequest request);

    Response<?> delete(Long id);
}
