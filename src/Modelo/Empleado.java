
package Modelo;

import java.sql.Date;


public class Empleado extends Persona {
    private int emp_codigo,emp_codper;
    private Date emp_fechacontratacion;
    private Double emp_salario;

    public Empleado() {
    }

    public Empleado(int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario) {
        this.emp_codigo = emp_codigo;
        this.emp_codper = emp_codper;
        this.emp_fechacontratacion = emp_fechacontratacion;
        this.emp_salario = emp_salario;
    }

    public Empleado(int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario, int per_codigo, String per_cedula, String per_primernom, String per_segundonom, String per_apellidopater, String per_apellidomater, java.util.Date per_fechanac, String per_genero, String per_telefono, String per_email, String per_direccion) {
        super(per_codigo, per_cedula, per_primernom, per_segundonom, per_apellidopater, per_apellidomater, per_fechanac, per_genero, per_telefono, per_email, per_direccion);
        this.emp_codigo = emp_codigo;
        this.emp_codper = emp_codper;
        this.emp_fechacontratacion = emp_fechacontratacion;
        this.emp_salario = emp_salario;
    }

    public int getEmp_codigo() {
        return emp_codigo;
    }

    public void setEmp_codigo(int emp_codigo) {
        this.emp_codigo = emp_codigo;
    }

    public int getEmp_codper() {
        return emp_codper;
    }

    public void setEmp_codper(int emp_codper) {
        this.emp_codper = emp_codper;
    }

    public Date getEmp_fechacontratacion() {
        return emp_fechacontratacion;
    }

    public void setEmp_fechacontratacion(Date emp_fechacontratacion) {
        this.emp_fechacontratacion = emp_fechacontratacion;
    }

    public Double getEmp_salario() {
        return emp_salario;
    }

    public void setEmp_salario(Double emp_salario) {
        this.emp_salario = emp_salario;
    }
}
