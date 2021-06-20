package com.project.domain;

/**
 * Этот класс реализует сущность Читатель.
 */
public class Reader {
    /**
     * Уникальный номер читателя в БД.
     */
    private Long id;
    /**
     *Имя читателя.
     */
    private String name;

    public Reader(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Reader(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}