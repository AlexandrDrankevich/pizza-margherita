package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class HomePageForYourCity extends AbstractPage {

    private String patternProductLocator = "//div[@data-gtm-id='product-title'][contains(text(),'%s')]";
    private String cartElementsLocator = "//section[@data-testid='cart__list']//h3";
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

    public HomePageForYourCity clickAddProductToCart() {
        try {
            waitForElementToBeClickable(buttonAdd).click();
        } catch (StaleElementReferenceException e) {
            waitForElementToBeClickable(buttonAdd).click();
        }
        return this;
    }

    public HomePageForYourCity clickCart() {
        buttonCart.click();
        return this;
    }

    public HomePageForYourCity chooseProductAddToCart(String productName) {
        chooseProduct(productName);
        clickAddProductToCart();
        return this;
    }

    public boolean isProductInCart(String productName) {
        waitForVisibilityOfElement(driver.findElement(By.xpath(cartElementsLocator)));
        List<WebElement> elements = driver.findElements(By.xpath(cartElementsLocator));
        List<String> productCollect = elements.stream().map(WebElement::getText).collect(Collectors.toList());
        return productCollect.contains(productName);
    }
}
