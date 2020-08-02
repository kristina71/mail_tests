package elements;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class ProfileWidget {
  private static final String profileLocator = ".x-ph__menu__button__text_auth";

  private SelenideElement findProfileBlock() {
    return $(profileLocator);
  }

  public void checkName(String loginName) {
    findProfileBlock().shouldHave(text(loginName));
  }
}
