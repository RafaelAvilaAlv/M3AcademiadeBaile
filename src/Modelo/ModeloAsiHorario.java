package Modelo;

import Conexion.ConexionPG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloAsiHorario extends AsiHorario {

    ConexionPG conpg = new ConexionPG();

    public ModeloAsiHorario() {
    }

    public ModeloAsiHorario(int asih_codigo, int asih_codcurso, int asih_codhorario, Date asih_fecha, String asih_estado) {
        super(asih_codigo, asih_codcurso, asih_codhorario, asih_fecha, asih_estado);
    }

    public SQLException asignarHorario() {
        String sql = "INSERT INTO asihorario(asih_codcurso, asih_codhorario, asih_fecha, asih_estado) VALUES (" + getAsih_codcurso() + ", " + getAsih_codhorario() + ", '" + getAsih_fecha() + "', 'A');";

        return conpg.accion(sql);
    }

    public SQLException modificarAsignacionFecha() {
        String sql = "UPDATE asihorario SET asih_fecha = '" + getAsih_fecha() + "' where asih_codigo = " + getAsih_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException modificarAsignacionCompleto() {
        String sql = "UPDATE asihorario SET asih_codcurso = " + getAsih_codcurso() + ", asih_codhorario = " + getAsih_codhorario() + ", asih_fecha = '" + getAsih_fecha() + "' where asih_codigo = " + getAsih_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException eliminarAsignacion(int codigoAsignacion) {
        String sql = "UPDATE asihorario SET asih_estado = 'I' where asih_codigo = " + codigoAsignacion + ";";

        return conpg.accion(sql);
    }

    public List<AsiHorario> listaAsignacionesTabla() {
        try {
            List<AsiHorario> lista = new ArrayList<>();

            String sql = "select * from asihorario where asih_estado = 'A'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                AsiHorario asignacion = new AsiHorario();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                asignacion.setAsih_codigo(rs.getInt("asih_codigo"));
                asignacion.setAsih_codcurso(rs.getInt("asih_codcurso"));
                asignacion.setAsih_codhorario(rs.getInt("asih_codhorario"));
                asignacion.setAsih_fecha(rs.getDate("asih_fecha"));
                asignacion.setAsih_estado(rs.getString("asih_estado"));

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
