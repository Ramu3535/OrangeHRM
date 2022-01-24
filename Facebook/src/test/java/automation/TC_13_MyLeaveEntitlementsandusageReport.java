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

public class TC_13_MyLeaveEntitlementsandusageReport extends openandclose {

	@Test(priority = 14,dataProvider = "myleaveentitlementreports")
	public void leaveEntitlements(String username,String password,String fromandto ) throws Exception {
		
		Properties properties = new Properties(); 
		
		FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");
		
	    properties.load(Lis);
		driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);
		
		driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);
		
		driver.findElement(By.id(properties.getProperty("Login"))).click();
		
		driver.findElement(By.id(properties.getProperty("Leave"))).click();
		
		driver.findElement(By.id(properties.getProperty("leavereports"))).click();
		
		driver.findElement(By.id(properties.getProperty("myleaveusage"))).click();
		
		WebElement mle=driver.findElement(By.id(properties.getProperty("Leaveperiod")));
		
		mle.click();
		
		Select myleaventitlementsreports=new Select(mle);
		
		Thread.sleep(2000);
		
		myleaventitlementsreports.selectByVisibleText(fromandto);
		
		Thread.sleep(2000);
		
		driver.findElement(By.name(properties.getProperty("myleaveviewbtn"))).click();
		
		Thread.sleep(2000);
		
		

	}

	@DataProvider(name="myleaveentitlementreports")
public Object[][] MultipleaddEntitlements() throws Exception{
		
		Object[][] data=null;
		
		File file=new File("./src/test/testdata/Leave.xlsx");
		
		FileInputStream mlr=new FileInputStream(file);
		
		Workbook wbmlr=WorkbookFactory.create(mlr);
		
		Sheet stmlr=wbmlr.getSheet("Reportsonmyleaveentitlement");
		
		data=new Object[stmlr.getPhysicalNumberOfRows()-1][stmlr.getRow(0).getPhysicalNumberOfCells()];
		
		for(int rowindex=stmlr.getFirstRowNum()+1;rowindex<=stmlr.getLastRowNum();rowindex++) {
			
			for(int colindex=stmlr.getRow(rowindex).getFirstCellNum();colindex<stmlr.getRow(rowindex).getLastCellNum();colindex++) {
				
				data[rowindex-1][colindex]=stmlr.getRow(rowindex).getCell(colindex).toString();	
						
			
			}
			
			
			
		}
		
		wbmlr.close();
		
		return data;
	}
		
		
}
