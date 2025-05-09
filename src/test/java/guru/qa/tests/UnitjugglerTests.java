package guru.qa.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class UnitjugglerTests{

    @CsvSource(value = {
            "1, 0.39370078740157",
            "10, 3.9370078740157",
            "100, 39.370078740157",
            "1000, 393.70078740157"
    })

    @ParameterizedTest(name = "{0} см = {1} дюймов")
    @DisplayName("Для результата должно получится корректное значение")
    @Tag("WEB")
    void converterTest(String valueCm, String valueIn){

        open("https://www.unitjuggler.com/%D0%BF%D0%B5%D1%80%D0%B5%D0%B2%D0%BE%D0%B4-length-%D0%B8%D0%B7-cm-%D0%B2-in.html");

        $(".converter").setValue(valueCm).pressEnter();
        $("#dFormValueRight").shouldHave(text(valueIn));
    }


    @CsvFileSource(resources = "/testdata/newfile.csv")
    @ParameterizedTest
    @Tag("WEB")
    void loveRadioHeaderNavMenuTest(String menuItem, String menuItemValue){
        open("https://www.loveradio.ru/");
        $$(".header-nav__menu-item").findBy(text(menuItem)).hover();
        $$("ul .header-nav__submenu li").filter(visible).get(0)
                .shouldHave(text(menuItemValue));
    }


}
