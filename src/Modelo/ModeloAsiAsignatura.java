package Modelo;

import Conexion.ConexionPG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloAsiAsignatura extends AsiAsignatura {

    ConexionPG conpg = new ConexionPG();

    public ModeloAsiAsignatura() {
    }

    public ModeloAsiAsignatura(int asig_codigo, int asig_coddoc, int asig_codasi, Date asig_fecha) {
        super(asig_codigo, asig_coddoc, asig_codasi, asig_fecha);
    }

    public SQLException asignarAsignatura() {
        String sql = "INSERT INTO asiasignatura(asig_coddoc, asig_codasi, asig_fecha, asig_estado) VALUES (" + getAsig_coddoc() + ", " + getAsig_codasi() + ", '" + getAsig_fecha() + "', 'A');";

        return conpg.accion(sql);
    }

    public SQLException modificarAsignacionFecha() {
        String sql = "UPDATE asiasignatura SET asig_fecha = '" + getAsig_fecha() + "' where asig_codigo = " + getAsig_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException modificarAsignacionCompleto() {
        String sql = "UPDATE asiasignatura SET asig_coddoc = " + getAsig_coddoc() + ", asig_codasi = " + getAsig_codasi() + ", asig_fecha = '" + getAsig_fecha() + "' where asig_codigo = " + getAsig_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException eliminarAsignacion(int codigoAsignacion) {
        String sql = "UPDATE asiasignatura SET asig_estado = 'I' where asig_codigo = " + codigoAsignacion + ";";

        return conpg.accion(sql);
    }

    public List<AsiAsignatura> listaAsignacionesTabla() {
        try {
            List<AsiAsignatura> lista = new ArrayList<>();

            String sql = "select * from asiasignatura where asig_estado = 'A'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                AsiAsignatura asignacion = new AsiAsignatura();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                asignacion.setAsig_codigo(rs.getInt("asig_codigo"));
                asignacion.setAsig_coddoc(rs.getInt("asig_coddoc"));
                asignacion.setAsig_codasi(rs.getInt("asig_codasi"));
                asignacion.setAsig_fecha(rs.getDate("asig_fecha"));

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
