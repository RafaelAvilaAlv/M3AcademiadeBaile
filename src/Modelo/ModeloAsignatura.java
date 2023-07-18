package Modelo;

import Conexion.ConexionPG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloAsignatura extends Asignatura {

    ConexionPG conpg = new ConexionPG();

    public ModeloAsignatura() {
    }

    public ModeloAsignatura(int asi_codigo, int asi_codCurso, String asi_nombre, String asi_descripcion, String asi_estado) {
        super(asi_codigo, asi_codCurso, asi_nombre, asi_descripcion, asi_estado);
    }

    public SQLException crearAsignatura() {
        String sql = "INSERT INTO asignatura(asi_codcurso, asi_nombre, asi_descripcion, asi_estado) VALUES (" + getAsi_codCurso() + ", '" + getAsi_nombre() + "', '" + getAsi_descripcion() + "', 'A');";

        return conpg.accion(sql);
    }

    public SQLException modificarAsignatura() {
        String sql = "UPDATE asignatura SET asi_codcurso = " + getAsi_codCurso() + ",asi_nombre = '" + getAsi_nombre() + "', asi_descripcion = '" + getAsi_descripcion() + "' where asi_codigo = " + getAsi_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException eliminarAsignatura(int codigoAsignatura) {
        String sql = "UPDATE asignatura SET asi_estado = 'I' where asi_codigo = " + codigoAsignatura + "";

        return conpg.accion(sql);
    }

    public List<Asignatura> listaAsignaturaTabla() {
        try {
            //Me retorna un "List" de "persona"
            List<Asignatura> lista = new ArrayList<>();

            String sql = "select * from asignatura where asi_estado = 'A'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                Asignatura asignatura = new Asignatura();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                asignatura.setAsi_codigo(rs.getInt("asi_codigo"));
                asignatura.setAsi_codCurso(rs.getInt("asi_codcurso"));
                asignatura.setAsi_nombre(rs.getString("asi_nombre"));
                asignatura.setAsi_descripcion(rs.getString("asi_descripcion"));

                lista.add(asignatura); //Agrego los datos a la lista
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

    public List<Asignatura> buscarAsignatura(String nombre) {
        try {
            //Me retorna un "List" de "persona"
            List<Asignatura> lista = new ArrayList<>();

            String sql = "select * from asignatura where Lower(asi_nombre) like '"+nombre.toLowerCase()+"%' and asi_estado = 'A' ";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                Asignatura asignatura = new Asignatura();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                asignatura.setAsi_codigo(rs.getInt("asi_codigo"));
                asignatura.setAsi_codCurso(rs.getInt("asi_codcurso"));
                asignatura.setAsi_nombre(rs.getString("asi_nombre"));
                asignatura.setAsi_descripcion(rs.getString("asi_descripcion"));

                lista.add(asignatura); //Agrego los datos a la lista
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
}
