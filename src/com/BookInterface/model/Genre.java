package com.BookInterface.model;

/**
 * Enumeration representing the genre of a book.
 * This provides type safety and ensures only valid genre values are used.
 */
public enum Genre {
    FICTION,
    NON_FICTION;

    /**
     * Converts a string representation to the corresponding Genre enum value.
     * 
     * @param genreStr the string representation of the genre
     * @return the corresponding Genre enum value
     * @throws IllegalArgumentException if the string doesn't match any valid genre
     */
    public static Genre fromString(String genreStr) {
        if (genreStr == null) {
            throw new IllegalArgumentException("Genre string cannot be null");
        }
        
        switch (genreStr.trim().toLowerCase()) {
            case "fiction":
                return FICTION;
            case "non fiction":
            case "nonfiction":
            case "non-fiction":
                return NON_FICTION;
            default:
                throw new IllegalArgumentException("Unknown genre: " + genreStr);
        }
    }

    /**
     * Returns a human-readable string representation of the genre.
     * 
     * @return formatted string representation
     */
    @Override
    public String toString() {
        switch (this) {
            case FICTION:
                return "Fiction";
            case NON_FICTION:
                return "Non Fiction";
            default:
                return name();
        }
    }
}
