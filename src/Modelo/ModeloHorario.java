package Modelo;

import Conexion.ConexionPG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloHorario extends Horario {

    ConexionPG conpg = new ConexionPG();

    public ModeloHorario() {
    }

    public ModeloHorario(int codigo, String dia, String horaInicio, String horaFin, String estado) {
        super(codigo, dia, horaInicio, horaFin, estado);
    }

    public SQLException crearHorario() {

        String sql = "Insert into horario (hor_horaini, hor_horafin, hor_dia, hor_estado) values ('" + getHor_horaIni() + "', '" + getHor_horaFin() + "', '" + getHor_dia() + "', 'A')";

        return conpg.accion(sql);
    }

    public SQLException modificarHorario() {
        String sql = "UPDATE horario SET hor_dia = '" + getHor_dia() + "', hor_horaini = '" + getHor_horaIni() + "', hor_horafin = '" + getHor_horaFin() + "' where hor_codigo = " + getHor_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException eliminarHorario(int codigoHorario) {
        String sql = "UPDATE horario SET hor_estado= 'I' where hor_codigo = " + codigoHorario + ";";

        return conpg.accion(sql);
    }

    public List<Horario> listaHorarioTabla() {
        try {
            List<Horario> lista = new ArrayList<>();

            String sql = "select * from horario where hor_estado = 'A'";

            ResultSet rs = conpg.consulta(sql);

            while (rs.next()) {

                Horario horario = new Horario();

                horario.setHor_codigo(rs.getInt("hor_codigo"));
                horario.setHor_horaIni(rs.getString("hor_horaini"));
                horario.setHor_horaFin(rs.getString("hor_horafin"));
                horario.setHor_dia(rs.getString("hor_dia"));
                horario.setHor_estado(rs.getString("hor_estado"));

                lista.add(horario);
            }

            rs.close();
            //Retorno la lista            
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloHorario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
