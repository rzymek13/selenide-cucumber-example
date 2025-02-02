package ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class ProductPage extends Page{

     public static String productPrice;
     public static String productFee;

    SelenideElement productPriceOnProductPage = $(By.xpath("(//*[text()=\"Do zapłaty na start\"]/following::div[contains(text(),'zł')])[3]"));
    SelenideElement feeOnProductPage = $(By.xpath("(//*[text()=\"Do zapłaty miesięcznie\"]/following::div[contains(text(),'zł')])[3]"));
    SelenideElement productLabel = $(By.xpath("//*[@data-qa=\"PRD_ProductName\"]"));
    SelenideElement addToCartButton = $(By.xpath("(//button[@data-qa=\"PRD_AddToBasket\"])[2]"));
    @Override
    public String getPageName() {
        return "productPage";
    }
    public void captureProductPrice() {
        productPrice = productPriceOnProductPage.getText().replaceAll("[^\\d.]", "");
        log.info("Captured product price on product page: " + productPrice);
    }

    public void captureFee() {
        productFee = feeOnProductPage.getText().replaceAll("[^\\d.]", "");
        log.info("Captured fee on product page: " + productFee);
    }

}
