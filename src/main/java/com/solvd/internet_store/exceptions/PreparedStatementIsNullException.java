package com.solvd.internet_store.exceptions;

public class PreparedStatementIsNullException extends  Exception{
    public PreparedStatementIsNullException() {
        this("Create prepared statement first!");
    }

    public PreparedStatementIsNullException(String message) {
        super(message);
    }
}
