package com.webstore.selenium.product;

import com.webstore.selenium.shoppingcart.ShoppingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductManagementPage {
    private WebDriver driver;
    public ProductManagementPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //quit
    public void quit() {
        this.driver.quit();
    }
    //open
    public void open(String url) {
        this.driver.get(url);
    }
    //fields
    @FindBy(name = "productNumber")
    public WebElement productNumberField; //test with 'P1234567890' as productNumber
    @FindBy(name = "name")
    public WebElement nameField;
    @FindBy(name = "price")
    public WebElement priceField;
    @FindBy(id = "descriptionID")
    public WebElement descriptionField;
    @FindBy(name = "numberInStock")
    public WebElement numberInStockField;
    @FindBy(id = "addBtnID")
    public WebElement addButton;
    //
    @FindBy(id = "searchTextID")
    public WebElement searchTextField;
    @FindBy(id = "searchBtnID")
    public WebElement searchButton;

    @FindBy(id = "messageID")
    public WebElement messageText;

    public void enterData(String productNumber, String name, String price, String description, String num) {
        this.productNumberField.sendKeys(productNumber);
        this.nameField.sendKeys(name);
        this.priceField.sendKeys(price);
        this.descriptionField.sendKeys(description);
        this.numberInStockField.sendKeys(num);
    }
//    public void enterData2(String productNumber, String name, String price, String description, String num) {
//        driver.findElement(By.name("productNumber")).sendKeys(productNumber);
//        driver.findElement(By.name("name")).sendKeys(name);
//        driver.findElement(By.name("price")).sendKeys(price);
//        driver.findElement(By.id("descriptionID")).sendKeys(description);
//        driver.findElement(By.name("numberInStock")).sendKeys(num);
//    }
    public void clickButtonAdd() {
        this.addButton.click();
    }
//    public void clickButtonAdd2() {
//        driver.findElement(By.id("addBtnID")).click();
//    }
    public String waitAndGetResultAfterThen(String componentId) {
        By textLocator = By.id(componentId);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));
        element.click();
        return element.getText();
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
    public void clickById(String componentId) {
        driver.findElement(By.id(componentId)).click();
    }
    public ProductDetailPage clickDetailButtonById(String componentId) {
        driver.findElement(By.id(componentId)).click();
        return new ProductDetailPage(this.driver);
    }
    public ProductDetailPage waitAndClickDetailButtonById(String componentId) {
        PageFactory.initElements(driver, this);
        By textLocator = By.id(componentId);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));
        element.click();
        return new ProductDetailPage(this.driver);
    }
    public ShoppingPage gotoShoppingPage() {
        driver.findElement(By.id("menuShoppingID")).click();
        return new ShoppingPage(driver);
    }

    public void enterSearchData(String value) {
        WebElement element = driver.findElement(By.id("searchTextID"));
        element.sendKeys(value);
    }

    public void clickSearchButton() {
        WebElement element = driver.findElement(By.id("searchBtnID"));
        element.click();
    }
}
