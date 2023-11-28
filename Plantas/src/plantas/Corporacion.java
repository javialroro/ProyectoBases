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
    private JTextField plantaTF;

    public Corporacion() {
        setContentPane(mainPanel);
        setTitle("Corporacion");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1250, 500);
        setVisible(true);

        Comprobante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!plantaTF.getText().equals("1") && !plantaTF.getText().equals("2") && !plantaTF.getText().equals("3")) {
                    JOptionPane.showMessageDialog(null, "Ingrese una planta válida", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                getComprobante(Integer.parseInt(plantaTF.getText()));
            }
        });
        Pagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!plantaTF.getText().equals("1") && !plantaTF.getText().equals("2") && !plantaTF.getText().equals("3")) {
                    JOptionPane.showMessageDialog(null, "Ingrese una planta válida", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                pagarPlanilla(Integer.parseInt(plantaTF.getText()));
            }
        });
    }

    public void getComprobante(int planta){
        String str = "";
        try{
            Connection connection = ConexionSQLServer.getConnection();
            Statement statement = null;
            statement = connection.createStatement();
            String selectSql = "";
            switch (planta){
                case 1:
                    selectSql = " EXEC GetComprobanteEmpleado ?, ?";
                    break;
                case 2:
                    selectSql = " EXEC GetComprobanteEmpleado2 ?, ?";
                    break;
                case 3:
                    selectSql = " EXEC GetComprobanteEmpleado3 ?, ?";
                    break;

            }
            // Create and execute a SELECT SQL statement.
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

    public void pagarPlanilla(int planta){
        try{
            Connection connection = ConexionSQLServer.getConnection();
            Statement statement = null;
            statement = connection.createStatement();
            String selectSql = "";

            // Create and execute a SELECT SQL statement.
            switch (planta){
                case 1:
                    selectSql = " EXEC PagarPlanilla ?";
                    break;
                case 2:
                    selectSql = " EXEC PagarPlanilla2 ?";
                    break;
                case 3:
                    selectSql = " EXEC PagarPlanilla3 ?";
                    break;

            }


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
