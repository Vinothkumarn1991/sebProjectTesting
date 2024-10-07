package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub

	}

	@FindBy(xpath = "//*[@id=\"login-username\"]")

	WebElement UserEmail;

	@FindBy(xpath = "//*[@id=\"login-password\"]")

	WebElement UserPassword;

	@FindBy(xpath = "//*[@id=\"login-btn\"]")
	WebElement LoginButton;

	public void SetUserEmail(String email)

	{
		UserEmail.sendKeys(email);

	}

	public void SetUserPassword(String password)

	{
		UserPassword.sendKeys(password);

	}

	public void clickLogin()

	{

		LoginButton.click();
	}

}
