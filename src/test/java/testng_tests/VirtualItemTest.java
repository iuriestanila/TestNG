package testng_tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.VirtualItem;

public class VirtualItemTest {
    VirtualItem virtualItem;
    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    void init() {
        virtualItem = new VirtualItem();
        softAssert = new SoftAssert();
    }

    @Test(groups = {"regression"})
    void setNameVirtualItem() {
        virtualItem.setName("Windows");
        final String nameRealItem = virtualItem.getName();
        softAssert.assertEquals(nameRealItem, virtualItem.getName());
        softAssert.assertAll();
    }
}

