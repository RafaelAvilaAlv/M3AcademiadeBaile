/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Usuario
 */
public class Indumentaria {
    private String ins_nombre,ins_marca,ins_tipo,ins_estado;
    private int ins_codigo,ins_setcodigo;
    private double ins_valor;

    public Indumentaria() {
    }

    public Indumentaria(String ins_nombre, String ins_marca, String ins_tipo, String ins_estado, int ins_codigo, int ins_setcodigo, double ins_valor) {
        this.ins_nombre = ins_nombre;
        this.ins_marca = ins_marca;
        this.ins_tipo = ins_tipo;
        this.ins_estado = ins_estado;
        this.ins_codigo = ins_codigo;
        this.ins_setcodigo = ins_setcodigo;
        this.ins_valor = ins_valor;
    }

    public String getIns_nombre() {
        return ins_nombre;
    }

    public void setIns_nombre(String ins_nombre) {
        this.ins_nombre = ins_nombre;
    }

    public String getIns_marca() {
        return ins_marca;
    }

    public void setIns_marca(String ins_marca) {
        this.ins_marca = ins_marca;
    }

    public String getIns_tipo() {
        return ins_tipo;
    }

    public void setIns_tipo(String ins_tipo) {
        this.ins_tipo = ins_tipo;
    }

    public String getIns_estado() {
        return ins_estado;
    }

    public void setIns_estado(String ins_estado) {
        this.ins_estado = ins_estado;
    }

    public int getIns_codigo() {
        return ins_codigo;
    }

    public void setIns_codigo(int ins_codigo) {
        this.ins_codigo = ins_codigo;
    }

    public int getIns_setcodigo() {
        return ins_setcodigo;
    }

    public void setIns_setcodigo(int ins_setcodigo) {
        this.ins_setcodigo = ins_setcodigo;
    }

    public double getIns_valor() {
        return ins_valor;
    }

    public void setIns_valor(double ins_valor) {
        this.ins_valor = ins_valor;
    }
    
    
}
