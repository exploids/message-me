package webeng.logic;

import webeng.data.DaoFactory;
import webeng.data.UserDao;
import webeng.transfer.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserManager {
    private final UserDao dao;
    private final MessageManager messageManager;

    public UserManager() {
        dao = DaoFactory.getUserDao();
        messageManager = new MessageManager();
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

    /**
     * Gets a list of contacts for a specific user.
     *
     * @param name the name of the user
     * @return the list of contacts
     */
    public List<User> getContacts(String name) {
        Set<String> names = messageManager.getAll().stream()
                .filter(message -> message.getSender().equals(name) || message.getReceiver().equals(name))
                .map(message -> message.getSender().equals(name) ? message.getReceiver() : message.getSender())
                .collect(Collectors.toSet());
        return getAll().stream().filter(user -> names.contains(user.getName())).collect(Collectors.toList());
    }

    /**
     * Searches for user.
     *
     * @param query   the search query
     * @param exclude the name of a user to exclude from the results
     * @return the list of results
     */
    public List<User> search(String query, String exclude) {
        return getAll().stream()
                .filter(contact -> contact.getName().contains(query) && !contact.getName().equals(exclude))
                .collect(Collectors.toList());
    }
}
