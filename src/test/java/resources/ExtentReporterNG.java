package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReport() {

		String reporterPath = System.getProperty("user.dir") + "/reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reporterPath);
		reporter.config().setReportName("Coach Automation Report");
		reporter.config().setDocumentTitle("Test Execution Report");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Application", "Coach");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester", "Mallikarjun");
		extent.setSystemInfo("Framework", "Selenium Java TestNG");

		return extent;

	}

}
