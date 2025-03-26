package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;


public class ArticlePageObject extends MainPageObject{

    private static final String
            TITLE = "xpath://*[@class='android.widget.TextView'][@instance='0']",
            FOOTER_ELEMENT = "xpath://*[@content-desc='View article in browser']",
            SAVE_ARTICLE_BUTTON = "xpath://android.widget.TextView[@text='Save']",
            ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to list']",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find the article title on the page!", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of the article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                SAVE_ARTICLE_BUTTON,
                "Cannot find Save button",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into article folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void addFollowUpArticlesToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                SAVE_ARTICLE_BUTTON,
                "Cannot find Save button",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
    }

    public void assertArticleTitlePresent() {

        this.assertElementPresent(
                TITLE,
                "The title element is not found in the article"
        );
    }
}
