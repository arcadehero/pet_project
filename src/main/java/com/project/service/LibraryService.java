package com.project.service;

import com.project.dao.BookDao;
import com.project.dao.LibraryDao;
import com.project.dao.ReaderDao;
import com.project.domain.Book;

import java.util.List;

public class LibraryService { // Вся бизнес-логика должна быть представлена ниже

    BookDao bookDao = new BookDao();
    ReaderDao readerDao = new ReaderDao();
    LibraryDao libraryDao = new LibraryDao();



    public List<Book> getAllBooks() { // Получить перечень все книг, доступных для Читателя
        return bookDao.getAll();
    }


    // Взять книгу на чтение.


    // Отдать книгу обратно в библиотеку

}
