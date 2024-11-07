package HarshalKapse;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class Standalonetest {
    public static void main(String[] args) {
        String userName = "standard_user";
        String password = "secret_sauce";
        String productName = "Sauce Labs Backpack";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        //Landing Page
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        //ProductCatalouge
        List<WebElement> products =driver.findElements(By.xpath("//div/a/div"));
        products.stream().filter(s -> s.findElement(By.xpath("//div/a/div"))
                .getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        assert products != null;
        driver.findElement(By.xpath("//button[contains(@class,'btn btn_primary btn_small btn_inventory ')]")).click();

        //CartPage
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        List<WebElement> cartProd = driver.findElements(By.xpath("//div/a/div"));
        boolean match = cartProd.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match,"product" + productName + "is not found in Cart");

        //checkoutPage
        driver.findElement(By.xpath("//button[@class='btn btn_action btn_medium checkout_button ']")).click();
        driver.findElement(By.xpath("(//input[contains(@class,'input_error form_input')])[1]")).sendKeys("Harshal");
        driver.findElement(By.xpath("(//input[contains(@class,'input_error form_input')])[2]")).sendKeys("Kapse");
        driver.findElement(By.xpath("(//input[contains(@class,'input_error form_input')])[3]")).sendKeys("411060");
        driver.findElement(By.xpath("//input[@class='submit-button btn btn_primary cart_button btn_action']")).click();

        driver.findElement(By.xpath("//button[@class='btn btn_action btn_medium cart_button']")).click();

        //confirmationPage
        String message = driver.findElement(By.xpath("//div/h2")).getText();
        Assert.assertTrue(message.equalsIgnoreCase("Thank you for your order!"));
        driver.close();

    }
}
