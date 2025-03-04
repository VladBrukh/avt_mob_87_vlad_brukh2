import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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
    public void testSwipeArticle()
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
                "Appium",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']" +
                        "[@text='Appium']"),
                "Cannot find 'Appium' article in search",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@class='android.view.View'][@content-desc='Appium']"),
                "Cannot find article title",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[@content-desc='View article in browser']"),
                "Cannot find the end of the article",
                20
        );

    }

    @Test
    public void saveFirstArticleToMyList()
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
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Object-oriented programming language']"),
                "Cannot find 'Java (programming language)' article in search",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Save']"),
                "Cannot find Save button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to list']"),
                "Cannot find option to add article to reading list",
                5
        );

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into article folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find back arrow link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find back arrow link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Saved']"),
                "Cannot find navigation button to My list",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.Button[@text='Not now']"),
                "Cannot find Not now button on Sync reading lists",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch()
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

        String search_line = "Linkin Park Discography";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@class='android.view.ViewGroup']/*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by request " + search_line,
                15
        );

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );

    }

    @Test
    public void testAmountOfEmptySearch()
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

        String search_line = "sdsdsdada";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@class='android.view.ViewGroup']/*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                15
        );

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by the request " + search_line
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
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

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Object-oriented programming language']"),
                "Cannot find 'Java (programming language)' topic searching by " + search_line,
                15
        );

        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "name",
                "Cannot find title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "name",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title has been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "name",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title has been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );

    }

    @Test
    public void testCheckSearchArticleInBackground()
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

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Object-oriented programming language']"),
                "Cannot find 'Java (programming language)' topic searching by " + search_line,
                5
        );

        driver.runAppInBackground(2);

        waitForElementPresent(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Object-oriented programming language']"),
                "Cannot find article after returning from background",
                5
        );
    }

    @Test
    public void changeScreenOrientation()
    {

        checkScreenOrientation();

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

        String search_line = "Java";
        String article_description = "Object-oriented programming language";
        String title = "Java (programming language)";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='" + article_description + "']"),
                "Cannot find article with " + article_description + " text searching by " + search_line,
                15
        );

        waitForElementPresent(
                By.xpath("//android.view.View[@content-desc='" + article_description + "']"),
                "The article with " + article_description + " is not opened",
                15
        );

        String title_element = "//android.view.View[@content-desc='" + title + "']";

        assertElementPresent(
                By.xpath(title_element),
                "The title " + title + " is not found in the article with description " + article_description
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

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();

    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int  max_swipes)
    {

        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){

            if (already_swiped > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }

    private int getAmountOfElements (By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message)
    {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute (By by, String attribute, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        return element.getAttribute(attribute);
    }

    private void assertElementPresent(By searchable_element, String error_message)
    {
        int amount_of_elements = getAmountOfElements(searchable_element);
        if (amount_of_elements == 0) {
            String default_message = "An element '" + searchable_element.toString() + "' is not present\n";
            throw new AssertionError(default_message + " " + error_message);

        }

    }

    public void checkScreenOrientation()
    {
        ScreenOrientation orientation = driver.getOrientation();
        if(orientation == ScreenOrientation.LANDSCAPE) {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }

}


