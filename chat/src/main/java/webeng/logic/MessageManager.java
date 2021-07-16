package webeng.logic;

import webeng.data.DaoFactory;
import webeng.data.MessageDao;
import webeng.transfer.Message;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

public class MessageManager {
    private final MessageDao dao;

    public MessageManager() {
        dao = DaoFactory.getMessageDao();
    }

    public List<Message> getAll() {
        return dao.getAll();
    }

    public void add(Message message) {
        dao.add(message);
    }

    /**
     * Sends a message.
     *
     * @param sender   the sender
     * @param receiver the receiver
     * @param text     the text
     * @return a list of all current messages
     */
    public List<Message> send(String sender, String receiver, String text) {
        Message bean = new Message();
        bean.setSender(sender);
        bean.setReceiver(receiver);
        bean.setText(text);
        bean.setTime(Timestamp.from(Instant.now().atZone(ZoneId.of("Europe/Berlin")).toLocalDateTime().toInstant(ZoneOffset.UTC)));
        add(bean);
        return getFor(sender, receiver);
    }

    /**
     * Gets all messages between two users.
     *
     * @param name1 the first user name
     * @param name2 the second use name
     * @return the list of messages
     */
    public List<Message> getFor(String name1, String name2) {
        return getAll().stream().filter(message -> concerns(message, name1, name2)).collect(Collectors.toList());
    }

    /**
     * Checks, whether a message concerns two users.
     *
     * @param message the message
     * @param name1   the first user name
     * @param name2   the second user name
     * @return <code>true</code>, if the message concerns both users
     */
    private boolean concerns(Message message, String name1, String name2) {
        return (message.getSender().equals(name1) && message.getReceiver().equals(name2)) ||
                (message.getSender().equals(name2) && message.getReceiver().equals(name1));
    }
}
