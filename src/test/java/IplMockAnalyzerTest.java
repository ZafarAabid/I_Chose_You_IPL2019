import com.csvBuilder.CSVBuilderException;
import com.ipl.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IplMockAnalyzerTest {
    public static final String IPL_BATTING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019FactsheetMostRuns.csv";
    public static final String IPL_BOWLING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019FactsheetMostWkts.csv";

    private ArrayList<IplPlayersDAO> expectedAllRounderMap;
    private ArrayList<IplPlayersDAO> expectedBattingMap;
    private ArrayList<IplPlayersDAO> expectedBowlingMap;

    @Before
    public void setup() {
        IplBatsmanData hardik_pandya = new IplBatsmanData("Hardik Pandya", 402.0, 44.06, 191.42, 210, 28, 29);
        IplBatsmanData andre_russell = new IplBatsmanData("Andre Russell", 510.0, 56.66, 204.81, 249, 31, 52);
        IplBatsmanData ms_dhoni = new IplBatsmanData("MS Dhoni", 416.0, 83.2, 134.62, 309, 22, 23);
        IplBatsmanData ishant_sharma = new IplBatsmanData("Ishant Sharma", 10.0, 0.0, 333.33, 3, 1, 1);
        IplBatsmanData david_warner = new IplBatsmanData("David Warner", 692.0, 69.2, 143.86, 481, 57, 21);
        this.expectedBattingMap = new ArrayList<IplPlayersDAO>() {
            {
                add(new IplPlayersDAO(hardik_pandya));
                add(new IplPlayersDAO(andre_russell));
                add(new IplPlayersDAO(ms_dhoni));
                add(new IplPlayersDAO(ishant_sharma));
                add(new IplPlayersDAO(david_warner));
            }
        };

        IplBowlerData imran_tahir = new IplBowlerData("Imran Tahir", 17, 17, 64.2, 431, 26, 0, 16.57, 6.69, 14.84, 2, 0.0);
        IplBowlerData hardikPandya = new IplBowlerData("Hardik Pandya", 16, 16, 42.3, 390, 14, 0, 27.85, 9.17, 18.21, 0, 0.0);
        IplBowlerData kagiso_rabada = new IplBowlerData("Kagiso Rabada", 12, 12, 47.0, 368, 25, 0, 14.72, 7.82, 11.28, 2, 0.0);
        IplBowlerData alzarri_joseph = new IplBowlerData("Alzarri Joseph", 3, 3, 8.4, 87, 6, 0, 14.5, 10.03, 8.66, 0, 1.0);
        IplBowlerData anukul_roy = new IplBowlerData("Anukul Roy", 1, 1, 2.0, 11, 1, 0, 11.0, 5.5, 12.0, 0, 0.0);
        IplBowlerData shivam_dube = new IplBowlerData("Shivam Dube", 4, 2, 1.4, 8, 0, 0, 0.0, 4.8, 0.0, 0, 0.0);
        this.expectedBowlingMap = new ArrayList<IplPlayersDAO>() {
            {
                add(new IplPlayersDAO(imran_tahir));
                add(new IplPlayersDAO(hardikPandya));
                add(new IplPlayersDAO(kagiso_rabada));
                add(new IplPlayersDAO(alzarri_joseph));
                add(new IplPlayersDAO(anukul_roy));
                add(new IplPlayersDAO(shivam_dube));
            }
        };
        this.expectedAllRounderMap = new ArrayList<IplPlayersDAO>() {
            {
                add(new IplPlayersDAO(hardik_pandya));
                add(new IplPlayersDAO(andre_russell));
                add(new IplPlayersDAO(ms_dhoni));
                add(new IplPlayersDAO(ishant_sharma));
                add(new IplPlayersDAO(david_warner));
                add(new IplPlayersDAO(imran_tahir));
                add(new IplPlayersDAO(kagiso_rabada));
                add(new IplPlayersDAO(alzarri_joseph));
                add(new IplPlayersDAO(anukul_roy));
            }
        };
    }

    @Test
    public void forGivenCsv_WhenFetchTheData_IfSuccessfullReturnTrue() throws IPLAnalyserException, CSVBuilderException {

        IplBattingDataLoader dataLoader = mock(IplBattingDataLoader.class);
        when(dataLoader.getDataFile(any())).thenReturn(expectedBattingMap);
        IPLAnalyser analyser = new IPLAnalyser(dataLoader);
        List<IplPlayersDAO> output = analyser.loadData(IPL_BATTING_DATA_CSV_FILE);
        Assert.assertEquals(5, output.size());
    }

    @Test
    public void forGivenBowlingCsv_WhenFetchTheData_IfSuccessfullReturnTrue() throws IPLAnalyserException, CSVBuilderException {
        IplBowlingDataLoader dataLoader = mock(IplBowlingDataLoader.class);
        when(dataLoader.getDataFile(any())).thenReturn(expectedBowlingMap);
        IPLAnalyser analyser = new IPLAnalyser(dataLoader);
        List<IplPlayersDAO> output = analyser.loadData(IPL_BATTING_DATA_CSV_FILE);
        Assert.assertEquals(6, output.size());
    }

    @Test
    public void forGivenAllRounderCsv_WhenFetchTheData_IfSuccessfulReturnTrue() throws IPLAnalyserException, CSVBuilderException {
        IplBowlingDataLoader bowlingDataLoader = mock(IplBowlingDataLoader.class);
        IplBattingDataLoader battingDataLoader = mock(IplBattingDataLoader.class);
        when(bowlingDataLoader.getDataFile(any())).thenReturn(expectedBowlingMap);
        when(battingDataLoader.getDataFile(any())).thenReturn(expectedBattingMap);
        IPLAnalyser battingAnalyser = new IPLAnalyser(battingDataLoader);
        IPLAnalyser bowlingAnalyser = new IPLAnalyser(bowlingDataLoader);
        List<IplPlayersDAO> battingOutput = battingAnalyser.loadData(IPL_BATTING_DATA_CSV_FILE);
        List<IplPlayersDAO> bowlingOutput = bowlingAnalyser.loadData(IPL_BATTING_DATA_CSV_FILE);
        List<IplPlayersDAO> margedData = battingAnalyser.mergingData(bowlingOutput, battingOutput);
        margedData.forEach(System.out::println);
        Assert.assertEquals(5, battingOutput.size());
    }

    @Test
    public void forGivenTwoCsv_WhenFetchTheData_IfSuccessfulReturnAllRounderList() throws IPLAnalyserException, CSVBuilderException {

        IplBowlingDataLoader bowlingDataLoader = mock(IplBowlingDataLoader.class);
        IplBattingDataLoader battingDataLoader = mock(IplBattingDataLoader.class);
        DataMerging dataMerging = mock(DataMerging.class);

        when(bowlingDataLoader.getDataFile(any())).thenReturn(expectedBowlingMap);
        when(battingDataLoader.getDataFile(any())).thenReturn(expectedBattingMap);
        when(dataMerging.mergeData(any(),any())).thenReturn(expectedAllRounderMap);

        IPLAnalyser battingAnalyser = new IPLAnalyser(battingDataLoader);
        IPLAnalyser bowlingAnalyser = new IPLAnalyser(bowlingDataLoader);

        List<IplPlayersDAO> battingOutput = battingAnalyser.loadData(IPL_BATTING_DATA_CSV_FILE);
        List<IplPlayersDAO> bowlingOutput = bowlingAnalyser.loadData(IPL_BATTING_DATA_CSV_FILE);
        List<IplPlayersDAO> margedData = battingAnalyser.mergingData(bowlingOutput,battingOutput);

        margedData.forEach(System.out::println);

        Assert.assertEquals(5, battingOutput.size());

    }

    @Test
    public void forGivenTwoCsv_WhenCombinedTheData_IfSortedByBestBattingWithBestBowlingAvg_lReturnTrue() {
        IplBowlingDataLoader bowlingDataLoader = mock(IplBowlingDataLoader.class);
        IplBattingDataLoader battingDataLoader = mock(IplBattingDataLoader.class);
        ComparatorParameters comparatorParameters = mock(ComparatorParameters.class);
        DataSorting dataSorting = new DataSorting();
        try {
            when(bowlingDataLoader.getDataFile(any())).thenReturn(expectedBowlingMap);
            when(battingDataLoader.getDataFile(any())).thenReturn(expectedBattingMap);
            Comparator comparator = Comparator.<IplPlayersDAO, Double>comparing(census -> census.playersBattingAvg).reversed();
            when(comparatorParameters.getComparator(notNull())).thenReturn(comparator);
            IPLAnalyser battingAnalyser = new IPLAnalyser(battingDataLoader);
            IPLAnalyser bowlingAnalyser = new IPLAnalyser(bowlingDataLoader);
            List<IplPlayersDAO> battingOutput = battingAnalyser.loadData(IPL_BATTING_DATA_CSV_FILE);
            List<IplPlayersDAO> bowlingOutput = bowlingAnalyser.loadData(IPL_BATTING_DATA_CSV_FILE);
            List<IplPlayersDAO> margedData = battingAnalyser.mergingData(battingOutput, bowlingOutput);
            List<IplPlayersDAO> SortedDataList = dataSorting.sortByParamter(margedData, ComparatorParameters.BattingParameter.BATTING_WITH_BOWLING_AVERAGE);
            SortedDataList.forEach(System.out::println);
            Assert.assertEquals("Hardik Pandya", (SortedDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

}
