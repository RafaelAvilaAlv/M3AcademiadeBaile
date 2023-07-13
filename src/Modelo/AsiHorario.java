package Modelo;

import java.util.Date;

public class AsiHorario {

    private int asih_codigo;
    private int asih_codcurso;
    private int asih_codhorario;
    private Date asih_fecha;
    private String asih_estado;

    public AsiHorario() {
    }

    public AsiHorario(int asih_codigo, int asih_codcurso, int asih_codhorario, Date asih_fecha, String asih_estado) {
        this.asih_codigo = asih_codigo;
        this.asih_codcurso = asih_codcurso;
        this.asih_codhorario = asih_codhorario;
        this.asih_fecha = asih_fecha;
        this.asih_estado = asih_estado;
    }

    public int getAsih_codigo() {
        return asih_codigo;
    }

    public void setAsih_codigo(int asih_codigo) {
        this.asih_codigo = asih_codigo;
    }

    public int getAsih_codcurso() {
        return asih_codcurso;
    }

    public void setAsih_codcurso(int asih_codcurso) {
        this.asih_codcurso = asih_codcurso;
    }

    public int getAsih_codhorario() {
        return asih_codhorario;
    }

    public void setAsih_codhorario(int asih_codhorario) {
        this.asih_codhorario = asih_codhorario;
    }

    public Date getAsih_fecha() {
        return asih_fecha;
    }

    public void setAsih_fecha(Date asih_fecha) {
        this.asih_fecha = asih_fecha;
    }

    public String getAsih_estado() {
        return asih_estado;
    }

    public void setAsih_estado(String asih_estado) {
        this.asih_estado = asih_estado;
    }
}
