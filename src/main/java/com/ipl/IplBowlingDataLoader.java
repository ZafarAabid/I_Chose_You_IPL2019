package com.ipl;

import com.csvBuilder.CSVBuilderException;
import com.csvBuilder.CSVBuilderFactory;
import com.csvBuilder.ICSVBuilder;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class IplBowlingDataLoader implements IplLoader{
    public List getDataFile(Reader reader) throws CSVBuilderException {

        List<IplPlayersDAO> dataList = new ArrayList();
        ICSVBuilder csvbuilder = CSVBuilderFactory.createCsvbuilder();
        Iterator<IplBowlerData> IplIterator = csvbuilder.getCsvFileIterator(reader, IplBowlerData.class);
        Iterable<IplBowlerData> csvIterable = () -> IplIterator;
        StreamSupport.stream(csvIterable.spliterator(), false)
                .map(IplBowlerData.class::cast)
                .forEach(bowlerData -> dataList.add(new IplPlayersDAO(bowlerData)));
        return dataList;
    }
}
