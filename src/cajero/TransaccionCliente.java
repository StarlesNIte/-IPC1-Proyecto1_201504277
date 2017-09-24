/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cajero;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author LuisMiguel
 */
public class TransaccionCliente extends JFrame implements Serializable, ActionListener {

    private JMenuBar menu;
    private JMenu archivo, ayuda;
    private JMenuItem guardar, salir, nuevocliente, acercade, datoscuenta;
    private JPanel panelprincipal, panelinferior, panelsuperior;
    private JLabel tipobilletelabel, cantidadbilletelabel, cienlabel, doscientoslabel, cincuentalabel, veintelabel, diezlabel, montolabel, retiralabel;
    private JTextField doscientostext, cientext, cincuentatext, veintetext, dieztext;
    private Persona cajero;
    private Alumno[] alumnos;
    private int index;

    public TransaccionCliente(int index) {
        /*
        cash money es true en banco 
        y pro pisto false
         */
        this.index = index;
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setSize(500, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10));

        try {
            ObjectInputStream read = new ObjectInputStream(new FileInputStream("cajero.data"));
            cajero = (Persona) read.readObject();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se ha creado un archivo de record");
            cajero = new Persona();
        }
        try {
            ObjectInputStream read = new ObjectInputStream(new FileInputStream("alumnos.data"));
            alumnos = (Alumno[]) read.readObject();
        } catch (Exception e) {
            alumnos = new Alumno[100];
        }

        if (alumnos[index].getBanco()) {
            this.setTitle("CASH-Money / " + alumnos[index].getUsuario());
            this.setIconImage(new ImageIcon(getClass().getResource("/cash.jpg")).getImage());
        } else {
            this.setTitle("PRO-Money / " + alumnos[index].getUsuario());
            this.setIconImage(new ImageIcon(getClass().getResource("/pro.jpg")).getImage());
        }

        menu = new JMenuBar();
        this.setJMenuBar(menu);

        archivo = new JMenu("Archivo");
        menu.add(archivo);

        ayuda = new JMenu("Ayuda");
        menu.add(ayuda);

        guardar = new JMenuItem("Retirar");
        archivo.add(guardar);
        guardar.addActionListener(this);

        nuevocliente = new JMenuItem("Deposito a amigo");
        archivo.add(nuevocliente);
        nuevocliente.addActionListener(this);

        datoscuenta = new JMenuItem("Datos personales de cuenta");
        archivo.add(datoscuenta);
        datoscuenta.addActionListener(this);

        salir = new JMenuItem("Cerrar sesión");
        archivo.add(salir);
        salir.addActionListener(this);

        acercade = new JMenuItem("Acerca de...");
        ayuda.add(acercade);
        acercade.addActionListener(this);

        panelprincipal = new JPanel();
        panelprincipal.setLayout(new GridLayout(0, 2, 10, 10));
        this.add(panelprincipal, BorderLayout.CENTER);

        tipobilletelabel = new JLabel();
        tipobilletelabel.setText("Tipo de billete");
        panelprincipal.add(tipobilletelabel);

        cantidadbilletelabel = new JLabel();
        cantidadbilletelabel.setText("Cantidad de billetes");
        panelprincipal.add(cantidadbilletelabel);

        doscientoslabel = new JLabel();
        doscientoslabel.setText("Q. 200");
        panelprincipal.add(doscientoslabel);

        doscientostext = new JTextField(Integer.toString(alumnos[index].getDoscientos()));
        panelprincipal.add(doscientostext);

        cienlabel = new JLabel();
        cienlabel.setText("Q. 100");
        panelprincipal.add(cienlabel);

        cientext = new JTextField(Integer.toString(alumnos[index].getCien()));
        panelprincipal.add(cientext);

        cincuentalabel = new JLabel();
        cincuentalabel.setText("Q. 50");
        panelprincipal.add(cincuentalabel);

        cincuentatext = new JTextField(Integer.toString(alumnos[index].getCincuenta()));
        panelprincipal.add(cincuentatext);

        veintelabel = new JLabel();
        veintelabel.setText("Q. 20");
        panelprincipal.add(veintelabel);

        veintetext = new JTextField(Integer.toString(alumnos[index].getVeinte()));
        panelprincipal.add(veintetext);

        diezlabel = new JLabel();
        diezlabel.setText("Q. 10");
        panelprincipal.add(diezlabel);

        dieztext = new JTextField(Integer.toString(alumnos[index].getDiez()));
        panelprincipal.add(dieztext);

        panelinferior = new JPanel();
        panelinferior.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.add(panelinferior, BorderLayout.NORTH);

        montolabel = new JLabel();
        montolabel.setText("Bienvenido " + alumnos[index].getUsuario() + "! Tu saldo actual es de Q. " + alumnos[index].getMontoactual());
        panelinferior.add(montolabel);

        panelsuperior = new JPanel();
        panelsuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.add(panelsuperior, BorderLayout.SOUTH);

        retiralabel = new JLabel();
        retiralabel.setText("La transacción actual tiene el monto de Q. " + montoRetirar(doscientostext.getText(), cientext.getText(), cincuentatext.getText(), veintetext.getText(), dieztext.getText()));
        panelsuperior.add(retiralabel);

    }

    private int montoRetirar(String doscientos, String cien, String cincuenta, String veinte, String diez) {
        return Integer.parseInt(doscientos) * 200 + Integer.parseInt(cien) * 100 + Integer.parseInt(cincuenta) * 50 + Integer.parseInt(veinte) * 20 + Integer.parseInt(diez) * 10;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guardar) {
            //retiro
            if (montoRetirar(doscientostext.getText(), cientext.getText(), cincuentatext.getText(), veintetext.getText(), dieztext.getText()) <= alumnos[index].getMontoactual()
                    && cajero.getDoscientos() >= Integer.parseInt(doscientostext.getText())
                    && cajero.getCien() >= Integer.parseInt(cientext.getText())
                    && cajero.getCincuenta() >= Integer.parseInt(cincuentatext.getText())
                    && cajero.getVeinte() >= Integer.parseInt(veintetext.getText())
                    && cajero.getDiez() >= Integer.parseInt(dieztext.getText())) {

                alumnos[index].setMontoactual(alumnos[index].getMontoactual() - montoRetirar(doscientostext.getText(), cientext.getText(), cincuentatext.getText(), veintetext.getText(), dieztext.getText()));
                cajero.setDoscientos(cajero.getDoscientos() - Integer.parseInt(doscientostext.getText()));
                cajero.setCien(cajero.getCien() - Integer.parseInt(cientext.getText()));
                cajero.setCincuenta(cajero.getCincuenta() - Integer.parseInt(cincuentatext.getText()));
                cajero.setVeinte(cajero.getVeinte() - Integer.parseInt(veintetext.getText()));
                cajero.setDiez(cajero.getDiez() - Integer.parseInt(dieztext.getText()));

                montolabel.setText("Bienvenido " + alumnos[index].getUsuario() + "! Tu saldo actual es de Q. " + alumnos[index].getMontoactual());
                retiralabel.setText("La transacción actual tiene el monto de Q. " + montoRetirar(doscientostext.getText(), cientext.getText(), cincuentatext.getText(), veintetext.getText(), dieztext.getText()));

                voucher();
                try {
                    FileOutputStream out = new FileOutputStream("cajero.data");
                    ObjectOutputStream cajerofile = new ObjectOutputStream(out);

                    cajerofile.writeObject(cajero);

                } catch (Exception a) {

                }
                try {
                    FileOutputStream out = new FileOutputStream("alumnos.data");
                    ObjectOutputStream alumnosarray = new ObjectOutputStream(out);

                    alumnosarray.writeObject(alumnos);

                } catch (Exception a) {
                    JOptionPane.showMessageDialog(null, "missing file alumnos.data");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede realizar la transacción, intenta mas tarde");
            }

        } else if (e.getSource() == nuevocliente) {
            //deposito a amigo
            depositar();

        } else if (e.getSource() == datoscuenta) {
            //datos cuenta

        } else if (e.getSource() == salir) {
            //cerrar sesion
            Cajero cajero = new Cajero();
            this.setVisible(false);
        }

    }

    private void voucher() {

        FileWriter filewriter = null;
        PrintWriter printw = null;
        Calendar calendario = new GregorianCalendar();

        try {
            filewriter = new FileWriter("voucher.html");//declarar el archivo
            printw = new PrintWriter(filewriter);//declarar un impresor

            printw.println("<html>");
            printw.println("<head><title>Voucher</title></head>");
            /*
            si queremos escribir una comilla " en el 
            archivo uzamos la diagonal invertida \" 
             */
            printw.println("<body bgcolor=\"#99CC99\">");
            /*
            si quisieramos escribir una cadena que vide de una lista o 
            de una variable lo concatenamos
             */
            printw.println("<center><h1><font color=\"navy\">" + retiralabel.getText() + "</font></h1></center>");
            printw.println("<center><h1><font color=\"navy\">" + montolabel.getText() + "</font></h1></center>");
            printw.println("<center><h1><font color=\"navy\">" + "Rerirada en: 2017/" + (1 + calendario.get(Calendar.MONTH)) + "/" + calendario.get(Calendar.DAY_OF_MONTH) + " a las " + calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE) + "</font></h1></center>");
            printw.println("</body>");
            printw.println("</html>");

            //no devemos olvidar cerrar el archivo para que su lectura sea correcta
            printw.close();//cerramos el archivo
        } catch (Exception e) {

        }
    }

    private void depositar() {
        JFrame frame = new JFrame();
        String depositado = JOptionPane.showInputDialog(frame, "Ingresa el usuario al que deseas depositar", "Deposito bancario", JOptionPane.QUESTION_MESSAGE);
        if (depositado == null) {
            JOptionPane.showMessageDialog(null, "Debes ingresar un nombre");
        } else {
            int i;
            for (i = 0; i < alumnos.length; i++) {

                try {
                    if (alumnos[i].getUsuario().equals(depositado)) {
                        if (alumnos[index].getMontoactual() >= montoRetirar(doscientostext.getText(), cientext.getText(), cincuentatext.getText(), veintetext.getText(), dieztext.getText())
                                && alumnos[i].getMontomax() >= alumnos[i].getMontoactual() + montoRetirar(doscientostext.getText(), cientext.getText(), cincuentatext.getText(), veintetext.getText(), dieztext.getText())) {

                            //le añadimos el monto al que deseamos depositar
                            alumnos[i].setMontoactual(alumnos[i].getMontoactual() + montoRetirar(doscientostext.getText(), cientext.getText(), cincuentatext.getText(), veintetext.getText(), dieztext.getText()));
                            //le quitamos al usuario actual dinero de su cuenta
                            alumnos[index].setMontoactual(alumnos[index].getMontoactual() - montoRetirar(doscientostext.getText(), cientext.getText(), cincuentatext.getText(), veintetext.getText(), dieztext.getText()));
                            JOptionPane.showMessageDialog(null, "Transacción satisfactoria!");
                            montolabel.setText("Bienvenido " + alumnos[index].getUsuario() + "! Tu saldo actual es de Q. " + alumnos[index].getMontoactual());
                            retiralabel.setText("La transacción actual tiene el monto de Q. " + montoRetirar(doscientostext.getText(), cientext.getText(), cincuentatext.getText(), veintetext.getText(), dieztext.getText()));
                            
                            try {
                                FileOutputStream out = new FileOutputStream("alumnos.data");
                                ObjectOutputStream alumnosarray = new ObjectOutputStream(out);

                                alumnosarray.writeObject(alumnos);

                            } catch (Exception a) {
                            }
                            
                            voucher();

                        } else {
                            JOptionPane.showMessageDialog(frame, "No tienes suficientes fondos y/o el usuario no puede aceptar la transacción", "Advertencia", JOptionPane.WARNING_MESSAGE);

                        }

                        i = alumnos.length; //dejar de recorrer el arreglo para mejor optimización
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "No se encontró tal usuario.");
                    i = alumnos.length;
                }
            }
        }
    }
}
