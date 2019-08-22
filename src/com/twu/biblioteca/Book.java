package com.twu.biblioteca;

import java.io.PrintStream;

public class Book extends LibraryItem {
    private String title;
    private String author;
    private String year;


    public Book(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getYear() {
        return this.year;
    }

    public void printBook(PrintStream printStream){
        printStream.println(this.getTitle() + " " + this.getAuthor() + " - " + this.getYear());

    }

}
