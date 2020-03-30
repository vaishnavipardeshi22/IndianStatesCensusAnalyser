package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.exception.StateCensusAnalyserException;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class StateCensusAnalyser {

    List<CSVCensusDAO> csvCensusDAOList = null;
    Map<String, CSVCensusDAO> csvStateCensusDAOMap = null;

    public StateCensusAnalyser() {
        this.csvStateCensusDAOMap = new HashMap<>();
    }

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("******************** WELCOME TO INDIAN STATES CENSUS ANALYSER ********************");
    }

    public int loadStateCensusCSVData(Country country, String... csvFilePath) throws StateCensusAnalyserException {
        csvStateCensusDAOMap = CensusAdapterFactory.getCensusData(country, csvFilePath);
        csvCensusDAOList = csvStateCensusDAOMap.values().stream().collect(Collectors.toList());
        return csvStateCensusDAOMap.size();
    }

    //FUNCTION FOR SORTING
    private <T> void sortCSVData(Comparator<T> censusCSVComparator, List<T> csvList) {
        for (int index1 = 0; index1 < csvList.size() - 1; index1++) {
            for (int index2 = 0; index2 < csvList.size() - index1 - 1; index2++) {
                T census1 = csvList.get(index2);
                T census2 = csvList.get(index2 + 1);
                if (censusCSVComparator.compare(census1, census2) > 0) {
                    csvList.set(index2, census2);
                    csvList.set(index2 + 1, census1);
                }
            }
        }
    }

    //FUNCTION TO SORT STATE CENSUS DATA
    public String getSortedStateWiseCensusData() throws StateCensusAnalyserException {
        if (csvStateCensusDAOMap == null || csvStateCensusDAOMap.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No census data");
        Comparator<CSVCensusDAO> censusCSVComparator = Comparator.comparing(csvStateCensus -> csvStateCensus.state);
        List<CSVCensusDAO> censusDAOList = csvStateCensusDAOMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(censusCSVComparator, censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    //FUNCTION TO SORT CENSUS DATA BY POPULATION
    public String getSortedPopulationWiseCensusData() throws StateCensusAnalyserException {
        if (csvStateCensusDAOMap == null || csvStateCensusDAOMap.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No census data");
        Comparator<CSVCensusDAO> censusCSVComparator = Comparator.comparing(csvStateCensus -> csvStateCensus.population);
        List<CSVCensusDAO> censusDAOList = csvStateCensusDAOMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(censusCSVComparator, censusDAOList);
        Collections.reverse(censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    //FUNCTION TO SORT CENSUS DATA BY POPULATION DENSITY
    public String getSortedPopulationDensityWiseCensusData() throws StateCensusAnalyserException {
        if (csvStateCensusDAOMap == null || csvStateCensusDAOMap.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No census data");
        Comparator<CSVCensusDAO> censusCSVComparator = Comparator.comparing(csvStateCensus -> csvStateCensus.densityPerSqKm);
        List<CSVCensusDAO> censusDAOList = csvStateCensusDAOMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(censusCSVComparator, censusDAOList);
        Collections.reverse(censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    //FUNCTION TO SORT CENSUS DATA BY AREA
    public String getSortedStateAreaWiseCensusData() throws StateCensusAnalyserException {
        if (csvStateCensusDAOMap == null || csvStateCensusDAOMap.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No census data");
        Comparator<CSVCensusDAO> censusCSVComparator = Comparator.comparing(csvStateCensus -> csvStateCensus.areaInSqKm);
        List<CSVCensusDAO> censusDAOList = csvStateCensusDAOMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(censusCSVComparator, censusDAOList);
        Collections.reverse(censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    public enum Country {INDIA, US}
}

