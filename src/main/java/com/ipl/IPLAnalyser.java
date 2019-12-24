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

    private final IplLoader iplLoader;

    public IPLAnalyser(IplLoader iplLoader) {
        this.iplLoader = iplLoader;
    }
    DataSorting dataSorting = new DataSorting();
    DataMerging dataMerging =  new DataMerging();

    public List loadData(String iplDataCsvFile) throws IPLAnalyserException, CSVBuilderException {
        if (new File(iplDataCsvFile).length() == 0 | new File(iplDataCsvFile) == null) {
            throw new IPLAnalyserException("Given file is either empty or null", IPLAnalyserException.ExceptionType.NO_SUCH_FILE_ERROR);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(iplDataCsvFile))) {
            List<IplPlayersDAO> dataList = this.iplLoader.getDataFile(reader);
            return dataList;
        } catch (IOException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public List<IplPlayersDAO> mergingData(List<IplPlayersDAO> firstFieldDataList, List<IplPlayersDAO> secondFieldDataList) {
        return dataMerging.mergeData(firstFieldDataList,secondFieldDataList);
    }

    public List<IplPlayersDAO> sortByParamter(List<IplPlayersDAO> iplDataList, SortingParamters... parameter) {
        return dataSorting.sortByParamter(iplDataList, parameter);
    }

    public List<IplPlayersDAO> strikeRateBasedOnBoundries(List<IplPlayersDAO> iplDataList, ComparatorParameters.BattingParameter... battingParameter) {
        return dataSorting.strikeRateBasedOnBoundries(iplDataList, battingParameter);
    }

    public List<IplPlayersDAO> allRounder(List<IplPlayersDAO> iplDataList, ComparatorParameters.BowlingParameter... battingParameter) {
        return dataSorting.allRounder(iplDataList, battingParameter);
    }

    public List<IplPlayersDAO> bestBattingWithBowlingAverage(List<IplPlayersDAO> iplDataList, ComparatorParameters.BattingParameter... battingParameter) {
        return dataSorting.bestBattingWithBowlingAverage(iplDataList,battingParameter);
    }
    }
