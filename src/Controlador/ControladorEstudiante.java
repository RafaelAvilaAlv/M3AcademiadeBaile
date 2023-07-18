package Controlador;

import Conexion.ConexionPG;
import Modelo.Estudiante;
import Modelo.ModeloEstudiante;
import Modelo.ModeloPersona;
import Modelo.Persona;
import Vista.VistaEstudiante;
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

public class ControladorEstudiante {

    ModeloEstudiante modelo;
    VistaEstudiante vista;

    VistaPrincipal p = new VistaPrincipal();

    public ControladorEstudiante(ModeloEstudiante modelo, VistaEstudiante vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);

        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        vista.getjDlgBuscarPersonas().setResizable(false);
        vista.getjDlgBuscarPersonas().setLocationRelativeTo(null);
        vista.getBtnImprimir().addActionListener(l -> imprimir());
        cargarTablaDeEstudiantes();
        buscarEstudiante();
    }

    public void iniciarControl() {
        vista.getBtnAsignar().addActionListener(l -> abrirjDlgEstudiante());
        vista.getBtnGuardar().addActionListener(l -> crearEditarEstudiante());
        vista.getBtnBuscarPersona().addActionListener(l -> abrirjDialogPersonas());
        vista.getBtnCargarPer().addActionListener(l -> cargarDatosPersonaEnTXT());
        vista.getBtnActualizar().addActionListener(l -> cargarTablaDeEstudiantes());
        vista.getBtnModificar().addActionListener(l -> cargarDatosEstudianteEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarEstudiante());
        vista.getBtnCancelar().addActionListener(l -> cancelar());
        //vista.getBtnImprimir().addActionListener(l -> imprimir());
        buscarEstudiante();
        
    }

    public void cargarTablaDeEstudiantes() {
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblEstudiante().getModel();
        tabla.setNumRows(0);

        List<Estudiante> docentes = modelo.listaEstudiantesTabla();
        docentes.stream().forEach(p -> {
            String[] datos = {String.valueOf(p.getEst_codigo()), p.getPer_cedula(), p.getPer_primernom() + " " + p.getPer_apellidopater(), p.getPer_telefono(), p.getPer_genero()};
            tabla.addRow(datos);
        });
    }

    int codigoEstudiante; //Esta variable almacenara el codigo del estudiante traido de la BD. Sirve para modificar los datos del estudiante

    public void cargarDatosEstudianteEnTXT() {
        int fila = vista.getTblEstudiante().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            //Abrir jDialog de campos de Docente
            vista.getjDlgEstudiante().setVisible(true);
            vista.getjDlgEstudiante().setSize(838, 602);
            vista.getjDlgEstudiante().setLocationRelativeTo(null);
            vista.getjDlgEstudiante().setName("Modificar estudiante");
            vista.getjDlgEstudiante().setTitle("Modificar  estudiante");
            

            //ModeloPersona modeloPersona = new ModeloPersona();
            List<Estudiante> listap = modelo.listaEstudiantesTabla();

            listap.stream().forEach(persona -> {

                if (persona.getEst_codigo() == Integer.parseInt(vista.getTblEstudiante().getValueAt(fila, 0).toString())) {

                    vista.getTxtCedula().setText(persona.getPer_cedula());
                    vista.getTxtPrimerNombre().setText(persona.getPer_primernom());
                    vista.getTxtSegundoNombre().setText(persona.getPer_segundonom());
                    vista.getTxtPrimerApellido().setText(persona.getPer_apellidopater());
                    vista.getTxtSegundoApellido().setText(persona.getPer_apellidomater());

                    if (persona.getPer_genero().equals("M")) {
                        vista.getMasculino().setSelected(true);
                    } else {
                        if (persona.getPer_genero().equals("F")) {
                            vista.getFemenino().setSelected(true);
                        } else {
                            vista.getNoBinario().setSelected(true);
                        }
                    }

                    vista.getTxtTelefono().setText(persona.getPer_telefono());
                    vista.getFechaNacimiento().setDate(persona.getPer_fechanac());
                    vista.getTxtEmail().setText(persona.getPer_email());
                    vista.getTxtDireccion().setText(persona.getPer_direccion());
                    codigoEstudiante = persona.getEst_codigo();//Asigno el codigo del estudiante a la variable
                }
            });
        }
    }

    public void abrirjDlgEstudiante() {

        vista.getjDlgEstudiante().setVisible(true);
        vista.getjDlgEstudiante().setSize(840, 610);
        vista.getjDlgEstudiante().setLocationRelativeTo(null);
        vista.getjDlgEstudiante().setName("Crear nuevo estudiante");
        vista.getjDlgEstudiante().setTitle("Crear nuevo estudiante");
        limpiarCampos();
        bloquearCampos1();
    }

    public void crearEditarEstudiante() {

        if ("Crear nuevo estudiante".equals(vista.getjDlgEstudiante().getName())) {

            if (validarDatos()) {
                ModeloPersona persona = new ModeloPersona();

                if (modelo.traerEstadoDelEstudiante(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText())).equals("A")) {

                    JOptionPane.showMessageDialog(null, "Esta persona ya ha sido ha asignada como estudiante");
                } else {
                    if (modelo.traerEstadoDelEstudiante(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText())).equals("I")) {
                        if (modelo.crearEstudiante2(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText())) == null) {
                            JOptionPane.showMessageDialog(null, "Estudiante asignado correctamente");
                        }
                    } else {
                        //Setear Datos de estudiante
                        ModeloEstudiante estudiantes = new ModeloEstudiante();
                        estudiantes.setEst_codper(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText()));

                        if (estudiantes.crearEstudiante() == null) {

                            JOptionPane.showMessageDialog(null, "Estudiante asignado correctamente");

                        } else {
                            JOptionPane.showMessageDialog(null, "Error al asignar el rol de estudiante");
                        }
                    }
                }
            }
        } else {

            if (validarDatos()) {
                ModeloPersona persona = new ModeloPersona();

                if (modelo.traerEstadoDelEstudiante(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText())).equals("A")) {

                    JOptionPane.showMessageDialog(null, "Esta persona ya ha sido ha asignada como estudiante");
                } else {
                    if (modelo.traerEstadoDelEstudiante(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText())).equals("I")) {
                        if (modelo.modificarEstudiante2(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText())) == null) {
                            if (modelo.eliminarEstudiante(codigoEstudiante) == null) {
                                JOptionPane.showMessageDialog(null, "Estudiante modificado correctamente. Cambio estado");
                            }
                        }
                    } else {

                        //Setear Datos de estudiante
                        modelo.setEst_codigo(codigoEstudiante);
                        System.out.println("Codigo del estudiante: " + codigoEstudiante);
                        modelo.setEst_codper(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText()));
                        System.out.println("Codigo de la persona: " + persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText()));

                        if (modelo.modificarEstudiante() == null) {

                            JOptionPane.showMessageDialog(null, "Estudiante modificado correctamente. Modificado");

                        } else {
                            JOptionPane.showMessageDialog(null, "Error al modificar estudiante");
                        }
                    }
                }
            }
        }

        cargarTablaDeEstudiantes();
    }

    public void eliminarEstudiante() {
        ModeloEstudiante estudiante = new ModeloEstudiante();

        int fila = vista.getTblEstudiante().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigoEstudianteEliminar;
                codigoEstudianteEliminar = Integer.parseInt(vista.getTblEstudiante().getValueAt(fila, 0).toString());

                if (estudiante.eliminarEstudiante(codigoEstudianteEliminar) == null) {
                    JOptionPane.showMessageDialog(null, "El estudiante fue eliminado exitosamente");
                    cargarTablaDeEstudiantes();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "Error: El estudiante no se pudo eliminar");
                }
            }
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

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblEstudiante().getModel();
                tabla.setNumRows(0);

                List<Estudiante> estudiantes = modelo.buscarEstudiante(vista.getTxtBuscar().getText());
                estudiantes.stream().forEach(p -> {
                    String[] datos = {p.getPer_cedula(), p.getPer_primernom() + " " + p.getPer_apellidopater(), p.getPer_telefono(), p.getPer_genero()};
                    tabla.addRow(datos);
                });
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

        public void buscarPersona() {

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

                ModeloPersona modeloPersona = new ModeloPersona();
                vista.getTblDlgPersonas().setRowHeight(25);
                DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgPersonas().getModel();
                estructuraTabla.setRowCount(0);

                List<Persona> listap = modeloPersona.buscarPersona(vista.getTxtBuscarPer().getText());

                Holder<Integer> i = new Holder<>(0);

                listap.stream().forEach(persona -> {

                    estructuraTabla.addRow(new Object[8]);

                    vista.getTblDlgPersonas().setValueAt(persona.getPer_cedula(), i.value, 0);
                    vista.getTblDlgPersonas().setValueAt(persona.getPer_primernom(), i.value, 1);
                    vista.getTblDlgPersonas().setValueAt(persona.getPer_apellidopater(), i.value, 2);
                    vista.getTblDlgPersonas().setValueAt(persona.getPer_telefono(), i.value, 3);
                    vista.getTblDlgPersonas().setValueAt(persona.getPer_email(), i.value, 4);

                    i.value++;
                });
            }
        };

        vista.getTxtBuscarPer().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }
    //Todo sobre el registro de personas en el jDialog
    public void abrirjDialogPersonas() {
        vista.getjDlgBuscarPersonas().setVisible(true);
        vista.getjDlgBuscarPersonas().setSize(763, 366);
        vista.getjDlgBuscarPersonas().setLocationRelativeTo(null);
        vista.getjDlgBuscarPersonas().setTitle("Seleccione una persona");
        cargarRegistroDePersonas();
        buscarPersona();
    }

    public void cargarRegistroDePersonas() {

        ModeloPersona modeloPersona = new ModeloPersona();
        vista.getTblDlgPersonas().setRowHeight(25);
        DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgPersonas().getModel();
        estructuraTabla.setRowCount(0);

        List<Persona> listap = modeloPersona.listaPersonasTabla();

        Holder<Integer> i = new Holder<>(0);

        listap.stream().forEach(persona -> {

            estructuraTabla.addRow(new Object[8]);

            vista.getTblDlgPersonas().setValueAt(persona.getPer_cedula(), i.value, 0);
            vista.getTblDlgPersonas().setValueAt(persona.getPer_primernom(), i.value, 1);
            vista.getTblDlgPersonas().setValueAt(persona.getPer_apellidopater(), i.value, 2);
            vista.getTblDlgPersonas().setValueAt(persona.getPer_telefono(), i.value, 3);
            vista.getTblDlgPersonas().setValueAt(persona.getPer_email(), i.value, 4);

            i.value++;
        });
    }

    public void cargarDatosPersonaEnTXT() {
        int fila = vista.getTblDlgPersonas().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            ModeloPersona modeloPersona = new ModeloPersona();
            List<Persona> listap = modeloPersona.listaPersonasTabla();

            listap.stream().forEach(persona -> {

                if (persona.getPer_cedula().equals(vista.getTblDlgPersonas().getValueAt(fila, 0).toString())) {

                    //vista.getTxtCodigoPersona().setText(String.valueOf(persona.getPer_codigo())); //SE PUEDE ELIMINAR
                    vista.getTxtCedula().setText(persona.getPer_cedula());
                    vista.getTxtPrimerNombre().setText(persona.getPer_primernom());
                    vista.getTxtSegundoNombre().setText(persona.getPer_segundonom());
                    vista.getTxtPrimerApellido().setText(persona.getPer_apellidopater());
                    vista.getTxtSegundoApellido().setText(persona.getPer_apellidomater());

                    if (persona.getPer_genero().equals("M")) {
                        vista.getMasculino().setSelected(true);
                    } else {
                        if (persona.getPer_genero().equals("F")) {
                            vista.getFemenino().setSelected(true);
                        } else {
                            vista.getNoBinario().setSelected(true);
                        }
                    }

                    vista.getTxtTelefono().setText(persona.getPer_telefono());
                    vista.getFechaNacimiento().setDate(persona.getPer_fechanac());
                    vista.getTxtEmail().setText(persona.getPer_email());
                    vista.getTxtDireccion().setText(persona.getPer_direccion());

                }
            });

            vista.getjDlgBuscarPersonas().dispose();

        }
    }

    public boolean validarDatos() {

        boolean validar = true;

        if (vista.getTxtCedula().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione una persona");
            validar = false;
        }
        return validar;
    }

    public void bloquearCampos() {
        vista.getBtnBuscarPersona().setVisible(false);
        vista.getTxtCedula().setEditable(false);
        vista.getTxtPrimerNombre().setEditable(false);
        vista.getTxtSegundoNombre().setEditable(false);
        vista.getTxtPrimerApellido().setEditable(false);
        vista.getTxtSegundoApellido().setEditable(false);
        vista.getMasculino().setEnabled(false);
        vista.getFemenino().setEnabled(false);
        vista.getNoBinario().setEnabled(false);
        vista.getTxtTelefono().setEditable(false);
        vista.getFechaNacimiento().setEnabled(false);
        vista.getTxtEmail().setEditable(false);
        vista.getTxtDireccion().setEditable(false);
    }

    public void bloquearCampos1() {
        vista.getTxtCedula().setEditable(false);
        vista.getTxtPrimerNombre().setEditable(false);
        vista.getTxtSegundoNombre().setEditable(false);
        vista.getTxtPrimerApellido().setEditable(false);
        vista.getTxtSegundoApellido().setEditable(false);
        vista.getMasculino().setEnabled(false);
        vista.getFemenino().setEnabled(false);
        vista.getNoBinario().setEnabled(false);
        vista.getTxtTelefono().setEditable(false);
        vista.getFechaNacimiento().setEnabled(false);
        vista.getTxtEmail().setEditable(false);
        vista.getTxtDireccion().setEditable(false);
    }

    public void limpiarCampos() {
        vista.getTxtCedula().setText("");
        vista.getTxtPrimerNombre().setText("");
        vista.getTxtSegundoNombre().setText("");
        vista.getTxtPrimerApellido().setText("");
        vista.getTxtSegundoApellido().setText("");
        vista.getGenero().clearSelection();
        vista.getTxtTelefono().setText("");
        vista.getFechaNacimiento().setDate(null);
        vista.getTxtEmail().setText("");
        vista.getTxtDireccion().setText("");
    }

    public void cancelar() {
        vista.getjDlgEstudiante().setVisible(false);
    }
       public void imprimir() {

        ConexionPG cpg = new ConexionPG();//Instanciar la conexion con esto abrimos la conexion a la BD
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/EstudianteReporte.jasper"));

            //Hacer una vista previa
            //JasperPrint jp = JasperFillManager.fillReport(jr, null, cpg.getCon());//JasperFillManager.fillReport: Carga los datos de la BD.//JasperPrint: Hace la impresion del reporte. Puede ir 'null' si en el jasper no existen parametros caso contrario se envian los parametros necesarios
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("ImagenRuta", "src/imagenesJasper/estudiante.png"); //En donde esta 'titulo' tienen que ser igual al nombre que esta en el parametro del jasper

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cpg.getCon());//'parametros' es el Map recien creado que contiene los parametros que iran al jasper

            JasperViewer jv = new JasperViewer(jp, false); //Se pasa false para que no se cierre el sistema 
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
