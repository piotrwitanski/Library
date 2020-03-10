package com.company.library.ui.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class AuthorModel {
    private SimpleStringProperty authorId = new SimpleStringProperty("");
    private SimpleStringProperty firstName = new SimpleStringProperty("");
    private SimpleStringProperty lastName = new SimpleStringProperty("");
    private SimpleStringProperty nationality = new SimpleStringProperty("");

    public String getAuthorId() {
        return authorId.get();
    }

    public SimpleStringProperty authorIdProperty() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId.set(authorId);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getNationality() {
        return nationality.get();
    }

    public SimpleStringProperty nationalityProperty() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
    }
}
