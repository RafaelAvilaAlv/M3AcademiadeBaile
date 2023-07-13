package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionPG {

    Connection con;

    //String cadenaConexion = "jdbc:postgresql://containers-us-west-141.railway.app:6363/railway";
    String cadenaConexion = "jdbc:postgresql://localhost:5432/";
    //jdbc:postgresql://localhost:5432/ProyectoAcademiaMusical
    String usuarioPG = "postgres";
    String passPG = "1234";
    //bfml1123 


    public ConexionPG() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            con = DriverManager.getConnection(cadenaConexion, usuarioPG, passPG);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet consulta(String sql) {

        try {
            Statement st = con.createStatement();
            return st.executeQuery(sql);

        } catch (SQLException ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //FORMA DE SQLException
    public SQLException accion(String sql) {
        try {
            Statement st = con.createStatement();
            st.execute(sql);
            st.close();
            return null;
        } catch (SQLException ex) {
            //Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex); //NO PONER EL LOGGER, CASO CONTRARIO SALE LA EXECEPCION EN LA CONSOLA
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            return ex;
        }

    }

    public Connection getCon() {
        return con;
    }

    //FORMA DE BOOLEAN
//    public boolean accion(String sql) {
//        boolean correcto;
//
//        try {
//            Statement st = con.createStatement();
//            st.execute(sql);
//            st.close();
//            correcto = true;
//        } catch (SQLException ex) {
//            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, "Captura: " + ex.getMessage());
//            correcto = false;
//        }
//
//        return correcto;
//    }
}
