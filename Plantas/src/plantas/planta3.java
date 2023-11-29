package plantas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class planta3 extends JFrame{
    private JButton marcarSalida;
    private JLabel nombre;
    private JPanel mainPanel;
    private JTextField idTF;
    private JTextField horaTF;
    private JTextField fechaTF;
    private JButton marcarEntrada;
    private JLabel idLabel;
    private JLabel fechaLabel;
    private JLabel horaLabel;

    public planta3() {
        setContentPane(mainPanel);
        setTitle("Planta 3");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        marcarSalida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello World");
            }
        });
        marcarEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                marcarEntrada();
            }
        });
        marcarSalida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                marcarSalida();
            }
        });
    }

    public void marcarEntrada() {
        String id = idTF.getText();
        String fecha = fechaTF.getText();
        String hora = horaTF.getText();
        Conexion cn = new Conexion();

        try (CallableStatement cst = cn.con.prepareCall("{CALL planta3.`MARCAR ENTRADA`(?, ?, ?)}")) {
            cst.setInt(1, Integer.parseInt(id));
            cst.setDate(2, java.sql.Date.valueOf(fecha));
            cst.setTime(3, java.sql.Time.valueOf(hora));

            cst.execute();
            JOptionPane.showMessageDialog(null, "Entrada marcada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // No need to retrieve results as the stored procedure doesn't return a ResultSet.
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al marcar la entrada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + e);
        } finally {
            try {
                if (cn.con != null) {
                    cn.con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void marcarSalida() {
        String id = idTF.getText();
        String fecha = fechaTF.getText();
        String salida = horaTF.getText();
        Conexion cn = new Conexion();

        try (CallableStatement cst = cn.con.prepareCall("{CALL planta3.`MARCAR_SALIDA`(?, ?, ?)}")) {
            cst.setInt(1, Integer.parseInt(id));
            cst.setDate(2, java.sql.Date.valueOf(fecha));
            cst.setTime(3, java.sql.Time.valueOf(salida));

            boolean hasResultSet = cst.execute();

            if (hasResultSet) {
                ResultSet rs = cst.getResultSet();
                if (rs.next()) {
                    // Check the result from the stored procedure
                    String resultMessage = rs.getString(1);
                    JOptionPane.showMessageDialog(null, resultMessage);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Salida marcada con éxito.");
            }

        } catch (SQLException | NumberFormatException e) {
            // Show an error message
            JOptionPane.showMessageDialog(null, "Error al marcar la salida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + e);
        } finally {
            try {
                if (cn.con != null) {
                    cn.con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
