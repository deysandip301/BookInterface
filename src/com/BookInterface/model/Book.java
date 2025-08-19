package com.BookInterface.model;


public class Book {

    private final String title;
    private final String author;
    private final double userRating;
    private final int reviews;
    private final int price;
    private final int year;
    private final Genre genre;

    public Book(String title, String author, double userRating, int reviews, int price, int year, Genre genre) {
        this.title = title;
        this.author = author;
        this.userRating = userRating;
        this.reviews = reviews;
        this.price = price;
        this.year = year;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getUserRating() {
        return userRating;
    }

    public int getReviews() {
        return reviews;
    }

    public int getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return String.format(
            "Title: %s\n  Author: %s\n  User Rating: %.1f\n  Reviews: %d\n  Price: $%d\n  Year: %d\n  Genre: %s\n",
            title, author, userRating, reviews, price, year, genre
        );
    }
}