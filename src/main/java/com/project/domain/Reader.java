package com.project.domain;

public class Reader {

    private Integer id;
    private String name;
    private Integer booksOnHands;

    public Reader(Integer id, String name, Integer booksOnHands) {
        this.id = id;
        this.name = name;
        this.booksOnHands = booksOnHands;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBooksOnHands() {
        return booksOnHands;
    }

    public void setBooksOnHands(Integer booksOnHands) {
        this.booksOnHands = booksOnHands;
    }
}
