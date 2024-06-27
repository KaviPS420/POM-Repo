package com.qa.opencart.tests;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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

public class AccountsPageTest extends BaseTest {

	@BeforeClass()
	public void accSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test()
	@Description("Verify Account page title")
	@Owner("Kavitha PS")
	@Issue("ADO121")
	@Feature("Account page title")
	@Severity(SeverityLevel.MINOR)
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND);
	}
	
	@Test
	@Description("Verify Account page url")
	@Owner("Kavitha PS")
	@Issue("ADO122")
	@Feature("Account page url")
	@Severity(SeverityLevel.MINOR)
	public void accPageURLTest() {
		Assert.assertTrue(accPage.getAccPageURL().contains(AppConstants.ACC_PAGE_FRACTION_URL), AppErrors.URL_NOT_FOUND);
	}
	

	@Test
	@Description("Verify Account page Header")
	@Owner("Kavitha PS")
	@Issue("ADO121")
	@Feature("Account page Header")
	@Severity(SeverityLevel.MINOR)
	public void accPageHeadersTest() {
		List<String> accPageHeadersList = accPage.getAccPageHeaders();
		Assert.assertEquals(accPageHeadersList, AppConstants.ACC_PAGE_HEADERS_LIST, AppErrors.LIST_IS_NOT_MATCHED);
	}
	
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"macbook", 3},
			{"imac", 1},
			{"samsung", 2}
			
		};
	}
	
	@Test(dataProvider = "getSearchData")
	@Description("Verify Account page search Test")
	@Owner("Kavitha PS")
	@Issue("ADO125")
	@Feature("Account page Header")
	@Severity(SeverityLevel.MINOR)
	public void searchTest(String searchKey, int resultsCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		
		Assert.assertEquals(searchResultsPage.getSearchResultsCount(), resultsCount, AppErrors.RESULTS_COUNT_MISMATCHED);
	}
	
	
}
