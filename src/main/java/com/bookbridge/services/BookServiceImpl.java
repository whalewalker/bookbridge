package com.bookbridge.services;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.repo.BookRepo;
import com.bookbridge.data.request.BookRequest;
import com.bookbridge.data.response.Response;
import com.bookbridge.services.contract.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bookbridge.util.Util.successfulResponse;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final ModelMapper mapper;

    @Override
    public Response<Book> getAll() {
        List<Book> books = bookRepo.getAll();
        return successfulResponse(books);
    }

    @Override
    public Response<Book> getById(Long id) {
        Book book = bookRepo.getById(id);
        return successfulResponse(List.of(book));
    }

    @Override
    public Response<Book> create(BookRequest request) {
        Book book = mapper.map(request, Book.class);
        return successfulResponse(List.of(bookRepo.create(book)));
    }

    @Override
    public Response<Book> update(Long id, BookRequest request) {
        Book bookToUpdate = bookRepo.getById(id);
        mapper.map(request, bookToUpdate);
        return successfulResponse(List.of(bookRepo.update(bookToUpdate)));
    }

    @Override
    public Response<?> delete(Long id) {
        bookRepo.delete(id);
        return successfulResponse(null);
    }
}