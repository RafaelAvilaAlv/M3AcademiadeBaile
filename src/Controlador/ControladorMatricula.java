package Controlador;

import static Controlador.ControladorLogin.usuario;
import Modelo.Administrador;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Matricula;
import Modelo.ModeloAdministrador;
import Modelo.ModeloCurso;
import Modelo.ModeloEstudiante;
import Modelo.ModeloMatricula;
import Vista.VistaMatricula;
import Vista.VistaPrincipal;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.ws.Holder;

public class ControladorMatricula {

    ModeloMatricula modelo;
    VistaMatricula vista;

    VistaPrincipal p = new VistaPrincipal();

    //Variable estatica para saber si el estudiante esta o no matriculado en un curso
    static boolean matriculado;

    public ControladorMatricula(ModeloMatricula modelo, VistaMatricula vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        cargarTablaDeMatriculas();
    }

    public void iniciarControl() {

        //Matricula
        vista.getBtnMatricular().addActionListener(l -> abrirjDialogMatricula());
        vista.getBtnGuardar().addActionListener(l -> crearMatricula());
        vista.getBtnEliminar().addActionListener(l -> eliminarMatricula());
        vista.getBtnCancelar().addActionListener(l -> botonCancelar());
        //vista.getBtnImprimir().addActionListener(l-> imprimir());
        buscarMatricula();
        //Cargar datos del estudiante
        vista.getBtnBuscarEstudiante().addActionListener(l -> abrirjDialogEstudiante());
        vista.getBtnCargarEst().addActionListener(l -> cargarDatosEstudianteEnTXT());
        //Cargar datos del curso
        vista.getBtnBuscarCurso().addActionListener(l -> abrirjDialogCurso());
        vista.getBtnCargarCur().addActionListener(l -> cargarDatosCursoEnTXT());
        //Cargar datos del administrador
        cargarDatosAdministradorEnTxt();
    }

    //Matricula
    public void abrirjDialogMatricula() {
        vista.getjDlgMatricula().setVisible(true);
        vista.getjDlgMatricula().setSize(858, 585);
        vista.getjDlgMatricula().setLocationRelativeTo(null);
        vista.getjDlgMatricula().setTitle("Registrar matricula");
        vista.getjDlgMatricula().setName("Registrar matricula");

        Date fecha = new Date();
        vista.getFechaDeMatricula().setDate(fecha);
        bloquearYVisibilidadCrear();
        limpiar();
    }

    public void cargarTablaDeMatriculas() {
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblMatricula().getModel();
        tabla.setNumRows(0);

        ModeloAdministrador modeloAdministrador = new ModeloAdministrador();
        ModeloEstudiante modeloEstudiante = new ModeloEstudiante();
        ModeloCurso modeloCurso = new ModeloCurso();

        List<Matricula> matriculas = modelo.listaMatriculaTabla();
        List<Administrador> administradores = modeloAdministrador.listaAdministradoresTabla();
        List<Estudiante> estudiantes = modeloEstudiante.listaEstudiantesTabla();
        List<Curso> cursos = modeloCurso.listaCursoTabla();

        matriculas.stream().forEach(m -> {

            administradores.stream().forEach(a -> {

                if (m.getMat_codAmd() == a.getAdm_codigo()) {

                    estudiantes.stream().forEach(e -> {

                        if (m.getMat_codEst() == e.getEst_codigo()) {

                            cursos.stream().forEach(c -> {

                                if (m.getMat_codCur() == c.getCur_codigo()) {
                                    String[] datos = {String.valueOf(m.getMat_codigo()), e.getPer_cedula(), e.getPer_primernom() + " " + e.getPer_apellidopater(), a.getPer_cedula(), a.getPer_primernom() + " " + a.getPer_apellidopater(), c.getCur_nombre()};
                                    tabla.addRow(datos);
                                }
                            });
                        }
                    });
                }
            });
        });
    }

    public void crearMatricula() {
        matriculado = false;
        if (vista.getjDlgMatricula().getName().equals("Registrar matricula")) {

            if (validarDatos()) {

                List<Matricula> matriculas = modelo.listaMatriculaTabla();

                matriculas.stream().forEach(m -> {

                    if (m.getMat_codEst() == Integer.parseInt(vista.getTxtCodigoEstudiantematri().getText()) && m.getMat_codCur() == Integer.parseInt(vista.getTxtCodigoCurso().getText()) && m.getMat_estado().equals("A")) {
                        matriculado = true;
                    }
                });

                if (matriculado) {
                    JOptionPane.showMessageDialog(null, "El estudiante ya se encuentra matriculado en este curso");
                } else {
                    modelo.setMat_codAmd(Integer.parseInt(vista.getTxtCodigoAdministrador().getText()));
                    modelo.setMat_codEst(Integer.parseInt(vista.getTxtCodigoEstudiantematri().getText()));
                    modelo.setMat_codCur(Integer.parseInt(vista.getTxtCodigoCurso().getText()));

                    Date fecha = vista.getFechaDeMatricula().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    modelo.setMat_fechamatri(fechaSQL);

                    if (modelo.crearMatricula() == null) {
                        JOptionPane.showMessageDialog(null, "Se registro satisfactoriamente la matricula.\nLa matrícula esta siendo enviada al email del estudiante...");
                        cargarTablaDeMatriculas();

                        
                        vista.getjDlgMatricula().setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puedo registrar la matricula");
                    }
                }
            }
        }
    }

    public void eliminarMatricula() {

        int fila = vista.getTblMatricula().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigoMatriculaEliminar;
                codigoMatriculaEliminar = Integer.parseInt(vista.getTblMatricula().getValueAt(fila, 0).toString());

                if (modelo.eliminarMatricula(codigoMatriculaEliminar) == null) {
                    JOptionPane.showMessageDialog(null, "La matricula fue eliminada exitosamente");
                    cargarTablaDeMatriculas();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "La matricula no se pudo eliminar");
                }
            }
        }
    }

    public void buscarMatricula() {

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

                //CODIGO PARA FILTRAR LOS DATOS DIRECTAMENTE DE LA TABLA. NO ELIMINAR. SI FUNCIONA. ES MUY IMPORTANTE
                TableRowSorter<DefaultTableModel> filtrar;

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblMatricula().getModel();

                //vista.getTablaconduccion().setAutoCreateRowSorter(true);
                filtrar = new TableRowSorter<>(tabla);
                vista.getTblMatricula().setRowSorter(filtrar);

                try {

                    filtrar.setRowFilter(RowFilter.regexFilter(vista.getTxtBuscar().getText())); //Se pasa como parametro el campo de donde se va a obtener la informacion y el (3) es la columna con la cual va a buscar las coincidencias
                } catch (Exception ex) {
                    System.out.println("Error: " + ex);
                }
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //Todo sobre estudiante
    public void abrirjDialogEstudiante() {
        vista.getjDlgBuscarEstudiante().setVisible(true);
        vista.getjDlgBuscarEstudiante().setSize(708, 416);
        vista.getjDlgBuscarEstudiante().setLocationRelativeTo(vista.getBtnBuscarEstudiante());
        vista.getjDlgBuscarEstudiante().setTitle("Seleccione un estudiante");

        cargarRegistroDeEstudiantes();
        buscarEstudiante();
    }

    public void cargarRegistroDeEstudiantes() {

        ModeloEstudiante modeloEstudiante = new ModeloEstudiante();

        DefaultTableModel tabla = (DefaultTableModel) vista.getTblDlgEstudiantes().getModel();
        tabla.setNumRows(0);

        List<Estudiante> docentes = modeloEstudiante.listaEstudiantesTabla();
        docentes.stream().forEach(p -> {
            String[] datos = {p.getPer_cedula(), p.getPer_primernom(), p.getPer_apellidopater(), p.getPer_telefono(), p.getPer_email()};
            tabla.addRow(datos);
        });
    }

    public void cargarDatosEstudianteEnTXT() {
        int fila = vista.getTblDlgEstudiantes().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            ModeloEstudiante modeloEstudiante = new ModeloEstudiante();
            List<Estudiante> listap = modeloEstudiante.listaEstudiantesTabla();

            listap.stream().forEach(persona -> {

                if (persona.getPer_cedula().equals(vista.getTblDlgEstudiantes().getValueAt(fila, 0).toString())) {

                    System.out.println("Codigo est: " + persona.getEst_codigo());

                    vista.getTxtCodigoEstudiantematri().setText(String.valueOf(persona.getEst_codigo()));
                    vista.getTxtCedulaEstudiante().setText(persona.getPer_cedula());
                    vista.getTxtNombreYApellidoEstudiante().setText(persona.getPer_primernom() + " " + persona.getPer_apellidopater());
                    vista.getTxtCorreoEstudiante().setText(persona.getPer_email());

                }
            });

            vista.getjDlgBuscarEstudiante().dispose();
        }
    }

    public void buscarEstudiante() {

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

                ModeloEstudiante modeloEstudiante = new ModeloEstudiante();

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblDlgEstudiantes().getModel();
                tabla.setNumRows(0);

                List<Estudiante> estudiantes = modeloEstudiante.buscarEstudiante(vista.getTxtBuscarEst().getText());
                estudiantes.stream().forEach(p -> {
                    String[] datos = {p.getPer_cedula(), p.getPer_primernom(), p.getPer_apellidopater(), p.getPer_telefono(), p.getPer_email()};
                    tabla.addRow(datos);
                });
            }
        };

        vista.getTxtBuscarEst().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //Todo sobre curso
    public void abrirjDialogCurso() {
        vista.getjDlgBuscarCurso().setVisible(true);
        vista.getjDlgBuscarCurso().setSize(585, 420);
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
            //vista.getTxtCodigoCurso().setVisible(false);
            ModeloCurso modeloCurso = new ModeloCurso();
            List<Curso> listap = modeloCurso.listaCursoTabla();

            listap.stream().forEach(c -> {

                if (c.getCur_codigo() == Integer.parseInt(vista.getTblDlgjCurso().getValueAt(fila, 0).toString())) {

                    vista.getTxtCodigoCurso().setText(String.valueOf(c.getCur_codigo()));
                    vista.getTxtNombreCurso().setText(c.getCur_nombre());

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

    //Todo sobre Administrador
    public void cargarDatosAdministradorEnTxt() {
        ModeloAdministrador modeloAdministrador = new ModeloAdministrador();
        List<Administrador> listAdmin = modeloAdministrador.listaAdministradoresTabla();

        listAdmin.stream().forEach(a -> {

            if ((usuario).equals(a.getAdm_usuario())) {

                vista.getTxtCodigoAdministrador().setText(String.valueOf(a.getAdm_codigo()));
                vista.getTxtCedulaAdministrador().setText(a.getPer_cedula());
                vista.getTxtNombreYApellidoAdministrador().setText(a.getPer_primernom() + " " + a.getPer_apellidopater());
            }
        });
    }

    public boolean validarDatos() {

        boolean validar = true;

        if (vista.getTxtCedulaEstudiante().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un estudiante");
            validar = false;
        }

        if (vista.getTxtNombreCurso().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un curso");
            validar = false;
        }

        return validar;

    }

    public void bloquearYVisibilidadCrear() {
        vista.getTxtCodigoAdministrador().setVisible(false);
        vista.getTxtCedulaAdministrador().setEditable(false);
        vista.getTxtNombreYApellidoAdministrador().setEditable(false);
        vista.getTxtCodigoEstudiantematri().setVisible(false);
        vista.getTxtCedulaEstudiante().setEditable(false);
        vista.getTxtNombreYApellidoEstudiante().setEditable(false);
        vista.getTxtCorreoEstudiante().setEditable(false);
        vista.getTxtCodigoCurso().setVisible(false);
        vista.getTxtNombreCurso().setEditable(false);
        vista.getFechaDeMatricula().setEnabled(false);
    }

    public void bloquearYVisibilidadModificar() {
        vista.getTxtCodigoAdministrador().setVisible(false);
        vista.getTxtCedulaAdministrador().setEditable(false);
        vista.getTxtNombreYApellidoAdministrador().setEditable(false);
        vista.getTxtCodigoEstudiantematri().setVisible(false);
        vista.getTxtCedulaEstudiante().setEditable(false);
        vista.getTxtNombreYApellidoEstudiante().setEditable(false);
        vista.getTxtCorreoEstudiante().setEditable(false);
        vista.getTxtCodigoCurso().setVisible(false);
        vista.getTxtNombreCurso().setEditable(false);
        vista.getFechaDeMatricula().setEnabled(false);
        vista.getBtnBuscarEstudiante().setVisible(false);
    }

    public void limpiar() {

        vista.getTxtCodigoEstudiantematri().setText("");
        vista.getTxtCedulaEstudiante().setText("");
        vista.getTxtNombreYApellidoEstudiante().setText("");
        vista.getTxtCorreoEstudiante().setText("");
        vista.getTxtCodigoCurso().setText("");
        vista.getTxtNombreCurso().setText("");
    }

   

    public void botonCancelar() {
        vista.getjDlgMatricula().setVisible(false);
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
 /*else {
            matriculado = false;
            if (validarDatos()) {

                List<Matricula> matriculas = modelo.listaMatriculaTabla();

                matriculas.stream().forEach(m -> {

                    if (m.getMat_codEst() == Integer.parseInt(vista.getTxtCodigoEstudiantematri().getText()) && m.getMat_codCur() == Integer.parseInt(vista.getTxtCodigoCurso().getText()) && m.getMat_estado().equals("A")) {
                        matriculado = true;
                    }
                });

                if (matriculado) {
                    JOptionPane.showMessageDialog(null, "El estudiante ya se encuentra matriculado en este curso");
                } else {
                    modelo.setMat_codigo(codigoMatricula);
                    modelo.setMat_codEst(Integer.parseInt(vista.getTxtCodigoEstudiantematri().getText()));
                    modelo.setMat_codCur(Integer.parseInt(vista.getTxtCodigoCurso().getText()));

                    Date fecha = vista.getFechaDeMatricula().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    modelo.setMat_fechamatri(fechaSQL);

                    if (modelo.modificarMatricula() == null) {
                        JOptionPane.showMessageDialog(null, "La matricula se modifico exitosamente");
                        vista.getjDlgMatricula().setVisible(false);
                        cargarTablaDeMatriculas();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo modificar la matricula");
                    }
                }
            }
        }*/
}
