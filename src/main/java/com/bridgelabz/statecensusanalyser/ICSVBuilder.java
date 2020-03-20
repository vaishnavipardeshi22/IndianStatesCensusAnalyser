package com.bridgelabz.statecensusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
    public <T> Iterator<T> getCSVIterator(Reader reader, Class<T> csvClass);
}
