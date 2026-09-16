package listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import resources.ExtentReporterNG;
import testComponents.BaseTestt;

public class Listeners implements ITestListener {
	ExtentReports extent;
	ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public void onStart(ITestContext context) {
		extent = ExtentReporterNG.getReport();

	}

	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);

	}

	public void onTestSuccess(ITestResult result) {
		test.get().pass("test Passed");

	}

	public void onTestFailure(ITestResult result) {
		// Mark test as failed in Extent Report
		test.get().fail(result.getThrowable());

		// Get the current test class object

		// Object currentClass = result.getInstance();

		BaseTestt baseTest = (BaseTestt) result.getInstance();

		WebDriver driver = baseTest.getDriver();
		// Get WebDriver from BaseTestt

		TakesScreenshot tss = (TakesScreenshot) driver;
		File source = tss.getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "/reports/screenshots/"
				+ result.getMethod().getMethodName() + ".png";
		File dest = new File(screenshotPath);

		try {
			FileUtils.copyFile(source, dest);
			test.get().addScreenCaptureFromPath(dest.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test.get().skip("test skipped");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	public void onFinish(ITestContext context) {
		System.out.println("========== EXTENT FLUSH ==========");

		extent.flush();

		System.out.println("========== REPORT GENERATED ==========");

	}
}
