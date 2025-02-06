package ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class HomePage extends Page {

    private static final String HOME_URL = "https://www.t-mobile.pl/";

    public SelenideElement numberOfProductsInCart = $(By.xpath("//div[@class=\"ml-auto flex lg:mt-auto group-[.shrank-header]/header:lg:mt-0\"]//a[@data-ma=\"menu-basket\"]//div"));
    SelenideElement logoImage = $(By.xpath("//div[@class=\"container mx-auto lg:px-6 max-lg:hidden\"]//*[@id=\"logo-svg\"]"));
    SelenideElement coockieAcceptButton = $("#didomi-notice-agree-button");
    SelenideElement devicesDropdownMenuButton = $(By.xpath("//button[text()=\"Urządzenia\"]"));
    SelenideElement smartwatchesItem = $(By.xpath("//*[@data-ga-ea=\"nav-links - Urządzenia/Bez abonamentu/Smartwatche\"]"));

    public void openAppAndAcceptCookies() {
        open(HOME_URL);
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
