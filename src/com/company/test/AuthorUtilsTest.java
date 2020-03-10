package com.company.test;

import com.company.library.dto.Author;
import com.company.library.utils.AuthorUtils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AuthorUtilsTest {

    private AuthorUtils authorUtils;

    @org.junit.Before
    public void setup() {
        authorUtils = new AuthorUtils();
    }

    @org.junit.Test
    public void addAuthor() {

    }

    @org.junit.Test
    public void deleteAuthor() {
    }

    @org.junit.Test
    public void downloadAuthors() {
        List<Author> authors = authorUtils.downloadAuthors();
        List<Author> expectedAuthorsList = new ArrayList<>();
        expectedAuthorsList.add(new Author(12, "Piotr", "Wit", "Poland"));
        expectedAuthorsList.add(new Author(13, "Cris", "Who", "Canada"));
        expectedAuthorsList.add(new Author(14, "Paul", "Some", "USA"));
        expectedAuthorsList.add(new Author(15, "John", "Snow", "Germany"));

        assertEquals(authors, expectedAuthorsList);
    }

    @org.junit.Test
    public void updateAuthor() {
    }

    @org.junit.Test
    public void downloadAuthor() {
        Author author = authorUtils.downloadAuthor(12);

        assertEquals("Piotr", author.getFirstName());
        assertEquals("Wit", author.getLastName());
        assertEquals("Poland", author.getNationality());
    }

}