package webeng.logic;

import webeng.data.DaoFactory;
import webeng.data.UserDao;
import webeng.transfer.User;

import java.util.List;

public class UserManager {
    private UserDao dao;

    public UserManager() {
        dao = DaoFactory.getUserDao();
    }

    public User get(String name) {
        return dao.get(name);
    }

    public List<User> getAll() {
        return dao.getAll();
    }

    public void add(User user) {
        dao.add(user);
    }

    public void update(User user) {
        dao.update(user);
    }

    public void delete(User user) {
        dao.delete(user);
    }

    public User authenticate(String name, String password) {
        User realUser = get(name);
        if (realUser != null && realUser.getPassword().equals(password)) {
            return realUser;
        } else {
            return null;
        }
    }
}
