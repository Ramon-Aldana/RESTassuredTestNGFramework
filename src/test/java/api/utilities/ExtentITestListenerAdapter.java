package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentITestListenerAdapter implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	Logger logger;
	
	String repName;

	public void onStart(ITestContext testContext) {
		logger = LogManager.getLogger(this.getClass());
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report" + timestamp + ".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
		
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); 
		sparkReporter.config().setReportName("Pet Store Users API"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pest Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "REAB");
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		String logline = "----------------- START: " + result.getName() + "---------------------";
		logger.info( logline);
		System.out.println(logline);
		System.out.flush();
		
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Failed");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

}
