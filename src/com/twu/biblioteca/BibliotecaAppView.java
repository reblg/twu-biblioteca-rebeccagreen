package com.twu.biblioteca;

import java.io.PrintStream;

public class BibliotecaAppView {
    private final PrintStream printStream;

    public BibliotecaAppView(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printInvalidInputMessage() {
        printStream.println("Please Enter a Valid Option");
    }

    public void displayWelcomeMessage(){
        printStream.println("welcome to rebecca and syd's library! \n");
    }

    public void displayOptionMenu() {
        printStream.println("Options");
        printStream.println("1 - List of Books");
        printStream.println("2 - Check out a book");
        printStream.println("3 - Return a book");
        printStream.println("4 - List of Movies");
        printStream.println("5 - Check out a movie");
        printStream.println("Please Enter the Number of Your Choice Here: ");
    }

    public void showQuitMessage() {
        printStream.println("You are quitting the application.");
    }

    public void displayCheckOutConfirmationMessage(LibraryItem libraryItem) {
        printStream.println("You successfully checked out " + libraryItem.getTitle());
    }

    public void askWhichItemToCheckOut() {
        printStream.println("Which would you like to check out?");
    }

    public void askWhichItemToReturn() {
        printStream.println("Which book would you like to return?");
    }

    public void displayCheckOutUnsuccessfulMessage(LibraryItem libraryItem) {
        printStream.println("Sorry, " + libraryItem.getTitle() + " is not available.");
        printStream.println("Please enter a valid title.");
    }

    public void displayReturnConfirmationMessage(LibraryItem libraryItem) {
        printStream.println("You successfully returned " + libraryItem.getTitle());
    }

    public void displayLibraryItemReturnUnsuccessfulMessage() {
        printStream.println("Error returning requested item");
        printStream.println("Please enter a valid title.");
    }
}
