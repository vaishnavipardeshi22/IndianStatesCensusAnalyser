package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.exception.StateCensusAnalyserException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {
    public abstract Map<String, CSVCensusDAO> loadCensusData(String... csvFilePath) throws StateCensusAnalyserException;

    public <T> Map<String, CSVCensusDAO> loadCensusData(Class<T> censusCSVClass, String csvFilePath) throws StateCensusAnalyserException {
        Map<String, CSVCensusDAO> csvCensusDAOMap = new HashMap<>();
        String fileFormat = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!fileFormat.equals("csv"))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_TYPE,
                                                    "Incorrect file type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<T> csvStateCensusIterator = csvBuilder.getCSVIterator(reader, censusCSVClass);
            Iterable<T> stateCensusIterable = () -> csvStateCensusIterator;
            if (censusCSVClass.getName().equals("com.bridgelabz.statecensusanalyser.CSVStateCensus")) {
                StreamSupport.stream(stateCensusIterable.spliterator(), false)
                        .map(CSVStateCensus.class::cast)
                        .forEach(censusCSV -> csvCensusDAOMap.put(censusCSV.state, new CSVCensusDAO(censusCSV)));
            } else if (censusCSVClass.getName().equals("com.bridgelabz.statecensusanalyser.CSVUSCensus")) {
                StreamSupport.stream(stateCensusIterable.spliterator(), false)
                        .map(CSVUSCensus.class::cast)
                        .forEach(censusCSV -> csvCensusDAOMap.put(censusCSV.usState, new CSVCensusDAO(censusCSV)));
            }
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_DELIMITER_OR_HEADER,
                                                    "Incorrect delimiter or header.");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, "Incorrect file.");
        } catch (CSVBuilderException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvCensusDAOMap;
    }
}
