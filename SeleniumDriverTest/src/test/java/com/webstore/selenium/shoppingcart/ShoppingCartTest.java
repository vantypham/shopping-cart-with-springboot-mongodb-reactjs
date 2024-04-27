package com.webstore.selenium.shoppingcart;

import com.webstore.selenium.Utils;
import com.webstore.selenium.product.ProductDetailPage;
import com.webstore.selenium.product.ProductManagementPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ShoppingCartTest {
    WebDriver driver;
    ShoppingPage page1;
    MakeReviewPage page2;
    GotoCardPage page3;
    ProductManagementPage productManagementPage;
    ProductDetailPage detailPage;

    @Before
    public void createWebDriver() {
        // set path to chromedriver.exe
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", Utils.getDriverURL());
        options.setBinary(Utils.getBinaryURL());
        options.addArguments("--remote-allow-origins=*");
        // create chrome instance
        driver = new ChromeDriver(options);
        page1 = PageFactory.initElements(driver, ShoppingPage.class);
        page1.open("http://localhost:3000/shoppings");
    }

    @Test
    public void testA_BrowseAProductOnShoppingPage() {
        //First goto Product Page then create a product
        productManagementPage = page1.gotoProductManagementPage();//to create product first
        productManagementPage.enterData("P1234567xxx", "Green Banana", "3.5", "Fresh Green Banana 2pounds", "100");
        productManagementPage.clickButtonAdd();
        productManagementPage.waitAndGetResultById("messageID");
        //Back to Shopping Page to verify the given product has been created
        page1 = productManagementPage.gotoShoppingPage();
        String inStock = page1.waitAndGetResultById("P1234567xxx_numberInStockID");
        assertThat(inStock, is("100"));
    }

    @Test
    public void testB_AddToCart() {
        //First goto Product Page then create a product
        productManagementPage = page1.gotoProductManagementPage();//to create product first
        //productManagementPage.waitAndGetResultByName("productNumber");
        productManagementPage.enterData("P1234567xx1", "Green Banana", "3.5", "Fresh Green Banana 2pounds", "100");
        productManagementPage.clickButtonAdd();
        productManagementPage.waitAndGetResultById("messageID");
        //Back to Shopping Page to verify the given product has been created
        page1 = productManagementPage.gotoShoppingPage();
        String inStock = page1.waitAndGetResultById("P1234567xx1_numberInStockID");
        assertThat(inStock, is("100"));
        //click AddToCart button
        page1.clickById("P1234567xx1_addID");
        String quantity = page1.waitAndGetResultById("itemQuantityShoppingID");
        assertThat(quantity, is("1"));
        //click AddToCart button, more time
        page1.clickById("P1234567xx1_addID");
        quantity = page1.waitAndGetResultById("itemQuantityShoppingID");
        assertThat(quantity, is("2"));
    }


    @Test
    public void testC_MakeAReview() {
        //First goto Product Page then create a NEW PRODUCT
        productManagementPage = page1.gotoProductManagementPage();//to create product first
        //productManagementPage.waitAndGetResultByName("productNumber");
        productManagementPage.enterData("P1234567xx2", "Green Banana", "3.5", "Fresh Green Banana 2pounds", "100");
        productManagementPage.clickButtonAdd();
        productManagementPage.waitAndGetResultById("messageID");
        //Back to Shopping Page to verify the given product has been created
        page1 = productManagementPage.gotoShoppingPage();
        String inStock = page1.waitAndGetResultById("P1234567xx2_numberInStockID");
        assertThat(inStock, is("100"));
        //click MakeReview button
        page2 = page1.clickMakeReviewThenGotoMakeReviewPage("P1234567xx2_makeReviewID");
        page2.waitAndGetResultByTagName("h3");
        //enter input data
        page2.enterData("user101", "This product is good", "4 - GOOD");
        page1 = page2.clickBtnAddReview();
        page1.waitResultById("menuProductID");
        //go to Product Page to verify the result:
        productManagementPage = page1.gotoProductManagementPage();

//        productManagementPage.enterSearchData("P1234567xx2");
//        productManagementPage.clickSearchButton();clickSearchButton
//        productManagementPage.waitAndGetResultById("P1234567xx2_detailID");
        //CLICK Detail button
         detailPage = productManagementPage.waitAndClickDetailButtonById("P1234567xx2_detailID");

        String actual = detailPage.findFirstReviewItemTD();
        assertThat(actual, containsString("This product is good"));
        ////*[@id="root"]/div/div/form/table[2]/tbody/tr/td
        ////*[@id="tableTwoID"]/tbody/tr/td
    }
    @Test
    public void testD_CheckoutFlow() {
        //First goto Product Page then create a product
        productManagementPage = page1.gotoProductManagementPage();//to create product first
        productManagementPage.enterData("P1234567xx3", "Green Banana", "3.5", "Fresh Green Banana 2pounds", "100");
        productManagementPage.clickButtonAdd();
        productManagementPage.waitAndGetResultById("messageID");
        //Back to Shopping Page to verify the given product has been created
        page1 = productManagementPage.gotoShoppingPage();
        String inStock = page1.waitAndGetResultById("P1234567xx3_numberInStockID");
        assertThat(inStock, is("100"));
    }

    @After
    public void tearDown() {
        page1.quit();
        if (page2 != null) {
            page2.quit();
        }
        if (page3 != null) {
            page3.quit();
        }
    }
}
