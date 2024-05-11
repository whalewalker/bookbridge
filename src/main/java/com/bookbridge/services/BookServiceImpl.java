package com.bookbridge.services;

import com.bookbridge.data.model.Book;
import com.bookbridge.data.repo.BookRepo;
import com.bookbridge.data.request.BookRequest;
import com.bookbridge.data.response.Response;
import com.bookbridge.services.contract.BookService;
import com.bookbridge.util.Util;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

import static com.bookbridge.util.Util.successfulResponse;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final ModelMapper mapper;

    @Cacheable(cacheNames = "books", key = "'all'")
    @Override
    public Response<Book> getAll() {
        List<Book> books = bookRepo.getAll();
        return successfulResponse(books);
    }

    @Cacheable(cacheNames = "books", key = "#id")
    @Override
    public Response<Book> getById(Long id) {
        Book book = bookRepo.getById(id);
        return successfulResponse(List.of(book));
    }

    @CachePut(cacheNames = "books", key = "#result.modelList.get(0).id")
    @Override
    public Response<Book> create(BookRequest request) {
        Book book = mapper.map(request, Book.class);
        return successfulResponse(List.of(bookRepo.create(book)));
    }

    @CachePut(cacheNames = "books", key = "#id")
    @Override
    public Response<Book> update(Long id, BookRequest request) {
        Book bookToUpdate = bookRepo.getById(id);
        mapper.map(request, bookToUpdate);
        return successfulResponse(List.of(bookRepo.update(bookToUpdate)));
    }

    @CacheEvict(cacheNames = "books", allEntries = true)
    @Override
    public Response<?> delete(Long id) {
        bookRepo.delete(id);
        return successfulResponse(null);
    }
}