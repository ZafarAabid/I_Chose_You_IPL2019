import com.ipl.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/* IPL_SAMPLE_BATTING_CSV_FILE_DATA_FOR_VALIDATION
POS,PLAYER          ,Mat ,Inns,Ov    ,Runs,Wkts,BBI,Avg    ,Econ  ,SR    ,4w ,5w,NullFile
1  ,Imran Tahir     ,17  ,17  ,64.2  ,431 ,26  ,0  ,16.57  ,6.69  ,14.84 ,2  ,0 ,
2  ,Yuzvendra Chahal,14  ,14  ,49.2  ,386 ,18  ,0  ,21.44  ,7.82  ,16.44 ,1  ,0 ,
3  ,Harbhajan Singh ,11  ,11  ,44    ,312 ,16  ,0  ,19.5   ,7.09  ,16.5  ,0  ,0 ,
4  ,Ravindra Jadeja ,16  ,16  ,54    ,343 ,15  ,0  ,22.86  ,6.35  ,21.6  ,0  ,0 ,
5  ,Alzarri Joseph  ,3   ,3   ,8.4   ,87  ,6   ,0  ,14.5   ,10.03 ,8.66  ,0  ,1 ,
*/

public class IPL2019AnalyserTest {
    public static final String IPL_BATTING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019FactsheetMostRuns.csv";
    public static final String IPL_BATTING_DATA_CSV_FILE_WITH_WRONG_FILETYPE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019FactsheetMostRuns.txt";
    public static final String IPL_BATTING_DATA_CSV_FILE_WITH_NullFILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019NullFile.txt";
    public static final String IPL_BATTING_DATA_CSV_FILE_WITH_WRONG_DELIMETER = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019NullFile.txt";
    public static final String IPL_SAMPLE_BATTING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019SampleFactsheetMostRuns.csv";

    public static final String IPL_BOWLING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019FactsheetMostWkts.csv";
    public static final String IPL_SAMPLE_BOWLING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019SampleFactsheetMostWkts.csv";

    @Test
    public void forGivenCsv_WhenFetchTheData_IfSuccessfullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BATTING_DATA_CSV_FILE);
            iplDataList.forEach(System.out::println);
            Assert.assertEquals("David Warner", (iplDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenCsvSample_WhenFetchTheData_IfSuccessfullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_SAMPLE_BATTING_DATA_CSV_FILE);
            Assert.assertEquals("Andre Russell", (iplDataList.get(0).playerName).trim());
            Assert.assertEquals("Virat Kohli", (iplDataList.get(1).playerName).trim());
            Assert.assertEquals("AB de Villiers", (iplDataList.get(2).playerName).trim());
            Assert.assertEquals("MS Dhoni", (iplDataList.get(3).playerName).trim());
            Assert.assertEquals("Moeen Ali", (iplDataList.get(4).playerName).trim());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenCsv_WithWrongFileType_shouldThrowException() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BATTING_DATA_CSV_FILE_WITH_WRONG_FILETYPE);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenCsv_WithWrongNullFile_shouldThrowException() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BATTING_DATA_CSV_FILE_WITH_NullFILE);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.NO_SUCH_FILE_ERROR);
        }
    }

    @Test
    public void forGivenCsv_WithWrongDelimeter_shouldThrowException() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BATTING_DATA_CSV_FILE_WITH_WRONG_DELIMETER);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.NO_SUCH_FILE_ERROR);
        }
    }

    @Test
    public void forGivenCsv_WhenFetchTheData_IfSortedByBattingAveragefullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BATTING_DATA_CSV_FILE);
            iplDataList = iplAnalyzer.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.AVERAGE);
            Assert.assertEquals("Tim Southee", (iplDataList.get(iplDataList.size()-1).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenSampleCsv_WhenFetchTheData_IfSortedByBattingAveragefullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_SAMPLE_BATTING_DATA_CSV_FILE);

            iplDataList = iplAnalyzer.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.AVERAGE);

            Assert.assertEquals("Moeen Ali", (iplDataList.get(4).playerName).trim());
            Assert.assertEquals("Virat Kohli", (iplDataList.get(3).playerName).trim());
            Assert.assertEquals("AB de Villiers", (iplDataList.get(2).playerName).trim());
            Assert.assertEquals("Andre Russell", (iplDataList.get(1).playerName).trim());
            Assert.assertEquals("MS Dhoni", (iplDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenCsv_WhenFetchTheData_IfSortedByStrikeRatefullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BATTING_DATA_CSV_FILE);
            iplDataList = iplAnalyzer.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.STRIKE_RATE);
            Assert.assertEquals("Ishant Sharma", (iplDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenTwoCsv_WhenCombinedTheData_SortByAllRounder_ReturnBestPlayer() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> batsmanDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BATTING_DATA_CSV_FILE);
            List<IplPlayersDAO> bowlersDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BOWLING, IPL_BOWLING_DATA_CSV_FILE);
            List<IplPlayersDAO> mergedIplData = iplAnalyzer.mergingData(batsmanDataList,bowlersDataList);
            batsmanDataList = iplAnalyzer.bestBattingWithBowlingAverage(mergedIplData, ComparatorParameters.BattingParameter.BATTING_WITH_BOWLING_AVERAGE);
            Assert.assertEquals("Harpreet Brar", (batsmanDataList.get(0).playerName).trim());

        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenSampleCsv_WhenFetchTheData_IfSortedByStrikeRatefullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_SAMPLE_BATTING_DATA_CSV_FILE);

            iplDataList = iplAnalyzer.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.STRIKE_RATE);

            Assert.assertEquals("MS Dhoni", (iplDataList.get(4).playerName).trim());
            Assert.assertEquals("Virat Kohli", (iplDataList.get(3).playerName).trim());
            Assert.assertEquals("AB de Villiers", (iplDataList.get(2).playerName).trim());
            Assert.assertEquals("Moeen Ali", (iplDataList.get(1).playerName).trim());
            Assert.assertEquals("Andre Russell", (iplDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenCsv_WhenFetchTheData_IfSortedBy6sAnd4sReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BATTING_DATA_CSV_FILE);
            iplDataList = iplAnalyzer.strikeRateBasedOnBoundries(iplDataList, ComparatorParameters.BattingParameter.STRIKE_RATE_BASED_ON_6s4s);
            Assert.assertEquals("Ishant Sharma", (iplDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenSampleCsv_WhenFetchTheData_IfSortedBy6sAnd4sReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_SAMPLE_BATTING_DATA_CSV_FILE);

            iplDataList = iplAnalyzer.strikeRateBasedOnBoundries(iplDataList, ComparatorParameters.BattingParameter.STRIKE_RATE_BASED_ON_6s4s);

            Assert.assertEquals("Andre Russell", (iplDataList.get(0).playerName).trim());
            Assert.assertEquals("Moeen Ali", (iplDataList.get(1).playerName).trim());
            Assert.assertEquals("AB de Villiers", (iplDataList.get(2).playerName).trim());
            Assert.assertEquals("Virat Kohli", (iplDataList.get(3).playerName).trim());
            Assert.assertEquals("MS Dhoni", (iplDataList.get(4).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenCsv_WhenFetchTheData_IfSortedByAverageWithStrikeRateReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BATTING_DATA_CSV_FILE);
            iplDataList = iplAnalyzer.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.AVERAGE, ComparatorParameters.BattingParameter.STRIKE_RATE);
            Assert.assertEquals("Tim Southee", (iplDataList.get(iplDataList.size()-1).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenSampleCsv_WhenFetchTheData_IfSortedByAverageWithStrikeRateReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_SAMPLE_BATTING_DATA_CSV_FILE);

            iplDataList = iplAnalyzer.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.AVERAGE, ComparatorParameters.BattingParameter.STRIKE_RATE);

            Assert.assertEquals("Moeen Ali", (iplDataList.get(4).playerName).trim());
            Assert.assertEquals("Virat Kohli", (iplDataList.get(3).playerName).trim());
            Assert.assertEquals("AB de Villiers", (iplDataList.get(2).playerName).trim());
            Assert.assertEquals("Andre Russell", (iplDataList.get(1).playerName).trim());
            Assert.assertEquals("MS Dhoni", (iplDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenCsv_WhenFetchTheData_IfSortedByMaxRunWithBestAverageRateReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BATTING_DATA_CSV_FILE);
            iplDataList = iplAnalyzer.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.MAX_RUNS, ComparatorParameters.BattingParameter.AVERAGE);
            Assert.assertEquals("Tim Southee", (iplDataList.get(iplDataList.size()-1).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenSampleCsv_WhenFetchTheData_IfSortedByMaxRunWithBestAverageRateReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_SAMPLE_BATTING_DATA_CSV_FILE);

            iplDataList = iplAnalyzer.sortByParamter(iplDataList, ComparatorParameters.BattingParameter.MAX_RUNS, ComparatorParameters.BattingParameter.AVERAGE);

            Assert.assertEquals("Andre Russell", (iplDataList.get(0).playerName).trim());
            Assert.assertEquals("Virat Kohli", (iplDataList.get(1).playerName).trim());
            Assert.assertEquals("AB de Villiers", (iplDataList.get(2).playerName).trim());
            Assert.assertEquals("MS Dhoni", (iplDataList.get(3).playerName).trim());
            Assert.assertEquals("Moeen Ali", (iplDataList.get(4).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenBowlersCsv_WhenFetchTheData_IfSuccessfullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BOWLING_DATA_CSV_FILE);
            iplDataList.forEach(System.out::println);
            Assert.assertEquals("Imran Tahir", (iplDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenBowlersCsvSample_WhenFetchTheData_IfSuccessfullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_SAMPLE_BOWLING_DATA_CSV_FILE);
            Assert.assertEquals("Imran Tahir", (iplDataList.get(0).playerName).trim());
            Assert.assertEquals("Yuzvendra Chahal", (iplDataList.get(1).playerName).trim());
            Assert.assertEquals("Harbhajan Singh", (iplDataList.get(2).playerName).trim());
            Assert.assertEquals("Ravindra Jadeja", (iplDataList.get(3).playerName).trim());
            Assert.assertEquals("Alzarri Joseph", (iplDataList.get(4).playerName).trim());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenBowlingCsv_WhenFetchTheData_IfSortedByBowlingAveragefullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> iplDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BOWLING, IPL_BOWLING_DATA_CSV_FILE);
            iplDataList = iplAnalyzer.sortByParamter(iplDataList, ComparatorParameters.BowlingParameter.AVERAGE);
            Assert.assertEquals("Liam Livingstone", (iplDataList.get(iplDataList.size()-1).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenTwoCsv_WhenCombinedTheData_IfSortedByStrikeRateWith4w5w_lReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> batsmanDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BATTING_DATA_CSV_FILE);
            List<IplPlayersDAO> bowlersDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BOWLING, IPL_BOWLING_DATA_CSV_FILE);
            List<IplPlayersDAO> mergedIplData = iplAnalyzer.mergingData(batsmanDataList,bowlersDataList);

            batsmanDataList = iplAnalyzer.sortByParamter(mergedIplData, ComparatorParameters.BowlingParameter.STRIKE_RATE_WITHW4W5);
            batsmanDataList.forEach(System.out::println);
            Assert.assertEquals("Ishant Sharma", (batsmanDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenTwoCsv_WhenCombinedTheData_IfSortedByBestBowlingAvgWithStrikeRate_lReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> batsmanDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BATTING_DATA_CSV_FILE);
            List<IplPlayersDAO> bowlersDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BOWLING, IPL_BOWLING_DATA_CSV_FILE);
            List<IplPlayersDAO> mergedIplData = iplAnalyzer.mergingData(batsmanDataList,bowlersDataList);
            batsmanDataList = iplAnalyzer.sortByParamter(mergedIplData,ComparatorParameters.BowlingParameter.BOWLING_AVG, ComparatorParameters.BowlingParameter.STRIKE_RATE);
            Assert.assertEquals("Krishnappa Gowtham", (batsmanDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenTwoCsv_WhenCombinedTheData_IfSortedByBestBattingWithBestBowlingAvg_lReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> batsmanDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BATTING_DATA_CSV_FILE);
            List<IplPlayersDAO> bowlersDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BOWLING, IPL_BOWLING_DATA_CSV_FILE);
            List<IplPlayersDAO> mergedIplData = iplAnalyzer.mergingData(batsmanDataList,bowlersDataList);
            batsmanDataList = iplAnalyzer.bestBattingWithBowlingAverage(mergedIplData, ComparatorParameters.BattingParameter.BATTING_WITH_BOWLING_AVERAGE);
            Assert.assertEquals("Harpreet Brar", (batsmanDataList.get(0).playerName).trim());

        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    @Test
    public void forGivenTwoCsv_WhenCombinedTheData_findAllRounder_lReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplPlayersDAO> batsmanDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BATTING, IPL_BATTING_DATA_CSV_FILE);
            List<IplPlayersDAO> bowlersDataList = iplAnalyzer.loadData(IplDataLoaderFactory.DataFor.BOWLING, IPL_BOWLING_DATA_CSV_FILE);
            List<IplPlayersDAO> mergedIplData = iplAnalyzer.mergingData(batsmanDataList,bowlersDataList);
            batsmanDataList = iplAnalyzer.allRounder(mergedIplData, ComparatorParameters.BowlingParameter.ALL_ROUNDER);
            Assert.assertEquals("Hardik Pandya", (batsmanDataList.get(0).playerName).trim());
            Assert.assertEquals("Andre Russell", (batsmanDataList.get(1).playerName).trim());

        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }


}
