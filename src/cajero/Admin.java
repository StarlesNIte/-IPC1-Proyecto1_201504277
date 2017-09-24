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
public class Admin extends JFrame implements ActionListener, Serializable {
    
    private JMenuBar menu;
    private JMenu archivo, ayuda;
    private JMenuItem guardar, salir, nuevocliente, acercade;
    private JPanel panelprincipal, panelinferior;
    private JLabel tipobilletelabel, cantidadbilletelabel, cienlabel, doscientoslabel, cincuentalabel, veintelabel, diezlabel, montolabel;
    private JTextField doscientostext, cientext, cincuentatext, veintetext, dieztext;
    private Persona cajero;

    public Admin() {
        
        super("Administración cajero - USAC");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setSize(500,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10,10));
        this.setIconImage(new ImageIcon(getClass().getResource("/icono.jfif")).getImage());
        
        cajero = new Persona();
        try{
            ObjectInputStream read = new ObjectInputStream(new FileInputStream("cajero.data"));
            cajero = (Persona) read.readObject();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Se ha creado un archivo de record");
            cajero = new Persona();
        }
        
        menu = new JMenuBar();
        this.setJMenuBar(menu);
        
        archivo = new JMenu("Archivo");
        menu.add(archivo);
        
        ayuda = new JMenu("Ayuda");
        menu.add(ayuda);
        
        guardar = new JMenuItem("Guardar");
        archivo.add(guardar);
        guardar.addActionListener(this);
        
        nuevocliente = new JMenuItem("Añadir nuevo cliente alumno");
        archivo.add(nuevocliente);
        nuevocliente.addActionListener(this);
        
        salir = new JMenuItem("Cerrar sesión");
        archivo.add(salir);
        salir.addActionListener(this);
        
        acercade = new JMenuItem("Acerca de...");
        ayuda.add(acercade);
        acercade.addActionListener(this);
        
        panelprincipal = new JPanel();
        panelprincipal.setLayout(new GridLayout(0,2,10,10));
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
        
        doscientostext = new JTextField(Integer.toString(cajero.getDoscientos()));
        panelprincipal.add(doscientostext);
        
        cienlabel = new JLabel();
        cienlabel.setText("Q. 100");
        panelprincipal.add(cienlabel);
        
        cientext = new JTextField(Integer.toString(cajero.getCien()));
        panelprincipal.add(cientext);
        
        cincuentalabel = new JLabel();
        cincuentalabel.setText("Q. 50");
        panelprincipal.add(cincuentalabel);
        
        cincuentatext = new JTextField(Integer.toString(cajero.getCincuenta()));
        panelprincipal.add(cincuentatext);
        
        veintelabel = new JLabel();
        veintelabel.setText("Q. 20");
        panelprincipal.add(veintelabel);
        
        veintetext = new JTextField(Integer.toString(cajero.getVeinte()));
        panelprincipal.add(veintetext);
        
        diezlabel = new JLabel();
        diezlabel.setText("Q. 10");
        panelprincipal.add(diezlabel);
        
        dieztext = new JTextField(Integer.toString(cajero.getDiez()));
        panelprincipal.add(dieztext);
        
        panelinferior = new JPanel();
        panelinferior.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        this.add(panelinferior, BorderLayout.SOUTH);
        
        montolabel = new JLabel();
        montolabel.setText("El monto total que el cajero posee actualmente es de Q. "+ cajero.getMonto(cajero.getDoscientos(), cajero.getCien(), cajero.getCincuenta(), cajero.getVeinte(), cajero.getDiez()));
        panelinferior.add(montolabel);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nuevocliente) {
            CrearCliente nuevocliente = new CrearCliente();       
        } else if(e.getSource() == acercade) {
            JOptionPane.showMessageDialog(null,"Cajero estudiantil v<1.0> \n"
                    + "by Luis Miguel");
        }else if(e.getSource() == guardar) {
            
            cajero.setDoscientos(Integer.parseInt(doscientostext.getText()));
            cajero.setCien(Integer.parseInt(cientext.getText()));
            cajero.setCincuenta(Integer.parseInt(cincuentatext.getText()));
            cajero.setVeinte(Integer.parseInt(veintetext.getText()));
            cajero.setDiez(Integer.parseInt(dieztext.getText()));
            
            montolabel.setText("El monto total que el cajero posee actualmente es de Q. "+ cajero.getMonto(cajero.getDoscientos(), cajero.getCien(), cajero.getCincuenta(), cajero.getVeinte(), cajero.getDiez()));
            
            try{
                FileOutputStream out = new FileOutputStream("cajero.data");
                ObjectOutputStream cajerofile = new ObjectOutputStream(out);
        
                cajerofile.writeObject(cajero);
                
            }catch(Exception a){
                
            }
            
        } else if(e.getSource() == salir){
            Cajero cajero = new Cajero();
            this.setVisible(false);
        }

    }

}
