package com.ipl;

import com.csvBuilder.CSVBuilderException;
import com.csvBuilder.CSVBuilderFactory;
import com.csvBuilder.ICSVBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IPLAnalyser {

    public <E> List<E> loadData(Class<E> iplDataClass, String iplDataCsvFile) throws IPLAnalyserException {
        List dataList = new ArrayList();
        if (new File(iplDataCsvFile).length() == 0 | new File(iplDataCsvFile) == null) {
            throw new IPLAnalyserException("Given file is either empty or null", IPLAnalyserException.ExceptionType.NO_SUCH_FILE_ERROR);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(iplDataCsvFile))) {
            ICSVBuilder csvbuilder = CSVBuilderFactory.createCsvbuilder();
            Iterator IplIterator = csvbuilder.getCsvFileIterator(reader, iplDataClass);
            Iterable csvIterable = () -> IplIterator;

            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(iplDataClass::cast)
                    .forEach(PlayerData -> dataList.add(PlayerData));
            return dataList;

        } catch (IOException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    public <E> List<E> sortByParamter(List<E> iplDataList, ComparatorParameters.BattingParameter... parameter) {
        Comparator comparator = ComparatorParameters.getComparator(parameter[0]);
        if (parameter.length > 1)
            for (int i = 1; i < parameter.length; i++) {
                comparator = comparator.thenComparing(ComparatorParameters.getComparator(parameter[i]));
            }
        List dataList = (List) iplDataList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        dataList.forEach(System.out::println);
        return dataList;
    }

    public List<IplBatsmanData> strikeRateBasedOnBoundries(List<IplBatsmanData> iplDataList, ComparatorParameters.BattingParameter... battingParameter) {
        for (IplBatsmanData batsmanData : iplDataList
        ) {
            batsmanData.setPlayersStrikeRate(((double) batsmanData.getPlayers6s() * 6 + batsmanData.getPlayers4s() * 4) / batsmanData.playerBallsFaced);
        }
        return sortByParamter(iplDataList, battingParameter);
    }
}
