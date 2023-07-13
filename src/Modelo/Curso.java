package Modelo;

import java.util.Date;

public class Curso {

    private int cur_codigo;
    private String cur_nombre;
    private String cur_periodo;
    private Date cur_fechaInicio;
    private Date cur_fechaFin;
    private String cur_descripcion;
    private double cur_precio;
    private String cur_estado;

    public Curso() {
    }

    public Curso(int cur_codigo, String cur_nombre, String cur_periodo, Date cur_fechaInicio, Date cur_fechaFin, String cur_descripcion, double cur_precio, String cur_estado) {
        this.cur_codigo = cur_codigo;
        this.cur_nombre = cur_nombre;
        this.cur_periodo = cur_periodo;
        this.cur_fechaInicio = cur_fechaInicio;
        this.cur_fechaFin = cur_fechaFin;
        this.cur_descripcion = cur_descripcion;
        this.cur_precio = cur_precio;
        this.cur_estado = cur_estado;
    }

    public int getCur_codigo() {
        return cur_codigo;
    }

    public void setCur_codigo(int cur_codigo) {
        this.cur_codigo = cur_codigo;
    }

    public String getCur_nombre() {
        return cur_nombre;
    }

    public void setCur_nombre(String cur_nombre) {
        this.cur_nombre = cur_nombre;
    }

    public String getCur_periodo() {
        return cur_periodo;
    }

    public void setCur_periodo(String cur_periodo) {
        this.cur_periodo = cur_periodo;
    }

    public Date getCur_fechaInicio() {
        return cur_fechaInicio;
    }

    public void setCur_fechaInicio(Date cur_fechaInicio) {
        this.cur_fechaInicio = cur_fechaInicio;
    }

    public Date getCur_fechaFin() {
        return cur_fechaFin;
    }

    public void setCur_fechaFin(Date cur_fechaFin) {
        this.cur_fechaFin = cur_fechaFin;
    }

    public String getCur_descripcion() {
        return cur_descripcion;
    }

    public void setCur_descripcion(String cur_descripcion) {
        this.cur_descripcion = cur_descripcion;
    }

    public double getCur_precio() {
        return cur_precio;
    }

    public void setCur_precio(double cur_precio) {
        this.cur_precio = cur_precio;
    }

    public String getCur_estado() {
        return cur_estado;
    }

    public void setCur_estado(String cur_estado) {
        this.cur_estado = cur_estado;
    }
}
