package webeng.data.source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcBase {
    private Connection connection;

    public JdbcBase() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./h2/db", "sa", "sa");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        try {
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists User (" +
                    "name varchar(32) not null," +
                    "password varchar(128) not null," +
                    "description varchar(255) not null," +
                    "primary key (name))");
            statement.execute("create table if not exists Message (" +
                    "sender varchar(32) not null," +
                    "receiver varchar(32) not null," +
                    "time timestamp not null," +
                    "text varchar(255) not null," +
                    "primary key (sender, receiver, time)," +
                    "foreign key (sender) references User (name)," +
                    "foreign key (receiver) references User (name))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Connection getConnection() {
        return connection;
    }
}
