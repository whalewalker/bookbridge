package com.bookbridge.data.repo.contract;

import org.springframework.data.domain.Page;


public interface IRepo<T> {

    T create(T product);

    Page<T> getAll(int pageNum, int pageSize);

    T getById(long id);

    T update(T product);

    void delete(long id);
}
