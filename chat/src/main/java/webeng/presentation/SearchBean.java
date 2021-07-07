package webeng.presentation;

import webeng.logic.MessageManager;
import webeng.logic.UserManager;
import webeng.transfer.Message;
import webeng.transfer.User;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        MessageManager messageManager = new MessageManager();
        Set<String> names = messageManager.getAll().stream()
                .filter(message -> message.getSender().equals(userBean.getUser().getName()) || message.getReceiver().equals(userBean.getUser().getName()))
                .map(message -> message.getSender().equals(userBean.getUser().getName()) ? message.getReceiver() : message.getSender())
                .collect(Collectors.toSet());
        contacts = manager.getAll().stream().filter(user -> names.contains(user.getName())).collect(Collectors.toList());
    }

    public void search() {
        System.out.println(query);
        if(!query.isEmpty()) {
            contacts = manager.getAll().stream()
                    .filter(contact -> contact.getName().contains(query) && !contact.getName().equals(userBean.getUser().getName()))
                    .collect(Collectors.toList());
        }
    }

}
