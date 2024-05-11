package com.bookbridge.data.repo.contract;

import org.springframework.data.domain.Page;

import java.util.List;


public interface IRepo<T> {

    T create(T product);

    List<T> getAll();

    T getById(long id);

    T update(T product);

    void delete(long id);
}
