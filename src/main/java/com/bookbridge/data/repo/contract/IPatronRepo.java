package com.bookbridge.data.repo.contract;


import com.bookbridge.data.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPatronRepo extends JpaRepository<Patron, Long> {
    Optional<Patron> findByEmail(String email);
}