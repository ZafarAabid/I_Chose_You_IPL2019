package com.ipl;

import com.csvBuilder.CSVBuilderException;

import java.io.Reader;
import java.util.List;

public interface IplLoader {
    List<IplPlayersDAO> getDataFile(Reader reader) throws CSVBuilderException;
}
