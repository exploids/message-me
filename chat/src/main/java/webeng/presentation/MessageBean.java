package webeng.presentation;

import webeng.logic.MessageManager;
import webeng.transfer.Message;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class MessageBean implements Serializable {
    private MessageManager manager;
    private List<Message> messageList;

    @PostConstruct
    private void init() {
        manager = new MessageManager();
        messageList = manager.getAll();
    }

    /**
     * Gets the messages of the message bean
     * @return the list of massages
     */
    public List<Message> getMessageList() {
        return messageList;
    }

    /**
     * Sets the messages of the message bean
     * @param messageList sets new messages
     */
    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
