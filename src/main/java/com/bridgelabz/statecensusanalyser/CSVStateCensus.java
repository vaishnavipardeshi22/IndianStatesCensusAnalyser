package com.bridgelabz.statecensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public double areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public double densityPerSqKm;

    public CSVStateCensus() {
    }

    public CSVStateCensus(String state, int population, double areaInSqKm, double densityPerSqKm) {
        this.state = state;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.densityPerSqKm = densityPerSqKm;
    }
}
