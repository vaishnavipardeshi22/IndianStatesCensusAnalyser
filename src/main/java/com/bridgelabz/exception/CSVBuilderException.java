package com.bridgelabz.exception;

public class CSVBuilderException extends Exception {

    public ExceptionType type;

    //CONSTRUCTOR
    public CSVBuilderException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    //ENUM CLASS
    public enum ExceptionType {
        NO_SUCH_FILE, NO_SUCH_FILE_TYPE, NO_SUCH_DELIMITER_OR_HEADER;
    }
}
