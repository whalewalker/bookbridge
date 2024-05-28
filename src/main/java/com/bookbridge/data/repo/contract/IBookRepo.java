package com.bookbridge.data.repo.contract;

import com.bookbridge.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepo extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
}