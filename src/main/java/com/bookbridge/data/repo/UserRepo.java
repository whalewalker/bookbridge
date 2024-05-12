package com.bookbridge.data.repo;

import com.bookbridge.data.model.User;
import com.bookbridge.data.repo.contract.IUserRepo;
import com.bookbridge.data.repo.contract.RelationalBaseRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepo extends RelationalBaseRepo<User, IUserRepo> {
    private final IUserRepo iUserRepo;

    public UserRepo(@Lazy IUserRepo iUserRepo) {
        super(iUserRepo, "User");
        this.iUserRepo = iUserRepo;
    }

    public Optional<User> getByEmail(String email) {
        return iUserRepo.findByEmail(email);
    }
}
