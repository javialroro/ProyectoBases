package plantas;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class planta3EmpleadosArchivo {
    public void generarArchivoEmpleados() {
        String archivo = "empleadosPlanta3.txt";
        try {
            Connection con = ConexionSQLServer.getConnection();
            Statement statement = con.createStatement();

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * FROM EMPLEADOS WHERE planta = 3";
            PreparedStatement preparedStatement = con.prepareStatement(selectSql);
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println(rs);
            // Use try-with-resources for FileWriter
            try (FileWriter escribir = new FileWriter(archivo)) {
                escribir.append("id\tnombre\tfecha_ingreso\tfecha_salida\ttipo\tdepartamento\tid_supervisor\n");

                // Print results from select statement
                while (rs.next()) {
                    escribir.append(rs.getString(1)).append("\t");
                    escribir.append(rs.getString(2)).append("\t");
                    escribir.append(rs.getString(3)).append("\t");
                    escribir.append(rs.getString(4)).append("\t");
                    escribir.append(rs.getString(5)).append("\t");
                    escribir.append(rs.getString(6)).append("\t");
                    escribir.append(rs.getString(7)).append("\n");
                }
                System.out.println("Datos exportados a " + archivo);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
