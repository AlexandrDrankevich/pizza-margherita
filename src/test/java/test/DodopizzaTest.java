package test;

import org.junit.jupiter.api.Test;
import pageobjects.HomePage;
import pageobjects.HomePageForYourCity;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DodopizzaTest extends AbstractTest {
    private final String cityName = "Минск";
    private final String pizzaName = "Маргарита";
    private final String drinkName = "Pepsi";

    @Test
    public void testAddPizzaToCart() {
        HomePageForYourCity homePageForYourCity = new HomePage(driver)
                .openPage().chooseCity(cityName)
                .chooseProductAddToCart(pizzaName)
                .chooseProductAddToCart(drinkName)
                .clickButtonCart();
        assertTrue(homePageForYourCity.isProductInCart(pizzaName));
        assertTrue(homePageForYourCity.isProductInCart(drinkName));
    }
}
