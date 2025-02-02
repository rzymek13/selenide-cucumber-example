package ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class HomePage extends Page {

    SelenideElement coockieAcceptButton = $("#didomi-notice-agree-button");
    SelenideElement logoImage = $(By.xpath("(//*[@data-ma=\"menu-logo\"])[1]"));
    SelenideElement devicesDropdownMenuButton = $(By.xpath("//button[text()=\"Urządzenia\"]"));
    SelenideElement smartwatchesItem = $(By.xpath("//*[@data-ga-ea=\"nav-links - Urządzenia/Bez abonamentu/Smartwatche\"]"));



    public void openAppAndAcceptCookies() {
        open("https://www.t-mobile.pl/");
        coockieAcceptButton.click();
    }

    @Override
    public String getPageName() {
        return "homePage";
    }
}
