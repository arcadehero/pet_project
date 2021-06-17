package com.project.dao;

import com.project.domain.Book;

public class DaoRunner {
    public static void main(String[] args) {
        BookDao bookDao = new BookDao();
        Book book = new Book();
        book.setAuthor("J K Rowling");
        book.setTitle("Harry Potter");
        book.setQuantity(10);

        Book createdBook = bookDao.create(book);
        System.out.println(createdBook);
    }
}
