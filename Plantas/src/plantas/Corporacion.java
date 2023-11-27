package plantas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Corporacion extends JFrame{
    private JPanel mainPanel;
    private JTextField planillaTF;
    private JTextField idTF;
    private JButton Comprobante;
    private JTextArea ComprobanteText;
    private JButton Pagar;

    public Corporacion() {
        setContentPane(mainPanel);
        setTitle("Corporacion");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1250, 500);
        setVisible(true);

        Comprobante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getComprobante();
            }
        });
        Pagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagarPlanilla();
            }
        });
    }

    public void getComprobante(){
        String str = "";
        try{
            Connection connection = ConexionSQLServer.getConnection();
            Statement statement = null;
            statement = connection.createStatement();

            // Create and execute a SELECT SQL statement.
            String selectSql = "EXEC GetComprobanteEmpleado ?, ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);

            // Set parameters for the stored procedure.
            preparedStatement.setString(2, idTF.getText());
            preparedStatement.setString(1, planillaTF.getText());

            System.out.println("id: "+idTF.getText());
            System.out.println("planilla: "+planillaTF.getText());

            // Execute the stored procedure.
            ResultSet resultSet = preparedStatement.executeQuery();


            // Print results from select statement
            while (resultSet.next()) {
                str += resultSet.getString(1) +"\t" +
                        resultSet.getString(2) +"\t" +
                        resultSet.getString(3) +"\t" +
                        resultSet.getString(4) +"\t" +
                        resultSet.getString(5) + "\t" +
                        resultSet.getString(6) +"\t" +
                        resultSet.getString(7) +"\t" +
                        resultSet.getString(8) +"\t" +
                        resultSet.getString(9) +"\t" +
                        resultSet.getString(10) +"\t" +
                        resultSet.getString(11) +"\t" +
                        resultSet.getString(12)
                        +"\n";

            }
            ComprobanteText.setText(str);
            System.out.println("Resultado: "+str);
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void pagarPlanilla(){
        try{
            Connection connection = ConexionSQLServer.getConnection();
            Statement statement = null;
            statement = connection.createStatement();

            // Create and execute a SELECT SQL statement.
            String selectSql = " EXEC PagarPlanilla ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);

            // Set parameters for the stored procedure.
            preparedStatement.setString(1, planillaTF.getText());

            // Execute the stored procedure.
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Planilla pagada", "Exito", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
