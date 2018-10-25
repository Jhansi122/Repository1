package com.org.trujet.maven.selenium;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

public class TrujetTest {
	WebDriver driver;

	@BeforeTest
	public void browesr() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Selenium\\Geckodriver\\geckodriver.exe\\");
		
		driver = new FirefoxDriver();
		
		driver.get("https://www.trujet.com/");
		Set<Cookie> cookie = driver.manage().getCookies();
		System.out.println("Total number of Cookies in the website are "+cookie.size());
		for (Cookie cookie1 : cookie) 
		{
			System.out.println("Name = " +cookie1.getName());
			System.out.println("Domain = " +cookie1.getDomain());
			System.out.println("Path = " +cookie1.getPath());
			System.out.println("Value = " +cookie1.getValue());
			System.out.println("Hash Codes = " +cookie1.hashCode());
		}
		
		driver.manage().deleteAllCookies();
		
		Set<Cookie> afterDeletion = driver.manage().getCookies();
		
		System.out.println("Cookies after deletion = "+afterDeletion.size());
		
		Thread.sleep(3000);

		driver.findElement(By.xpath("/html/body/form/section[1]/nav/div/ul/li[5]/a")).click();

		driver.findElement(By.xpath("//a[@title='Travel Agent']")).click();
	}

	// Handling current window
	
	@Test
	
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
		      
		//Thread.sleep(5000);
		      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

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
