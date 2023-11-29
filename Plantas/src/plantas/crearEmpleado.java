package plantas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class crearEmpleado {
    private JLabel nombre;
    private JTextField nombreTF;
    private JTextField idTF;
    private JTextField fechaEntradaTF;
    private JTextField fechaSalidaTF;
    private JTextField tipoTF;
    private JTextField departamentoTf;
    private JTextField supervisorTF;
    private JPanel mainPanel;
    private JTextField PlantaTF;
    private JButton crearEmpleadoButton;

    public crearEmpleado() {
        JFrame frame = new JFrame("Crear Empleado");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        crearEmpleadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearEmpleado();
            }
        });
    }

    public void crearEmpleado() {
        String nombre = nombreTF.getText();
        String id = idTF.getText();
        String fechaEntrada = fechaEntradaTF.getText();
        String fechaSalida = fechaSalidaTF.getText();
        String tipo = tipoTF.getText();
        String departamento = departamentoTf.getText();
        String supervisor = supervisorTF.getText();
        String planta = PlantaTF.getText();
        if (fechaSalida.equals("")){
            fechaSalida = null;
        }

        ConexionSQLServer conexion = new ConexionSQLServer();
        try{
            Connection connection = ConexionSQLServer.getConnection();
            Statement statement = null;
            statement = connection.createStatement();
            String insertSql = "INSERT INTO Empleados (id, nombre, fecha_ingreso, fecha_salida, tipo, departamento, id_supervisor, planta) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, fechaEntrada);
            if (fechaSalida == null){
                preparedStatement.setNull(4, Types.DATE);
            }else {
                preparedStatement.setString(4, fechaSalida);
            }
            preparedStatement.setString(5, tipo);
            preparedStatement.setString(6, departamento);
            preparedStatement.setString(7, supervisor);
            preparedStatement.setString(8, planta);
            preparedStatement.executeUpdate();


    } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear empleado: "+e, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Conexion cn = new Conexion();
        String str = "";
        switch(planta) {
            case "1":
                str = "INSERT INTO planta1.empleados1 (id, nombre, fecha_ingreso, fecha_salida, tipo, departamento, id_supervisor) VALUES (?, ?, ?, ?, ?, ?, ?)";
                break;
            case "2":
                str = "INSERT INTO planta2.empleados1 (id, nombre, fecha_ingreso, fecha_salida, tipo, departamento, id_supervisor) VALUES (?, ?, ?, ?, ?, ?, ?)";
                break;
            default:
                JOptionPane.showMessageDialog(null, "Ingrese una planta válida", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        try (PreparedStatement preparedStatement = cn.con.prepareStatement(str)) {
            // Set parameters for the insertion
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, fechaEntrada);
            if (fechaSalida == null){
                preparedStatement.setNull(4, Types.DATE);
            }else {
                preparedStatement.setString(4, fechaSalida);
            }
            preparedStatement.setString(5, tipo);
            preparedStatement.setString(6, departamento);
            preparedStatement.setString(7, supervisor);

            // Execute the insertion
            preparedStatement.executeUpdate();

            // Inform the user about the successful insertion
            JOptionPane.showMessageDialog(null, "Inserción realizada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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


}
