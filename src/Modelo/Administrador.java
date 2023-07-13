
package Modelo;

import java.sql.Date;


public class Administrador extends Empleado {
    private int adm_codemp, adm_codigo;
    private String adm_usuario, adm_clave, adm_estado;

    public Administrador() {
    }

    public Administrador(int adm_copemp, int adm_codigo, String adm_usuario, String adm_clave, String adm_estado) {
        this.adm_codemp = adm_copemp;
        this.adm_codigo = adm_codigo;
        this.adm_usuario = adm_usuario;
        this.adm_clave = adm_clave;
        this.adm_estado = adm_estado;
    }

    public Administrador(int adm_copemp, int adm_codigo, String adm_usuario, String adm_clave, String adm_estado, int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario) {
        super(emp_codigo, emp_codper, emp_fechacontratacion, emp_salario);
        this.adm_codemp = adm_copemp;
        this.adm_codigo = adm_codigo;
        this.adm_usuario = adm_usuario;
        this.adm_clave = adm_clave;
        this.adm_estado = adm_estado;
    }

    public Administrador(int adm_copemp, int adm_codigo, String adm_usuario, String adm_clave, String adm_estado, int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario, int per_codigo, String per_cedula, String per_primernom, String per_segundonom, String per_apellidopater, String per_apellidomater, java.util.Date per_fechanac, String per_genero, String per_telefono, String per_email, String per_direccion) {
        super(emp_codigo, emp_codper, emp_fechacontratacion, emp_salario, per_codigo, per_cedula, per_primernom, per_segundonom, per_apellidopater, per_apellidomater, per_fechanac, per_genero, per_telefono, per_email, per_direccion);
        this.adm_codemp = adm_copemp;
        this.adm_codigo = adm_codigo;
        this.adm_usuario = adm_usuario;
        this.adm_clave = adm_clave;
        this.adm_estado = adm_estado;
    }

    public int getAdm_codemp() {
        return adm_codemp;
    }

    public void setAdm_codemp(int adm_codemp) {
        this.adm_codemp = adm_codemp;
    }

    public int getAdm_codigo() {
        return adm_codigo;
    }

    public void setAdm_codigo(int adm_codigo) {
        this.adm_codigo = adm_codigo;
    }

    public String getAdm_usuario() {
        return adm_usuario;
    }

    public void setAdm_usuario(String adm_usuario) {
        this.adm_usuario = adm_usuario;
    }

    public String getAdm_clave() {
        return adm_clave;
    }

    public void setAdm_clave(String adm_clave) {
        this.adm_clave = adm_clave;
    }

    public String getAdm_estado() {
        return adm_estado;
    }

    public void setAdm_estado(String adm_estado) {
        this.adm_estado = adm_estado;
    }
    
    
}
