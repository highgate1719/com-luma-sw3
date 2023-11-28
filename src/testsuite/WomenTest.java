package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Create the package ‘testsuite’ and create the
 * following classes inside the ‘testsuite’ package.
 * <p>
 * 1. WomenTest
 * 2. MenTest
 * 3. GearTest
 * <p>
 * 4. Write down the following test into WomenTestclass
 * 1. verifyTheSortByProductNameFilter
 * * Mouse Hover on Women Menu
 * * Mouse Hover on Tops
 * * Click on Jackets
 * * Select Sort By filter “Product Name”
 * * Verify the products name display in
 * alphabetical order
 * 2. verifyTheSortByPriceFilter
 * * Mouse Hover on Women Menu
 * * Mouse Hover on Tops
 * * Click on Jackets
 * * Select Sort By filter “Price”
 * * Verify the products price display in
 * Low to High
 */
public class WomenTest extends Utility {

    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyTheSortByProductNameFilter() throws InterruptedException {
        //mouse hover on women menu
        mouseHoverToElement(By.xpath("//span[normalize-space()='Women']"));
        Thread.sleep(2000);
        // mouse hover on tops
        mouseHoverToElement(By.xpath("//a[@id='ui-id-9']"));
        //click on jackets
        clickOnElement(By.xpath("//a[@id='ui-id-11']"));
        Thread.sleep(2000);

        //Select Sort By filter “Product Name”
        selectByVisibleTextFromDropDown(By.xpath("(//select[@id='sorter'])[1]"), "Product Name");
        Thread.sleep(2000);

        //Verify the products name display in alphabetical order
        List<WebElement> expectedProductsNameList = driver.findElements(By.xpath("//strong[contains(@class,'product name product-item-name')]/child::a"));
        List<String> expectedProductLists = new ArrayList<>();
        for (WebElement productName : expectedProductsNameList) {
            expectedProductLists.add(productName.getText());
        }
        System.out.println("Expected Product Name in Alphabetical Order: " + expectedProductLists);
        Thread.sleep(2000);

        //Original list of products name
        List<WebElement> originalProductsNameList = driver.findElements(By.xpath("//strong[contains(@class,'product name product-item-name')]/child::a"));
        List<String> originalProductLists = new ArrayList<>();
        for (WebElement productName : originalProductsNameList) {
            originalProductLists.add(productName.getText());
        }
        System.out.println("Original Product Name in Alphabetical Order: " + originalProductLists);
        Thread.sleep(2000);
        Assert.assertEquals(expectedProductLists, originalProductLists);
        Thread.sleep(2000);
    }

    @Test
    public void verifyTheSortByPriceFilter() throws InterruptedException {

        //mouse Hover on Women Menu
        Thread.sleep(2000);
        mouseHoverToElement(By.xpath("//span[contains(text(),'Women')]"));
        Thread.sleep(2000);

        //mouse Hover on Tops
        mouseHoverToElement(By.xpath("//li[contains(@class,'nav-2-1 ')]/child::a"));
        Thread.sleep(2000);

        // Click on Jackets
        mouseHoverOnElementAndClick(By.xpath("//li[contains(@class,'nav-2-1 ')]/child::ul/child::li[1]/child::a"));
        Thread.sleep(2000);

        //select Sort By filter “Price”
        selectByVisibleTextFromDropDown(By.xpath("//div[@class='column main']/child::div[2]/child::div[3]/child::select[1]"), "Price");
        Thread.sleep(2000);

        //expected list of products price in Low to High
        List<WebElement> expectedProductsPriceList = driver.findElements(By.cssSelector("span[data-price-type='finalPrice']"));
        List<Double> expectedProductPriceLists = new ArrayList<>();
        for (WebElement productPrice : expectedProductsPriceList) {
            expectedProductPriceLists.add(Double.valueOf(productPrice.getText().replace("$", "")));
        }
        System.out.println("Expected Product Price in Low to High: " + expectedProductPriceLists);
        Thread.sleep(2000);

        //original list of products price in Low to High
        List<WebElement> originalProductsPriceList = driver.findElements(By.cssSelector("span[data-price-type='finalPrice']"));
        List<Double> originalProductPriceLists = new ArrayList<>();
        for (WebElement productPrice : expectedProductsPriceList) {
            originalProductPriceLists.add(Double.valueOf(productPrice.getText().replace("$", "")));
        }
        System.out.println("Original Product Price in Low to High: " + originalProductPriceLists);
        Thread.sleep(2000);

        //verify the products price display in Low to High
        Assert.assertEquals(expectedProductPriceLists, originalProductPriceLists);
        Thread.sleep(2000);


    }


    @After
    public void TearDown() {
        closeBrowser();
    }
}