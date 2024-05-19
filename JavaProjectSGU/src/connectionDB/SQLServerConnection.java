package connectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLServerConnection {
//    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=dbDoAnJava";
//    private static final String username = "sa";
//    private static final String password = "kaka3135134162";
//
//    public static Connection getConnection() {
//        try {
//            return DriverManager.getConnection(url, username, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    private static String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=dbDoAnJava;user=sa;password=kaka3135134162;encrypt=false;";

    public static Connection getConnection() {
        Connection conn=null;
        try {
            conn=DriverManager.getConnection(connectionUrl);
            
        } catch (SQLException ex) {
            Logger.getLogger(SQLServerConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ket noi that bai");
        }
        return conn;
    }
    public static void main(String[] args) {
        getConnection();
    }
}
