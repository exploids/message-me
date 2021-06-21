package webeng.data;

import webeng.data.source.JdbcMessageDao;
import webeng.data.source.JdbcUserDao;

public class DaoFactory {
    public static UserDao getUserDao() {
        return new JdbcUserDao();
    }

    public static MessageDao getMessageDao() {
        return new JdbcMessageDao();
    }
}
