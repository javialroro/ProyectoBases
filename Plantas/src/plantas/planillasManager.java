package plantas;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class planillasManager {
    private JPanel mainPanel;
    private JLabel labelCalendario;
    private JComboBox calendarios;
    private JComboBox Meses;
    private JTextField quincenaFin;
    private JTextField semanaInicio;
    private JTextField semanaFin;
    private JTextField quincenaInicio;
    private JButton solicitarPlanilla;

    public planillasManager() {
        JFrame frame = new JFrame("Planillas Manager");
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
        calendarios.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Check if the selected item is "Calendario 5"
                boolean isCalendario1or2 = "Calendario 1".equals(calendarios.getSelectedItem()) || "Calendario 2".equals(calendarios.getSelectedItem());
                boolean isCalendario3or4 = "Calendario 3".equals(calendarios.getSelectedItem()) || "Calendario 4".equals(calendarios.getSelectedItem());
                boolean isCalendario5 = "Calendario 5".equals(calendarios.getSelectedItem());

                // Enable or disable the Meses combo box based on the selected calendar
                semanaInicio.setEnabled(isCalendario1or2);
                semanaFin.setEnabled(isCalendario1or2);
                quincenaInicio.setEnabled(isCalendario3or4);
                quincenaFin.setEnabled(isCalendario3or4);
                Meses.setEnabled(isCalendario5);
            }
        });
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
