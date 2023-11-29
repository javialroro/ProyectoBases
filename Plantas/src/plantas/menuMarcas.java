package plantas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuMarcas {
    private JButton marcasPlanta1Button;
    private JButton marcasPlanta2Button;
    private JButton marcasPlanta3Button;
    private JPanel mainPanel;


    public menuMarcas() {
        JFrame frame = new JFrame("MenuMarcas");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        marcasPlanta1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planta1 p1 = new planta1();
            }
        });
        marcasPlanta2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planta2 p2 = new planta2();
            }
        });
        marcasPlanta3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planta3 p3 = new planta3();
            }
        });
    }
}
