package plantas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Conexion {
    Connection con;
    public Conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/planta1","root","1234");
        } catch (Exception e) {
            System.err.println("Error:" +e);
        }
    }


    public int obtenerUltimoIdMarcas1() {
        int ultimoId = -1; // Valor por defecto si hay un error o la tabla está vacía

        try {
            Statement statement = con.createStatement();
            String consulta = "SELECT MAX(id) as ultimo_id FROM empleados1";
            ResultSet resultSet = statement.executeQuery(consulta);

            if (resultSet.next()) {
                ultimoId = resultSet.getInt("ultimo_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ultimoId;
    }

    public static void main(String[] args) {
        Conexion cn=new Conexion();
        Statement st;
        ResultSet rs;
        try {
            st=cn.con.createStatement();
            rs=st.executeQuery("select * from marcas1");
            while (rs.next()) {
                System.out.println(rs.getInt("id_empleado")+" " +rs.getString("fecha")+ " " +rs.getString("hora_entrada")+" " +rs.getString("hora_salida") );
            }
            int ultimoId = cn.obtenerUltimoIdMarcas1();
            System.out.println("El último ID de la tabla empleados1 es: " + ultimoId);
            cn.con.close();
        } catch (Exception e) {
        }

    }
}
