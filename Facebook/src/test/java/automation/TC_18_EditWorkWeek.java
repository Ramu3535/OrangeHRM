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

public class TC_18_EditWorkWeek extends openandclose {
	
	@Test(priority = 10, dataProvider = "editWorkWeek")
	public void editWorkweek(String username, String password, String week) throws Exception {

		// Edit Work Week
		Properties properties = new Properties(); 
		FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");
		
	    properties.load(Lis);

		driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);

		driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);

		driver.findElement(By.id(properties.getProperty("Login"))).click();

		driver.findElement(By.id(properties.getProperty("Leave"))).click();

		driver.findElement(By.linkText(properties.getProperty("editleave"))).click();

		driver.findElement(By.linkText(properties.getProperty("editworkweek"))).click();

		driver.findElement(By.id(properties.getProperty("editsave"))).click();
		
		WebElement ww=driver.findElement(By.name(properties.getProperty("day")));
		ww.click();

		Select workweek = new Select(ww);

		Thread.sleep(2000);

		workweek.selectByVisibleText(week);

		Thread.sleep(2000);

		driver.findElement(By.id(properties.getProperty("savebn"))).click();

		Thread.sleep(2000);

	}

	@DataProvider(name = "editWorkWeek")

	public Object[][] MultipleaddEntitlements() throws Exception {

		Object[][] data = null;

		File file = new File("./src/test/testdata/Leave.xlsx");

		FileInputStream eww = new FileInputStream(file);

		Workbook wbeww = WorkbookFactory.create(eww);

		Sheet steww = wbeww.getSheet("editworkweek");

		data = new Object[steww.getPhysicalNumberOfRows()-1][steww.getRow(0).getPhysicalNumberOfCells()];

		for (int rowindex = steww.getFirstRowNum()+1; rowindex<=steww.getLastRowNum(); rowindex++) {

			for (int colindex = steww.getRow(rowindex).getFirstCellNum(); colindex<steww.getRow(rowindex).getLastCellNum(); colindex++) {

				data[rowindex-1][colindex] = steww.getRow(rowindex).getCell(colindex).toString();

			}

		}

		wbeww.close();

		return data;
	}

}
