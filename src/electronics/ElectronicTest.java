package electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class ElectronicTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";



    @Before
    public void setBaseUrl()
    {
        openBrowser(baseUrl);
    }
    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully(){
        //1.1 Mouse Hover on “Electronics” Tab
        //1.2 Mouse Hover on “Cell phones” and click
        //1.3 Verify the text “Cell phones”
        mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));
        mouseHoverAndClickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        String actualMessage= "Cell phones";
        WebElement expectedMessage = driver.findElement(By.xpath("//h1[contains(text(),'Cell phones')]"));
        String expectedMessage1= expectedMessage.getText();
        Assert.assertEquals("Error",actualMessage,expectedMessage1);
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully()throws InterruptedException{
        //2.1  Mouse Hover on “Electronics” Tab

        mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));
        //2.2 Mouse Hover on “Cell phones” and click
        mouseHoverAndClickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        //2.3 Verify the text “Cell phones”
        String actualMessage= "Cell phones";
        WebElement expectedMessage = driver.findElement(By.xpath("//h1[contains(text(),'Cell phones')]"));
        String expectedMessage1= expectedMessage.getText();
        Assert.assertEquals("Error",actualMessage,expectedMessage1);
        //2.4 Click on List View Tab
        Thread.sleep(2000);
        clickOnElement(By.xpath("//a[@class='viewmode-icon list']"));

        //2.5  Click on product name “Nokia Lumia 1020” link
        Thread.sleep(2000);
        clickOnElement(By.xpath("//h2[@class='product-title']//a[contains(text(),'Nokia Lumia 1020')]"));
        //2.6 Verify the text “Nokia Lumia 1020”
        String expectedNokia ="Nokia Lumia 1020";
        String actualNokia =driver.findElement(By.xpath("//body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[1]/h1[1]")).getText();
        verifyElementMethod(expectedNokia,actualNokia);
        //2.7 Verify the price “$349.00”
        String expectedPrice ="$349.00";
        String actualPrice= driver.findElement(By.xpath("//span[normalize-space()='$349.00']")).getText();
        verifyElementMethod(expectedPrice,actualPrice);

        //2.8 Change quantity to 2
        driver.findElement(By.xpath("//input[@id='product_enteredQuantity_20']")).clear();
        driver.findElement(By.xpath("//input[@id='product_enteredQuantity_20']")).sendKeys("2");

        //2.9  Click on “ADD TO CART” tab
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));

        //2.10 2.10 Verify the Message "The product has been added to your shopping cart" on Top green Bar
        //After that close the bar clicking on the cross button.
        String expectedAdded = "The product has been added to your shopping cart";
        String actualAdded = driver.findElement(By.xpath("//p[@class='content']")).getText();
        verifyElementMethod(expectedAdded,actualAdded);
        clickOnElement(By.xpath("//div[@class='bar-notification success']/span"));

        //2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverAndClickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));

        //2.12  Verify the message "Shopping cart"
        String expectedCartMessage = "Shopping cart";
        String actualCartMessage = driver.findElement(By.xpath("//h1[normalize-space()='Shopping cart']")).getText();
        verifyElementMethod(expectedCartMessage,actualCartMessage);

        //2.13 Verify the quantity is 2


        WebElement text = driver.findElement(By.xpath("//input[@class='qty-input']"));
        String expectedText1 = "2";
        String actualText2 = text.getAttribute("value");
        verifyElementMethod(expectedText1, actualText2);


        //2.14 Verify the Total $698.00
        String expectedTotalNumber = "$698.00";
        String actualTotalNumber =driver.findElement(By.xpath("//span[@class='product-subtotal']")).getText();
        verifyElementMethod(expectedTotalNumber,actualTotalNumber);

        //2.15  click on checkbox “I agree with the terms of service”
        selectCheckBox(By.xpath("//input[@id='termsofservice']"));

        //2.16  Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

        //2.17  Verify the Text “Welcome, Please Sign In!”
        String expectedWelcome= "Welcome, Please Sign In!";
        String actualWelcome = getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        verifyElementMethod(expectedWelcome,actualWelcome);

        //2.18  Click on “REGISTER” tab
        clickOnElement(By.xpath("//a[normalize-space()='Register']"));

        //2.19  Verify the text “Register”
        String expectedRegister= "Register";
        String actualRegister= driver.findElement(By.xpath("//h1[normalize-space()='Register']")).getText();
        verifyElementMethod(expectedRegister,actualRegister);

        //2.20  Fill the mandatory fields
        sendTextToElement(By.id("FirstName"), "Neha");
        sendTextToElement(By.id("LastName"), "Smith");
        sendTextToElement(By.id("Email"), "Neha9978@gmail.com");
        sendTextToElement(By.id("Password"), "User123");
        sendTextToElement(By.id("ConfirmPassword"), "User123");

        //2.21 Click on “REGISTER” Button
        Thread.sleep(2000);
        clickOnElement(By.id("register-button"));

        //2.22  Verify the message “Your registration completed”
        String expectedRegistration = "Your registration completed";
        String actualRegistration = driver.findElement(By.xpath("//div[contains(text(),'Your registration completed')]")).getText();
        verifyElementMethod(expectedRegistration, actualRegistration);

        //2.23  Click on “CONTINUE” tab
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));

        //2.24  Verify the text “Shopping card”
        String expectedShopping = "Shopping cart";
        String actualShopping =driver.findElement(By.xpath("//h1[normalize-space()='Shopping cart']")).getText();
        verifyElementMethod(expectedShopping,actualShopping);


        // 2.24 Click on login
        clickOnElement(By.xpath("//a[normalize-space()='Log in']"));



        // Enter email
        sendTextToElement(By.xpath("//input[@id='Email']"),"Neha9978@gmail.com");
        // password
        sendTextToElement(By.xpath("//input[@id='Password']"),"User123");
        // click
        clickOnElement(By.xpath("//button[contains(text(),'Log in')]"));

        // 2.25 click on checkbox “I agree with the terms of service”
        Thread.sleep(2000);
        clickOnElement(By.xpath("//input[@id='termsofservice']"));


        //2.26 Click on “CHECKOUT”
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[@id='checkout']"));

        //2.27  Fill the Mandatory fields
        Thread.sleep(2000);

        selectFromDropDownMenu(By.xpath("//select[@id='BillingNewAddress_CountryId']"),"United States");
        //state
        selectFromDropDownMenu(By.xpath("//select[@id='BillingNewAddress_StateProvinceId']"),"Alaska");
        //city
        sendTextToElement(By.id("BillingNewAddress_City"),"Reading");
        //Address
        sendTextToElement(By.id("BillingNewAddress_Address1"),"CrossWay");
        //Postal code
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"),"363641");
        //Phone number
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"),"9825655114");



        //2.28  Click on “CONTINUE”
        Thread.sleep(2000);
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[2]/div[1]/button[4]"));

        //2.29 Click on Radio Button “2nd Day Air ($0.00)”
        Thread.sleep(2000);
        clickOnElement(By.xpath("//input[@id='shippingoption_2']"));

        //2.30 Click on “CONTINUE”
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        //2.312.31 Select Radio Button “Credit Card”
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        Thread.sleep(2000);
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[4]/div[2]/div[1]/button[1]"));

        //2.32 Select “Visa” From Select credit card dropdown
        selectFromDropDownMenu(By.xpath("//select[@id='CreditCardType']"), "Visa");

        //2.33 Fill all the details
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Miss Neha");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5555555555554444");
        selectFromDropDownMenu(By.xpath("//select[@id='ExpireMonth']"), "10");
        selectFromDropDownMenu(By.xpath("//select[@id='ExpireYear']"), "2027");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "789");

        // 2.34 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        //2.35 Verify “Payment Method” is “Credit Card”
        String expectedCard = "Credit Card";
        String actualCard = driver.findElement(By.xpath("//span[contains(text(),'Credit Card')]")).getText();
        verifyElementMethod(expectedCard, actualCard);

        //2.36  Verify “Shipping Method” is “2nd Day Air”
        String expectedShippingMethod = "2nd Day Air";
        String actualShippingMethod =driver.findElement(By.xpath("//li[@class='shipping-method']/span[@class='value']")).getText();
        verifyElementMethod(expectedShippingMethod, actualShippingMethod);

        //2.37 Verify Total is “$698.00”
        String expectedTotalPrice = "$698.00";
        String actualTotalPrice = driver.findElement(By.xpath("//tbody/tr[1]/td[6]/span[1]")).getText();
        verifyElementMethod(expectedTotalPrice, actualTotalPrice);

        //2.38 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        //2.39 Verify the Text “Thank You”
        String expectedMessage14 = "Thank you";
        String actualMessage14 = driver.findElement(By.xpath("//h1[text()='Thank you']")).getText();
        verifyElementMethod(expectedMessage14, actualMessage14);

        //2.40 Verify the message “Your order has been successfully processed!”
        String expectedMessage15 = "Your order has been successfully processed!";
        String actualMessage15 =driver.findElement(By.xpath("//strong[text()='Your order has been successfully processed!']")).getText();
        verifyElementMethod(expectedMessage15, actualMessage15);

        //2.41 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 order-completed-continue-button']"));

        //2.42 Verify the text “Welcome to our store”
        String expectedMessage16 = "Welcome to our store";
        String actualMessage16 =driver.findElement(By.xpath("//h2[text()='Welcome to our store']")).getText();
        verifyElementMethod(expectedMessage16, actualMessage16);

        //2.43 Click on “Logout” link
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));

        //2.44 Verify the URL is “https://demo.nopcommerce.com/”
        String expectedMessage17 = "https://demo.nopcommerce.com/";
        String actualMessage17 = driver.getCurrentUrl();
        verifyElementMethod(expectedMessage17, actualMessage17);
    }



    @After
    public void tearDown()
    {
         driver.close();
    }
}



