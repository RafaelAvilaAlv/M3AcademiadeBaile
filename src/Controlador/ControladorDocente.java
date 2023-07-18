package Controlador;

import Conexion.ConexionPG;
import Modelo.Docente;
import Modelo.Empleado;
import Modelo.ModeloDocente;
import Modelo.ModeloEmpleado;
import Modelo.ModeloPersona;
import Modelo.Persona;
import Vista.VistaDocente;
import Vista.VistaPrincipal;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

public class ControladorDocente {

    ModeloDocente modelo;
    VistaDocente vista;

    static boolean asignar; //Esta variable es de tipo static para que funcione dentro de la expresion lambda. Esta variable sera true o false dependiendo si la persona es o no docente

    VistaPrincipal p = new VistaPrincipal();

    public ControladorDocente(ModeloDocente modelo, VistaDocente vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);

        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        vista.getjDlgBuscarPersonas().setResizable(false);
        vista.getjDlgBuscarPersonas().setLocationRelativeTo(null);
        cargarTablaDeDocentes();
    }

    public void iniciarControl() {
        vista.getBtnAsignar().addActionListener(l -> abrirjDlgDocente());
        vista.getBtnGuardar().addActionListener(l -> crearEditarDocente());
        vista.getBtnBuscarPersona().addActionListener(l -> abrirjDialogPersonas());
        vista.getBtnCargarPer().addActionListener(l -> cargarDatosPersonaEnTXT());
        vista.getBtnActualizar().addActionListener(l -> cargarTablaDeDocentes());
        vista.getBtnModificar().addActionListener(l -> cargarDatosDocentesEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarDocente());
        vista.getBtnCancelar().addActionListener(l -> botonCancelar());
        vista.getBtnImprimir().addActionListener(l -> imprimir());
        buscarDocente();
    }

    public void cargarTablaDeDocentes() {
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblDocente().getModel();
        tabla.setNumRows(0);

        List<Docente> docentes = modelo.listaDocentesTabla();
        docentes.stream().forEach(p -> {
            String[] datos = {p.getPer_cedula(), p.getPer_primernom() + " " + p.getPer_apellidopater(), String.valueOf(p.getEmp_codigo()), p.getEmp_salario().toString(),String.valueOf(p.getDoc_codigo()), p.getDoc_especialidad()};
            tabla.addRow(datos);
        });
    }

    int codigoDocente; //Esta variable almacenara el codigo del docente traido de la BD. Sirve para modificar los datos del docente
    int codigoEmpleado;

    public void cargarDatosDocentesEnTXT() {
        int fila = vista.getTblDocente().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            //Abrir jDialog de campos de Docente
            vista.getjDlgDocente().setVisible(true);
            vista.getjDlgDocente().setSize(889, 685);
            vista.getjDlgDocente().setLocationRelativeTo(null);
            vista.getjDlgDocente().setName("Modificar docente");
            vista.getjDlgDocente().setTitle("Modificar  docente");

            bloquearCamposModificar();

            //ModeloPersona modeloPersona = new ModeloPersona();
            List<Docente> listap = modelo.listaDocentesTabla();

            listap.stream().forEach(persona -> {

                if (persona.getPer_cedula().equals(vista.getTblDocente().getValueAt(fila, 0).toString())) {
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

                    codigoDocente = persona.getDoc_codigo();//Asigno el codigo del docente a la variable
                    vista.getTxtEspecialidad().setText(persona.getDoc_especialidad());

                    codigoEmpleado = persona.getDoc_codemp();
                    vista.getSpinnerSueldo().setValue(persona.getEmp_salario());
                    vista.getFechaContratacion().setDate(persona.getEmp_fechacontratacion());
                }
            });
        }
    }

    public void abrirjDlgDocente() {

        vista.getjDlgDocente().setVisible(true);
        vista.getjDlgDocente().setSize(889, 685);
        vista.getjDlgDocente().setLocationRelativeTo(null);
        vista.getjDlgDocente().setName("Crear nuevo docente");
        vista.getjDlgDocente().setTitle("Crear nuevo docente");

        desbloquearCampos();
        limpiarCampos();
    }

    public void crearEditarDocente() {
        if ("Crear nuevo docente".equals(vista.getjDlgDocente().getName())) {

            if (validarDatosCrear()) {

                asignar = false; //Siempre que ingrese a este metodo la variable sera false

                ModeloPersona persona = new ModeloPersona();
                ModeloEmpleado empleado = new ModeloEmpleado();
                ModeloDocente docente = new ModeloDocente();

                //List<Persona> personas = persona.listaPersonasTabla();
                List<Empleado> empleados = empleado.listaEmpleadoTabla();
                List<Docente> docentes = docente.listaDocentesTabla();

                empleados.stream().forEach(e -> {
                    if (persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText()) == e.getEmp_codper()) {

                        docentes.stream().forEach(d -> {
                            if (e.getEmp_codigo() == d.getDoc_codemp()) {
                                //Si la persona ya es docente 'asignar' va a ser true
                                asignar = true;
                            }
                        });
                    }
                });

                //Si 'asignar' es true NO va a guardar los datos ni en empleado ni en docente. Si 'asiganar' es false se guardaran los datos en las tablas de empleado y de docente
                if (asignar) {
                    JOptionPane.showMessageDialog(null, "Esta persona ya ha sido asignada como docente");
                } else {
                    //Setear Datos de empleado
                    empleado.setEmp_codper(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText()));

                    Date fecha = vista.getFechaContratacion().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    empleado.setEmp_fechacontratacion(fechaSQL);
                    empleado.setEmp_salario(Double.parseDouble(vista.getSpinnerSueldo().getValue().toString()));

                    if (empleado.crearEmpleado() == null) {

                        //Setear Datos de Docente
                        docente.setDoc_codemp(empleado.traerCodigoDeEmpleadoCrear());
                        docente.setDoc_especialidad(vista.getTxtEspecialidad().getText());

                        if (docente.crearDocente() == null) {
                            JOptionPane.showMessageDialog(null, "Docente asignado correctamente");
                            vista.getjDlgDocente().setVisible(false);

                        } else {
                            JOptionPane.showConfirmDialog(null, "No se pudo asignar el rol");
                        }
                    } else {
                        System.out.println("Error al insetar empleado");
                    }
                }
                System.out.println("Asiginar: " + asignar);
            }
        } else {
            //EDITAR
            if (validarDatosModificar()) {

                ModeloEmpleado empleado = new ModeloEmpleado();
                ModeloDocente docente = new ModeloDocente();

                //Setear los datos de empleado
                empleado.setEmp_codigo(codigoEmpleado);
                Date fechaM = vista.getFechaContratacion().getDate();
                java.sql.Date fechaSQLM = new java.sql.Date(fechaM.getTime());
                empleado.setEmp_fechacontratacion(fechaSQLM);
                empleado.setEmp_salario(Double.parseDouble(vista.getSpinnerSueldo().getValue().toString()));

                //Setear Datos de Docente
                docente.setDoc_codigo(codigoDocente);
                docente.setDoc_especialidad(vista.getTxtEspecialidad().getText());

                if (empleado.modificarEmpleado() == null) {
                    if (docente.modificarDocente() == null) {
                        JOptionPane.showMessageDialog(null, "La informacion se modifico exitosamente");
                        vista.getjDlgDocente().setVisible(false);
                    } else {
                        System.out.println("Error al modificar la informacion");
                    }
                } else {
                    System.out.println("Error al modificar empleado");
                }
            }
        }
        cargarTablaDeDocentes();
    }

    public void eliminarDocente() {
        ModeloDocente docente = new ModeloDocente();

        int fila = vista.getTblDocente().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigoDocenteEliminar;
                codigoDocenteEliminar = Integer.parseInt(vista.getTblDocente().getValueAt(fila, 4).toString());

                if (docente.eliminarDocente(codigoDocenteEliminar) == null) {
                    JOptionPane.showMessageDialog(null, "El docente fue eliminado exitosamente");
                    cargarTablaDeDocentes();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "Error: El docente no se pudo eliminar");
                }
            }
        }
    }

    public void buscarDocente() {

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

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblDocente().getModel();
                tabla.setNumRows(0);

                List<Docente> docentes = modelo.buscarDocente(vista.getTxtBuscar().getText());
                docentes.stream().forEach(p -> {
                    String[] datos = {p.getPer_cedula(), p.getPer_primernom() + " " + p.getPer_apellidopater(), String.valueOf(p.getEmp_codigo()), p.getEmp_salario().toString(),String.valueOf(p.getDoc_codigo()), p.getDoc_especialidad()};
                    tabla.addRow(datos);
                });
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //Todo sobre el registro de personas en el jDialog
    public void abrirjDialogPersonas() {
        vista.getjDlgBuscarPersonas().setVisible(true);
        vista.getjDlgBuscarPersonas().setSize(605, 409);
        vista.getjDlgBuscarPersonas().setLocationRelativeTo(vista.getBtnBuscarPersona());
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

    public boolean validarDatosCrear() {
        Validaciones mivalidacion = new Validaciones();

        boolean validar = true;

        if (vista.getTxtCedula().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione una persona");
            validar = false;
        }

        if (vista.getFechaContratacion().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese la fecha de contratacion");
            validar = false;
        } else {

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");//Doy formato a la fecha
            String fechaContratacionT = formato.format(vista.getFechaContratacion().getDate()); //Paso de la fecha de contratacion de tipo de Date a String con el formato especificado

            Date fechaConD = null;
            try {
                fechaConD = formato.parse(fechaContratacionT); //Paso la fecha de contratacion de String a Date

            } catch (ParseException ex) {
                Logger.getLogger(ControladorDocente.class.getName()).log(Level.SEVERE, null, ex);
            }

            Date fechaNueva = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()); //Paso la fecha actual de tipo LocalDate a Date
            if (fechaConD.after(fechaNueva)) {
                JOptionPane.showMessageDialog(null, "La fecha de contratacion no puede superar a la fecha actual");
                return false;
            }

        }

        if (vista.getTxtEspecialidad().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la especialidad");
            validar = false;
        } else {
            if (!mivalidacion.validarTextoSinEspacio(vista.getTxtEspecialidad().getText())) {
                JOptionPane.showMessageDialog(null, "Especialidad incorrecta");
                validar = false;
            }
        }

        return validar;
    }

    public boolean validarDatosModificar() {
        Validaciones mivalidacion = new Validaciones();

        boolean validar = true;

        if (vista.getFechaContratacion().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese la fecha de contratacion");
            validar = false;
        } else {

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");//Doy formato a la fecha
            String fechaContratacionT = formato.format(vista.getFechaContratacion().getDate()); //Paso de la fecha de contratacion de tipo de Date a String con el formato especificado

            Date fechaConD = null;
            try {
                fechaConD = formato.parse(fechaContratacionT); //Paso la fecha de contratacion de String a Date

            } catch (ParseException ex) {
                Logger.getLogger(ControladorDocente.class.getName()).log(Level.SEVERE, null, ex);
            }

            Date fechaNueva = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()); //Paso la fecha actual de tipo LocalDate a Date
            if (fechaConD.after(fechaNueva)) {
                JOptionPane.showMessageDialog(null, "La fecha de contratacion no puede superar a la fecha actual");
                return false;
            }

        }

        if (vista.getTxtEspecialidad().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la especialidad");
            validar = false;
        } else {
            if (!mivalidacion.validarTextoSinEspacio(vista.getTxtEspecialidad().getText())) {
                JOptionPane.showMessageDialog(null, "Especialidad incorrecta");
                validar = false;
            }
        }

        return validar;
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
        vista.getTxtEspecialidad().setText("");
        vista.getSpinnerSueldo().setValue(0);
        vista.getFechaContratacion().setDate(null);
    }

    public void bloquearCamposModificar() {
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

    public void desbloquearCampos() {
        vista.getBtnBuscarPersona().setVisible(true);
        vista.getTxtCedula().setEditable(true);
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

    public void botonCancelar() {
        vista.getjDlgDocente().setVisible(false);
    }

        public void imprimir() {

        ConexionPG cpg = new ConexionPG();//Instanciar la conexion con esto abrimos la conexion a la BD
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/DocenteReporte.jasper"));

            //Hacer una vista previa
            //JasperPrint jp = JasperFillManager.fillReport(jr, null, cpg.getCon());//JasperFillManager.fillReport: Carga los datos de la BD.//JasperPrint: Hace la impresion del reporte. Puede ir 'null' si en el jasper no existen parametros caso contrario se envian los parametros necesarios
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("RutaImagen", "src/imagenesJasper/profesor.png"); //En donde esta 'titulo' tienen que ser igual al nombre que esta en el parametro del jasper

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cpg.getCon());//'parametros' es el Map recien creado que contiene los parametros que iran al jasper

            JasperViewer jv = new JasperViewer(jp, false); //Se pasa false para que no se cierre el sistema 
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
