package businessScenario;

import org.testng.annotations.*;
import utilities.Base;
import utilities.CommonOps;
import utilities.TestData;

import java.io.IOException;

import static flow.Guru99Flows.*;

public class Guru99 extends Base {

    @BeforeClass
    public static void setUp() throws IOException {
        initExecution(); // init driver
    }

    @BeforeMethod
    public static void initReportTest(){
        initReportTest(testName + " - " + getData("BrowserType"),"This Test performs menu selection");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test(dataProvider = "guru99data_one_str", dataProviderClass = TestData.class)
    public void Guru99_with_one_string(String optCatAndSubcat) throws Exception {
        navigationToBaseURL(baseURL);
        verifyURLAppears();
        clickOnMenu(optCatAndSubcat);
        CommonOps.sleep(5000);
    }

    @Test(dataProvider = "guru99data_separated", dataProviderClass = TestData.class)
    public void Guru99_with_category_and_subcategory(String category, String subcategory) throws Exception {
        navigationToBaseURL(baseURL);
        verifyURLAppears();
        clickOptionOnMenu(category, subcategory);
        CommonOps.sleep(5000);
    }

}
