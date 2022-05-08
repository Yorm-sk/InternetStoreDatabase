package com.solvd.internet_store.exceptions;

public class ConnectionIsNotClosedException extends Exception{
    public ConnectionIsNotClosedException() {
        this("Your connection is not closed");
    }

    public ConnectionIsNotClosedException(String message) {
        super(message);
    }
}
