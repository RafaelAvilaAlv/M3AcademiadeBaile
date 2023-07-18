package Modelo;

import Conexion.ConexionPG;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloEmpleado extends Empleado {

    ConexionPG conpg = new ConexionPG();

    public ModeloEmpleado() {
    }

    public ModeloEmpleado(int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario) {
        super(emp_codigo, emp_codper, emp_fechacontratacion, emp_salario);
    }

    public ModeloEmpleado(int emp_codigo, int emp_codper, Date emp_fechacontratacion, Double emp_salario, int per_codigo, String per_cedula, String per_primernom, String per_segundonom, String per_apellidopater, String per_apellidomater, java.util.Date per_fechanac, String per_genero, String per_telefono, String per_email, String per_direccion) {
        super(emp_codigo, emp_codper, emp_fechacontratacion, emp_salario, per_codigo, per_cedula, per_primernom, per_segundonom, per_apellidopater, per_apellidomater, per_fechanac, per_genero, per_telefono, per_email, per_direccion);
    }

    public SQLException crearEmpleado() {
        String sql = "INSERT INTO empleado(emp_codper, emp_fechacontratacion, emp_salario) VALUES (" + getEmp_codper() + ", '" + getEmp_fechacontratacion() + "', " + getEmp_salario() + ");";

        return conpg.accion(sql);
    }

    public SQLException modificarEmpleado() {
        String sql = "UPDATE empleado SET emp_fechacontratacion= '" + getEmp_fechacontratacion() + "', emp_salario='" + getEmp_salario() + "' where emp_codigo = " + getEmp_codigo()+ ";";

        return conpg.accion(sql);
    }

    public List<Empleado> listaEmpleadoTabla() {
        try {
            //Me retorna un "List" de "persona"
            List<Empleado> lista = new ArrayList<>();

            String sql = "select * from empleado";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de los docentes
                Empleado empleado = new Empleado();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                empleado.setEmp_codigo(rs.getInt("emp_codigo"));
                empleado.setEmp_codper(rs.getInt("emp_codper"));
                empleado.setEmp_fechacontratacion(rs.getDate("emp_fechacontratacion"));
                empleado.setEmp_salario(rs.getDouble("emp_salario"));

                lista.add(empleado); //Agrego los datos a la lista
            }

            //Cierro la conexion a la BD
            rs.close();
            //Retorno la lista
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int traerCodigoDeEmpleadoCrear() { //Retorna el codigo maximo. Este codigo sirve para crear un docente en la BD
        int codigo = 0;
        try {

            String sql = "select max(emp_codigo) from empleado";

            ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                codigo = rs.getInt("max"); //Trae el codigo de la persona recien creada

            }

            //Cierro la conexion a la BD
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ModeloEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return codigo;
    }
}
