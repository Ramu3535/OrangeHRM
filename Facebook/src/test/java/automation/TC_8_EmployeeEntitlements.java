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


public class TC_8_EmployeeEntitlements extends openandclose {

	@Test(priority = 4,dataProvider = "Employeeentitlements")
	public void employeeEntitlements(String username,String password,String employeename,String leavtyp,String prleave) throws Exception {
	
	Properties properties=new Properties();
	
	FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");
	
    properties.load(Lis);
    	
	driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);
	
	driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);
	
	driver.findElement(By.id(properties.getProperty("Login"))).click();
	
	driver.findElement(By.id(properties.getProperty("Leave"))).click();
	
	driver.findElement(By.id(properties.getProperty("Addsingleentitlement"))).click();
	
	driver.findElement(By.id(properties.getProperty("entitlementsemp"))).click();
	
	WebElement employee=driver.findElement(By.id(properties.getProperty("emplname")));
	
	Thread.sleep(2000);
	
	employee.click();
	
	Thread.sleep(2000);
	
	employee.clear();
	
	Thread.sleep(2000);
	
	employee.sendKeys(employeename);
	
	Actions actionl = new Actions(driver);
	
	actionl.sendKeys(Keys.DOWN);
	
	actionl.sendKeys(Keys.ENTER);
	
	actionl.perform();
	
	
	WebElement lt=driver.findElement(By.id(properties.getProperty("Leavetype")));
	
	lt.click();
	
    Select sele=new Select(lt);
	
	Thread.sleep(2000);
	
	sele.selectByVisibleText(leavtyp);;
	
	Thread.sleep(2000);
	
	WebElement lp=driver.findElement(By.id(properties.getProperty("Leaveperiod")));
	
	lp.click();
	
	Select leaveperiod=new Select(lp);

	Thread.sleep(2000);
	
	leaveperiod.selectByVisibleText(prleave);
	
	Thread.sleep(2000);
	
	driver.findElement(By.id(properties.getProperty("search"))).click();
	
	}
	@DataProvider(name="Employeeentitlements")
	
	public Object[][] MultipleaddEntitlements() throws Exception{
		
		Object[][] data=null;
		
		File file=new File("./src/test/testdata/Leave.xlsx");
		
		FileInputStream ee=new FileInputStream(file);
		
		Workbook wbee=WorkbookFactory.create(ee);
		
		Sheet st=wbee.getSheet("EmployeeEntitlements");
		
		data=new Object[st.getPhysicalNumberOfRows()-1][st.getRow(0).getPhysicalNumberOfCells()];
		
		for(int rowindex=st.getFirstRowNum()+1;rowindex<=st.getLastRowNum();rowindex++) {
			
			for(int colindex=st.getRow(rowindex).getFirstCellNum();colindex<st.getRow(rowindex).getLastCellNum();colindex++) {
				
				data[rowindex-1][colindex]=st.getRow(rowindex).getCell(colindex).toString();	
						
			
			}
			
			
			
		}
		
		wbee.close();
		
		return data;
		
		}
		
		
	
	

}
