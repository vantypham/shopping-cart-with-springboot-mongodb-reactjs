package com.webstore.selenium.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderDetailPage {

    WebDriver driver;
    public OrderDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void quit() {
        this.driver.quit();
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
