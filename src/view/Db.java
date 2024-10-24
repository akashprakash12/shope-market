package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/shope_market";
    private static final String USRNAME_STRING = "root";
    private static final String PASSWORD_STRING = "root";
    public static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(JDBC_URL, USRNAME_STRING, PASSWORD_STRING);
                System.out.println("connected");
            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }

        }

        return connection;
    }

}
