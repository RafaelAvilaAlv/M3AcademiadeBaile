package Controlador;

import Conexion.ConexionPG;
import Modelo.Curso;
import Modelo.ModeloCurso;
import Vista.VistaCurso;
import Vista.VistaPrincipal;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControladorCurso {

    ModeloCurso modelo;
    VistaCurso vista;

    VistaPrincipal p = new VistaPrincipal();

    public ControladorCurso(ModeloCurso modelo, VistaCurso vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);

        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        vista.getjDlgCurso().setResizable(false);
        cargarTablaDeCurso();
    }

    public void iniciarControl() {
        vista.getBtnCrear().addActionListener(l -> abrirDialogCrear());
        vista.getBtnGuardar().addActionListener(l -> crearEditarCurso());
        vista.getBtnActualizar().addActionListener(l -> cargarTablaDeCurso());
        vista.getBtnModificar().addActionListener(l -> cargarDatosCursoEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarCurso());
        vista.getBtnCancelar().addActionListener(l -> botonCancelar());
        vista.getBtnImprimir().addActionListener(l -> imprimir());
        buscarCurso();
    }

    public void imprimir() {

        ConexionPG cpg = new ConexionPG();//Instanciar la conexion con esto abrimos la conexion a la BD
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/CursoReporte.jasper"));

            //Hacer una vista previa
            //JasperPrint jp = JasperFillManager.fillReport(jr, null, cpg.getCon());//JasperFillManager.fillReport: Carga los datos de la BD.//JasperPrint: Hace la impresion del reporte. Puede ir 'null' si en el jasper no existen parametros caso contrario se envian los parametros necesarios
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("RutaImagen", "src/imagenesJasper/curso.png"); //En donde esta 'titulo' tienen que ser igual al nombre que esta en el parametro del jasper

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cpg.getCon());//'parametros' es el Map recien creado que contiene los parametros que iran al jasper

            JasperViewer jv = new JasperViewer(jp, false); //Se pasa false para que no se cierre el sistema 
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrirDialogCrear() {
        vista.getjDlgCurso().setVisible(true);
        vista.getjDlgCurso().setSize(602, 592);
        vista.getjDlgCurso().setLocationRelativeTo(null);
        vista.getjDlgCurso().setName("Crear nuevo curso");
        vista.getjDlgCurso().setTitle("Crear nuevo curso");

        //Limpiar los datos del jDialog
        limpiarDatos();
    }

    public void cargarTablaDeCurso() {
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblCurso().getModel();
        tabla.setNumRows(0);

        List<Curso> cursos = modelo.listaCursoTabla();
        cursos.stream().forEach(p -> {
            String[] datos = {String.valueOf(p.getCur_codigo()), p.getCur_nombre(), p.getCur_periodo(), p.getCur_fechaInicio().toString(), p.getCur_fechaFin().toString(), String.valueOf(p.getCur_precio())};
            tabla.addRow(datos);
        });
    }

    private void crearEditarCurso() {
        if ("Crear nuevo curso".equals(vista.getjDlgCurso().getName())) {

            //INSERTAR
            if (validarDatos()) {
                ModeloCurso curso = new ModeloCurso();
                curso.setCur_nombre(vista.getTxtNombre().getText());
                curso.setCur_periodo(vista.getTxtPeriodo().getText());
                curso.setCur_descripcion(vista.getDescripcion().getText());
                curso.setCur_precio(Double.parseDouble(vista.getSpinnerPrecio().getValue().toString()));

                java.sql.Date fechaIni = new java.sql.Date(vista.getFechaInicio().getDate().getTime());//Paso de util.Date a sql.Date
                java.sql.Date fechaFin = new java.sql.Date(vista.getFechFin().getDate().getTime());
                curso.setCur_fechaInicio(fechaIni);
                curso.setCur_fechaFin(fechaFin);

                if (curso.crearCurso() == null) {
                    vista.getjDlgCurso().setVisible(false);
                    JOptionPane.showMessageDialog(vista, "Curso creado satisfactoriamente");
                    cargarTablaDeCurso();
                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo crear el curso");
                }
            }

        } else {

            //EDITAR
            if (validarDatos()) {
                ModeloCurso curso = new ModeloCurso();
                curso.setCur_codigo(codigoCurso);
                curso.setCur_nombre(vista.getTxtNombre().getText());
                curso.setCur_periodo(vista.getTxtPeriodo().getText());
                curso.setCur_descripcion(vista.getDescripcion().getText());
                curso.setCur_precio(Double.parseDouble(vista.getSpinnerPrecio().getValue().toString()));

                java.sql.Date fechaIni = new java.sql.Date(vista.getFechaInicio().getDate().getTime());//Paso de util.Date a sql.Date
                java.sql.Date fechaFin = new java.sql.Date(vista.getFechFin().getDate().getTime());
                curso.setCur_fechaInicio(fechaIni);
                curso.setCur_fechaFin(fechaFin);

                if (curso.modificarCurso() == null) {
                    vista.getjDlgCurso().setVisible(false);
                    JOptionPane.showMessageDialog(vista, "Curso modificado satisfactoriamente");
                    cargarTablaDeCurso();
                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo modificar el curso");
                }
            }
        }

        cargarTablaDeCurso(); //Actualizo la tabla con los datos
    }

    public void eliminarCurso() {

        int fila = vista.getTblCurso().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigo;
                codigo = Integer.parseInt(vista.getTblCurso().getValueAt(fila, 0).toString());

                if (modelo.eliminarCurso(codigo) == null) {
                    JOptionPane.showMessageDialog(null, "El curso fue eliminado exitosamente");
                    cargarTablaDeCurso();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "Error: El curso no pudo ser eliminado");
                }
            }
        }
    }

    int codigoCurso;//Variable que almacenara el codigo del curso traido de la BD

    public void cargarDatosCursoEnTXT() {
        int fila = vista.getTblCurso().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            //Abrir jDialog de campos de Docente
            vista.getjDlgCurso().setName("Modificar curso");
            vista.getjDlgCurso().setLocationRelativeTo(null);
            vista.getjDlgCurso().setSize(602, 592);
            vista.getjDlgCurso().setTitle("Modificar  curso");
            vista.getjDlgCurso().setVisible(true);

            //ModeloPersona modeloPersona = new ModeloPersona();
            List<Curso> listap = modelo.listaCursoTabla();

            listap.stream().forEach(curso -> {

                if (curso.getCur_codigo() == Integer.parseInt(vista.getTblCurso().getValueAt(fila, 0).toString())) {
                    codigoCurso = curso.getCur_codigo();
                    vista.getTxtNombre().setText(curso.getCur_nombre());
                    vista.getTxtPeriodo().setText(curso.getCur_periodo());
                    vista.getFechaInicio().setDate(curso.getCur_fechaInicio());
                    vista.getFechFin().setDate(curso.getCur_fechaFin());
                    vista.getDescripcion().setText(curso.getCur_descripcion());
                    vista.getSpinnerPrecio().setValue(curso.getCur_precio());
                }
            });
        }
    }

    public void buscarCurso() {

        KeyListener eventoTeclado = new KeyListener() {//Crear un objeto de tipo keyListener(Es una interface) por lo tanto se debe implementar sus metodos abstractos

            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblCurso().getModel();
                tabla.setNumRows(0);

                List<Curso> cursos = modelo.buscarCurso(vista.getTxtBuscar().getText());
                cursos.stream().forEach(p -> {
                    String[] datos = {String.valueOf(p.getCur_codigo()), p.getCur_nombre(), p.getCur_periodo(), p.getCur_fechaInicio().toString(), p.getCur_fechaFin().toString(), String.valueOf(p.getCur_precio())};
                    tabla.addRow(datos);
                });
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    public boolean validarDatos() {
        Validaciones mivalidacion = new Validaciones();

        boolean validar = true;

        if (vista.getTxtNombre().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre");
            validar = false;
        } else {
            if (!mivalidacion.validarTextoConEspacio(vista.getTxtNombre().getText())) {
                JOptionPane.showMessageDialog(null, "Nombre incorrecto");
                validar = false;
            }
        }
        if (vista.getTxtPeriodo().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el periodo academico");
            validar = false;
        } else {
            if (!mivalidacion.validarPeriodoAcademico(vista.getTxtPeriodo().getText())) {
                JOptionPane.showMessageDialog(null, "El periodo academico debe ser mayor o igual al año actual: Ejemplo 2023");
                validar = false;
            }
        }

        if (vista.getFechaInicio().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese la fecha de inicio");
            validar = false;
        }

        if (vista.getFechFin().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese la fecha fin");
            validar = false;
        }
        if (vista.getDescripcion().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No puede estar vacio el campo de la descripcion");
            validar = false;
        }

        return validar;
    }

    public void limpiarDatos() {
        vista.getTxtNombre().setText("");
        vista.getTxtPeriodo().setText("");
        vista.getFechaInicio().setDate(null);
        vista.getFechFin().setDate(null);
        vista.getDescripcion().setText("");
        vista.getSpinnerPrecio().setValue(0.1);
    }

    public void botonCancelar() {
        vista.getjDlgCurso().setVisible(false);
    }

    /*public void imprimir() {

        ConexionPG conpg = new ConexionPG();//Instanciar la conexion con esto abrimos la conexion a la BD
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/vista/reportes/Reporte mvc.jasper"));

            //Hacer una vista previa
            //JasperPrint jp = JasperFillManager.fillReport(jr, null, cpg.getCon());//JasperFillManager.fillReport: Carga los datos de la BD.//JasperPrint: Hace la impresion del reporte. Puede ir 'null' si en el jasper no existen parametros caso contrario se envian los parametros necesarios
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("titulo", vista.getTxtTitulo().getText()); //En donde esta 'titulo' tienen que ser igual al nombre que esta en el parametro del jasper
            parametros.put("limitea", Double.parseDouble(vista.getSpinnerSueldomaximo().getValue().toString()));
            parametros.put("limiteb", Double.parseDouble(vista.getSpinnerSueldominimo().getValue().toString()));//Cuando se quiere pasar un tipo de dato int '100' se coloca la 'd' despues del dato'100d'

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cpg.getCon());//'parametros' es el Map recien creado que contiene los parametros que iran al jasper

            JasperViewer jv = new JasperViewer(jp, false); //Se pasa false para que no se cierre el sistema 
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
