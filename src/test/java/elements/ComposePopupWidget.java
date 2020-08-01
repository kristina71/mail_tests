package elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ComposePopupWidget {
    private final static String toLocator="//*[@data-name='to']//input";
    private final static String subjectLocator="Subject";
    private final static String messageLocator=".cke_editable";
    private final static String submitBtn=".button2_base";
    private final static String successTextLocator=".layer__link";

    public void setTo(String sendTo){
        findTo().setValue(sendTo);
    }

    public void setSubject(String subject){
        findSubject().setValue(subject);
    }

    public void setMessage(String message){
        findMessage().setValue(message);
    }

    public void submit(){
        findSubmitBtn().click();
    }

    public void checkSuccessMessage(String successText){
        findSuccessText().shouldHave(text(successText));
    }

    private SelenideElement findTo(){
        return $x(toLocator);
    }

    private SelenideElement findSubject(){
        return  $(By.name(subjectLocator));
    }

    private SelenideElement findMessage(){
        return $(messageLocator);
    }

    private SelenideElement findSubmitBtn(){
        return $(submitBtn);
    }

    private SelenideElement findSuccessText(){
        return $(successTextLocator);
    }

}
