package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseclass.BaseClass1;
import pages.Login;
import utilities.ExcelUtil;


public class TC1_Login extends BaseClass1 {
	Login login;
	
	@BeforeClass
	public void setUp() throws IOException
	{
		browserInitialization();
		login=new Login();
		logger= report.createTest("TC1_Login");
		ExcelUtil.setExcelFile();
	}
	
  @Test 
  public void EnterUsename() throws InterruptedException {
	  
	  login.getUserame("fragnel@eloit.com");
	  
  }
  
  @Test (dependsOnMethods ="EnterUsename")
  public void ClickNext() throws InterruptedException {
	  
	  login.ClickNextBtn();
	  
  }
  
  @Test (dependsOnMethods ="ClickNext")
  public void EnterPwd() throws InterruptedException {
	  
	  login.getPwd("FrAgnelV@2020&");
	  Thread.sleep(10000);
	  
  }
  
  @Test (dependsOnMethods ="EnterPwd")
  public void ClickLogin() throws InterruptedException {
	  Thread.sleep(1000);
	  login.ClickLoginBtn();
	  Thread.sleep(1000);
	  
	  
  }
}
