package HarshalKapse;

import Abstract.Abstract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalouge extends Abstract {
    WebDriver driver;
    public ProductCatalouge(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath= ".//div[@class='inventory_item']")
    List<WebElement> products;


    By addToCart =By.xpath(".//button[@class='btn btn_primary btn_small btn_inventory ']");


    public List<WebElement> getProductlist() throws InterruptedException {

        return products;
    }
    public WebElement getProductByName(String productName) throws InterruptedException {
        return getProductlist().stream().filter(s -> s.findElement(By.xpath(".//div/a/div"))
                .getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
    }

    public void addProductToCart(String productName) throws InterruptedException {
    WebElement prod = getProductByName(productName);
        if (prod != null) {
            prod.findElement(addToCart).click();  // Or any other actions on the element
        } else {
            System.out.println("Product element not found!");
        }

    }
}


