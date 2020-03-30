package com.bridgelabz.exception;

public class StateCensusAnalyserException extends Exception {

    public ExceptionType type;

    //CONSTRUCTOR
    public StateCensusAnalyserException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    //ENUM CLASS
    public enum ExceptionType {
        NO_SUCH_FILE, NO_SUCH_FILE_TYPE, NO_SUCH_DELIMITER_OR_HEADER, NO_CENSUS_DATA, NO_SUCH_COUNTRY;
    }
}
