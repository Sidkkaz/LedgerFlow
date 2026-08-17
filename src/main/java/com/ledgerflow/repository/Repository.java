package com.ledgerflow.repository;

import java.util.List;

public interface Repository<T> {
    void add(T t);
    void update(T t);
    void delete(T t);
    List<T> list(T t);
}
