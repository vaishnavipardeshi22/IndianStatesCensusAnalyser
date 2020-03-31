package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.exception.StateCensusAnalyserException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndianCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CSVCensusDAO> loadCensusData(String[] csvFilePath) throws StateCensusAnalyserException {
        Map<String, CSVCensusDAO> censusDAOMap = super.loadCensusData(CSVStateCensus.class, csvFilePath[0]);
        if (csvFilePath.length == 1)
            return censusDAOMap;
        return loadStateCodeCSVData(censusDAOMap, csvFilePath[1]);
    }

    private Map<String, CSVCensusDAO> loadStateCodeCSVData(Map<String, CSVCensusDAO> censusDAOMap, String csvFilePath)
                                        throws StateCensusAnalyserException {
        String fileFormat = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!fileFormat.equals("csv"))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_TYPE,
                                                    "Incorrect file type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCode> csvStateCodeIterator = csvBuilder.getCSVIterator(reader, CSVStateCode.class);
            Iterable<CSVStateCode> csvIterable = () -> csvStateCodeIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvStateCode -> censusDAOMap.get(csvStateCode.state) != null)
                    .forEach(csvStateCode -> censusDAOMap.get(csvStateCode.state).stateCode = csvStateCode.stateCode);
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
        return censusDAOMap;
    }
}
