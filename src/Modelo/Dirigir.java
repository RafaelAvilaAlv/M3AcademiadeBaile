/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Dirigir {
    private int dir_codigo;
    private int dir_codset;
    private int dir_codpro;
    private Date dir_fecharegistro;
    private String dir_estado;

    public Dirigir() {
    }

    public Dirigir(int dir_codigo, int dir_codset, int dir_codpro, Date dir_fecharegistro, String dir_estado) {
        this.dir_codigo = dir_codigo;
        this.dir_codset = dir_codset;
        this.dir_codpro = dir_codpro;
        this.dir_fecharegistro = dir_fecharegistro;
        this.dir_estado = dir_estado;
    }

    public int getDir_codigo() {
        return dir_codigo;
    }

    public void setDir_codigo(int dir_codigo) {
        this.dir_codigo = dir_codigo;
    }

    public int getDir_codset() {
        return dir_codset;
    }

    public void setDir_codset(int dir_codset) {
        this.dir_codset = dir_codset;
    }

    public int getDir_codpro() {
        return dir_codpro;
    }

    public void setDir_codpro(int dir_codpro) {
        this.dir_codpro = dir_codpro;
    }

    public Date getDir_fecharegistro() {
        return dir_fecharegistro;
    }

    public void setDir_fecharegistro(Date dir_fecharegistro) {
        this.dir_fecharegistro = dir_fecharegistro;
    }

    public String getDir_estado() {
        return dir_estado;
    }

    public void setDir_estado(String dir_estado) {
        this.dir_estado = dir_estado;
    }
    
}
