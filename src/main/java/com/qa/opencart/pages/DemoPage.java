package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class DemoPage {
	int a,b;
	
	By cart = By.id("Demo123");
	
	public void getDemo() {
		System.out.println("Working on demo page" + cart);
		a=10;
		b=50;
		int c= a+b;
		System.out.println(c);
	}

}
