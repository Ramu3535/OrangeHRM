package core;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class openandclose {
 
	protected WebDriver driver=null;
	public WebElement test= new RemoteWebElement();
	
	
 @BeforeTest
	public void open() {
		//open browser
        WebDriverManager.chromedriver().setup();
		
		driver=new ChromeDriver();

		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
	}
	@AfterTest
	public void close() throws Exception {
		
		driver.findElement(By.id("welcome")).click();
		Thread.sleep(2000);
		
		
		driver.findElement(By.linkText("Logout")).click();
		
		driver.quit();
		
		
		
		
		
	}
}
