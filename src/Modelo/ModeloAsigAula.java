package Modelo;

import Conexion.ConexionPG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ModeloAsigAula extends AsigAula {

    ConexionPG conpg = new ConexionPG();

    public ModeloAsigAula() {
    }

    public ModeloAsigAula(int asia_codigo, int asia_codcurso, int asia_codaula, java.sql.Date asia_fecha, String asi_estado) {
        super(asia_codigo, asia_codcurso, asia_codaula, asia_fecha, asi_estado);
    }

    public SQLException asignarAula() {
        String sql = "INSERT INTO asigaula( asia_codcurso, asia_codaula, asia_fecha ,asia_estado) VALUES (" + getAsia_codcurso() + ", " + getAsia_codaula() + ",'" + getAsia_fecha() + "', 'A');";

        return conpg.accion(sql);
    }

    public SQLException modificarAsignacionFecha() {
        String sql = "UPDATE asigaula SET asia_fecha = '" + getAsia_fecha() + "' where asia_codigo = " + getAsia_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException modificarAsignacionCompleto() {
        String sql = "UPDATE asigaula SET asia_codcurso = " + getAsia_codcurso() + ", asia_codaula = " + getAsia_codaula() + ", asia_fecha = '" + getAsia_fecha() + "' where asia_codigo = " + getAsia_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException eliminarAsignacion(int codigoAsignacion) {
        String sql = "UPDATE asigaula SET asia_estado = 'I' where asia_codigo = " + codigoAsignacion + ";";

        return conpg.accion(sql);
    }

    public List<AsigAula> listaAsignacionesTabla() {
        try {
            List<AsigAula> lista = new ArrayList<>();

            String sql = "select * from asigaula where asia_estado = 'A'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                AsigAula asignacion = new AsigAula();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                asignacion.setAsia_codigo(rs.getInt("asia_codigo"));
                asignacion.setAsia_codaula(rs.getInt("asia_codaula"));
                asignacion.setAsia_codcurso(rs.getInt("asia_codcurso"));
                asignacion.setAsia_fecha(rs.getDate("asia_fecha"));

                lista.add(asignacion); //Agrego los datos a la lista
            }

            //Cierro la conexion a la BD
            rs.close();
            //Retorno la lista
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloAsiAsignatura.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
