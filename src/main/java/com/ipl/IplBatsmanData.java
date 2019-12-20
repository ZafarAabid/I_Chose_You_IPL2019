package com.ipl;

import com.opencsv.bean.CsvBindByName;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@ToString

public class IplBatsmanData {
    @CsvBindByName(column = "PLAYER")
    public String playerName;

    @CsvBindByName(column = "Runs")
    public Double playersRun;

    @CsvBindByName(column = "Avg")
    public Double playersAvg;

    @CsvBindByName(column = "StrikeRate")
    public Double playersStrikeRate;

    @CsvBindByName(column = "6s")
    public Integer players6s;

    @CsvBindByName(column = "4s")
    public Integer players4s;

    @CsvBindByName(column = "BF")
    public Integer playerBallsFaced;

    public IplBatsmanData(String playerName, Double playersRun, Double playersAvg, Double playersStrikeRate, Integer players6s, Integer players4s, Integer playerBallsFaced) {
        this.playerName = playerName;
        this.playersRun = playersRun;
        this.playersAvg = playersAvg;
        this.playersStrikeRate = playersStrikeRate;
        this.players6s = players6s;
        this.players4s = players4s;
        this.playerBallsFaced = playerBallsFaced;
    }
}
