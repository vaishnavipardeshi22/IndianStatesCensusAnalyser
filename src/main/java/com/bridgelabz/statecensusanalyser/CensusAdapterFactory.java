package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.exception.StateCensusAnalyserException;

import java.util.Map;

public class CensusAdapterFactory {
    public static Map<String, CSVCensusDAO> getCensusData(StateCensusAnalyser.Country country, String[] csvFilePath) throws StateCensusAnalyserException {
        if (country.equals(StateCensusAnalyser.Country.INDIA))
            return new IndianCensusAdapter().loadCensusData(csvFilePath);
        else if (country.equals(StateCensusAnalyser.Country.US))
            return new USCensusAdapter().loadCensusData(csvFilePath);
        else
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_COUNTRY, "Invalid country");
    }
}
