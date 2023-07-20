package Modelo;

import Conexion.ConexionPG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloIndumentaria extends Indumentaria {

    ConexionPG conpg = new ConexionPG();

    public ModeloIndumentaria() {
    }

    public ModeloIndumentaria(String ins_nombre, String ins_marca, String ins_tipo, String ins_estado, int ins_codigo, int ins_setcodigo, double ins_valor) {
        super(ins_nombre, ins_marca, ins_tipo, ins_estado, ins_codigo, ins_setcodigo, ins_valor);
    }

    public SQLException crearInstrumento() {
        String sql = "INSERT INTO indumentaria(ins_setcodigo, ins_nombre, ins_marca, ins_tipo, ins_valor, ins_estado) VALUES (" + getIns_setcodigo() + ",'" + getIns_nombre() + "', '" + getIns_marca() + "', '" + getIns_tipo() + "', " + getIns_valor() + ", 'A');";

        return conpg.accion(sql);
    }

    public SQLException modificarInstrumento() {
        String sql = "UPDATE indumentaria SET ins_nombre = '" + getIns_nombre() + "', ins_marca = '" + getIns_marca() + "', ins_tipo = '" + getIns_tipo() + "', ins_valor = "+ getIns_valor() + ", ins_setcodigo = " + getIns_setcodigo() + " where ins_codigo = " + getIns_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException eliminarInstrumento(int codigoInstrumento) {
        String sql = "UPDATE instrumento SET ins_estado= 'I' where ins_codigo = " + codigoInstrumento + ";";

        return conpg.accion(sql);
    }

    public List<Indumentaria> listaInstumentoTabla() {
        try {
            List<Indumentaria> lista = new ArrayList<>();

            String sql = "select * from indumentaria where ins_estado = 'A'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {

                Indumentaria instrumento = new Indumentaria();

                //Todo lo que haga en la sentencia define como voy a extraer los datos4
                instrumento.setIns_codigo(rs.getInt("ins_codigo"));
                instrumento.setIns_marca(rs.getString("ins_marca"));
                instrumento.setIns_nombre(rs.getString("ins_nombre"));
                instrumento.setIns_setcodigo(rs.getInt("ins_setcodigo"));
                instrumento.setIns_tipo(rs.getString("ins_tipo"));
                instrumento.setIns_valor(rs.getDouble("ins_valor"));

                lista.add(instrumento); //Agrego los datos a la lista
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

    public List<Indumentaria> buscarInstrumento(String nombre) {
        try {
            //Me retorna un "List" de "persona"
            List<Indumentaria> lista = new ArrayList<>();

            String sql = "select * from indumentaria where ins_estado = 'A' and Lower(ins_nombre) like '" + nombre.toLowerCase() + "%'"; //Paso a minuscula el nombre del curso que esta 
            //guardado en la BD y tambien paso a minuscula el nombre recuperado del txr para poder comparar los nombres. Sin importar si esta en mayuscula o en minuscula

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                Indumentaria instrumento = new Indumentaria();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                instrumento.setIns_codigo(rs.getInt("ins_codigo"));
                instrumento.setIns_marca(rs.getString("ins_marca"));
                instrumento.setIns_nombre(rs.getString("ins_nombre"));
                instrumento.setIns_setcodigo(rs.getInt("ins_setcodigo"));
                instrumento.setIns_tipo(rs.getString("ins_tipo"));
                instrumento.setIns_valor(rs.getDouble("ins_valor"));

                lista.add(instrumento); //Agrego los datos a la lista
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
