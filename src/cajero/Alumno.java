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
public class Alumno extends Persona implements Serializable{
    private Boolean banco;
    private int montomax;
    private int montoactual;
    

    /**
     * @return the banco
     */
    
    public Boolean getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(Boolean banco) {
        this.banco = banco;
    }

    /**
     * @return the montomax
     */
    public int getMontomax() {
        return montomax;
    }

    /**
     * @param montomax the montomax to set
     */
    public void setMontomax(int montomax) {
        this.montomax = montomax;
    }

    /**
     * @return the trasferencias
     */

    /**
     * @return the montoactual
     */
    public int getMontoactual() {
        return montoactual;
    }

    /**
     * @param montoactual the montoactual to set
     */
    public void setMontoactual(int montoactual) {
        this.montoactual = montoactual;
    }

    public String getStringBanco(Boolean banco) {
        if (banco == true) {
            return "Cash-money";
        } else {
            return "Pro-money";
        }
    }
}
