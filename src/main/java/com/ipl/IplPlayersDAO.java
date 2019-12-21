package com.ipl;

import lombok.*;
@Getter
@Setter
@ToString

public class IplPlayersDAO {
    public String playerName;
    public Double playersRun;
    public Double playersBattingAvg;
    public Double playersStrikeRate;
    public Integer players6s;
    public Integer players4s;
    public Integer playerBallsFaced;

    public Double playersMatches;
    public String playersInnings;
    public Double playerOvers;
    public Integer RunGivenByPlayer;
    public Integer playersWkts;
    public String playerBBI;
    public Double playersBwolingAvg;
    public Double playersEcon;
    public Double players4w;
    public Integer players5w;
    public Double playersBowlingStrikeRate;


    public IplPlayersDAO() {
    }

    public IplPlayersDAO(IplBatsmanData batsmanData) {
        this.playerName = batsmanData.playerName;
        this.playersRun = batsmanData.playersRun;
        this.playersBattingAvg = batsmanData.playersAvg;
        this.playersStrikeRate = batsmanData.playersStrikeRate;
        this.players6s = batsmanData.players6s;
        this.players4s = batsmanData.players4s;
        this.playerBallsFaced = batsmanData.playerBallsFaced;
    }

    public IplPlayersDAO(IplBowlerData bowlerData) {
        this.playerName = bowlerData.playerName;
        playersMatches = bowlerData.playersMatches;
         playersInnings = bowlerData.playersInnings;
         playerOvers = bowlerData.playerOvers;
         RunGivenByPlayer = bowlerData.playersRun;
         playersWkts = bowlerData.playersWkts;
         playerBBI = bowlerData.playerBBI;
         playersBwolingAvg = bowlerData.playersAvg;
         playersEcon = bowlerData.playersEcon;
         players4w = bowlerData.players4w;
         players5w = bowlerData.players5w;
         playersBowlingStrikeRate = bowlerData.playersSR;

    }
}
