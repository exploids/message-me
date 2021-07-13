package webeng.presentation;

import webeng.logic.MessageManager;
import webeng.logic.UserManager;
import webeng.transfer.Message;
import webeng.transfer.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class DetailBean implements Serializable {
    @ManagedProperty("#{param.name}")
    private String name;
    private User user;
    private List<Message> messages;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private String message;
    private MessageManager messageManager;

    @PostConstruct
    private void init() {
        UserManager userManager = new UserManager();
        user = userManager.get(name);
        messageManager = new MessageManager();
        messages = messageManager.getFor(userBean.getUser().getName(), name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public void send() {
        messages = messageManager.send(userBean.getUser().getName(), name, message);
        message = null;
    }
}
