package plantas;

import javax.swing.*;

public class simulacionMarcas {

    private JTextField JtextId1;
    private JComboBox elegirCalendarios;
    private JButton iniciarSimulaciónButton;
    private JPanel mainPanel;
    private JTextField textField1;
    private JComboBox comboBox1;

    public simulacionMarcas() {
        JFrame frame = new JFrame("simulacionMarcas");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    

}
