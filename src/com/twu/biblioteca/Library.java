package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.*;

public class Library {

    private PrintStream printStream;
    private ArrayList<Book> bookList;
    private ArrayList<Movie> movieList;

    public Library(PrintStream printStream, ArrayList<Book> bookList, ArrayList<Movie> movieList) {
        this.printStream = printStream;
        this.bookList = bookList;
        this.movieList = movieList;
    }

    public void printBooklist(){
        for (Book book : bookList){
            if(!book.getCheckedOut()) {
                book.printBook(printStream);
            }
        }
    }

    public void printMovieList() {
        for (Movie movie : movieList){
                movie.printMovie(printStream);
        }
    }

//    public void checkOutBook(Book book) {
//        book.setCheckedOut(true);
//    }

    public Book findBookByTitle(String title) {
        for (Book book : bookList) {
            if(book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public Movie findMovieByTitle(String title) {
        for (Movie movie : movieList) {
            if(movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

}
