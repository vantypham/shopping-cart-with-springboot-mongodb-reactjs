package com.webstore.selenium.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailPage {
    WebDriver driver;
    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void quit() {
        this.driver.quit();
    }
    //fields
    @FindBy(id = "productNumberTextID")
    public WebElement textProductNumber;

    @FindBy(id = "menuProductID")
    public WebElement menuProductManagement;
    @FindBy(id = "updateBtnID")
    public WebElement updateBtn;

    public String getProductNumberDisplay() {
        return textProductNumber.getText();
    }

    public ProductManagementPage clickMenuProductManagement() {
        this.menuProductManagement.click();
        return new ProductManagementPage(driver);
    }

    public void clickById(String componentId) {
        driver.findElement(By.id(componentId)).click();
    }
    public String waitAndGetResultAfterThen(String componentId) {
        By textLocator = By.id(componentId);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));
        element.click();
        return element.getText();
    }


    public void clickUpdateButton() {
        this.updateBtn.click();
    }

    public String findFirstReviewItemTD() {
        ////*[@id="tableTwoID"]/tbody/tr/td
        WebElement element = driver.findElement(By.xpath("//*[@id='tableTwoID']/tbody/tr/td"));
        return element.getText();
    }
}
