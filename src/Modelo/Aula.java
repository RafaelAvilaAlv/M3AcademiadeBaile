package Modelo;

public class Aula {

    private int aul_codigo, aul_capacidad;
    private String aul_nombre, aul_ubicacion, aul_descripcion, aul_estado;

    public Aula() {
    }

    public Aula(int aul_codigo, int aul_capacidad, String aul_nombre, String aul_ubicacion, String aul_descripcion, String aul_estado) {
        this.aul_codigo = aul_codigo;
        this.aul_capacidad = aul_capacidad;
        this.aul_nombre = aul_nombre;
        this.aul_ubicacion = aul_ubicacion;
        this.aul_descripcion = aul_descripcion;
        this.aul_estado = aul_estado;
    }

    public int getAul_codigo() {
        return aul_codigo;
    }

    public void setAul_codigo(int aul_codigo) {
        this.aul_codigo = aul_codigo;
    }

    public int getAul_capacidad() {
        return aul_capacidad;
    }

    public void setAul_capacidad(int aul_capacidad) {
        this.aul_capacidad = aul_capacidad;
    }

    public String getAul_nombre() {
        return aul_nombre;
    }

    public void setAul_nombre(String aul_nombre) {
        this.aul_nombre = aul_nombre;
    }

    public String getAul_ubicacion() {
        return aul_ubicacion;
    }

    public void setAul_ubicacion(String aul_ubicacion) {
        this.aul_ubicacion = aul_ubicacion;
    }

    public String getAul_descripcion() {
        return aul_descripcion;
    }

    public void setAul_descripcion(String aul_descripcion) {
        this.aul_descripcion = aul_descripcion;
    }

    public String getAul_estado() {
        return aul_estado;
    }

    public void setAul_estado(String aul_estado) {
        this.aul_estado = aul_estado;
    }
}
