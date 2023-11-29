package plantas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileWriter;
import java.io.IOException;
public class planillasArchivo {
    public void guardarPlanillasArchivos()  {
            String archivo1 = "lineasPlanillas.txt";
            String archivo2 = "planillas.txt";
            String query2 = "SELECT * FROM planta3.planillas;";
            String query1 = "SELECT * FROM planta3.lineplanillas;";


            try (Connection  con= DriverManager.getConnection("jdbc:mysql://localhost:3306/planta3","root","1234");
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(query1);
                 FileWriter escribir = new FileWriter(archivo1)) {

                escribir.append("idPlanilla\tlineNum\tidEmpleado\tnombre\thora_entrada\thora_salida\tfecha\thoras_laboradas\thoras_extra\thoras_normales\tsalario_bruto\tsalario_neto\n");


                while (rs.next()) {
                    escribir.append(rs.getString("idPlanilla")).append("\t");
                    escribir.append(rs.getString("lineNum")).append("\t");
                    escribir.append(rs.getString("idEmpleado")).append("\t");
                    escribir.append(rs.getString("nombre")).append("\t");
                    escribir.append(rs.getString("hora_entrada")).append("\t");
                    escribir.append(rs.getString("hora_salida")).append("\t");
                    escribir.append(rs.getString("fecha")).append("\t");
                    escribir.append(rs.getString("horas_laboradas")).append("\t");
                    escribir.append(rs.getString("horas_extra")).append("\t");
                    escribir.append(rs.getString("horas_normales")).append("\t");
                    escribir.append(rs.getString("salario_bruto")).append("\t");
                    escribir.append(rs.getString("salario_neto")).append("\n");


                }
                System.out.println("Datos exportados a " + archivo1);
            }


            catch (SQLException | IOException e) {
                e.printStackTrace();
            }

        try (Connection  con= DriverManager.getConnection("jdbc:mysql://localhost:3306/planta3","root","1234");
             Statement st = con.createStatement();
             ResultSet rs2 = st.executeQuery(query2);
             FileWriter escribir2 = new FileWriter(archivo2)) {


            escribir2.append("id\tfechaInicio\tfechaFinal\tpagada\n");


            while (rs2.next()) {
                escribir2.append(rs2.getString("id")).append("\t");
                escribir2.append(rs2.getString("fechaInicio")).append("\t");
                escribir2.append(rs2.getString("fechaFinal")).append("\t");
                escribir2.append(rs2.getString("pagada")).append("\n");



            }
            System.out.println("Datos exportados a " + archivo2);
        }


        catch (SQLException | IOException e) {
            e.printStackTrace();
        }



    }


}

