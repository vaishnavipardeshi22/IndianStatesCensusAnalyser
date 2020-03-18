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
}
