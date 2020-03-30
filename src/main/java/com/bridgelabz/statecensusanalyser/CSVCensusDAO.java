package com.bridgelabz.statecensusanalyser;

public class CSVCensusDAO {

    public String state;
    public String stateCode;
    public double areaInSqKm;
    public double densityPerSqKm;
    public long population;

    public CSVCensusDAO(CSVStateCensus indianCensusCSV) {
        this.state = indianCensusCSV.state;
        this.areaInSqKm = indianCensusCSV.areaInSqKm;
        this.densityPerSqKm = indianCensusCSV.densityPerSqKm;
        this.population = indianCensusCSV.population;
    }

    public CSVCensusDAO(CSVUSCensus usCensusCSV) {
        this.state = usCensusCSV.usState;
        this.stateCode = usCensusCSV.stateId;
        this.areaInSqKm = usCensusCSV.totalArea;
        this.densityPerSqKm = usCensusCSV.populationDensity;
        this.population = usCensusCSV.usPopulation;
    }
}
