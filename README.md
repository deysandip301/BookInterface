# BookInterface Java Application

This is a command-line application built in Java to analyze Amazon's Top 50 bestselling books from 2009 to 2019. The project serves as a practical case study in applying core software design principles to build a system that is modular, extensible, and maintainable.

***

## Core Architectural Principles

The architecture of this project is driven by a commitment to the SOLID principles to create a clean, decoupled, and scalable application. The key design choices are explained below:

* **Single Responsibility Principle (SRP):** Each class in the application has one, and only one, reason to change.
    * The `Book` class only represents the data.
    * The `Genre` enum provides type-safe genre values (FICTION, NON_FICTION).
    * The `DatasetReader` only reads and parses the CSV file.
    * The `InMemoryBookRepository` only executes queries against the in-memory data.
    * The `BookApp` only handles the console user interface and interaction logic.
    * The `AppFactory` only creates and assembles the application's components.
    * This separation makes the code easy to understand, test, and maintain.

* **Programming to Interfaces for Decoupling:** The application's core logic is decoupled from its implementation details through the use of interfaces (`IBookRepository`, `BookReader`). The `BookApp` interacts with the `IBookRepository` interface, not the concrete `InMemoryBookRepository`.
    * **Pro:** This is the most critical design choice for extensibility. It allows the underlying data storage mechanism to be completely swapped (e.g., from an in-memory list to a database) without changing a single line of code in the application logic.
    * **Con:** It adds a minor layer of indirection, but the immense gain in flexibility is the standard and accepted tradeoff.

* **Centralized Dependency Management (Factory Pattern):** Instead of letting the `Main` class or the `BookApp` create their own dependencies (like the repository), this responsibility is delegated to a dedicated `AppFactory`.
    * **Pro:** All configuration and object creation logic is centralized in a single location. To reconfigure the entire application—for instance, to use a `DatabaseRepository` instead of the `InMemoryBookRepository`—only the factory needs to be modified. This makes the system extremely flexible.
    * **Con:** For a very small application, a factory can seem like over-engineering, but it is a foundational pattern for building maintainable software.

* **Clear Separation of Layers:** The project is organized into distinct layers (Model, Utils, Repository, Application, Configuration). This logical separation makes the codebase easy to navigate and ensures that concerns like data access are not mixed with user interface logic.

***

## Project Structure

BookInterface/
├── data/
│   └── book_data.csv
├── src/
│   └── com/
│       └── BookInterface/
│           ├── config/
│           │   └── AppFactory.java
│           ├── utils/
│           │   ├── BookReader.java
│           │   └── DatasetReader.java
│           ├── model/
│           │   ├── Book.java
│           │   └── Genre.java
│           ├── repository/
│           │   ├── IBookRepository.java
│           │   └── InMemoryBookRepository.java
│           ├── BookApp.java
│           └── Main.java
└── bin/

***

## Model Enhancements

### Genre Enum
The application uses a `Genre` enum instead of plain strings for book genres, providing:
* **Type Safety:** Ensures only valid genre values (FICTION, NON_FICTION) can be used
* **Compile-time Checking:** Prevents invalid genre assignments at compile time
* **Consistent Representation:** Centralized handling of genre string parsing and formatting
* **Extensibility:** Easy to add new genres by simply adding enum values

The `Genre.fromString()` method handles various string formats from the CSV data:
- "Fiction" → `Genre.FICTION`
- "Non Fiction" → `Genre.NON_FICTION`
- Also supports variations like "nonfiction" and "non-fiction"

***

## How to Run

1.  Ensure you have a Java Development Kit (JDK) 11 or newer installed.
2.  Place the `book_data.csv` file in a `data` directory at the root of the project.
3.  Compile all `.java` files from the `src` directory.
    ```sh
    javac -d bin -sourcepath src src/com/BookInterface/Main.java src/com/BookInterface/BookApp.java src/com/BookInterface/config/AppFactory.java src/com/BookInterface/model/Book.java src/com/BookInterface/repository/*.java src/com/BookInterface/utils/*.java
    ```
4.  Run the application from the project root.
    ```sh
    java -cp bin com.BookInterface.Main
    ```

## Features

The application provides a command-line menu to perform the following operations based on the dataset:
* Count the total number of books written by a specific author.
* Display a list of all unique authors in the dataset.
* List the names of all books written by a specific author.
* List all books that have a specific user rating.
* List the names and prices of all books by a specific author.