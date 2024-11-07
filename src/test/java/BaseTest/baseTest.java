package BaseTest;

import HarshalKapse.LoginPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class baseTest {
    public WebDriver driver;
    public LoginPage loginpage;

    public WebDriver initalizeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//Resources//Globalproperties.properties");
        prop.load(fis);
        String browserName =System.getProperty("browser") !=null ?System.getProperty("browser") : prop.getProperty("browser");
        if(browserName.contains("chrome")){
            ChromeOptions option = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(browserName.contains("headless")){
                option.addArguments("headless");
            }
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }else if(browserName.equalsIgnoreCase("firefox")){
            System.setProperty("Webdriver.gecko.driver","C:\\Harshal\\Work\\Mavenproject\\geckodriver.exe");
            driver = new FirefoxDriver();
        }else if (browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
        //Read Json to String
        String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
        //String to HashMap JacksonDataBind
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
    }

    public File getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source =ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//src//Screenshots" +testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return file;
    }

    @BeforeMethod(alwaysRun = true)
    public LoginPage launchApplication() throws IOException {
        driver = initalizeDriver();
        loginpage = new LoginPage(driver);
        loginpage.goTo();
        return new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        driver.close();
    }
}
