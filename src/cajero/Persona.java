/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cajero;

import java.io.Serializable;

/**
 *
 * @author LuisMiguel
 */
public class Persona implements Serializable {

    
    private String nombre;
    private String usuario;
    private String password;
    private int doscientos;
    private int cien;
    private int cincuenta;
    private int veinte;
    private int diez;
    
    /* public Persona() {
        this.nombre = "";
        this.usuario = "";
        this.password = "";
        this.doscientos = 0;
        this.cien = 0;
        this.cincuenta = 0;
        this.veinte = 0;
        this.diez = 0;
    } */

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the doscientos
     */
    public int getDoscientos() {
        return doscientos;
    }

    /**
     * @param doscientos the doscientos to set
     */
    public void setDoscientos(int doscientos) {
        this.doscientos = doscientos;
    }

    /**
     * @return the cien
     */
    public int getCien() {
        return cien;
    }

    /**
     * @param cien the cien to set
     */
    public void setCien(int cien) {
        this.cien = cien;
    }

    /**
     * @return the cincuenta
     */
    public int getCincuenta() {
        return cincuenta;
    }

    /**
     * @param cincuenta the cincuenta to set
     */
    public void setCincuenta(int cincuenta) {
        this.cincuenta = cincuenta;
    }

    /**
     * @return the veinte
     */
    public int getVeinte() {
        return veinte;
    }

    /**
     * @param veinte the veinte to set
     */
    public void setVeinte(int veinte) {
        this.veinte = veinte;
    }

    /**
     * @return the diez
     */
    public int getDiez() {
        return diez;
    }

    /**
     * @param diez the diez to set
     */
    public void setDiez(int diez) {
        this.diez = diez;
    }
    
    public int getMonto(int doscientos, int cien, int cincuenta, int veinte, int diez){
        return doscientos * 200 + cien * 100 + cincuenta * 50 + veinte * 20 + diez *10;
    }
   
}
