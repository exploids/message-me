package webeng.presentation;

import webeng.logic.UserManager;
import webeng.transfer.User;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@RequestScoped
public class SearchBean implements Serializable {

    private UserManager manager;
    private String query;
    private List<User> contacts;

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

    @PostConstruct
    private void init() {
        manager = new UserManager();
        contacts = manager.getAll();
    }

    public void search() {
        System.out.println(query);
        contacts = contacts.stream().filter(contact -> contact.getName().contains(query)).collect(Collectors.toList());
    }



}
