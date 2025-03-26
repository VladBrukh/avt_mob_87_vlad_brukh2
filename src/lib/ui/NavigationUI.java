package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{

    private static final String
            BACK_ARROW_LINK = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
            MY_LISTS_LINK = "xpath://android.widget.TextView[@text='Saved']";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void navigateBackByBackArrow()
    {
        this.waitForElementAndClick(
                BACK_ARROW_LINK,
                "Cannot find back arrow link",
                5
        );
    }

    public void clickMyLists()
    {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to My list",
                10
        );

    }

}
