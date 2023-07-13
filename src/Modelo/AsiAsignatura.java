package Modelo;

import java.util.Date;

public class AsiAsignatura {

    private int asig_codigo;
    private int asig_coddoc;
    private int asig_codasi;
    private Date asig_fecha;

    public AsiAsignatura() {
    }

    public AsiAsignatura(int asig_codigo, int asig_coddoc, int asig_codasi, Date asig_fecha) {
        this.asig_codigo = asig_codigo;
        this.asig_coddoc = asig_coddoc;
        this.asig_codasi = asig_codasi;
        this.asig_fecha = asig_fecha;
    }

    public int getAsig_codigo() {
        return asig_codigo;
    }

    public void setAsig_codigo(int asig_codigo) {
        this.asig_codigo = asig_codigo;
    }

    public int getAsig_coddoc() {
        return asig_coddoc;
    }

    public void setAsig_coddoc(int asig_coddoc) {
        this.asig_coddoc = asig_coddoc;
    }

    public int getAsig_codasi() {
        return asig_codasi;
    }

    public void setAsig_codasi(int asig_codasi) {
        this.asig_codasi = asig_codasi;
    }

    public Date getAsig_fecha() {
        return asig_fecha;
    }

    public void setAsig_fecha(Date asig_fecha) {
        this.asig_fecha = asig_fecha;
    }
}
