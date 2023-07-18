
package Modelo;

import Conexion.ConexionPG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloEstudiante extends Estudiante {

    ConexionPG conpg = new ConexionPG();

    public ModeloEstudiante() {
    }

    public ModeloEstudiante(int est_codigo, int est_codper, String est_estado) {
        super(est_codigo, est_codper, est_estado);
    }

    public ModeloEstudiante(int est_codigo, int est_codper, String est_estado, int per_codigo, String per_cedula, String per_primernom, String per_segundonom, String per_apellidopater, String per_apellidomater, Date per_fechanac, String per_genero, String per_telefono, String per_email, String per_direccion) {
        super(est_codigo, est_codper, est_estado, per_codigo, per_cedula, per_primernom, per_segundonom, per_apellidopater, per_apellidomater, per_fechanac, per_genero, per_telefono, per_email, per_direccion);
    }

    public SQLException crearEstudiante() {
        String sql = "INSERT INTO estudiante(est_codper, est_estado) VALUES (" + getEst_codper() + ", 'A');";

        return conpg.accion(sql);
    }

    public SQLException crearEstudiante2(int codigoPersona) {
        String sql = "UPDATE estudiante SET est_estado= 'A' where est_codper = " + codigoPersona + ";";

        return conpg.accion(sql);
    }

    public SQLException modificarEstudiante() {
        String sql = "UPDATE estudiante SET est_codper = " + getEst_codper() + " where est_codigo = " + getEst_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException modificarEstudiante2(int codigoPersona) {
        String sql = "UPDATE estudiante SET est_estado= 'A' where est_codper = " + codigoPersona + ";";

        return conpg.accion(sql);
    }

    public SQLException eliminarEstudiante(int codigoEstudiante) {
        String sql = "UPDATE estudiante SET est_estado= 'I' where est_codigo = " + codigoEstudiante + ";";

        return conpg.accion(sql);
    }

    public List<Estudiante> listaEstudiantesTabla() {
        try {
            //Me retorna un "List" de "persona"
            List<Estudiante> lista = new ArrayList<>();

            String sql = "select * from persona, estudiante where per_codigo = est_codper and est_estado = 'A'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                Estudiante estudiante = new Estudiante();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                estudiante.setPer_codigo(rs.getInt("per_codigo"));
                estudiante.setPer_cedula(rs.getString("per_cedula"));
                estudiante.setPer_primernom(rs.getString("per_primernom"));
                estudiante.setPer_segundonom(rs.getString("per_segundonom"));
                estudiante.setPer_apellidopater(rs.getString("per_apellidopater"));
                estudiante.setPer_apellidomater(rs.getString("per_apellidomater"));
                estudiante.setPer_fechanac(rs.getDate("per_fechanac"));
                estudiante.setPer_genero(rs.getString("per_genero"));
                estudiante.setPer_telefono(rs.getString("per_telefono"));
                estudiante.setPer_email(rs.getString("per_email"));
                estudiante.setPer_direccion(rs.getString("per_direccion"));
                estudiante.setEst_codigo(rs.getInt("est_codigo"));
                estudiante.setEst_codper(rs.getInt("est_codper"));
                estudiante.setEst_estado(rs.getString("est_estado"));

                lista.add(estudiante); //Agrego los datos a la lista
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

    public List<Estudiante> buscarEstudiante(String cedula) {
        try {
            //Me retorna un "List" de "persona"
            List<Estudiante> lista = new ArrayList<>();

            String sql = "select * from persona, estudiante where per_codigo = est_codper  and est_estado = 'A' and per_cedula like '" + cedula + "%'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                Estudiante estudiante = new Estudiante();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                estudiante.setPer_codigo(rs.getInt("per_codigo"));
                estudiante.setPer_cedula(rs.getString("per_cedula"));
                estudiante.setPer_primernom(rs.getString("per_primernom"));
                estudiante.setPer_segundonom(rs.getString("per_segundonom"));
                estudiante.setPer_apellidopater(rs.getString("per_apellidopater"));
                estudiante.setPer_apellidomater(rs.getString("per_apellidomater"));
                estudiante.setPer_fechanac(rs.getDate("per_fechanac"));
                estudiante.setPer_genero(rs.getString("per_genero"));
                estudiante.setPer_telefono(rs.getString("per_telefono"));
                estudiante.setPer_email(rs.getString("per_email"));
                estudiante.setPer_direccion(rs.getString("per_direccion"));
                estudiante.setEst_codigo(rs.getInt("est_codigo"));
                estudiante.setEst_codper(rs.getInt("est_codper"));
                estudiante.setEst_estado(rs.getString("est_estado"));

                lista.add(estudiante); //Agrego los datos a la lista
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

    public String traerEstadoDelEstudiante(int codigoPersona) { //Trae el codigo de la persona que esta en la tabla estudiante
        String estado = "";
        try {

            String sql = "select est_estado from estudiante where est_codper = " + codigoPersona + ";";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                estado = rs.getString("est_estado"); //Trae el estado del estudiante

            }

            //Cierro la conexion a la BD
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ModeloEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return estado;
    }

    public int traerCodigoDeEstudianteEliminar(String cedula) {
        int codigo = 0;
        try {

            String sql = "select est_codigo from persona,estudiante where per_cedula = '" + cedula + "' and per_codigo = est_codper;";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                codigo = rs.getInt("est_codigo"); //Trae el codigo de la persona recien creada

            }

            //Cierro la conexion a la BD
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ModeloEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return codigo;
    }
}
