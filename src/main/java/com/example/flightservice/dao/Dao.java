package com.example.flightservice.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, K> {
    List<K> findAll();

    Optional<K> findById(T id);

    boolean delete(T id);

    void update(K entity);

    void save(K entity);
}
