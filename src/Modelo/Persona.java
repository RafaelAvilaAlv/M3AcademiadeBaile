package Modelo;

import java.util.Date;

public class Persona {

    private int per_codigo;
    private String per_cedula;
    private String per_primernom;
    private String per_segundonom;
    private String per_apellidopater;
    private String per_apellidomater;
    private Date per_fechanac;
    private String per_genero;
    private String per_telefono;
    private String per_email;
    private String per_direccion;

    public Persona() {
    }

    public Persona(int per_codigo, String per_cedula, String per_primernom, String per_segundonom, String per_apellidopater, String per_apellidomater, Date per_fechanac, String per_genero, String per_telefono, String per_email, String per_direccion) {
        this.per_codigo = per_codigo;
        this.per_cedula = per_cedula;
        this.per_primernom = per_primernom;
        this.per_segundonom = per_segundonom;
        this.per_apellidopater = per_apellidopater;
        this.per_apellidomater = per_apellidomater;
        this.per_fechanac = per_fechanac;
        this.per_genero = per_genero;
        this.per_telefono = per_telefono;
        this.per_email = per_email;
        this.per_direccion = per_direccion;
    }

    public int getPer_codigo() {
        return per_codigo;
    }

    public void setPer_codigo(int per_codigo) {
        this.per_codigo = per_codigo;
    }

    public String getPer_cedula() {
        return per_cedula;
    }

    public void setPer_cedula(String per_cedula) {
        this.per_cedula = per_cedula;
    }

    public String getPer_primernom() {
        return per_primernom;
    }

    public void setPer_primernom(String per_primernom) {
        this.per_primernom = per_primernom;
    }

    public String getPer_segundonom() {
        return per_segundonom;
    }

    public void setPer_segundonom(String per_segundonom) {
        this.per_segundonom = per_segundonom;
    }

    public String getPer_apellidopater() {
        return per_apellidopater;
    }

    public void setPer_apellidopater(String per_apellidopater) {
        this.per_apellidopater = per_apellidopater;
    }

    public String getPer_apellidomater() {
        return per_apellidomater;
    }

    public void setPer_apellidomater(String per_apellidomater) {
        this.per_apellidomater = per_apellidomater;
    }

    public Date getPer_fechanac() {
        return per_fechanac;
    }

    public void setPer_fechanac(Date per_fechanac) {
        this.per_fechanac = per_fechanac;
    }

    public String getPer_genero() {
        return per_genero;
    }

    public void setPer_genero(String per_genero) {
        this.per_genero = per_genero;
    }

    public String getPer_telefono() {
        return per_telefono;
    }

    public void setPer_telefono(String per_telefono) {
        this.per_telefono = per_telefono;
    }

    public String getPer_email() {
        return per_email;
    }

    public void setPer_email(String per_email) {
        this.per_email = per_email;
    }

    public String getPer_direccion() {
        return per_direccion;
    }

    public void setPer_direccion(String per_direccion) {
        this.per_direccion = per_direccion;
    }
}
