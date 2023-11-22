package plantas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
            cn.con.close();
        } catch (Exception e) {
        }

    }
}
