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
    private ArrayList<Book> bookListWithTwoMockBooks;
    private ArrayList<Book> bookListWithOneCheckedOutBook;
    private ArrayList<Book> bookListWithOneRealBook;

//    private Library libraryWithOneRealBook;

    private Book mockBook;
    private Book mockBook2;
    private Book mockCheckOutBook;
    private Book nineteenEightFour;

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
        bookListWithTwoMockBooks = new ArrayList<Book>();
        bookListWithOneCheckedOutBook = new ArrayList<Book>();
        bookListWithOneRealBook = new ArrayList<Book>();
//        books

        mockBook = mock(Book.class);
        mockBook2 = mock(Book.class);
        mockCheckOutBook = mock(Book.class);
        nineteenEightFour = new Book("1984", "GO", "2000");


//       constructed book lists
        bookListWithOneMockBook.add(mockBook);

        bookListWithTwoMockBooks.add(mockBook);
        bookListWithTwoMockBooks.add(mockBook2);

        bookListWithOneCheckedOutBook.add(mockCheckOutBook);

        bookListWithOneRealBook.add(nineteenEightFour);

////        library
//        libraryWithOneRealBook = new Library(mockPrintStream, bookListWithOneRealBook);

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
        Library libWithTwoMockBooks = new Library(mockPrintStream, bookListWithTwoMockBooks);
        app = new BibliotecaApp(libWithTwoMockBooks, mockBufferedReader, mockBibliotecaAppView);

        when(mockBufferedReader.readLine()).thenReturn("1").thenReturn("q");
        app.start();


        verify(mockBook, times(1)).printBook(mockPrintStream);
        verify(mockBook2, times(1)).printBook(mockPrintStream);
    }


    @Test
    public void shouldDisplayWhen1IsSelectedFromOptions() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("1").thenReturn("q");

        app.start();

        verify(mockLibrary).printBooklist();
    }

    @Test
    public void shouldInformUserOfInValidInputWhenInputIsNot1() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("t").thenReturn("1").thenReturn("q");

        app.start();

        verify(mockBibliotecaAppView, atLeastOnce()).printInvalidInputMessage();
    }
    @Test
    public void shouldQuitApplicationWhenUserInputsQFromOptionMenu() throws IOException {

        when(mockBufferedReader.readLine()).thenReturn("q");

        app.start();

        verify(mockBibliotecaAppView).showQuitMessage();
    }

    @Test
    public void libraryShouldNotDisplayAnythingIfOnlyBookInLibraryIsCheckedOut() throws IOException {

        Library lib = new Library(mockPrintStream, bookListWithOneCheckedOutBook);
        app = new BibliotecaApp(lib, mockBufferedReader, mockBibliotecaAppView);

        when(mockCheckOutBook.getCheckedOut()).thenReturn(true);

        when(mockBufferedReader.readLine()).thenReturn("1").thenReturn("q");

        app.start();

        verify(mockCheckOutBook, never()).printBook(mockPrintStream);
    }

    @Test
    public void shouldCheckOutBookIfUserChecksOutBook() throws IOException {
        Library lib = new Library(mockPrintStream, bookListWithOneRealBook);

        app = new BibliotecaApp(lib, mockBufferedReader, mockBibliotecaAppView);

        when(mockBufferedReader.readLine()).thenReturn("2").thenReturn("1984").thenReturn("q");

        app.start();

        assertTrue(nineteenEightFour.getCheckedOut());
        verify(mockBibliotecaAppView).displayCheckOutConfirmationMessage(nineteenEightFour);
    }

    @Test
    public void shouldDisplayUnsuccessfulCheckOutMessageIfCannotCheckOutBook() throws IOException {
        nineteenEightFour.setCheckedOut(true);

        Library lib = new Library(mockPrintStream, bookListWithOneRealBook);
        app = new BibliotecaApp(lib, mockBufferedReader, mockBibliotecaAppView);

        when(mockBufferedReader.readLine()).thenReturn("2").thenReturn("1984").thenReturn("q");

        app.start();

        verify(mockBibliotecaAppView).displayCheckOutUnsuccessfulMessage(nineteenEightFour);
    }

    @Test
    public void shouldReturnBookIfUserReturnsBook() throws IOException {
        nineteenEightFour.setCheckedOut(true);

        Library lib = new Library(mockPrintStream, bookListWithOneRealBook);
        app = new BibliotecaApp(lib, mockBufferedReader, mockBibliotecaAppView);

        when(mockBufferedReader.readLine()).thenReturn("3").thenReturn("1984").thenReturn("q");

        app.start();

        assertFalse(nineteenEightFour.getCheckedOut());

        verify(mockBibliotecaAppView).displayBookReturnConfirmation(nineteenEightFour);
    }

    @Test
    public void shouldDisplayUnsuccessfulReturnMessageIfCannotBeReturned() throws IOException {
        nineteenEightFour.setCheckedOut(true);

        Library lib = new Library(mockPrintStream, bookListWithOneRealBook);

        app = new BibliotecaApp(lib, mockBufferedReader, mockBibliotecaAppView);

        when(mockBufferedReader.readLine()).thenReturn("3").thenReturn("hp").thenReturn("q");

        app.start();

        verify(mockBibliotecaAppView).displayBookReturnUnsuccessfulMessage();
    }

}