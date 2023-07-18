package Modelo;

import Conexion.ConexionPG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloAula extends Aula {

    ConexionPG conpg = new ConexionPG();

    public ModeloAula() {
    }

    public ModeloAula(int aul_codigo, int aul_capacidad, String aul_nombre, String aul_ubicacion, String aul_descripcion, String aul_estado) {
        super(aul_codigo, aul_capacidad, aul_nombre, aul_ubicacion, aul_descripcion, aul_estado);
    }

    public SQLException crearAula() {
        String sql = "INSERT INTO aula(aul_nombre, aul_ubicacion, aul_capacidad, aul_descripcion, aul_estado) VALUES ('" + getAul_nombre() + "', '" + getAul_ubicacion() + "', " + getAul_capacidad() + ",'" + getAul_descripcion() + "','A');";

        return conpg.accion(sql);
    }

    public SQLException modificarAula() {
        String sql = "UPDATE aula SET aul_nombre = '" + getAul_nombre() + "', aul_ubicacion = '" + getAul_ubicacion() + "', aul_capacidad = " + getAul_capacidad() + ", aul_descripcion = '" + getAul_descripcion() + "' where aul_codigo = " + getAul_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException eliminarAula(int codigoAula) {
        String sql = "UPDATE aula SET aul_estado= 'I' where aul_codigo = " + codigoAula + ";";

        return conpg.accion(sql);
    }

    public List<Aula> listaAulaTabla() {
        try {
            //Me retorna un "List" de "persona"
            List<Aula> lista = new ArrayList<>();

            String sql = "select * from aula where aul_estado = 'A'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                Aula aula = new Aula();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                aula.setAul_codigo(rs.getInt("aul_codigo"));
                aula.setAul_nombre(rs.getString("aul_nombre"));
                aula.setAul_capacidad(rs.getInt("aul_capacidad"));
                aula.setAul_ubicacion(rs.getString("aul_ubicacion"));
                aula.setAul_descripcion(rs.getString("aul_descripcion"));

                lista.add(aula); //Agrego los datos a la lista
            }

            //Cierro la conexion a la BD
            rs.close();
            //Retorno la lista
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloAula.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Aula> buscarAula(String nombre) {
        try {
            //Me retorna un "List" de "aulas"
            List<Aula> lista = new ArrayList<>();

            String sql = "select * from aula where aul_estado = 'A' and Lower(aul_nombre) like '" + nombre.toLowerCase() + "%'"; //Paso a minuscula el nombre del curso que esta 
            //guardado en la BD y tambien paso a minuscula el nombre recuperado del txr para poder comparar los nombres. Sin importar si esta en mayuscula o en minuscula

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                Aula aula = new Aula();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                aula.setAul_codigo(rs.getInt("aul_codigo"));
                aula.setAul_nombre(rs.getString("aul_nombre"));
                aula.setAul_capacidad(rs.getInt("aul_capacidad"));
                aula.setAul_ubicacion(rs.getString("aul_ubicacion"));
                aula.setAul_descripcion(rs.getString("aul_descripcion"));

                lista.add(aula); //Agrego los datos a la lista
            }

            //Cierro la conexion a la BD
            rs.close();
            //Retorno la lista
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloAula.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
