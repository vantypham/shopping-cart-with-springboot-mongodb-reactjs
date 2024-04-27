package com.webstore.selenium.shoppingcart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GotoCardPage {
    WebDriver driver;
    public GotoCardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void quit() {
        this.driver.quit();
    }

    public String waitAndGetResultById(String componentId) {
        By textLocator = By.id(componentId);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));
        element.click();
        return element.getText();
    }
    public String waitAndGetResultByName(String componentName) {
        By textLocator = By.name(componentName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));
        element.click();
        return element.getText();
    }
    public String waitAndGetResultByTagName(String componentName) {
        By textLocator = By.tagName(componentName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));
        element.click();
        return element.getText();
    }
    public void clickById(String componentId) {
        driver.findElement(By.id(componentId)).click();
    }
}
