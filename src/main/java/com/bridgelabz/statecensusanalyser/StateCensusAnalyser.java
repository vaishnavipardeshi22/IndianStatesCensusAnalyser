package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.exception.StateCensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

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

    //FUNCTION TO LOAD CSV DATA AND COUNT NUMBER OF RECORDS IN CSV FILE
    public int loadCSVDataFile(String csvFilePath) throws StateCensusAnalyserException {
        int numberOfRecords = 0;
        String fileFormat = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!fileFormat.equals("csv")) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_TYPE, "Incorrect file type");
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStateCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<CSVStateCensus> csvStateCensusIterator = csvToBean.iterator();

            while (csvStateCensusIterator.hasNext()) {
                numberOfRecords++;
                csvStateCensusIterator.next();
            }
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER, "Incorrect file.");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, "Incorrect file.");
        } catch (IOException e) {
            e.getStackTrace();
        }
        return numberOfRecords;
    }
}
