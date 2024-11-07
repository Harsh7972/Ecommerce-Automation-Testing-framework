package HarshalKapse;

import Abstract.Abstract;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends Abstract {
    public WebDriver driver;
    public CheckOutPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//button[@class='btn btn_action btn_medium checkout_button ']")
    WebElement select;
    @FindBy(xpath = "(//input[contains(@class,'input_error form_input')])[1]")
    WebElement name;
    @FindBy(xpath ="(//input[contains(@class,'input_error form_input')])[2]")
    WebElement surname;
    @FindBy(xpath = "(//input[contains(@class,'input_error form_input')])[3]")
    WebElement pincode;
    @FindBy(xpath = "//input[@class='submit-button btn btn_primary cart_button btn_action']")
    WebElement send;

    public ConfirmationPage SubmitOrder(String x, String y, String z){
        select.click();
        name.sendKeys(x);
        surname.sendKeys(y);
        pincode.sendKeys(z);
        send.click();
        return new ConfirmationPage(driver);
    }
}
