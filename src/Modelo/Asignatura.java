package Modelo;

public class Asignatura {

    private int asi_codigo;
    private int asi_codCurso;
    private String asi_nombre;
    private String asi_descripcion;
    private String asi_estado;

    public Asignatura() {
    }

    public Asignatura(int asi_codigo, int asi_codCurso, String asi_nombre, String asi_descripcion, String asi_estado) {
        this.asi_codigo = asi_codigo;
        this.asi_codCurso = asi_codCurso;
        this.asi_nombre = asi_nombre;
        this.asi_descripcion = asi_descripcion;
        this.asi_estado = asi_estado;
    }

    public int getAsi_codigo() {
        return asi_codigo;
    }

    public void setAsi_codigo(int asi_codigo) {
        this.asi_codigo = asi_codigo;
    }

    public int getAsi_codCurso() {
        return asi_codCurso;
    }

    public void setAsi_codCurso(int asi_codCurso) {
        this.asi_codCurso = asi_codCurso;
    }

    public String getAsi_nombre() {
        return asi_nombre;
    }

    public void setAsi_nombre(String asi_nombre) {
        this.asi_nombre = asi_nombre;
    }

    public String getAsi_descripcion() {
        return asi_descripcion;
    }

    public void setAsi_descripcion(String asi_descripcion) {
        this.asi_descripcion = asi_descripcion;
    }

    public String getAsi_estado() {
        return asi_estado;
    }

    public void setAsi_estado(String asi_estado) {
        this.asi_estado = asi_estado;
    }
}
