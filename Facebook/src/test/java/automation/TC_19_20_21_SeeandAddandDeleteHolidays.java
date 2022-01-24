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

public class TC_19_20_21_SeeandAddandDeleteHolidays extends openandclose{

	@Test(priority = 11,dataProvider = "Seeadddeleteholidays")
		public void seeandAddDeleteholidays(String username,String password,String fromdate,String todate,String day,String holidayadd) throws Exception {
//see Holidays
		
		Properties properties = new Properties(); 
		FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");
		
	    properties.load(Lis);
		
			driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);
			
			driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);
			
			driver.findElement(By.id(properties.getProperty("Login"))).click();
			
			driver.findElement(By.id(properties.getProperty("Leave"))).click();
			
			driver.findElement(By.linkText(properties.getProperty("editleave"))).click();
			
			driver.findElement(By.id(properties.getProperty("seeholidays"))).click();
			
			WebElement seefrom=driver.findElement(By.id(properties.getProperty("holidaysfrom")));
			
			Thread.sleep(2000);
			
			
			seefrom.click();
			Thread.sleep(2000); 
			
			seefrom.clear();
			Thread.sleep(2000);
			
			seefrom.sendKeys(fromdate);
			
			Thread.sleep(2000);
			
			
			
			Actions seefromdate =new Actions(driver);
			
			seefromdate.sendKeys(Keys.DOWN);
			
			seefromdate.sendKeys(Keys.ENTER);
			
			seefromdate.perform();
			
			
			
			
			Thread.sleep(2000);
			
			WebElement seedateto=driver.findElement(By.id(properties.getProperty("holidaysto")));
			
			Thread.sleep(2000);
			
			seedateto.click();
			Thread.sleep(2000);
			seedateto.clear();
			
			Thread.sleep(2000);
			seedateto.sendKeys(todate);
			Thread.sleep(2000);
			
            Actions seetodate =new Actions(driver);
			
			seetodate.sendKeys(Keys.DOWN);
			
			seetodate.sendKeys(Keys.ENTER);
			
			seetodate.perform();
			
			
			
			
			Thread.sleep(2000);
			
			driver.findElement(By.id(properties.getProperty("holidayssearch"))).click();
			
			Thread.sleep(2000);
			
			//Add Holidays
			
			driver.findElement(By.id(properties.getProperty("holidaysadd"))).click();
			
			Thread.sleep(2000);
			
			driver.findElement(By.id(properties.getProperty("holiday"))).sendKeys(day);
			
			Thread.sleep(2000);
			
			WebElement addholiday=driver.findElement(By.id(properties.getProperty("holidaydate")));
			
			addholiday.click();
			
			addholiday.clear();
			
			addholiday.sendKeys(holidayadd);
			
			Thread.sleep(2000);
			
			 Actions addtodate =new Actions(driver);
				
				addtodate.sendKeys(Keys.DOWN);
				
				addtodate.sendKeys(Keys.ENTER);
				
				addtodate.perform();
				
			
			Select add=new Select(driver.findElement(By.id(properties.getProperty("holidaylength"))));
			
			Thread.sleep(2000);
			
			add.selectByIndex(1);
			
			Thread.sleep(2000);
			
			driver.findElement(By.id(properties.getProperty("savebutton"))).click();
			
			Thread.sleep(5000);
			
			//delete holiday
			
			
			WebElement deleteholiday=driver.findElement(By.name(properties.getProperty("selectbox")));
			Thread.sleep(2000);
			
			deleteholiday.click();
			Thread.sleep(2000);
			
			driver.findElement(By.id(properties.getProperty("delete"))).click();
			
			Thread.sleep(2000);
			driver.findElement(By.id(properties.getProperty("okbutton"))).click();
			Thread.sleep(2000);
			
	}
	
	@DataProvider(name="Seeadddeleteholidays")
	
public Object[][] MultipleaddEntitlements() throws Exception{
		
		Object[][] data=null;
		
		File file=new File("./src/test/testdata/Leave.xlsx");
		
		FileInputStream sadh=new FileInputStream(file);
		
		Workbook wbsadh=WorkbookFactory.create(sadh);
		
		Sheet stsadh=wbsadh.getSheet("seeAdddeleteHoliday");
		
		data=new Object[stsadh.getPhysicalNumberOfRows()-1][stsadh.getRow(0).getPhysicalNumberOfCells()];
		
		for(int rowindex=stsadh.getFirstRowNum()+1;rowindex<=stsadh.getLastRowNum();rowindex++) {
			
			for(int colindex=stsadh.getRow(rowindex).getFirstCellNum();colindex<stsadh.getRow(rowindex).getLastCellNum();colindex++) {
				
				data[rowindex-1][colindex]=stsadh.getRow(rowindex).getCell(colindex).toString();	
						
			
			}
			
			
			
		}
		
		wbsadh.close();
		
		return data;
	}
		
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

}
