package com.bridgelabz.statecensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVUSCensus {
    @CsvBindByName(column = " State Id", required = true)
    public String stateId;

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "Total area", required = true)
    public double totalArea;

    @CsvBindByName(column = "Population Density", required = true)
    public double populationDensity;

    public CSVUSCensus() {
    }

    public CSVUSCensus(String stateId, String state, int population, double totalArea, double populationDensity) {
        this.stateId = stateId;
        this.state = state;
        this.population = population;
        this.totalArea = totalArea;
        this.populationDensity = populationDensity;
    }
}
