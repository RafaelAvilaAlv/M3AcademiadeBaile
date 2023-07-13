/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

 
public class SetGrabacion {
    private int set_codigo;
    private String set_tamanio,set_nombre,set_ubicacion,set_estado;

    public SetGrabacion() {
    }

    public SetGrabacion(int set_codigo, String set_tamanio, String set_nombre, String set_ubicacion, String set_estado) {
        this.set_codigo = set_codigo;
        this.set_tamanio = set_tamanio;
        this.set_nombre = set_nombre;
        this.set_ubicacion = set_ubicacion;
        this.set_estado = set_estado;
    }

    public int getSet_codigo() {
        return set_codigo;
    }

    public void setSet_codigo(int set_codigo) {
        this.set_codigo = set_codigo;
    }

    public String getSet_tamanio() {
        return set_tamanio;
    }

    public void setSet_tamanio(String set_tamanio) {
        this.set_tamanio = set_tamanio;
    }

    public String getSet_nombre() {
        return set_nombre;
    }

    public void setSet_nombre(String set_nombre) {
        this.set_nombre = set_nombre;
    }

    public String getSet_ubicacion() {
        return set_ubicacion;
    }

    public void setSet_ubicacion(String set_ubicacion) {
        this.set_ubicacion = set_ubicacion;
    }

    public String getSet_estado() {
        return set_estado;
    }

    public void setSet_estado(String set_estado) {
        this.set_estado = set_estado;
    }
    
    
}
