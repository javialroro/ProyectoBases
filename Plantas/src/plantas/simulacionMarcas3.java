package plantas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class simulacionMarcas3 {

    private  JTextField JtextId1;
    private  JComboBox elegirCalendarios;
    private   JButton iniciarSimulaciónButton;
    private  JPanel mainPanel;
    private JTextField textField1;
    private  JComboBox comboBox1;
    private  JTextField textField2;
    private  JTextField textField3;
    private  JTextField textField4;
    private JButton pararSimulaciónButton;

    public simulacionMarcas3() {
        JFrame frame = new JFrame("simulacionMarcas");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        final boolean[] ejecutar = {true};
        pararSimulaciónButton.setEnabled(false);
        Thread miHilo = new Thread(() -> {
            while (ejecutar[0]) {
                simularMarcas();
                try {
                    Thread.sleep(5000); // Pausa el hilo durante 5 segundos
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }});

        iniciarSimulaciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                miHilo.start();
                iniciarSimulaciónButton.setEnabled(false);
                pararSimulaciónButton.setEnabled(true);
            }
        });

        pararSimulaciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutar[0] = false;
                iniciarSimulaciónButton.setEnabled(true);
                pararSimulaciónButton.setEnabled(false);
            }
        });
    }

    public  void simularMarcas() {
        Conexion conexion = new Conexion();

        String fechaInicioStr = JtextId1.getText();
        String fechaFinStr = textField1.getText();
        int ausentismo = Integer.parseInt(textField2.getText());
        int omision = Integer.parseInt(textField3.getText());
        int tardías = Integer.parseInt(textField4.getText());
        String cal = elegirCalendarios.getSelectedItem().toString();
        String modo = comboBox1.getSelectedItem().toString();
        if(modo.equals("Final")){
            try {

                if (cal.equals("Cal1")) {
                    Connection con = conexion.con;
                    Statement st = con.createStatement();
                    String consulta = "SELECT * FROM planta3.empleados1 WHERE TIPO = 1";
                    ResultSet rs = st.executeQuery(consulta);
                    List<Integer> listaempleados = new ArrayList<>();
                    while (rs.next()) {
                        listaempleados.add(rs.getInt("id"));
                    }

                    // EMPLEADO QUE MARCARÁ
                    int empleadoAleatorio = obtenerEmpleadoAleatorio(listaempleados);


                    // FECHA EN LA QUE SE GENERARÁ LA MARCA
                    String fechaAleatoria = generarFechaAleatoria(fechaInicioStr, fechaFinStr);

                    // HORAS DE ENTRADA Y SALIDA
                    String horaEntrada = "08:00:00";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime horaE = LocalTime.parse(horaEntrada, formatter);
                    int horaEInt = horaE.getHour();

                    String horaSalida = "16:00:00";
                    LocalTime horaS = LocalTime.parse(horaSalida, formatter);
                    int horaSInt = horaS.getHour();

                    Statement stec = con.createStatement();
                    String empleadoCiclo = "SELECT * FROM planta3.empleados1 WHERE id = " + empleadoAleatorio;
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

                                try (CallableStatement cst = con.prepareCall("{CALL planta3.`MARCAR ENTRADA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaET));

                                    cst.execute();
                                    //JOptionPane.showMessageDialog(null, "Entrada marcada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    // No need to retrieve results as the stored procedure doesn't return a ResultSet.
                                } catch (SQLException | NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Error al marcar la entrada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }


                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " llegó a tiempo");
                                System.out.println("Hora de entrada: " + horaEInt+ ":00:00");

                                try (CallableStatement cst = con.prepareCall("{CALL planta3.`MARCAR ENTRADA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaEntrada));

                                    cst.execute();
                                    //JOptionPane.showMessageDialog(null, "Entrada marcada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    // No need to retrieve results as the stored procedure doesn't return a ResultSet.
                                } catch (SQLException | NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Error al marcar la entrada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }


                            }

                            if (probabilidad(omision)) {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " olvidó marcar salida");

                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " marcó salida");
                                System.out.println("Hora de salida: " + horaSInt+ ":00:00");
                                try (CallableStatement cst = con.prepareCall("{CALL planta3.`MARCAR_SALIDA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaSalida));

                                    boolean hasResultSet = cst.execute();

                                    if (hasResultSet) {
                                        ResultSet rss = cst.getResultSet();
                                        if (rss.next()) {
                                            // Check the result from the stored procedure
                                            String resultMessage = rs.getString(1);
                                            JOptionPane.showMessageDialog(null, resultMessage);
                                        }
                                    } else {
                                        //JOptionPane.showMessageDialog(null, "Salida marcada con éxito.");
                                    }

                                } catch (SQLException | NumberFormatException e) {
                                    // Show an error message
                                    JOptionPane.showMessageDialog(null, "Error al marcar la salida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }


                            }

                        }


                    }

                    System.out.println("-----------------------------------------------------------------------------------");
                } else if (cal.equals("Cal2")) {

                    Connection con = conexion.con;
                    Statement st = con.createStatement();
                    String consulta = "SELECT * FROM planta3.empleados1 WHERE TIPO = 2";
                    ResultSet rs = st.executeQuery(consulta);
                    List<Integer> listaempleados = new ArrayList<>();
                    while (rs.next()) {
                        listaempleados.add(rs.getInt("id"));
                    }

                    // EMPLEADO QUE MARCARÁ
                    int empleadoAleatorio = obtenerEmpleadoAleatorio(listaempleados);


                    // FECHA EN LA QUE SE GENERARÁ LA MARCA
                    String fechaAleatoria = generarFechaAleatoria(fechaInicioStr, fechaFinStr);

                    // HORAS DE ENTRADA Y SALIDA
                    String horaEntrada = "08:00:00";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime horaE = LocalTime.parse(horaEntrada, formatter);
                    int horaEInt = horaE.getHour();

                    String horaSalida = "16:00:00";
                    LocalTime horaS = LocalTime.parse(horaSalida, formatter);
                    int horaSInt = horaS.getHour();

                    Statement stec = con.createStatement();
                    String empleadoCiclo = "SELECT * FROM planta3.empleados1 WHERE id = " + empleadoAleatorio;
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

                                try (CallableStatement cst = con.prepareCall("{CALL planta3.`MARCAR ENTRADA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaET));

                                    cst.execute();
                                    //JOptionPane.showMessageDialog(null, "Entrada marcada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    // No need to retrieve results as the stored procedure doesn't return a ResultSet.
                                } catch (SQLException | NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Error al marcar la entrada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }


                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " llegó a tiempo");
                                System.out.println("Hora de entrada: " + horaEInt+ ":00:00");

                                try (CallableStatement cst = con.prepareCall("{CALL planta3.`MARCAR ENTRADA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaEntrada));

                                    cst.execute();
                                    //JOptionPane.showMessageDialog(null, "Entrada marcada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    // No need to retrieve results as the stored procedure doesn't return a ResultSet.
                                } catch (SQLException | NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Error al marcar la entrada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }


                            }

                            if (probabilidad(omision)) {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " olvidó marcar salida");

                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " marcó salida");
                                System.out.println("Hora de salida: " + horaSInt+ ":00:00");
                                try (CallableStatement cst = con.prepareCall("{CALL planta3.`MARCAR_SALIDA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaSalida));

                                    boolean hasResultSet = cst.execute();

                                    if (hasResultSet) {
                                        ResultSet rss = cst.getResultSet();
                                        if (rss.next()) {
                                            // Check the result from the stored procedure
                                            String resultMessage = rs.getString(1);
                                            JOptionPane.showMessageDialog(null, resultMessage);
                                        }
                                    } else {
                                        //JOptionPane.showMessageDialog(null, "Salida marcada con éxito.");
                                    }

                                } catch (SQLException | NumberFormatException e) {
                                    // Show an error message
                                    JOptionPane.showMessageDialog(null, "Error al marcar la salida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }

                            }

                        }


                    }

                    System.out.println("-----------------------------------------------------------------------------------");
                } else if (cal.equals("Cal3")) {
                    Connection con = conexion.con;
                    Statement st = con.createStatement();
                    String consulta = "SELECT * FROM planta3.empleados1 WHERE TIPO = 3";
                    ResultSet rs = st.executeQuery(consulta);
                    List<Integer> listaempleados = new ArrayList<>();
                    while (rs.next()) {
                        listaempleados.add(rs.getInt("id"));
                    }

                    // EMPLEADO QUE MARCARÁ
                    int empleadoAleatorio = obtenerEmpleadoAleatorio(listaempleados);


                    // FECHA EN LA QUE SE GENERARÁ LA MARCA
                    String fechaAleatoria = generarFechaAleatoria(fechaInicioStr, fechaFinStr);

                    // HORAS DE ENTRADA Y SALIDA
                    String horaEntrada = "08:00:00";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime horaE = LocalTime.parse(horaEntrada, formatter);
                    int horaEInt = horaE.getHour();

                    String horaSalida = "16:00:00";
                    LocalTime horaS = LocalTime.parse(horaSalida, formatter);
                    int horaSInt = horaS.getHour();

                    Statement stec = con.createStatement();
                    String empleadoCiclo = "SELECT * FROM planta3.empleados1 WHERE id = " + empleadoAleatorio;
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

                                try (CallableStatement cst = con.prepareCall("{CALL planta3.`MARCAR ENTRADA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaET));

                                    cst.execute();
                                    //JOptionPane.showMessageDialog(null, "Entrada marcada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    // No need to retrieve results as the stored procedure doesn't return a ResultSet.
                                } catch (SQLException | NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Error al marcar la entrada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }


                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " llegó a tiempo");
                                System.out.println("Hora de entrada: " + horaEInt+ ":00:00");

                                try (CallableStatement cst = con.prepareCall("{CALL planta3.`MARCAR ENTRADA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaEntrada));

                                    cst.execute();
                                    //JOptionPane.showMessageDialog(null, "Entrada marcada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    // No need to retrieve results as the stored procedure doesn't return a ResultSet.
                                } catch (SQLException | NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Error al marcar la entrada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }


                            }

                            if (probabilidad(omision)) {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " olvidó marcar salida");

                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " marcó salida");
                                System.out.println("Hora de salida: " + horaSInt+ ":00:00");
                                try (CallableStatement cst = con.prepareCall("{CALL planta3.`MARCAR_SALIDA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaSalida));

                                    boolean hasResultSet = cst.execute();

                                    if (hasResultSet) {
                                        ResultSet rss = cst.getResultSet();
                                        if (rss.next()) {
                                            // Check the result from the stored procedure
                                            String resultMessage = rs.getString(1);
                                            JOptionPane.showMessageDialog(null, resultMessage);
                                        }
                                    } else {
                                        //JOptionPane.showMessageDialog(null, "Salida marcada con éxito.");
                                    }

                                } catch (SQLException | NumberFormatException e) {
                                    // Show an error message
                                    JOptionPane.showMessageDialog(null, "Error al marcar la salida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }


                            }

                        }


                    }

                    System.out.println("-----------------------------------------------------------------------------------");
                } else if (cal.equals("Cal4y5")) {
                    Connection con = conexion.con;
                    Statement st = con.createStatement();
                    String consulta = "SELECT * FROM planta3.empleados1 WHERE TIPO = 4 OR TIPO = 5";
                    ResultSet rs = st.executeQuery(consulta);
                    List<Integer> listaempleados = new ArrayList<>();
                    while (rs.next()) {
                        listaempleados.add(rs.getInt("id"));
                    }

                    // EMPLEADO QUE MARCARÁ
                    int empleadoAleatorio = obtenerEmpleadoAleatorio(listaempleados);


                    // FECHA EN LA QUE SE GENERARÁ LA MARCA
                    String fechaAleatoria = generarFechaAleatoria(fechaInicioStr, fechaFinStr);

                    // HORAS DE ENTRADA Y SALIDA
                    String horaEntrada = "08:00:00";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime horaE = LocalTime.parse(horaEntrada, formatter);
                    int horaEInt = horaE.getHour();

                    String horaSalida = "16:00:00";
                    LocalTime horaS = LocalTime.parse(horaSalida, formatter);
                    int horaSInt = horaS.getHour();

                    Statement stec = con.createStatement();
                    String empleadoCiclo = "SELECT * FROM planta3.empleados1 WHERE id = " + empleadoAleatorio;
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

                                try (CallableStatement cst = con.prepareCall("{CALL planta3.`MARCAR ENTRADA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaET));

                                    cst.execute();
                                    //JOptionPane.showMessageDialog(null, "Entrada marcada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    // No need to retrieve results as the stored procedure doesn't return a ResultSet.
                                } catch (SQLException | NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Error al marcar la entrada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }


                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " llegó a tiempo");
                                System.out.println("Hora de entrada: " + horaEInt+ ":00:00");

                                try (CallableStatement cst = con.prepareCall("{CALL planta1.`MARCAR ENTRADA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaEntrada));

                                    cst.execute();
                                    //JOptionPane.showMessageDialog(null, "Entrada marcada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    // No need to retrieve results as the stored procedure doesn't return a ResultSet.
                                } catch (SQLException | NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Error al marcar la entrada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }


                            }

                            if (probabilidad(omision)) {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " olvidó marcar salida");

                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " marcó salida");
                                System.out.println("Hora de salida: " + horaSInt+ ":00:00");
                                try (CallableStatement cst = con.prepareCall("{CALL planta3.`MARCAR_SALIDA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaSalida));

                                    boolean hasResultSet = cst.execute();

                                    if (hasResultSet) {
                                        ResultSet rss = cst.getResultSet();
                                        if (rss.next()) {
                                            // Check the result from the stored procedure
                                            String resultMessage = rs.getString(1);
                                            JOptionPane.showMessageDialog(null, resultMessage);
                                        }
                                    } else {
                                        //JOptionPane.showMessageDialog(null, "Salida marcada con éxito.");
                                    }

                                } catch (SQLException | NumberFormatException e) {
                                    // Show an error message
                                    JOptionPane.showMessageDialog(null, "Error al marcar la salida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }


                            }

                        }


                    }

                    System.out.println("-----------------------------------------------------------------------------------");
                } else {

                    Connection con = conexion.con;
                    Statement st = con.createStatement();
                    String consulta = "SELECT * FROM planta3.empleados1";
                    ResultSet rs = st.executeQuery(consulta);
                    List<Integer> listaempleados = new ArrayList<>();
                    while (rs.next()) {
                        listaempleados.add(rs.getInt("id"));
                    }

                    // EMPLEADO QUE MARCARÁ
                    int empleadoAleatorio = obtenerEmpleadoAleatorio(listaempleados);


                    // FECHA EN LA QUE SE GENERARÁ LA MARCA
                    String fechaAleatoria = generarFechaAleatoria(fechaInicioStr, fechaFinStr);

                    // HORAS DE ENTRADA Y SALIDA
                    String horaEntrada = "08:00:00";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime horaE = LocalTime.parse(horaEntrada, formatter);
                    int horaEInt = horaE.getHour();

                    String horaSalida = "16:00:00";
                    LocalTime horaS = LocalTime.parse(horaSalida, formatter);
                    int horaSInt = horaS.getHour();

                    Statement stec = con.createStatement();
                    String empleadoCiclo = "SELECT * FROM planta3.empleados1 WHERE id = " + empleadoAleatorio;
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

                                try (CallableStatement cst = con.prepareCall("{CALL planta3.`MARCAR ENTRADA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaET));

                                    cst.execute();
                                    // JOptionPane.showMessageDialog(null, "Entrada marcada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    // No need to retrieve results as the stored procedure doesn't return a ResultSet.
                                } catch (SQLException | NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Error al marcar la entrada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }


                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " llegó a tiempo");
                                System.out.println("Hora de entrada: " + horaEInt+ ":00:00");

                                try (CallableStatement cst = con.prepareCall("{CALL planta3.`MARCAR ENTRADA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaEntrada));

                                    cst.execute();
                                    //JOptionPane.showMessageDialog(null, "Entrada marcada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    // No need to retrieve results as the stored procedure doesn't return a ResultSet.
                                } catch (SQLException | NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Error al marcar la entrada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }


                            }

                            if (probabilidad(omision)) {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " olvidó marcar salida");

                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " marcó salida");
                                System.out.println("Hora de salida: " + horaSInt+ ":00:00");
                                try (CallableStatement cst = con.prepareCall("{CALL planta1.`MARCAR_SALIDA`(?, ?, ?)}")) {
                                    cst.setInt(1, Integer.parseInt(rsec.getString("id")));
                                    cst.setDate(2, java.sql.Date.valueOf(fechaAleatoria));
                                    cst.setTime(3, java.sql.Time.valueOf(horaSalida));

                                    boolean hasResultSet = cst.execute();

                                    if (hasResultSet) {
                                        ResultSet rss = cst.getResultSet();
                                        if (rss.next()) {
                                            // Check the result from the stored procedure
                                            String resultMessage = rs.getString(1);
                                            JOptionPane.showMessageDialog(null, resultMessage);
                                        }
                                    } else {
                                        // JOptionPane.showMessageDialog(null, "Salida marcada con éxito.");
                                    }

                                } catch (SQLException | NumberFormatException e) {
                                    // Show an error message
                                    JOptionPane.showMessageDialog(null, "Error al marcar la salida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("Error: " + e);
                                }


                            }

                        }


                    }

                    System.out.println("-----------------------------------------------------------------------------------");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        else{




            try {

                if (cal.equals("Cal1")) {
                    Connection con = conexion.con;
                    Statement st = con.createStatement();
                    String consulta = "SELECT * FROM planta3.empleados1 WHERE TIPO = 1";
                    ResultSet rs = st.executeQuery(consulta);
                    List<Integer> listaempleados = new ArrayList<>();
                    while (rs.next()) {
                        listaempleados.add(rs.getInt("id"));
                    }

                    // EMPLEADO QUE MARCARÁ
                    int empleadoAleatorio = obtenerEmpleadoAleatorio(listaempleados);


                    // FECHA EN LA QUE SE GENERARÁ LA MARCA
                    String fechaAleatoria = generarFechaAleatoria(fechaInicioStr, fechaFinStr);

                    // HORAS DE ENTRADA Y SALIDA
                    String horaEntrada = "08:00:00";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime horaE = LocalTime.parse(horaEntrada, formatter);
                    int horaEInt = horaE.getHour();

                    String horaSalida = "16:00:00";
                    LocalTime horaS = LocalTime.parse(horaSalida, formatter);
                    int horaSInt = horaS.getHour();

                    Statement stec = con.createStatement();
                    String empleadoCiclo = "SELECT * FROM planta3.empleados1 WHERE id = " + empleadoAleatorio;
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
                                System.out.println("Hora de entrada: " + horaEInt+ ":00:00");



                            }

                            if (probabilidad(omision)) {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " olvidó marcar salida");

                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " marcó salida");
                                System.out.println("Hora de salida: " + horaSInt+ ":00:00");



                            }

                        }


                    }

                    System.out.println("-----------------------------------------------------------------------------------");
                } else if (cal.equals("Cal2")) {

                    Connection con = conexion.con;
                    Statement st = con.createStatement();
                    String consulta = "SELECT * FROM planta3.empleados1 WHERE TIPO = 2";
                    ResultSet rs = st.executeQuery(consulta);
                    List<Integer> listaempleados = new ArrayList<>();
                    while (rs.next()) {
                        listaempleados.add(rs.getInt("id"));
                    }

                    // EMPLEADO QUE MARCARÁ
                    int empleadoAleatorio = obtenerEmpleadoAleatorio(listaempleados);


                    // FECHA EN LA QUE SE GENERARÁ LA MARCA
                    String fechaAleatoria = generarFechaAleatoria(fechaInicioStr, fechaFinStr);

                    // HORAS DE ENTRADA Y SALIDA
                    String horaEntrada = "08:00:00";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime horaE = LocalTime.parse(horaEntrada, formatter);
                    int horaEInt = horaE.getHour();

                    String horaSalida = "16:00:00";
                    LocalTime horaS = LocalTime.parse(horaSalida, formatter);
                    int horaSInt = horaS.getHour();

                    Statement stec = con.createStatement();
                    String empleadoCiclo = "SELECT * FROM planta3.empleados1 WHERE id = " + empleadoAleatorio;
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
                                System.out.println("Hora de entrada: " + horaEInt+ ":00:00");




                            }

                            if (probabilidad(omision)) {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " olvidó marcar salida");

                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " marcó salida");
                                System.out.println("Hora de salida: " + horaSInt+ ":00:00");


                            }

                        }


                    }

                    System.out.println("-----------------------------------------------------------------------------------");
                } else if (cal.equals("Cal3")) {
                    Connection con = conexion.con;
                    Statement st = con.createStatement();
                    String consulta = "SELECT * FROM planta3.empleados1 WHERE TIPO = 3";
                    ResultSet rs = st.executeQuery(consulta);
                    List<Integer> listaempleados = new ArrayList<>();
                    while (rs.next()) {
                        listaempleados.add(rs.getInt("id"));
                    }

                    // EMPLEADO QUE MARCARÁ
                    int empleadoAleatorio = obtenerEmpleadoAleatorio(listaempleados);


                    // FECHA EN LA QUE SE GENERARÁ LA MARCA
                    String fechaAleatoria = generarFechaAleatoria(fechaInicioStr, fechaFinStr);

                    // HORAS DE ENTRADA Y SALIDA
                    String horaEntrada = "08:00:00";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime horaE = LocalTime.parse(horaEntrada, formatter);
                    int horaEInt = horaE.getHour();

                    String horaSalida = "16:00:00";
                    LocalTime horaS = LocalTime.parse(horaSalida, formatter);
                    int horaSInt = horaS.getHour();

                    Statement stec = con.createStatement();
                    String empleadoCiclo = "SELECT * FROM planta3.empleados1 WHERE id = " + empleadoAleatorio;
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
                                System.out.println("Hora de entrada: " + horaEInt+ ":00:00");



                            }

                            if (probabilidad(omision)) {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " olvidó marcar salida");

                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " marcó salida");
                                System.out.println("Hora de salida: " + horaSInt+ ":00:00");


                            }

                        }


                    }

                    System.out.println("-----------------------------------------------------------------------------------");
                } else if (cal.equals("Cal4y5")) {
                    Connection con = conexion.con;
                    Statement st = con.createStatement();
                    String consulta = "SELECT * FROM planta3.empleados1 WHERE TIPO = 4 OR TIPO = 5";
                    ResultSet rs = st.executeQuery(consulta);
                    List<Integer> listaempleados = new ArrayList<>();
                    while (rs.next()) {
                        listaempleados.add(rs.getInt("id"));
                    }

                    // EMPLEADO QUE MARCARÁ
                    int empleadoAleatorio = obtenerEmpleadoAleatorio(listaempleados);


                    // FECHA EN LA QUE SE GENERARÁ LA MARCA
                    String fechaAleatoria = generarFechaAleatoria(fechaInicioStr, fechaFinStr);

                    // HORAS DE ENTRADA Y SALIDA
                    String horaEntrada = "08:00:00";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime horaE = LocalTime.parse(horaEntrada, formatter);
                    int horaEInt = horaE.getHour();

                    String horaSalida = "16:00:00";
                    LocalTime horaS = LocalTime.parse(horaSalida, formatter);
                    int horaSInt = horaS.getHour();

                    Statement stec = con.createStatement();
                    String empleadoCiclo = "SELECT * FROM planta3.empleados1 WHERE id = " + empleadoAleatorio;
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
                                System.out.println("Hora de entrada: " + horaEInt+ ":00:00");




                            }

                            if (probabilidad(omision)) {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " olvidó marcar salida");

                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " marcó salida");
                                System.out.println("Hora de salida: " + horaSInt+ ":00:00");



                            }

                        }


                    }

                    System.out.println("-----------------------------------------------------------------------------------");
                } else {

                    Connection con = conexion.con;
                    Statement st = con.createStatement();
                    String consulta = "SELECT * FROM planta3.empleados1";
                    ResultSet rs = st.executeQuery(consulta);
                    List<Integer> listaempleados = new ArrayList<>();
                    while (rs.next()) {
                        listaempleados.add(rs.getInt("id"));
                    }

                    // EMPLEADO QUE MARCARÁ
                    int empleadoAleatorio = obtenerEmpleadoAleatorio(listaempleados);


                    // FECHA EN LA QUE SE GENERARÁ LA MARCA
                    String fechaAleatoria = generarFechaAleatoria(fechaInicioStr, fechaFinStr);

                    // HORAS DE ENTRADA Y SALIDA
                    String horaEntrada = "08:00:00";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime horaE = LocalTime.parse(horaEntrada, formatter);
                    int horaEInt = horaE.getHour();

                    String horaSalida = "16:00:00";
                    LocalTime horaS = LocalTime.parse(horaSalida, formatter);
                    int horaSInt = horaS.getHour();

                    Statement stec = con.createStatement();
                    String empleadoCiclo = "SELECT * FROM planta3.empleados1 WHERE id = " + empleadoAleatorio;
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
                                System.out.println("Hora de entrada: " + horaEInt+ ":00:00");




                            }

                            if (probabilidad(omision)) {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " olvidó marcar salida");

                            } else {
                                System.out.println("El empleado/a " + rsec.getString("nombre") + " marcó salida");
                                System.out.println("Hora de salida: " + horaSInt+ ":00:00");


                            }

                        }


                    }

                    System.out.println("-----------------------------------------------------------------------------------");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }


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









