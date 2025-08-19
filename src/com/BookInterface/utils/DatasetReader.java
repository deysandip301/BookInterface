package com.BookInterface.utils;

import com.BookInterface.model.Book;
import com.BookInterface.model.Genre;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DatasetReader implements BookReader {

    private final String filePath;

    public DatasetReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Book> readBooks() {
        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                if (values.length == 7) {
                    try {
                        String title = values[0];
                        String author = values[1];
                        double userRating = Double.parseDouble(values[2]);
                        int reviews = Integer.parseInt(values[3]);
                        int price = Integer.parseInt(values[4]);
                        int year = Integer.parseInt(values[5]);
                        String genreStr = values[6];
                        Genre genre = Genre.fromString(genreStr);

                        books.add(new Book(title, author, userRating, reviews, price, year, genre));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping malformed line (number format error): " + line);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Skipping malformed line (invalid genre): " + line);
                    }
                }
            }
        } 
        catch (IOException e) {
            System.err.println("Error reading the file: " + filePath);
            e.printStackTrace();
        }
        return books;
    }
}
