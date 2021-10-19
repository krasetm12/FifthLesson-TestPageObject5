package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{

    public static final String
            NAVIGATE_UP = "//android.widget.ImageButton[@content-desc='Navigate up']";
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }
    public void comeBackToPage()
    {
        this.waitForElementAndClick(By.xpath(NAVIGATE_UP),
                "cannot find Navigate up",
                5);
    }
}
