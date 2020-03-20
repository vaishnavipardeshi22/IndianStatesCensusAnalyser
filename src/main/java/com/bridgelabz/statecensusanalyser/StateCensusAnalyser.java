package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.exception.StateCensusAnalyserException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("******************** WELCOME TO INDIAN STATES CENSUS ANALYSER ********************");
    }

    //FUNCTION TO LOAD CSV DATA AND COUNT NUMBER OF RECORDS IN STATE CENSUS CSV FILE
    public int loadCSVDataFileForStateCensusData(String csvFilePath) throws StateCensusAnalyserException {
        String fileFormat = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!fileFormat.equals("csv")) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_TYPE,
                                                    "Incorrect file type");
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCensus> csvCensusIterator = csvBuilder.getCSVIterator(reader, CSVStateCensus.class);
            return this.getCount(csvCensusIterator);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER,
                                                    "Incorrect delimiter or header.");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE,
                                                    "Incorrect file.");
        } catch (IOException e) {
            e.getStackTrace();
        }
        return 0;
    }

    //FUNCTION TO LOAD CSV DATA AND COUNT NUMBER OF RECORDS IN STATE CODE CSV FILE
    public int loadCSVDataFileForStateCodeData(String stateCodeDataCsvFilePath) throws StateCensusAnalyserException {
        int numberOfRecords = 0;
        String fileFormat = stateCodeDataCsvFilePath.substring(stateCodeDataCsvFilePath.lastIndexOf(".") + 1);
        if (!fileFormat.equals("csv")) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_TYPE,
                                                    "Incorrect file type");
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(stateCodeDataCsvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCode> csvCensusIterator = csvBuilder.getCSVIterator(reader, CSVStateCode.class);
            return this.getCount(csvCensusIterator);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER,
                                                    "Incorrect delimiter or header.");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE,
                                                    "Incorrect file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
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
}
