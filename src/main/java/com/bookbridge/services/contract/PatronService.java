package com.bookbridge.services.contract;

import com.bookbridge.data.model.Patron;
import com.bookbridge.data.request.PatronRequest;
import com.bookbridge.data.response.Response;

public interface PatronService {
    Response<Patron> getAll();

    Response<Patron> getById(Long id);

    Response<Patron> create(PatronRequest request);

    Response<Patron> update(Long id, PatronRequest request);

    Response<?> delete(Long id);
}
