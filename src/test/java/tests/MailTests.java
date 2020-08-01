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


public class MailTests extends TestBase{

    @Test
    @DisplayName("Login and check userName")
    public void UIAuthMailTest(){
        final String url = "https://account.mail.ru/";
        open(url);
        LoginWidget loginWidget= new LoginPage().getLoginWidget();

        final String userName="kristina71-00";
        final String userPassword="obYRtaMPp34*";

        LoginHelper.signIn(userName,userPassword,loginWidget);

        ProfileWidget profileWidget= new MailPage().getProfileWidget();
        profileWidget.checkName(userName);
    }

    @Test
    @DisplayName("Send message")
    public void UIMailSendMessageTest() {
        final String url = "https://account.mail.ru/";
        open(url);
        LoginWidget loginWidget = new LoginPage().getLoginWidget();

        final String userName = "kristina71-00";
        final String userPassword = "obYRtaMPp34*";

        LoginHelper.signIn(userName,userPassword,loginWidget);

        ComposePopupWidget composePopupWidget= new MailPage().getComposeWidget().clickComposeBtn();

        final String sendTo = "ezikova2018@yandex.ru";
        final String subject =getRandomString(10);
        final String message =getRandomMessage(10,20);

        MailHelper.sendMail(sendTo,subject,message,composePopupWidget);

        final String successText="Письмо отправлено";
        composePopupWidget.checkSuccessMessage(successText);
    }

    @Test
    @DisplayName("Send message and check letter")
    public void UIMailSendMessageAndCheckLetterTest() {
        final String url = "https://account.mail.ru/";
        open(url);
        LoginWidget loginWidget = new LoginPage().getLoginWidget();

        final String userName1 = "kristina71-00";
        final String userPassword1 = "obYRtaMPp34*";

        final String userName2 = "kristina71-11";
        final String userPassword2 = "obYRtaMPp34*";

        LoginHelper.signIn(userName1,userPassword1,loginWidget);

        ProfileWidget profileWidget= new MailPage().getProfileWidget();
        profileWidget.checkName(userName1);

        ComposePopupWidget composePopupWidget = new MailPage().getComposeWidget().clickComposeBtn();

        final String sendTo = String.format("%s@mail.ru",userName2);
        final String subject = getRandomString(10);
        final String message = getRandomMessage(10, 20);

        MailHelper.sendMail(sendTo,subject,message,composePopupWidget);

        final String successText = "Письмо отправлено";
        composePopupWidget.checkSuccessMessage(successText);

        closeBrowser();

        open(url);
        LoginWidget loginWidget2 = new LoginPage().getLoginWidget();
        LoginHelper.signIn(userName2,userPassword2,loginWidget2);

        MailPage mailPage = new MailPage();
        ProfileWidget profileWidget2= mailPage.getProfileWidget();
        profileWidget2.checkName(userName2);

        mailPage.getMessageWidget().findMessageBySubject(subject);
    }

    //Не рабоает =(
    @Test
    @DisplayName("Отправляем (UI) и получаем почту (API)")
    void sendUIReceiveApiMailTest() {
        final String url = "https://account.mail.ru/";
        open(url);
        LoginWidget loginWidget = new LoginPage().getLoginWidget();

        final String userName1 = "kristina71-00";
        final String userPassword1 = "obYRtaMPp34*";

        final String userName2 = "kristina71-11";
        final String userPassword2 = "obYRtaMPp34*";

        LoginHelper.signIn(userName1,userPassword1,loginWidget);

        ProfileWidget profileWidget= new MailPage().getProfileWidget();
        profileWidget.checkName(userName1);

        ComposePopupWidget composePopupWidget = new MailPage().getComposeWidget().clickComposeBtn();

        final String sendTo = String.format("%s@mail.ru",userName2);
        final String subject = getRandomString(10);
        final String message = getRandomMessage(10, 20);

        MailHelper.sendMail(sendTo,subject,message,composePopupWidget);

        final String successText = "Письмо отправлено";
        composePopupWidget.checkSuccessMessage(successText);

        //Не рабоает =(
        new EmailUtils().verifyMailHasMessage(userName2+"@mail.ru", userPassword2, subject);
    }


    //Не рабоает =(
    @Test
    @DisplayName("Отправляем (API) и получаем почту (API)")
    void sendApiAndReceiveApiMailTest() {
        String randomMessage = getRandomMessage(30, 60);

        final String userName1 = "kristina71-00";
        final String userPassword1 = "obYRtaMPp34*";

        final String userName2 = "kristina71-11";
        final String userPassword2 = "obYRtaMPp34*";

        new EmailUtils().sendMail(userName1+"@mail.ru", userPassword1, userName2+"@mail.ru", "", randomMessage);

        new EmailUtils().verifyMailHasMessage(userName2+"@mail.ru", userPassword2, randomMessage);

    }
}
