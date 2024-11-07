package HarshalKapse;

import Abstract.Abstract;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends Abstract {

   public WebDriver driver;
   public ConfirmationPage(WebDriver driver){
       super(driver);
       this.driver = driver;
       PageFactory.initElements(driver,this);
   }

    @FindBy(xpath = "//button[@class='btn btn_action btn_medium cart_button']")
    WebElement submit ;
   @FindBy(xpath = "//div/h2")
   WebElement confirmationmessage;

    public void submitOrder(){
        submit.click();
    }

    public String getConfirmationmessage(){
        return confirmationmessage.getText();
    }
}
