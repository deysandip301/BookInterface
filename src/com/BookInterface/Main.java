
package com.BookInterface;

import com.BookInterface.config.AppFactory;

public class Main {

    public static void main(String[] args) {
        AppFactory.createBookApp().run();
    }
}