package com.project.dao;

import java.util.List;

public interface CrudDao<K, E> {

    E create(E teacher);

    E get(K id);

    E update(E e);

    boolean delete(K id);

    List<E> getAll();
}