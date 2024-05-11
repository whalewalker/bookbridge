package com.bookbridge.services;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.model.Patron;
import com.bookbridge.data.repo.PatronRepo;
import com.bookbridge.data.response.Response;
import com.bookbridge.services.contract.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {
    private final PatronRepo patronRepo;

    @Override
    public Response<Patron> getAll() {
        return null;
    }

    @Override
    public Response<Patron> getById(Long id) {
        return null;
    }

    @Override
    public Response<Patron> create(Book book) {
        return null;
    }

    @Override
    public Response<Patron> update(Long id, Book book) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
