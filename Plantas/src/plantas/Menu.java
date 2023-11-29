package plantas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JButton corporaciónButton;
    private JButton planillasButton;
    private JButton marcasButton;
    private JButton simuladorButton;
    private JPanel mainPanel;
    private JButton enviarPlanillasPorArchivoButton;
    private JButton enviarEmpleadosPorArchivoButton;
    private JButton crearEmpleadoButton;


    public Menu(){
        JFrame frame = new JFrame("Menu");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        corporaciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Corporacion c = new Corporacion();
            }
        });


        planillasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPlanillas  mp = new MenuPlanillas();
            }
        });

        marcasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuMarcas mm = new menuMarcas();
            }
        });

        simuladorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuSimulacion ms = new menuSimulacion();
            }
        });
        enviarPlanillasPorArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planillasArchivo pa = new planillasArchivo();
                pa.guardarPlanillasArchivos();
            }
        });

        enviarEmpleadosPorArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planta3EmpleadosArchivo ap3 = new planta3EmpleadosArchivo();
                ap3.generarArchivoEmpleados();
            }
        });
        crearEmpleadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearEmpleado ce = new crearEmpleado();
            }
        });
    }
}
