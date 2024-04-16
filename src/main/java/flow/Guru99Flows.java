package flow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.CommonOps;

import java.time.Duration;

public class Guru99Flows extends CommonOps {

    public static void navigationToBaseURL(String baseURL){
        CommonOps.get(baseURL, "Navigate to Guru99 URL ");
        CommonOps.waitUntilElementVisible(guru99.guru_home, 5, "Wait for the guru99 site to appear");
    }

    public static boolean verifyURLAppears(){

        return CommonOps.verifyTrue(guru99.guru_home.isDisplayed(), "Guru URL was loaded correct");
    }

    public static void clickOnMenu(String optionStr){
        String category;
        String subCategory = "None";

        if (optionStr.contains(";")){
            category = optionStr.split(";")[0];
            subCategory = optionStr.split(";")[1];
        } else {
            category = optionStr;
        }

        clickOptionOnMenu(category, subCategory);
    }

    public static void clickOptionOnMenu(String category, String subCategory){
        String categoryXpath = "//ul[@class='nav navbar-nav']/li/a[contains(text(), '" + category + "')]";

        WebElement categoryElement = driver.findElement(By.xpath(categoryXpath));
        CommonOps.click(categoryElement, "Click on the category " + category);

        if (!subCategory.equals("None")) {
            String subCategoryXpath = categoryXpath + "/following-sibling::ul/li/a[contains(text(), '" + subCategory + "')]";
            WebElement subCategoryElement = driver.findElement(By.xpath(subCategoryXpath));
            CommonOps.waitUntilElementVisible(subCategoryElement, 2, "Wait for the subCategory menu to appear");
            CommonOps.click(subCategoryElement, "Click on subCategory " + subCategory);
        }
    }
}
