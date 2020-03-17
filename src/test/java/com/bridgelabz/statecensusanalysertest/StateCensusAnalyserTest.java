package com.bridgelabz.statecensusanalysertest;

import com.bridgelabz.exception.StateCensusAnalyserException;
import com.bridgelabz.statecensusanalyser.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {

    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
    private static final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static final String CSV_FILE_INCORRECT_PATH = "src/test/resources/stateCensusData.csv";

    @Test
    public void givenIndianStatesCensusCSVFile_WhenMatchNoOfRecord_ThenReturnTrue() {
        try {
            int numberOfRecord = stateCensusAnalyser.loadCSVDataFile(CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (IOException | StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenIncorrectIndianStatesCensusCSVFile_WhenMatchNoOfRecord_ThenThrowCustomException() {
        try {
            int numberOfRecord = stateCensusAnalyser.loadCSVDataFile(CSV_FILE_INCORRECT_PATH);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
