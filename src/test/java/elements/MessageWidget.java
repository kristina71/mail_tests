package elements;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

public class MessageWidget {
  public void findMessageBySubject(String subject) {
    $("body").shouldHave(Condition.text(subject));
  }
}
