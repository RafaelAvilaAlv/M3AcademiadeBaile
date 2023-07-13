package Modelo;

import java.util.Date;

public class Matricula {

    private int mat_codigo;
    private int mat_codAmd;
    private int mat_codEst;
    private int mat_codCur;
    private Date mat_fechamatri;
    private String mat_estado;

    public Matricula() {
    }

    public Matricula(int mat_codigo, int mat_codAmd, int mat_codEst, int mat_codCur, Date mat_fechamatri, String mat_estado) {
        this.mat_codigo = mat_codigo;
        this.mat_codAmd = mat_codAmd;
        this.mat_codEst = mat_codEst;
        this.mat_codCur = mat_codCur;
        this.mat_fechamatri = mat_fechamatri;
        this.mat_estado = mat_estado;
    }

    public int getMat_codigo() {
        return mat_codigo;
    }

    public void setMat_codigo(int mat_codigo) {
        this.mat_codigo = mat_codigo;
    }

    public int getMat_codAmd() {
        return mat_codAmd;
    }

    public void setMat_codAmd(int mat_codAmd) {
        this.mat_codAmd = mat_codAmd;
    }

    public int getMat_codEst() {
        return mat_codEst;
    }

    public void setMat_codEst(int mat_codEst) {
        this.mat_codEst = mat_codEst;
    }

    public int getMat_codCur() {
        return mat_codCur;
    }

    public void setMat_codCur(int mat_codCur) {
        this.mat_codCur = mat_codCur;
    }

    public Date getMat_fechamatri() {
        return mat_fechamatri;
    }

    public void setMat_fechamatri(Date mat_fechamatri) {
        this.mat_fechamatri = mat_fechamatri;
    }

    public String getMat_estado() {
        return mat_estado;
    }

    public void setMat_estado(String mat_estado) {
        this.mat_estado = mat_estado;
    }

}
