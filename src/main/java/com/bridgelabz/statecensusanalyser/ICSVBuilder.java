package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.exception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
    public <T> Iterator<T> getCSVIterator(Reader reader, Class<T> csvClass) throws CSVBuilderException;
}
