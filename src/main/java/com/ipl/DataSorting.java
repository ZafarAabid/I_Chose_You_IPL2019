package com.ipl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DataSorting {
    ComparatorParameters comparatorParameters = new ComparatorParameters();
    public List<IplPlayersDAO> sortByParamter(List<IplPlayersDAO> iplDataList, SortingParamters... parameter) {

        Comparator<IplPlayersDAO> comparator = comparatorParameters.getComparator(parameter[0]);
        if (parameter.length > 1)
            for (int i = 1; i < parameter.length; i++) {
                comparator = comparator.thenComparing(comparatorParameters.getComparator(parameter[i]));
            }
        List dataList = iplDataList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        return dataList;
    }

    public List<IplPlayersDAO> strikeRateBasedOnBoundries(List<IplPlayersDAO> iplDataList, ComparatorParameters.BattingParameter... battingParameter) {
        for (IplPlayersDAO batsmanData : iplDataList
        ) {
            batsmanData.setPlayersStrikeRate(((double) batsmanData.getPlayers6s() * 6 + batsmanData.getPlayers4s() * 4) / batsmanData.playerBallsFaced);
        }
        return sortByParamter(iplDataList, battingParameter);
    }

    public List<IplPlayersDAO> bestBattingWithBowlingAverage(List<IplPlayersDAO> iplDataList, ComparatorParameters.BattingParameter... battingParameter) {
        for (IplPlayersDAO playersDAO : iplDataList
        ) {
            playersDAO.setRating(playersDAO.playersBattingAvg/playersDAO.playersBwolingAvg);
        }
        return sortByParamter(iplDataList, battingParameter);
    }

    public List<IplPlayersDAO> allRounder(List<IplPlayersDAO> iplDataList, ComparatorParameters.BowlingParameter... battingParameter) {
        for (IplPlayersDAO playersDAO : iplDataList
        ) {
            playersDAO.setRating(playersDAO.playersBattingAvg*playersDAO.playersWkts);
        }
        return sortByParamter(iplDataList, battingParameter);
    }

}
