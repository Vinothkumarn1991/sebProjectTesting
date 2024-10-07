package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager extends BaseClass implements ITestListener {

	public ExtentSparkReporter sparkReporter;

	public ExtentReports extent;

	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {

		/*
		 * SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		 * 
		 * 
		 * Date dt=new Date();
		 * 
		 * String currentDateTimeStamp=df.format(dt);
		 */

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		repName = "Test-Report" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
		sparkReporter.config().setDocumentTitle("SEB Automation Report");
		sparkReporter.config().setReportName("SEB Functional Testing");

		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();

		extent.attachReporter(sparkReporter);

		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Application", "SEB");
		extent.setSystemInfo("Module", "Admin");

		extent.setSystemInfo("Sub Module", "Inschool");

		extent.setSystemInfo("User Name", System.getProperty("user.name"));

		extent.setSystemInfo("Environment", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");

		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");

		extent.setSystemInfo("Browser", browser);

		java.util.List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();

		if (!includeGroups.isEmpty()) {

			extent.setSystemInfo("Groups", includeGroups.toString());

		}

	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());

		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.PASS, result.getName() + "got successfully executed");

	}

	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());

		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, result.getName() + "got failed");

		test.log(Status.INFO, result.getThrowable().getMessage());

		try {

			String imgPath = new BaseClass().captureScreen(result.getName());

			test.addScreenCaptureFromPath(imgPath);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());

		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + "got skipped");

		test.log(Status.INFO, result.getThrowable().getMessage());

	}

	public void onFinish(ITestContext testcontext) {

		extent.flush();

		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;

		File extentReport = new File(pathOfExtentReport);

		try {

			Desktop.getDesktop().browse(extentReport.toURI());

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);

			ImageHtmlEmail email = new ImageHtmlEmail();

			email.setDataSourceResolver(new DataSourceUrlResolver(url));

			email.setHostName("https://outlook.office365.com/mail/");

			email.setSmtpPort(6000);

		//	email.setAuthenticator(new DefaultAuthenticator("vinothkumar.n@tavant.com", "Vaisali@654321"));

			email.setSSLOnConnect(true);

			email.setFrom("vinothkumar.n@tavant.com");

			email.setSubject("Test Results");

			email.setMsg("please find the attached report");

			email.addTo("vinothkumar.n@tavant.com");

			email.attach(url, "extent report", "please check report...");
			email.send();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
