package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseclass.BaseClass1;
import pages.HomePage;
import pages.Login;
import utilities.ExcelUtil;

public class TC2_ManageStudents extends BaseClass1{
	HomePage homepage;
	Login login;
	
	@BeforeClass
	public void setUp() throws IOException
	{
		browserInitialization();
		homepage=new HomePage();
		login=new Login();
		logger= report.createTest("TC2_ManageStudents");
		ExcelUtil.setExcelFile();
	}
	
	
  @Test
  public void StudentClickMod() {
	  
	  
	  homepage.MngstudentClick();
  }
}
