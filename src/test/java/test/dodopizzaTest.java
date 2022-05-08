package test;

import org.junit.jupiter.api.Test;
import pageobjects.HomePage;
import pageobjects.HomePageForYourCity;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class dodopizzaTest extends AbstractTest {
    private final String cityName = "Минск";
    private final String productName = "Маргарита";

    @Test
    public void testAddPizzaToBucket() {
        HomePageForYourCity homePageForYourCity = new HomePage(driver)
                .openPage().chooseCity(cityName)
                .chooseProduct(productName)
                .clickAddProductToCart();
        assertTrue(homePageForYourCity.isProductInCart(productName));
    }
}
