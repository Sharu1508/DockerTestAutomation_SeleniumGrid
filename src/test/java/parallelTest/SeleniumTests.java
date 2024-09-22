package parallelTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.AssertJUnit.assertEquals;

public class SeleniumTests {

    WebDriver driver;
    ChromeOptions options;
    EdgeOptions options1;

    @Parameters("browserType")
    @BeforeTest

    public void setup(@Optional("chrome") String browser) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //check if parameter is passed from TestNG is firefox
        if (browser.equalsIgnoreCase("firefox")) {
            capabilities.setBrowserName("firefox");

        }
        //check if parameter is passed from TestNG is chrome
        if (browser.equalsIgnoreCase("chrome")) {
            capabilities.setBrowserName("chrome");
        }

        if (browser.equalsIgnoreCase("Edge")) {
            capabilities.setBrowserName("MicrosoftEdge");
         }


            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            Thread.sleep(2000);
            driver.get("https://anupdamoda.github.io/AceOnlineShoePortal/ShoeTypes.html");
            Thread.sleep(2000);
    }

    @Test
    public void SeleniumCrossBrowserTests() {

        driver.get("https://anupdamoda.github.io/AceOnlineShoePortal/index.html");
        driver.findElement(By.xpath("//*[@id=\"menuToggle\"]/input")).click();

        driver.findElement(By.xpath("//*[@id=\"menu\"]/a[2]/li")).click();
        driver.findElement(By.xpath("//*[@id=\"usr\"]")).sendKeys("Sha");
        driver.findElement(By.xpath("//*[@id=\"pwd\"]")).sendKeys("Sha");

        driver.findElement(By.xpath("//*[@id=\"second_form\"]/input")).click();
        String shoeCategory = driver.findElement(By.xpath("//*[@id=\"ShoeType\"]")).getText();
        assertEquals(shoeCategory, "Formal Shoes");

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}