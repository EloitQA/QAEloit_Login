package baseclass;

import java.io.File;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass1 {
	
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();//used for parallel execution,
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExtentHtmlReporter extent;																						//in same browser multiple test cases 
	
	
	
	
	public void browserInitialization() {
		WebDriverManager.chromedriver().setup();
		setDriver(new ChromeDriver());
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		getDriver().manage().window().maximize();
		String url = "https://qa.edisapp.net/";
		getDriver().get(url);
	}

	public void setDriver(RemoteWebDriver driverValue) {
		driver.set(driverValue);
	}

	public RemoteWebDriver getDriver() {
		return driver.get();
	}
	@AfterClass
	public void quitBrowser()
	{
		
		getDriver().quit();
	}
	public void waitForElementVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void dropDownSelectionByVisibleText(WebElement ele, String text) {
		Select dropDown = new Select(ele);
		dropDown.selectByVisibleText(text);
	}
	public String captureScreenshot() throws Exception {
	Date date = new Date();
	String timestamp = "IMG"+date.getTime();
	String fileWithPath = System.getProperty("user.dir") + 
			"\\screenshots\\" + timestamp + ".png";
	TakesScreenshot scrShot = ((TakesScreenshot)getDriver());
	File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
	File DestFile = new File(fileWithPath);
	FileUtils.copyFile(SrcFile, DestFile);
	return fileWithPath;
}

	@BeforeSuite
	public void reportSetUp() 
	{
//	For creating folder and html
	extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/ExtentSelenium.html"));
	report=new ExtentReports();
	report.attachReporter(extent);
	}

	@AfterMethod
	public void stepStatus(ITestResult result) {

	if (result.getStatus() == ITestResult.FAILURE)
	{
		try {
			logger.fail("FAILED: "+result.getName(),MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
			logger.fail("Reason for Failure:"+result.getThrowable());
		} catch (Exception e) {
		}
	}
	else if(result.getStatus() == ITestResult.SUCCESS)
	{
		try {
			logger.pass("PASSED: "+result.getName());
		} catch (Exception e) {
		}
	}
else if(result.getStatus() == ITestResult.SKIP)//stack trace
	{
		try {
			logger.skip("SKIPPED: "+result.getName());
		} catch (Exception e) {
		}
	}
	report.flush();//for cleaning purpose
}
	

}
