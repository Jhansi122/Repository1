package com.org.trujet.maven.selenium;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.openqa.selenium.JavascriptExecutor;

public class Trujet {
	WebDriver driver;

	@BeforeTest
	public void browesr() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Selenium\\Geckodriver\\geckodriver.exe\\");
		
		driver = new FirefoxDriver();
		
		driver.get("https://www.trujet.com/");
		
		driver.manage().deleteAllCookies();
		
		Thread.sleep(3000);

		driver.findElement(By.xpath("/html/body/form/section[1]/nav/div/ul/li[5]/a")).click();

		driver.findElement(By.xpath("//a[@title='Travel Agent']")).click();
	}

	// Handling current window
	@Test(priority = 1)
	
	     public void WindowHandling() throws InterruptedException 
	      {
		      String parent = driver.getWindowHandle();
		      
		      Set<String> child = driver.getWindowHandles();

		      for (String all : child) 
		     
		      {
		    	    
			if (!parent.equalsIgnoreCase(all)) 
			{

				driver.switchTo().window(all);

			}
			
			System.out.println("total number of windows opened=  " + child.size());
		}
		      
		Thread.sleep(6000);

		driver.findElement(By.xpath("//span[contains(text(),'Create Profile')]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@id='txtfname']")).sendKeys("abc");
		
		driver.findElement(By.xpath("//input[@id='txtlname']")).sendKeys("xyz");
		Thread.sleep(2000);
		
		((JavascriptExecutor) driver).executeScript("scroll(0,400)");
		
		Select country = new Select(driver.findElement(By.xpath("//select[@id='cboOrigin']")));
		country.selectByValue("IN");
		
		((JavascriptExecutor) driver).executeScript("scroll(0,800)");
		
		Select state = new Select(driver.findElement(By.xpath("//select[@id='cboDestination']")));
		state.selectByVisibleText("Hyderabad");

		driver.findElement(By.xpath("//input[@id='chkTandC']")).click();
		
		driver.findElement(By.xpath("//input[@id='cmdsubmit']")).click();
		
		((JavascriptExecutor) driver).executeScript("scroll(0,800)");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@value='Reset']")).click();
		
		driver.navigate().back();

		Thread.sleep(2000);
		driver.close();
		
		driver.switchTo().window(parent);
		driver.close();

	}
}
