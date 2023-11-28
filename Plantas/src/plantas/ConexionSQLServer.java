package plantas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLServer {
    private static final String connectionString =
            "jdbc:sqlserver://DESKTOP-6C4FRHV:1433;"
                    + "database=Corporacion;"
                    + "user=xd;"
                    + "password=1234;"
                    + "encrypt=false;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=30;";

    private ConexionSQLServer() {
    }

    public static Connection getConnection(){
        try {
            Connection connection;
            connection = DriverManager.getConnection(connectionString);
            return connection;
        } catch (SQLException ex) {}
        return null;
    }
}
