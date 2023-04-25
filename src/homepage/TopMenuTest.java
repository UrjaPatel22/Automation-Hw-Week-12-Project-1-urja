package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class TopMenuTest extends Utility {



    String baseUrl = "https://demo.nopcommerce.com/";


    String menu = "computers" ;
    @Before
    public void setBaseUrl()
    {
        openBrowser(baseUrl); // set up the browser
    }

//  1.1 create method with name "selectMenu" it has one parameter name "menu" of type string
//1.2 This method should click on the menu whatever name is passed as parameter.
    public void selectMenu(String menu)
    {
        // storing all top menu elements in a list
        List<WebElement> elements = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']//child::li"));
        //will find the computers and click on the computers
        for (WebElement element:elements) {
            if(element.getText().equalsIgnoreCase(menu))
            {
                element.click();
                break;
            }

        }

    }

    //1.3
    @Test
    public void verifyPageNavigation()
    {
        //1.3. create the @Test method name verifyPageNavigation.use selectMenu method to
        //select the Menu and click on it and verify the page navigation.
        selectMenu(menu);
        String expectedMessage = "Computers";
        String actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Computers']"));
        //verifying whether the user is navigated to computer page
        Assert.assertEquals("User is not navigated to computer page ",expectedMessage,actualMessage);
    }
    @After

    public void tearDown()
    {
        driver.close(); // closing the browser
    }

}
