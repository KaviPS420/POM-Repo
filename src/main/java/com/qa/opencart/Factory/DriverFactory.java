package com.qa.opencart.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.io.FileHandler;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.Exceptions.BrowserExceptions;
import com.qa.opencart.Exceptions.FrameworkException;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.logger.Log;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	

	/**
	 * This is used to init the driver on the basis on given browser name.
	 * 
	 * @param browserName
	 */
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This is used to init the driver on the basis on given browser name.
	 * 
	 * @param browserName
	 */
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("browser name is : " + browserName);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver());
			break;
		case "firefox":
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
			break;
		case "edge":
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
			break;
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			//System.out.println("plz pass the right browser name..." + browserName);
			Log.error("Please pass the correct browser Name" + browserName);
			throw new BrowserExceptions(AppErrors.BROWSER_NOT_FOUND);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));// loginPage

		return getDriver();

	}
	
	/**
	 * get the local thread copy of the driver
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * this is used to init the properties from the .properties file
	 * 
	 * @return this returns properties (prop)
	 */
	
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;
		// mvn clean install -Denv="qa"
		// mvn clean install

		String envName = System.getProperty("env");
		System.out.println("running test suite on env--->" + envName);

		if (envName == null) {
			System.out.println("env name is not given, hence running it on QA environment....");
			try {
				ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
					break;
				case "stage":
					ip = new FileInputStream(AppConstants.CONFIG_STAGE_FILE_PATH);
					break;
				case "dev":
					ip = new FileInputStream(AppConstants.CONFIG_DEV_FILE_PATH);
					break;
				case "uat":
					ip = new FileInputStream(AppConstants.CONFIG_UAT_FILE_PATH);
					break;
				case "prod":
					ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
					break;

				default:
					System.out.println("please pass the right env name.." + envName);
					throw new FrameworkException("===WRONGENVPASSED===");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	
	/**
	 * take screenshot
	 */
	
	public static String getScreenshot(String methodName) {
		
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp location
		
		String path = System.getProperty("user.dir")+"/screenshots/"+methodName+"_"+System.currentTimeMillis()+".png";
		
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
}
