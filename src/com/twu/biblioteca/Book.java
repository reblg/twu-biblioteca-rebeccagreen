package com.twu.biblioteca;

import java.io.PrintStream;

public class Book {
    private String title;
    private String author;
    private String year;
    private Boolean checkedOut;

    public Book(String title, String author, String year) {

        this.title = title;
        this.author = author;
        this.year = year;
        this.checkedOut = false;
    }


    public String getTitle(){
        return this.title;
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

    public void setCheckedOut(Boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public Boolean getCheckedOut() {
        return checkedOut;
    }
}
