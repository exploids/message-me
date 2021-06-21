package webeng.data.source;

import webeng.data.MessageDao;
import webeng.transfer.Message;

import java.sql.Timestamp;
import java.util.List;

public class JdbcMessageDao extends JdbcBase implements MessageDao {
    @Override
    public Message get(String sender, String receiver, Timestamp time) {
        return null;
    }

    @Override
    public List<Message> getAll() {
        return null;
    }

    @Override
    public void add(Message message) {

    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void delete(Message message) {

    }
}
