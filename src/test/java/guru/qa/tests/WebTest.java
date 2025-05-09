package guru.qa.tests;

import guru.qa.data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class WebTest {

    @BeforeEach
    void setup(){
        open("https://duckduckgo.com/");
    }


    //Один параметр передаем
    @ValueSource(strings = {
            "Selenide", "Junit5", "Anatoliy"
    })
    @ParameterizedTest(name = "Для запроса {0} должно появляться не пустой списсок карточек")
    @DisplayName("Для результата должно появляться не пустой списсок карточек")
    void successfulSearchTest(String searchQuery){

        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $$(".react-results--main li[data-layout='organic']")
                .shouldBe(sizeGreaterThan(5));
    }


    //Два параметра напрямую
    @CsvSource(value = {
            "Selenide, selenide.org/",
            "Junit5, https://junit.org/"
    })
    @ParameterizedTest(name = "Для запроса {0} должна быть ссылка {1}")
    @DisplayName("Для результата должно появляться не пустой списсок карточек")
    void successfulSearchTestExpectedLink(String searchQuery, String link){

        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $$("li[data-layout='organic'] a").get(1)
                .shouldHave(text(link));
    }

    //Два параметра через csv
    @CsvFileSource(resources = "/testdata/newfile.csv")
    @ParameterizedTest(name = "Для запроса {0} должна быть ссылка {1}")
    @DisplayName("Для результата должно появляться не пустой списсок карточек")
    void successfulSearchTestExpectedLinkCsv(String searchQuery, String link){

        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $$("li[data-layout='organic'] a").get(1)
                .shouldHave(text(link));
    }

    @Test
    @DisplayName("Для результата должно появляться не пустой списсок карточек")
    void openGoGoTestPhoto(){

        $("#searchbox_input").setValue("Selenide").pressEnter();
        $("li a").shouldHave(text("Изображения")).click();

        $$("[data-testid='zci-images'] ol")
                .shouldBe(sizeGreaterThan(10));
    }


    @ParameterizedTest
    @EnumSource(Language.class)
    void selenideSiteShoulHaveCorrectText(Language language){
        open("https://selenide.org/");
        $$("#languages a").findBy(text(language.name())).click();
        $(".wrapper-color-content").shouldHave(text(language.description));

    }


    static Stream<Arguments> selenideSiteSgouldDisplayCorrectButtonsTest(){

        return Stream.of(
                Arguments.of(Language.EN, List.of()),
                Arguments.of(Language.RU, List.of())
        );
    }


    @ParameterizedTest
    @MethodSource
    void selenideSiteSgouldDisplayCorrectButtonsTest(Language language, List<String> list){

        open("https://selenide.org/");
        $$("#languages a").findBy(text(language.name())).click();
        $(".wrapper-color-content").shouldHave(text(language.description));
        $$(".main-menu-pages a").filter(visible)
                .shouldHave(texts(list));

    }
}
