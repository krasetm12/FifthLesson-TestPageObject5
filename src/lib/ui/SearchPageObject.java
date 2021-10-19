package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject
{
    private static final String
    SEARCH_INIT_ELEMENT= "//*[contains(@text,'SKIP')]",
    SEARCH_INPUT= "//*[contains(@text,'Search Wikipedia')]",
    SEARCH_CANCEL_BUTTON="org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_BY_SUBSTRING_TPL="//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
    SEARCH_RESULT_LIST="org.wikipedia:id/search_results_list";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }

    public void initSearchInput()
    {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search skip after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INPUT), "Cannot find and click search input element", 5);
    }
    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON),"Cannot find element X",5);
    }
    public void waitForCancelButtonToDisAppear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),"Search X is still present",5);
    }
    public void waitForSearchListToDisAppear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_RESULT_LIST),"Search list is still present",5);
    }
    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),"cannot find and search cancel button",5);
    }
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT),search_line, "Cannot find and type into search_line input",5);
    }
    public void waitForSearchResult(String subsrting)
    {
        String search_result_xpath=getResultSearchElement(subsrting);
        this.waitForElementPresent(By.xpath(search_result_xpath),"Cannot find search result with substring"+subsrting);
    }
    public void clickByArticleWithSubstring(String subsrting)
    {
        String search_result_xpath=getResultSearchElement(subsrting);
        this.waitForElementAndClick(By.xpath(search_result_xpath),"Cannot find and click search result with substring"+subsrting,10);
    }

}
