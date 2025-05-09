package guru.qa.tests;

import guru.qa.data.Language;
import guru.qa.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

@DisplayName("Тесты на странице авторизации")
public class BccAuthPageTests extends TestBase{

    MainPage mainPage = new MainPage();

    @ParameterizedTest(name = "Для языка {0} приветственный текст - {1}")
    @MethodSource("guru.qa.data.TestData#MainPageChoiceLanguageTest")
    @Tag("SMOKE")
    void MainPageChoiceLanguageTest(Language language, List<String> list){

        mainPage.openPage()
                .clickLenguage(language)
                .checkNessuseryText(list);
    }
}
