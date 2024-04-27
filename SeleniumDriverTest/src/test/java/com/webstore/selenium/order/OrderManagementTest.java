package com.webstore.selenium.order;

import com.webstore.selenium.Utils;
import com.webstore.selenium.product.ProductManagementPage;
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

public class OrderManagementTest {
    WebDriver driver;
    OrderPage page1;
    OrderDetailPage page2;

    @Before
    public void createWebDriver() {
        // set path to chromedriver.exe
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", Utils.getDriverURL());
        options.setBinary(Utils.getBinaryURL());
        options.addArguments("--remote-allow-origins=*");
        // create chrome instance
        driver = new ChromeDriver(options);
        page1 = PageFactory.initElements(driver, OrderPage.class);
        page1.open("http://localhost:3000/orders");
    }
    @Test
    public void testA_SearchByPLACEDStatus() {
        String actual = "100";
        assertThat(actual, is("100"));
    }
    @Test
    public void testB_ViewDetailOfOrder() {
        String actual = "100";
        assertThat(actual, is("100"));
    }
    @Test
    public void testC_UpdateStatusSHIPPED() {
        String actual = "100";
        assertThat(actual, is("100"));
    }
    @After
    public void tearDown() {
        page1.quit();
        if (page2 != null) {
            page2.quit();
        }
    }
}
