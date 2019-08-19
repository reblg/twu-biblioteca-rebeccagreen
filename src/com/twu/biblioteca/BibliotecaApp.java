package com.twu.biblioteca;

import java.io.*;
import java.util.ArrayList;

public class BibliotecaApp {
    private final BufferedReader reader;
    private Library library;
    private BibliotecaAppView bibliotecaAppView;



    public BibliotecaApp(Library library, BufferedReader reader, BibliotecaAppView bibliotecaAppView) {
        this.library = library;
        this.reader = reader;
        this.bibliotecaAppView = bibliotecaAppView;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();

        bookList.add(new Book("1984", "George Orwell", "2018"));
        bookList.add(new Book("Beloved", "Toni Morrison", "2010"));
        Book checkedOutBook = new Book("hp", "jk", "2012");
        checkedOutBook.setCheckedOut(true);
        bookList.add(checkedOutBook);

        ArrayList<Movie> movieList = new ArrayList<Movie>();

        movieList.add(new Movie("Shrek", "DreamWorks", "2001", "10"));
        movieList.add(new Movie("Shrek 2", "DreamWorks", "2004", "9"));
        movieList.add(new Movie("Shrek The Third", "DreamWorks", "2007", "5"));
//        Movie checkedOutBook = new Movie("hp", "jk", "2012");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintStream printStream = new PrintStream(System.out);
        BibliotecaAppView bibliotecaAppView = new BibliotecaAppView(printStream);

        Library lib = new Library(printStream, bookList, movieList);
        BibliotecaApp app = new BibliotecaApp(lib, reader, bibliotecaAppView);

        app.start();
    }

    public void start() throws IOException {
        bibliotecaAppView.displayWelcomeMessage();
        bibliotecaAppView.displayOptionMenu();
        boolean running = true;
        String choice = getUserInput();

        while (running){
            if (choice.equals("1")){
                library.printBooklist();
                choice = getUserInput().toLowerCase();
            }
            else if(choice.equals("2")) {
                library.printBooklist();
                checkOutBookSequence();
                bibliotecaAppView.displayOptionMenu();
                choice = getUserInput().toLowerCase();
            }
            else if(choice.equals("3")) {
                returnBookSequence();
                bibliotecaAppView.displayOptionMenu();
                choice = getUserInput().toLowerCase();
            }
            else if(choice.equals("4")) {
                library.printMovieList();
                choice = getUserInput().toLowerCase();
            }
            else if(choice.equals("q")){
                bibliotecaAppView.showQuitMessage();
                running = false;
            }
            else {
                bibliotecaAppView.printInvalidInputMessage();
                bibliotecaAppView.displayWelcomeMessage();
                choice = getUserInput().toLowerCase();
            }
        }

    }

    private void returnBookSequence() throws IOException {
        bibliotecaAppView.askWhichBookToReturn();

        String bookTitleToReturn = getUserInput();

        Book bookToReturn = library.findBookByTitle(bookTitleToReturn);

        if(bookToReturn != null) {
            bookToReturn.setCheckedOut(false);
            bibliotecaAppView.displayBookReturnConfirmation(bookToReturn);
        } else {
            bibliotecaAppView.displayBookReturnUnsuccessfulMessage();
        }

    }

    private void checkOutBookSequence() throws IOException {
        bibliotecaAppView.askWhichBookToCheckOut();
        String bookTitleToCheckOut = getUserInput();
        Book bookToCheckout = library.findBookByTitle(bookTitleToCheckOut);

        if (!bookToCheckout.getCheckedOut()) {
            library.checkOutBook(bookToCheckout);
            bibliotecaAppView.displayCheckOutConfirmationMessage(bookToCheckout);
            bibliotecaAppView.displayOptionMenu();
        }
        else {
            bibliotecaAppView.displayCheckOutUnsuccessfulMessage(bookToCheckout);
        }
    }



    private String getUserInput() throws IOException {
        return reader.readLine();
    }

}
