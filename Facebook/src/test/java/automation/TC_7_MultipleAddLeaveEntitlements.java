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


public class TC_7_MultipleAddLeaveEntitlements extends openandclose {
	
	
@Test(priority = 3,dataProvider="MultipleaddEntitlements")
	public void multipleAddentitlements(String username,String password,String location,String subunit,String typeleave,String prleave,String entitlement) throws Exception {
		
	Properties properties = new Properties(); 
	FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");
	
    properties.load(Lis);
	
		driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);
		
		driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);
		
		driver.findElement(By.id(properties.getProperty("Login"))).click();
		
		driver.findElement(By.id(properties.getProperty("Leave"))).click();
		
		driver.findElement(By.id(properties.getProperty("Addsingleentitlement"))).click();
		
		driver.findElement(By.id(properties.getProperty("Singleaddentitlement"))).click();
		
		driver.findElement(By.id(properties.getProperty("multiplecheckbox"))).click();
		
		Thread.sleep(2000);
		
		WebElement addloc=driver.findElement(By.id(properties.getProperty("multiplelocation")));
		
		addloc.click();
		
		Select Addlea=new Select(addloc);
		
		Thread.sleep(2000);
		
		Addlea.selectByVisibleText(location);
		
		Thread.sleep(2000);
		
		WebElement addleav=driver.findElement(By.id(properties.getProperty("multiplesubunit")));
		
		addleav.click();
		
		Select Addleave=new Select(addleav);
		
		Thread.sleep(5000);
		
		Addleave.selectByVisibleText(subunit);
		
		Thread.sleep(2000);
		
		WebElement letp=driver.findElement(By.id(properties.getProperty("multipleleavetype")));
		
		letp.click();
		
         Select leavetypee=new Select(letp);
		
	    Thread.sleep(2000);
	    
	    leavetypee.selectByVisibleText(typeleave);

		Thread.sleep(2000);
		
		WebElement lp=driver.findElement(By.id(properties.getProperty("Leaveperiod")));
		
		lp.click();
		
		Select dropdownnn=new Select(lp);
		
		Thread.sleep(2000);
		
		dropdownnn.selectByVisibleText(prleave);
		
		Thread.sleep(2000);
		
		driver.findElement(By.id(properties.getProperty("multipleentitlement"))).sendKeys(entitlement);
		
		Thread.sleep(2000);
		
		driver.findElement(By.id(properties.getProperty("Save"))).click();
		
		}
@DataProvider(name="MultipleaddEntitlements")

public Object[][] MultipleaddEntitlements() throws Exception{
	
	Object[][] data=null;
	
	File file=new File("./src/test/testdata/Leave.xlsx");
	
	FileInputStream mae=new FileInputStream(file);
	
	Workbook wb=WorkbookFactory.create(mae);
	
	Sheet st=wb.getSheet("MultipleAddLeaveEntitlements");
	
	data=new Object[st.getPhysicalNumberOfRows()-1][st.getRow(0).getPhysicalNumberOfCells()];
	
	for(int rowindex=st.getFirstRowNum()+1;rowindex<=st.getLastRowNum();rowindex++) {
		
		for(int colindex=st.getRow(rowindex).getFirstCellNum();colindex<st.getRow(rowindex).getLastCellNum();colindex++) {
			
			data[rowindex-1][colindex]=st.getRow(rowindex).getCell(colindex).toString();	
					
		
		}
		
		
		
	}
	
	wb.close();
	
	return data;
	
	}
	
	
	
	
}



