package webeng.data.source;

import webeng.data.UserDao;
import webeng.transfer.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcUserDao extends JdbcBase implements UserDao {
    @Override
    public User get(String name) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void add(User user) {
        try (PreparedStatement statement = getConnection().prepareStatement("insert into User values (?, ?, ?)")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getDescription());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement statement = getConnection().prepareStatement("update User set password = ?, description = ? where name = ?")) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getDescription());
            statement.setString(3, user.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try (PreparedStatement statement = getConnection().prepareStatement("delete from User where name = ?")) {
            statement.setString(1, user.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
