package ui.steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import ui.pages.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class Steps {

    HomePage homePage = new HomePage();
    SmartwatchesPage smartwatchesPage = new SmartwatchesPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();

    @Given("^Open browser$")
    public void openBrowser() {
        Configuration.browserSize = "2500x1080";
        open("https://www.google.com");
        log.info(Configuration.browserSize);

    }

    @When("^Open the (.+) website$")
    public void openWebsite(String url) throws InterruptedException {
        homePage.openAppAndAcceptCookies();
    }

    @Then("^(.+): element (.+) (is|is not) displayed$")
    public void isElementDisplayed(String pageName, String elementName, String option) {
        Page page = pageSelector(pageName);
        log.info(Configuration.browserSize);

        Assert.assertEquals("Not on the expected page", page.getPageName(), pageName);

        boolean shouldBeDisplayed = option.equals("is");
        boolean isDisplayed = page.getElement(elementName).isDisplayed();

        if (shouldBeDisplayed) {
            Assert.assertTrue("Element " + elementName + " on " + pageName + " is not displayed.", isDisplayed);
        } else {
            Assert.assertFalse("Element " + elementName + " on " + pageName + " is displayed.", isDisplayed);
        }
    }


    @When("^(.+): click on (.+)")
    public void clickElement(String pageName, String elementName) {
        Page page = pageSelector(pageName);
        page.getElement(elementName).click();
    }

    private Page pageSelector(String pageName) {
        switch (pageName.toLowerCase().trim()) {
            case "homepage":
                return homePage;
            case "smartwatchespage":
                return smartwatchesPage;
            case "productpage":
                return productPage;
            case "cartpage":
                return cartPage;
            default:
                throw new IllegalArgumentException("Unknown page: " + pageName);
        }
    }

    @And("^pause for (.+) seconds$")
    public void pauseForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000); // Convert seconds to milliseconds
    }


    @Then("^productPage: capture price from productPriceOnProductPage$")
    public void captureProductPrice() {
        productPage.captureProductPrice();
    }

    @Then("^productPage: capture price from feeOnProductPage$")
    public void captureProductFee() {
        productPage.captureFee();
    }

    @Then("^cartPage: capture price from productPriceOnCart$")
    public void captureCartPrice() {
        cartPage.captureCartPrice();
    }

    @Then("^cartPage: capture price from feeOnCart$")
    public void captureCartFee() {
        cartPage.captureCartFee();
    }

    @Then("^Verify that productPrice equals cartPrice$")
    public void verifyPriceConsistency() {
        Assert.assertEquals("The product price does not match the cart price", ProductPage.productPrice, CartPage.cartPrice);
        Assert.assertEquals("The product fee does not match the cart fee", ProductPage.productFee, CartPage.cartFee);
    }

}
