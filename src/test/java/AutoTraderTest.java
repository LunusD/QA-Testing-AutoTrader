import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.security.util.PendingException;

import java.io.File;
import java.util.List;

public class AutoTraderTest {

    private JavascriptExecutor js;
    private SpreadSheetReader spreadsheetReader;
    private static ExtentReports reports;
    private WebDriver webDriver;
    private List<String> inputs;
    String baseUrl;

    @BeforeClass
    public static void init(){
        reports = new ExtentReports();
        String fileName = "AutoTraderReport" + ".html";
        String filePath = System.getProperty("user.dir") + File.separatorChar + fileName;
        reports.attachReporter(new ExtentHtmlReporter(filePath));
    }

    @Before
    public void setup(){
        spreadsheetReader = new SpreadSheetReader("AutoTraderProperties.xlsx");
        inputs = spreadsheetReader.readRow(1, "setup");
        String browser = inputs.get(0);
        baseUrl = inputs.get(1);
        webDriver = BrowserFactory.getBrowser(browser);
        webDriver.manage().window().maximize();
        webDriver.navigate().to(baseUrl);
    }

    @After
    public void tearDown(){
        BrowserFactory.closeAllDriver();
    }

    @AfterClass
    public static void destroy(){
        reports.flush();
    }

    @Test
    public void autoTraderSearchTest(){
        ExtentTest autoTraderQuickSearchTest = reports.createTest("Testing Quick Search on Auto Trader Homepage");
        autoTraderQuickSearchTest.log(Status.INFO, "Stating test of Auto Trader quick search.");
        autoTraderQuickSearchTest.log(Status.INFO, "Website loaded at : " + baseUrl);

        inputs = spreadsheetReader.readRow(1, "quickSearch");
        String postcode = inputs.get(0);
        String distance = inputs.get(1);
        Boolean showUsed = Boolean.parseBoolean(inputs.get(2));
        Boolean showNearlyNew = Boolean.parseBoolean(inputs.get(3));
        Boolean showNew = Boolean.parseBoolean(inputs.get(4));
        String make = inputs.get(5);
        String model = inputs.get(6);
        String minPrice = inputs.get(7);
        String maxPrice = inputs.get(8);


        autoTraderQuickSearchTest.log(Status.INFO, "Passing in info to the quick search fields.");
        Homepage homepage = PageFactory.initElements(webDriver, Homepage.class);
        homepage.enterPostcode(postcode);
        homepage.selectDistance(distance);
        homepage.toggleUsed(showUsed);
        homepage.toggleNearlyNew(showNearlyNew);
        homepage.toggleNew(showNew);
        do
        {
            try
            {
                homepage = PageFactory.initElements(webDriver, Homepage.class);
                homepage.selectMake(make);
                break;
            }
            catch(StaleElementReferenceException see)
            {
            }
        } while(1>0);

        do
        {
            try
            {
                homepage = PageFactory.initElements(webDriver, Homepage.class);
                homepage.selectModel(model);
                break;
            }
            catch(StaleElementReferenceException see)
            {
            }
        } while(1>0);

        do
        {
            try
            {
                homepage = PageFactory.initElements(webDriver, Homepage.class);
                homepage.selectMinPrice(minPrice);
                break;
            }
            catch(StaleElementReferenceException see)
            {
            }
        } while(1>0);

        do
        {
            try
            {
                homepage = PageFactory.initElements(webDriver, Homepage.class);
                homepage.selectMaxPrice(maxPrice);
                break;
            }
            catch(StaleElementReferenceException see)
            {
            }
        } while(1>0);

        autoTraderQuickSearchTest.log(Status.INFO, "Searching");
        homepage.quickSearch();

        String expectedUrlToContain = "https://www.autotrader.co.uk/car-search";
        String actualUrl = webDriver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlToContain));
    }

    @Test
    public void regCheckTest(){
        ExtentTest autoTraderRegCheckTest = reports.createTest("Testing Reg Check on Auto Trader Homepage");
        autoTraderRegCheckTest.log(Status.INFO, "Stating test of Auto Trader Reg Check.");
        autoTraderRegCheckTest.log(Status.INFO, "Website loaded at : " + baseUrl);

        throw new PendingException("This test is not yet implemented");
    }

    @Test
    public void signUpTest(){
        throw new PendingException("This test is not yet implemented");
    }

    @Test
    public void detailedSearchTest(){
        throw new PendingException("This test is not yet implemented");
    }

    @Test
    public void valueCarTest(){
        throw new PendingException("This test is not yet implemented");
    }

    @Test
    public void gapInsuranceTest(){
        throw new PendingException("This test is not yet implemented");
    }

    @Test
    public void getWarrantyQuoteTest(){
        throw new PendingException("This test is not yet implemented");
    }

    @Test
    public void getInsuranceQuoteTest(){
        throw new PendingException("This test is not yet implemented");
    }

    @Test
    public void createSellAdTest(){
        throw new PendingException("This test is not yet implemented");
    }

    @Test
    public void findLocalDealerTest(){
        throw new PendingException("This test is not yet implemented");
    }
}
