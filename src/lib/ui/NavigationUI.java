package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{

    private static final String
            BACK_ARROW_LINK = "//android.widget.ImageButton[@content-desc='Navigate up']",
            MY_LISTS_LINK = "//android.widget.TextView[@text='Saved']";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void navigateBackByBackArrow()
    {
        this.waitForElementAndClick(
                By.xpath(BACK_ARROW_LINK),
                "Cannot find back arrow link",
                5
        );
    }

    public void clickMyLists()
    {
        this.waitForElementAndClick(
                By.xpath(MY_LISTS_LINK),
                "Cannot find navigation button to My list",
                10
        );

    }

}
