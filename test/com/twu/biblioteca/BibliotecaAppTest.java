package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {
    private Library mockLibrary;
    private BufferedReader mockBufferedReader;
    private BibliotecaAppView mockBibliotecaAppView;
    private BibliotecaApp app;
    private PrintStream mockPrintStream;

    private ArrayList<Book> bookListWithOneMockBook;
    private Book mockBook;


    @Before
    public void setUp() {
//        bib app set up
        mockBufferedReader = mock(BufferedReader.class);
        mockLibrary = mock(Library.class);
        mockBibliotecaAppView = mock(BibliotecaAppView.class);

//      construct mock app
        app = new BibliotecaApp(mockLibrary, mockBufferedReader, mockBibliotecaAppView);

//        print stream
        mockPrintStream = mock(PrintStream.class);

//        book lists
        bookListWithOneMockBook = new ArrayList<Book>();
//        books

        mockBook = mock(Book.class);

//       constructed book lists
        bookListWithOneMockBook.add(mockBook);

    }

    @Test
    public void shouldSeeWelcomeMessageWhenAppStarts() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("1").thenReturn("q");
        app.start();
        verify(mockBibliotecaAppView).displayWelcomeMessage();
    }

    @Test
    public void shouldDisplayOptionsAfterWelcomeMessage() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("1").thenReturn("q");
        app.start();
        verify(mockBibliotecaAppView).displayOptionMenu();
    }


    @Test
    public void shouldSeeIfStartIsCalled() throws IOException {
        BibliotecaApp mockApp = mock(BibliotecaApp.class);
        mockApp.start();
        verify(mockApp).start();
    }


    @Test
    public void shouldPrintOneBookWhenOption1IsSelectedAndThereIsOneBookInTheLibrary() throws IOException {
        Library libWithMockBook = new Library(mockPrintStream, bookListWithOneMockBook);
        app = new BibliotecaApp(libWithMockBook, mockBufferedReader, mockBibliotecaAppView);

        when(mockBufferedReader.readLine()).thenReturn("1").thenReturn("q");
        app.start();

        verify(mockBook).printBook(mockPrintStream);
    }


    @Test
    public void shouldPrintTwoBooksWhenOption1IsSelectedAndThereAreTwoBooksInTheLibrary() throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book mockBook = mock(Book.class);
        Book mockBook2 = mock(Book.class);
        bookList.add(mockBook);
        bookList.add(mockBook2);

        Library libWithMockBook = new Library(mockPrintStream, bookList);
        app = new BibliotecaApp(libWithMockBook, mockBufferedReader, mockBibliotecaAppView);

        when(mockBufferedReader.readLine()).thenReturn("1").thenReturn("q");
        app.start();


        verify(mockBook, times(1)).printBook(mockPrintStream);
        verify(mockBook2, times(1)).printBook(mockPrintStream);
    }


    @Test
    public void shouldDisplayWhen1IsSelectedFromOptions() throws IOException {

        BibliotecaApp app = new BibliotecaApp(mockLibrary, mockBufferedReader, mockBibliotecaAppView);

        when(mockBufferedReader.readLine()).thenReturn("1").thenReturn("q");

        app.start();
        verify(mockLibrary).printBooklist();
    }

    @Test
    public void shouldInformUserOfInValidInputWhenInputIsNot1() throws IOException {
        BibliotecaApp app = new BibliotecaApp(mockLibrary, mockBufferedReader, mockBibliotecaAppView);

        when(mockBufferedReader.readLine()).thenReturn("t").thenReturn("1").thenReturn("q");

        app.start();
        verify(mockBibliotecaAppView, atLeastOnce()).printInvalidInputMessage();
    }
    @Test
    public void shouldQuitApplicationWhenUserInputsQFromOptionMenu() throws IOException {

        BibliotecaApp app = new BibliotecaApp(mockLibrary, mockBufferedReader, mockBibliotecaAppView);

        when(mockBufferedReader.readLine()).thenReturn("q");

        app.start();

        verify(mockBibliotecaAppView).showQuitMessage();
    }

    @Test
    public void libraryShouldNotDisplayAnythingIfOnlyBookInLibraryIsCheckedOut() throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book mockCheckedOutBook = mock(Book.class);
        bookList.add(mockCheckedOutBook);

        Library lib = new Library(mockPrintStream, bookList);
        app = new BibliotecaApp(lib, mockBufferedReader, mockBibliotecaAppView);

        when(mockCheckedOutBook.getCheckedOut()).thenReturn(true);

        when(mockBufferedReader.readLine()).thenReturn("1").thenReturn("q");

        app.start();

        verify(mockCheckedOutBook, never()).printBook(mockPrintStream);
    }

    @Test
    public void shouldCheckOutBookIfUserChecksOutBook() throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book = new Book("1984", "GO", "2000");
        bookList.add(book);

        Library lib = new Library(mockPrintStream, bookList);
        app = new BibliotecaApp(lib, mockBufferedReader, mockBibliotecaAppView);

        when(mockBufferedReader.readLine()).thenReturn("2").thenReturn("1984").thenReturn("q");

        app.start();

        assertTrue(book.getCheckedOut());

        verify(mockBibliotecaAppView).displayCheckOutConfirmationMessage(book);
    }

    @Test
    public void shouldDisplayUnsuccessfulCheckOutMessageIfCannotCheckOutBook() throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book = new Book("1984", "GO", "2000");
        book.setCheckedOut(true);
        bookList.add(book);

        Library lib = new Library(mockPrintStream, bookList);
        app = new BibliotecaApp(lib, mockBufferedReader, mockBibliotecaAppView);

        when(mockBufferedReader.readLine()).thenReturn("2").thenReturn("1984").thenReturn("q");

        app.start();

        verify(mockBibliotecaAppView).displayCheckOutUnsuccessfulMessage(book);
    }

    @Test
    public void shouldReturnBookIfUserReturnsBook() throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book = new Book("1984", "GO", "2000");
        book.setCheckedOut(true);
        bookList.add(book);

        Library lib = new Library(mockPrintStream, bookList);
        app = new BibliotecaApp(lib, mockBufferedReader, mockBibliotecaAppView);

        when(mockBufferedReader.readLine()).thenReturn("3").thenReturn("1984").thenReturn("q");

        app.start();

        assertFalse(book.getCheckedOut());

        verify(mockBibliotecaAppView).displayBookReturnConfirmation(book);
    }

    @Test
    public void shouldDisplayUnsuccessfulReturnMessageIfCannotBeReturned() throws IOException {
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book = new Book("1984", "GO", "2000");
        book.setCheckedOut(true);
        bookList.add(book);

        Library lib = new Library(mockPrintStream, bookList);
        app = new BibliotecaApp(lib, mockBufferedReader, mockBibliotecaAppView);

        when(mockBufferedReader.readLine()).thenReturn("3").thenReturn("hp").thenReturn("q");

        app.start();

        verify(mockBibliotecaAppView).displayBookReturnUnsuccessfulMessage();
    }

}