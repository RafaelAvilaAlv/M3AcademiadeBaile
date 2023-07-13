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
public class Estudiante extends Persona {

    private int est_codigo, est_codper;
    private String est_estado;

    public Estudiante() {
    }

    public Estudiante(int est_codigo, int est_codper, String est_estado) {
        this.est_codigo = est_codigo;
        this.est_codper = est_codper;
        this.est_estado = est_estado;
    }

    public Estudiante(int est_codigo, int est_codper, String est_estado, int per_codigo, String per_cedula, String per_primernom, String per_segundonom, String per_apellidopater, String per_apellidomater, Date per_fechanac, String per_genero, String per_telefono, String per_email, String per_direccion) {
        super(per_codigo, per_cedula, per_primernom, per_segundonom, per_apellidopater, per_apellidomater, per_fechanac, per_genero, per_telefono, per_email, per_direccion);
        this.est_codigo = est_codigo;
        this.est_codper = est_codper;
        this.est_estado = est_estado;
    }

    public int getEst_codigo() {
        return est_codigo;
    }

    public void setEst_codigo(int est_codigo) {
        this.est_codigo = est_codigo;
    }

    public int getEst_codper() {
        return est_codper;
    }

    public void setEst_codper(int est_codper) {
        this.est_codper = est_codper;
    }

    public String getEst_estado() {
        return est_estado;
    }

    public void setEst_estado(String est_estado) {
        this.est_estado = est_estado;
    }
    
    
    
}
