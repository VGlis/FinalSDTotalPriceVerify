package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FramesTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\drago\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://demoqa.com/frames");
        //First find the element using any of locator stratedgy
        WebElement iframeElement = driver.findElement(By.id("frame2"));

        //now use the switch command
        driver.switchTo().frame(iframeElement);
        WebElement h1 = driver.findElement(By.id("sampleHeading"));
        System.out.println(h1.getText());
        driver.switchTo().defaultContent();
        WebElement header = driver.findElement(By.xpath("//div[@class='main-header']"));
        System.out.println(header.getText());

        driver.quit();
    }

}
