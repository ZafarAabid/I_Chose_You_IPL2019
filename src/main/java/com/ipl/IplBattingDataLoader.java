package com.ipl;

import com.csvBuilder.CSVBuilderException;
import com.csvBuilder.CSVBuilderFactory;
import com.csvBuilder.ICSVBuilder;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class IplBattingDataLoader implements IplLoader{
    public List getDataFile(Reader reader) throws CSVBuilderException {
        List<IplPlayersDAO> dataList = new ArrayList();
        ICSVBuilder csvbuilder = CSVBuilderFactory.createCsvbuilder();
        Iterator<IplBatsmanData> IplIterator = csvbuilder.getCsvFileIterator(reader, IplBatsmanData.class);
        Iterable<IplBatsmanData> csvIterable = () -> IplIterator;
        StreamSupport.stream(csvIterable.spliterator(), false)
                .map(IplBatsmanData.class::cast)
                .forEach(batsmanData -> dataList.add(new IplPlayersDAO(batsmanData)));
        return dataList;
    }
}
