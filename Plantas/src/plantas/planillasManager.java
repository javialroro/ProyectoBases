package plantas;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;

public class planillasManager extends JFrame{
    private JPanel mainPanel;

    private JPanel panelResult;
    private JLabel labelCalendario;
    private JComboBox calendarios;
    private JComboBox Meses;
    private JTextField quincenaFin;
    private JTextField diaSemana;
    private JTextField semanaFin;
    private JTextField diaQuincena;
    private JButton solicitarPlanilla;
    private JLabel Anio;
    private JTextField anioTF;
    private JButton aceptarPlanillaButton;
    private JButton rechazarPlanillaButton;
    private JTable Table;
    private JTextField idBorrar;
    private JTextField fechaBorrar;
    private JButton borrarMarcaButton;
    private JButton borrarPlanillaButton;
    private HashMap<String, Integer> monthMap;

    int calendario = 0;


    LocalDate fechaInicio;
    LocalDate fechaFinal;

    public planillasManager() {
        calendarios.addItem("Calendario 1");
        calendarios.addItem("Calendario 2");
        calendarios.addItem("Calendario 3");
        calendarios.addItem("Calendario 4");
        calendarios.addItem("Calendario 5");
        Meses.addItem("Enero");
        Meses.addItem("Febrero");
        Meses.addItem("Marzo");
        Meses.addItem("Abril");
        Meses.addItem("Mayo");
        Meses.addItem("Junio");
        Meses.addItem("Julio");
        Meses.addItem("Agosto");
        Meses.addItem("Septiembre");
        Meses.addItem("Octubre");
        Meses.addItem("Noviembre");
        Meses.addItem("Diciembre");
        monthMap = new HashMap<>();
        createHashMap();
        calendarios.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Check if the selected item is "Calendario 5"
                boolean isCalendario1or2 = "Calendario 1".equals(calendarios.getSelectedItem()) || "Calendario 2".equals(calendarios.getSelectedItem());
                boolean isCalendario3or4 = "Calendario 3".equals(calendarios.getSelectedItem()) || "Calendario 4".equals(calendarios.getSelectedItem());
                boolean isCalendario5 = "Calendario 5".equals(calendarios.getSelectedItem());

                // Enable or disable the Meses combo box based on the selected calendar
                if (isCalendario5){
                    calendario = 5;
                    for (ActionListener listener : solicitarPlanilla.getActionListeners()) {
                            solicitarPlanilla.removeActionListener(listener);
                    }
                    solicitarPlanilla.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            planillaMeses();
                        }
                    });
                }

                if (isCalendario1or2){
                    if ("Calendario 1".equals(calendarios.getSelectedItem())){
                        calendario = 1;
                    }
                    if ("Calendario 2".equals(calendarios.getSelectedItem())){
                        calendario = 2;
                    }
                    for (ActionListener listener : solicitarPlanilla.getActionListeners()) {
                        solicitarPlanilla.removeActionListener(listener);
                    }
                    solicitarPlanilla.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            planillaSemanas();
                        }
                    });
                }
                if (isCalendario3or4){
                    if ("Calendario 3".equals(calendarios.getSelectedItem())){
                        calendario = 3;
                    }
                    if ("Calendario 4".equals(calendarios.getSelectedItem())){
                        calendario = 4;
                    }
                    for (ActionListener listener : solicitarPlanilla.getActionListeners()) {
                        solicitarPlanilla.removeActionListener(listener);
                    }
                    solicitarPlanilla.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            planillaQuincena();
                        }
                    });
                }



                diaSemana.setEnabled(isCalendario1or2);
                diaQuincena.setEnabled(isCalendario3or4);
                anioTF.setEnabled(isCalendario5);
                Meses.setEnabled(isCalendario5);
            }
        });
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 1000);
        setVisible(true);

        aceptarPlanillaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = JOptionPane.showInputDialog("Ingrese el ID de la planilla a aceptar:");

                // Verificar si el usuario hizo clic en "Cancelar" o cerró el cuadro de diálogo
                if (inputText != null &&  areDigit(inputText)) {
                    System.out.println("El usuario ingresó: " + inputText);
                    aceptarPlanilla(Integer.parseInt(inputText));
                } else {
                    // El usuario hizo clic en "Cancelar" o cerró el cuadro de diálogo
                    JOptionPane.showMessageDialog(null, "No se ingresó un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        rechazarPlanillaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idBorrar.setEnabled(true);
                fechaBorrar.setEnabled(true);
                borrarMarcaButton.setEnabled(true);

            }
        });
        borrarMarcaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idBorrar.getText().isEmpty() || fechaBorrar.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Ingrese un ID y una fecha", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                borrarMarca();
            }
        });
        borrarPlanillaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = JOptionPane.showInputDialog("Ingrese el ID de la planilla a borrar:");

                // Verificar si el usuario hizo clic en "Cancelar" o cerró el cuadro de diálogo
                if (inputText != null &&  areDigit(inputText)) {
                    System.out.println("El usuario ingresó: " + inputText);
                    borrarPlanilla(Integer.parseInt(inputText));
                } else {
                    // El usuario hizo clic en "Cancelar" o cerró el cuadro de diálogo
                    JOptionPane.showMessageDialog(null, "No se ingresó un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void createHashMap(){
        monthMap.put("Enero", 1);
        monthMap.put("Febrero", 2);
        monthMap.put("Marzo", 3);
        monthMap.put("Abril", 4);
        monthMap.put("Mayo", 5);
        monthMap.put("Junio", 6);
        monthMap.put("Julio", 7);
        monthMap.put("Agosto", 8);
        monthMap.put("Septiembre", 9);
        monthMap.put("Octubre", 10);
        monthMap.put("Noviembre", 11);
        monthMap.put("Diciembre", 12);
    }
    public void planillaMeses(){
        //Table.setText("");
        int mes = monthMap.get(Meses.getSelectedItem());
        if (anioTF.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese un año", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        fechaInicio = LocalDate.of(Integer.parseInt(anioTF.getText()), mes, 1);
        fechaFinal = fechaInicio.withDayOfMonth(fechaInicio.lengthOfMonth());
        System.out.println("Primer día del mes: " + fechaInicio+ "\nÚltimo día del mes: " + fechaFinal);
        Conexion cn = new Conexion();

        try (CallableStatement cst = cn.con.prepareCall("{CALL planta1.`Planilla`(?, ?, ?)}")) {
            cst.setInt(1, calendario);
            cst.setDate(2, java.sql.Date.valueOf(fechaInicio));
            cst.setDate(3, java.sql.Date.valueOf(fechaFinal));

            boolean hasResults = cst.execute();

            if (hasResults) {
                try (ResultSet resultSet = cst.getResultSet()) {
                    Table.setModel(DbUtils.resultSetToTableModel(resultSet));
                }
            }
            aceptarPlanillaButton.setEnabled(true);
            rechazarPlanillaButton.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Planilla solicitada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // No need to retrieve results as the stored procedure doesn't return a ResultSet.
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al solicitar la planilla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    public void planillaSemanas(){
        //Table.setText("");
        LocalDate diaSeleccionado = LocalDate.parse(diaSemana.getText());
        fechaInicio = diaSeleccionado.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        fechaFinal = diaSeleccionado.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        System.out.println("Inicio de la semana: " + fechaInicio+ "\nFin de la semana: " + fechaFinal);
        Conexion cn = new Conexion();

        try (CallableStatement cst = cn.con.prepareCall("{CALL planta1.`Planilla`(?, ?, ?)}")) {
            cst.setInt(1, calendario);
            cst.setDate(2, java.sql.Date.valueOf(fechaInicio));
            cst.setDate(3, java.sql.Date.valueOf(fechaFinal));

            boolean hasResults = cst.execute();

            if (hasResults) {
                try (ResultSet resultSet = cst.getResultSet()) {
                    Table.setModel(DbUtils.resultSetToTableModel(resultSet));
                }
            }
            aceptarPlanillaButton.setEnabled(true);
            rechazarPlanillaButton.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Planilla solicitada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // No need to retrieve results as the stored procedure doesn't return a ResultSet.
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al solicitar la planilla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    public void planillaQuincena() {
        //Table.setText("");
        LocalDate diaSeleccionado = LocalDate.parse(diaQuincena.getText());

        // Calcular la quincena
        int diaMes = diaSeleccionado.getDayOfMonth();


        if (diaMes <= 15) {
            // Primer quincena del mes
            fechaInicio = diaSeleccionado.withDayOfMonth(1);
            fechaFinal  = diaSeleccionado.withDayOfMonth(15);
        } else {
            // Segunda quincena del mes
            fechaInicio= diaSeleccionado.withDayOfMonth(16);
            fechaFinal = diaSeleccionado.with(TemporalAdjusters.lastDayOfMonth());
        }

        System.out.println("Inicio de la quincena: " + fechaInicio + "\nFin de la quincena: " + fechaFinal);

        Conexion cn = new Conexion();

        try (CallableStatement cst = cn.con.prepareCall("{CALL planta1.`Planilla`(?, ?, ?)}")) {
            cst.setInt(1, calendario);
            cst.setDate(2, java.sql.Date.valueOf(fechaInicio));
            cst.setDate(3, java.sql.Date.valueOf(fechaFinal));

            boolean hasResults = cst.execute();

            if (hasResults) {
                try (ResultSet resultSet = cst.getResultSet()) {
                    Table.setModel(DbUtils.resultSetToTableModel(resultSet));
                }
            }
            aceptarPlanillaButton.setEnabled(true);
            rechazarPlanillaButton.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Planilla solicitada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // No es necesario recuperar resultados ya que el procedimiento almacenado no devuelve un ResultSet.
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al solicitar la planilla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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


    void aceptarPlanilla(int idPlanilla) {
        Conexion cn = new Conexion();
        int lineNum = 0;

        try {
            try (PreparedStatement pst = cn.con.prepareStatement("INSERT INTO planta1.planillas (id, fechaInicio, fechaFinal,pagada,calendario) VALUES (?,?, ?, ?,?)")) {
                pst.setInt(1, idPlanilla);
                pst.setDate(2, Date.valueOf(fechaInicio));
                pst.setDate(3, Date.valueOf(fechaFinal));
                pst.setString(4, "N");
                pst.setInt(5, calendario);


                pst.executeUpdate();
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error al ingresar la planilla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
                // Puedes manejar errores específicos si es necesario
            }

            for (int row = 0; row < Table.getRowCount(); row++) {
                String id = Table.getModel().getValueAt(row, 0).toString();
                String nombre = Table.getModel().getValueAt(row, 1).toString();
                String hora_entrada = Table.getModel().getValueAt(row, 2).toString();
                String hora_salida = Table.getModel().getValueAt(row, 3).toString();
                String fecha = Table.getModel().getValueAt(row, 4).toString();
                String horas_laboradas = Table.getModel().getValueAt(row, 5).toString();
                String horas_extra = Table.getModel().getValueAt(row, 6).toString();
                String horas_normales = Table.getModel().getValueAt(row, 7).toString();
                String salario_bruto = Table.getModel().getValueAt(row, 8).toString();
                String salario_neto = Table.getModel().getValueAt(row, 9).toString();

                try (PreparedStatement pst = cn.con.prepareStatement("INSERT INTO planta1.lineplanillas (idPlanilla,lineNum, idEmpleado,nombre, hora_entrada, hora_salida, fecha, horas_laboradas, horas_extra, horas_normales, salario_bruto, salario_neto) VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    pst.setInt(1, idPlanilla);
                    pst.setInt(2, lineNum++);
                    pst.setInt(3, Integer.parseInt(id));
                    pst.setString(4, nombre);
                    pst.setString(5, hora_entrada);
                    pst.setString(6, hora_salida);
                    pst.setString(7, fecha);
                    pst.setString(8, horas_laboradas);
                    pst.setString(9, horas_extra);
                    pst.setString(10, horas_normales);
                    pst.setString(11, salario_bruto);
                    pst.setString(12, salario_neto);


                    pst.executeUpdate();
                } catch (SQLException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error al ingresar la planilla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    // Puedes manejar errores específicos si es necesario
                }
            }

            JOptionPane.showMessageDialog(null, "Todas las planillas aceptadas correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Desactivar botones después de la operación
            aceptarPlanillaButton.setEnabled(false);
            rechazarPlanillaButton.setEnabled(false);
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

    public static boolean areDigit(String str) {
        // Verificar cada carácter en el String
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                // Si encontramos un carácter que no es un dígito, devolvemos false
                return false;
            }
        }
        // Si todos los caracteres son dígitos, devolvemos true
        return true;
    }

    void borrarMarca() {
        Conexion cn = new Conexion();
        try {
            try (PreparedStatement pst = cn.con.prepareStatement("DELETE FROM planta1.marcas1 WHERE id_empleado = ? and fecha = ?" )) {
                pst.setInt(1, Integer.parseInt(idBorrar.getText()));
                pst.setDate(2, Date.valueOf(fechaBorrar.getText()));
                pst.executeUpdate();
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error al borrar la marca: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                // Puedes manejar errores específicos si es necesario
            }
            JOptionPane.showMessageDialog(null, "Marca borrada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Desactivar botones después de la operación
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

    void borrarPlanilla(int idPlanilla) {
        Conexion cn = new Conexion();
        try {
            try (PreparedStatement pst = cn.con.prepareStatement("DELETE FROM planta1.planillas WHERE id = ?  ")) {
                pst.setInt(1, idPlanilla);
                pst.executeUpdate();
                PreparedStatement pst2 = cn.con.prepareStatement("DELETE FROM planta1.lineplanillas WHERE idPlanilla = ?  ");
                pst2.setInt(1, idPlanilla);
                pst2.executeUpdate();
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error al borrar la planilla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                // Puedes manejar errores específicos si es necesario
            }
            JOptionPane.showMessageDialog(null, "Planilla borrada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Desactivar botones después de la operación
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


