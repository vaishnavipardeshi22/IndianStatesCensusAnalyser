package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.exception.StateCensusAnalyserException;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CSVCensusDAO> loadCensusData(String[] csvFilePath) throws StateCensusAnalyserException {
        return super.loadCensusData(CSVUSCensus.class, csvFilePath[0]);
    }
}
