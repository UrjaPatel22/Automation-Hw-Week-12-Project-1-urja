package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {
    String menu;
    String baseUrl="https://demo.nopcommerce.com/";
    @Before
    public void setBaseUrl()
    {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder()
    {
        //1.1  Click on Computer Menu.
        mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
        //mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));
        //1.2  Click on Desktop
        mouseHoverAndClickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));
        //1.3  Select Sort By position "Name: Z to A"
        //1.4 Verify the Product will arrange in Descending order.
        List<WebElement> beforeSelectElementList = driver.findElements(By.xpath("//div/h2[@class='product-title']"));
        List<String> beforeSelectElementList1 = new ArrayList<>();
        for (WebElement list : beforeSelectElementList) {
            beforeSelectElementList1.add(String.valueOf(list.getText()));
        }

        selectFromDropDownMenu(By.xpath("//select[@id='products-orderby']"),"Name: Z to A");
        List<WebElement> afterSelectElementList = driver.findElements(By.xpath("//h2[@class='product-title']"));
        List<String> afterSelectElementList1 = new ArrayList<>();
        for (WebElement list :afterSelectElementList ) {
            afterSelectElementList1.add(String.valueOf(list.getText()));
        }

        Collections.sort(beforeSelectElementList1);
        //  Collections.reverse(afterSelectElementList1);
        Assert.assertEquals("Product is not displayed ",beforeSelectElementList1,afterSelectElementList1);

    }
    @Test
    public void verifyProductAddedToShoppingCartSuccessFully()throws InterruptedException{
        //2.1  Click on Computer Menu.
        mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));

        //2.2  Click on Desktop
        mouseHoverAndClickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));
        //2.3  Select Sort By position "Name: A to Z"
        selectFromDropDownMenu(By.xpath("//select[@id='products-orderby']"),"Name: A to Z");
        //2.4 Click on "Add To Cart"


        Thread.sleep(2000);
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        //2.5 Verify the Text "Build your own computer"
        String expectedText ="Build your own computer";
        WebElement actualText = driver.findElement(By.xpath("//div[@class='product-name']"));
        String actualText1 = actualText.getText();
        Assert.assertEquals("Error Message,Product is not added to card",expectedText,actualText1);
        //2.6  Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectFromDropDownMenu(By.xpath("//select[@id='product_attribute_1']"),"2.2 GHz Intel Pentium Dual-Core E2200");
        //2.7 .Select "8GB [+$60.00]" using Select class.
        selectFromDropDownMenu(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[6]/dl[1]/dd[2]/select[1]"),"8GB [+$60.00]");
        //2.8 Select HDD radio "400 GB [+$100.00]"
        Thread.sleep(2000);
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));
        //2.9 Select OS radio "Vista Premium [+$60.00]"

        clickOnElement(By.xpath("//label[text()='Vista Premium [+$60.00]']"));
        //2.10 2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander[+$5.00]"

        Thread.sleep(2000);
        clickOnElement(By.xpath("//input[@id='product_attribute_5_12']"));
        //2.11 Verify the price "$1,475.00"
        String expectedMessage = "$1,475.00";
        String actualMessage = driver.findElement(By.xpath("//span[text()='$1,475.00']")).getText();
        verifyElementMethod(expectedMessage,actualMessage);
        //2.12 Click on "ADD TO CARD" Button.
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));
        //2.13 2.13 Verify the Message "The product has been added to your shopping cart" on Top green Bar
        //After that close the bar clicking on the cross button.
        String expectedMessage1 = "The product has been added to your shopping cart";
        String actualMessage1 = driver.findElement(By.xpath("//div[@class='bar-notification success']/p")).getText();
        verifyElementMethod(expectedMessage1,actualMessage1);

        Thread.sleep(1000);
        clickOnElement(By.xpath("//div[@class='bar-notification success']/span"));
        //2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverOnElement(By.xpath("//span[@class='cart-label']"));
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[@class='button-1 cart-button']"));
        //2.15  Verify the message "Shopping cart"
        String expectedCart = "Shopping cart";
        String actualCart = driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText();
        verifyElementMethod(expectedCart,actualCart);
        Thread.sleep(2000);
        //2.16  Change the Qty to "2" and Click on "Update shopping cart"
        driver.findElement(By.xpath("//input[@class='qty-input']")).clear();
        sendTextToElement(By.xpath("//input[@class='qty-input']"),"2");
        clickOnElement(By.xpath("//button[@class='button-2 update-cart-button']"));
        //2.17 Verify the Total"$2,950.00"
        String expectedTotal = "$2,950.00";
        String actualTotal = driver.findElement(By.xpath("//td[@class='subtotal']/span[text()='$2,950.00']")).getText();
        verifyElementMethod(expectedTotal,actualTotal);
        //2.18  click on checkbox “I agree with the terms of service”
        Thread.sleep(2000);
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        //2.19  Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

        //2.20 Verify the Text “Welcome, Please Sign In!”
        String expectedWelcome = "Welcome, Please Sign In!";
        String actualWelcome = driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText();
        verifyElementMethod(expectedWelcome,actualWelcome);
        //2.21 Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));
        //2.22 Fill the all mandatory field
        sendTextToElement(By.id("BillingNewAddress_FirstName"),"john");
        sendTextToElement(By.id("BillingNewAddress_LastName"),"smith");
        sendTextToElement(By.id("BillingNewAddress_Email"),"john@gmail.com");
        selectFromDropDownMenu(By.xpath("//select[@id='BillingNewAddress_CountryId']"),"United Kingdom");
        sendTextToElement(By.id("BillingNewAddress_City"),"London");
        sendTextToElement(By.id("BillingNewAddress_Address1"),"22 A Eton");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"),"ha0 2fc");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"),"07234567869");
        //2.23 2.23 Click on “CONTINUE”

        Thread.sleep(2000);
        clickOnElement(By.xpath("//div[@id='billing-buttons-container']/button[@class='button-1 new-address-next-step-button']"));
        //2.24 Click on Radio Button
        clickOnElement(By.xpath("//input[@id='shippingoption_1']"));
        //2.25 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        //2.26 Select Radio Button “Credit Card”
        selectRadioButton(By.xpath("//input[@id='paymentmethod_1']"));
        //2.27  Select “Master card” From Select credit card dropdown
        Thread.sleep(2000);
       // selectFromDropDownMenu(By.xpath("//select[@id='CreditCardType']"),"Master card");
        Thread.sleep(1000);
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));
        //2.28  Fill all the details
        sendTextToElement(By.id("CardholderName"),"Mr John Smith");
        sendTextToElement(By.id("CardNumber"),"3256754579765121");
        selectFromDropDownMenu(By.xpath("//select[@id='ExpireMonth']"),"07");
        selectFromDropDownMenu(By.xpath("//select[@id='ExpireYear']"),"2026");
        sendTextToElement(By.xpath("//input[@id='CardCode']"),"333");
        //2.29 Click on “CONTINUE”
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        //2.30 Verify “Payment Method” is “Credit Card”
        String expectedPaymenMethod = "Credit Card";
        String actualPaymentMethod = driver.findElement(By.xpath("//li[@class='payment-method']/span[@class='value']")).getText();
        Thread.sleep(1000);
        Assert.assertEquals("not credit card",expectedPaymenMethod,actualPaymentMethod);
//2.31 Verify “Shipping Method” is “Next Day Air”
        String expectedShippingMethod ="Next Day Air";
        String actualShippingMethod = getTextFromElement(By.xpath("//li[@class='shipping-method']/span[@class='value']"));
        Thread.sleep(1000);
        Assert.assertEquals("not next day air",expectedShippingMethod,actualShippingMethod);
        // 2.32 Verify Total is “$2,950.00”
        String expectedTotalAmount = "$2,950.00";
        String actualTotalAmount = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        Assert.assertEquals("Not Verify",expectedTotalAmount,actualTotalAmount);
        // 2.33 Click on “CONFIRM”
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@class='button-1 confirm-order-next-step-button']"));

        String expectedThankYou = "Thank you";
        Thread.sleep(1000);
        String actualThankYou = getTextFromElement(By.xpath("//div[@class='page-title']/h1"));
        // 2.34 Verify the Text “Thank You”
        Thread.sleep(1000);
        Assert.assertEquals("Thank you not displayed",expectedThankYou,actualThankYou);

        String expectedSuccessfullyProcessed = "Your order has been successfully processed!";
        String actualSuccessfullyProcessed = getTextFromElement(By.xpath("//div[@class='section order-completed']/div[@class='title']/strong"));
        //2.35  Verify the message “Your order has been successfully processed!”
        Assert.assertEquals("Not processed",expectedSuccessfullyProcessed,actualSuccessfullyProcessed);
        // 2.36 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 order-completed-continue-button']"));
        //2.37 Verify the text “Welcome to our store”
        String expectedWelcomeMessage = "Welcome to our store";
        String actualWelcomeMessage = getTextFromElement(By.xpath("//div[@class='topic-block-title']/h2"));

        Thread.sleep(1000);
        Assert.assertEquals("Not successfully processed",expectedWelcomeMessage,actualWelcomeMessage);
    }

    @After
    public void tearDown()
    {
         driver.close();
    }
}


