package ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class HomePage extends Page {

    public SelenideElement numberOfProductsInCart = $(By.xpath("(//a[@data-ma=\"menu-basket\"]//div)[1]"));
    SelenideElement logoImage = $(By.xpath("(//*[@data-ma=\"menu-logo\"]/*[@id=\"logo-svg\"])[1]|(//*[@data-ma=\"menu-logo\"]/*[@id=\"logo-svg\"])[2]"));
    SelenideElement coockieAcceptButton = $("#didomi-notice-agree-button");
    SelenideElement devicesDropdownMenuButton = $(By.xpath("//button[text()=\"Urządzenia\"]"));
    SelenideElement smartwatchesItem = $(By.xpath("//*[@data-ga-ea=\"nav-links - Urządzenia/Bez abonamentu/Smartwatche\"]"));

    public void openAppAndAcceptCookies() {
        open("https://www.t-mobile.pl/");
        if (coockieAcceptButton.exists()) {
            log.info("Coockies are accepted");
            coockieAcceptButton.click();
        } else {
            log.info("No need to accept cookies");
        }
    }

    @Override
    public String getPageName() {
        return "homePage";
    }
}
