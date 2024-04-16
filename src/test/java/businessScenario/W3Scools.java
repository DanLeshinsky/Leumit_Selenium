package businessScenario;

import org.testng.annotations.*;
import utilities.Base;
import utilities.CommonOps;
import utilities.DataHelper;
import utilities.TestData;

import java.io.IOException;

import static flow.W3SchoolFlows.*;

public class W3Scools extends Base {

    @BeforeTest
    public void beforeTest(){
        initExtentReport();
    }

    @BeforeClass
    public static void setUp() throws IOException {
        initExecution(); // init driver
    }

    @BeforeMethod
    public static void initReportTest(){
        initReportTest(testName + " - " + getData("BrowserType"),"This Test performs search in table");
        navigationToBaseURL(baseURL);
        verifyURLAppears();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @AfterTest
    public void finalizingReport() {
        finalizeExtentReport();
        driver.quit();
    }

    @Test(dataProvider = "DataproviderW3School", dataProviderClass = TestData.class)
    public void TestW3chool_with_for_loop(DataHelper dh){
//        initReportTest(testName + " - " + getData("BrowserType"),
//                "This Test performs search in table within a loop");

        CommonOps.scrollUntilElementVisible(w3school.w3s_table);
        verifyTableCellTextAsExpected( dh.getSearchColumnNum(), dh.getSearchText(), dh.getReturnColumnText(),dh.getExpectedText() );
    }

    @Test
    public void TestW3chool_with_xpath() throws Exception {
        CommonOps.scrollUntilElementVisible(w3school.w3s_table);
        verifyTableCellTextAsExpectedByXpath(1, "Ernst Handel", 3, "Austria");
    }
}
