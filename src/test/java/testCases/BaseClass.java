package testCases;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.commons.compress.harmony.pack200.NewAttribute;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v128.filesystem.model.File;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

public class BaseClass {

	public static WebDriver driver;

	public Logger logger;
	
	public Properties p;

	@BeforeClass(groups ={"Sanity","Master","Regression"})

	@Parameters({ "os", "browser" })

	public void setup(String os, String br) throws IOException {
		
		//Loading config property file
		FileReader file= new FileReader("./src//test//resources//config.properties");
		
		p=new Properties();
		
		
		p.load(file);

		logger = LogManager.getLogger(this.getClass());
		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid Browser name");
			return;

		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("QA"));//reading url from property file
		
		
		driver.manage().window().maximize();

	}

	@AfterClass(groups ={"Sanity","Master","Regression"})
	public void tearDown() {

		driver.quit();

	}

	public String randomString()

	{

		String generatedString = RandomStringUtils.randomAlphabetic(5);

		return generatedString;

	}

	public String randomNumber()

	{

		String generatedNumber = RandomStringUtils.randomNumeric(10);

		return generatedNumber;

	}

	public String randomAlphaNumeric()

	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(10);

		String generatedAlphaNumeric = generatedString + "@" + generatedNumber;

		return generatedAlphaNumeric;

	}
	
	public String captureScreen(String tname) throws IOException {
		
		
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenShot=(TakesScreenshot) driver;
		
		java.io.File sourceFile=takeScreenShot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+ tname +"_" + timeStamp +".png";
		
		
		java.io.File targetFile=new java.io.File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		
		return targetFilePath;
		
	}

	

}
