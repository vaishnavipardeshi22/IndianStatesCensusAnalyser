package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.exception.StateCensusAnalyserException;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StateCensusAnalyser {

    List<CSVCensusDAO> csvCensusDAOList = null;
    Map<String, CSVCensusDAO> csvStateCensusDAOMap = null;
    private Country country;

    //CONSTRUCTOR
    public StateCensusAnalyser(Country country) {
        this.country = country;
    }

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("******************** WELCOME TO INDIAN STATES CENSUS ANALYSER ********************");
    }

    //METHOD TO LOAD CENSUS DATA
    public int loadStateCensusCSVData(Country country, String... csvFilePath) throws StateCensusAnalyserException {
        csvStateCensusDAOMap = CensusAdapterFactory.getCensusData(country, csvFilePath);
        csvCensusDAOList = csvStateCensusDAOMap.values().stream().collect(Collectors.toList());
        return csvStateCensusDAOMap.size();
    }

    //FUNCTION FOR SORT CENSUS DATA
    public String getSortedCensusData(SortingMode mode) throws StateCensusAnalyserException {
        if (csvCensusDAOList == null || csvCensusDAOList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No census data");
        ArrayList arrayList = csvStateCensusDAOMap.values().stream()
                .sorted(CSVCensusDAO.getSortComparator(mode))
                .map(csvCensusDAO -> csvCensusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(arrayList);
    }

    //ENUM FOR COUNTRY
    public enum Country {INDIA, US}

    //ENUM FOR SORTING MODE
    public enum SortingMode {STATE, POPULATION, AREA, DENSITY}
}

