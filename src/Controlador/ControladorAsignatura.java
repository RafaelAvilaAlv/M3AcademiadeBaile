package Controlador;

import Modelo.Asignatura;
import Conexion.ConexionPG;
import Modelo.Curso;
import Modelo.ModeloAsignatura;
import Modelo.ModeloCurso;
import Vista.VistaAsignatura;
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
import javax.xml.ws.Holder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControladorAsignatura {

    ModeloAsignatura modelo;
    VistaAsignatura vista;

    VistaPrincipal p = new VistaPrincipal();

    public ControladorAsignatura(ModeloAsignatura modelo, VistaAsignatura vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        cargarTablaDeAsignaturas();
    }

    public void iniciarControl() {

        vista.getBtnCrear().addActionListener(l -> abrirjDlgAsignatura());
        vista.getBtnGuardar().addActionListener(l -> crearEditarAsignatura());
        vista.getBtnModificar().addActionListener(l -> cargarDatosAsignaturaEnTXT());
        vista.getBtnBuscarCurso().addActionListener(l -> abrirjDialogCurso());
        vista.getBtnCargarCur().addActionListener(l -> cargarDatosCursoEnTXT());
        vista.getBtnActualizar().addActionListener(l -> cargarTablaDeAsignaturas());
        vista.getBtnEliminar().addActionListener(l -> eliminarAsignatura());
        vista.getBtnCancelar().addActionListener(l -> cancelar());
        vista.getBtnImprimir().addActionListener(l -> imprimir());
        buscarAsignatura();
    }

    public void abrirjDlgAsignatura() {
        limpiarDatos(); //Limpia los datos cada vez que se habra la ventana para crear la asignatura

        vista.getjDlgAsignatura().setVisible(true);
        vista.getjDlgAsignatura().setSize(1080, 345);
        vista.getjDlgAsignatura().setLocationRelativeTo(null);
        vista.getjDlgAsignatura().setName("Crear nueva asignatura");
        vista.getjDlgAsignatura().setTitle("Crear nueva asignatura");
        bloquearcampos();
        limpiarDatos();
    }

    public void imprimir() {

        ConexionPG cpg = new ConexionPG();//Instanciar la conexion con esto abrimos la conexion a la BD
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/AsignaturaReporte.jasper"));

            //Hacer una vista previa
            //JasperPrint jp = JasperFillManager.fillReport(jr, null, cpg.getCon());//JasperFillManager.fillReport: Carga los datos de la BD.//JasperPrint: Hace la impresion del reporte. Puede ir 'null' si en el jasper no existen parametros caso contrario se envian los parametros necesarios
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("ImagenRuta", "src/imagenesJasper/horario.png"); //En donde esta 'titulo' tienen que ser igual al nombre que esta en el parametro del jasper

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cpg.getCon());//'parametros' es el Map recien creado que contiene los parametros que iran al jasper

            JasperViewer jv = new JasperViewer(jp, false); //Se pasa false para que no se cierre el sistema 
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearEditarAsignatura() {

        if ("Crear nueva asignatura".equals(vista.getjDlgAsignatura().getName())) {

            if (validarDatos()) {
                ModeloAsignatura asignatura = new ModeloAsignatura();

                //Setear el codigo del curso
                asignatura.setAsi_codCurso(Integer.parseInt(vista.getTxtcodigoCurso().getText()));

                //Setear los datos de la asignatura
                asignatura.setAsi_nombre(vista.getTxtNombreAsignatura().getText());
                asignatura.setAsi_descripcion(vista.getDescripcion().getText());

                if (asignatura.crearAsignatura() == null) {

                    JOptionPane.showMessageDialog(null, "Asignatura creada correctamente");
                    vista.getjDlgAsignatura().setVisible(false);
                    cargarTablaDeAsignaturas();

                } else {
                    JOptionPane.showConfirmDialog(null, "Error: No se pudo crear la asignatura");
                }
            }

        } else {

            if (validarDatos()) {
                //EDITAR
                ModeloAsignatura asignatura = new ModeloAsignatura();

                //Setear el codigo del curso
                asignatura.setAsi_codCurso(Integer.parseInt(vista.getTxtcodigoCurso().getText()));

                //Setear los datos de la asignatura
                asignatura.setAsi_codigo(codigoAsignatura);
                asignatura.setAsi_nombre(vista.getTxtNombreAsignatura().getText());
                asignatura.setAsi_descripcion(vista.getDescripcion().getText());

                if (asignatura.modificarAsignatura() == null) {

                    JOptionPane.showMessageDialog(null, "Asignatura modificada correctamente");
                    vista.getjDlgAsignatura().setVisible(false);
                    cargarTablaDeAsignaturas();

                } else {
                    JOptionPane.showConfirmDialog(null, "Error: No se pudo modificar la asignatura");
                }
            }
        }
    }

    public void cargarTablaDeAsignaturas() {
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblAsignatura().getModel();
        tabla.setNumRows(0);

        ModeloCurso curso = new ModeloCurso();
        List<Curso> cursos = curso.listaCursoTabla();
        List<Asignatura> asignaturas = modelo.listaAsignaturaTabla();
        asignaturas.stream().forEach(p -> {

            cursos.stream().forEach(c -> {
                if (p.getAsi_codCurso() == c.getCur_codigo()) {
                    String[] datos = {String.valueOf(p.getAsi_codigo()), p.getAsi_nombre(), c.getCur_nombre()};
                    tabla.addRow(datos);
                }
            });
        });
    }

    public void eliminarAsignatura() {
        ModeloAsignatura asignatura = new ModeloAsignatura();

        int fila = vista.getTblAsignatura().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigoAsignatura;
                codigoAsignatura = Integer.parseInt(vista.getTblAsignatura().getValueAt(fila, 0).toString());

                if (asignatura.eliminarAsignatura(codigoAsignatura) == null) {
                    JOptionPane.showMessageDialog(null, "La asignatura fue eliminada exitosamente");
                    cargarTablaDeAsignaturas();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "Error: La asignatura no se pudo eliminar");
                }
            }
        }
    }

    int codigoAsignatura;

    public void cargarDatosAsignaturaEnTXT() {
        int fila = vista.getTblAsignatura().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            //Quito la visibilidad del txt del codigo del curso
            vista.getTxtcodigoCurso().setVisible(false);

            //Abrir jDialog de campos de Docente
            vista.getjDlgAsignatura().setName("Modificar asignatura");
            vista.getjDlgAsignatura().setLocationRelativeTo(null);
            vista.getjDlgAsignatura().setSize(1061, 345);
            vista.getjDlgAsignatura().setTitle("Modificar asignatura");
            vista.getjDlgAsignatura().setVisible(true);

            //Creo un objeto de la clase ModeloCurso para traer el listado de cursos
            ModeloCurso curso = new ModeloCurso();
            List<Curso> listac = curso.listaCursoTabla();

            List<Asignatura> listap = modelo.listaAsignaturaTabla();

            listap.stream().forEach(a -> {

                if (a.getAsi_codigo() == Integer.parseInt(vista.getTblAsignatura().getValueAt(fila, 0).toString())) {

                    codigoAsignatura = a.getAsi_codigo(); //Obtengo el codigo de la asignatura el cual sirve para poder modificar los datos.

                    listac.stream().forEach(c -> {

                        if (a.getAsi_codCurso() == c.getCur_codigo()) {

                            vista.getTxtcodigoCurso().setText(String.valueOf(c.getCur_codigo()));
                            vista.getTxtNombreCurso().setText(c.getCur_nombre());
                            vista.getTxtPeriodoCurso().setText(c.getCur_periodo());
                            vista.getTxtPrecioCurso().setText(String.valueOf(c.getCur_precio()));
                            vista.getTxtNombreAsignatura().setText(a.getAsi_nombre());
                            vista.getDescripcion().setText(a.getAsi_descripcion());
                        }
                    });
                }
            });
        }
    }

    //Todo sobre el registro de cursos en el jDialog
    public void abrirjDialogCurso() {
        vista.getjDlgBuscarCurso().setVisible(true);
        vista.getjDlgBuscarCurso().setSize(661, 414);
        vista.getjDlgBuscarCurso().setLocationRelativeTo(null);
        vista.getjDlgBuscarCurso().setTitle("Seleccione un curso");
        cargarRegistroDeCursos();
        buscarCurso();
    }

    public void cargarRegistroDeCursos() {

        ModeloCurso modeloCurso = new ModeloCurso();
        vista.getTblDlgjCurso().setRowHeight(25);
        DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgjCurso().getModel();
        estructuraTabla.setRowCount(0);

        List<Curso> listap = modeloCurso.listaCursoTabla();

        Holder<Integer> i = new Holder<>(0);

        listap.stream().forEach(c -> {

            estructuraTabla.addRow(new Object[8]);

            vista.getTblDlgjCurso().setValueAt(c.getCur_codigo(), i.value, 0);
            vista.getTblDlgjCurso().setValueAt(c.getCur_nombre(), i.value, 1);
            vista.getTblDlgjCurso().setValueAt(c.getCur_periodo(), i.value, 2);

            i.value++;
        });
    }

    public void cargarDatosCursoEnTXT() {
        int fila = vista.getTblDlgjCurso().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            //Quito la visibilidad del txt del codigo del curso
            vista.getTxtcodigoCurso().setVisible(false);

            ModeloCurso modeloCurso = new ModeloCurso();
            List<Curso> listap = modeloCurso.listaCursoTabla();

            listap.stream().forEach(c -> {

                if (c.getCur_codigo() == Integer.parseInt(vista.getTblDlgjCurso().getValueAt(fila, 0).toString())) {

                    //codigoCurso = Integer.parseInt(vista.getTblDlgjCurso().getValueAt(fila, 0).toString());
                    vista.getTxtcodigoCurso().setText(String.valueOf(c.getCur_codigo()));
                    vista.getTxtNombreCurso().setText(c.getCur_nombre());
                    vista.getTxtPeriodoCurso().setText(c.getCur_periodo());
                    vista.getTxtPrecioCurso().setText(String.valueOf(c.getCur_precio()));

                }
            });

            vista.getjDlgBuscarCurso().dispose();
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

                ModeloCurso modeloCurso = new ModeloCurso();
                vista.getTblDlgjCurso().setRowHeight(25);
                DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgjCurso().getModel();
                estructuraTabla.setRowCount(0);

                List<Curso> listap = modeloCurso.buscarCurso(vista.getTxtBuscarCur().getText());

                Holder<Integer> i = new Holder<>(0);

                listap.stream().forEach(c -> {

                    estructuraTabla.addRow(new Object[8]);

                    vista.getTblDlgjCurso().setValueAt(c.getCur_codigo(), i.value, 0);
                    vista.getTblDlgjCurso().setValueAt(c.getCur_nombre(), i.value, 1);
                    vista.getTblDlgjCurso().setValueAt(c.getCur_periodo(), i.value, 2);

                    i.value++;
                });
            }
        };

        vista.getTxtBuscarCur().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    public void buscarAsignatura() {

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
                DefaultTableModel tabla = (DefaultTableModel) vista.getTblAsignatura().getModel();
                tabla.setNumRows(0);

                ModeloCurso curso = new ModeloCurso();
                List<Curso> cursos = curso.listaCursoTabla();
                List<Asignatura> asignaturas = modelo.buscarAsignatura(vista.getTxtBuscar().getText());
                asignaturas.stream().forEach(p -> {

                    cursos.stream().forEach(c -> {
                        if (p.getAsi_codCurso() == c.getCur_codigo()) {
                            String[] datos = {String.valueOf(p.getAsi_codigo()), p.getAsi_nombre(), c.getCur_nombre()};
                            tabla.addRow(datos);
                        }
                    });
                });
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    public boolean validarDatos() {
        Validaciones mivalidacion = new Validaciones();

        boolean validar = true;

        if (vista.getTxtNombreCurso().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un curso");
            validar = false;
        }

        if (vista.getTxtNombreAsignatura().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre de la asignatura");
            validar = false;
        } else {
            if (!mivalidacion.validarTextoConEspacio(vista.getTxtNombreAsignatura().getText())) {
                JOptionPane.showMessageDialog(null, "Nombre de la asignatura incorrecto");
                validar = false;
            }
        }

        if (vista.getDescripcion().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de la descripción no puede estar vacio");
            validar = false;
        }

        return validar;
    }

    public void limpiarDatos() {
        vista.getTxtNombreCurso().setText("");
        vista.getTxtPeriodoCurso().setText("");
        vista.getTxtPrecioCurso().setText("");
        vista.getTxtNombreAsignatura().setText("");
        vista.getDescripcion().setText("");
    }

    public void bloquearcampos() {
        vista.getTxtNombreCurso().setEditable(false);
        vista.getTxtPeriodoCurso().setEditable(false);
        vista.getTxtPrecioCurso().setEditable(false);
        vista.getTxtcodigoCurso().setVisible(false);
    }

    public void cancelar() {
        vista.getjDlgAsignatura().setVisible(false);
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
