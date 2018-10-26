package com.org.Trujet.maven.testng;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TrujetWebsiteAutomationTest {
	WebDriver driver;
	
	@BeforeTest
	public void webdriver() throws InterruptedException
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Selenium\\Geckodriver\\geckodriver.exe\\");
		driver=new FirefoxDriver();
		driver.get("https://www.trujet.com/");
		 System.out.println("========================================================");
		System.out.println("Title of the webpage is is" +driver.getTitle());
		Thread.sleep(2000);
				
	}
	@Test(priority=1)
	public void cookieManagement() {
		Set<Cookie> before_deletion= driver.manage().getCookies();
		 System.out.println("========================================================");
		 System.out.println(" Number of Cookies before deletion = "+before_deletion.size());
		 System.out.println("========================================================");
		for (Cookie cookie : before_deletion) 
		{
		    System.out.println("========================================================");
			System.out.println("Name of the Cookie = "+cookie.getName());
			System.out.println("Domain of the Cookie = "+cookie.getDomain());
			System.out.println("Path of the Cookie = "+cookie.getPath());
			System.out.println("Value of the Cookie = "+cookie.getValue());
			System.out.println("HashCode of the Cookie = "+cookie.hashCode());
		    System.out.println("========================================================");
		}
		driver.manage().deleteAllCookies();
		Set<Cookie> after_deletion= driver.manage().getCookies();
		 System.out.println("========================================================");
		System.out.println("Number of Cookies after deletion = "+after_deletion.size());
		 System.out.println("========================================================");
		
	}
	
	
	@Test(priority=2)
	public void BootstrapDropDown()
	{
		driver.findElement(By.xpath("/html/body/form/section[1]/nav/div/ul/li[5]/a")).click();
		driver.findElement(By.xpath("//a[@title='Travel Agent']")).click();
		
	}
	 @Test(priority=3)
	 public void MultipleWindow() throws InterruptedException {
		 
		 String Parent = driver.getWindowHandle();
		 Thread.sleep(2000);
		 driver.close();
		 Set<String>child_window = driver.getWindowHandles();
		 System.out.println("========================================================");
		 System.out.println("Total number of winows opened = "+child_window.size());
		 System.out.println("========================================================");
		 for (String child : child_window) 
		 {
			if(!child.equalsIgnoreCase(Parent))
			{
				driver.switchTo().window(child);
			}
		}
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 driver.findElement(By.xpath("//a[@class='menuRed']")).click();
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//input[@id='txtfname']")).sendKeys("ABC");
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//input[@id='txtlname']")).sendKeys("XYZ");
	 }
	 
	 @Test(priority=4)
	 public void Scrolling() throws InterruptedException 
	 {
		 ((JavascriptExecutor)driver).executeScript("scroll(0,500)");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@id='txtlogin']")).sendKeys("ABC.XYZ");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@id='txtpassword']")).sendKeys("12345");
		 Thread.sleep(2000);
		 
	 }
	 
	 @Test(priority=5)
	 public void DropdownCountries() throws InterruptedException
	 {
		 Select country= new Select(driver.findElement(By.xpath("//select[@id='cboOrigin']")));
		 country.selectByValue("IN");
		 List<WebElement> options = country.getOptions();
		 
		 for (WebElement countries : options) 
		 {
		    
			System.out.println("Countries list = "+countries.getText());
			
		}
		 System.out.println("========================================================");
		 System.out.println("Total number of countries = "+options.size());
		 System.out.println("========================================================");
		 Thread.sleep(4000);
	 }
	 
	 @Test(priority=6)
	 public void StateDropDown() throws InterruptedException 
	 {
		 Select state = new Select(driver.findElement(By.xpath("//select[@id='cboDestination']")));
		 state.selectByValue("HYD");
		 
		List<WebElement> states= state.getOptions();
		System.out.println("========================================================");
		System.out.println("Total number of States are "+states.size());
		System.out.println("========================================================");
		for (WebElement state1 : states) 
		{
			
			System.out.println("States present in the list = "+state1.getText());
			
			
		}
		Thread.sleep(2000);
	 }
	 
	 @Test(priority=7)
	 public void submitAndRest() throws InterruptedException 
	 {
		 ((JavascriptExecutor)driver).executeScript("scroll(0, 800)");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@id='chkTandC']" )).click();
		 driver.findElement(By.xpath("//input[@id='cmdsubmit']" )).click();
		 Thread.sleep(2000);
		 ((JavascriptExecutor)driver).executeScript("scroll(0, 800)");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='Reset']" )).click();
		 Thread.sleep(2000);
		 driver.navigate().back();
		 driver.close();
	 }
	 
}
	 	 
	 
	 
	 
	
	 
	 
	 
	 
	 
	 
	 
	 


	 
	 
	 
	 
	 

	