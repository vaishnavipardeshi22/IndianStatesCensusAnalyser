package com.bridgelabz.statecensusanalysertest;

import com.bridgelabz.exception.StateCensusAnalyserException;
import com.bridgelabz.statecensusanalyser.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {

    private static final String STATE_CENSUS_DATA_CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static final String STATE_CENSUS_DATA_CSV_FILE_INCORRECT_PATH = "src/test/resources/stateCensusData.csv";
    private static final String STATE_CENSUS_DATA_CSV_INCORRECT_FILE_TYPE = "src/test/resources/stateCensusData.pdf";
    private static final String STATE_CENSUS_DATA_CSV_INCORRECT_DELIMITER_FILE_TYPE = "src/test/resources/StateCensusDataIncorrectDelimiter.csv";
    private static final String STATE_CENSUS_DATA_CSV_INCORRECT_HEADER_FILE = "src/test/resources/StateCensusDataIncorrectHeader.csv";
    private static final String STATE_CODE_DATA_CSV_FILE_PATH = "src/test/resources/StateCode.csv";
    private static final String STATE_CODE_DATA_CSV_FILE_INCORRECT_PATH = "src/test/resources/stateCode.csv";
    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();

    @Test
    public void givenIndianStatesCensusCSVFile_WhenMatchNoOfRecord_ThenReturnTrue() {
        try {
            int numberOfRecord = stateCensusAnalyser.loadCSVDataFileForStateCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenIncorrectIndianStatesCensusCSVFile_WhenUnmatch_ThenThrowCustomException() {
        try {
            stateCensusAnalyser.loadCSVDataFileForStateCensusData(STATE_CENSUS_DATA_CSV_FILE_INCORRECT_PATH);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIncorrectIndianStatesCensusCSVFileType_WhenUnmatch_ThenThrowCustomException() {
        try {
            int numberOfRecord = stateCensusAnalyser.loadCSVDataFileForStateCensusData(STATE_CENSUS_DATA_CSV_INCORRECT_FILE_TYPE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenIncorrectDelimiterIndianStatesCensusCSVFile_WhenUnmatch_ThenThrowCustomException() {
        try {
            stateCensusAnalyser.loadCSVDataFileForStateCensusData(STATE_CENSUS_DATA_CSV_INCORRECT_DELIMITER_FILE_TYPE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIncorrectHeaderIndianStatesCensusCSVFile_WhenUnmatch_ThenThrowCustomException() {
        try {
            stateCensusAnalyser.loadCSVDataFileForStateCensusData(STATE_CENSUS_DATA_CSV_INCORRECT_HEADER_FILE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianStatesCodeCSVFile_WhenMatchNoOfRecord_ThenReturnTrue() {
            try {
                int numberOfRecord = stateCensusAnalyser.loadCSVDataFileForStateCodeData(STATE_CODE_DATA_CSV_FILE_PATH);
                Assert.assertEquals(37, numberOfRecord);
            } catch (StateCensusAnalyserException e) {
                e.printStackTrace();
            }
    }

    @Test
    public void givenIncorrectIndianStatesCodeCSVFile_WhenUnmatch_ThenThrowCustomException() {
        try {
            stateCensusAnalyser.loadCSVDataFileForStateCodeData(STATE_CODE_DATA_CSV_FILE_INCORRECT_PATH);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

}

