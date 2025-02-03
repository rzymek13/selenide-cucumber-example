package ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class CartPage extends Page {
    public static String cartPrice;
    public static String cartFee;

    SelenideElement addToCartButton = $(By.xpath("(//button[@data-qa=\"PRD_AddToBasket\"])[2]"));
    SelenideElement cartTitle = $(By.xpath("//h1"));
    SelenideElement productPriceOnCart = $(By.xpath("//*[@data-qa=\"BKT_TotalupFront\"]"));
    SelenideElement feeOnCart = $(By.xpath("//*[@data-qa=\"BKT_TotalMonthly\"]"));

    @Override
    public String getPageName() {
        return "cartPage";
    }

    public void captureCartPrice() {
        cartPrice = productPriceOnCart.getText().replaceAll("[^\\d.]", "");
        log.info("Captured product price in cart: " + cartPrice);
    }

    public void captureCartFee() {
        cartFee = feeOnCart.getText().replaceAll("[^\\d.]", "");
        log.info("Captured fee in cart: " + cartFee);
    }
}
