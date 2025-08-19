package com.BookInterface.repository;

import com.BookInterface.model.Book;
import java.util.List;
import java.util.Set;

public interface IBookRepository {
    long countBooksByAuthor(String authorName);
    Set<String> getAllAuthors();
    List<String> getBookTitlesByAuthor(String authorName);
    List<Book> getBooksByRating(double rating);
    List<String> getBookPricesByAuthor(String authorName);
}