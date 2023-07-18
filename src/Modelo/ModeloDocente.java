package Modelo;

import Conexion.ConexionPG;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloDocente extends Docente {

    ConexionPG conpg = new ConexionPG();
//
//    public ModeloDocente() {
//    }

    public ModeloDocente() {
    }

    public ModeloDocente(int doc_codigo, int doc_codemp, String doc_especialidad, String doc_estado) {
        super(doc_codigo, doc_codemp, doc_especialidad, doc_estado);
    }

    public ModeloDocente(int doc_codigo, int doc_codemp, String doc_especialidad, String doc_estado, int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario) {
        super(doc_codigo, doc_codemp, doc_especialidad, doc_estado, emp_codigo, emp_codper, emp_fechacontratacion, emp_salario);
    }

//    public ModeloDocente(int doc_codigo, int doc_codemp, String doc_especialidad, String doc_estado) {
//        super(doc_codigo, doc_codemp, doc_especialidad, doc_estado);
//    }
//
//    public ModeloDocente(int doc_codigo, int doc_codemp, String doc_especialidad, String doc_estado, int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario) {
//        super(doc_codigo, doc_codemp, doc_especialidad, doc_estado, emp_codigo, emp_codper, emp_fechacontratacion, emp_salario);
//    }
//    public ModeloDocente(int doc_codigo, int doc_codemp, String doc_especialidad, String doc_estado, int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario, int per_codigo, String per_cedula, String per_primernom, String per_segundonom, String per_apellidopater, String per_apellidomater, java.util.Date per_fechanac, String per_genero, String per_telefono, String per_email, String per_direccion) {
//        super(doc_codigo, doc_codemp, doc_especialidad, doc_estado, emp_codigo, emp_codper, emp_fechacontratacion, emp_salario, per_codigo, per_cedula, per_primernom, per_segundonom, per_apellidopater, per_apellidomater, per_fechanac, per_genero, per_telefono, per_email, per_direccion);
//    }
    public ModeloDocente(int doc_codigo, int doc_codemp, String doc_especialidad, String doc_estado, int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario, int per_codigo, String per_cedula, String per_primernom, String per_segundonom, String per_apellidopater, String per_apellidomater, java.util.Date per_fechanac, String per_genero, String per_telefono, String per_email, String per_direccion) {
        super(doc_codigo, doc_codemp, doc_especialidad, doc_estado, emp_codigo, emp_codper, emp_fechacontratacion, emp_salario, per_codigo, per_cedula, per_primernom, per_segundonom, per_apellidopater, per_apellidomater, per_fechanac, per_genero, per_telefono, per_email, per_direccion);
    }

    public SQLException crearDocente() {
        String sql = "INSERT INTO docente(doc_codemp, doc_especialidad, doc_estado) VALUES (" + getDoc_codemp() + ", '" + getDoc_especialidad() + "', 'A');";

        return conpg.accion(sql);
    }

    public SQLException modificarDocente() {
        String sql = "UPDATE docente SET doc_especialidad= '" + getDoc_especialidad() + "' where doc_codigo = " + getDoc_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException eliminarDocente(int codigoDocente) {
        String sql = "UPDATE docente SET doc_estado= 'I' where doc_codigo = " + codigoDocente + ";";

        return conpg.accion(sql);
    }

    public List<Docente> listaDocentesTabla() {
        try {
            //Me retorna un "List" de "persona"
            List<Docente> lista = new ArrayList<>();

            String sql = "select * from persona, empleado, docente where per_codigo = emp_codper and emp_codigo = doc_codemp and doc_estado = 'A'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                Docente docente = new Docente();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                docente.setPer_codigo(rs.getInt("per_codigo"));
                docente.setPer_cedula(rs.getString("per_cedula"));
                docente.setPer_primernom(rs.getString("per_primernom"));
                docente.setPer_segundonom(rs.getString("per_segundonom"));
                docente.setPer_apellidopater(rs.getString("per_apellidopater"));
                docente.setPer_apellidomater(rs.getString("per_apellidomater"));
                docente.setPer_fechanac(rs.getDate("per_fechanac"));
                docente.setPer_genero(rs.getString("per_genero"));
                docente.setPer_telefono(rs.getString("per_telefono"));
                docente.setPer_email(rs.getString("per_email"));
                docente.setPer_direccion(rs.getString("per_direccion"));
                docente.setEmp_codigo(rs.getInt("emp_codigo"));
                docente.setEmp_codper(rs.getInt("emp_codper"));
                docente.setEmp_fechacontratacion(rs.getDate("emp_fechacontratacion"));
                docente.setEmp_salario(rs.getDouble("emp_salario"));
                docente.setDoc_codigo(rs.getInt("doc_codigo"));
                docente.setDoc_codemp(rs.getInt("doc_codemp"));
                docente.setDoc_especialidad(rs.getString("doc_especialidad"));
                docente.setDoc_estado(rs.getString("doc_estado"));

                lista.add(docente); //Agrego los datos a la lista
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

    public List<Docente> buscarDocente(String cedula) {
        try {
            //Me retorna un "List" de "persona"
            List<Docente> lista = new ArrayList<>();

            String sql = "select * from persona, empleado, docente where per_codigo = emp_codper and emp_codigo = doc_codemp and doc_estado = 'A' and per_cedula like '" + cedula + "%'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                Docente docente = new Docente();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                docente.setPer_codigo(rs.getInt("per_codigo"));
                docente.setPer_cedula(rs.getString("per_cedula"));
                docente.setPer_primernom(rs.getString("per_primernom"));
                docente.setPer_segundonom(rs.getString("per_segundonom"));
                docente.setPer_apellidopater(rs.getString("per_apellidopater"));
                docente.setPer_apellidomater(rs.getString("per_apellidomater"));
                docente.setPer_fechanac(rs.getDate("per_fechanac"));
                docente.setPer_genero(rs.getString("per_genero"));
                docente.setPer_telefono(rs.getString("per_telefono"));
                docente.setPer_email(rs.getString("per_email"));
                docente.setPer_direccion(rs.getString("per_direccion"));
                docente.setEmp_codigo(rs.getInt("emp_codigo"));
                docente.setEmp_codper(rs.getInt("emp_codper"));
                docente.setEmp_fechacontratacion(rs.getDate("emp_fechacontratacion"));
                docente.setEmp_salario(rs.getDouble("emp_salario"));
                docente.setDoc_codigo(rs.getInt("doc_codigo"));
                docente.setDoc_codemp(rs.getInt("doc_codemp"));
                docente.setDoc_especialidad(rs.getString("doc_especialidad"));
                docente.setDoc_estado(rs.getString("doc_estado"));

                lista.add(docente); //Agrego los datos a la lista
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
    public int traerCodigoDeDocenteEliminar(String cedula) { //Retorna el codigo maximo. Este codigo sirve para crear un docente en la BD
        int codigo = 0;
        try {

            String sql = "select doc_codigo from persona,empleado,docente where per_cedula = '" + cedula + "' and per_codigo = emp_codper and emp_codigo = doc_codemp;";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                codigo = rs.getInt("doc_codigo"); //Trae el codigo de la persona recien creada

            }

            //Cierro la conexion a la BD
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ModeloEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return codigo;
    }
}
