package helpers;

import elements.ComposePopupWidget;

public class MailHelper {
    public static void sendMail(String sendTo, String subject, String message, ComposePopupWidget composePopupWidget){
        composePopupWidget.setTo(sendTo);
        composePopupWidget.setSubject(subject);
        composePopupWidget.setMessage(message);
        composePopupWidget.submit();
    }
}
