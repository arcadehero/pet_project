package com.project.console;

import com.project.service.LibraryService;

public class Console {
    public static void main(String[] args) {
        final LibraryService libraryService = new LibraryService();

          System.out.println(libraryService.getAllReadersWithBooks());

          System.out.println(libraryService.createNewBook("Игрок", "Достоевский", null));

          System.out.println(libraryService.createNewReader("Timur"));

         System.out.println(libraryService.getAllReaders());

         System.out.println(libraryService.handOverBook("To Kill a Mockingbird"));

         System.out.println(libraryService.giveOutBook(4L, "Lord of the Flies"));

        System.out.println(libraryService.deleteBook(10L));

         System.out.println(libraryService.deleteReader(2L));

        System.out.println(libraryService.getAllBooks());

    }
}