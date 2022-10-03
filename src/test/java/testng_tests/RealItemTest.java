package testng_tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.RealItem;

public class RealItemTest {
    RealItem car;
    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    void init() {
        car = new RealItem();
        softAssert = new SoftAssert();
    }

    @Test(groups = {"sanity"})
    void setNameRealItem() {
        car.setPrice(500);
        final double priceRealItem = car.getPrice();
        softAssert.assertEquals(priceRealItem, car.getPrice());
        softAssert.assertAll();
    }
}

