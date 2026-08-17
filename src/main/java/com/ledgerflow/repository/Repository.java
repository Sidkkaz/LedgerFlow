package com.ledgerflow.repository;

public interface Repository<T> {
    T add(T t);
    T update(T t);
    T delete(T t);
    T list(T t);
}
