package com.bookbridge.data.repo;

import com.bookbridge.data.model.Patron;
import com.bookbridge.data.repo.contract.IPatronRepo;
import com.bookbridge.data.repo.contract.RelationalBaseRepo;
import org.springframework.stereotype.Service;

@Service
public class PatronRepo extends RelationalBaseRepo<Patron, IPatronRepo> {
    private final IPatronRepo iPatronRepo;

    public PatronRepo(IPatronRepo iPatronRepo) {
        super(iPatronRepo, "Patron");
        this.iPatronRepo = iPatronRepo;
    }
}
