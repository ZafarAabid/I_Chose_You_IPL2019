import com.ipl.IPLAnalyser;
import com.ipl.IPLAnalyserException;
import com.ipl.IplBatsmanData;
import com.ipl.IplBowlerData;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IPL2019BowlersAnalyzerTests {
    public static final String IPL_BOWLING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019FactsheetMostWkts.csv";
    public static final String IPL_BOWLING_DATA_CSV_FILE_WITH_WRONG_FILETYPE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019FactsheetMostRuns.txt";
    public static final String IPL_BOWLING_DATA_CSV_FILE_WITH_NullFILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019NullFile.txt";
    public static final String IPL_BOWLING_DATA_CSV_FILE_WITH_WRONG_DELIMETER = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019NullFile.txt";
    public static final String IPL_SAMPLE_BOWLING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019SampleFactsheetMostWkts.csv";


    @Test
    public void forGivenCsv_WhenFetchTheData_IfSuccessfullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplBowlerData> iplDataList = iplAnalyzer.loadBowlingData(IplBowlerData.class, IPL_BOWLING_DATA_CSV_FILE);
            iplDataList.forEach(System.out::println);
            Assert.assertEquals("Imran Tahir", (iplDataList.get(0).playerName).trim());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forGivenCsvSample_WhenFetchTheData_IfSuccessfullReturnTrue() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplBowlerData> iplDataList = iplAnalyzer.loadBowlingData(IplBowlerData.class, IPL_SAMPLE_BOWLING_DATA_CSV_FILE);
            Assert.assertEquals("Imran Tahir", (iplDataList.get(0).playerName).trim());
            Assert.assertEquals("Yuzvendra Chahal", (iplDataList.get(1).playerName).trim());
            Assert.assertEquals("Harbhajan Singh", (iplDataList.get(2).playerName).trim());
            Assert.assertEquals("Ravindra Jadeja", (iplDataList.get(3).playerName).trim());
            Assert.assertEquals("Alzarri Joseph", (iplDataList.get(4).playerName).trim());
        } catch (IPLAnalyserException e) {
            System.out.println("EEEE");
            e.printStackTrace();
        }
    }

}
