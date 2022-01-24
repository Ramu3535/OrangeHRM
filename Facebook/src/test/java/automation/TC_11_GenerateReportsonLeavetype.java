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


public class TC_11_GenerateReportsonLeavetype extends openandclose{

	@Test(priority = 6,dataProvider = "leavetypereports")
	public void generatereportsonLeaveType(String username,String password,String generate,String tleave,String fromda,String jobTitle,String location,String Subunit) throws Exception {

		Properties properties = new Properties(); 
		FileInputStream Lis= new FileInputStream("C:\\Automationworkspace\\Facebook\\src\\test\\java\\datafile\\testdata.properties");

		properties.load(Lis);


		driver.findElement(By.id(properties.getProperty("Username"))).sendKeys(username);

		driver.findElement(By.id(properties.getProperty("Password"))).sendKeys(password);

		driver.findElement(By.id(properties.getProperty("Login"))).click();

		driver.findElement(By.id(properties.getProperty("Leave"))).click();

		driver.findElement(By.id(properties.getProperty("leavereports"))).click();

		driver.findElement(By.id(properties.getProperty("usagereports"))).click();

		WebElement generatelt=driver.findElement(By.id(properties.getProperty("generstefor")));
		generatelt.click();

		Select reportsonleave=new Select(generatelt);

		reportsonleave.selectByVisibleText(generate);

		WebElement tyleave=driver.findElement(By.id(properties.getProperty("balanceleavetype")));
		tyleave.click();

		Select reportsonleavetype=new Select(tyleave);

		reportsonleavetype.selectByVisibleText(tleave);

		WebElement date=driver.findElement(By.id(properties.getProperty("Leaveperiod")));
		date.click();

		Select from=new Select(date);

		Thread.sleep(2000);

		from.selectByVisibleText(fromda);

		Thread.sleep(2000);

		WebElement jt=driver.findElement(By.id(properties.getProperty("reportsjobtitle")));
		jt.click();

		Select jobtitle=new Select(jt);

		Thread.sleep(2000);

		jobtitle.selectByVisibleText(jobTitle);

		Thread.sleep(2000);

		WebElement ln=driver.findElement(By.id(properties.getProperty("reportslocation")));
		ln.click();

		Select Location=new Select(ln);

		Thread.sleep(2000);

		Location.selectByVisibleText(location);

		Thread.sleep(2000);

		WebElement su=driver.findElement(By.id(properties.getProperty("reportssubunit")));
		su.click();

		Select subunit=new Select(su);

		Thread.sleep(2000);

		subunit.selectByVisibleText(Subunit);

		Thread.sleep(2000);

		driver.findElement(By.name(properties.getProperty("button"))).click();

		Thread.sleep(2000);


	}

	@DataProvider(name="leavetypereports")
	public Object[][] MultipleaddEntitlements() throws Exception{

		Object[][] data=null;

		File file=new File("./src/test/testdata/Leave.xlsx");

		FileInputStream lr=new FileInputStream(file);

		Workbook wblr=WorkbookFactory.create(lr);

		Sheet stlr=wblr.getSheet("generateReportsonleavetype");

		data=new Object[stlr.getPhysicalNumberOfRows()-1][stlr.getRow(0).getPhysicalNumberOfCells()];

		for(int rowindex=stlr.getFirstRowNum()+1;rowindex<=stlr.getLastRowNum();rowindex++) {

			for(int colindex=stlr.getRow(rowindex).getFirstCellNum();colindex<stlr.getRow(rowindex).getLastCellNum();colindex++) {

				data[rowindex-1][colindex]=stlr.getRow(rowindex).getCell(colindex).toString();						

			}

		}

		wblr.close();

		return data;
	}





}
