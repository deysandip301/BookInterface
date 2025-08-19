package com.BookInterface.repository;
import com.BookInterface.model.Book;
import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Comparator;

public class InMemoryBookRepository implements IBookRepository {

    private final List<Book> books;

    public InMemoryBookRepository(List<Book> books) {
        this.books = books;
    }

    @Override
    public long countBooksByAuthor(String authorName) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(authorName))
                .count();
    }

    @Override
    public Set<String> getAllAuthors() {
        return books.stream()
                .map(Book::getAuthor)
                .collect(Collectors.toSet());
    }

    @Override
    public List<String> getBookTitlesByAuthor(String authorName) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(authorName))
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksByRating(double rating) {
        return books.stream()
                .filter(book -> book.getUserRating() == rating)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBookPricesByAuthor(String authorName) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(authorName))
                .map(book -> String.format("'%s': $%d", book.getTitle(), book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getUniqueBookTitlesByAuthor(String authorName) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(authorName))
                .collect(Collectors.groupingBy(Book::getTitle))
                .keySet()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getUniqueBooksByRating(double rating) {
        return books.stream()
                .filter(book -> book.getUserRating() == rating)
                .collect(Collectors.groupingBy(
                    book -> book.getTitle() + "|" + book.getAuthor(),
                    Collectors.maxBy(Comparator.comparing(Book::getYear))
                ))
                .values()
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }
}