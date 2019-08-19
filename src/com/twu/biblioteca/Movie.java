package com.twu.biblioteca;

import java.io.PrintStream;

public class Movie {
    private String title;
    private String director;
    private String year;
    private String rating;


    public Movie(String title, String director, String year, String rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getYear() {
        return year;
    }

    public void printMovie(PrintStream printStream){
        printStream.println(this.getTitle() + " - " + this.getDirector() + " - " + this.getYear());

    }
}
