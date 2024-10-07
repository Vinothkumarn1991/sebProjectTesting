package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {

		super(driver);

	}
	
	
	@FindBy(xpath="//*[@id=\"navbar-collapse-header\"]/div/a[2]")
	WebElement Register;
	
	public void clickRegisterButton() {
		
		Register.click();
		
		
	}
	
	
	@FindBy(xpath="//*[@id=\"login-btn\"]")
	WebElement Login;
	
	
	public void clickOnLoginButton() {
		
		Login.click();
		
	}
	
}
