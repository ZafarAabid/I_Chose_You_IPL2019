package com.ipl;

import lombok.*;
@Getter
@Setter
@ToString

public class IplPlayersDAO {
    public String playerName="";
    public Double rating =0.0;

    public Double playersRun =0.0;
    public Double playersBattingAvg=0.0;
    public Double playersStrikeRate=0.0;
    public Integer players6s=0;
    public Integer players4s=0;
    public Integer playerBallsFaced=0;

    public Double playersMatches=0.0;
    public String playersInnings="";
    public Double playerOvers=0.0;
    public Integer RunGivenByPlayer=99;
    public Integer playersWkts=0;
    public String playerBBI="";
    public Double playersBwolingAvg=99.0;
    public Double playersEcon=0.0;
    public Double players4w=0.0;
    public Integer players5w=0;
    public Double playersBowlingStrikeRate=0.0;

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
         RunGivenByPlayer = bowlerData.playersRunGiven;
         playersWkts = bowlerData.playersWkts;
         playerBBI = bowlerData.playerBBI;
         playersBwolingAvg = bowlerData.playersAvg;
         playersEcon = bowlerData.playersEcon;
         players4w = bowlerData.players4w;
         players5w = bowlerData.players5w;
         playersBowlingStrikeRate = bowlerData.playersSR;

    }
}
