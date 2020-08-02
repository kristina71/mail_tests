package tests;

import static com.codeborne.selenide.Selenide.closeWebDriver;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
  @BeforeAll
  public static void setUp() {
    SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true));
  }

  @AfterEach
  public void closeBrowser() {
    closeWebDriver();
  }
}
