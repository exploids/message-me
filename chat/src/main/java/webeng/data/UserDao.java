package webeng.data;

import webeng.transfer.User;

import java.util.List;

public interface UserDao {
    User get(String name);

    List<User> getAll();

    void add(User user);

    void update(User user);

    void delete(User user);
}
