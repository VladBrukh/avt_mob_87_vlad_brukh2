package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject
{
    private static final String
            SEARCH_SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container",
            SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@class='android.view.ViewGroup']" +
                    "//*[@text='{SUBSTRING}'][@instance!='0']",
            SEARCH_RESULT_BY_SUBSTRING_AND_DESCRIPTION_TPL = "xpath://*[@class='android.view.ViewGroup'][@instance!='0']" +
                    "//*[@text='{TITLE}']/following-sibling::*[@text='{DESCRIPTION}']",
            SEARCH_RESULT_ELEMENT = "xpath://*[@class='android.view.ViewGroup']/*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultByTitleAndDescription(String title, String description) {

        return SEARCH_RESULT_BY_SUBSTRING_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATE METHODS */

    public void skipOnboarding()
    {
        this.waitForElementPresent(SEARCH_SKIP_BUTTON, "Cannot find skip onboarding button");
        this.waitForElementAndClick(SEARCH_SKIP_BUTTON, "Cannot find and click skip onboarding button", 5);
    }

    public void initSearchInput()
    {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search line", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by request",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);

    }

    public void waitForElementByTitleAndDescription(String title, String description) {

        String search_result_xpath = getResultByTitleAndDescription(title, description);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with title " + title + " or/and with description " + description, 10);

    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);

    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed to see some results");
    }
}
