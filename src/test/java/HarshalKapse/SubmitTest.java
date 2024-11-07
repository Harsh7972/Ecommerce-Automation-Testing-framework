package HarshalKapse;
import BaseTest.baseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class SubmitTest extends baseTest {


    @Test(dataProvider = "getData")
    public void SubmitOrder(HashMap<String,String> input) throws InterruptedException {

        ProductCatalouge productCatalouge = loginpage.Setlogin(input.get("userName"),input.get("password"));

        List<WebElement> products = productCatalouge.getProductlist();

        productCatalouge.addProductToCart(input.get("productName"));

        CartPage cartPage = productCatalouge.goToCartPage();


        Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckOutPage checkOutPage =cartPage.goToCheckout();
        ConfirmationPage confirmationPage = checkOutPage.SubmitOrder(input.get("name"), input.get("surname"),input.get("pincode"));
        confirmationPage.submitOrder();
        String message = confirmationPage.getConfirmationmessage();
        Assert.assertTrue(message.equalsIgnoreCase("Thank you for your order!"));
    }

    @DataProvider
    public Object [][] getData() throws IOException {
    List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//DataPackage//data.json");
    return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)},{data.get(4)},{data.get(5)}};
    }

}
