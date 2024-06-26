package com.bookbridge.data.repo;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.repo.contract.IBookRepo;
import com.bookbridge.data.repo.contract.RelationalBaseRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class BookRepo extends RelationalBaseRepo<Book, IBookRepo> {
    private final IBookRepo iBookRepo;

    public BookRepo(@Lazy IBookRepo iBookRepo) {
        super(iBookRepo, "Book");
        this.iBookRepo = iBookRepo;
    }

    public Book getByISBN(String isbn) {
       return iBookRepo.findByIsbn(isbn).orElse(null);
    }
}
