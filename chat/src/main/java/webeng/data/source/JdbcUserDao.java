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

    }

    @Override
    public void update(User user) {
        try (PreparedStatement statement = getConnection().prepareStatement("update user set password = ?, description = ? where name = ?")) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getDescription()); // description
            statement.setString(3, user.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try (PreparedStatement statement = getConnection().prepareStatement("delete from user where name = ?")) {
            statement.setString(1, user.getName());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
