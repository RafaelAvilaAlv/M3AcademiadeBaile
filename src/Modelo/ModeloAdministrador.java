package Modelo;

import Conexion.ConexionPG;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloAdministrador extends Administrador {

    ConexionPG conpg = new ConexionPG();

    public ModeloAdministrador() {
    }

    public ModeloAdministrador(int adm_copemp, int adm_codigo, String adm_usuario, String adm_clave, String adm_estado) {
        super(adm_copemp, adm_codigo, adm_usuario, adm_clave, adm_estado);
    }

    public ModeloAdministrador(int adm_copemp, int adm_codigo, String adm_usuario, String adm_clave, String adm_estado, int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario) {
        super(adm_copemp, adm_codigo, adm_usuario, adm_clave, adm_estado, emp_codigo, emp_codper, emp_fechacontratacion, emp_salario);
    }

    public ModeloAdministrador(int adm_copemp, int adm_codigo, String adm_usuario, String adm_clave, String adm_estado, int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario, int per_codigo, String per_cedula, String per_primernom, String per_segundonom, String per_apellidopater, String per_apellidomater, java.util.Date per_fechanac, String per_genero, String per_telefono, String per_email, String per_direccion) {
        super(adm_copemp, adm_codigo, adm_usuario, adm_clave, adm_estado, emp_codigo, emp_codper, emp_fechacontratacion, emp_salario, per_codigo, per_cedula, per_primernom, per_segundonom, per_apellidopater, per_apellidomater, per_fechanac, per_genero, per_telefono, per_email, per_direccion);
    }

    public SQLException crearAdministrador() {

        String sql = "INSERT INTO administrador(adm_codemp, adm_usuario, adm_clave, adm_estado) VALUES (" + getAdm_codemp() + ", '" + getAdm_usuario() + "','" + getAdm_clave() + "', 'A');";

        return conpg.accion(sql);
    }

    public SQLException modificarAdministrador() {
        String sql = "UPDATE administrador SET adm_usuario= '" + getAdm_usuario() + "', adm_clave= '" + getAdm_clave() + "' where adm_codigo = " + getAdm_codigo() + ";";

        return conpg.accion(sql);
    }

    public SQLException eliminarAdministrador(int codigoAdministrador) {
        String sql = "UPDATE administrador SET adm_estado= 'I' where adm_codigo = " + codigoAdministrador + ";";

        return conpg.accion(sql);
    }

    public List<Administrador> listaAdministradoresTabla() {
        try {
            //Me retorna un "List" de "persona"
            List<Administrador> lista = new ArrayList<>();

            String sql = "select * from persona, empleado, administrador where per_codigo = emp_codper and emp_codigo = adm_codemp and adm_estado = 'A'";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                Administrador administrador = new Administrador();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                administrador.setPer_codigo(rs.getInt("per_codigo"));
                administrador.setPer_cedula(rs.getString("per_cedula"));
                administrador.setPer_primernom(rs.getString("per_primernom"));
                administrador.setPer_segundonom(rs.getString("per_segundonom"));
                administrador.setPer_apellidopater(rs.getString("per_apellidopater"));
                administrador.setPer_apellidomater(rs.getString("per_apellidomater"));
                administrador.setPer_fechanac(rs.getDate("per_fechanac"));
                administrador.setPer_genero(rs.getString("per_genero"));
                administrador.setPer_telefono(rs.getString("per_telefono"));
                administrador.setPer_email(rs.getString("per_email"));
                administrador.setPer_direccion(rs.getString("per_direccion"));
                administrador.setEmp_codigo(rs.getInt("emp_codigo"));
                administrador.setEmp_codper(rs.getInt("emp_codper"));
                administrador.setEmp_fechacontratacion(rs.getDate("emp_fechacontratacion"));
                administrador.setEmp_salario(rs.getDouble("emp_salario"));
                administrador.setAdm_codigo(rs.getInt("adm_codigo"));
                administrador.setAdm_codemp(rs.getInt("adm_codemp"));
                administrador.setAdm_usuario(rs.getString("adm_usuario"));
                administrador.setAdm_clave(rs.getString("adm_clave"));
                administrador.setAdm_estado(rs.getString("adm_estado"));

                lista.add(administrador); //Agrego los datos a la lista
            }

            //Cierro la conexion a la BD
            rs.close();
            //Retorno la lista
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Administrador> buscarAdministrador(String cedula) {
        try {
            //Me retorna un "List" de "persona"
            List<Administrador> lista = new ArrayList<>();

            String sql = "select * from persona, empleado, administrador where per_codigo = emp_codper and emp_codigo = adm_codemp and adm_estado = 'A' and per_cedula like '" + cedula + "%'";
            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                Administrador administrador = new Administrador();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                administrador.setPer_codigo(rs.getInt("per_codigo"));
                administrador.setPer_cedula(rs.getString("per_cedula"));
                administrador.setPer_primernom(rs.getString("per_primernom"));
                administrador.setPer_segundonom(rs.getString("per_segundonom"));
                administrador.setPer_apellidopater(rs.getString("per_apellidopater"));
                administrador.setPer_apellidomater(rs.getString("per_apellidomater"));
                administrador.setPer_fechanac(rs.getDate("per_fechanac"));
                administrador.setPer_genero(rs.getString("per_genero"));
                administrador.setPer_telefono(rs.getString("per_telefono"));
                administrador.setPer_email(rs.getString("per_email"));
                administrador.setPer_direccion(rs.getString("per_direccion"));
                administrador.setEmp_codigo(rs.getInt("emp_codigo"));
                administrador.setEmp_codper(rs.getInt("emp_codper"));
                administrador.setEmp_fechacontratacion(rs.getDate("emp_fechacontratacion"));
                administrador.setEmp_salario(rs.getDouble("emp_salario"));
                administrador.setAdm_codigo(rs.getInt("adm_codigo"));
                administrador.setAdm_codemp(rs.getInt("adm_codemp"));
                administrador.setAdm_usuario(rs.getString("adm_usuario"));
                administrador.setAdm_clave(rs.getString("adm_clave"));
                administrador.setAdm_estado(rs.getString("adm_estado"));

                lista.add(administrador); //Agrego los datos a la lista
            }

            //Cierro la conexion a la BD
            rs.close();
            //Retorno la lista
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //Dependiendo del numero de cedula trae el codigo del docente que se va a eliminar
    public int traerCodigoDeAdministradorEliminar(String cedula) { //Retorna el codigo maximo. Este codigo sirve para crear un docente en la BD
        int codigo = 0;
        try {

            String sql = "select adm_codigo from persona,empleado,administrador where per_cedula = '" + cedula + "' and per_codigo = emp_codper and emp_codigo = adm_codemp;";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                codigo = rs.getInt("adm_codigo"); //Trae el codigo de la persona recien creada

            }

            //Cierro la conexion a la BD
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ModeloAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return codigo;
    }

    public int validarUsuariosRepetidos(String usuario) { //Metodo que sirve para validar la cantidad de usuarios registrados en la BD
        int cantidad = 0;
        try {

            //Cuenta la cantidad de nombres de usuario que se repiten siempre y cuando el estado este en 'A' y si el estado esta en 'I' no lo toma en cuenta
            String sql = "select COUNT(*) from administrador where adm_usuario ='" + usuario + "' and adm_estado = 'A'"; 

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                cantidad = rs.getInt("COUNT"); //Trae la cantidad de usuarios repetidos

            }

            //Cierro la conexion a la BD
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cantidad;
    }
}
