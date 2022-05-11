package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageForYourCity extends AbstractPage {

    private String patternProductLocator = "//div[@data-gtm-id='product-title'][contains(text(),'%s')]";
    @FindBy(xpath = "//button[contains(text(),'Добавить')]")
    private WebElement buttonAdd;
    @FindBy(xpath = "//div[@data-testid='navigation__cart']/button")
    private WebElement buttonCart;

    public HomePageForYourCity(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePageForYourCity chooseProduct(String productName) {
        waitForVisibilityOfElement(driver.findElement(By.xpath(String.format(patternProductLocator, productName))))
                .click();
        return this;
    }

    public HomePageForYourCity clickButtonAddProductToCart() {
        try {
            waitForElementToBeClickable(buttonAdd).click();
        } catch (StaleElementReferenceException e) {
            waitForElementToBeClickable(buttonAdd).click();
        }
        return this;
    }

    public CartPage clickButtonCart() {
        waitForElementToBeClickable(buttonCart).click();
        return new CartPage(driver);
    }

    public HomePageForYourCity chooseProductAddToCart(String productName) {
        chooseProduct(productName);
        clickButtonAddProductToCart();
        return this;
    }
}
