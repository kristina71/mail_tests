package tests;

import elements.ComposePopupWidget;
import elements.LoginWidget;
import elements.ProfileWidget;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static utils.RandomUtils.getRandomMessage;
import static utils.RandomUtils.getRandomString;

import pages.LoginPage;
import pages.MailPage;


public class MailTests extends TestBase{

    @Test
    public void UIAuthMailTest(){
        final String url = "https://account.mail.ru/";
        open(url);
        LoginWidget loginWidget= new LoginPage().getLoginWidget();

        final String userName="kristina71-00";
        final String userPassword="obYRtaMPp34*";

        loginWidget.sendLogin(userName);
        loginWidget.clickNextBtn();
        loginWidget.sendPassword(userPassword);
        loginWidget.signIn();

        ProfileWidget profileWidget= new MailPage().getProfileWidget();
        profileWidget.checkName(userName);
    }

    @Test
    public void UIMailSendMessageTest() {
        final String url = "https://account.mail.ru/";
        open(url);
        LoginWidget loginWidget = new LoginPage().getLoginWidget();

        final String userName = "kristina71-00";
        final String userPassword = "obYRtaMPp34*";

        loginWidget.sendLogin(userName);
        loginWidget.clickNextBtn();
        loginWidget.sendPassword(userPassword);
        loginWidget.signIn();

        ComposePopupWidget composePopupWidget= new MailPage().getComposeWidget().clickComposeBtn();

        final String sendTo = "ezikova2018@yandex.ru";
        final String subject =getRandomString(10);
        final String message =getRandomMessage(10,20);

        composePopupWidget.setTo(sendTo);
        composePopupWidget.setSubject(subject);
        composePopupWidget.setMessage(message);
        composePopupWidget.submit();

        final String successText="Письмо отправлено";
        composePopupWidget.checkSuccessMessage(successText);
    }
}
