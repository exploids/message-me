package webeng.data.source;

import webeng.data.UserDao;
import webeng.transfer.User;

import javax.xml.transform.Result;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao extends JdbcBase implements UserDao {
    @Override
    public User get(String name) {
        User user = null;
        try (PreparedStatement statement = getConnection().prepareStatement("select * from User where name = ?")) {
            statement.setString(1, name);

            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    user = new User();
                    user.setName(results.getString(1));
                    user.setPassword(results.getString(2));
                    user.setDescription(results.getString(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> userliste = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement("select * from User")) {

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    User user = new User();
                    user.setName(results.getString(1));
                    user.setPassword(results.getString(2));
                    user.setDescription(results.getString(3));
                    userliste.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userliste;
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
