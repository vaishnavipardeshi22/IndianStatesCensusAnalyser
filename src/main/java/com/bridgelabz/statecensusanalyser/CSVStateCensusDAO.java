package com.bridgelabz.statecensusanalyser;

public class CSVStateCensusDAO {

    public String state;
    public String stateCode;
    public int areaInSqKm;
    public int densityPerSqKm;
    public int population;

    public CSVStateCensusDAO(CSVStateCensus stateCensus) {
        state = stateCensus.state;
        areaInSqKm = stateCensus.areaInSqKm;
        densityPerSqKm = stateCensus.densityPerSqKm;
        population = stateCensus.population;
    }
}
