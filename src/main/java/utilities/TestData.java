package utilities;
import org.testng.annotations.DataProvider;

import static utilities.TextHelpers.*;

public class TestData {

    @DataProvider(name = "DataproviderW3School")
    public static Object[][] testData() {
        return new Object[][]{
                {new DataHelper(SEARCH_COLUMN_1, SEARCH_TEXT_1, RETURN_COLUMN_1, EXPECTED_TEXT_1)},
                {new DataHelper(SEARCH_COLUMN_2, SEARCH_TEXT_2, RETURN_COLUMN_2, EXPECTED_TEXT_2)},
                {new DataHelper(SEARCH_COLUMN_2, SEARCH_TEXT_2, RETURN_COLUMN_2-1, EXPECTED_TEXT_2)}
        };
    }

    @DataProvider(name = "guru99data_one_str")
    public Object[][] testDataGuru_one_str() {
        String[][] data = {
                {"Selenium;Table Demo"},
                {"Bank Project"},
                {"SEO;Page-6"}};
        return data;
    }

    @DataProvider(name = "guru99data_separated")
    public Object[][] testDataGuru_separated() {
        String[][] data = {
                {"Selenium", "Table Demo"},
                {"Bank Project", "None"},
                {"SEO", "Page-6"}};
        return data;
    }
}
