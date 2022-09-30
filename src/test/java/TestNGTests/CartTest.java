package TestNGTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

public class CartTest {
    Cart andrewCart;
    RealItem car;
    VirtualItem disk;
    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    void init() {
        andrewCart = new Cart("andrew-cart");
        car = new RealItem();
        softAssert = new SoftAssert();
    }

    @Test(groups = {"sanity"},priority = 2)
    void totalPriceRealItem() {
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);
        andrewCart.addRealItem(car);

        final double expected = andrewCart.getTotalPrice();
        softAssert.assertEquals(expected, andrewCart.getTotalPrice());
        softAssert.assertAll();
    }

    @Parameters({"name", "price","weight"})
    @Test(groups = "regression", priority = 1)
    void addRealItemAsInput(@Optional("Audi") String name,@Optional("32026.9") double price,
                            @Optional("1560.7") double weight) {
        car.setName(name);
        car.setPrice(price);
        car.setWeight(weight);
        andrewCart.addRealItem(car);
        final double totalPrice = andrewCart.getTotalPrice();

        softAssert.assertEquals(name, car.getName());
        softAssert.assertEquals(price, car.getPrice(),0.001);
        softAssert.assertEquals(weight, car.getWeight(),0.001);
        softAssert.assertEquals(totalPrice,andrewCart.getTotalPrice());
        softAssert.assertAll();
    }
}

