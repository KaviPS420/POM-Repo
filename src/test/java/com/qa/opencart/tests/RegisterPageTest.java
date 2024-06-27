package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterPageTest extends BaseTest{
	
	
	@BeforeClass()
	public void regSetup() {
		regPage = loginPage.navigateToRegisterPage();
	}
	
	
	@DataProvider
	public Object[][] userRegTestData() {
		return new Object[][] {
			{"Kavithamani", "selenium", "9800000656", "Kavi@123", "yes"},
			{"Ruhikashree", "Selenium", "9098765490", "Ruhi@123", "no"},
			{"Athiran", "Sele", "9876711111", "Athi@123", "yes"}
		};
	}
	
	@DataProvider
	public Object[][] userRegTestDataFromSheet() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	@DataProvider
	public Object[][] userRegTestDataFromCSV() {
		return CSVUtil.csvData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	
	@Test(dataProvider = "userRegTestDataFromCSV")
	public void userRegisterationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
				
		Assert.assertTrue
		(regPage.userRegister(firstName, lastName, StringUtils.getRandomEmailId(), telephone, password, subscribe), 
						AppErrors.USER_REG_NOT_DONE);
	}
	
	

}