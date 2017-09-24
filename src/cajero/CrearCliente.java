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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author LuisMiguel
 */
public class CrearCliente extends JFrame implements ActionListener, Serializable{

    private JPanel panelprincipal, panelinferior, radiobuttons;
    private JLabel nombrelabel, usuariolabel, passwordlabel, montomaxlabel, montoactuallabel, banco;
    private JTextField nombretext, usuariotext, montomaxtext, montoactualtext;
    private JPasswordField passwordtext;
    private ButtonGroup grupo1;
    private JRadioButton cashmoney, propisto;
    private JButton guardar, cancelar;
    private int contador;
    private Alumno[] alumnos;

    public CrearCliente() {

        super("Añadir alumno - USAC");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setSize(450,325);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10));
        this.setIconImage(new ImageIcon(getClass().getResource("/icono.jfif")).getImage());

        contador = 0;
        alumnos = new Alumno[100];

        try {
            ObjectInputStream read = new ObjectInputStream(new FileInputStream("alumnos.data"));
            alumnos = (Alumno[]) read.readObject();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se ha creado un archivo de record");
        }
        try {
            ObjectInputStream r = new ObjectInputStream(new FileInputStream("contador.data"));
            contador = (Integer) r.readObject();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se ha creado un archivo de record");
        }

        panelprincipal = new JPanel();
        panelprincipal.setLayout(new GridLayout(0, 2, 10, 10));
        this.add(panelprincipal, BorderLayout.CENTER);

        nombrelabel = new JLabel();
        nombrelabel.setText("Nombre");
        panelprincipal.add(nombrelabel);

        nombretext = new JTextField();
        panelprincipal.add(nombretext);

        usuariolabel = new JLabel();
        usuariolabel.setText("Usuario");
        panelprincipal.add(usuariolabel);

        usuariotext = new JTextField();
        panelprincipal.add(usuariotext);

        passwordlabel = new JLabel();
        passwordlabel.setText("Password");
        panelprincipal.add(passwordlabel);

        passwordtext = new JPasswordField();
        panelprincipal.add(passwordtext);

        montoactuallabel = new JLabel();
        montoactuallabel.setText("Monto inicial (Q)");
        panelprincipal.add(montoactuallabel);

        montoactualtext = new JTextField();
        panelprincipal.add(montoactualtext);

        montomaxlabel = new JLabel();
        montomaxlabel.setText("Monto máximo (Q) ");
        panelprincipal.add(montomaxlabel);

        montomaxtext = new JTextField();
        panelprincipal.add(montomaxtext);

        banco = new JLabel();
        banco.setText("Banco");
        panelprincipal.add(banco);

        radiobuttons = new JPanel();
        radiobuttons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelprincipal.add(radiobuttons);

        grupo1 = new ButtonGroup();

        cashmoney = new JRadioButton("Cash-Money", true);
        cashmoney.addActionListener(this);
        grupo1.add(cashmoney);
        radiobuttons.add(cashmoney);

        propisto = new JRadioButton("Cash-Money", false);
        propisto.addActionListener(this);
        grupo1.add(propisto);
        radiobuttons.add(propisto);

        panelinferior = new JPanel();
        panelinferior.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.add(panelinferior, BorderLayout.SOUTH);

        guardar = new JButton("Guardar");
        guardar.addActionListener(this);
        panelinferior.add(guardar);

        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(this);
        panelinferior.add(cancelar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cashmoney) {
            cashmoney.setSelected(true);
            propisto.setSelected(false);

        } else if (e.getSource() == propisto) {
            cashmoney.setSelected(false);
            propisto.setSelected(true);
        } else if (e.getSource() == cancelar) {
            this.setVisible(false);
        } else if (e.getSource() == guardar) {
            guardar();
        }
    }

    private void guardar() {

        int i;
        if (nombretext.getText().equals("") || usuariotext.getText().equals("") || passwordtext.getText().equals("") || montoactualtext.getText().equals("") || montomaxtext.getText().equals("") || Integer.parseInt(montoactualtext.getText()) > Integer.parseInt(montomaxtext.getText())) {
            JOptionPane.showMessageDialog(null, "Valores inválidos, intente de nuevo.");
        } else {
            for (i = 0; i < alumnos.length; i++) {
                if (i == contador) {
                    contador++;

                    alumnos[i] = new Alumno();
                    alumnos[i].setNombre(nombretext.getText());
                    alumnos[i].setUsuario(usuariotext.getText());
                    alumnos[i].setPassword(passwordtext.getText());
                    if (propisto.isSelected()) {
                        alumnos[i].setBanco(false);
                    } else if (cashmoney.isShowing()) {
                        alumnos[i].setBanco(true);
                    }
                    try {
                        alumnos[i].setMontoactual(Integer.parseInt(montoactualtext.getText()));
                        alumnos[i].setMontomax(Integer.parseInt(montomaxtext.getText()));
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }
                    try {
                        FileOutputStream out = new FileOutputStream("alumnos.data");
                        ObjectOutputStream alumnosarray = new ObjectOutputStream(out);

                        alumnosarray.writeObject(alumnos);

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "missing file alumnos.data");
                    }
                    try {
                        FileOutputStream out = new FileOutputStream("contador.data");
                        ObjectOutputStream cont = new ObjectOutputStream(out);

                        cont.writeObject(contador);

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "missing file alumnos.data");
                    }
                    JOptionPane.showMessageDialog(null, "Alumno guardado");
                    this.setVisible(false);
                    i=alumnos.length;
                }
            }
        }
    }

}

/*
contador++;

                        alumnos[i] = new Alumno();
                        alumnos[i].setNombre(nombretext.getText());
                        alumnos[i].setUsuario(usuariotext.getText());
                        alumnos[i].setPassword(passwordtext.getText());
                        if (propisto.isSelected()) {
                            alumnos[i].setBanco(false);
                        } else if (cashmoney.isShowing()) {
                            alumnos[i].setBanco(true);
                        }
                        try {
                            alumnos[i].setMontoactual(Integer.parseInt(montoactualtext.getText()));
                            alumnos[i].setMontomax(Integer.parseInt(montomaxtext.getText()));
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, e);
                        }
                        try {
                            FileOutputStream out = new FileOutputStream("alumnos.data");
                            ObjectOutputStream alumnosarray = new ObjectOutputStream(out);

                            alumnosarray.writeObject(alumnos);

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "missing file alumnos.data");
                        }
                        try {
                            FileOutputStream out = new FileOutputStream("contador.data");
                            ObjectOutputStream cont = new ObjectOutputStream(out);

                            cont.writeObject(contador);

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "missing file alumnos.data");
                        }
*/