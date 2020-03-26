package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.exception.StateCensusAnalyserException;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {

    Map<String, CSVStateCensusDAO> csvStateCensusDAOMap = null;

    public StateCensusAnalyser() {
        this.csvStateCensusDAOMap = new HashMap<String, CSVStateCensusDAO>();
    }

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("******************** WELCOME TO INDIAN STATES CENSUS ANALYSER ********************");
    }

    //FUNCTION TO LOAD CSV DATA AND COUNT NUMBER OF RECORDS IN STATE CENSUS CSV FILE
    public int loadCSVDataFileForStateCensusData(String csvFilePath) throws StateCensusAnalyserException {
        int numberOfRecords = 0;
        String fileFormat = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!fileFormat.equals("csv"))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_TYPE,
                                                    "Incorrect file type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCensus> csvStateCensusIterator = csvBuilder.getCSVIterator(reader, CSVStateCensus.class);
            Iterable<CSVStateCensus> stateCensusIterable = () -> csvStateCensusIterator;
            StreamSupport.stream(stateCensusIterable.spliterator(), false)
                    .forEach(censusCSV -> csvStateCensusDAOMap.put(censusCSV.state, new CSVStateCensusDAO(censusCSV)));
            numberOfRecords = csvStateCensusDAOMap.size();
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER,
                                                    "Incorrect delimiter or header.");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, "Incorrect file.");
        } catch (CSVBuilderException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return numberOfRecords;
    }

    //FUNCTION TO LOAD CSV DATA AND COUNT NUMBER OF RECORDS IN STATE CODE CSV FILE
    public int loadCSVDataFileForStateCodeData(String stateCodeDataCsvFilePath) throws StateCensusAnalyserException {
        int numberOfRecords = 0;
        String fileFormat = stateCodeDataCsvFilePath.substring(stateCodeDataCsvFilePath.lastIndexOf(".") + 1);
        if (!fileFormat.equals("csv"))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_TYPE,
                                                    "Incorrect file type");
        try (Reader reader = Files.newBufferedReader(Paths.get(stateCodeDataCsvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCode> csvStateCodeIterator = csvBuilder.getCSVIterator(reader, CSVStateCode.class);
            Iterable<CSVStateCode> csvIterable = () -> csvStateCodeIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> csvStateCensusDAOMap.get(csvState.state) != null)
                    .forEach(csvState -> csvStateCensusDAOMap.get(csvState.state).stateCode = csvState.stateCode);
            numberOfRecords = csvStateCensusDAOMap.size();
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER,
                                                    "Incorrect delimiter or header.");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, "Incorrect file.");
        } catch (CSVBuilderException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberOfRecords;
    }

    //FUNCTION FOR SORTING
    private<T> void sortCSVData(Comparator<T> censusCSVComparator, List<T> csvList) {
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
        Comparator<CSVStateCensusDAO> censusCSVComparator = Comparator.comparing(csvStateCensus -> csvStateCensus.state);
        List<CSVStateCensusDAO> censusDAOList = csvStateCensusDAOMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(censusCSVComparator, censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    //FUNCTION TO SORT CENSUS DATA BY POPULATION
    public String getSortedPopulationWiseCensusData() throws StateCensusAnalyserException {
        if (csvStateCensusDAOMap == null || csvStateCensusDAOMap.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No census data");
        Comparator<CSVStateCensusDAO> censusCSVComparator = Comparator.comparing(csvStateCensus -> csvStateCensus.population);
        List<CSVStateCensusDAO> censusDAOList = csvStateCensusDAOMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(censusCSVComparator, censusDAOList);
        Collections.reverse(censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    public String getSortedPopulationDensityWiseCensusData() throws StateCensusAnalyserException{
        if (csvStateCensusDAOMap == null || csvStateCensusDAOMap.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No census data");
        Comparator<CSVStateCensusDAO> censusCSVComparator = Comparator.comparing(csvStateCensus -> csvStateCensus.densityPerSqKm);
        List<CSVStateCensusDAO> censusDAOList = csvStateCensusDAOMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(censusCSVComparator, censusDAOList);
        Collections.reverse(censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    public String getSortedStateAreaWiseCensusData() throws StateCensusAnalyserException {
        if (csvStateCensusDAOMap == null || csvStateCensusDAOMap.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No census data");
        Comparator<CSVStateCensusDAO> censusCSVComparator = Comparator.comparing(csvStateCensus -> csvStateCensus.areaInSqKm);
        List<CSVStateCensusDAO> censusDAOList = csvStateCensusDAOMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(censusCSVComparator, censusDAOList);
        Collections.reverse(censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }
}
