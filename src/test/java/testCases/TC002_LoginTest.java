package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoanCreationPage;
import pageObjects.LoginPage;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void verify_Login() {

		logger.info("*****Starting TC002_LoginTest ");

		try {


	

			// Login

			LoginPage lp = new LoginPage(driver);

			lp.SetUserEmail(p.getProperty("email"));

			lp.SetUserPassword(p.getProperty("password"));
			
		//	Thread.sleep(10000);
			

			
			lp.clickLogin();
			
			
			Thread.sleep(10000);

			// LoanCreation page

			LoanCreationPage lcp = new LoanCreationPage(driver);

			boolean targetPage = lcp.IsTitlePageIsExist();
			
			System.out.println(targetPage);

			// Assert.assertEquals(targetPage, true,"Login failed");

			Assert.assertTrue(targetPage);

		}

		catch (Exception e) {
			Assert.fail();
			logger.info("TC002_LoginTest is failed ");
		}

		

	}

}
