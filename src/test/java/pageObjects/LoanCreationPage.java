package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoanCreationPage extends BasePage {

	public LoanCreationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//h3[contains(text(),'Welcome to Education Loan Finance!')]")

	WebElement Titlepage;

	@FindBy(xpath = "//*[@id=\\\"header-blk-id\\\"]/div/div[2]/div[1]/button")

	WebElement LogoutButton;

	public boolean IsTitlePageIsExist() {

	try {
		return (Titlepage.isDisplayed());
		
		

	} catch (Exception e) {
		return false;
	}}

	public void clickLogoutButton() {
		
		
		LogoutButton.click();

	}

}
