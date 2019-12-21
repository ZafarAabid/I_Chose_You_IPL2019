package com.ipl;

import com.csvBuilder.CSVBuilderException;

import java.io.Reader;
import java.util.List;

public class IplDataLoaderFactory {

    public enum DataFor {
        BATTING {
            public List loadData(Reader reader) throws CSVBuilderException {
                return IplBattingLoader.getDataFile(reader);
            }
        }, BOWLING {
            public List loadData(Reader reader) throws CSVBuilderException {
                return IplBowlingDataLoader.getDataFile(reader);
            }
        };
        public abstract List loadData(Reader reader) throws CSVBuilderException;
    }

    public static List getIplDataFor(DataFor field,Reader reader) throws CSVBuilderException {
        return field.loadData(reader);
    }
}
