package Modelo;

import Conexion.ConexionPG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloPersona extends Persona {

    ConexionPG conpg = new ConexionPG();

    public ModeloPersona() {
    }

    public ModeloPersona(int per_codigo, String per_cedula, String per_primernom, String per_segundonom, String per_apellidopater, String per_apellidomater, Date per_fechanac, String per_genero, String per_telefono, String per_email, String per_direccion) {
        super(per_codigo, per_cedula, per_primernom, per_segundonom, per_apellidopater, per_apellidomater, per_fechanac, per_genero, per_telefono, per_email, per_direccion);
    }

    public SQLException crearPersona() {
        String sql = "INSERT INTO persona(per_cedula, per_primernom, per_segundonom, per_apellidopater, per_apellidomater, per_fechanac, per_genero, per_telefono, per_email, per_direccion) VALUES ('" + getPer_cedula() + "', '" + getPer_primernom() + "', '" + getPer_segundonom() + "', '" + getPer_apellidopater() + "', '" + getPer_apellidomater() + "', '" + getPer_fechanac() + "','" + getPer_genero() + "', '" + getPer_telefono() + "', '" + getPer_email() + "', '" + getPer_direccion() + "');";

        return conpg.accion(sql);
    }

    public SQLException modificarPersona() {
        String sql = "UPDATE persona SET per_primernom= '"+getPer_primernom()+"', per_segundonom='"+getPer_segundonom()+"', per_apellidopater='"+getPer_apellidopater()+"', per_apellidomater='"+getPer_apellidomater()+"', per_fechanac='"+getPer_fechanac()+"', per_genero='"+getPer_genero()+"', per_telefono='"+getPer_telefono()+"', per_email='"+getPer_email()+"', per_direccion='"+getPer_direccion()+"' WHERE per_cedula = '"+getPer_cedula()+"';";

        return conpg.accion(sql);
    }

    public List<Persona> listaPersonasTabla() {
        try {
            //Me retorna un "List" de "persona"
            List<Persona> lista = new ArrayList<>();

            String sql = "select * from persona";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de las personas
                Persona persona = new Persona();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                persona.setPer_codigo(rs.getInt("per_codigo"));
                persona.setPer_cedula(rs.getString("per_cedula"));
                persona.setPer_primernom(rs.getString("per_primernom"));
                persona.setPer_segundonom(rs.getString("per_segundonom"));
                persona.setPer_apellidopater(rs.getString("per_apellidopater"));
                persona.setPer_apellidomater(rs.getString("per_apellidomater"));
                persona.setPer_fechanac(rs.getDate("per_fechanac"));
                persona.setPer_genero(rs.getString("per_genero"));
                persona.setPer_telefono(rs.getString("per_telefono"));
                persona.setPer_email(rs.getString("per_email"));
                persona.setPer_direccion(rs.getString("per_direccion"));

                lista.add(persona); //Agrego los datos a la lista
            }

            //Cierro la conexion a la BD
            rs.close();
            //Retorno la lista
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Persona> buscarPersona(String cedula) {
        try {
            //Me retorna un "List" de "persona"
            List<Persona> lista = new ArrayList<>();

            String sql = "select * from persona where per_cedula like '" + cedula + "%'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de las personas
                Persona persona = new Persona();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                persona.setPer_codigo(rs.getInt("per_codigo"));
                persona.setPer_cedula(rs.getString("per_cedula"));
                persona.setPer_primernom(rs.getString("per_primernom"));
                persona.setPer_segundonom(rs.getString("per_segundonom"));
                persona.setPer_apellidopater(rs.getString("per_apellidopater"));
                persona.setPer_apellidomater(rs.getString("per_apellidomater"));
                persona.setPer_fechanac(rs.getDate("per_fechanac"));
                persona.setPer_genero(rs.getString("per_genero"));
                persona.setPer_telefono(rs.getString("per_telefono"));
                persona.setPer_email(rs.getString("per_email"));
                persona.setPer_direccion(rs.getString("per_direccion"));

                lista.add(persona); //Agrego los datos a la lista
            }

            //Cierro la conexion a la BD
            rs.close();
            //Retorno la lista
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int traerCodigoDePersonaCrear(String cedula) {
        int codigo = 0;
        try {

            String sql = "select per_codigo from persona where per_cedula = '" + cedula + "'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                codigo = rs.getInt("per_codigo"); //Trae el codigo de la persona recien creada

            }

            //Cierro la conexion a la BD
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);

        }

        return codigo;
    }

    public int validarRepetidos(String cedula) { //Metodo que sirve para validar la cantidad de cedulas existentes en la BD
        int cantidad = 0;
        try {

            String sql = "select COUNT(*) from persona where per_cedula='" + cedula + "'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                cantidad = rs.getInt("COUNT"); //Trae la cantidad de dni repetidos

            }

            //Cierro la conexion a la BD
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cantidad;
    }
}
