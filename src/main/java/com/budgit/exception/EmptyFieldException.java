package com.budgit.exception;

public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException(String field) {
        super(field + "can't be empty.");
    }
}