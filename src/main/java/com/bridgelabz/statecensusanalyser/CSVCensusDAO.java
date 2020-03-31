package com.bridgelabz.statecensusanalyser;

import java.util.Comparator;

public class CSVCensusDAO {

    public String state;
    public String stateCode;
    public double areaInSqKm;
    public double densityPerSqKm;
    public int population;

    public CSVCensusDAO(CSVStateCensus indianCensusCSV) {
        this.state = indianCensusCSV.state;
        this.areaInSqKm = indianCensusCSV.areaInSqKm;
        this.densityPerSqKm = indianCensusCSV.densityPerSqKm;
        this.population = indianCensusCSV.population;
    }

    public CSVCensusDAO(CSVUSCensus usCensusCSV) {
        this.state = usCensusCSV.state;
        this.stateCode = usCensusCSV.stateId;
        this.areaInSqKm = usCensusCSV.totalArea;
        this.densityPerSqKm = usCensusCSV.populationDensity;
        this.population = usCensusCSV.population;
    }

    public static Comparator<CSVCensusDAO> getSortComparator(StateCensusAnalyser.SortingMode mode) {
        if (mode.equals(StateCensusAnalyser.SortingMode.STATE))
            return Comparator.comparing(csvCensusDAO -> csvCensusDAO.state);
        if (mode.equals(StateCensusAnalyser.SortingMode.POPULATION))
            return Comparator.comparing(CSVCensusDAO::getPopulation).reversed();
        if (mode.equals(StateCensusAnalyser.SortingMode.AREA))
            return Comparator.comparing(CSVCensusDAO::getAreaInSqKm).reversed();
        if (mode.equals(StateCensusAnalyser.SortingMode.DENSITY))
            return Comparator.comparing(CSVCensusDAO::getDensityPerSqKm).reversed();
        return null;
    }

    public int getPopulation() {
        return population;
    }

    public double getAreaInSqKm() {
        return areaInSqKm;
    }

    public double getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public Object getCensusDTO(StateCensusAnalyser.Country country) {
        if (country.equals(StateCensusAnalyser.Country.INDIA))
            return new CSVStateCensus(state, population, areaInSqKm, densityPerSqKm);
        if (country.equals(StateCensusAnalyser.Country.US))
            return new CSVUSCensus(stateCode, state, population, areaInSqKm, densityPerSqKm);
        return null;
    }
}
