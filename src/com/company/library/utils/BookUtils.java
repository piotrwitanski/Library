package com.company.library.utils;

import com.company.library.dto.Book;

import java.util.List;

import static com.company.library.dictonaries.Errors.DB_ERROR;
import static com.company.library.enums.TypesOfData.BOOKS;

public class BookUtils {

    private DataBaseUtils dataBaseUtils;

    public BookUtils() {
        this.dataBaseUtils = new DataBaseUtils();
    }

    public int addBook(Book book, int authorId) {

        try {
            int bookId = dataBaseUtils.addBook(book, authorId);

            return bookId;
        }
        catch(Exception e) {
            System.out.println(DB_ERROR + e.getMessage());
            return -1;
        }
    }

    public void deleteBook(int bookId) {

        try {
            dataBaseUtils.deleteBook(bookId);
        }
        catch(Exception e) {
            System.out.println(DB_ERROR + e.getMessage());
        }

    }

    public List<Book> downloadBooks() {

        try {
            return (List<Book>) (List<?>) dataBaseUtils.download(BOOKS);
        }
        catch(Exception e) {
            System.out.println(DB_ERROR + e.getMessage());
            return null;
        }
    }

    public void updateBook(Book newBook, int bookId) {

        try {
            dataBaseUtils.updateBook(newBook, bookId);
        }
        catch(Exception e) {
            System.out.println(DB_ERROR + e.getMessage());
        }
    }

    public Book downloadBook(int bookId) {

        try {
            return dataBaseUtils.downloadBook(bookId);
        }
        catch(Exception e) {
            System.out.println(DB_ERROR + e.getMessage());
            return null;
        }
    }

    public List<Book> downloadBooksOfAuthor(int authorId) {

        try {
            return dataBaseUtils.downloadBooksOfAuthor(authorId);
        }
        catch(Exception e) {
            System.out.println(DB_ERROR + e.getMessage());
            return null;
        }
    }
}
