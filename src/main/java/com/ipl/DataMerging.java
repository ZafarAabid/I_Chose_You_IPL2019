package com.ipl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataMerging {


    public List<IplPlayersDAO> mergeData(List<IplPlayersDAO> firstFieldDataList, List<IplPlayersDAO> secondFieldDataList) {

        Map<String, IplPlayersDAO> firstDataList = firstFieldDataList.stream().collect(Collectors.toMap(IplPlayersDAO::getPlayerName, iplPlayersDAO -> iplPlayersDAO));
        Map<String, IplPlayersDAO> secondDataList = secondFieldDataList.stream().collect(Collectors.toMap(IplPlayersDAO::getPlayerName, iplPlayersDAO -> iplPlayersDAO));
        for (IplPlayersDAO data : firstDataList.values()) {
            if (((secondDataList.containsKey(data.getPlayerName())))) {
                IplPlayersDAO secondData = secondDataList.get(data.getPlayerName());
                data.setPlayersBowlingStrikeRate(secondData.getPlayersBowlingStrikeRate());
                data.setPlayerBallsFaced(secondData.getPlayerBallsFaced());
                data.setPlayerBBI(secondData.getPlayerBBI());
                data.setPlayerOvers(secondData.getPlayerOvers());
                data.setPlayers4w(secondData.getPlayers4w());
                data.setPlayers5w(secondData.getPlayers5w());
                data.setPlayersBwolingAvg(secondData.getPlayersBwolingAvg());
                data.setPlayersEcon(secondData.getPlayersEcon());
                data.setPlayersMatches(secondData.getPlayersMatches());
                data.setPlayersInnings(secondData.getPlayersInnings());
                data.setRunGivenByPlayer(secondData.getRunGivenByPlayer());
                data.setPlayersWkts(secondData.getPlayersWkts());
            }
        }
        List<IplPlayersDAO> daoList = new ArrayList(firstDataList.values());
        return daoList;
    }

}
