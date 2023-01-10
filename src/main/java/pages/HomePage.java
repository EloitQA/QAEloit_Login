package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseclass.BaseClass1;

public class HomePage extends BaseClass1 {

	public HomePage() {

		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(xpath = "//span[contains(text(),'Student  ')]")
	public WebElement student;
	@FindBy(xpath = "//span[contains(text(),'Manage Students')]")
	public WebElement mngstudent;
	
	public void studentClick()
	{
		student.click();
	}
	public void MngstudentClick()
	{
		mngstudent.click();
	}
	
	
}
