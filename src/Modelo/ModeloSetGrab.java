
package Modelo;

import Conexion.ConexionPG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ModeloSetGrab extends SetGrabacion {

    ConexionPG conpg = new ConexionPG();

    public ModeloSetGrab() {
    }

    public ModeloSetGrab(int set_codigo, String set_tamanio, String set_nombre, String set_ubicacion, String set_estado) {
        super(set_codigo, set_tamanio, set_nombre, set_ubicacion, set_estado);
    }

    public SQLException crearSetGrabacion() {
        String sql = "INSERT INTO setgrabacion(set_tamanio, set_nombre, set_ubicacion, set_estado) VALUES ('" + getSet_tamanio() + "', '" + getSet_nombre() + "', '" + getSet_ubicacion() + "', 'A');";

        return conpg.accion(sql);
    }

    public SQLException modificarSetGrabacion() {
        String sql = "UPDATE setgrabacion SET set_nombre = '" + getSet_nombre() + "', set_tamanio = '" + getSet_tamanio() + "', set_ubicacion = '" + getSet_ubicacion() + "' where set_codigo = " + getSet_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException eliminarSetGrabacion(int codigoSetGrab) {
        String sql = "UPDATE setgrabacion SET set_estado= 'I' where set_codigo = " + codigoSetGrab + ";";

        return conpg.accion(sql);
    }

    public List<SetGrabacion> listaSetGrabTabla() {
        try {
            List<SetGrabacion> lista = new ArrayList<>();

            String sql = "select * from setgrabacion where set_estado = 'A'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {

                SetGrabacion set = new SetGrabacion();

                set.setSet_codigo(rs.getInt("set_codigo"));
                set.setSet_nombre(rs.getString("set_nombre"));
                set.setSet_tamanio(rs.getString("set_tamanio"));
                set.setSet_ubicacion(rs.getString("set_ubicacion"));

                lista.add(set); //Agrego los datos a la lista
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

    public List<SetGrabacion> buscarSetGrabacion(String nombre) {
        try {
            List<SetGrabacion> lista = new ArrayList<>();

            String sql = "select * from setgrabacion where set_estado = 'A' and Lower(set_nombre) like '" + nombre.toLowerCase() + "%'"; //Paso a minuscula el nombre del curso que esta 
            //guardado en la BD y tambien paso a minuscula el nombre recuperado del txr para poder comparar los nombres. Sin importar si esta en mayuscula o en minuscula

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                SetGrabacion set = new SetGrabacion();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                set.setSet_codigo(rs.getInt("set_codigo"));
                set.setSet_nombre(rs.getString("set_nombre"));
                set.setSet_tamanio(rs.getString("set_tamanio"));
                set.setSet_ubicacion(rs.getString("set_ubicacion"));

                lista.add(set); //Agrego los datos a la lista
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
