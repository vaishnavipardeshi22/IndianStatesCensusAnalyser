package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.exception.StateCensusAnalyserException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

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
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<CSVStateCensus> csvStateCensusList = csvBuilder.getCSVList(reader, CSVStateCensus.class);
            return csvStateCensusList.size();
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER,
                                                    "Incorrect delimiter or header.");
        } catch(NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE,
                                                    "Incorrect file.");
        } catch(CSVBuilderException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return 0;
    }

    //FUNCTION TO LOAD CSV DATA AND COUNT NUMBER OF RECORDS IN STATE CODE CSV FILE
    public int loadCSVDataFileForStateCodeData(String stateCodeDataCsvFilePath) throws StateCensusAnalyserException {
        String fileFormat = stateCodeDataCsvFilePath.substring(stateCodeDataCsvFilePath.lastIndexOf(".") + 1);
        if (!fileFormat.equals("csv")) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_TYPE,
                                                    "Incorrect file type");
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(stateCodeDataCsvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<CSVStateCode> csvStateCensusList = csvBuilder.getCSVList(reader, CSVStateCode.class);
            return csvStateCensusList.size();
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER,
                                                    "Incorrect delimiter or header.");
        } catch(NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE,
                                                    "Incorrect file.");
        } catch(CSVBuilderException e) {
            e.getStackTrace();
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
