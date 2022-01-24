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


public class TC_25_AssignLeave extends openandclose{
@Test(priority = 13,dataProvider = "assignleave")
	public void assignLeave(String username,String password,String name,String leavetype,String fromdate,String todate) throws Exception {
		// Assign Leave
	Properties properties = new Properties(); 
	FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");
	
    properties.load(Lis);
	
	
			driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);
			
			driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);
			
			driver.findElement(By.id(properties.getProperty("Login"))).click();
			
			driver.findElement(By.id(properties.getProperty("Leave"))).click();
			
			driver.findElement(By.linkText(properties.getProperty("assiagnleave"))).click();
			
			WebElement assign=driver.findElement(By.id(properties.getProperty("assignleaveempname")));
			
			Thread.sleep(2000);
			
			assign.click();
			
			Thread.sleep(2000);
			
			assign.clear();
			
			Thread.sleep(2000);
			
			assign.sendKeys(name);
			
			Thread.sleep(2000);
			
			Actions assignemployee=new Actions(driver);
			
			Thread.sleep(2000);
			
			assignemployee.sendKeys(Keys.DOWN);
			
			Thread.sleep(2000);
			
			assignemployee.sendKeys(Keys.ENTER); 
			
			Thread.sleep(2000);
			
			assignemployee.perform();
			
			Thread.sleep(2000);
			
			WebElement lta=driver.findElement(By.id(properties.getProperty("assignleavetype")));
			lta.click();
			
			Select leavetypeassign=new Select(lta);
			
			
			Thread.sleep(2000);
			
			leavetypeassign.selectByVisibleText(leavetype);
			
			Thread.sleep(2000);
			
			WebElement assigndate=driver.findElement(By.id(properties.getProperty("assignleavefrom")));
			
			Thread.sleep(2000);
			
			assigndate.click();
			
			assigndate.clear();
			
			Thread.sleep(2000);
			
			assigndate.sendKeys(fromdate);
			
			Thread.sleep(2000);
			
			Actions assignfromdate=new Actions(driver);
			
			assignfromdate.sendKeys(Keys.DOWN);
			
			assignfromdate.sendKeys(Keys.ENTER);
			
			assignfromdate.perform();
			
			WebElement assignto=driver.findElement(By.id(properties.getProperty("assignleaveto")));
			
			Thread.sleep(2000);
			
			assignto.click();
			
			Thread.sleep(2000);
			
			assignto.clear();
			
			Thread.sleep(2000);
			
			assignto.sendKeys(todate);
			
			Thread.sleep(2000);
			
			Actions assigntodate=new Actions(driver);
			
			assigntodate.sendKeys(Keys.DOWN);
			
			assigntodate.sendKeys(Keys.ENTER);
			//
			assigntodate.perform();
		Thread.sleep(2000);
			
			driver.findElement(By.id(properties.getProperty("assignbutton"))).click();
			 Thread.sleep(2000);
			
			 driver.findElement(By.id(properties.getProperty("assignok"))).click();
			 
			 Thread.sleep(2000);
			

	}

@DataProvider(name="assignleave")

public Object[][] MultipleaddEntitlements() throws Exception{
	
	Object[][] data=null;
	
	File file=new File("./src/test/testdata/Leave.xlsx");
	
	FileInputStream al=new FileInputStream(file);
	
	Workbook wbal=WorkbookFactory.create(al);
	
	Sheet stal=wbal.getSheet("assignleaves");
	
	data=new Object[stal.getPhysicalNumberOfRows()-1][stal.getRow(0).getPhysicalNumberOfCells()];
	
	for(int rowindex=stal.getFirstRowNum()+1;rowindex<=stal.getLastRowNum();rowindex++) {
		
		for(int colindex=stal.getRow(rowindex).getFirstCellNum();colindex<stal.getRow(rowindex).getLastCellNum();colindex++) {
			
			data[rowindex-1][colindex]=stal.getRow(rowindex).getCell(colindex).toString();	
					
		
		}
		
		
		
	}
	
	wbal.close();
	
	return data;
}
	

}
