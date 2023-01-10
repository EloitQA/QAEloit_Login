package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseclass.BaseClass1;

public class Login extends BaseClass1{
	
	public Login() {

		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//input[@id='txtUserName']")
	public WebElement txtUsr;//fragnel@eloit.com//FrAgnelV@2022#

	@FindBy(xpath = "//span[contains(text(),'Next')]")
	public WebElement btnNxt;
	
	
	@FindBy(xpath = "//input[@id='txtPassword']")
	public WebElement txtPwd;
	
	@FindBy(xpath = "//span[contains(text(),'Login')]")
	public WebElement btnLogin;
	
	public void getUserame(String User) throws InterruptedException {
		txtUsr.clear();
		txtUsr.sendKeys(User);
		
		
	}
	
	public void getPwd(String pwd) throws InterruptedException {
		txtPwd.clear();
		txtPwd.sendKeys(pwd);
		
		
	}
	public void ClickNextBtn() throws InterruptedException {
		btnNxt.click();
		
	}
	
	public void ClickLoginBtn() throws InterruptedException {
		btnLogin.click();
		
	}
}
