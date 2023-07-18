package Modelo;

import Conexion.ConexionPG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloCurso extends Curso {

    ConexionPG conpg = new ConexionPG();

    public ModeloCurso() {
    }

    public ModeloCurso(int cur_codigo, String cur_nombre, String cur_periodo, Date cur_fechaInicio, Date cur_fechaFin, String cur_descripcion, double cur_precio, String cur_estado) {
        super(cur_codigo, cur_nombre, cur_periodo, cur_fechaInicio, cur_fechaFin, cur_descripcion, cur_precio, cur_estado);
    }

    public SQLException crearCurso() {
        String sql = "INSERT INTO curso(cur_nombre, cur_periodo, cur_fechaini, cur_fechafin, cur_descripcion, cur_precio, cur_estado) VALUES ('" + getCur_nombre() + "', '" + getCur_periodo() + "', '" + getCur_fechaInicio() + "', '" + getCur_fechaFin() + "','" + getCur_descripcion() + "', " + getCur_precio() + ", 'A');";

        return conpg.accion(sql);
    }

    public SQLException modificarCurso() {
        String sql = "UPDATE curso SET cur_nombre = '" + getCur_nombre() + "', cur_periodo = '" + getCur_periodo() + "', cur_fechaini = '" + getCur_fechaInicio() + "', cur_fechafin = '" + getCur_fechaFin() + "', cur_descripcion = '" + getCur_descripcion() + "', cur_precio = " + getCur_precio() + " where cur_codigo = " + getCur_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException eliminarCurso(int codigoCurso) {
        String sql = "UPDATE curso SET cur_estado= 'I' where cur_codigo = " + codigoCurso + ";";

        return conpg.accion(sql);
    }

    public List<Curso> listaCursoTabla() {
        try {
            List<Curso> lista = new ArrayList<>();

            String sql = "select * from curso where cur_estado = 'A'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {

                Curso curso = new Curso();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                curso.setCur_codigo(rs.getInt("cur_codigo"));
                curso.setCur_nombre(rs.getString("cur_nombre"));
                curso.setCur_periodo(rs.getString("cur_periodo"));
                curso.setCur_fechaInicio(rs.getDate("cur_fechaini"));
                curso.setCur_fechaFin(rs.getDate("cur_fechafin"));
                curso.setCur_descripcion(rs.getString("cur_descripcion"));
                curso.setCur_precio(rs.getDouble("cur_precio"));

                lista.add(curso); //Agrego los datos a la lista
            }

            //Cierro la conexion a la BD
            rs.close();
            //Retorno la lista
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloCurso.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Curso> buscarCurso(String nombre) {
        try {
            //Me retorna un "List" de "persona"
            List<Curso> lista = new ArrayList<>();

            String sql = "select * from curso where cur_estado = 'A' and Lower(cur_nombre) like '" + nombre.toLowerCase() + "%'"; //Paso a minuscula el nombre del curso que esta 
            //guardado en la BD y tambien paso a minuscula el nombre recuperado del txr para poder comparar los nombres. Sin importar si esta en mayuscula o en minuscula

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                Curso curso = new Curso();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                curso.setCur_codigo(rs.getInt("cur_codigo"));
                curso.setCur_nombre(rs.getString("cur_nombre"));
                curso.setCur_periodo(rs.getString("cur_periodo"));
                curso.setCur_fechaInicio(rs.getDate("cur_fechaini"));
                curso.setCur_fechaFin(rs.getDate("cur_fechafin"));
                curso.setCur_descripcion(rs.getString("cur_descripcion"));
                curso.setCur_precio(rs.getDouble("cur_precio"));

                lista.add(curso); //Agrego los datos a la lista
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
