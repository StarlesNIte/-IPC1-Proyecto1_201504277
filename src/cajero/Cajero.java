package cajero;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Cajero extends JFrame implements ActionListener, Serializable {

    JLabel labelusuario, labelnombre, labelcontrasena, labelcuenta;
    JTextField textusuario, textnombre, textcuenta;
    JPasswordField contrasenatext;
    JPanel paneletiquetas;
    JButton registrarbutton;
    JRadioButton adminradio, clienteradio;
    ButtonGroup admincliente;
    Alumno[] alumnos;

    public Cajero() {
        super("Ingreso/Registro - Cajero estudiantil USAC");
        this.setVisible(true);
        this.setSize(425, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/icono.jfif")).getImage());
        
        try {
            ObjectInputStream read = new ObjectInputStream(new FileInputStream("alumnos.data"));
            alumnos = (Alumno[]) read.readObject();
        } catch (Exception e) {
            alumnos = new Alumno[100];
        }

        registrarbutton = new JButton("Entrar");
        registrarbutton.setBounds(165, 200, 100, 40);
        registrarbutton.addActionListener(this);
        add(registrarbutton);

        labelusuario = new JLabel();
        labelusuario.setText("Usuario");
        labelusuario.setBounds(50, 50, 150, 30);
        add(labelusuario);

        labelcontrasena = new JLabel();
        labelcontrasena.setText("Contraseña");
        labelcontrasena.setBounds(50, 100, 150, 30);
        add(labelcontrasena);

        textusuario = new JTextField();
        textusuario.setText("");
        textusuario.setBounds(175, 50, 200, 30);
        add(textusuario);

        contrasenatext = new JPasswordField();
        contrasenatext.setBounds(175, 100, 200, 30);
        add(contrasenatext);

        admincliente = new ButtonGroup();

        clienteradio = new JRadioButton("Cliente", true);
        clienteradio.setBounds(135, 150, 100, 30);
        clienteradio.addActionListener(this);
        admincliente.add(clienteradio);
        add(clienteradio);

        adminradio = new JRadioButton("Admin", false);
        adminradio.setBounds(215, 150, 100, 30);
        adminradio.addActionListener(this);
        admincliente.add(adminradio);
        add(adminradio);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registrarbutton) {

            if (adminradio.isSelected() && textusuario.getText().equals("ipc1Admin") && contrasenatext.getText().equals("aux1")) {
                Admin admin = new Admin();
                this.setVisible(false);
            } else if (clienteradio.isSelected()) {

                try {

                    int i;

                    for (i = 0; i < alumnos.length; i++) {
                        if (textusuario.getText().equals(alumnos[i].getUsuario()) && contrasenatext.getText().equals(alumnos[i].getPassword())) {
                            TransaccionCliente tc = new TransaccionCliente(i);
                            JOptionPane.showMessageDialog(null, "Log in!");
                            this.setVisible(false);
                            i = alumnos.length;
                        }
                    }

                } catch (Exception o) {
                    JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos");
            }

        } else if (e.getSource() == clienteradio) {
            clienteradio.setSelected(true);
            adminradio.setSelected(false);
        } else if (e.getSource() == adminradio) {
            clienteradio.setSelected(false);
            adminradio.setSelected(true);
        }
    }

    public static void main(String[] args) {
        new Cajero();
    }

}
