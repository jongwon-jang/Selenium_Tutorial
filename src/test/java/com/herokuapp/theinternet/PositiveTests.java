package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void loginTest() {
		System.out.println("Starting loginTest");

//		Create Driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		WebDriver driver = new ChromeDriver();

//		maximize browser window
		driver.manage().window().maximize();

//		open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page opened");

//		enter user name
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
//		enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
//		click login button
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();
//		
//		verification:
//			new url
		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page URL is not the same as expected");
//			logout button is visible
		WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "Log out Button not visible");

//			successful login message
		WebElement successMessage = driver.findElement(By.cssSelector("div#flash"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = successMessage.getText();
//		Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not contain the expected message");
//		Close Browser
		driver.quit();

	}

}
