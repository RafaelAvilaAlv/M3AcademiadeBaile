package Controlador;

import Conexion.ConexionPG;
import Modelo.Empleado;
import Modelo.ModeloEmpleado;
import Modelo.ModeloProductor;
import Modelo.ModeloPersona;
import Modelo.Persona;
import Modelo.Productor;
import Vista.VistaPrincipal;
import Vista.VistaProductor;
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

public class ControladorProductor {

    ModeloProductor modelo;
    VistaProductor vista;

    static boolean asignar;

    VistaPrincipal p = new VistaPrincipal();

    public ControladorProductor(ModeloProductor modelo, VistaProductor vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);

        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        vista.getjDlgBuscarPersonas().setResizable(false);
        vista.getjDlgBuscarPersonas().setLocationRelativeTo(null);
        cargarTablaDeProductor();
    }

    public void iniciarControl() {
        vista.getBtnAsignar().addActionListener(l -> abrirjDlgProductor());
        vista.getBtnGuardar().addActionListener(l -> crearEditarProductor());
        vista.getBtnBuscarPersona().addActionListener(l -> abrirjDialogPersonas());
        vista.getBtnCargarPer().addActionListener(l -> cargarDatosPersonaEnTXT());
        vista.getBtnActualizar().addActionListener(l -> cargarTablaDeProductor());
        vista.getBtnModificar().addActionListener(l -> cargarDatosProductoresEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarProductor());
        vista.getBtnCancelar().addActionListener(l -> cancelar());
        vista.getBtnImprimir().addActionListener(l -> imprimir());
        buscarProductor();
    }

    public void cargarTablaDeProductor() {
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblProductor().getModel();
        tabla.setNumRows(0);

        List<Productor> productores = modelo.listaProductorTabla();
        productores.stream().forEach(p -> {
            Object[] datos = {p.getPer_cedula(), p.getPer_primernom() + " " + p.getPer_apellidopater(), p.getEmp_codigo(), p.getEmp_salario().toString(), p.getPro_codigo(), p.getPro_expe()};
            tabla.addRow(datos);
        });
    }

    int codigoProductor; //Esta variable almacenara el codigo del docente traido de la BD. Sirve para modificar los datos del docente
    int codigoEmpleado; //Esta variable almacenara el codigo del empleado traido de la BD. Sirve para modificar los datos del empleado

    public void cargarDatosProductoresEnTXT() {
        int fila = vista.getTblProductor().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            //Abrir jDialog de campos de Docente
            vista.getjDlgProductor().setVisible(true);
            vista.getjDlgProductor().setSize(817, 667);
            vista.getjDlgProductor().setLocationRelativeTo(null);
            vista.getjDlgProductor().setName("Modificar productor");
            vista.getjDlgProductor().setTitle("Modificar productor");
            bloquearCampos();

            //ModeloPersona modeloPersona = new ModeloPersona();
            List<Productor> listap = modelo.listaProductorTabla();

            listap.stream().forEach(persona -> {

                if (persona.getPer_cedula().equals(vista.getTblProductor().getValueAt(fila, 0).toString())) {
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

                    codigoProductor = persona.getPro_codigo();//Asigno el codigo del productor a la variable
                    vista.getSpinnerExperiencia().setValue(persona.getPro_expe());

                    codigoEmpleado = persona.getPro_codemp();
                    vista.getSpinnerSueldo().setValue(persona.getEmp_salario());
                    vista.getFechaContratacion().setDate(persona.getEmp_fechacontratacion());
                }
            });
        }
    }

    public void abrirjDlgProductor() {

        vista.getjDlgProductor().setVisible(true);
        vista.getjDlgProductor().setSize(817, 667);
        vista.getjDlgProductor().setLocationRelativeTo(null);
        vista.getjDlgProductor().setName("Crear nuevo productor");
        vista.getjDlgProductor().setTitle("Crear nuevo productor");
        bloquearCampos();
        limpiarCampos();
    }

    public void crearEditarProductor() {

        if ("Crear nuevo productor".equals(vista.getjDlgProductor().getName())) {

            if (validarDatosCrear()) {

                asignar = false;

                ModeloPersona persona = new ModeloPersona();
                ModeloEmpleado empleado = new ModeloEmpleado();
                ModeloProductor productor = new ModeloProductor();

                List<Empleado> empleados = empleado.listaEmpleadoTabla();
                List<Productor> productores = productor.listaProductorTabla();

                empleados.stream().forEach(e -> {
                    if (persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText()) == e.getEmp_codper()) {

                        productores.stream().forEach(p -> {
                            if (e.getEmp_codigo() == p.getPro_codemp()) {
                                //Si la persona ya es docente 'asignar' va a ser true
                                asignar = true;
                            }
                        });
                    }
                });

                if (asignar) {
                    JOptionPane.showMessageDialog(null, "Esta persona ya ha sido asignada como productor");
                } else {

                    //Setear Datos de empleado
                    empleado.setEmp_codper(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText()));

                    Date fecha = vista.getFechaContratacion().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    empleado.setEmp_fechacontratacion(fechaSQL);
                    empleado.setEmp_salario(Double.parseDouble(vista.getSpinnerSueldo().getValue().toString()));

                    if (empleado.crearEmpleado() == null) {

                        //Setear Datos de Productor
                        productor.setPro_codemp(empleado.traerCodigoDeEmpleadoCrear());
                        productor.setPro_expe(Integer.parseInt(vista.getSpinnerExperiencia().getValue().toString()));

                        if (productor.crearProductor() == null) {
                            JOptionPane.showMessageDialog(null, "Productor asignado correctamente");
                            vista.getjDlgProductor().setVisible(false);

                        } else {
                            JOptionPane.showConfirmDialog(null, "No se pudo asignar el rol");
                        }
                    } else {
                        System.out.println("Error al insertar empleado");
                    }
                }
                System.out.println("Asiginar: " + asignar);
            }
        } else {
            //EDITAR
            if (validarDatosModificar()) {

                ModeloEmpleado empleado = new ModeloEmpleado();
                ModeloProductor productor = new ModeloProductor();

                //Setear los datos de empleado
                empleado.setEmp_codigo(codigoEmpleado);
                Date fechaM = vista.getFechaContratacion().getDate();
                java.sql.Date fechaSQLM = new java.sql.Date(fechaM.getTime());
                empleado.setEmp_fechacontratacion(fechaSQLM);
                empleado.setEmp_salario(Double.parseDouble(vista.getSpinnerSueldo().getValue().toString()));

                //Setear Datos del productor
                productor.setPro_codigo(codigoProductor);
                productor.setPro_expe(Integer.parseInt(vista.getSpinnerExperiencia().getValue().toString()));

                if (empleado.modificarEmpleado() == null) {
                    if (productor.modificarProductor() == null) {
                        JOptionPane.showMessageDialog(null, "La informacion se modifico exitosamente");
                        vista.getjDlgProductor().setVisible(false);
                    } else {
                        System.out.println("Error al modificar la informacion");
                    }
                } else {
                    System.out.println("Error al modificar empleado");
                }
            }
        }

        cargarTablaDeProductor();
    }

    public void eliminarProductor() {
        ModeloProductor productor = new ModeloProductor();

        int fila = vista.getTblProductor().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigoProductorEliminar;
                codigoProductorEliminar = Integer.parseInt(vista.getTblProductor().getValueAt(fila, 4).toString());

                if (productor.eliminarProductor(codigoProductorEliminar) == null) {
                    JOptionPane.showMessageDialog(null, "El productor fue eliminado exitosamente");
                    cargarTablaDeProductor();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "Error: El productor no se pudo eliminar");
                }
            }
        }
    }

    public void buscarProductor() {

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

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblProductor().getModel();
                tabla.setNumRows(0);

                List<Productor> productores = modelo.buscarProductor(vista.getTxtBuscar().getText());
                productores.stream().forEach(p -> {
                    Object[] datos = {p.getPer_cedula(), p.getPer_primernom() + " " + p.getPer_apellidopater(), p.getEmp_codigo(), p.getEmp_salario().toString(), p.getPro_codigo(), p.getPro_expe()};
                    tabla.addRow(datos);
                });
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //Todo sobre el registro de personas en el jDialog
    public void abrirjDialogPersonas() {
        vista.getjDlgBuscarPersonas().setVisible(true);
        vista.getjDlgBuscarPersonas().setSize(722, 433);
        vista.getjDlgBuscarPersonas().setLocationRelativeTo(null);
        vista.getjDlgBuscarPersonas().setTitle("Seleccione una persona");
        cargarRegistroDePersonas();
        buscarPersona();
    }

    public void cargarRegistroDePersonas() {

        ModeloPersona modeloPersona = new ModeloPersona();
        vista.getTblCargarPersonas().setRowHeight(25);
        DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblCargarPersonas().getModel();
        estructuraTabla.setRowCount(0);

        List<Persona> listap = modeloPersona.listaPersonasTabla();

        Holder<Integer> i = new Holder<>(0);

        listap.stream().forEach(persona -> {

            estructuraTabla.addRow(new Object[8]);

            vista.getTblCargarPersonas().setValueAt(persona.getPer_cedula(), i.value, 0);
            vista.getTblCargarPersonas().setValueAt(persona.getPer_primernom(), i.value, 1);
            vista.getTblCargarPersonas().setValueAt(persona.getPer_apellidopater(), i.value, 2);
            vista.getTblCargarPersonas().setValueAt(persona.getPer_telefono(), i.value, 3);
            vista.getTblCargarPersonas().setValueAt(persona.getPer_email(), i.value, 4);

            i.value++;
        });
    }

    public void cargarDatosPersonaEnTXT() {
        int fila = vista.getTblCargarPersonas().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            ModeloPersona modeloPersona = new ModeloPersona();
            List<Persona> listap = modeloPersona.listaPersonasTabla();

            listap.stream().forEach(persona -> {

                if (persona.getPer_cedula().equals(vista.getTblCargarPersonas().getValueAt(fila, 0).toString())) {

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
                vista.getTblCargarPersonas().setRowHeight(25);
                DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblCargarPersonas().getModel();
                estructuraTabla.setRowCount(0);

                List<Persona> listap = modeloPersona.buscarPersona(vista.getTxtBuscarPers().getText());

                Holder<Integer> i = new Holder<>(0);

                listap.stream().forEach(persona -> {

                    estructuraTabla.addRow(new Object[8]);

                    vista.getTblCargarPersonas().setValueAt(persona.getPer_cedula(), i.value, 0);
                    vista.getTblCargarPersonas().setValueAt(persona.getPer_primernom(), i.value, 1);
                    vista.getTblCargarPersonas().setValueAt(persona.getPer_apellidopater(), i.value, 2);
                    vista.getTblCargarPersonas().setValueAt(persona.getPer_telefono(), i.value, 3);
                    vista.getTblCargarPersonas().setValueAt(persona.getPer_email(), i.value, 4);

                    i.value++;
                });
            }
        };

        vista.getTxtBuscarPers().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    public boolean validarDatosCrear() {

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
        return validar;
    }

    public boolean validarDatosModificar() {

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
        vista.getSpinnerExperiencia().setValue(0);
        vista.getSpinnerSueldo().setValue(0);
        vista.getFechaContratacion().setDate(null);
    }

    public void bloquearCampos() {
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
    }

    public void cancelar() {
        vista.getjDlgProductor().setVisible(false);
    }

    public void imprimir() {

        ConexionPG cpg = new ConexionPG();//Instanciar la conexion con esto abrimos la conexion a la BD
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/ProductorReporte.jasper"));

            //Hacer una vista previa
            //JasperPrint jp = JasperFillManager.fillReport(jr, null, cpg.getCon());//JasperFillManager.fillReport: Carga los datos de la BD.//JasperPrint: Hace la impresion del reporte. Puede ir 'null' si en el jasper no existen parametros caso contrario se envian los parametros necesarios
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("RutaImagen", "src/imagenesJasper/productor.png"); //En donde esta 'titulo' tienen que ser igual al nombre que esta en el parametro del jasper

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cpg.getCon());//'parametros' es el Map recien creado que contiene los parametros que iran al jasper

            JasperViewer jv = new JasperViewer(jp, false); //Se pasa false para que no se cierre el sistema 
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
