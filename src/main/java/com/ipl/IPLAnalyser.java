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
    public  List loadBattingData(Class className, String iplBattingDataCsvFile) throws IPLAnalyserException {
        List dataList = new ArrayList();
        if (new File(iplBattingDataCsvFile).length()==0 | new File(iplBattingDataCsvFile)==null){
            throw new IPLAnalyserException("Given file is either empty or null",IPLAnalyserException.ExceptionType.NO_SUCH_FILE_ERROR);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(iplBattingDataCsvFile))) {
            ICSVBuilder csvbuilder = CSVBuilderFactory.createCsvbuilder();
            Iterator IplIterator = csvbuilder.getCsvFileIterator(reader, className);
            Iterable csvIterable = () -> IplIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IplBatsmanData.class::cast)
                    .forEach(PlayerData -> dataList.add(PlayerData));
            return dataList;
        } catch (IOException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    public List<IplBatsmanData> sortByParamter(List<IplBatsmanData> iplDataList, ComparatorParameters.Parameter... parameter) {
        Comparator comparator = ComparatorParameters.getComparator(parameter[0]);
        if (parameter.length > 1) {
            comparator = ComparatorParameters.getComparator(parameter[0]).thenComparing(ComparatorParameters.getComparator(parameter[1]));
        }
        List dataList = (List) iplDataList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        dataList.forEach(System.out::println);
        return dataList;
    }

    public List<IplBatsmanData> strikeRateBasedOnBoundries(List<IplBatsmanData> iplDataList, ComparatorParameters.Parameter... parameter) {
        for (IplBatsmanData batsmanData : iplDataList
        ) {
            batsmanData.setPlayersStrikeRate(((double) batsmanData.getPlayers6s() * 6 + batsmanData.getPlayers4s() * 4) / batsmanData.getPlayers6s() + batsmanData.getPlayers4s());
        }
        return sortByParamter(iplDataList, parameter);
    }


    public List<IplBowlerData> loadBowlingData(Class<IplBowlerData> iplBowlerDataClass, String iplBowlingDataCsvFile) throws IPLAnalyserException {
        List dataList = new ArrayList();
        if (new File(iplBowlingDataCsvFile).length()==0 | new File(iplBowlingDataCsvFile)==null){
            throw new IPLAnalyserException("Given file is either empty or null",IPLAnalyserException.ExceptionType.NO_SUCH_FILE_ERROR);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(iplBowlingDataCsvFile))) {
            ICSVBuilder csvbuilder = CSVBuilderFactory.createCsvbuilder();
            Iterator IplIterator = csvbuilder.getCsvFileIterator(reader, iplBowlerDataClass);
            Iterable csvIterable = () -> IplIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IplBowlerData.class::cast)
                    .forEach(PlayerData -> dataList.add(PlayerData));
            return dataList;
        } catch (IOException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}
