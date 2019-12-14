import com.ipl.IPLAnalyserException;
import com.ipl.IPLAnalyser;
import com.ipl.IplBatsmanData;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/* IPL_SAMPLE_BATTING_CSV_FILE_DATA_FOR_VALIDATION
0  ,Andre Russell       ,14 ,13  ,4 ,510 ,80* ,56.66,249,204.81    ,0  ,4 ,31,52
1  ,Virat Kohli         ,14 ,14  ,0 ,464 ,100 ,33.14,328,141.46    ,1  ,2 ,46,13
2 ,AB de Villiers      ,13 ,13  ,3 ,442 ,82* ,44.2 ,287,154       ,0  ,5 ,31,26
3 ,MS Dhoni            ,15 ,12  ,7 ,416 ,84* ,83.2 ,309,134.62    ,2  ,3 ,22,23
4 ,Moeen Ali           ,11 ,10  ,2 ,220 ,66  ,27.5 ,133,165.41    ,2  ,2 ,16,17*/

public class IPL2019AnalyserTest {
    public static final String IPL_BATTING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019FactsheetMostRuns.csv";
    public static final String IPL_BATTING_DATA_CSV_FILE_WITH_WRONG_FILETYPE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019FactsheetMostRuns.txt";
    public static final String IPL_BATTING_DATA_CSV_FILE_WITH_NullFILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019NullFile.txt";
    public static final String IPL_BATTING_DATA_CSV_FILE_WITH_WRONG_DELIMETER = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019NullFile.txt";
    public static final String IPL_SAMPLE_BATTING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019SampleFactsheetMostRuns.csv";

    @Test
    public void forGivenCsv_WhenFetchTheData_IfSuccessfullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplBatsmanData> iplDataList = iplAnalyzer.loadBattingData(IplBatsmanData.class, IPL_BATTING_DATA_CSV_FILE);
            Assert.assertEquals("David Warner", (iplDataList.get(0).PlayerName).trim());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenCsvSample_WhenFetchTheData_IfSuccessfullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplBatsmanData> iplDataList = iplAnalyzer.loadBattingData(IplBatsmanData.class, IPL_SAMPLE_BATTING_DATA_CSV_FILE);
            Assert.assertEquals("Andre Russell", (iplDataList.get(0).PlayerName).trim());
            Assert.assertEquals("Virat Kohli", (iplDataList.get(1).PlayerName).trim());
            Assert.assertEquals("AB de Villiers", (iplDataList.get(2).PlayerName).trim());
            Assert.assertEquals("MS Dhoni", (iplDataList.get(3).PlayerName).trim());
            Assert.assertEquals("Moeen Ali", (iplDataList.get(4).PlayerName).trim());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenCsv_WithWrongFileType_shouldThrowException() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplBatsmanData> iplDataList = iplAnalyzer.loadBattingData(IplBatsmanData.class, IPL_BATTING_DATA_CSV_FILE_WITH_WRONG_FILETYPE);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    }

}
