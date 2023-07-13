package Modelo;

public class Horario {

    private int hor_codigo;
    private String hor_dia;
    private String hor_horaIni;
    private String hor_horaFin;
    private String hor_estado;

    public Horario() {
    }

    public Horario(int hor_codigo, String hor_dia, String hor_horaIni, String hor_horaFin, String hor_estado) {
        this.hor_codigo = hor_codigo;
        this.hor_dia = hor_dia;
        this.hor_horaIni = hor_horaIni;
        this.hor_horaFin = hor_horaFin;
        this.hor_estado = hor_estado;
    }

    public int getHor_codigo() {
        return hor_codigo;
    }

    public void setHor_codigo(int hor_codigo) {
        this.hor_codigo = hor_codigo;
    }

    public String getHor_dia() {
        return hor_dia;
    }

    public void setHor_dia(String hor_dia) {
        this.hor_dia = hor_dia;
    }

    public String getHor_horaIni() {
        return hor_horaIni;
    }

    public void setHor_horaIni(String hor_horaIni) {
        this.hor_horaIni = hor_horaIni;
    }

    public String getHor_horaFin() {
        return hor_horaFin;
    }

    public void setHor_horaFin(String hor_horaFin) {
        this.hor_horaFin = hor_horaFin;
    }

    public String getHor_estado() {
        return hor_estado;
    }

    public void setHor_estado(String hor_estado) {
        this.hor_estado = hor_estado;
    }
}
