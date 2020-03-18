package com.bridgelabz.statecensusanalysertest;

import com.bridgelabz.exception.StateCensusAnalyserException;
import com.bridgelabz.statecensusanalyser.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {

    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
    private static final String STATE_CENSUS_ANALYSER_CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static final String STATE_CENSUS_ANALYSER_CSV_FILE_INCORRECT_PATH = "src/test/resources/stateCensusData.csv";
    private static final String STATE_CENSUS_ANALYSER_CSV_INCORRECT_FILE_TYPE = "src/test/resources/stateCensusData.pdf";
    private static final String STATE_CENSUS_ANALYSER_CSV_INCORRECT_DELIMITER_FILE_TYPE = "src/test/resources/StateCensusDataIncorrectDelimiter.csv";

    @Test
    public void givenIndianStatesCensusCSVFile_WhenMatchNoOfRecord_ThenReturnTrue() {
        try {
            int numberOfRecord = stateCensusAnalyser.loadCSVDataFile(STATE_CENSUS_ANALYSER_CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenIncorrectIndianStatesCensusCSVFile_WhenUnmatch_ThenThrowCustomException() {
        try {
            stateCensusAnalyser.loadCSVDataFile(STATE_CENSUS_ANALYSER_CSV_FILE_INCORRECT_PATH);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIncorrectIndianStatesCensusCSVFileType_WhenUnmatch_ThenThrowCustomException() {
        try {
            int numberOfRecord = stateCensusAnalyser.loadCSVDataFile(STATE_CENSUS_ANALYSER_CSV_INCORRECT_FILE_TYPE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenIncorrectDelimiterIndianStatesCensusCSVFile_WhenUnmatch_ThenThrowCustomException() {
        try {
            stateCensusAnalyser.loadCSVDataFile(STATE_CENSUS_ANALYSER_CSV_INCORRECT_DELIMITER_FILE_TYPE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER, e.type);
        }
    }
}
