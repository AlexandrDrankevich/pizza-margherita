package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HomePageForYourCity extends AbstractPage {

    private String patternProductLocator = "//div[@data-gtm-id='product-title'][contains(text(),'%s')]";
    private String cartElementsLocator = "//section[@data-testid='cart__list']//h3";
    @FindBy(xpath = "//button[contains(text(),'Добавить')]")
    private WebElement buttonAdd;
    @FindBy(xpath = "//div[@data-testid='navigation__cart']/button")
    private WebElement buttonCart;
    @FindBy(xpath = "//section[@data-testid='cart__list']")
    private WebElement cart;

    public HomePageForYourCity(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePageForYourCity chooseProduct(String productName) {
        driver.findElement(By.xpath(String.format(patternProductLocator, productName))).click();
        return this;
    }

    public HomePageForYourCity clickAddProductToCart() {
        buttonAdd.click();
        return this;
    }

    public HomePageForYourCity clickCart() {
        buttonCart.click();
        return this;
    }

    public boolean isProductInCart(String productName) {
        clickCart();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS)).until(ExpectedConditions.visibilityOf(cart));
        List<WebElement> elements = driver.findElements(By.xpath(cartElementsLocator));
        List<String> productCollect = elements.stream().map(s -> s.getText()).collect(Collectors.toList());
        return productCollect.contains(productName);
    }
}
