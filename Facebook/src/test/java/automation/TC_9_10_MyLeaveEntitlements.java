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


public class TC_9_10_MyLeaveEntitlements extends openandclose {
	
	@Test(priority = 5 ,dataProvider = "myLeaveentitlement")

	public void myLeaventitlements(String username,String password,String typeleave,String leavperiod) throws Exception {
		
		Properties properties=new Properties();
		FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");
		
	    properties.load(Lis);
	    
		driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);
		
		driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);
		
		driver.findElement(By.id(properties.getProperty("Login"))).click();
		
		driver.findElement(By.id(properties.getProperty("Leave"))).click();
		
		driver.findElement(By.id(properties.getProperty("Addsingleentitlement"))).click();
		
		driver.findElement(By.id(properties.getProperty("myleaveentitlements"))).click();
		
		WebElement elt=driver.findElement(By.id(properties.getProperty("Leavetype")));
		elt.click();
		
		Select leaveetype=new Select(elt);
		
		Thread.sleep(2000);
	
		
		leaveetype.selectByVisibleText(typeleave);
		
		Thread.sleep(2000);
		WebElement pl=driver.findElement(By.id(properties.getProperty("Leaveperiod")));
		
		pl.click();
		Select periodleave=new Select(pl);
		
		Thread.sleep(2000);
		
		periodleave.selectByVisibleText(leavperiod);
		
		Thread.sleep(2000);
		
		driver.findElement(By.id(properties.getProperty("search"))).click();
		
		//delete 
		
		//driver.findElement(By.name("chkSelectRow[]")).click();
		
		//Thread.sleep(2000);
		
		//driver.findElement(By.id("btnDelete")).click();
		
		//Thread.sleep(2000);
		
	}
	
	@DataProvider(name="myLeaveentitlement")
	
public Object[][] MultipleaddEntitlements() throws Exception{
		
		Object[][] data=null;
		
		File file=new File("./src/test/testdata/Leave.xlsx");
		
		FileInputStream mle=new FileInputStream(file);
		
		Workbook wbmle=WorkbookFactory.create(mle);
		
		Sheet stle=wbmle.getSheet("Myleaveentitlement");
		
		data=new Object[stle.getPhysicalNumberOfRows()-1][stle.getRow(0).getPhysicalNumberOfCells()];
		
		for(int rowindex=stle.getFirstRowNum()+1;rowindex<=stle.getLastRowNum();rowindex++) {
			
			for(int colindex=stle.getRow(rowindex).getFirstCellNum();colindex<stle.getRow(rowindex).getLastCellNum();colindex++) {
				
				data[rowindex-1][colindex]=stle.getRow(rowindex).getCell(colindex).toString();	
						
			
			}
			
			
			
		}
		
		wbmle.close();
		
		return data;
	}
		
		
		
		
		

	

}
