package com.bridgelabz.statecensusanalysertest;

import com.bridgelabz.statecensusanalyser.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {

    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
    private static final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";

    @Test
    public void givenIndianStatesCensusCSVFile_WhenMatchNoOfRecord_ThenReturnTrue() {
        try {
            int numberOfRecord = stateCensusAnalyser.loadCSVDataFile(CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecord);
        }
        catch (IOException e){
            e.getStackTrace();
        }
    }
}
