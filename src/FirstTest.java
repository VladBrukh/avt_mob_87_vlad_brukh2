import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\user\\Downloads\\JavaAppiumAutomation2" +
                "\\JavaAppiumAutomation2\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void searchResult()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find onboarding skip button",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                15
        );

        waitForElementHasWord(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Java']"),
                "Java",
                "The article with 'Java' title does not exist",
                15
        );

        waitForElementHasWord(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Java (programming language)']"),
                "Java",
                "The article with 'Java (programming language)' title does not exist",
                15
        );

        waitForElementHasWord(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='JavaScript']"),
                "Java",
                "The article with 'JavaScript' title does not exist",
                15
        );

        waitForElementHasWord(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Java version history']"),
                "Java",
                "The article with 'Java version history' title does not exist",
                15
        );

        waitForElementHasWord(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Javanese language']"),
                "Java",
                "The article with 'Javanese language' title does not exist",
                15
        );

        waitForElementHasWord(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Java (software platform)']"),
                "Java",
                "The article with 'Java (software platform)' title does not exist",
                15
        );

    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText (WebElement element, String expected_text, String error_message)
    {
        String search_text = element.getAttribute("text");
        Assert.assertTrue(
                error_message,
                search_text.contains(expected_text)
        );
    }

    private void waitForElementHasWord(By by, String word, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        String article = element.getAttribute("text");
        Assert.assertTrue("The article " + article + " does not contain word " + word,article.contains(word));
    }
}


