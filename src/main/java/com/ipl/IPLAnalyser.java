package com.ipl;

import com.csvBuilder.CSVBuilderException;
import com.csvBuilder.CSVBuilderFactory;
import com.csvBuilder.ICSVBuilder;

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
    public List loadBattingData(Class<IplBatsmanData> className, String iplBattingDataCsvFile) throws IPLAnalyserException {
        List dataList = new ArrayList();
        try (Reader reader = Files.newBufferedReader(Paths.get(iplBattingDataCsvFile))) {
            ICSVBuilder csvbuilder = CSVBuilderFactory.createCsvbuilder();
            Iterator IndiaStateCodeIterator = csvbuilder.getCsvFileIterator(reader, className);
            Iterable csvIterable = () -> IndiaStateCodeIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IplBatsmanData.class::cast)
                    .forEach(IplBatsmanData -> dataList.add(IplBatsmanData));
            return dataList;
        } catch (IOException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    public List<IplBatsmanData> sortByParamter(List<IplBatsmanData> iplDataList, ComparatorParameters.Parameter parameter) {

        Comparator comparator = ComparatorParameters.getComparator(parameter);
        List dataList = (List) iplDataList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        iplDataList.forEach(System.out::println);
        return dataList;
    }

}
