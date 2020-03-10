package com.company.library.utils;

import com.company.library.dto.Author;

import java.util.List;

import static com.company.library.dictonaries.Errors.DB_ERROR;
import static com.company.library.enums.TypesOfData.AUTHORS;

public class AuthorUtils {

    private DataBaseUtils dataBaseUtils;

    public AuthorUtils() {
        this.dataBaseUtils = new DataBaseUtils();
    }

    public int addAuthor(Author author) {

        try {
            int authorId = dataBaseUtils.addAuthor(author);

            return authorId;
        }
        catch(Exception e) {
            System.out.println(DB_ERROR + e.getMessage());
            return -1;
        }
    }

    public void deleteAuthor(int authorId) {

        try {
            dataBaseUtils.deleteAuthor(authorId);
        }
        catch(Exception e) {
            System.out.println(DB_ERROR + e.getMessage());
        }

    }

    public List<Author> downloadAuthors() {

        try {
            return (List<Author>) (List<?>) dataBaseUtils.download(AUTHORS);
        }
        catch(Exception e) {
            System.out.println(DB_ERROR + e.getMessage());
            return null;
        }
    }

    public void updateAuthor(Author author, int authorId) {

        try {
            dataBaseUtils.updateAuthor(author, authorId);
        }
        catch(Exception e) {
            System.out.println(DB_ERROR + e.getMessage());
        }

    }

    public Author downloadAuthor(int authorId) {

        try {
            return dataBaseUtils.downloadAuthor(authorId);
        }
        catch(Exception e) {
            System.out.println(DB_ERROR + e.getMessage());
            return null;
        }

    }
}
