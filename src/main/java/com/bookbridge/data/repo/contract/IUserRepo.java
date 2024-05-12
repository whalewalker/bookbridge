package com.bookbridge.data.repo.contract;

import com.bookbridge.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
