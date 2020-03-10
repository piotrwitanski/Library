package com.company.library.utils;

import com.company.library.dto.Author;
import com.company.library.dto.Book;
import com.company.library.dto.User;
import com.company.library.enums.TypesOfData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class DataBaseUtils {
    // create session factory
    private SessionFactory factory;

    private Session session;

    public int addAuthor(Author author) {

        createFactory();
        try {

            session = factory.getCurrentSession();
            // start transaction
            session.beginTransaction();

            System.out.println("Saving author: " + author);
            session.save(author);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

            return author.getId();
        } finally {
            session.close();

            factory.close();
        }
    }

    public void deleteAuthor(int authorId) {

        createFactory();
        try {
            session = factory.getCurrentSession();
            // start transaction
            session.beginTransaction();

            Author author = session.get(Author.class, authorId);

            if(author != null) {
                System.out.println("Found author: " + author);
                session.delete(author);
            }

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();

            factory.close();
        }
    }

    public int addBook(Book book, int authorId) {

        createFactory();
        try {
            session = factory.getCurrentSession();
            // start transaction
            session.beginTransaction();

            Author author = session.get(Author.class, authorId);

            author.addBook(book);

            session.save(book);

            session.getTransaction().commit();

            System.out.println("Done!");

            return book.getId();
        } finally {
            session.close();

            factory.close();
        }

    }

    public void deleteBook(int bookId) {

        createFactory();
        try {
            session = factory.getCurrentSession();
            // start transaction
            session.beginTransaction();

            Book book = session.get(Book.class, bookId);

            if(book != null) {
                System.out.println("Found the book: " + book);

                session.delete(book);
            }

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();

            factory.close();
        }
    }

    private void createFactory() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    public List<Object> download(TypesOfData typesOfData) {

        try {
            return downloadType(typesOfData);
        }
        catch(Exception e) {
            System.out.println("Error" + e);
        }
        return null;
    }

    private List<Object> downloadType(TypesOfData typesOfData) throws Exception {

        switch (typesOfData) {
            case AUTHORS:
                return downloadAuthors();
            case BOOKS:
                return downloadBooks();
            default:
                throw new Exception("Incorrect data format");
        }
    }

    private List<Object> downloadAuthors() {

        try {
            List<Object> authors = new ArrayList<>();

            createFactory();

            session = factory.getCurrentSession();

            // start transaction
            session.beginTransaction();

            authors = session.createQuery("from Author").getResultList();

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
            return authors;
        } finally {
            session.close();

            factory.close();
        }
    }

    private List<Object> downloadBooks() {

        try {
            List<Object> books = new ArrayList<>();

            createFactory();

            session = factory.getCurrentSession();

            // start transaction
            session.beginTransaction();

            books = session.createQuery("from Book").getResultList();

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
            return books;
        } finally {
            session.close();

            factory.close();
        }
    }

    public List<Book> downloadBooksOfAuthor(int authorId) {
        try {
            List<Book> books = new ArrayList<>();

            createFactory();

            session = factory.getCurrentSession();

            // start transaction
            session.beginTransaction();

            books = session.createQuery("from Book b where b.author.id=" + authorId).getResultList();

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
            return books;
        } finally {
            session.close();

            factory.close();
        }
    }

    public Author downloadAuthor(int authorId) {
        try {

            createFactory();

            session = factory.getCurrentSession();

            // start transaction
            session.beginTransaction();

            Author author = session.get(Author.class, authorId);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
            return author;
        } finally {
            session.close();

            factory.close();
        }
    }

    public Book downloadBook(int bookId) {
        try {

            createFactory();

            session = factory.getCurrentSession();

            // start transaction
            session.beginTransaction();

            Book book = session.get(Book.class, bookId);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
            return book;
        } finally {
            session.close();

            factory.close();
        }
    }

    public void updateAuthor(Author newAuthor, int authorId) {

        try {

            createFactory();

            session = factory.getCurrentSession();

            // start transaction
            session.beginTransaction();

            Author author = session.get(Author.class, authorId);

            author.setFirstName(newAuthor.getFirstName());
            author.setLastName(newAuthor.getLastName());
            author.setNationality(newAuthor.getNationality());

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();

            factory.close();
        }
    }

    public void updateBook(Book newBook, int bookId) {

        try {
            createFactory();

            session = factory.getCurrentSession();

            // start transaction
            session.beginTransaction();

            Book book = session.get(Book.class, bookId);

            book.setTitle(newBook.getTitle());

            // commit transaction

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();

            factory.close();
        }
    }

    public void createUser(User user) {


        try {
            createFactory();

            session = factory.getCurrentSession();
            // start transaction
            session.beginTransaction();

            System.out.println("Saving user");
            session.save(user);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            session.close();

            factory.close();
        }
    }

    public User downloadUser(int userId) {


        try {
            createFactory();
            session = factory.getCurrentSession();
            // start transaction
            session.beginTransaction();

            User user = session.get(User.class, userId);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

            return user;
        } finally {
            session.close();

            factory.close();
        }
    }
}
