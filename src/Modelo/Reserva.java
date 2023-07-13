package Modelo;

import java.sql.Date;

public class Reserva {

    private int res_codigo, res_codestudiante, res_codset;
    private String res_especificacion, res_estado;
    private Date res_fechareser, res_fechaentra;

    public Reserva() {
    }

    public Reserva(int res_codigo, int res_codestudiante, int res_codset, String res_especificacion, String res_estado, Date res_fechareser, Date res_fechaentra) {
        this.res_codigo = res_codigo;
        this.res_codestudiante = res_codestudiante;
        this.res_codset = res_codset;
        this.res_especificacion = res_especificacion;
        this.res_estado = res_estado;
        this.res_fechareser = res_fechareser;
        this.res_fechaentra = res_fechaentra;
    }

    public int getRes_codigo() {
        return res_codigo;
    }

    public void setRes_codigo(int res_codigo) {
        this.res_codigo = res_codigo;
    }

    public int getRes_codestudiante() {
        return res_codestudiante;
    }

    public void setRes_codestudiante(int res_codestudiante) {
        this.res_codestudiante = res_codestudiante;
    }

    public int getRes_codset() {
        return res_codset;
    }

    public void setRes_codset(int res_codset) {
        this.res_codset = res_codset;
    }

    public String getRes_especificacion() {
        return res_especificacion;
    }

    public void setRes_especificacion(String res_especificacion) {
        this.res_especificacion = res_especificacion;
    }

    public String getRes_estado() {
        return res_estado;
    }

    public void setRes_estado(String res_estado) {
        this.res_estado = res_estado;
    }

    public Date getRes_fechareser() {
        return res_fechareser;
    }

    public void setRes_fechareser(Date res_fechareser) {
        this.res_fechareser = res_fechareser;
    }

    public Date getRes_fechaentra() {
        return res_fechaentra;
    }

    public void setRes_fechaentra(Date res_fechaentra) {
        this.res_fechaentra = res_fechaentra;
    }
}
