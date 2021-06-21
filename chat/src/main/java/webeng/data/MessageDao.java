package webeng.data;

import webeng.transfer.Message;

import java.sql.Timestamp;
import java.util.List;

public interface MessageDao {
    Message get(String sender, String receiver, Timestamp time);

    List<Message> getAll();

    void add(Message message);

    void update(Message message);

    void delete(Message message);
}
