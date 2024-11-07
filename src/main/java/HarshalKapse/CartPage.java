package HarshalKapse;

import Abstract.Abstract;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends Abstract {
    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div/a/div")
    List<WebElement> cartProd;

    @FindBy(xpath ="//a[@class='shopping_cart_link']" )
    WebElement checkout;


        public boolean verifyProductDisplay(String productName) {
            return cartProd.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
        }

        public CheckOutPage goToCheckout(){
        checkout.click();
        return new CheckOutPage(driver);
        }
}
