package elements;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class MessageWidget {
    public void findMessageBySubject(String subject){
        $("body").shouldHave(Condition.text(subject));
    }
}
