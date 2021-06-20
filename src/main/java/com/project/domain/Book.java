package com.project.domain;

/**
 * Этот класс реализует сущность Книга.
 */
public class Book {
    /**
     * Уникальный номер книги в БД.
     */
    private Long id;
    /**
     * Название книги.
     */
    private String title;
    /**
     * Автор книги.
     */
    private String author;
    /**
     * Сущность Читателя. Null означает, что книга никем не читается в данный момент.
     */
    private Reader reader;

    public Book(Long id, String title, String author, Reader reader) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.reader = reader;
    }

    public Book(String title, String author, Reader reader) {
        this.title = title;
        this.author = author;
        this.reader = reader;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Object getReader() {
        return reader;
    }

    public void setReader(Object readerId) {
        this.reader = reader;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", reader=" + reader +
                '}';
    }
}

