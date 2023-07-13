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
public class Productor extends Empleado {

    private int pro_codemp, pro_codigo, pro_expe;
    private String pro_estado;

    public Productor() {
    }

    public Productor(int pro_codemp, int pro_codigo, int pro_expe, String pro_estado) {
        this.pro_codemp = pro_codemp;
        this.pro_codigo = pro_codigo;
        this.pro_expe = pro_expe;
        this.pro_estado = pro_estado;
    }

    public Productor(int pro_codemp, int pro_codigo, int pro_expe, String pro_estado, int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario) {
        super(emp_codigo, emp_codper, emp_fechacontratacion, emp_salario);
        this.pro_codemp = pro_codemp;
        this.pro_codigo = pro_codigo;
        this.pro_expe = pro_expe;
        this.pro_estado = pro_estado;
    }

    public Productor(int pro_codemp, int pro_codigo, int pro_expe, String pro_estado, int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario, int per_codigo, String per_cedula, String per_primernom, String per_segundonom, String per_apellidopater, String per_apellidomater, java.util.Date per_fechanac, String per_genero, String per_telefono, String per_email, String per_direccion) {
        super(emp_codigo, emp_codper, emp_fechacontratacion, emp_salario, per_codigo, per_cedula, per_primernom, per_segundonom, per_apellidopater, per_apellidomater, per_fechanac, per_genero, per_telefono, per_email, per_direccion);
        this.pro_codemp = pro_codemp;
        this.pro_codigo = pro_codigo;
        this.pro_expe = pro_expe;
        this.pro_estado = pro_estado;
    }

    public int getPro_codemp() {
        return pro_codemp;
    }

    public void setPro_codemp(int pro_codemp) {
        this.pro_codemp = pro_codemp;
    }

    public int getPro_codigo() {
        return pro_codigo;
    }

    public void setPro_codigo(int pro_codigo) {
        this.pro_codigo = pro_codigo;
    }

    public int getPro_expe() {
        return pro_expe;
    }

    public void setPro_expe(int pro_expe) {
        this.pro_expe = pro_expe;
    }

    public String getPro_estado() {
        return pro_estado;
    }

    public void setPro_estado(String pro_estado) {
        this.pro_estado = pro_estado;
    }
}