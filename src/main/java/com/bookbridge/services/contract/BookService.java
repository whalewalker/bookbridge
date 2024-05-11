package com.bookbridge.services.contract;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.response.Response;

public interface BookService {
    Response<Book> getAll();

    Response<Book> getById(Long id);

    Response<Book> create(Book book);

    Response<Book> update(Long id, Book book);

    void delete(Long id);
}
