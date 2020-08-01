package helpers;

import elements.LoginWidget;

public class LoginHelper {
    public static void signIn(String userName, String userPassword, LoginWidget loginWidget){
        loginWidget.sendLogin(userName);
        loginWidget.clickNextBtn();
        loginWidget.sendPassword(userPassword);
        loginWidget.signIn();

    }
}
