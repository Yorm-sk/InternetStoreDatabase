package com.solvd.internet_store.exceptions;

public class ConnectionIsNullException extends Exception{
    public ConnectionIsNullException() {
        this("Can`t connect, your connection is null!");
    }

    public ConnectionIsNullException(String message) {
        super(message);
    }
}
