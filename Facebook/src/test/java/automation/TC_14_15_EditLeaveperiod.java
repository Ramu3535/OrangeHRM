package automation;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.openandclose;

public class TC_14_15_EditLeaveperiod extends openandclose{
	
	@Test(priority = 8,dataProvider = "editleavesperiod")

	public void editLeave(String username,String password,String month,String date) throws Exception {
		
		Properties properties = new Properties(); 
		FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");
		
	    properties.load(Lis);

		driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);
		
		driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);
		
		driver.findElement(By.id(properties.getProperty("Login"))).click();
		
		driver.findElement(By.id(properties.getProperty("Leave"))).click();
		
		driver.findElement(By.linkText(properties.getProperty("editleave"))).click();
		
		driver.findElement(By.linkText(properties.getProperty("editleaveperiod"))).click();
		
		driver.findElement(By.name(properties.getProperty("editbutton"))).click();
		
		WebElement sm=driver.findElement(By.id(properties.getProperty("editstartmonth")));
		sm.click();
		
		Select startmonth=new Select(sm);
		
		Thread.sleep(2000);
		
		startmonth.selectByVisibleText(month);
		
		Thread.sleep(2000);
		
		WebElement sd=driver.findElement(By.id(properties.getProperty("editstartdate")));
		sd.click();
		
		Select startdate=new Select(sd);
		
		Thread.sleep(2000);
		
		startdate.selectByVisibleText(date);
		
		Thread.sleep(2000);
		
		driver.findElement(By.name(properties.getProperty("editbutton"))).click();
		
		//edit
		
		Thread.sleep(2000);
		
		driver.findElement(By.name(properties.getProperty("editbutton"))).click();
		
		Thread.sleep(2000);		
		//Reset 
		
		driver.findElement(By.name(properties.getProperty("resetbutton"))).click();
		
		Thread.sleep(2000);
		
	
	}
	
	@DataProvider(name="editleavesperiod")
	
		
public Object[][] MultipleaddEntitlements() throws Exception{
		
		Object[][] data=null;
		
		File file=new File("./src/test/testdata/Leave.xlsx");
		
		FileInputStream elp=new FileInputStream(file);
		
		Workbook wbelp=WorkbookFactory.create(elp);
		
		Sheet stelp=wbelp.getSheet("editleaveperiod");
		
		data=new Object[stelp.getPhysicalNumberOfRows()-1][stelp.getRow(0).getPhysicalNumberOfCells()];
		
		for(int rowindex=stelp.getFirstRowNum()+1;rowindex<=stelp.getLastRowNum();rowindex++) {
			
			for(int colindex=stelp.getRow(rowindex).getFirstCellNum();colindex<stelp.getRow(rowindex).getLastCellNum();colindex++) {
				
				data[rowindex-1][colindex]=stelp.getRow(rowindex).getCell(colindex).toString();	
						
			
			}
			
			
			
		}
		
		wbelp.close();
		
		return data;
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	

}
