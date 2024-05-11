package com.bookbridge.data.repo.contract;

import com.bookbridge.data.model.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBorrowedBookRepo extends JpaRepository<BorrowedBook, Long> {
    @Query("SELECT br FROM BorrowedBook br WHERE br.book.id = :bookId AND br.patron.id = :patronId AND br.returnedDate IS NULL")
    Optional<BorrowedBook> findBorrowedBook(@Param("bookId") Long bookId, @Param("patronId") Long patronId);}