package webeng.presentation;

import webeng.transfer.User;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class UserBean implements Serializable {
    private User user;

    /**
     * Gets the user of this user bean.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user of this user bean.
     *
     * @param user the new user
     */
    public void setUser(User user) {
        this.user = user;
    }

    public void login() {

    }

    public void logout() {

    }
}
