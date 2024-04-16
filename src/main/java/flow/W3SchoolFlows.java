package flow;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.CommonOps;

import java.util.List;

public class W3SchoolFlows extends CommonOps {

    public static void navigationToBaseURL(String baseURL){
        CommonOps.get(baseURL, "Navigate to W3School URL ");
        CommonOps.waitUntilElementVisible(w3school.w3s_table, 5, "Wait for the table to appear");
    }

    public static boolean verifyURLAppears(){
        return CommonOps.verifyTrue(w3school.w3s_table.isDisplayed(), "URL was loaded correct");
    }

    public static boolean verifyTableCellTextAsExpected(int searchColumn, String searchText, int returnColumnText, String expectedText){
        return CommonOps.verifyTrue(
                verifyTableCellText(w3school.w3s_table, searchColumn, searchText, returnColumnText, expectedText),
                "The table search by loop was correct");
    }

    public static boolean verifyTableCellTextAsExpectedByXpath(int searchColumn, String searchText, int returnColumnText, String expectedText) throws Exception {
        return CommonOps.verifyTrue(
                verifyTableCellTextByXpath(w3school.w3s_table, searchColumn, searchText, returnColumnText, expectedText),
                "The table search by xpath was correct");
    }

    public static boolean verifyTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText, String expectedText) {
        String actualText = getTableCellTextByLoop(table, searchColumn, searchText, returnColumnText);
        if (!actualText.equals(expectedText)){
            test.log(Status.INFO, "Expected text should be ---> : " + expectedText);
            test.log(Status.INFO, "Actual text was -----------> : " + actualText);
        }
        return actualText.equals(expectedText);
    }

    public static boolean verifyTableCellTextByXpath(WebElement table, int searchColumn, String searchText, int returnColumnText, String expectedText) throws Exception {
        String actualText = getTableCellTextByXpath(table, searchColumn, searchText, returnColumnText);
        if (!actualText.equals(expectedText)){
            test.log(Status.INFO, "Expected text should be ---> : " + expectedText);
            test.log(Status.INFO, "Actual text was -----------> : " + actualText);
        }
        return actualText.equals(expectedText);
    }

    public static String getTableCellTextByLoop(WebElement table, int searchColumn, String searchText, int returnColumnText) {
        String result = "Empty search";

        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (int i = 1; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if (columns.get(searchColumn - 1).getText().equals(searchText)) {
                // Return the text of the column specified by returnColumnText
                return columns.get(returnColumnText - 1).getText();
            }
        }

        return result;
    }

    public static String getTableCellTextByXpath(WebElement table, int searchColumn, String searchText, int returnColumnText) throws Exception {
        String xpath = "//table[@id='customers']//tr[td[" + searchColumn + "][text()='" + searchText + "']]/td[" + returnColumnText + "]";
        WebElement cell = table.findElement(By.xpath(xpath));
        return cell.getText();
    }
}
