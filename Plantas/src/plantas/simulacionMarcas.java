package plantas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class simulacionMarcas {

    private JTextField JtextId1;
    private JComboBox elegirCalendarios;
    private JButton iniciarSimulaciónButton;
    private JPanel mainPanel;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    public simulacionMarcas() {
        JFrame frame = new JFrame("simulacionMarcas");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        iniciarSimulaciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simularMarcas();
            }
        });
    }

    public void simularMarcas() {
        Conexion conexion = new Conexion();

        String fechaInicioStr = JtextId1.getText();
        String fechaFinStr = textField1.getText();
        int ausentismo = Integer.parseInt(textField2.getText());
        int omision = Integer.parseInt(textField3.getText());
        int tardías = Integer.parseInt(textField4.getText());
        String cal = elegirCalendarios.getSelectedItem().toString();
        String modo = comboBox1.getSelectedItem().toString();

        try {


            if (cal.equals("Cal1")) {


                Connection con = conexion.con;
                Statement st = con.createStatement();
                String consulta = "SELECT * FROM empleados1 WHERE TIPO = 1";
                ResultSet rs = st.executeQuery(consulta);
                List<Integer> listaempleados = new ArrayList<>();
                while (rs.next()) {
                    listaempleados.add(rs.getInt("id"));
                }

                // EMPLEADO QUE MARCARÁ
                int empleadoAleatorio = obtenerEmpleadoAleatorio(listaempleados);


                // FECHA EN LA QUE SE GENERARÁ LA MARCA
                //String fechaAleatoria = generarFechaAleatoria(fechaInicioStr, fechaFinStr);

                // HORAS DE ENTRADA Y SALIDA
                String horaEntrada = "07:00:00";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime horaE = LocalTime.parse(horaEntrada, formatter);
                int horaEInt = horaE.getHour();

                String horaSalida = "16:00:00";
                LocalTime horaS = LocalTime.parse(horaSalida, formatter);
                int horaSInt = horaS.getHour();

                Statement stec = con.createStatement();
                String empleadoCiclo = "SELECT * FROM empleados1 WHERE id = " + empleadoAleatorio;
                ResultSet rsec = stec.executeQuery(empleadoCiclo);
                System.out.println("-----------------------------------------------------------------------------------");
                if (rsec.next()) {
                    if (probabilidad(ausentismo)) {
                        System.out.println("El empleado/a " + rsec.getString("nombre") + " no asistió");

                    } else {
                        System.out.println("El empleado/a " + rsec.getString("nombre") + " asistió");

                        if (probabilidad(tardías)) {

                            System.out.println("El empleado/a " + rsec.getString("nombre") + " llegó tarde");
                            Random randht = new Random();
                            int horasTarde = randht.nextInt((3 - 1) + 1) + 1;
                            int horaETInt = horaEInt + horasTarde;
                            String horaET = horaETInt + ":00:00";
                            System.out.println("Hora de entrada: " + horaET);


                        } else {
                            System.out.println("El empleado/a " + rsec.getString("nombre") + " llegó a tiempo");


                        }

                        if (probabilidad(omision)) {
                            System.out.println("El empleado/a " + rsec.getString("nombre") + " olvidó marcar salida");

                        } else {
                            System.out.println("El empleado/a " + rsec.getString("nombre") + " marcó salida");

                        }


                    }


                }

                System.out.println("-----------------------------------------------------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public static String generarFechaAleatoria(String fechaInicioStr, String fechaFinStr) {
        LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
        LocalDate fechaFin = LocalDate.parse(fechaFinStr);

        int añoInicio = fechaInicio.getYear();
        int mesInicio = fechaInicio.getMonthValue();
        int diaInicio = fechaInicio.getDayOfMonth();

        int añoFin = fechaFin.getYear();
        int mesFin = fechaFin.getMonthValue();
        int diaFin = fechaFin.getDayOfMonth();

        Random rand = new Random();

        int añoAleatorio = rand.nextInt((añoFin - añoInicio) + 1) + añoInicio;

        int mesAleatorio;
        if (añoAleatorio == añoInicio && añoAleatorio == añoFin) {
            mesAleatorio = rand.nextInt((mesFin - mesInicio) + 1) + mesInicio;
        } else if (añoAleatorio == añoInicio) {
            mesAleatorio = rand.nextInt((12 - mesInicio) + 1) + mesInicio;
        } else if (añoAleatorio == añoFin) {
            mesAleatorio = rand.nextInt(mesFin) + 1;
        } else {
            mesAleatorio = rand.nextInt(12) + 1;
        }

        int diaAleatorio;
        if (añoAleatorio == añoInicio && mesAleatorio == mesInicio && añoAleatorio == añoFin && mesAleatorio == mesFin) {
            diaAleatorio = rand.nextInt((diaFin - diaInicio) + 1) + diaInicio;
        } else if (añoAleatorio == añoInicio && mesAleatorio == mesInicio) {
            diaAleatorio = rand.nextInt((31 - diaInicio) + 1) + diaInicio;
        } else if (añoAleatorio == añoFin && mesAleatorio == mesFin) {
            diaAleatorio = rand.nextInt(diaFin) + 1;
        } else {
            diaAleatorio = rand.nextInt(31) + 1;
        }

        String mesFormateado = String.format("%02d", mesAleatorio);
        String diaFormateado = String.format("%02d", diaAleatorio);
        System.out.println(añoAleatorio + "-" + mesFormateado + "-" + diaFormateado);
        return añoAleatorio + "-" + mesFormateado + "-" + diaFormateado;
    }

    public static int obtenerEmpleadoAleatorio(List<Integer> lista) {
        // Verificar si la lista no está vacía
        if (lista.isEmpty()) {
            throw new IllegalArgumentException("La lista está vacía");
        }

        // Crear una instancia de la clase Random
        Random rand = new Random();

        // Obtener un índice aleatorio dentro del tamaño de la lista
        int indiceAleatorio = rand.nextInt(lista.size());

        // Obtener el número aleatorio de la lista utilizando el índice
        return lista.get(indiceAleatorio);
    }



    public static boolean probabilidad(int porcentaje) {
        // Verificar si el porcentaje está en el rango válido (0-100)
        if (porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("La probabilidad debe estar en el rango de 0 a 100.");
        }

        // Crear una instancia de la clase Random
        Random rand = new Random();

        // Generar un número aleatorio entre 0 y 99 (inclusive)
        int numeroAleatorio = rand.nextInt(100);

        // Verificar si el número aleatorio es menor que el porcentaje
        return numeroAleatorio < porcentaje;
    }

}









