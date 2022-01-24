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

public class TC_16_17_AddandDeleteLeaveTypes extends openandclose{

	@Test(priority = 9,dataProvider = "Addanddeleteleavetypes")
	public void addandDelete(String username,String password,String day) throws Exception {
		// Add leave type
		Properties properties = new Properties(); 
		FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");
		
	    properties.load(Lis);
	    
		driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);
		
		driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);
		
		driver.findElement(By.id(properties.getProperty("Login"))).click();
		
		driver.findElement(By.id(properties.getProperty("Leave"))).click();
		
		driver.findElement(By.linkText(properties.getProperty("editleave"))).click();
		
		driver.findElement(By.linkText(properties.getProperty("addleavetype"))).click();
		
		driver.findElement(By.name(properties.getProperty("addbutton"))).click();
		
		driver.findElement(By.name(properties.getProperty("addleavetypename"))).sendKeys(day);
		
		Thread.sleep(2000);
		
		driver.findElement(By.name(properties.getProperty("savebutton"))).click();
		

		Thread.sleep(2000);
		
		//delete leave type
		
		driver.findElement(By.name(properties.getProperty("select"))).click();
		
		Thread.sleep(2000);

		
		driver.findElement(By.id(properties.getProperty("delete"))).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.id(properties.getProperty("okbutton"))).click();
		
		Thread.sleep(2000);
		

	}
	
	@DataProvider(name="Addanddeleteleavetypes")
	
	
public Object[][] MultipleaddEntitlements() throws Exception{
		
		Object[][] data=null;
		
		File file=new File("./src/test/testdata/Leave.xlsx");
		
		FileInputStream adlt=new FileInputStream(file);
		
		Workbook wbadlt=WorkbookFactory.create(adlt);
		
		Sheet stadlt=wbadlt.getSheet("addanddeleteleavetypes");
		
		data=new Object[stadlt.getPhysicalNumberOfRows()-1][stadlt.getRow(0).getPhysicalNumberOfCells()];
		
		for(int rowindex=stadlt.getFirstRowNum()+1;rowindex<=stadlt.getLastRowNum();rowindex++) {
			
			for(int colindex=stadlt.getRow(rowindex).getFirstCellNum();colindex<stadlt.getRow(rowindex).getLastCellNum();colindex++) {
				
				data[rowindex-1][colindex]=stadlt.getRow(rowindex).getCell(colindex).toString();	
						
			
			}
			
			
			
		}
		
		wbadlt.close();
		
		return data;
	}
		
		

}
