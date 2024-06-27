package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppErrors;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	@Description("Verify Login page title ")
	@Owner("Kavitha PS")
	@Issue("ADO101")
	@Feature("Login page title")
	@Severity(SeverityLevel.MINOR)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND);
	}
	
	
	@Test(priority = 2)
	@Description("Verify Login page URL ")
	@Owner("Kavitha PS")
	@Issue("ADO102")
	@Feature("Login page URL")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppErrors.URL_NOT_FOUND);
	}
	
	
	@Test(priority = 3)
	@Description("Verify forgot password link")
	@Owner("Kavitha PS")
	@Feature("Login page forgot password")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.checkForgotPwdLinkExist(), AppErrors.ELEMENT_NOT_FOUND);
	}
	
	
	@Test(priority = 4)
	@Description("Verify Login functionality")
	@Owner("Kavitha PS")
	@Issue("ADO155")
	@Feature("Login page functionality")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		//if you are running from cmd use System.getProperty insted of prop
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND);
	}
	
	
}