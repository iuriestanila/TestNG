import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;
import java.io.*;

public class JsonParserTest {
    SoftAssert softAssert;
    private Parser parser;
    private Cart andrewCart;
    RealItem car;
    VirtualItem disk;
    Gson gson;

    @BeforeMethod(alwaysRun = true)
    void init() {
        parser = new JsonParser();
        andrewCart = new Cart("andrew-cart");
        disk = new VirtualItem();
        car = new RealItem();
        softAssert = new SoftAssert();
    }

    @Test(dataProvider = "dataSet", groups = "sanity")
    void FileNotFoundExceptionFileWriter(String pathname) {
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);

        disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(11);
        disk.setSizeOnDisk(20000);

        andrewCart.addRealItem(car);
        andrewCart.addVirtualItem(disk);
        parser.writeToFile(andrewCart);
        final BufferedReader[] reader = {null};

        Assert.assertThrows(FileNotFoundException.class,
                () -> reader[0] = new BufferedReader(new FileReader(pathname)));
    }

    @Test
    @DataProvider
    public Object[] dataSet(){
        return new Object[] {"src/main/andrew-cart.json","src/resources/andrew-cart.json",
                "main/resources/andrew-cart.json","src/main/resources",
                "src/main/resources/andre-cart.json"};
    }

    @Test(enabled = false)
    void readProperFile() {
        parser = new JsonParser();
        Cart eugenCart = parser.readFromFile(new File("src/main/resources/eugen-cart.json"));
        softAssert.assertEquals("eugen-cart", eugenCart.getCartName());
        softAssert.assertAll();
    }
}

