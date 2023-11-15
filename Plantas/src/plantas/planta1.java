package plantas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class planta1 extends JFrame{
    private JButton button1;
    private JLabel nombre;
    private JPanel mainPanel;

    public planta1() {
        setContentPane(mainPanel);
        setTitle("Planta 1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello World");
            }
        });
    }
}
