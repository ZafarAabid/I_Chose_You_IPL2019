package com.ipl;

public class PlayersDAO {
    public String PlayerName;

    public Double PlayersRun;

    public String PlayersAvg;

    public Double PlayersStrikeRate;

    public Integer Players6s;

    public Integer Players4s;

    public PlayersDAO(IplBatsmanData iplBatsmanData) {
        PlayerName = iplBatsmanData.PlayerName;
        PlayersRun = iplBatsmanData.PlayersRun;
        PlayersAvg = iplBatsmanData.PlayersAvg;
        PlayersStrikeRate = iplBatsmanData.PlayersStrikeRate;
        Players6s = iplBatsmanData.Players6s;
        Players4s = iplBatsmanData.Players4s;
    }

    @Override
    public String toString() {
        return "PlayersDAO{" +
                "PlayerName='" + PlayerName + '\'' +
                ", PlayersRun=" + PlayersRun +
                ", PlayersAvg='" + PlayersAvg + '\'' +
                ", PlayersStrikeRate=" + PlayersStrikeRate +
                ", Players6s=" + Players6s +
                ", Players4s=" + Players4s +
                '}';
    }
}
