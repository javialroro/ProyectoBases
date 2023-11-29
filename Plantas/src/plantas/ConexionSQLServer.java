package plantas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLServer {
    private static final String connectionString =
            "jdbc:sqlserver://JAVITO:1433;"
                    + "database=Corporacion;"
                    + "user=JAVI;"
                    + "password=1234;"
                    + "encrypt=false;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=30;";

    ConexionSQLServer() {
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
