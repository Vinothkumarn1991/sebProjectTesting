package testCases;

import static org.testng.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.util.Assert;

import pageObjects.RegistrationPage;

public class TC001_SebRegistrationPage extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_Seb_Registration() throws InterruptedException {

		try {

			logger.info("User is started to registered the application");

			RegistrationPage hp = new RegistrationPage(driver);
			logger.info("User is enter the user name and passowrd");
			hp.setFirstName(randomString().toUpperCase());
			hp.setLastName(randomString().toUpperCase());
			hp.setEmail(randomString() + "@yopmail.com");

			String Password = randomAlphaNumeric();
			hp.setPassword(Password);

			hp.setConfirmPassword(Password);
			logger.info("User is naviage to Homepage");
			hp.clickCreateProfile();
			logger.info("validae registration");
			if (hp.verifyNaviageteToHomeDashboard()) {

				System.out.println("User is registered successfully");

			}

			else {

				System.out.println("User is not registered successfully");

			}

			Thread.sleep(5000);

		}

		catch (Exception e) {

			logger.error("Test failed ");

			logger.debug("Debug logs");

			org.testng.Assert.fail();
			// TODO: handle exception
		}

		logger.info("Testing is finished ");

	}
}
