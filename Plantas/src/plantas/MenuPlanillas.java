package plantas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPlanillas {
    private JButton planillasPlanta1Button;
    private JButton planillasPlanta2Button;
    private JButton planillasPlanta3Button;
    private JPanel mainPanel;

    public MenuPlanillas() {
        JFrame frame = new JFrame("MenuPlanillas");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        planillasPlanta1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planillasManager p = new planillasManager();
            }
        });
        planillasPlanta2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planillasManager2 p2 = new planillasManager2();
            }
        });

        planillasPlanta3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planillasManager3 p3 = new planillasManager3();
            }
        });
    }
}
