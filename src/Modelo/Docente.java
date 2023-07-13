
package Modelo;

import java.sql.Date;

public class Docente extends Empleado{
    private int doc_codigo,doc_codemp;
    private String doc_especialidad,doc_estado;

    public Docente() {
    }

    public Docente(int doc_codigo, int doc_codemp, String doc_especialidad, String doc_estado) {
        this.doc_codigo = doc_codigo;
        this.doc_codemp = doc_codemp;
        this.doc_especialidad = doc_especialidad;
        this.doc_estado = doc_estado;
    }

    public Docente(int doc_codigo, int doc_codemp, String doc_especialidad, String doc_estado, int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario) {
        super(emp_codigo, emp_codper, emp_fechacontratacion, emp_salario);
        this.doc_codigo = doc_codigo;
        this.doc_codemp = doc_codemp;
        this.doc_especialidad = doc_especialidad;
        this.doc_estado = doc_estado;
    }

    public Docente(int doc_codigo, int doc_codemp, String doc_especialidad, String doc_estado, int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario, int per_codigo, String per_cedula, String per_primernom, String per_segundonom, String per_apellidopater, String per_apellidomater, java.util.Date per_fechanac, String per_genero, String per_telefono, String per_email, String per_direccion) {
        super(emp_codigo, emp_codper, emp_fechacontratacion, emp_salario, per_codigo, per_cedula, per_primernom, per_segundonom, per_apellidopater, per_apellidomater, per_fechanac, per_genero, per_telefono, per_email, per_direccion);
        this.doc_codigo = doc_codigo;
        this.doc_codemp = doc_codemp;
        this.doc_especialidad = doc_especialidad;
        this.doc_estado = doc_estado;
    }

    public int getDoc_codigo() {
        return doc_codigo;
    }

    public void setDoc_codigo(int doc_codigo) {
        this.doc_codigo = doc_codigo;
    }

    public int getDoc_codemp() {
        return doc_codemp;
    }

    public void setDoc_codemp(int doc_codemp) {
        this.doc_codemp = doc_codemp;
    }

    public String getDoc_especialidad() {
        return doc_especialidad;
    }

    public void setDoc_especialidad(String doc_especialidad) {
        this.doc_especialidad = doc_especialidad;
    }

    public String getDoc_estado() {
        return doc_estado;
    }

    public void setDoc_estado(String doc_estado) {
        this.doc_estado = doc_estado;
    }
}
