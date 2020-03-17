package com.bridgelabz.exception;

public class StateCensusAnalyserException extends Exception {

    //ENUM CLASS
    public enum ExceptionType {
        NO_SUCH_FILE;
    }

    public ExceptionType type;

    //CONSTRUCTOR
    public StateCensusAnalyserException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
