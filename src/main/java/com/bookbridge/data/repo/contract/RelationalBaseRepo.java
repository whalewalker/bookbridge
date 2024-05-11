package com.bookbridge.data.repo.contract;


import com.bookbridge.exception.ResourceNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class RelationalBaseRepo<T extends BaseModel, R extends JpaRepository<T, Long>> implements IRepo<T> {

    private final R r;
    private final String name;


    protected RelationalBaseRepo(R r, String name) {
        this.r = r;
        this.name = name;
    }

    public T create(T t) {
        return r.save(t);
    }

    public T update(T t) {
        if (findById(r, t.getId()) == null)
            throw new ResourceNotFoundException(name + " not found");

        return r.save(t);
    }

    public T getById(long id) {
        return findById(r, id);
    }

    public List<T> getAll(){
        return r.findAll();
    }

    public void delete(long id) {
        T entity = findById(r, id);
        r.save(entity);
    }


    private T findById(R repository, long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("%s with id %s not found", name, id)));
    }


}
