package Abstract;

import HarshalKapse.CartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Abstract {
    WebDriver driver;

    public Abstract(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    WebElement cartheader;

    public CartPage goToCartPage() {
        cartheader.click();
        return new CartPage(driver);
    }
}
