package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver)

	{

		super(driver);
	}
	
	// FirstName

	@FindBy(xpath = "//*[@id=\"register-first-name\"]")

	WebElement FirstNametxt;

	// LastName
	@FindBy(xpath = "//*[@id=\"register-last-name\"]")

	WebElement LastNametxt;

	// Email
	@FindBy(xpath = "//*[@id=\"register-email\"]")

	WebElement Emailtxt;

	

	// Password
	@FindBy(xpath = "//*[@id=\"register-password\"]")

	WebElement Passwordtxt;
	
	// Confirm Password
		@FindBy(xpath = "//*[@id=\"register-confirm-password\"]")

		WebElement ConfirmPasswordtxt;

	//Create Profile
		@FindBy(xpath = "//*[@id=\"create-profile\"]")

		WebElement CreateProfile;
		
	
	public void setFirstName(String firstname) {

		FirstNametxt.sendKeys(firstname);

	}

	public void setLastName(String lastname) {

		LastNametxt.sendKeys(lastname);

	}

	public void setEmail(String email) {

		Emailtxt.sendKeys(email);

	}

	public void setPassword(String Password) {

		Passwordtxt.sendKeys(Password);

	}

	public void setConfirmPassword(String confirmpassword) {

		ConfirmPasswordtxt.sendKeys(confirmpassword);

	}
	
	
	public void clickCreateProfile() {

//		CreateProfile.submit();
		
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", CreateProfile);

	}
	
	
	// confirmation page

	@FindBy(xpath = "//h3[contains(text(),'Welcome to Education Loan Finance!')]")

	WebElement msgConfirmation;
	
	
	public boolean verifyNaviageteToHomeDashboard() {
		
	
		
		
		return msgConfirmation.isDisplayed();
		
		
		
		
		
		
	//	
		
	//	try {
			
	//		return(msgConfirmation.getText());
	//	}catch (Exception e) {
			// TODO: handle exception
			
			
	//		return(e.getMessage());
	//	}
	//	}
}}
