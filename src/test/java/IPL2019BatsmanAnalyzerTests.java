import com.csvBuilder.CSVBuilderException;
import com.ipl.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/* IPL_SAMPLE_BATTING_CSV_FILE_DATA_FOR_VALIDATION
POS,PLAYER              ,Mat  ,Inns  ,NO  ,Runs  ,HS   ,Avg    ,BF            ,StrikeRate  ,100  ,50  ,4s  ,6s
0  ,Andre Russell       ,14   ,13    ,4   ,510   ,80*  ,56.66  ,249           ,204.81      ,0    ,4   ,31  ,52
1  ,Virat Kohli         ,14   ,14    ,0   ,464   ,100  ,33.14  ,328           ,141.46      ,1    ,2   ,46  ,13
2 ,AB de Villiers       ,13   ,13    ,3   ,442   ,82*  ,44.2   ,287           ,154         ,0    ,5   ,31  ,26
3 ,MS Dhoni             ,15   ,12    ,7   ,416   ,84*  ,83.2   ,309           ,134.62      ,2    ,3   ,22  ,23
4 ,Moeen Ali            ,11   ,10    ,2   ,220   ,66   ,27.5   ,133           ,165.41      ,2    ,2   ,16  ,17
*/

public class IPL2019BatsmanAnalyzerTests {
    public static final String IPL_BATTING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019FactsheetMostRuns.csv";
    public static final String IPL_BATTING_DATA_CSV_FILE_WITH_WRONG_FILETYPE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019FactsheetMostRuns.txt";
    public static final String IPL_BATTING_DATA_CSV_FILE_WITH_NullFILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019NullFile.txt";
    public static final String IPL_BATTING_DATA_CSV_FILE_WITH_WRONG_DELIMETER = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019NullFile.txt";
    public static final String IPL_SAMPLE_BATTING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019SampleFactsheetMostRuns.csv";
    public static final String IPL_BOWLING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019FactsheetMostWkts.csv";

    DataSorting dataSorting = new DataSorting();
    @Test
    public void forGivenCsv_WhenFetchTheData_IfSuccessfullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData( IPL_BATTING_DATA_CSV_FILE);
            iplDataList.forEach(System.out::println);
            Assert.assertEquals("David Warner", (iplDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenCsvSample_WhenFetchTheData_IfSuccessfullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData( IPL_SAMPLE_BATTING_DATA_CSV_FILE);
            Assert.assertEquals("Andre Russell", (iplDataList.get(0).playerName).trim());
            Assert.assertEquals("Virat Kohli", (iplDataList.get(1).playerName).trim());
            Assert.assertEquals("AB de Villiers", (iplDataList.get(2).playerName).trim());
            Assert.assertEquals("MS Dhoni", (iplDataList.get(3).playerName).trim());
            Assert.assertEquals("Moeen Ali", (iplDataList.get(4).playerName).trim());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenCsv_WithWrongFileType_shouldThrowException() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData( IPL_BATTING_DATA_CSV_FILE_WITH_WRONG_FILETYPE);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.NO_SUCH_CLASS,e.type );
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenCsv_WithWrongNullFile_shouldThrowException() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData( IPL_BATTING_DATA_CSV_FILE_WITH_NullFILE);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.NO_SUCH_FILE_ERROR);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenCsv_WithWrongDelimeter_shouldThrowException() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData( IPL_BATTING_DATA_CSV_FILE_WITH_WRONG_DELIMETER);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.NO_SUCH_FILE_ERROR);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenCsv_WhenFetchTheData_IfSortedByBattingAveragefullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData( IPL_BATTING_DATA_CSV_FILE);
            iplDataList = dataSorting.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.AVERAGE);
            Assert.assertEquals("Tim Southee", (iplDataList.get(iplDataList.size() - 1).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenSampleCsv_WhenFetchTheData_IfSortedByBattingAveragefullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData( IPL_SAMPLE_BATTING_DATA_CSV_FILE);
            iplDataList = dataSorting.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.AVERAGE);
            Assert.assertEquals("Moeen Ali", (iplDataList.get(4).playerName).trim());
            Assert.assertEquals("Virat Kohli", (iplDataList.get(3).playerName).trim());
            Assert.assertEquals("AB de Villiers", (iplDataList.get(2).playerName).trim());
            Assert.assertEquals("Andre Russell", (iplDataList.get(1).playerName).trim());
            Assert.assertEquals("MS Dhoni", (iplDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenCsv_WhenFetchTheData_IfSortedByStrikeRatefullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData( IPL_BATTING_DATA_CSV_FILE);
            iplDataList = dataSorting.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.STRIKE_RATE);
            Assert.assertEquals("Ishant Sharma", (iplDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenTwoCsv_WhenCombinedTheData_SortByAllRounder_ReturnBestPlayer() {
        IPLAnalyser iplBattingAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        IPLAnalyser iplBowlingAnalyzer = new IPLAnalyser(new IplBowlingDataLoader());

        try {
            List<IplPlayersDAO> batsmanDataList = iplBattingAnalyzer.loadData(IPL_BATTING_DATA_CSV_FILE);
            List<IplPlayersDAO> bowlersDataList = iplBowlingAnalyzer.loadData(IPL_BOWLING_DATA_CSV_FILE);
            List<IplPlayersDAO> mergedIplData = iplBattingAnalyzer.mergingData(batsmanDataList, bowlersDataList);
            batsmanDataList = dataSorting.bestBattingWithBowlingAverage(mergedIplData, ComparatorParameters.BattingParameter.BATTING_WITH_BOWLING_AVERAGE);
            Assert.assertEquals("Harpreet Brar", (batsmanDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenSampleCsv_WhenFetchTheData_IfSortedByStrikeRatefullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IPL_SAMPLE_BATTING_DATA_CSV_FILE);
            iplDataList = dataSorting.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.STRIKE_RATE);
            Assert.assertEquals("MS Dhoni", (iplDataList.get(4).playerName).trim());
            Assert.assertEquals("Virat Kohli", (iplDataList.get(3).playerName).trim());
            Assert.assertEquals("AB de Villiers", (iplDataList.get(2).playerName).trim());
            Assert.assertEquals("Moeen Ali", (iplDataList.get(1).playerName).trim());
            Assert.assertEquals("Andre Russell", (iplDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenCsv_WhenFetchTheData_IfSortedBy6sAnd4sReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IPL_BATTING_DATA_CSV_FILE);
            iplDataList = dataSorting.strikeRateBasedOnBoundries(iplDataList, ComparatorParameters.BattingParameter.STRIKE_RATE_BASED_ON_6s4s);
            Assert.assertEquals("Ishant Sharma", (iplDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenSampleCsv_WhenFetchTheData_IfSortedBy6sAnd4sReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IPL_SAMPLE_BATTING_DATA_CSV_FILE);
            iplDataList = dataSorting.strikeRateBasedOnBoundries(iplDataList, ComparatorParameters.BattingParameter.STRIKE_RATE_BASED_ON_6s4s);
            Assert.assertEquals("Andre Russell", (iplDataList.get(0).playerName).trim());
            Assert.assertEquals("Moeen Ali", (iplDataList.get(1).playerName).trim());
            Assert.assertEquals("AB de Villiers", (iplDataList.get(2).playerName).trim());
            Assert.assertEquals("Virat Kohli", (iplDataList.get(3).playerName).trim());
            Assert.assertEquals("MS Dhoni", (iplDataList.get(4).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenCsv_WhenFetchTheData_IfSortedByAverageWithStrikeRateReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IPL_BATTING_DATA_CSV_FILE);
            iplDataList = dataSorting.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.AVERAGE, ComparatorParameters.BattingParameter.STRIKE_RATE);
            Assert.assertEquals("Tim Southee", (iplDataList.get(iplDataList.size() - 1).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenSampleCsv_WhenFetchTheData_IfSortedByAverageWithStrikeRateReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IPL_SAMPLE_BATTING_DATA_CSV_FILE);
            iplDataList = dataSorting.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.AVERAGE, ComparatorParameters.BattingParameter.STRIKE_RATE);
            Assert.assertEquals("Moeen Ali", (iplDataList.get(4).playerName).trim());
            Assert.assertEquals("Virat Kohli", (iplDataList.get(3).playerName).trim());
            Assert.assertEquals("AB de Villiers", (iplDataList.get(2).playerName).trim());
            Assert.assertEquals("Andre Russell", (iplDataList.get(1).playerName).trim());
            Assert.assertEquals("MS Dhoni", (iplDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenCsv_WhenFetchTheData_IfSortedByMaxRunWithBestAverageRateReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IPL_BATTING_DATA_CSV_FILE);
            iplDataList = dataSorting.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.MAX_RUNS, ComparatorParameters.BattingParameter.AVERAGE);
            Assert.assertEquals("Tim Southee", (iplDataList.get(iplDataList.size() - 1).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenSampleCsv_WhenFetchTheData_IfSortedByMaxRunWithBestAverageRateReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser(new IplBattingDataLoader());
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IPL_SAMPLE_BATTING_DATA_CSV_FILE);
            iplDataList = dataSorting.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.MAX_RUNS, ComparatorParameters.BattingParameter.AVERAGE);
            Assert.assertEquals("Andre Russell", (iplDataList.get(0).playerName).trim());
            Assert.assertEquals("Virat Kohli", (iplDataList.get(1).playerName).trim());
            Assert.assertEquals("AB de Villiers", (iplDataList.get(2).playerName).trim());
            Assert.assertEquals("MS Dhoni", (iplDataList.get(3).playerName).trim());
            Assert.assertEquals("Moeen Ali", (iplDataList.get(4).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
}
