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
        printStream.println("Please Enter the Number of Your Choice Here: ");
    }

    public void showQuitMessage() {
        printStream.println("You are quitting the application.");
    }

    public void displayCheckOutConfirmationMessage(Book book) {
        printStream.println("You successfully checked out " + book.getTitle());
    }

    public void askWhichBookToCheckOut() {
        printStream.println("Which book would you like to check out?");
    }

    public void askWhichBookToReturn() {
        printStream.println("Which book would you like to return?");
    }

    public void displayCheckOutUnsuccessfulMessage(Book book) {
        printStream.println("Sorry, " + book.getTitle() + " is not available.");
        printStream.println("Please enter a valid title.");
    }

    public void displayBookReturnConfirmation(Book book) {
        printStream.println("You successfully returned " + book.getTitle());
    }
}
