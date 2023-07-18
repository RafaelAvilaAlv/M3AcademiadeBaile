/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.ConexionPG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ModeloDirigir extends Dirigir {
    ConexionPG conpg = new ConexionPG();

    public ModeloDirigir() {
    }

    public ModeloDirigir(int dir_codigo, int dir_codset, int dir_codpro, Date dir_fecharegistro, String dir_estado) {
        super(dir_codigo, dir_codset, dir_codpro, dir_fecharegistro, dir_estado);
    }
    
    public SQLException asignarDirigir() {
        String sql = "INSERT INTO dirigir(dir_codset, dir_codpro, dir_fecharegistro, dir_estado) VALUES (" + getDir_codset() + ", " + getDir_codpro() + ", '" + getDir_fecharegistro() + "', 'A');";

        return conpg.accion(sql);
    }

    public SQLException modificarDirigirFecha() {
        String sql = "UPDATE dirigir SET dir_fecharegistro = '" + getDir_fecharegistro() + "' where dir_codigo = " + getDir_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException modificarDirigirCompleto() {
        String sql = "UPDATE dirigir SET dir_codset = " + getDir_codset() + ", dir_codpro = " + getDir_codpro() + ", dir_fecharegistro = '" + getDir_fecharegistro() + "' where dir_codigo = " + getDir_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException eliminarDirigir(int codigoDirigir) {
        String sql = "UPDATE dirigir SET dir_estado = 'I' where dir_codigo = " + codigoDirigir + ";";

        return conpg.accion(sql);
    }

    public List<Dirigir> listaDirigirTabla() {
        try {
            List<Dirigir> lista = new ArrayList<>();

            String sql = "select * from dirigir where dir_estado = 'A'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                Dirigir diri = new Dirigir();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                diri.setDir_codigo(rs.getInt("dir_codigo"));
                diri.setDir_codpro(rs.getInt("dir_codpro"));
                diri.setDir_codset(rs.getInt("dir_codset"));
                diri.setDir_fecharegistro(rs.getDate("dir_fecharegistro"));

                lista.add(diri); //Agrego los datos a la lista
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

    

