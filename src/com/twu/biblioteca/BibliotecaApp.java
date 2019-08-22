package com.twu.biblioteca;

import org.junit.After;

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

            switch(choice) {
                case "1":
                    library.printBooklist();

                    bibliotecaAppView.displayOptionMenu();
                    choice = getUserInput().toLowerCase();
                    break;
                case "2":
                    library.printBooklist();

                    Book bookToCheckOut = library.findBookByTitle(getUserInput());

                    checkOutSequence(bookToCheckOut);

                    bibliotecaAppView.displayOptionMenu();
                    choice = getUserInput().toLowerCase();
                    break;
                case "3":
                    returnBookSequence();

                    bibliotecaAppView.displayOptionMenu();
                    choice = getUserInput().toLowerCase();
                    break;
                case "4":
                    library.printMovieList();

                    bibliotecaAppView.displayOptionMenu();
                    choice = getUserInput().toLowerCase();
                    break;
                case "5":
                    library.printMovieList();

                    Movie movieToCheckOut = library.findMovieByTitle(getUserInput());

                    checkOutSequence(movieToCheckOut);

                    bibliotecaAppView.displayOptionMenu();
                    choice = getUserInput().toLowerCase();
                    break;
                case "q":
                    bibliotecaAppView.showQuitMessage();
                    running = false;
                    break;
                default:
                    bibliotecaAppView.printInvalidInputMessage();

                    bibliotecaAppView.displayOptionMenu();
                    choice = getUserInput().toLowerCase();
            }
        }
    }


    private void returnBookSequence() throws IOException {
        bibliotecaAppView.askWhichItemToReturn();

        String bookTitleToReturn = getUserInput();

        Book bookToReturn = library.findBookByTitle(bookTitleToReturn);

        if(bookToReturn != null) {
            bookToReturn.setCheckedOut(false);
            bibliotecaAppView.displayReturnConfirmationMessage(bookToReturn);
        } else {
            bibliotecaAppView.displayLibraryItemReturnUnsuccessfulMessage();
        }

    }

    private void checkOutSequence(LibraryItem libraryItem) {
        if (!libraryItem.getCheckedOut()) {
            libraryItem.setCheckedOut(true);
            bibliotecaAppView.displayCheckOutConfirmationMessage(libraryItem);
        }
        else {
            bibliotecaAppView.displayCheckOutUnsuccessfulMessage(libraryItem);
        }
    }

    private String getUserInput() throws IOException {
        return reader.readLine();
    }

}
