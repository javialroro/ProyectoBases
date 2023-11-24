package plantas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private JTextArea result;
    private HashMap<String, Integer> monthMap;

    int calendario = 0;


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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setVisible(true);

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
        result.setText("");
        int mes = monthMap.get(Meses.getSelectedItem());
        if (anioTF.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese un año", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalDate primerDiaDelMes = LocalDate.of(Integer.parseInt(anioTF.getText()), mes, 1);
        LocalDate ultimoDiaDelMes = primerDiaDelMes.withDayOfMonth(primerDiaDelMes.lengthOfMonth());
        System.out.println("Primer día del mes: " + primerDiaDelMes+ "\nÚltimo día del mes: " + ultimoDiaDelMes);
        Conexion cn = new Conexion();

        try (CallableStatement cst = cn.con.prepareCall("{CALL planta1.`Planilla`(?, ?, ?)}")) {
            cst.setInt(1, calendario);
            cst.setDate(2, java.sql.Date.valueOf(primerDiaDelMes));
            cst.setDate(3, java.sql.Date.valueOf(ultimoDiaDelMes));

            boolean hasResults = cst.execute();

            if (hasResults) {
                try (ResultSet resultSet = cst.getResultSet()) {
                    // Process the ResultSet as needed
                    while (resultSet.next()) {
                        // Access data from the ResultSet
                        int column1 = resultSet.getInt("id");
                        String column2 = resultSet.getString("nombre");
                        String column3 = resultSet.getString("hora_entrada");
                        String column4 = resultSet.getString("hora_salida");
                        String column5 = resultSet.getString("fecha");
                        String column6 = resultSet.getString("horas_laboradas");
                        String column7 = resultSet.getString("horas_extra");
                        String column8 = resultSet.getString("horas_laborables");
                        String column9 = resultSet.getString("salario");

                        // Perform additional processing as needed
                        result.append("ID: " + column1 + ", : " + column2+ ", Hora de entrada: "
                                + column3+ ", Hora de salida: " + column4+ ", Fecha: " + column5+ ", Horas laboradas: "
                                + column6+ ", Horas extra: " + column7+ ", Horas laborables: " + column8+ ", Salario: "
                                + column9+ "\n");
                        System.out.println("ID: " + column1 + ", : " + column2+ ", Hora de entrada: "
                                + column3+ ", Hora de salida: " + column4+ ", Fecha: " + column5+ ", Horas laboradas: "
                                + column6+ ", Horas extra: " + column7+ ", Horas laborables: " + column8+ ", Salario: "
                                + column9);
                    }
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
        result.setText("");
        LocalDate diaSeleccionado = LocalDate.parse(diaSemana.getText());
        LocalDate inicioSemana = diaSeleccionado.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate finSemana = diaSeleccionado.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        System.out.println("Inicio de la semana: " + inicioSemana+ "\nFin de la semana: " + finSemana);
        Conexion cn = new Conexion();

        try (CallableStatement cst = cn.con.prepareCall("{CALL planta1.`Planilla`(?, ?, ?)}")) {
            cst.setInt(1, calendario);
            cst.setDate(2, java.sql.Date.valueOf(inicioSemana));
            cst.setDate(3, java.sql.Date.valueOf(finSemana));

            boolean hasResults = cst.execute();

            if (hasResults) {
                try (ResultSet resultSet = cst.getResultSet()) {
                    // Process the ResultSet as needed
                    while (resultSet.next()) {
                        // Access data from the ResultSet
                        int column1 = resultSet.getInt("id");
                        String column2 = resultSet.getString("nombre");
                        String column3 = resultSet.getString("hora_entrada");
                        String column4 = resultSet.getString("hora_salida");
                        String column5 = resultSet.getString("fecha");
                        String column6 = resultSet.getString("horas_laboradas");
                        String column7 = resultSet.getString("horas_extra");
                        String column8 = resultSet.getString("horas_laborables");
                        String column9 = resultSet.getString("salario");

                        // Perform additional processing as needed
                        result.append("ID: " + column1 + ", : " + column2+ ", Hora de entrada: "
                                + column3+ ", Hora de salida: " + column4+ ", Fecha: " + column5+ ", Horas laboradas: "
                                + column6+ ", Horas extra: " + column7+ ", Horas laborables: " + column8+ ", Salario: "
                                + column9+ "\n");
                        System.out.println("ID: " + column1 + ", : " + column2+ ", Hora de entrada: "
                                + column3+ ", Hora de salida: " + column4+ ", Fecha: " + column5+ ", Horas laboradas: "
                                + column6+ ", Horas extra: " + column7+ ", Horas laborables: " + column8+ ", Salario: "
                                + column9);
                    }
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
        result.setText("");
        LocalDate diaSeleccionado = LocalDate.parse(diaQuincena.getText());

        // Calcular la quincena
        int diaMes = diaSeleccionado.getDayOfMonth();
        LocalDate inicioQuincena;
        LocalDate finQuincena;

        if (diaMes <= 15) {
            // Primer quincena del mes
            inicioQuincena = diaSeleccionado.withDayOfMonth(1);
            finQuincena = diaSeleccionado.withDayOfMonth(15);
        } else {
            // Segunda quincena del mes
            inicioQuincena = diaSeleccionado.withDayOfMonth(16);
            finQuincena = diaSeleccionado.with(TemporalAdjusters.lastDayOfMonth());
        }

        System.out.println("Inicio de la quincena: " + inicioQuincena + "\nFin de la quincena: " + finQuincena);

        Conexion cn = new Conexion();

        try (CallableStatement cst = cn.con.prepareCall("{CALL planta1.`Planilla`(?, ?, ?)}")) {
            cst.setInt(1, calendario);
            cst.setDate(2, java.sql.Date.valueOf(inicioQuincena));
            cst.setDate(3, java.sql.Date.valueOf(finQuincena));

            boolean hasResults = cst.execute();

            if (hasResults) {
                try (ResultSet resultSet = cst.getResultSet()) {
                    // Procesar el ResultSet según sea necesario
                    while (resultSet.next()) {
                        int column1 = resultSet.getInt("id");
                        String column2 = resultSet.getString("nombre");
                        String column3 = resultSet.getString("hora_entrada");
                        String column4 = resultSet.getString("hora_salida");
                        String column5 = resultSet.getString("fecha");
                        String column6 = resultSet.getString("horas_laboradas");
                        String column7 = resultSet.getString("horas_extra");
                        String column8 = resultSet.getString("horas_laborables");
                        String column9 = resultSet.getString("salario");

                        // Perform additional processing as needed
                        result.append("ID: " + column1 + ", : " + column2+ ", Hora de entrada: "
                                + column3+ ", Hora de salida: " + column4+ ", Fecha: " + column5+ ", Horas laboradas: "
                                + column6+ ", Horas extra: " + column7+ ", Horas laborables: " + column8+ ", Salario: "
                                + column9+ "\n");
                        System.out.println("ID: " + column1 + ", : " + column2+ ", Hora de entrada: "
                                + column3+ ", Hora de salida: " + column4+ ", Fecha: " + column5+ ", Horas laboradas: "
                                + column6+ ", Horas extra: " + column7+ ", Horas laborables: " + column8+ ", Salario: "
                                + column9);
                    }
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
}


