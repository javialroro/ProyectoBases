package plantas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuSimulacion {
    private JButton simuladorPlanta1Button;
    private JButton simuladorPlanta2Button;
    private JButton simuladorPlanta3Button;
    private JPanel mainPanel;

    public menuSimulacion() {
        JFrame frame = new JFrame("Simulacion");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        simuladorPlanta1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulacionMarcas s1 = new simulacionMarcas();
            }
        });

        simuladorPlanta2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulacionMarcas2 s2 = new simulacionMarcas2();
            }
        });


        simuladorPlanta3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulacionMarcas3 s3 = new simulacionMarcas3();
            }
        });
    }
}
