package com.twu.biblioteca;

public class LibraryItem {
    private boolean checkedOut;
    private String title;

    public LibraryItem() {
        this.checkedOut = false;
    }

    public boolean getCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public String getTitle() {
        return this.title;
    }
}
