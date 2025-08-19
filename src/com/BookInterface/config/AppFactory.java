package com.BookInterface.config;

import com.BookInterface.BookApp;
import com.BookInterface.utils.BookReader;
import com.BookInterface.utils.DatasetReader;
import com.BookInterface.model.Book;
import com.BookInterface.repository.IBookRepository;
import com.BookInterface.repository.InMemoryBookRepository;
import java.util.List;


public class AppFactory {

    public static BookApp createBookApp() {
        IBookRepository repository = createRepository();
        return new BookApp(repository);
    }

    
    private static IBookRepository createRepository() {
        BookReader dataSource = createDataSource();
        List<Book> bookList = dataSource.readBooks();
        
        if (bookList.isEmpty()) {
            System.out.println("Could not load book data. The application may not function correctly.");
        }
        
        return new InMemoryBookRepository(bookList);
    }

    private static BookReader createDataSource() {

        return new DatasetReader("data/book_data.csv");
    }
}