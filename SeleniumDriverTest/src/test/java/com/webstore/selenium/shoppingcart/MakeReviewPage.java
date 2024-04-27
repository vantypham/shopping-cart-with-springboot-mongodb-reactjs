package com.webstore.selenium.shoppingcart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MakeReviewPage {
    WebDriver driver;
    public MakeReviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "addReviewBtnID")
    public WebElement addReviewBtn;

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
    public void enterData(String username, String message, String rate) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("message")).sendKeys(message);
        driver.findElement(By.name("rate")).sendKeys(rate);
    }

    public ShoppingPage clickBtnAddReview() {
        By textLocator = By.id("addReviewBtnID");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));
        element.click();
        //addReviewBtn.click();
        return new ShoppingPage(driver);
    }
}
