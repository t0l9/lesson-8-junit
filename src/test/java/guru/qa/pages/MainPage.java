package guru.qa.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import guru.qa.data.Language;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final String pageUrl = "/";
    private final SelenideElement lenguageClick = $(".ant-select-selection-item");
    private final SelenideElement h4 = $(".loginBlock h4");
    private final SelenideElement antFormItemLabel = $(".ant-form-item-label");
    private final ElementsCollection lenguageOptionClick = $$(".ant-select-item-option-content");
    private final SelenideElement loginFormBtnText = $(".login-form__login-btn");

    public MainPage openPage(){
        open(pageUrl);
        return this;
    }

    public MainPage clickLenguage(Language language){
        lenguageClick.click();
        lenguageOptionClick.findBy(Condition.text(language.description)).click();
        return this;
    }

    public MainPage checkNessuseryText(List<String> list){
        h4.shouldHave(text(list.getFirst()));
        antFormItemLabel.shouldHave(text(list.get(2)));
        loginFormBtnText.shouldHave(text(list.get(1)));
        return this;
    }


}
