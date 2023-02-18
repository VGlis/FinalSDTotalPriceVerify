package provider;

import org.testng.annotations.DataProvider;

public class ProductsProvider {

    @DataProvider(name = "ProductProvider")
    public static Object[][] getProductProvider(){
        return new Object[][] {
                { "Sauce Labs Bike Light"},
                { "Sauce Labs Onesie" },
                { "Sauce Labs Fleece Jacket" },
                { "Sauce Labs Backpack" },
                { "Sauce Labs Bolt T-Shirt" },
                { "Test.allTheThings() T-Shirt (Red)" },

        };
    }


}
