package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Creates and closes a connection to the postgres DB
 */

public class ConnectionFactory {

    public static Connection try_connect() {
        Connection connection;
        connection = null;
        String host = "localhost";
        String port = "5432";
        String db_name = "postgres";
        String username = "postgres";
        String password = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name + "", "" + username + "", "" + password + "");
            if (connection != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        try {
            connection.close();
            System.out.println("connection closed");
        }
        catch(Exception e){
            System.out.println("couldn't close connection");
        }
    }
}
