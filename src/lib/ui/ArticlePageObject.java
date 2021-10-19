package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE = "pcs-edit-section-title-description",
            FOOTER_ELEMENT = "//*[@text='View article in browser']",
            SAVE_LIST = "//android.widget.TextView[@text='Save']",
            ADD_TO_LIST = "//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='ADD TO LIST']",
            INPUT_PLACEHOLDER = "org.wikipedia:id/textinput_placeholder",
            BUTTON_OK = "android:id/button1",

            MY_FIRST_LIST="//*[@resource-id='org.wikipedia:id/item_title'][@text='My first list']",
            VIEW_LIST="//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='VIEW LIST']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.SwipeUpFindElement(By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article", 20);
    }

    public void saveAndAddToList() {
        this.waitForElementAndClick(By.xpath(SAVE_LIST),
                "cannot find button to open article options",
                5);
        this.waitForElementPresent(
                By.xpath(ADD_TO_LIST),
                "cannot find element add to list",
                15);
        this.waitForElementAndClick(By.xpath(ADD_TO_LIST),
                "cannot find button Add to list",
                5);
    }

    public void createToList(String folder_name) {
        this.waitForElementAndSendKeys(By.id(INPUT_PLACEHOLDER),
                folder_name,
                "cannot find element org.wikipedia:id/textinput_placeholder",
                5);
        this.waitForElementAndClick(By.id(BUTTON_OK),
                "cannot find element android:id/button1",
                5);
    }


    public void addTwoAndMorePageToSaveList()
    {

        this.waitForElementAndClick(By.xpath(MY_FIRST_LIST),
            "cannot find button Add to list",
            5);
        this.waitForElementPresent(
                By.xpath(VIEW_LIST),
                "cannot find element add to list",
                15);
        this.waitForElementAndClick(By.xpath(VIEW_LIST),
                "cannot find button Add to list",
                5);
    }
    public void articleHasTitle () {
        this.assertElementPresent(
                By.id(TITLE),
                "cannot find article title"
        );
    }

}