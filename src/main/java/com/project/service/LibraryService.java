package com.project.service;

import com.project.dao.BookDao;
import com.project.dao.ReaderDao;
import com.project.domain.Book;
import com.project.domain.Reader;

import java.util.List;

/**
 * Этот класс реализует слой бизнес-логики работы библиотеки.
 */
public class LibraryService {

    BookDao bookDao = new BookDao();
    ReaderDao readerDao = new ReaderDao();

    /**
     * Метод создает нового читателя в БД
     *
     * @param readerName Имя читателя
     * @return созданную сущность читалеля в БД.
     */
    public Reader createNewReader(String readerName) {
        return readerDao.create(new Reader(readerName));
    }

    /**
     * Метод создает новую книгу в БД
     *
     * @param title  Название книги
     * @param author Имя автора
     * @param reader Сущность читателя, у которого эта книга находится на руках.
     * @return созданную сущность книги в БД.
     */
    public Book createNewBook(String title, String author, Reader reader) {
        return bookDao.create(new Book(title, author, reader));
    }

    /**
     * Метод создает список всех книг в библиотеке.
     *
     * @return список List<Book>
     */
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    /**
     * Метод создает список всех читателей библиотеки.
     *
     * @return список List<Reader>
     */
    public List<Reader> getAllReaders() {
        return readerDao.getAll();
    }

    /**
     * Метод удаляет книгу из БД.
     */

    public boolean deleteBook(Long id) {
        return bookDao.delete(id);
    }

    /**
     * Метод удаляет читалеля из БД.
     */
    public boolean deleteReader(Long id) {
        return readerDao.delete(id);
    }

    /**
     * Метод позволяет отдать книгу на чтение.
     *
     * @param readerId имя читателя, на кого записывается книга.
     * @param bookName название книги, которую записывают на читателя
     */
    public boolean giveOutBook(Long readerId, String bookName) {

        return BookDao.giveOutBook(readerId, bookName);
    }

    /**
     * Метод позволяет вернуть книгу обратно в библиотеку.
     *
     * @param bookName название возвращаемой книги
     * @return true, если книга успешно вернулась в библиотеку.
     */
    public boolean handOverBook(String bookName) {
        return BookDao.handOverBook(bookName);
    }

    /**
     * Метод создает список  всех читателей, у которых на руках есть книги.
     *
     * @return список List<Book>
     */
    public static List<Book> getAllReadersWithBooks() {
        return BookDao.getAllReadersWithBooks();
    }
}