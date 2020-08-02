package elements;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class ComposeWidget {

  private static final String composeLocator = ".compose-button";

  private SelenideElement findComposeLocator() {
    return $(composeLocator);
  }

  public ComposePopupWidget clickComposeBtn() {
    findComposeLocator().click();
    return new ComposePopupWidget();
  }
}
