package automation;

import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.openandclose;


@Test(priority = 1,dataProvider = "applyleave")
public class TC_2_ApplyLeave extends openandclose {
	
	public void applyLeave(String username,String password) throws Exception {
	
		Properties properties = new Properties(); 
		
		FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");
		
	    properties.load(Lis);
				
				driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);
				
				driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);
				
				driver.findElement(By.id(properties.getProperty("Login"))).click();
				
				driver.findElement(By.id(properties.getProperty("Leave"))).click();
				
				driver.findElement(By.id(properties.getProperty("Applyleave"))).click();
				
			
				
				
	}
		
	@DataProvider(name="applyleave")
	public Object[][] MultipleaddEntitlements() throws Exception{
		
		Object[][] data=null;
		
		File file=new File("./src/test/testdata/Leave.xlsx");
		
		FileInputStream al=new FileInputStream(file);
		
		Workbook wbal=WorkbookFactory.create(al);
		
		Sheet stal=wbal.getSheet("ApplyLeave");
		
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


