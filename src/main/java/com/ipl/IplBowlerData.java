package com.ipl;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class IplBowlerData {

    @CsvBindByName(column = "PLAYER")
    public String playerName;

    @CsvBindByName(column = "Mat")
    public Integer playersMatches;

    @CsvBindByName(column = "Inns")
    public Integer playersInnings;

    @CsvBindByName(column = "Ov")
    public Double playerOvers;

    @CsvBindByName(column = "Runs")
    public Integer playersRunGiven;

    @CsvBindByName(column = "Wkts")
    public Integer playersWkts;

    @CsvBindByName(column = "BBI")
    public Integer playerBBI;

    @CsvBindByName(column = "Avg")
    public Double playersAvg;

    @CsvBindByName(column = "Econ")
    public Double playersEcon;

    @CsvBindByName(column = "4w")
    public Double players4w;

    @CsvBindByName(column = "5w")
    public Integer players5w;

    @CsvBindByName(column = "SR")
    public Double playersSR;

    public IplBowlerData(String playerName, Integer playersMatches, Integer playersInnings, Double playerOvers,
                         Integer playersRunGiven, Integer playersWkts, Integer playerBBI, Double playersAvg,
                         Double playersEcon, Double players4w, Integer players5w, Double playersSR) {
        this.playerName = playerName;
        this.playersMatches = playersMatches;
        this.playersInnings = playersInnings;
        this.playerOvers = playerOvers;
        this.playersRunGiven = playersRunGiven;
        this.playersWkts = playersWkts;
        this.playerBBI = playerBBI;
        this.playersAvg = playersAvg;
        this.playersEcon = playersEcon;
        this.players4w = players4w;
        this.players5w = players5w;
        this.playersSR = playersSR;
    }
}
