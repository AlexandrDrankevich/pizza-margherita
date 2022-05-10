package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends AbstractPage {
    private String cartElementsLocator = "//section[@data-testid='cart__list']//h3";

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isProductInCart(String productName) {
        waitForVisibilityOfElement(driver.findElement(By.xpath(cartElementsLocator)));
        List<WebElement> elements = driver.findElements(By.xpath(cartElementsLocator));
        List<String> productCollect = elements.stream().map(WebElement::getText).collect(Collectors.toList());
        return productCollect.contains(productName);
    }
}
