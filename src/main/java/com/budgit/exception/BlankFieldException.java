package com.budgit.exception;

public class BlankFieldException extends RuntimeException {
    public BlankFieldException(String field) {
        super(field + "can't be blank.");
    }
}
