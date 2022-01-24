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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.openandclose;


public class TC_22_23_24_Searchandcancelandresetleaves extends openandclose{
	@Test(priority = 12,dataProvider = "Searchcancelreset")
	public void searchandCancelandResetleaves(String username,String password,String fromdate,String dateto) throws Exception {
		//search leaves
		Properties properties = new Properties(); 
		FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");
		
	    properties.load(Lis);
	
			driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);
			
			driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);
			
			driver.findElement(By.id(properties.getProperty("Login"))).click();
			
			driver.findElement(By.id(properties.getProperty("Leave"))).click();
			
			driver.findElement(By.linkText(properties.getProperty("leavelist"))).click();
			
			WebElement search=driver.findElement(By.id(properties.getProperty("fromdate")));
			
			Thread.sleep(2000);
			
			search.click();
			
			Thread.sleep(2000);
			
			search.clear();
			
			Thread.sleep(2000);
			
			search.sendKeys(fromdate);
			
			Thread.sleep(2000);
			
           Actions searc =new Actions(driver);
			

			searc.sendKeys(Keys.DOWN);
			
			searc.sendKeys(Keys.ENTER);
			
			searc.perform();
			
			WebElement todate=driver.findElement(By.id(properties.getProperty("todate")));
			
			Thread.sleep(2000);
			
			todate.click();
			
			Thread.sleep(2000);
			
			todate.clear();
			
			Thread.sleep(2000);
			
			todate.sendKeys(dateto);
			
			Thread.sleep(2000);
			
			 Actions sear =new Actions(driver);
				

				sear.sendKeys(Keys.DOWN);
				
				sear.sendKeys(Keys.ENTER);
				
				sear.perform();
			
			WebElement checkbox=driver.findElement(By.id(properties.getProperty("checkBox")));
			
			Thread.sleep(2000);
			
			checkbox.click();
			
			Thread.sleep(2000);
			
			
			
			//WebElement employeename=driver.findElement(By.id("leaveList_txtEmployee_empName"));
			
			//Thread.sleep(2000);
			
			//employeename.click();
			
			//Thread.sleep(2000);
			
			//employeename.clear();
			
			//Thread.sleep(2000);
			
			//employeename.sendKeys("Paul Collings");
			
		//	Thread.sleep(2000);
			
	//		Actions name=new Actions(driver);
		
	

//			name.sendKeys(Keys.DOWN);
			
	//		name.sendKeys(Keys.ENTER);
			
		//	name.perform();
			
	//		Select subunit=new Select(driver.findElement(By.name("leaveList[cmbSubunit]")));
			
		//	Thread.sleep(2000);
			
			//subunit.selectByIndex(11);
			
			//Thread.sleep(2000);
			
			driver.findElement(By.id(properties.getProperty("leavesearch"))).click();
			
			Thread.sleep(2000);
			
		//cancel leaves
			
			//Select cancelleav=new Select(driver.findElement(By.xpath("//select[@class='select_action quotaSelect']")));
			//Tread.sleep(2000);
			//cancelleav.selectByVisibleText(text);
			//Thread.sleep(2000);
			//driver.findElement(By.name("btnSave")).click();
			//reset leaves
			
			driver.findElement(By.name(properties.getProperty("leavereset"))).click();
			Thread.sleep(2000);
			
			
			
			
			
	}
	
	@DataProvider(name="Searchcancelreset")
	public Object[][] MultipleaddEntitlements() throws Exception{
		
		Object[][] data=null;
		
		File file=new File("./src/test/testdata/Leave.xlsx");
		
		FileInputStream scr=new FileInputStream(file);
		
		Workbook wbscr=WorkbookFactory.create(scr);
		
		Sheet stscr=wbscr.getSheet("searchandcancelandreset");
		
		data=new Object[stscr.getPhysicalNumberOfRows()-1][stscr.getRow(0).getPhysicalNumberOfCells()];
		
		for(int rowindex=stscr.getFirstRowNum()+1;rowindex<=stscr.getLastRowNum();rowindex++) {
			
			for(int colindex=stscr.getRow(rowindex).getFirstCellNum();colindex<stscr.getRow(rowindex).getLastCellNum();colindex++) {
				
				data[rowindex-1][colindex]=stscr.getRow(rowindex).getCell(colindex).toString();	
						
			
			}
			
			
			
		}
		
		wbscr.close();
		
		return data;
	}
		


}
