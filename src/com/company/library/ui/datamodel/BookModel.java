package com.company.library.ui.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class BookModel {
    private SimpleStringProperty bookId = new SimpleStringProperty("");
    private SimpleStringProperty title = new SimpleStringProperty("");
    private SimpleStringProperty author = new SimpleStringProperty("");

    public String getBookId() {
        return bookId.get();
    }

    public SimpleStringProperty bookIdProperty() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId.set(bookId);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }
}
