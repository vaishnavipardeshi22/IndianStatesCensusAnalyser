package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.exception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder {
    public <T> Iterator<T> getCSVIterator(Reader reader, Class csvClass) throws CSVBuilderException;
    public List getCSVList(Reader reader, Class csvClass) throws CSVBuilderException;
}
