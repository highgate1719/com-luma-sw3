package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

/**
 * 6.Write down the following test into ‘GearTest’ class
 * 1. userShouldAddProductSuccessFullyToShoppingCart()
 * * Mouse Hover on Gear Menu
 * * Click on Bags
 * * Click on Product Name ‘Overnight Duffle’
 * * Verify the text ‘Overnight Duffle’
 * * Change Qty 3
 * * Click on ‘Add to Cart’ Button.
 * * Verify the text
 * ‘You added Overnight Duffle to your shopping cart.’
 * * Click on ‘shopping cart’ Link into
 * message
 * * Verify the product name ‘Cronus Yoga Pant’
 * * Verify the Qty is ‘3’
 * * Verify the product price ‘$135.00’
 * * Change Qty to ‘5’
 * * Click on ‘Update Shopping Cart’ button
 * * Verify the product price ‘$225.00’
 */
public class GearTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() throws InterruptedException {
        Thread.sleep(2000);

        // mouse hover on gear menu
        mouseHoverToElement(By.xpath("//a[@id='ui-id-6']"));

        // click on bags
        mouseHoverAndClick(By.xpath("//a[@id='ui-id-25']"));

        //  click on product name overnight duffle
        mouseHoverAndClick(By.xpath("//a[contains(text(),'Overnight Duffle')]"));

        // verify the text overnight duffle
        String actualText = getTextElement(By.xpath("//span[@class='base']"));
        Assert.assertEquals("Overnight Duffle", actualText);
        Thread.sleep(2000);

        //change qty 3
        driver.findElement(By.xpath("//input[@id='qty']")).clear();
        sendTextToElement(By.xpath("//input[@id='qty']"), "3");
        Thread.sleep(2000);

        //click on add to cart button
        mouseHoverOnElementAndClick(By.xpath("//span[normalize-space()='Add to Cart']"));
        Thread.sleep(2000);

        // Verify the text ‘You added Overnight Duffle to your shopping cart.’
        String actualText1 = getTextElement(By.xpath("//div[@data-ui-id='message-success']"));
        Assert.assertEquals("You added Overnight Duffle to your shopping cart.", actualText1);
        Thread.sleep(2000);

        // Click on ‘shopping cart’ Link into message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        Thread.sleep(2000);

        //verify the product name "overnight duffle"
        String actualProductName = getTextElement(By.xpath("//td[@class='col item']/descendant::a[2]"));
        Assert.assertEquals("Overnight Duffle", actualProductName);
        Thread.sleep(2000);

        // Verify the Qty is ‘3’
        String actualProductQty = getTextElement(By.xpath("//span[@class='counter-number']"));
        Assert.assertEquals("3", actualProductQty);
        Thread.sleep(2000);

        // Verify the product price ‘$135.00’
        String actualProductPrice = getTextElement(By.xpath("//td[@class='col subtotal']//descendant::span[contains(text(),'$135.00')]"));
        Assert.assertEquals("$135.00", actualProductPrice);
        Thread.sleep(2000);

        // Change Qty to ‘5’
        driver.findElement(By.xpath("//td[@class='col qty']/child::div[1]/descendant::input")).clear();
        sendTextToElement(By.xpath("//td[@class='col qty']/child::div[1]/descendant::input"), "5");
        Thread.sleep(2000);

        // Click on ‘Update Shopping Cart’ button
        clickOnElement(By.xpath("//button[@title='Update Shopping Cart']"));
        Thread.sleep(2000);

        // Verify the product price ‘$225.00’
        String actualProductNewPrice = getTextElement(By.xpath("//td[@data-th='Subtotal']//span[@class='price']"));
        Assert.assertEquals("$225.00", actualProductNewPrice);
        Thread.sleep(2000);

    }


    @After
    public void TearDown() {
        closeBrowser();
    }
}
