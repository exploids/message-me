package webeng.presentation;

import webeng.logic.UserManager;
import webeng.transfer.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class SearchBean implements Serializable {
    private UserManager manager;
    private String query;
    private List<User> contacts;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

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

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    @PostConstruct
    private void init() {
        manager = new UserManager();
        contacts = manager.getContacts(userBean.getUser().getName());
    }

    public void search() {
        if (!query.isEmpty()) {
            contacts = manager.search(query, userBean.getUser().getName());
        }
    }
}
