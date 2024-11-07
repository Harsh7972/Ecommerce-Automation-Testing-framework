package HarshalKapse;

import Abstract.Abstract;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Abstract {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
        @FindBy(xpath = "//input[@id='user-name']")
        WebElement userName;
        @FindBy(xpath = "//input[@id='password']")
        WebElement password;
        @FindBy(xpath = "//input[@id='login-button']")
        WebElement submit;


        public ProductCatalouge Setlogin (String user, String pass) {
            userName.sendKeys(user);
            password.sendKeys(pass);
            submit.click();
            return new ProductCatalouge(driver);
        }
        public void goTo(){
            driver.get("https://www.saucedemo.com/");
            }

}
