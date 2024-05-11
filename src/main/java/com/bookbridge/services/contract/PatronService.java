package com.bookbridge.services.contract;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.model.Patron;
import com.bookbridge.data.response.Response;

public interface PatronService {
    Response<Patron> getAll();

    Response<Patron> getById(Long id);

    Response<Patron> create(Book book);

    Response<Patron> update(Long id, Book book);

    void delete(Long id);
}
