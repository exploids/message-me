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
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@RequestScoped
public class DetailBean implements Serializable {

    @ManagedProperty("#{param.name}")
    private String name;
    private User user;
    private List<Message> messages;
    //Inject
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private String message;
    private MessageManager messageManager;

    @PostConstruct
    private void init() {
        messageManager = new MessageManager();
        messages = messageManager.getAll().stream().filter(this::messageConcerns).collect(Collectors.toList());
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

    public void loadUser() {
        UserManager userManager = new UserManager();
        user = userManager.get(name);
    }

    private boolean messageConcerns(Message message) {
        return (message.getSender().equals(userBean.getUser().getName()) && message.getReceiver().equals(name)) ||
                (message.getSender().equals(name) && message.getReceiver().equals(userBean.getUser().getName()));
    }

    public void send() {
        Message bean = new Message();
        bean.setSender(userBean.getUser().getName());
        bean.setReceiver(name);
        bean.setText(message);
        bean.setTime(Timestamp.from(Instant.now()));
        messageManager.add(bean);
        message = null;
        messages = messageManager.getAll().stream().filter(this::messageConcerns).collect(Collectors.toList());
    }

}
