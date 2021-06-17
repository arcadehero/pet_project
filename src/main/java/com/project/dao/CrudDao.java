package com.project.dao;

import java.util.List;

public interface CrudDao<T> {

    T get(Long id);

    List<T> getAll();

    T update(Long id);

    T create(T teacher);

    boolean delete(Long id);

}
