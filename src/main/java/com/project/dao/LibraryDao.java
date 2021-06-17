package com.project.dao;

import com.project.domain.Library;

import java.util.List;

public class LibraryDao implements  CrudDao<Library> {
    @Override
    public Library get(Long id) {
        return null;
    }

    @Override
    public List<Library> getAll() {
        return null;
    }

    @Override
    public Library update(Long id) {
        return null;
    }

    @Override
    public Library create(Library teacher) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return true;
    }
}
