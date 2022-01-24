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

public class TC_12_GeneratereportsonEmployeetype extends openandclose{

	@Test(priority = 7,dataProvider = "reportsonemployeetype")

	public void generateReportsOnEmployeetype(String username,String password,String generateemp,String name,String fromemp) throws Exception {
		Properties properties = new Properties(); 
		FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");

		properties.load(Lis);

		driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);

		driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);

		driver.findElement(By.id(properties.getProperty("Login"))).click();

		driver.findElement(By.id(properties.getProperty("Leave"))).click();

		driver.findElement(By.id(properties.getProperty("leavereports"))).click();

		driver.findElement(By.id(properties.getProperty("usagereports"))).click();

		WebElement empreports=driver.findElement(By.id(properties.getProperty("empusagereports")));
		empreports.click();

		Select reportsonemployeetype=new Select(empreports);

		Thread.sleep(2000);

		reportsonemployeetype.selectByVisibleText(generateemp);

		WebElement employeetype=driver.findElement(By.name(properties.getProperty("empreportname")));

		Thread.sleep(2000);

		employeetype.click();

		Thread.sleep(2000);

		employeetype.clear();

		Thread.sleep(2000);

		employeetype.sendKeys(name);

		Actions actionemployee = new Actions(driver);

		actionemployee.sendKeys(Keys.DOWN);

		actionemployee.sendKeys(Keys.ENTER);

		actionemployee.perform();

		Thread.sleep(2000);

		WebElement empfrom=driver.findElement(By.id(properties.getProperty("Leaveperiod")));
		empfrom.click();

		Select employeefrom=new Select(empfrom);

		employeefrom.selectByVisibleText(fromemp);

		Thread.sleep(2000);

		driver.findElement(By.id(properties.getProperty("empreportbutton"))).click();

		Thread.sleep(2000);

	}

	@DataProvider(name="reportsonemployeetype")
	public Object[][] MultipleaddEntitlements() throws Exception{

		Object[][] data=null;

		File file=new File("./src/test/testdata/Leave.xlsx");

		FileInputStream er=new FileInputStream(file);

		Workbook wber=WorkbookFactory.create(er);

		Sheet ster=wber.getSheet("generateReportsonemployeetype");

		data=new Object[ster.getPhysicalNumberOfRows()-1][ster.getRow(0).getPhysicalNumberOfCells()];

		for(int rowindex=ster.getFirstRowNum()+1;rowindex<=ster.getLastRowNum();rowindex++) {

			for(int colindex=ster.getRow(rowindex).getFirstCellNum();colindex<ster.getRow(rowindex).getLastCellNum();colindex++) {

				data[rowindex-1][colindex]=ster.getRow(rowindex).getCell(colindex).toString();	


			}



		}

		wber.close();

		return data;
	}








}
