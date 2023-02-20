package tests;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;


public class ProductsTests {


    @Test
    public void verifyTotalPriceProduct() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);

        System.out.println("Number of Items in Cart before add: " + productsPage.productCountInCart());
        Assert.assertEquals(productsPage.productCountInCart(), 0, "Product count is not equals");

        productsPage.addProductToCartByName("Sauce Labs Backpack");
        productsPage.addProductToCartByName("Sauce Labs Bike Light");
        productsPage.addProductToCartByName("Sauce Labs Fleece Jacket");
        productsPage.addProductToCartByName("Sauce Labs Onesie");
        productsPage.addProductToCartByName("Sauce Labs Bolt T-Shirt");
        productsPage.addProductToCartByName("Test.allTheThings() T-Shirt (Red)");

        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        cartPage.setFirstName("Vladan");
        cartPage.setLastName("Glisovic");
        cartPage.setZip("11000");
        cartPage.clickContinue();

        System.out.println("Number of Items in Cart after add: " + productsPage.productCountInCart());
        System.out.println("List of items in Cart: ");
        cartPage.printNamesProductInCart();
        System.out.println("**************************************************************************");
        System.out.println("Total sum count in CartPage by APP: $" + cartPage.getTotalCount());
        System.out.println("Total sum count in CartPage by QA:  $" + cartPage.sumTotalCount());

        Assert.assertEquals(cartPage.getTotalCount(), cartPage.sumTotalCount(), "Product count is not equals");

        cartPage.close();
    }


}
