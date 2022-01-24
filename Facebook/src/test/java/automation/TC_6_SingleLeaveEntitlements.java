package automation;

import java.io.File;

import java.io.FileInputStream;

import java.util.Properties;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.openandclose;


public class TC_6_SingleLeaveEntitlements extends openandclose {

	@Test(priority = 2, dataProvider = "singleleaveEntitlements")
	public void singleLeaveEntitlements(String username, String password, String name,String typeleave,String levaep,String entitlements) throws Exception {
			

		Properties properties = new Properties(); 
		
		
		FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");
		
        properties.load(Lis);
        
        driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);

		driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);

		driver.findElement(By.id(properties.getProperty("Login"))).click();

		driver.findElement(By.id(properties.getProperty("Leave"))).click();

		driver.findElement(By.id(properties.getProperty("Addsingleentitlement"))).click();

		driver.findElement(By.id(properties.getProperty("Singleaddentitlement"))).click();

		WebElement dynamiclist = driver.findElement(By.id(properties.getProperty("Employeename")));

		dynamiclist.click();

		Thread.sleep(2000);

		dynamiclist.clear();

		Thread.sleep(2000);

		dynamiclist.sendKeys(name);

		Thread.sleep(2000);

		Actions action = new Actions(driver);
		
		action.sendKeys(Keys.DOWN);
		
		action.sendKeys(Keys.ENTER);
		
		action.perform();

		Select leavetype = new Select(driver.findElement(By.id(properties.getProperty("Leavetype"))));

		Thread.sleep(2000);

		leavetype.selectByVisibleText(typeleave);;

		Thread.sleep(2000);
		
        WebElement drwn= driver.findElement(By.id(properties.getProperty("Leaveperiod")));
		
		drwn.click();
		
		Select dropdown = new Select(drwn);
         	
        Thread.sleep(2000);

		dropdown.selectByVisibleText(levaep);

		Thread.sleep(2000);

		driver.findElement(By.id(properties.getProperty("Entitlement"))).sendKeys(entitlements);

		Thread.sleep(2000);

		driver.findElement(By.id(properties.getProperty("Save"))).click();

	}
 
	@DataProvider(name = "singleleaveEntitlements")

	public Object[][] singleleaveentitlements() throws Exception {

		Object[][] data = null;

		File file = new File("./src/test/testdata/Leave.xlsx");

		FileInputStream sle = new FileInputStream(file);

		Workbook wbsle = WorkbookFactory.create(sle);

		Sheet stsle = wbsle.getSheet("Singleleaveentitlements");

		data = new Object[stsle.getPhysicalNumberOfRows()-1][stsle.getRow(0).getPhysicalNumberOfCells()];

		for (int rowindex = stsle.getFirstRowNum()+1; rowindex<=stsle.getLastRowNum(); rowindex++) {

			for (int colindex = stsle.getRow(rowindex).getFirstCellNum(); colindex < stsle.getRow(rowindex).getLastCellNum(); colindex++) {
					

				data[rowindex-1][colindex] = stsle.getRow(rowindex).getCell(colindex).toString();

			}
		}

		wbsle.close();
		
		
		

		return data;

	}

}
