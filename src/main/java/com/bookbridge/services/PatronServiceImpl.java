package com.bookbridge.services;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.model.Patron;
import com.bookbridge.data.repo.PatronRepo;
import com.bookbridge.data.request.PatronRequest;
import com.bookbridge.data.response.Response;
import com.bookbridge.services.contract.PatronService;
import com.bookbridge.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bookbridge.util.Util.successfulResponse;

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
    public Response<Patron> create(PatronRequest request) {
        return null;
    }

    @Override
    public Response<Patron> update(Long id, PatronRequest request) {
        return null;
    }

    @Override
    public Response<?> delete(Long id) {
        return successfulResponse(null);
    }
}
