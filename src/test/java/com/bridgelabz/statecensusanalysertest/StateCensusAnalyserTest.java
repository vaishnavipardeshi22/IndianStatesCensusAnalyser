package com.bridgelabz.statecensusanalysertest;

import com.bridgelabz.exception.StateCensusAnalyserException;
import com.bridgelabz.statecensusanalyser.CSVStateCensus;
import com.bridgelabz.statecensusanalyser.CSVStateCode;
import com.bridgelabz.statecensusanalyser.StateCensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {

    private static final String STATE_CENSUS_DATA_CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static final String STATE_CENSUS_DATA_CSV_FILE_INCORRECT_PATH = "src/test/resources/stateCensusData.csv";
    private static final String STATE_CENSUS_DATA_CSV_INCORRECT_FILE_TYPE = "src/test/resources/StateCensusData.pdf";
    private static final String STATE_CENSUS_DATA_CSV_INCORRECT_DELIMITER_FILE_TYPE = "src/test/resources/StateCensusDataIncorrectDelimiter.csv";
    private static final String STATE_CENSUS_DATA_CSV_INCORRECT_HEADER_FILE = "src/test/resources/StateCensusDataIncorrectHeader.csv";
    private static final String STATE_CODE_DATA_CSV_FILE_PATH = "src/test/resources/StateCode.csv";
    private static final String STATE_CODE_DATA_CSV_FILE_INCORRECT_PATH = "src/test/resources/stateCode.csv";
    private static final String STATE_CODE_DATA_CSV_FILE_INCORRECT_TYPE = "src/test/resources/StateCode.pdf";
    private static final String STATE_CODE_DATA_CSV_FILE_INCORRECT_DELIMITER = "src/test/resources/StateCodeIncorrectDelimiter.csv";
    private static final String STATE_CODE_DATA_CSV_FILE_INCORRECT_HEADER = "src/test/resources/StateCodeIncorrectHeader.csv";
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
            stateCensusAnalyser.loadCSVDataFileForStateCensusData(STATE_CENSUS_DATA_CSV_INCORRECT_FILE_TYPE);
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

    @Test
    public void givenIncorrectIndianStatesCodeCSVFileType_WhenUnmatch_ThenThrowCustomException() {
        try {
            stateCensusAnalyser.loadCSVDataFileForStateCodeData(STATE_CODE_DATA_CSV_FILE_INCORRECT_TYPE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenIncorrectDelimiterIndianStatesCodeCSVFile_WhenUnmatch_ThenThrowCustomException() {
        try {
            stateCensusAnalyser.loadCSVDataFileForStateCodeData(STATE_CODE_DATA_CSV_FILE_INCORRECT_DELIMITER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIncorrectHeaderIndianStatesCodeCSVFile_WhenUnmatch_ThenThrowCustomException() {
        try {
            stateCensusAnalyser.loadCSVDataFileForStateCodeData(STATE_CODE_DATA_CSV_FILE_INCORRECT_HEADER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedState_thenReturnSortedResult() {
        try {
            stateCensusAnalyser.loadCSVDataFileForStateCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH);
            stateCensusAnalyser.loadCSVDataFileForStateCodeData(STATE_CODE_DATA_CSV_FILE_PATH);
            String sortedStateCensusData = stateCensusAnalyser.getSortedStateWiseCensusData();
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedStateCensusData, CSVStateCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenNoCensusData_ThenThrowException() {
        try {
            String sortedStateCensusData = stateCensusAnalyser.getSortedStateWiseCensusData();
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedStateCensusData, CSVStateCensus[].class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenStateCodeData_WhenSortedCode_ThenReturnSortedResult() {
        try {
            stateCensusAnalyser.loadCSVDataFileForStateCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH);
            stateCensusAnalyser.loadCSVDataFileForStateCodeData(STATE_CODE_DATA_CSV_FILE_PATH);
            String sortedStateCodeData = stateCensusAnalyser.getSortedStateWiseCensusData();
            CSVStateCode[] csvStateCodes = new Gson().fromJson(sortedStateCodeData, CSVStateCode[].class);
            Assert.assertEquals("AP", csvStateCodes[0].stateCode);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeData_WhenNoCodeData_ThenThrowException() {
        try {
            String sortedStateCodeData = stateCensusAnalyser.getSortedStateWiseCensusData();
            CSVStateCode[] codeCSV = new Gson().fromJson(sortedStateCodeData, CSVStateCode[].class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedByPopulation_ThenReturnSortedResult() {
        try {
            stateCensusAnalyser.loadCSVDataFileForStateCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH);
            String sortedPopulationWiseCensusData = stateCensusAnalyser.getSortedPopulationWiseCensusData();
            CSVStateCensus[] csvStateCensus = new Gson().fromJson(sortedPopulationWiseCensusData, CSVStateCensus[].class);
            Assert.assertEquals(199812341, csvStateCensus[0].population);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedByPopulationDensity_ThenReturnSortedResult() {
        try {
            stateCensusAnalyser.loadCSVDataFileForStateCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH);
            String sortedPopulationDensityWiseCensusData = stateCensusAnalyser.getSortedPopulationDensityWiseCensusData();
            CSVStateCensus[] csvStateCensus = new Gson().fromJson(sortedPopulationDensityWiseCensusData, CSVStateCensus[].class);
            Assert.assertEquals(1102, csvStateCensus[0].densityPerSqKm);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
