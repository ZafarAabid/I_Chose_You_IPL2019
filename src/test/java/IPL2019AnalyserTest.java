import com.google.gson.Gson;
import com.ipl.CensusAnalyserException;
import com.ipl.IPLAnalyser;
import com.ipl.IplBatsmanData;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class IPL2019AnalyserTest {
    public static final String IPL_BATTING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019FactsheetMostRuns.csv";
    public static final String IPL_BOWLING_DATA_CSV_FILE = "/home/user/workspace/IchooseYouIPL/src/test/resources/IPL2019FactsheetMostWkts.csv";
    @Test
    public void name() {
        IPLAnalyser iplAnalyzer = new IPLAnalyser();
        try {
            List<IplBatsmanData> iplDataList = iplAnalyzer.loadBattingData(IplBatsmanData.class,IPL_BATTING_DATA_CSV_FILE);
            iplDataList.forEach(System.out::println);
            Assert.assertEquals("David Warner",(iplDataList.get(0).PlayerName).trim());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }

    }
}
