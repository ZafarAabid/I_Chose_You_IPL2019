package com.ipl;

import com.opencsv.bean.CsvBindByName;
//POS,PLAYER              ,Mat,Inns,NO,Runs,HS  ,Avg  ,BF ,SR    ,100,50,4s,6s
public class IplBatsmanData {
    @CsvBindByName(column = "PLAYER")
    public String PlayerName;

    @CsvBindByName(column = "Runs")
    public Double PlayersRun;

    @CsvBindByName(column = "Avg")
    public String PlayersAvg;

    @CsvBindByName(column = "StrikeRate")
    public Double PlayersStrikeRate;

    @CsvBindByName(column = "6s")
    public Integer Players6s;

    @CsvBindByName(column = "4s")
    public Integer Players4s;


    public IplBatsmanData() {
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public Double getPlayersRun() {
        return PlayersRun;
    }

    public void setPlayersRun(Double playersRun) {
        PlayersRun = playersRun;
    }

    public String getPlayersAvg() {
        return PlayersAvg;
    }

    public void setPlayersAvg(String playersAvg) {
        PlayersAvg = playersAvg;
    }

    public Double getPlayersStrikeRate() {
        return PlayersStrikeRate;
    }

    public void setPlayersStrikeRate(Double playersStrikeRate) {
        PlayersStrikeRate = playersStrikeRate;
    }

    public Integer getPlayers6s() {
        return Players6s;
    }

    public void setPlayers6s(Integer players6s) {
        Players6s = players6s;
    }

    public Integer getPlayers4s() {
        return Players4s;
    }

    public void setPlayers4s(Integer players4s) {
        Players4s = players4s;
    }

    public IplBatsmanData(String playerName, Double playersRun, String playersAvg, Double playersStrikeRate, Integer players6s, Integer players4s) {
        PlayerName = playerName;
        PlayersRun = playersRun;
        PlayersAvg = playersAvg;
        PlayersStrikeRate = playersStrikeRate;
        Players6s = players6s;
        Players4s = players4s;
    }

    @Override
    public String toString() {
        return "IplBatsmanData{" +
                "PlayerName='" + PlayerName + '\'' +
                ", PlayersRun='" + PlayersRun + '\'' +
                ", PlayersAvg='" + PlayersAvg + '\'' +
                ", PlayersStrikeRate='" + PlayersStrikeRate + '\'' +
                ", Players6s='" + Players6s + '\'' +
                ", Players4s='" + Players4s + '\'' +
                '}';
    }
}
