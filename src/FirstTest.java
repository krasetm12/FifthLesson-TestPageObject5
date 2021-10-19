import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
import lib.ui.*;
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
import java.util.ArrayList;
import java.util.List;

public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;
    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject=new MainPageObject(driver);
    }


    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject= new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");}
    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject= new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();SearchPageObject.waitForCancelButtonToDisAppear();}
    @Test
    public void testCancelSearchJava() {

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"),
                "Cannot find Search Skip input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search wiki input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "java",
                "cannot find search input",
                5);
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "cannot find search field",
                5);


        MainPageObject.waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find  page_list_item_title topic searching by java ",
                15);

    }

    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject= new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject=new ArticlePageObject(driver);
       String article_title=ArticlePageObject.getArticleTitle();

        Assert.assertEquals("we see unexpected title",

                "Object-oriented programming language",
                article_title
        );
    }
    @Test
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject= new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject ArticlePageObject=new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

    }
    @Test
    public void testPresentSearchWikipedia() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Cannot find Search Skip input",
                5
        );
        MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find  Search Wiki  by java ",
                15);
        MainPageObject.assertElementHasText(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "text in element Search Wiki is not find");
        boolean title_element = MainPageObject.assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "text in element Search Wiki is not find"
        );

        Assert.assertEquals("we see unexpected text",

                true,
                title_element
        );


    }



    @Test
    public void testFindElementsWithTextByJava() {

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"),
                "Cannot find Search Skip input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search wiki input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search wiki input",
                5);
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "java",
                "cannot find search input",
                5);
        driver.hideKeyboard();
        MainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Java']"),
                "Cannot find  element 1 topic searching by java ",
                15);

        MainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='JavaScript']"),
                "Cannot find  element 2 topic searching by java ",
                15);

        MainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Java (programming language)']"),
                "Cannot find  element 3 topic searching by java ",
                15);

        ArrayList<String> element_with_text_javas = new ArrayList<String>();
        element_with_text_javas.add("Java");
        element_with_text_javas.add("JavaScript");
        element_with_text_javas.add("Java (programming language)");

        for (String element_with_text_java : element_with_text_javas) {
            if (element_with_text_javas.contains("Java")) {
                Assert.assertEquals("in search Has element with text not java",
                        true,
                        element_with_text_java.contains("Java")

                );
            }
        }
    }


    @Test
    public void testSwipeUpAndFindArticle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Cannot find Search Skip input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search wiki input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "appium",
                "cannot find search input",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "cannot find search Appium input",
                10

        );
        MainPageObject.waitForElementPresent(
               By.xpath("//*[@text='Appium']"),
              "cannot find article title",
               15
       );
        MainPageObject.SwipeUpFindElement (By.xpath("//*[@text='View article in browser']"),
                        "can not find the end of the article",
                        20
                );


    }
    @Test
    public void testSaveFirstArticleForMyList()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Cannot find Search Skip input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search wiki input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "java",
                "cannot find search input",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='Object-oriented programming language']"),
                "cannot find search java input",
                10

        );
        MainPageObject.waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "cannot find article title",
                15
        );
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.TextView[@text='Save']"),
                "cannot find button to open article options",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='ADD TO LIST']"),
                "cannot find button Add to list",
                5);

        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/textinput_placeholder"),
                "My first list",
                "cannot find element org.wikipedia:id/textinput_placeholder",
                5);
        MainPageObject.waitForElementAndClick(By.id("android:id/button1"),
                "cannot find element android:id/button1",
                5) ;
        MainPageObject.waitForElementAndClick(By.xpath(
                "//android.widget.ImageButton[@content-desc='Navigate up']"),
                "cannot find Navigate up",
                5);
        MainPageObject.waitForElementAndClick(By.xpath(
                "//android.widget.ImageView[@content-desc='Clear query']"),
                "cannot find clear query",
                5);
        MainPageObject.waitForElementAndClick(By.id(
                "org.wikipedia:id/recent_searches_delete_button"),
                "cannot find clear query",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='YES']"),
                "cannot find element YES",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@class='android.widget.ImageButton']"),
                "cannot find android.widget.ImageButton",
                5);

        MainPageObject.waitForElementAndClick(By.xpath(
                "//*[@resource-id='org.wikipedia:id/navigation_bar_item_small_label_view'][@text='Saved']"),
                "cannot find button to open article  saveD",
                10);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='My first list']"),
     "cannot find My first list",
     5);

        MainPageObject.waitForElementPresent(By.xpath(
                "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "cannot find list Java (programming language) ",
                10);
        MainPageObject.swipeElementToLeft(By.xpath(
                "//*[@text='Java (programming language)']"),
                "cannot swipe Java (programming language) ");
        MainPageObject.waitForElementNotPresent(By.xpath(
                "//*[@text='Java (programming language)']"),
                "cannot delete save Java (programming language) ",
                5);

    }
    @Test
    public void testAmountOfNotEmptySearch()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Cannot find Search Skip input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search wiki input",
                5
        );
        String search_line="Linkin Park diskography";
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "cannot find search input",
                5);
        String search_result_locator=
                "//android.widget.TextView[@text='Linkin Park discography']";
        MainPageObject.waitForElementPresent(By.xpath(search_result_locator),
            "cannot find anything by request"+search_line,
            5);
    int get_amount_of_search_element=MainPageObject.getAmountofElements(By.xpath(search_result_locator));
    Assert.assertTrue("we  find few elements with search line",
            get_amount_of_search_element>0);
    }
    @Test
    public void testAmountOfEmptySearchResult()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Cannot find Search Skip input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search wiki input",
                5
        );
        String search_line="kjvvcxfj";
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "cannot find search input",
                5);
        String search_result_locator=
                "//android.widget.TextView[@text='Linkin Park discography']";
        String empty_result_label="//*[@text='No results']";
        MainPageObject.waitForElementPresent(By.xpath(empty_result_label),
                "not find label no results"+empty_result_label,
                5);
        MainPageObject.assertElementNotPresent(By.xpath(search_result_locator),
                "We've found some results by request"+search_line);

    }
    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Cannot find Search Skip input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search wiki input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "java",
                "cannot find search input",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='Object-oriented programming language']"),
                "cannot find search java input",
                10

        );
        String title_before_rotaion= MainPageObject.waitForElementAndGetAttribute(By.id("pcs-edit-section-title-description"),
                "text",
                "cannot find title of article",
                15);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation=MainPageObject.waitForElementAndGetAttribute(By.id("pcs-edit-section-title-description"),
                "text",
                "cannot find title of article",
                15);
        Assert.assertEquals("Article title have changed after rotation",
                title_before_rotaion,
                title_after_rotation);
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation=MainPageObject.waitForElementAndGetAttribute(By.id("pcs-edit-section-title-description"),
                "text",
                "cannot find title of article",
                15);

        Assert.assertEquals("Article title have changed after rotation",
                title_before_rotaion,
                title_after_second_rotation);
    }
    @Test
    public void testSaveTwoArticlesToMyListe()
    {
        SearchPageObject SearchPageObject= new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String second_title = "High-level programming language";
        String folder_name="My first list";
        ArticlePageObject ArticlePageObject=new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.saveAndAddToList();
        ArticlePageObject.createToList(folder_name);
        NavigationUI NavigationUI=new NavigationUI(driver);
        NavigationUI.comeBackToPage();
        SearchPageObject.clickByArticleWithSubstring("High-level programming language");
        ArticlePageObject.saveAndAddToList();

        ArticlePageObject.addTwoAndMorePageToSaveList();

        MyListsPageObject MyListsPageObject=new MyListsPageObject(driver);
        MyListsPageObject.waitSavingArticleInList();
        MyListsPageObject.swipeByArticleToDeleteAndNotPresent();
        MyListsPageObject.waitAndClickSavedArticle( second_title);


        String name_of_title=ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Article title do not match",
                second_title,
                name_of_title
        );

    }
    @Test
    public void testOpenArticleAndCheckTitlex() {

        SearchPageObject SearchPageObject= new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject=new ArticlePageObject(driver);
        ArticlePageObject.articleHasTitle();
    }
    @Test
    public void testCancellationSearchJavak() {

        SearchPageObject SearchPageObject= new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");

        SearchPageObject.waitForSearchResult("Indonesian island");

        SearchPageObject.waitForSearchResult("High-level programming language");

        SearchPageObject.waitForSearchResult("Object-oriented programming language");

        SearchPageObject.clickCancelSearch();

        SearchPageObject.waitForSearchListToDisAppear();

    }



}
