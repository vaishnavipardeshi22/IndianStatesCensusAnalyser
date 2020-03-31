package com.bridgelabz.statecensusanalysertest;

import com.bridgelabz.exception.StateCensusAnalyserException;
import com.bridgelabz.statecensusanalyser.*;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import static com.bridgelabz.statecensusanalyser.StateCensusAnalyser.Country.INDIA;
import static com.bridgelabz.statecensusanalyser.StateCensusAnalyser.Country.US;

public class StateCensusAnalyserTest {

    private static final String INDIAN_STATE_CENSUS_DATA_CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static final String INDIAN_STATE_CENSUS_DATA_CSV_FILE_INCORRECT_PATH = "src/test/resources/stateCensusData.csv";
    private static final String INDIAN_STATE_CENSUS_DATA_CSV_INCORRECT_FILE_TYPE = "src/test/resources/StateCensusData.pdf";
    private static final String INDIAN_STATE_CENSUS_DATA_CSV_INCORRECT_DELIMITER_FILE_TYPE = "src/test/resources/StateCensusDataIncorrectDelimiter.csv";
    private static final String INDIAN_STATE_CENSUS_DATA_CSV_INCORRECT_HEADER_FILE = "src/test/resources/StateCensusDataIncorrectHeader.csv";
    private static final String INDIAN_STATE_CODE_DATA_CSV_FILE_PATH = "src/test/resources/StateCode.csv";
    private static final String INDIAN_STATE_CODE_DATA_CSV_FILE_INCORRECT_PATH = "src/test/resources/stateCode.csv";
    private static final String INDIAN_STATE_CODE_DATA_CSV_FILE_INCORRECT_TYPE = "src/test/resources/StateCode.pdf";
    private static final String INDIAN_STATE_CODE_DATA_CSV_FILE_INCORRECT_DELIMITER = "src/test/resources/StateCodeIncorrectDelimiter.csv";
    private static final String INDIAN_STATE_CODE_DATA_CSV_FILE_INCORRECT_HEADER = "src/test/resources/StateCodeIncorrectHeader.csv";
    private static final String US_CENSUS_DATA_CSV_FILE_PATH = "src/test/resources/USCensusData.csv";
    StateCensusAnalyser indianCensusAnalyser = new StateCensusAnalyser(INDIA);
    StateCensusAnalyser usCensusAnalyser = new StateCensusAnalyser(US);

    @Test
    public void givenIndianStatesCensusCSVFile_WhenMatchNoOfRecord_ThenReturnTrue() {
        try {
            int numberOfRecord = indianCensusAnalyser.loadStateCensusCSVData(INDIA,
                                                                            INDIAN_STATE_CENSUS_DATA_CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenIncorrectIndianStatesCensusCSVFile_WhenUnmatch_ThenThrowCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA,
                                                        INDIAN_STATE_CENSUS_DATA_CSV_FILE_INCORRECT_PATH);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIncorrectIndianStatesCensusCSVFileType_WhenUnmatch_ThenThrowCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA,
                                                        INDIAN_STATE_CENSUS_DATA_CSV_INCORRECT_FILE_TYPE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenIncorrectDelimiterIndianStatesCensusCSVFile_WhenUnmatch_ThenThrowCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA,
                                                        INDIAN_STATE_CENSUS_DATA_CSV_INCORRECT_DELIMITER_FILE_TYPE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIncorrectHeaderIndianStatesCensusCSVFile_WhenUnmatch_ThenThrowCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA,
                                                        INDIAN_STATE_CENSUS_DATA_CSV_INCORRECT_HEADER_FILE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianStatesCodeCSVFile_WhenMatchNoOfRecord_ThenReturnTrue() {
        try {
            int numberOfRecord = indianCensusAnalyser.loadStateCensusCSVData(INDIA,
                                                                            INDIAN_STATE_CENSUS_DATA_CSV_FILE_PATH,
                                                                            INDIAN_STATE_CODE_DATA_CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIncorrectIndianStatesCodeCSVFile_WhenUnmatch_ThenThrowCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA,
                                                        INDIAN_STATE_CODE_DATA_CSV_FILE_INCORRECT_PATH);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIncorrectIndianStatesCodeCSVFileType_WhenUnmatch_ThenThrowCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA,
                                                        INDIAN_STATE_CODE_DATA_CSV_FILE_INCORRECT_TYPE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenIncorrectDelimiterIndianStatesCodeCSVFile_WhenUnmatch_ThenThrowCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA,
                                                        INDIAN_STATE_CODE_DATA_CSV_FILE_INCORRECT_DELIMITER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIncorrectHeaderIndianStatesCodeCSVFile_WhenUnmatch_ThenThrowCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA,
                                                        INDIAN_STATE_CODE_DATA_CSV_FILE_INCORRECT_HEADER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedState_thenReturnSortedResult() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA,
                                                        INDIAN_STATE_CENSUS_DATA_CSV_FILE_PATH);
            String sortedStateCensusData = indianCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedStateCensusData, CSVStateCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenNoCensusData_ThenThrowException() {
        try {
            String sortedStateCensusData = indianCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedStateCensusData, CSVStateCensus[].class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedByPopulation_ThenReturnSortedResult() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA,
                                                        INDIAN_STATE_CENSUS_DATA_CSV_FILE_PATH);
            String sortedPopulationWiseCensusData = indianCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            CSVStateCensus[] csvStateCensus = new Gson().fromJson(sortedPopulationWiseCensusData, CSVStateCensus[].class);
            Assert.assertEquals(199812341, csvStateCensus[0].population);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedByPopulationDensity_ThenReturnSortedResult() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA,
                                                        INDIAN_STATE_CENSUS_DATA_CSV_FILE_PATH);
            String sortedPopulationDensityWiseCensusData = indianCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.DENSITY);
            CSVStateCensus[] csvStateCensus = new Gson().fromJson(sortedPopulationDensityWiseCensusData, CSVStateCensus[].class);
            Assert.assertEquals(1102, csvStateCensus[0].densityPerSqKm, 0);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedByStateArea_ThenReturnSortedResult() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA,
                                                        INDIAN_STATE_CENSUS_DATA_CSV_FILE_PATH);
            String sortedStateAreaWiseCensusData = indianCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.AREA);
            CSVStateCensus[] csvStateCensus = new Gson().fromJson(sortedStateAreaWiseCensusData, CSVStateCensus[].class);
            Assert.assertEquals(342239, csvStateCensus[0].areaInSqKm, 0);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenMatchNoOfRecord_ThenReturnTrue() {
        try {
            int numberOfRecords = usCensusAnalyser.loadStateCensusCSVData(US, US_CENSUS_DATA_CSV_FILE_PATH);
            Assert.assertEquals(51, numberOfRecords);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedByPopulation_ThenReturnSortedResult() {
        try {
            usCensusAnalyser.loadStateCensusCSVData(US, US_CENSUS_DATA_CSV_FILE_PATH);
            String sortedPopulationWiseCensusData = usCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            CSVUSCensus[] csvusCensuses = new Gson().fromJson(sortedPopulationWiseCensusData, CSVUSCensus[].class);
            Assert.assertEquals(37253956, csvusCensuses[0].population);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
