package com.BookInterface;

import com.BookInterface.model.Book;
import com.BookInterface.repository.IBookRepository;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class BookApp {

    private final IBookRepository repository;
    private final Scanner scanner;

    public BookApp(IBookRepository repository) {
        this.repository = repository;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleCountBooksByAuthor();
                    break;
                case 2:
                    handleGetAllAuthors();
                    break;
                case 3:
                    handleGetBookTitlesByAuthor();
                    break;
                case 4:
                    handleGetBooksByRating();
                    break;
                case 5:
                    handleGetBookPricesByAuthor();
                    break;
                case 0:
                    System.out.println("Exiting application... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("\n----------------------------------\n");
        }
    }

    private void handleCountBooksByAuthor() {
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        long count = repository.countBooksByAuthor(author);
        System.out.println("Total books by " + author + ": " + count);
    }

    private void handleGetAllAuthors() {
        Set<String> authors = repository.getAllAuthors();
        System.out.println("--- All Authors ---");
        authors.forEach(System.out::println);
    }

    private void handleGetBookTitlesByAuthor() {
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        List<String> titles = repository.getBookTitlesByAuthor(author);
        System.out.println("--- Books by " + author + " ---");
        titles.forEach(System.out::println);
    }

    private void handleGetBooksByRating() {
        System.out.print("Enter user rating (e.g., 4.7): ");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        List<Book> books = repository.getBooksByRating(rating);
        System.out.println("--- Books with rating " + rating + " ---");
        books.forEach(System.out::println);
    }

    private void handleGetBookPricesByAuthor() {
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        List<String> prices = repository.getBookPricesByAuthor(author);
        System.out.println("--- Book Prices by " + author + " ---");
        prices.forEach(System.out::println);
    }

    private void printMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Get total number of books by an author");
        System.out.println("2. List all authors");
        System.out.println("3. List all books by an author");
        System.out.println("4. List all books with a specific rating");
        System.out.println("5. List names and prices of books by an author");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }
}