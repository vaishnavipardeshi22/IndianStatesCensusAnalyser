package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.exception.StateCensusAnalyserException;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class StateCensusAnalyser {

    List<CSVStateCensus> csvStateCensusList = null;
    List<CSVStateCode> csvStateCodeList = null;

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
            csvStateCensusList = csvBuilder.getCSVList(reader, CSVStateCensus.class);
            numberOfRecords = csvStateCensusList.size();
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER,
                                                    "Incorrect delimiter or header.");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE,
                                                    "Incorrect file.");
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
            csvStateCodeList = csvBuilder.getCSVList(reader, CSVStateCode.class);
            numberOfRecords = csvStateCodeList.size();
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER,
                                                    "Incorrect delimiter or header.");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE,
                                                    "Incorrect file.");
        } catch (CSVBuilderException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberOfRecords;
    }

    //FUNCTION TO COUNT NUMBER OF RECORDS
    private <T> int getCount(Iterator<T> iterator) {
        int numberOfRecords = 0;
        while (iterator.hasNext()) {
            numberOfRecords++;
            iterator.next();
        }
        return numberOfRecords;
    }

    //FUNCTION TO SORT STATE CENSUS DATA BY STATE NAME
    public String getSortedStateWiseCensusData() throws StateCensusAnalyserException {
        if (csvStateCensusList == null || csvStateCensusList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA,
                                                    "No census data");
        Comparator<CSVStateCensus> censusCSVComparator = Comparator.comparing(csvStateCensus -> csvStateCensus.state);
        this.sortCSVData(censusCSVComparator, csvStateCensusList);
        String sortedStateCensusJson = new Gson().toJson(csvStateCensusList);
        return sortedStateCensusJson;

    }

    //FUNCTION TO SORT STATE CODE DATA BY STATE CODE
    public String getSortedStateCodeWiseData() throws StateCensusAnalyserException {
        if (csvStateCodeList == null || csvStateCodeList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA,
                                                    "No census data");
        Comparator<CSVStateCode> stateCodeComparator = Comparator.comparing(CSVStateCode -> CSVStateCode.stateCode);
        this.sortCSVData(stateCodeComparator, csvStateCodeList);
        String sortedStateCodeJson = new Gson().toJson(csvStateCodeList);
        return sortedStateCodeJson;
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


}
