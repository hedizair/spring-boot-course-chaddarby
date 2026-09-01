package com.hzair.demo.rest;

public class StudentNotFoundException extends RuntimeException { // * RuntimeException Allow to throw this exception with the keyword "throw" in the code.


    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
    
}
