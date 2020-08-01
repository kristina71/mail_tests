package pages;

import elements.ComposeWidget;
import elements.ProfileWidget;

public class MailPage {
    public ProfileWidget getProfileWidget(){
       return new ProfileWidget();
    }

    public ComposeWidget getComposeWidget(){
        return new ComposeWidget();
    }
}