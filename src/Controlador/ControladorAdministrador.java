package Controlador;

import Modelo.Administrador;
import Conexion.ConexionPG;
import Modelo.Empleado;
import Modelo.ModeloAdministrador;
import Modelo.ModeloEmpleado;
import Modelo.ModeloPersona;
import Modelo.Persona;
import Vista.VistaAdministrador;
import Vista.VistaPrincipal;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class ControladorAdministrador {

    ModeloAdministrador modelo;
    VistaAdministrador vista;

    static boolean asignar; //Esta variable es de tipo static para que funcione dentro de la expresion lambda. Esta variable sera true o false dependiendo si la persona es o no docente
    static boolean encontrarUsuarioMismaPosicion;// Esta varible sera true si el usuario que se ingresa en la modificacion es igual a otro en la BD
    static boolean encontrarUsuarioDiferentePosicion;

    VistaPrincipal p = new VistaPrincipal();

    public ControladorAdministrador(ModeloAdministrador modelo, VistaAdministrador vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        vista.getjDlgBuscarPersonas().setResizable(false);
        vista.getjDlgBuscarPersonas().setLocationRelativeTo(null);
        cargarTablaDeAdministradores();
    }

    public void iniciarControl() {
        vista.getBtnAsignar().addActionListener(l -> abrirjDlgAdministrador());
        vista.getBtnGuardar().addActionListener(l -> crearEditarAdministrador());
        vista.getBtnBuscarPersona().addActionListener(l -> abrirjDialogPersonas());
        vista.getBtnCargarPer().addActionListener(l -> cargarDatosPersonaEnTXT());
        vista.getBtnActualizar().addActionListener(l -> cargarTablaDeAdministradores());
        vista.getBtnModificar().addActionListener(l -> cargarDatosAdministradoresEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarAdministrador());
        vista.getBtnCancelar().addActionListener(l -> botonEliminar());
        vista.getBtnImprimir().addActionListener(l -> imprimir());
        buscarAdministrador();
    }

    public void cargarTablaDeAdministradores() {
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblAdministrador().getModel();
        tabla.setNumRows(0);

        List<Administrador> administradores = modelo.listaAdministradoresTabla();
        administradores.stream().forEach(p -> {
            String[] datos = {p.getPer_cedula(), p.getPer_primernom() + " " + p.getPer_apellidopater(), String.valueOf(p.getEmp_codigo()), p.getEmp_salario().toString(), String.valueOf(p.getAdm_codigo()), p.getAdm_usuario()};
            tabla.addRow(datos);
        });
    }

    public void imprimir() {

        ConexionPG cpg = new ConexionPG();//Instanciar la conexion con esto abrimos la conexion a la BD
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/AdministradorReporte.jasper"));

            //Hacer una vista previa
            //JasperPrint jp = JasperFillManager.fillReport(jr, null, cpg.getCon());//JasperFillManager.fillReport: Carga los datos de la BD.//JasperPrint: Hace la impresion del reporte. Puede ir 'null' si en el jasper no existen parametros caso contrario se envian los parametros necesarios
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("ImagenRuta", "src/imagenesJasper/administrador.png"); //En donde esta 'titulo' tienen que ser igual al nombre que esta en el parametro del jasper

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cpg.getCon());//'parametros' es el Map recien creado que contiene los parametros que iran al jasper

            JasperViewer jv = new JasperViewer(jp, false); //Se pasa false para que no se cierre el sistema 
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void bloquearClaves() {
        vista.getTxtCedula().setEnabled(false);
        vista.getTxtPrimerApellido().setEnabled(false);
        vista.getTxtDireccion().setEnabled(false);
        vista.getTxtPrimerNombre().setEnabled(false);
        vista.getTxtSegundoApellido().setEnabled(false);
        vista.getTxtEmail().setEnabled(false);
        vista.getTxtSegundoNombre().setEnabled(false);
        vista.getTxtTelefono().setEnabled(false);
        vista.getFechaNacimiento().setEnabled(false);
        vista.getFemenino().setEnabled(false);
        vista.getMasculino().setEnabled(false);
        vista.getNoBinario().setEnabled(false);
    }

    int codigoAdministrador;
    int codigoEmpleado;

    public void cargarDatosAdministradoresEnTXT() {
        int fila = vista.getTblAdministrador().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            //Abrir jDialog de campos de Docente
            vista.getjDlgAdministrador().setVisible(true);
            vista.getjDlgAdministrador().setName("Modificar administrador");
            vista.getjDlgAdministrador().setSize(978, 600);
            vista.getjDlgAdministrador().setLocationRelativeTo(null);
            vista.getjDlgAdministrador().setTitle("Modificar administrador");

            bloquearCampos();

            //ModeloPersona modeloPersona = new ModeloPersona();
            List<Administrador> listap = modelo.listaAdministradoresTabla();

            listap.stream().forEach(persona -> {

                if (persona.getPer_cedula().equals(vista.getTblAdministrador().getValueAt(fila, 0).toString())) {
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

                    codigoAdministrador = persona.getAdm_codigo();//Asigno el codigo del docente a la variable
                    vista.getTxtUsuario().setText(persona.getAdm_usuario());
                    vista.getTxtContrasenia().setText(persona.getAdm_clave());

                    codigoEmpleado = persona.getAdm_codemp();
                    vista.getSpinnerSueldo().setValue(persona.getEmp_salario());
                    vista.getFechaContratacion().setDate(persona.getEmp_fechacontratacion());
                }
            });
        }
    }

    public void abrirjDlgAdministrador() {

        vista.getjDlgAdministrador().setVisible(true);
        vista.getjDlgAdministrador().setName("Crear nuevo administrador");
        vista.getjDlgAdministrador().setSize(978, 600);
        vista.getjDlgAdministrador().setLocationRelativeTo(null);
        vista.getjDlgAdministrador().setTitle("Crear nuevo administrador");
        bloquearClaves();
        vista.getLblOcultar().setVisible(true);
        vista.getLblMostrar().setVisible(false);

        //VER Y OCULTAR LAS CONTRASENIA
        ocultarContrasenia();
        verContrasenia();

        desbloquearCampos();
        limpiarCampos();

    }

    public void crearEditarAdministrador() {
        if ("Crear nuevo administrador".equals(vista.getjDlgAdministrador().getName())) {

            if (validarDatosCrear()) {

                if (modelo.validarUsuariosRepetidos(vista.getTxtUsuario().getText()) == 0) {
                    asignar = false; //Siempre que ingrese a este metodo la variable sera false

                    ModeloPersona persona = new ModeloPersona();
                    ModeloEmpleado empleado = new ModeloEmpleado();
                    ModeloAdministrador administrador = new ModeloAdministrador();

                    //List<Persona> personas = persona.listaPersonasTabla();
                    List<Empleado> empleados = empleado.listaEmpleadoTabla();
                    List<Administrador> administradores = administrador.listaAdministradoresTabla();

                    empleados.stream().forEach(e -> {
                        if (persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText()) == e.getEmp_codper()) {

                            administradores.stream().forEach(a -> {
                                if (e.getEmp_codigo() == a.getAdm_codemp()) {
                                    //Si la persona ya es docente 'asignar' va a ser true
                                    asignar = true;
                                }
                            });
                        }
                    });

                    //Si 'asignar' es true NO va a guardar los datos ni en empleado ni en administrador. Si 'asiganar' es false se guardaran los datos en las tablas de empleado y de administrador
                    if (asignar) {
                        JOptionPane.showMessageDialog(null, "Esta persona ya ha sido asignada como administrador");
                    } else {
                        //Setear Datos de empleado
                        empleado.setEmp_codper(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText()));

                        Date fecha = vista.getFechaContratacion().getDate();
                        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                        empleado.setEmp_fechacontratacion(fechaSQL);
                        empleado.setEmp_salario(Double.parseDouble(vista.getSpinnerSueldo().getValue().toString()));

                        if (empleado.crearEmpleado() == null) {

                            //Setear Datos de administrador
                            administrador.setAdm_codemp(empleado.traerCodigoDeEmpleadoCrear());
                            administrador.setAdm_usuario(vista.getTxtUsuario().getText());
                            administrador.setAdm_clave(vista.getTxtContrasenia().getText());

                            if (administrador.crearAdministrador() == null) {
                                JOptionPane.showMessageDialog(null, "Administrador asignado correctamente");
                                vista.getjDlgAdministrador().setVisible(false);

                            } else {
                                JOptionPane.showConfirmDialog(null, "No se pudo asignar el rol");
                            }
                        } else {
                            System.out.println("Error al insertar empleado");
                        }
                    }
                    System.out.println("Asignar: " + asignar);
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre de usuario ya se encuentra registrado");
                }
            }

        } else {
            //EDITAR
            if (validarDatosModificar()) {

                encontrarUsuarioMismaPosicion = false;
                encontrarUsuarioDiferentePosicion = false;

                ModeloEmpleado empleado = new ModeloEmpleado();
                ModeloAdministrador administrador = new ModeloAdministrador();

                List<Administrador> administradores = administrador.listaAdministradoresTabla();

                administradores.stream().forEach(a -> {

                    if (a.getAdm_codigo() == codigoAdministrador) {

                        if (a.getAdm_usuario().equals(vista.getTxtUsuario().getText())) {
                            encontrarUsuarioMismaPosicion = true;
                        }
                    }

                });

                administradores.stream().forEach(a -> {

                    if (a.getAdm_usuario().equals(vista.getTxtUsuario().getText())) {

                        encontrarUsuarioDiferentePosicion = true;
                    }

                });

                if (encontrarUsuarioMismaPosicion == true || encontrarUsuarioDiferentePosicion == false) {
                    //Setear los datos de empleado
                    empleado.setEmp_codigo(codigoEmpleado);
                    Date fechaM = vista.getFechaContratacion().getDate();
                    java.sql.Date fechaSQLM = new java.sql.Date(fechaM.getTime());
                    empleado.setEmp_fechacontratacion(fechaSQLM);
                    empleado.setEmp_salario(Double.parseDouble(vista.getSpinnerSueldo().getValue().toString()));

                    //Setear Datos del administrador
                    administrador.setAdm_codigo(codigoAdministrador);
                    administrador.setAdm_usuario(vista.getTxtUsuario().getText());
                    administrador.setAdm_clave(vista.getTxtContrasenia().getText());

                    if (empleado.modificarEmpleado() == null) {
                        if (administrador.modificarAdministrador() == null) {
                            JOptionPane.showMessageDialog(null, "La informacion se modifico exitosamente");
                            vista.getjDlgAdministrador().setVisible(false);
                        } else {
                            System.out.println("Error al modificar la informacion");
                        }
                    } else {
                        System.out.println("Error al modificar empleado");
                    }
                }
            }
        }
        cargarTablaDeAdministradores();
    }

    public void eliminarAdministrador() {
        ModeloAdministrador administrador = new ModeloAdministrador();

        int fila = vista.getTblAdministrador().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigoAdministradorEliminar;
                codigoAdministradorEliminar = Integer.parseInt(vista.getTblAdministrador().getValueAt(fila, 4).toString());

                if (administrador.eliminarAdministrador(codigoAdministradorEliminar) == null) {
                    JOptionPane.showMessageDialog(null, "El administrador fue eliminado exitosamente");
                    cargarTablaDeAdministradores();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "Error: El administrador no se pudo eliminar");
                }
            }
        }
    }

    public void buscarAdministrador() {

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

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblAdministrador().getModel();
                tabla.setNumRows(0);

                List<Administrador> administradores = modelo.buscarAdministrador(vista.getTxtBuscar().getText());
                administradores.stream().forEach(p -> {
                    String[] datos = {p.getPer_cedula(), p.getPer_primernom() + " " + p.getPer_apellidopater(), p.getEmp_salario().toString(), p.getAdm_usuario()};
                    tabla.addRow(datos);
                });
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //Todo sobre el registro de personas en el jDialog
    public void abrirjDialogPersonas() {
        vista.getjDlgBuscarPersonas().setVisible(true);
        vista.getjDlgBuscarPersonas().setSize(587, 422);
        vista.getjDlgBuscarPersonas().setTitle("Seleccione una persona");
        vista.getjDlgBuscarPersonas().setLocationRelativeTo(vista.getBtnBuscarPersona());

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
                Logger.getLogger(ControladorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }

            Date fechaNueva = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()); //Paso la fecha actual de tipo LocalDate a Date
            if (fechaConD.after(fechaNueva)) {
                JOptionPane.showMessageDialog(null, "La fecha de contratacion no puede superar a la fecha actual");
                return false;
            }

        }

        if (vista.getTxtUsuario().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un usuario");
            validar = false;
        } else {
            if (!mivalidacion.validarUsuario(vista.getTxtUsuario().getText())) {
                JOptionPane.showMessageDialog(null, "Usuario incorrecto");
                validar = false;
            }
        }

        if (vista.getTxtContrasenia().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese una contraseña");
            validar = false;
        } else {
            if (!mivalidacion.validarContraseña(vista.getTxtContrasenia().getText())) {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
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
                Logger.getLogger(ControladorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }

            Date fechaNueva = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()); //Paso la fecha actual de tipo LocalDate a Date
            if (fechaConD.after(fechaNueva)) {
                JOptionPane.showMessageDialog(null, "La fecha de contratacion no puede superar a la fecha actual");
                return false;
            }

        }

        if (vista.getTxtUsuario().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un usuario");
            validar = false;
        } else {
            if (!mivalidacion.validarUsuario(vista.getTxtUsuario().getText())) {
                JOptionPane.showMessageDialog(null, "Usuario incorrecto");
                validar = false;
            }
        }

        if (vista.getTxtContrasenia().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese una contraseña");
            validar = false;
        } else {
            if (!mivalidacion.validarContraseña(vista.getTxtContrasenia().getText())) {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
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
        vista.getTxtUsuario().setText("");
        vista.getTxtContrasenia().setText("");
        vista.getSpinnerSueldo().setValue(0);
        vista.getFechaContratacion().setDate(null);
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

    public void desbloquearCampos() {
        vista.getBtnBuscarPersona().setVisible(true);
        vista.getTxtCedula().setEditable(true);
    }

    public void verContrasenia() {
        MouseListener evento = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                vista.getTxtContrasenia().setEchoChar('●');
                vista.getLblOcultar().setVisible(true);
                vista.getLblMostrar().setVisible(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        vista.getLblMostrar().addMouseListener(evento);
    }

    public void ocultarContrasenia() {
        MouseListener evento = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {

                vista.getTxtContrasenia().setEchoChar((char) 0);
                vista.getLblMostrar().setVisible(true);
                vista.getLblOcultar().setVisible(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        vista.getLblOcultar().addMouseListener(evento);
    }

    public void botonEliminar() {
        vista.getjDlgAdministrador().setVisible(false);
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
