package webeng.logic;

import webeng.data.DaoFactory;
import webeng.data.MessageDao;
import webeng.transfer.Message;

import java.sql.Timestamp;
import java.util.List;

public class MessageManager {
    private MessageDao dao;

    public MessageManager() {
        dao = DaoFactory.getMessageDao();
    }

    public Message get(String sender, String receiver, Timestamp time) {
        return dao.get(sender, receiver, time);
    }

    public List<Message> getAll() {
        return dao.getAll();
    }

    public void add(Message message) {
        dao.add(message);
    }

    public void update(Message message) {
        dao.update(message);
    }

    public void delete(Message message) {
        dao.delete(message);
    }
}
