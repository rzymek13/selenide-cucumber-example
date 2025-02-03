package ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;

import static com.codeborne.selenide.Condition.visible;


@Slf4j
public abstract class Page {
    WebDriver driver;

    public SelenideElement getElement(String elementName) {
        try {
            Field field = this.getClass().getDeclaredField(elementName);
            field.setAccessible(true);
            Object value = field.get(this);
            if (value instanceof SelenideElement) {
                return ((SelenideElement) value).shouldBe(visible);
            } else {
                throw new IllegalArgumentException("Field " + elementName + " is not a SelenideElement");
            }
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Element " + elementName + " not found on " + this.getClass().getSimpleName());
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to access element " + elementName, e);
        }
    }


    public abstract String getPageName();


}
