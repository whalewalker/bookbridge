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

    public Page<T> getAll(int pageNum, int pageSize) {
        List<T> ts;

        if (pageNum > 0) {
            return findAll(r, PageRequest.of(pageNum - 1, pageSize, Sort.by(new Sort.Order(Sort.Direction.DESC, "id"))));
        } else {
            ts = findAll(r, Sort.by(new Sort.Order(Sort.Direction.DESC, "id")));
            return new PageImpl<>(ts);
        }
    }

    public T getById(long id) {
        return findById(r, id);
    }

    public void delete(long id) {
        T entity = findById(r, id);
        r.save(entity);
    }

    public void deleteAll(List<T> entities) {
        for (T entity : entities) {
            T existingEntity = findById(r, entity.getId());
            r.save(existingEntity);
        }
    }

    private T findById(R repository, long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("%s with id %s not found", name, id)));
    }

    private Page<T> findAll(R r, Pageable pageable) {
        return r.findAll(pageable);
    }

    private List<T> findAll(R r, Sort sort) {
        return r.findAll(sort).stream().toList();
    }
}
