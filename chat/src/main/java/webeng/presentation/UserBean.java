package webeng.presentation;

import webeng.logic.UserManager;
import webeng.transfer.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
    private UserManager manager;
    private User user;
    private List<User> contacts;
    private String passwordRepeat;
    private boolean loggedIn;

    @PostConstruct
    private void init() {
        manager = new UserManager();
        user = new User();
        contacts = manager.getAll();
    }

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

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * Gets the contacts of this user bean.
     *
     * @return the contacts
     */
    public List<User> getContacts() {
        return contacts;
    }

    /**
     * Sets the contacts of this user bean.
     *
     * @param contacts the new contacts
     */
    public void setContacts(List<User> contacts) {
        this.contacts = contacts;
    }

    public String signUp() {
        manager.add(user);
        loggedIn = true;
        return "ok";
    }

    public String logIn() {
        User authenticatedUser = manager.authenticate(user.getName(), user.getPassword());
        if (authenticatedUser != null) {
            user = authenticatedUser;
            loggedIn = true;
            return "ok";
        }
        return null;
    }

    public String logOut() {
        loggedIn = false;
        return "ok";
    }
}
