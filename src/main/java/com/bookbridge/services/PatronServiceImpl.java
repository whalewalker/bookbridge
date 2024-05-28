package com.bookbridge.services;

import com.bookbridge.data.model.Patron;
import com.bookbridge.data.repo.PatronRepo;
import com.bookbridge.data.request.PatronRequest;
import com.bookbridge.data.response.Response;
import com.bookbridge.exception.RequestIsBadException;
import com.bookbridge.services.contract.PatronService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bookbridge.util.Util.successfulResponse;

@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {
    private final PatronRepo patronRepo;
    private final ModelMapper mapper;


    @Override
    public Response<Patron> getAll() {
        List<Patron> patrons = patronRepo.getAll();
        return successfulResponse(patrons);
    }

    @Override
    public Response<Patron> getById(Long id) {
        Patron patron = patronRepo.getById(id);
        return successfulResponse(List.of(patron));
    }

    @Override
    public Response<Patron> create(PatronRequest request) {
        Patron existingPatron = patronRepo.getByEmail(request.getEmail());
        if (existingPatron != null) throw new RequestIsBadException("Patron already exists with email " + request.getEmail());
        Patron patron = mapper.map(request, Patron.class);
        return successfulResponse(List.of(patronRepo.create(patron)));
    }


    @Override
    public Response<Patron> update(Long id, PatronRequest request) {
        Patron patronToUpdate = patronRepo.getById(id);
        mapper.map(request, patronToUpdate);
        return successfulResponse(List.of(patronRepo.update(patronToUpdate)));
    }

    @Override
    public Response<?> delete(Long id) {
        patronRepo.delete(id);
        return successfulResponse(null);
    }
}
