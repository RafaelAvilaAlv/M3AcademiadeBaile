/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Conexion.ConexionPG;
import java.sql.Date;
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
public class ModeloDirector_artistico extends Director_artistico {

    ConexionPG conpg = new ConexionPG();

    public ModeloDirector_artistico() {
    }

    public ModeloDirector_artistico(int pro_codemp, int pro_codigo, int pro_expe, String pro_estado) {
        super(pro_codemp, pro_codigo, pro_expe, pro_estado);
    }

    public ModeloDirector_artistico(int pro_codemp, int pro_codigo, int pro_expe, String pro_estado, int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario) {
        super(pro_codemp, pro_codigo, pro_expe, pro_estado, emp_codigo, emp_codper, emp_fechacontratacion, emp_salario);
    }

    public ModeloDirector_artistico(int pro_codemp, int pro_codigo, int pro_expe, String pro_estado, int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario, int per_codigo, String per_cedula, String per_primernom, String per_segundonom, String per_apellidopater, String per_apellidomater, java.util.Date per_fechanac, String per_genero, String per_telefono, String per_email, String per_direccion) {
        super(pro_codemp, pro_codigo, pro_expe, pro_estado, emp_codigo, emp_codper, emp_fechacontratacion, emp_salario, per_codigo, per_cedula, per_primernom, per_segundonom, per_apellidopater, per_apellidomater, per_fechanac, per_genero, per_telefono, per_email, per_direccion);
    }

    public SQLException crearProductor() {

        String sql = "INSERT INTO director_artistico(pro_codemp, pro_experiencia, pro_estado) VALUES (" + getPro_codemp() + ", " + getPro_expe() + ", 'A');";

        return conpg.accion(sql);
    }

    public SQLException modificarProductor() {
        String sql = "UPDATE director_artistico SET pro_experiencia= " + getPro_expe() + " where pro_codigo = " + getPro_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException eliminarProductor(int codigoProductor) {
        String sql = "UPDATE director_artistico SET pro_estado= 'I' where pro_codigo = " + codigoProductor + ";";

        return conpg.accion(sql);
    }

    public List<Director_artistico> listaProductorTabla() {
        try {
            //Me retorna un "List" de "persona"
            List<Director_artistico> lista = new ArrayList<>();

            String sql = "select * from persona, empleado, director_artistico where per_codigo = emp_codper and emp_codigo = pro_codemp and pro_estado = 'A'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                Director_artistico productor = new Director_artistico();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                productor.setPer_codigo(rs.getInt("per_codigo"));
                productor.setPer_cedula(rs.getString("per_cedula"));
                productor.setPer_primernom(rs.getString("per_primernom"));
                productor.setPer_segundonom(rs.getString("per_segundonom"));
                productor.setPer_apellidopater(rs.getString("per_apellidopater"));
                productor.setPer_apellidomater(rs.getString("per_apellidomater"));
                productor.setPer_fechanac(rs.getDate("per_fechanac"));
                productor.setPer_genero(rs.getString("per_genero"));
                productor.setPer_telefono(rs.getString("per_telefono"));
                productor.setPer_email(rs.getString("per_email"));
                productor.setPer_direccion(rs.getString("per_direccion"));
                productor.setEmp_codigo(rs.getInt("emp_codigo"));
                productor.setEmp_codper(rs.getInt("emp_codper"));
                productor.setEmp_fechacontratacion(rs.getDate("emp_fechacontratacion"));
                productor.setEmp_salario(rs.getDouble("emp_salario"));
                productor.setPro_codigo(rs.getInt("pro_codigo"));
                productor.setPro_codemp(rs.getInt("pro_codemp"));
                productor.setPro_expe(rs.getInt("pro_experiencia"));
                productor.setPro_estado(rs.getString("pro_estado"));

                lista.add(productor); //Agrego los datos a la lista
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

    public List<Director_artistico> buscarProductor(String cedula) {
        try {
            //Me retorna un "List" de "persona"
            List<Director_artistico> lista = new ArrayList<>();

            String sql = "select * from persona, empleado, director_artistico where per_codigo = emp_codper and emp_codigo = pro_codemp and pro_estado = 'A' and per_cedula like '" + cedula + "%'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                Director_artistico productor = new Director_artistico();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                productor.setPer_codigo(rs.getInt("per_codigo"));
                productor.setPer_cedula(rs.getString("per_cedula"));
                productor.setPer_primernom(rs.getString("per_primernom"));
                productor.setPer_segundonom(rs.getString("per_segundonom"));
                productor.setPer_apellidopater(rs.getString("per_apellidopater"));
                productor.setPer_apellidomater(rs.getString("per_apellidomater"));
                productor.setPer_fechanac(rs.getDate("per_fechanac"));
                productor.setPer_genero(rs.getString("per_genero"));
                productor.setPer_telefono(rs.getString("per_telefono"));
                productor.setPer_email(rs.getString("per_email"));
                productor.setPer_direccion(rs.getString("per_direccion"));
                productor.setEmp_codigo(rs.getInt("emp_codigo"));
                productor.setEmp_codper(rs.getInt("emp_codper"));
                productor.setEmp_fechacontratacion(rs.getDate("emp_fechacontratacion"));
                productor.setEmp_salario(rs.getDouble("emp_salario"));
                productor.setPro_codigo(rs.getInt("pro_codigo"));
                productor.setPro_codemp(rs.getInt("pro_codemp"));
                productor.setPro_expe(rs.getInt("pro_experiencia"));
                productor.setPro_estado(rs.getString("pro_estado"));

                lista.add(productor); //Agrego los datos a la lista
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

    //Dependiendo del numero de cedula trae el codigo del docente que se va a eliminar
    public int traerCodigoDeProductorEliminar(String cedula) { //Retorna el codigo maximo. Este codigo sirve para crear un docente en la BD
        int codigo = 0;
        try {

            String sql = "select pro_codigo from persona,empleado,director_artistico where per_cedula = '" + cedula + "' and per_codigo = emp_codper and emp_codigo = pro_codemp;";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                codigo = rs.getInt("pro_codigo"); //Trae el codigo de la persona recien creada

            }

            //Cierro la conexion a la BD
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ModeloEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return codigo;
    }
}
