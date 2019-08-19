package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.*;

public class Library {

    private PrintStream printStream;
    private ArrayList<Book> bookList;

    public Library(PrintStream printStream, ArrayList<Book> bookList) {

        this.printStream = printStream;
        this.bookList = bookList;
    }

    public void printBooklist(){
        for (Book book : bookList){
            if(!book.getCheckedOut()) {
                book.printBook(printStream);
            }
        }
    }

    public void checkOutBook(Book book) {
        book.setCheckedOut(true);
    }

    public Book findBookByTitle(String title) {
        for (Book book : bookList) {
            if(book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

}
