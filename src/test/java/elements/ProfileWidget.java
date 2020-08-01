package elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProfileWidget {
    private final static String profileLocator = ".x-ph__menu__button__text_auth";

    private SelenideElement findProfileBlock(){
        return $(profileLocator);
    }

    public void checkName(String loginName){
        findProfileBlock().shouldHave(text(loginName));
    }
}
