package com.webstore.selenium.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }


    public void quit() {
        driver.quit();
    }
    public void open(String url) {
        driver.get(url);
    }
    public String waitAndGetResultAfterThen(String componentId) {
        By textLocator = By.id(componentId);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));
        element.click();
        return element.getText();
    }
    public void clickById(String componentId) {
        driver.findElement(By.id(componentId)).click();
    }
}
