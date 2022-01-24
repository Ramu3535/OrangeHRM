
package automation;

import java.io.File;

import java.io.FileInputStream;

import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import core.openandclose;

public class TC_3_4_5_MyLeave extends openandclose{
	
	@Test (priority = 1,dataProvider="getTestData") 
	public void myLeave(String username,String password,String fromdate,String todate) throws Exception {
	
		Properties properties = new Properties(); 
		FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");
		
        properties.load(Lis);
		driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);
		
		driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);
		
		driver.findElement(By.id(properties.getProperty("Login"))).click();
		
		driver.findElement(By.id(properties.getProperty("Leave"))).click();
		
		driver.findElement(By.linkText(properties.getProperty("myleave"))).click();
		
		
		
		WebElement from =driver.findElement(By.id(properties.getProperty("Leavefrom")));
		Thread.sleep(2000);
		from.click();
		Thread.sleep(2000);
		
		from.clear();
		Thread.sleep(2000);
		from.sendKeys(fromdate);
		Actions fromleave=new Actions(driver);
		fromleave.sendKeys(Keys.DOWN);
		
		fromleave.sendKeys(Keys.ENTER);
		
		fromleave.perform();
		
		WebElement to=driver.findElement(By.id(properties.getProperty("Leaveto")));
		Thread.sleep(2000);
		to.click();
		Thread.sleep(2000);
		to.clear();
		Thread.sleep(2000);
		to.sendKeys(todate);
		Actions Toleave=new Actions(driver);
		Toleave.sendKeys(Keys.DOWN);
		
		Toleave.sendKeys(Keys.ENTER);
		
		Toleave.perform();
		WebElement leavecheckbox=driver.findElement(By.id(properties.getProperty("Checkboxselect")));
		
		leavecheckbox.click();
		
		leavecheckbox.click();
		
		
		driver.findElement(By.id(properties.getProperty("LeaveSearch"))).click();
		
		//Select myleave=new Select(driver.findElement(By.id("select_leave_action_69")));
		
		//Thread.sleep(2000);
		
		//myleave.selectByIndex(1);
		
		//driver.findElement(By.id("btnSave")).click();
		
		//Thread.sleep(2000);
		
	}
		@DataProvider(name="getTestData")
		
		public Object[][] getTestData() throws Exception{
			
			Object[][] data=null;
					
					File file=new File("./src/test/testdata/Leave.xlsx");
					FileInputStream fileinputstream= new FileInputStream(file);
					org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(fileinputstream);
					
					Sheet sheet = workbook.getSheet("MyLeave");
				
					data = new Object[sheet.getPhysicalNumberOfRows()-1][sheet.getRow(0).getPhysicalNumberOfCells()];
					
					for(int rowIndex=sheet.getFirstRowNum()+1;rowIndex<=sheet.getLastRowNum();rowIndex++) {
					
					 
						for(int colIndex=sheet.getRow(rowIndex).getFirstCellNum();colIndex<sheet.getRow(rowIndex).getLastCellNum();colIndex++) {
					    data [rowIndex-1][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).toString();
					    }
					}
					
			
					
			       fileinputstream.close();
			return data;
			
		}
		
	
	}




