package com.quizer;

public class InvalidQuizFormatException extends Exception {
    public InvalidQuizFormatException(String messageString) {
        super(messageString);
    }
}
