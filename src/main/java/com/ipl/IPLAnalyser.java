package com.ipl;

import com.csvBuilder.CSVBuilderException;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class IPLAnalyser {

    public <E> List loadData(IplDataLoaderFactory.DataFor dataForTheField, String iplDataCsvFile) throws IPLAnalyserException {
        if (new File(iplDataCsvFile).length() == 0 | new File(iplDataCsvFile) == null) {
            throw new IPLAnalyserException("Given file is either empty or null", IPLAnalyserException.ExceptionType.NO_SUCH_FILE_ERROR);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(iplDataCsvFile))) {
            List<IplPlayersDAO> dataList = IplDataLoaderFactory.getIplDataFor(dataForTheField, reader);
            return dataList;
        } catch (IOException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    public List<IplPlayersDAO> mergingData(List<IplPlayersDAO> firstFieldDataList, List<IplPlayersDAO> secondFieldDataList) {
        Map<String, IplPlayersDAO> firstDataList = firstFieldDataList.stream().collect(Collectors.toMap(IplPlayersDAO::getPlayerName, iplPlayersDAO -> iplPlayersDAO));
        Map<String, IplPlayersDAO> secondDataList = secondFieldDataList.stream().collect(Collectors.toMap(IplPlayersDAO::getPlayerName, iplPlayersDAO -> iplPlayersDAO));
        for (IplPlayersDAO data : firstDataList.values()) {

            if ((( secondDataList.containsKey( data.getPlayerName())))) {
                IplPlayersDAO secondData =secondDataList.get(data.getPlayerName());
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
