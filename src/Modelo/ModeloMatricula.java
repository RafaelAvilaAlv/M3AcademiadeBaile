package Modelo;

import Conexion.ConexionPG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloMatricula extends Matricula {

    ConexionPG conpg = new ConexionPG();

    public ModeloMatricula() {
    }

    public ModeloMatricula(int mat_codigo, int mat_codAmd, int mat_codEst, int mat_codCur, Date mat_fechamatri, String mat_estado) {
        super(mat_codigo, mat_codAmd, mat_codEst, mat_codCur, mat_fechamatri, mat_estado);
    }

    public SQLException crearMatricula() {

        String sql = "INSERT INTO matricula(mat_codamd, mat_codest, mat_codcurso, mat_fechamatri, mat_estado) VALUES (" + getMat_codAmd() + ", " + getMat_codEst() + ", " + getMat_codCur() + ", '" + getMat_fechamatri() + "','A');";

        return conpg.accion(sql);
    }

    public SQLException eliminarMatricula(int codigoMatricula) {
        String sql = "UPDATE matricula SET mat_estado= 'I' where mat_codigo = " + codigoMatricula + ";";

        return conpg.accion(sql);
    }

    public List<Matricula> listaMatriculaTabla() {
        try {
            List<Matricula> lista = new ArrayList<>();

            String sql = "select * from matricula where mat_estado = 'A'";

            ResultSet rs = conpg.consulta(sql);

            while (rs.next()) {

                Matricula matricula = new Matricula();

                matricula.setMat_codigo(rs.getInt("mat_codigo"));
                matricula.setMat_codAmd(rs.getInt("mat_codamd"));
                matricula.setMat_codEst(rs.getInt("mat_codest"));
                matricula.setMat_codCur(rs.getInt("mat_codcurso"));
                matricula.setMat_fechamatri(rs.getDate("mat_fechamatri"));
                matricula.setMat_estado(rs.getString("mat_estado"));

                lista.add(matricula);
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
}
