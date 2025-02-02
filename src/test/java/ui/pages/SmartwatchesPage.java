package ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SmartwatchesPage extends Page {

    SelenideElement firstProduct = $(By.xpath("//*[@data-qa='LST_ProductCard0']"));


    @Override
    public String getPageName() {
        return "smartwatchesPage";
    }
}
