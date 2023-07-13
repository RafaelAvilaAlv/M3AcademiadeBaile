/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class AsigAula {
    
    private int asia_codigo,asia_codcurso,asia_codaula;
    private Date asia_fecha;
    private String asi_estado;

    public AsigAula() {
    }

    public AsigAula(int asia_codigo, int asia_codcurso, int asia_codaula, Date asia_fecha, String asi_estado) {
        this.asia_codigo = asia_codigo;
        this.asia_codcurso = asia_codcurso;
        this.asia_codaula = asia_codaula;
        this.asia_fecha = asia_fecha;
        this.asi_estado = asi_estado;
    }

    public int getAsia_codigo() {
        return asia_codigo;
    }

    public void setAsia_codigo(int asia_codigo) {
        this.asia_codigo = asia_codigo;
    }

    public int getAsia_codcurso() {
        return asia_codcurso;
    }

    public void setAsia_codcurso(int asia_codcurso) {
        this.asia_codcurso = asia_codcurso;
    }

    public int getAsia_codaula() {
        return asia_codaula;
    }

    public void setAsia_codaula(int asia_codaula) {
        this.asia_codaula = asia_codaula;
    }

    public Date getAsia_fecha() {
        return asia_fecha;
    }

    public void setAsia_fecha(Date asia_fecha) {
        this.asia_fecha = asia_fecha;
    }

    public String getAsi_estado() {
        return asi_estado;
    }

    public void setAsi_estado(String asi_estado) {
        this.asi_estado = asi_estado;
    } 
    
}
