package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class DemoPage {
	
	By cart = By.id("Demo123");
	
	public void getDemo() {
		System.out.println("Working on demo page" + cart);
	}

}
