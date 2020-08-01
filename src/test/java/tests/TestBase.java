package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
        @BeforeAll
        public static void setUp() {
        }

        @AfterEach
        public void closeBrowser(){
            closeWebDriver();
        }
}
