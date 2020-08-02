package elements;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class ComposePopupWidget {
  private static final String toLocator = "//*[@data-name='to']//input";
  private static final String subjectLocator = "Subject";
  private static final String messageLocator = ".cke_editable";
  private static final String submitBtn = ".button2_base";
  private static final String successTextLocator = ".layer__link";

  public void setTo(String sendTo) {
    findTo().setValue(sendTo);
  }

  public void setSubject(String subject) {
    findSubject().setValue(subject);
  }

  public void setMessage(String message) {
    findMessage().setValue(message);
  }

  public void submit() {
    findSubmitBtn().click();
  }

  public void checkSuccessMessage(String successText) {
    findSuccessText().shouldHave(text(successText));
  }

  private SelenideElement findTo() {
    return $x(toLocator);
  }

  private SelenideElement findSubject() {
    return $(By.name(subjectLocator));
  }

  private SelenideElement findMessage() {
    return $(messageLocator);
  }

  private SelenideElement findSubmitBtn() {
    return $(submitBtn);
  }

  private SelenideElement findSuccessText() {
    return $(successTextLocator);
  }
}
