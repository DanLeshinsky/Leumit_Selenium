package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import page_elements.Guru99;
import page_elements.W3School;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.UUID;

public class Base {

    private static String sPathFile = "projConfig.xml";
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
    protected static String location;
    static protected String reportDirectory = "reports";
    static protected String reportFormat = "xml";
    static protected String testName = "" +
            "W";
    static protected String Path = "";
    static protected WebDriver driver;
    static protected WebDriverWait wait;
    static protected Guru99 guru99;
    static protected W3School w3school;
    static protected String baseURL;
    static protected String parentWindowHandle;


    public static void initExecution() throws IOException{
        BasicConfigurator.configure();
        String className = Thread.currentThread().getStackTrace()[2].getClassName();

        if (className.contains("Guru99")) {
            baseURL = getData("BaseURLGuru99").toLowerCase();
        } else {
            baseURL = getData("BaseURL").toLowerCase();
        }

        String browserName = getData("BrowserType").toLowerCase();
        switch (browserName) {
            case "chrome":
                setupChromeBrowser();
                break;
            case "firefox":
                setupFirefoxBrowser();
                break;
            default:
                System.out.println("Error Device Name");
                break;
        }
        initializeWebDriverWait();
        driver.manage().window().maximize();

        guru99 = PageFactory.initElements(driver, Guru99.class);
        w3school = PageFactory.initElements(driver, W3School.class);

    }

    public static void setupChromeBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-cache");
        System.out.println("init starts");
        driver = new ChromeDriver(options);
        System.out.println("init ends");
    }

    public static void setupFirefoxBrowser() throws IOException {
        System.out.println("init starts");
        driver = new FirefoxDriver();
        System.out.println("init ends");
    }

    public static void initializeWebDriverWait() {
        System.out.println("init Wait duration starts");
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        System.out.println("init Wait duration ends");
    }

    public static void initReportTest(String sTestName, String sTestDescription)
    {
        try {
            location = getData("ReportFilePath") + "Execution_" + timeStamp + "/";
            File dir = new File(location);
            dir.mkdir();
            test = extent.createTest(sTestName, sTestDescription);
        }
        catch (Exception e)
        {
            System.out.println("Extent report file creation failed. See details " + e.getMessage());
        }
    }

    public static void finilizeExtentReportTest() //Prepares the test to be added to the report on flush()
    {
        extent.removeTest(test);
    }

    public static void finalizeExtentReport() {
        extent.flush();
    }

    public static String CaptureScreen(String timeStamp)
    {
        String str = UUID.randomUUID().toString();
        String imgPath = getData("ReportFilePath") + "Execution_" + timeStamp + "/" + str + ".png";
        str+= ".png";
        TakesScreenshot oScn = (TakesScreenshot) driver;
        File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
        File oDest = new File(imgPath);
        //String RelativePath = oDest.getCanonicalPath();
        System.out.println(oDest);

        try {
            FileUtils.copyFile(oScnShot, oDest);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        return str;
    }


    public static String getData(String sNodeName) {
        try {
            File fXmlFile = new File(sPathFile);
            InputStream in = new FileInputStream(fXmlFile);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            return doc.getElementsByTagName(sNodeName).item(0).getTextContent();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return "";
    }


    public static void initExtentReport() {
        // getData("ReportFilePath") + "Execution_" + timeStamp + "/"

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(getData("ReportFilePath") + "Execution_" + timeStamp + "/" + getData("ReportFileName"));
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Automation Test Execution Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    public static String captureScreenshot(WebDriver driver) throws IOException {
        String FileSeparator = System.getProperty("file.separator");
        String Extent_report_path = "." + FileSeparator + "Reports";
        String ScreenshotPath = Extent_report_path + FileSeparator + "screenshots";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotName = "screenshot" + Math.random() + ".png";
        String screenshotpath = ScreenshotPath + FileSeparator + screenshotName;
        FileUtils.copyFile(src, new File(screenshotpath));
        return "." + FileSeparator + "screenshots" + FileSeparator + screenshotName;
    }
}
