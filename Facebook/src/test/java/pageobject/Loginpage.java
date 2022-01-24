package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
	
	
    @FindBy(id="txtUsername")
	private WebElement orangeusername=null;
    
    
    @FindBy(id="txtPassword")
    private WebElement orangepassword=null;
    
    @FindBy(id="btnLogin")
    private WebElement clickonlogin=null;
    
    
    public Loginpage(WebDriver driver) {
    	
    	PageFactory.initElements(driver, this);
    	
    }
	

	public void pagelogin(){
    	
    	orangeusername.sendKeys("Admin");
    	
    	orangepassword.sendKeys("admin123");
    	
    	clickonlogin.click();
    }
	
	
}
