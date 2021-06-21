package webeng.data.source;

import webeng.data.UserDao;
import webeng.transfer.User;

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

    }

    @Override
    public void delete(User user) {

    }
}
