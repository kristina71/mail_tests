package elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginWidget {
    private final static String userNameLocator = "username";
    private final static String userPasswordLocator = "password";
    private final static String submitBtnLocator="//*[@data-test-id='submit-button']";
    private final static String nextBtnLocator="//*[@data-test-id='next-button']";

    private SelenideElement findUserName(){
        return $(By.name(userNameLocator));
    }

    private SelenideElement findUserPassword(){
        return $(By.name(userPasswordLocator));
    }

    private SelenideElement findSubmitBtn(){
        return $x(submitBtnLocator);
    }

    private SelenideElement findNextBtn(){
        return $x(nextBtnLocator);
    }

    public void sendLogin(String userName){
        findUserName().setValue(userName);
    }

    public void sendPassword(String userPassword){
        findUserPassword().setValue(userPassword);
    }

    public void signIn(){ findSubmitBtn().click(); }

    public void clickNextBtn(){
        findNextBtn().click();
    }
}
