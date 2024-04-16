package utilities;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;

public class CommonOps extends Base {

    public static void get(String URL, String logtext) {
        try {
            driver.get(URL);
            if (!logtext.equals(""))
                test.log(Status.PASS, logtext + " - navigation to URL passed successfully");
        } catch (Throwable e) {
            test.log(Status.FAIL, logtext + " - navigation to URL failed, See error: " + e.getMessage() + test.addScreenCaptureFromPath(CaptureScreen(timeStamp)));
            fail(logtext + " - navigation to URL failed, See error: " + e.getMessage());
        }
    }

    public static void click(WebElement element, String logtext) {
        try {
            element.click();
            if (!logtext.equals(""))
                test.log(Status.PASS, logtext + " - click on element passed successfully");
        } catch (Throwable e) {
            test.log(Status.FAIL, logtext + " - click on element failed, See error: " + e.getMessage() + test.addScreenCaptureFromPath(CaptureScreen(timeStamp)));
            fail(logtext + " - click on element failed, See error: " + e.getMessage());
        }
    }

    public static void sendKey(WebElement element, String value, String Logtext) {
        try {
            element.sendKeys(value);
            test.log(Status.PASS, Logtext + " - sendKey to element passed successfully");
        } catch (Throwable e) {
            test.log(Status.FAIL, Logtext + " - sendKey to element failed, See error: " + e.getMessage() + test.addScreenCaptureFromPath(CaptureScreen(timeStamp)));
            fail(Logtext + " - sendKey to element failed, See error: " + e.getMessage());
        }
    }

    public static boolean waitUntilElementVisible(WebElement element, int timeOutDurationOfSeconds, String Logtext){
        return wait.withTimeout(Duration.ofSeconds(timeOutDurationOfSeconds)).until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public static boolean waitUntilElementInvisible(WebElement element, int timeOutDurationOfSeconds, String Logtext){
        return wait.withTimeout(Duration.ofSeconds(timeOutDurationOfSeconds)).until(ExpectedConditions.invisibilityOf(element));
    }

    public static boolean waitUntilClickableElementAppears(WebElement element, int timeOutDuration, String Logtext){
        return wait.withTimeout(Duration.ofSeconds(timeOutDuration))
                .until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
    }

    public static void waitDuration(int timeOutDuration){
        wait.withTimeout(Duration.ofSeconds(timeOutDuration));
    }

    public static void scrollUntilElementVisible(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            // Handle the exception or log an error message
            test.log(Status.FAIL, " - Failed to scroll to the element: " + e.getMessage());
        }
    }

    public static void waitXpath(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitWithId(String element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
    }

    public static String getText(WebElement element) {

        return element.getText();
    }

    public static boolean verifyEquals(String expected, String actual, String Logtext) {
        try {
            assertEquals(expected, actual);
            test.log(Status.PASS, Logtext + " - Equals assertion passed successfully.");
            return true;
        } catch (AssertionError e) {

            test.log(Status.FAIL, "- Equals assertion failed, See error: " + e.getMessage() + test.addScreenCaptureFromPath(CaptureScreen(timeStamp)));
            fail("Equals assertion failed, See error: " + e.getMessage());

            // return false;
        } catch (Exception e) {

            test.log(Status.FAIL, " - Verify Equals failed, See error: " + e.getMessage() + test.addScreenCaptureFromPath(CaptureScreen(timeStamp)));
            fail(" - Verify Equals failed, See error: " + e.getMessage());
        }
        return false;
    }

    public static boolean verifyTrue(boolean condition, String Logtext) {
        try
        {
            assertTrue(condition);
            test.log(Status.PASS, Logtext + " - verify true assertion passed successfully");
            return true;
        }
        catch (AssertionError e)
        {

            test.log(Status.FAIL, " - Verify true assertion failed, See error: " + e.getMessage() + test.addScreenCaptureFromPath(CaptureScreen(timeStamp)));
            fail("Verify Equals failed, See error: " + e.getMessage());

        }
        return false;
    }

    public static boolean verifyFalse(boolean condition, String Logtext) {
        try {

            assertFalse(condition);
            test.log(Status.PASS, Logtext + " - verify false assertion passed successfully");
            return true;
        } catch (AssertionError e) {

            test.log(Status.FAIL, " - Verify false assertion failed, See error: " + e.getMessage() + test.addScreenCaptureFromPath(CaptureScreen(timeStamp)));
            fail("Verify Equals failed, See error: " + e.getMessage());

        }
        return false;
    }

    public static void sleep(int sleepNumber){
        try {
            TimeUnit.MILLISECONDS.sleep(sleepNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void switchToChildWindow() {
        // Get the window handle of the parent window
        parentWindowHandle = driver.getWindowHandle();

        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();

        // Iterate through the window handles
        for (String handle : windowHandles) {
            // Switch to the child window by comparing the handle with the parent window handle
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    public static void switchToParentWindow() {
        // Switch back to the parent window using the parent window handle
        driver.switchTo().window(parentWindowHandle);
    }
}
