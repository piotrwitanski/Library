package com.company.library.ui.datamodel;

import com.company.library.dto.Author;
import com.company.library.dto.Book;
import com.company.library.dto.User;
import com.company.library.utils.AuthorUtils;
import com.company.library.utils.BookUtils;
import com.company.library.utils.UserUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UIData {

    private static UIData instance = new UIData();

    private AuthorUtils authorUtils;
    private BookUtils bookUtils;
    private UserUtils userUtils;

    private ObservableList<BookModel> bookModels;
    private ObservableList<AuthorModel> authorModels;

    private UIData() {
        this.authorUtils = new AuthorUtils();
        this.bookUtils = new BookUtils();
        this.userUtils = new UserUtils();
        this.bookModels = FXCollections.observableArrayList();
        this.authorModels = FXCollections.observableArrayList();
    }

    public static UIData getInstance() {
        return instance;
    }

    public ObservableList<BookModel> getBookModels() {
        return bookModels;
    }

    public ObservableList<AuthorModel> getAuthorModels() {
        return authorModels;
    }

    public User downloadUser(int userId) {
        return userUtils.downloadUser(userId);
    }

    public void loadBooks() {
        List<Book> books = bookUtils.downloadBooks();
        bookModels.removeAll(bookModels);
        addBookModels(books);
    }

    private void addBookModels(List<Book> books) {
        for(Book book: books) {

            bookModels.add(setBookModel(book));
        }
    }

    private BookModel setBookModel(Book book) {
        BookModel bookModel = new BookModel();

        bookModel.setBookId(Integer.toString(book.getId()));
        bookModel.setTitle(book.getTitle());
        bookModel.setAuthor(getAuthorName(book));

        return bookModel;
    }

    private String getAuthorName(Book book) {
        return book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName();
    }

    public void loadAuthors() {
        List<Author> authors = authorUtils.downloadAuthors();
        authorModels.removeAll(authorModels);
        addAuthorModels(authors);
    }

    private void addAuthorModels(List<Author> authors) {
        for(Author author: authors) {

            authorModels.add(setAuthorModels(author));
        }
    }

    private AuthorModel setAuthorModels(Author author) {
        AuthorModel authorModel = new AuthorModel();

        authorModel.setAuthorId(Integer.toString(author.getId()));
        authorModel.setFirstName(author.getFirstName());
        authorModel.setLastName(author.getLastName());
        authorModel.setNationality(author.getNationality());

        return authorModel;
    }

    public void deleteAuthor(AuthorModel authorModel) {
        authorModels.remove(authorModel);
        authorUtils.deleteAuthor(Integer.parseInt(authorModel.getAuthorId()));
    }

    public void deleteBook(BookModel bookModel) {
        bookModels.remove(bookModel);
        bookUtils.deleteBook(Integer.parseInt(bookModel.getBookId()));
    }

    public int saveAuthor(Author author) {
       int authorId = authorUtils.addAuthor(author);

       return authorId;
    }

    public void addAuthorModel(Author author) {
        AuthorModel authorModel = new AuthorModel();
        authorModel.setAuthorId(Integer.toString(author.getId()));
        authorModel.setFirstName(author.getFirstName());
        authorModel.setLastName(author.getLastName());
        authorModel.setNationality(author.getNationality());

        authorModels.add(authorModel);
    }

    public Author downloadAuthor(int authorId) {

        return authorUtils.downloadAuthor(authorId);
    }

    public int saveBook(Book book, int authorId) {
        int bookId = bookUtils.addBook(book, authorId);

        return bookId;
    }

    public void addBookModel(Book book) {
        BookModel bookModel = new BookModel();
        bookModel.setBookId(Integer.toString(book.getId()));
        bookModel.setTitle(book.getTitle());
        bookModel.setAuthor(getAuthorName(book));

        bookModels.add(bookModel);
    }

    public void removeBooksWithAuthor(int authorId) {
        List<Book> books = downloadBooksOfAuthor(authorId);
        for(Book book : books) {
            removeBookModel(book);
        }
    }

    private List<Book> downloadBooksOfAuthor(int authorId) {

        return bookUtils.downloadBooksOfAuthor(authorId);
    }

    private void removeBookModel(Book book) {
        BookModel bookModelToRemove = new BookModel();
        for(BookModel bookModel: bookModels) {
            if(Integer.parseInt(bookModel.getBookId()) == book.getId()) {
                bookModelToRemove = bookModel;
            }
        }
        bookModels.remove(bookModelToRemove);
    }

    public Book downloadBook(int bookId) {

        return bookUtils.downloadBook(bookId);

    }

    public void updateAuthor(Author author) {
        authorUtils.updateAuthor(author, author.getId());
    }

    public void updateAuthorModels(Author author) {
        for(AuthorModel authorModel: authorModels) {
            if(Integer.parseInt(authorModel.getAuthorId()) == author.getId()) {
                authorModel.setFirstName(author.getFirstName());
                authorModel.setLastName(author.getLastName());
                author.setNationality(author.getNationality());
            }
        }
    }

    public void updateBook(Book book) {
        bookUtils.updateBook(book, book.getId());
    }

    public void updateBookModels(Book book) {
        for(BookModel bookModel: bookModels) {
            if(Integer.parseInt(bookModel.getBookId()) == book.getId()) {
                bookModel.setTitle(book.getTitle());
            }
        }
    }
}
