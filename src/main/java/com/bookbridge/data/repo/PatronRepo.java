package com.bookbridge.data.repo;

import com.bookbridge.data.model.Patron;
import com.bookbridge.data.repo.contract.IPatronRepo;
import com.bookbridge.data.repo.contract.RelationalBaseRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatronRepo extends RelationalBaseRepo<Patron, IPatronRepo> {
    private final IPatronRepo iPatronRepo;

    public PatronRepo(IPatronRepo iPatronRepo) {
        super(iPatronRepo, "Patron");
        this.iPatronRepo = iPatronRepo;
    }

    public Optional<Patron> getByEmail(String email) {
        return iPatronRepo.findByEmail(email);
    }
}
