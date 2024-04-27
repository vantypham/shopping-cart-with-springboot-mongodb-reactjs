package com.webstore.selenium.product;

import com.webstore.selenium.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProductManagementTest {
    ProductManagementPage page1;
    ProductDetailPage page2;
    WebDriver driver;

    @Before
    public void createWebDriver() {
        // set path to chromedriver.exe
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", Utils.getDriverURL());
        options.setBinary(Utils.getBinaryURL());
        options.addArguments("--remote-allow-origins=*");
        // create chrome instance
         driver = new ChromeDriver(options);
        page1 = PageFactory.initElements(driver, ProductManagementPage.class);
        page1.open("http://localhost:3000/products");
    }

    @Test
    public void testAdd_Remove_Product_P1234567890() {
        page1.enterData("P1234567890", "Green Banana", "3.5", "Fresh Green Banana 2pounds", "10");
        page1.clickButtonAdd();
        String actual = page1.waitAndGetResultAfterThen("messageID");
        assertThat(actual, is("Added successfully!"));
        //CLICK REMOVE
        page1.clickById("P1234567890_removeID");
         actual = page1.waitAndGetResultAfterThen("messageID");
        assertThat(actual, is("Removed successfully!"));
    }

  @Test
   public void test_Detail_Update_Product_P1234567899() {
      page1.enterData("P1234567899", "Green Banana", "3.5", "Fresh Green Banana 2pounds", "10");
      page1.clickButtonAdd();
      String actual = page1.waitAndGetResultAfterThen("messageID");
      assertThat(actual, is("Added successfully!"));
      //CLICK Detail button
      page2 = page1.clickDetailButtonById("P1234567899_detailID");
      // move to next page then verify data
      actual = page2.getProductNumberDisplay();
      assertThat(actual, is("P1234567899"));
      //click UPDATE button
      page2.clickUpdateButton();
      //verify data
      actual = page2.waitAndGetResultAfterThen("messageID");
      assertThat(actual, is("Updated successfully!"));

      //Back to Products Page
      page1 = page2.clickMenuProductManagement();
      //CLICK REMOVE
      page1.clickById("P1234567899_removeID");
      //page1.waitAndGetResultAfterThen("P1234567899_removeID");
      actual = page1.waitAndGetResultAfterThen("messageID");
      assertThat(actual, is("Removed successfully!"));
   }

    @After
    public void tearDown() {
        page1.quit();
        if (page2 != null) {
            page2.quit();
        }
    }

}
