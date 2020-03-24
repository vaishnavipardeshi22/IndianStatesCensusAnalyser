package com.bridgelabz.statecensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    @CsvBindByName(column = "State", required = true)
    private String state;

    @CsvBindByName(column = "Population", required = true)
    private String population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    private String areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    private String densityPerSqKm;

    public String getState() {
        return state;
    }

    public String getPopulation() {
        return population;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public String getDensityPerSqKm() {
        return densityPerSqKm;
    }
}
