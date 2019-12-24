package com.ipl;

import com.csvBuilder.CSVBuilderException;

import java.io.Reader;
import java.util.List;

public class IplDataLoaderFactory {

    public enum DataFor {
        BATTING {
            public IplLoader loadData(Reader reader) throws CSVBuilderException {
                return new IplBattingDataLoader();
            }
        }, BOWLING {
            public IplLoader loadData(Reader reader) throws CSVBuilderException {
                return new IplBowlingDataLoader();
            }
        };
        public abstract IplLoader loadData(Reader reader) throws CSVBuilderException;
    }

    public IplLoader getIplDataFor(DataFor field,Reader reader) throws CSVBuilderException {
        return field.loadData(reader);
    }
}
