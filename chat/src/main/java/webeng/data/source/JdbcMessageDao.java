package webeng.data.source;

import webeng.data.MessageDao;
import webeng.transfer.Message;
import webeng.transfer.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class JdbcMessageDao extends JdbcBase implements MessageDao {
    @Override
    public Message get(String sender, String receiver, Timestamp time) {
        Message message = null;
        try (PreparedStatement statement = getConnection().prepareStatement("select * from Message where = ? and ? and ?")) {
            statement.setString(1, sender);
            statement.setString(2, receiver);
            statement.setTimestamp(3, time);

            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    message = new Message();
                    message.setSender(results.getString(1));
                    message.setReceiver(results.getString(2));
                    message.setTime(results.getTimestamp(3));
                    message.setText(results.getString(4));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return message;
    }

    @Override
    public List<Message> getAll() {
        List<Message> messageliste = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement("select * from Message")) {

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    Message message = new Message();
                    message.setSender(results.getString(1));
                    message.setReceiver(results.getString(2));
                    message.setTime(results.getTimestamp(3));
                    message.setText(results.getString(4));
                    messageliste.add(message);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messageliste;
    }

    @Override
    public void add(Message message) {
        try (PreparedStatement statement = getConnection().prepareStatement("insert into Message values (?, ?, ?, ?)")) {
            statement.setString(1, message.getSender());
            statement.setString(2, message.getReceiver());
            statement.setTimestamp(3, message.getTime());
            statement.setString(4, message.getText());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Message message) {
        try (PreparedStatement statement = getConnection().prepareStatement("update Message set text = ? where sender = ? and receiver = ? and time = ?")) {
            statement.setString(1, message.getText());
            statement.setString(2, message.getSender());
            statement.setString(4, message.getReceiver());
            statement.setTimestamp(3, message.getTime());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Message message) {
        try (PreparedStatement statement = getConnection().prepareStatement("delete from Message where sender = ? and receiver = ? and time = ?")) {
            statement.setString(1, message.getSender());
            statement.setString(2, message.getReceiver());
            statement.setTimestamp(3, message.getTime());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
