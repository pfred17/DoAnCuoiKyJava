package connectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLServerConnection {
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=dbDoAnJava";
    private static final String username = "sa";
    private static final String password = "kaka3135134162";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        getConnection();
    }
}
