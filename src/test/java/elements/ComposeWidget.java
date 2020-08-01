package elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ComposeWidget {

    private final static String composeLocator=".compose-button";

    private SelenideElement findComposeLocator(){
            return $(composeLocator);
        }

     public ComposePopupWidget clickComposeBtn(){
        findComposeLocator().click();
        return new ComposePopupWidget();
    }
}
