package tests;

import elements.ComposePopupWidget;
import elements.LoginWidget;
import elements.ProfileWidget;
import helpers.LoginHelper;
import helpers.MailHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MailPage;
import utils.EmailUtils;

import static com.codeborne.selenide.Selenide.open;
import static utils.RandomUtils.getRandomMessage;
import static utils.RandomUtils.getRandomString;

public class MailTests extends TestBase {
  private final String url = "https://account.mail.ru/";

  private final String userName1 = "kristina71-00";
  private final String userPassword1 = "obYRtaMPp34*";

  private final String userName2 = "kristina71-11";
  private final String userPassword2 = "obYRtaMPp34*";

  @Test
  @DisplayName("Login and check userName")
  public void UIAuthMailTest() {
    open(url);
    LoginWidget loginWidget = new LoginPage().getLoginWidget();
    LoginHelper.signIn(userName1, userPassword1, loginWidget);

    ProfileWidget profileWidget = new MailPage().getProfileWidget();
    profileWidget.checkName(userName1);
  }

  @Test
  @DisplayName("Send message")
  public void UIMailSendMessageTest() {
    open(url);
    LoginWidget loginWidget = new LoginPage().getLoginWidget();

    LoginHelper.signIn(userName1, userPassword1, loginWidget);

    ComposePopupWidget composePopupWidget = new MailPage().getComposeWidget().clickComposeBtn();

    final String sendTo = String.format("%s@mail.ru", userName1);
    final String subject = getRandomString(10);
    final String message = getRandomMessage(10, 20);

    MailHelper.sendMail(sendTo, subject, message, composePopupWidget);

    final String successText = "Письмо отправлено";
    composePopupWidget.checkSuccessMessage(successText);
  }

  @Test
  @DisplayName("Send message and check letter")
  public void UIMailSendMessageAndCheckLetterTest() {
    open(url);
    LoginWidget loginWidget = new LoginPage().getLoginWidget();

    LoginHelper.signIn(userName1, userPassword1, loginWidget);

    ProfileWidget profileWidget = new MailPage().getProfileWidget();
    profileWidget.checkName(userName1);

    ComposePopupWidget composePopupWidget = new MailPage().getComposeWidget().clickComposeBtn();

    final String sendTo = String.format("%s@mail.ru", userName2);
    final String subject = getRandomString(10);
    final String message = getRandomMessage(10, 20);

    MailHelper.sendMail(sendTo, subject, message, composePopupWidget);

    final String successText = "Письмо отправлено";
    composePopupWidget.checkSuccessMessage(successText);

    closeBrowser();

    open(url);
    LoginWidget loginWidget2 = new LoginPage().getLoginWidget();
    LoginHelper.signIn(userName2, userPassword2, loginWidget2);

    MailPage mailPage = new MailPage();
    ProfileWidget profileWidget2 = mailPage.getProfileWidget();
    profileWidget2.checkName(userName2);

    mailPage.getMessageWidget().findMessageBySubject(subject);
  }

  // Не рабоает =(
  @Test
  @DisplayName("Отправляем (UI) и получаем почту (API)")
  void sendUIReceiveApiMailTest() {
    open(url);
    LoginWidget loginWidget = new LoginPage().getLoginWidget();

    LoginHelper.signIn(userName1, userPassword1, loginWidget);

    ProfileWidget profileWidget = new MailPage().getProfileWidget();
    profileWidget.checkName(userName1);

    ComposePopupWidget composePopupWidget = new MailPage().getComposeWidget().clickComposeBtn();

    final String sendTo = String.format("%s@mail.ru", userName2);
    final String subject = getRandomString(10);
    final String message = getRandomMessage(10, 20);

    MailHelper.sendMail(sendTo, subject, message, composePopupWidget);

    final String successText = "Письмо отправлено";
    composePopupWidget.checkSuccessMessage(successText);

    // Не рабоает =(
    new EmailUtils().verifyMailHasMessage(sendTo, userPassword2, subject);
  }

  // Не рабоает =(
  @Test
  @DisplayName("Отправляем (API) и получаем почту (API)")
  void sendApiAndReceiveApiMailTest() {
    String randomMessage = getRandomMessage(30, 60);

    new EmailUtils()
        .sendMail(
            String.format("%s@mail.ru", userName1),
            userPassword1,
            String.format("%s@mail.ru", userName2),
            "",
            randomMessage);

    new EmailUtils()
        .verifyMailHasMessage(String.format("%s@mail.ru", userName2), userPassword2, randomMessage);
  }
}
