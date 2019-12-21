package com.ipl;

import com.csvBuilder.CSVBuilderException;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IPLAnalyser {

    public <E> List loadData(IplDataLoaderFactory.DataFor dataForTheField, String iplDataCsvFile) throws IPLAnalyserException {
        if (new File(iplDataCsvFile).length() == 0 | new File(iplDataCsvFile) == null) {
            throw new IPLAnalyserException("Given file is either empty or null", IPLAnalyserException.ExceptionType.NO_SUCH_FILE_ERROR);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(iplDataCsvFile))) {
             List<IplPlayersDAO> dataList =IplDataLoaderFactory.getIplDataFor(dataForTheField,reader);
            return dataList;
        } catch (IOException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    public  List<IplPlayersDAO> sortByParamter(List<IplPlayersDAO> iplDataList,SortingParamters... parameter) {

        Comparator<IplPlayersDAO> comparator = ComparatorParameters.getComparator(parameter[0]);
        if (parameter.length > 1)
            for (int i = 1; i < parameter.length; i++) {
                comparator = comparator.thenComparing(ComparatorParameters.getComparator(parameter[i]));
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
}
