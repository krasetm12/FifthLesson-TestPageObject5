package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyListsPageObject extends MainPageObject{

    public static final String
            SAVING_ARTICLE="//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']",
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']",
            DELETE_ARTICLE="//*[@text='Java (programming language)']",
            NAME_OF_SAVED_ARTICLE="pcs-edit-section-title-description";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String folder_name) {

        String folder_name_after = getFolderByName(folder_name);
        this.waitForElementAndClick(
                By.xpath(folder_name_after),
                "Cannot find folder by name " + folder_name,
                10
        );
    }
    public void waitSavingArticleInList()
    {
        this.waitForElementPresent(By.xpath(
                SAVING_ARTICLE),
                "cannot find list Java (programming language) ",
                10);
    }

    public void swipeByArticleToDeleteAndNotPresent()
    {

        this.swipeElementToLeft(By.xpath(
                DELETE_ARTICLE),
                "cannot swipe Java (programming language) ");
        this.waitForElementNotPresent(By.xpath(
                DELETE_ARTICLE),
                "cannot delete save Java (programming language) ",
                5);
    }
    public  void waitAndClickSavedArticle(String second_title)
    {   String second_title_xpath=getFolderXpathByName(second_title);
        this.waitForElementPresent(
                By.xpath(second_title_xpath),
                "Cannot find saved article",
                10
        );
        this.waitForElementAndClick(

                By.xpath(second_title_xpath),
                "Cannot find '" + second_title + "' title",
                30
        );
    }


    private static String getFolderByName(String folder_name) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folder_name);
    }

    private static String getFolderXpathByName(String second_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", second_title);
    }
}
