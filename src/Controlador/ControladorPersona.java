package Controlador;

import Modelo.Administrador;
import Conexion.ConexionPG;
import Modelo.Docente;
import Modelo.Empleado;
import Modelo.ModeloAdministrador;
import Modelo.ModeloDocente;
import Modelo.ModeloEmpleado;
import Modelo.ModeloEstudiante;
import Modelo.ModeloPersona;
import Modelo.ModeloProductor;
import Modelo.Persona;
import Modelo.Productor;
import Vista.VistaPersona;
import Vista.VistaPrincipal;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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

public class ControladorPersona {

    private ModeloPersona modelo;
    private VistaPersona vista;

    static boolean asignar; //Esta variable es de tipo static para que funcione dentro de la expresion lambda. Esta variable sera true o false dependiendo si la persona es o no docente

    VistaPrincipal p = new VistaPrincipal();

    public ControladorPersona(ModeloPersona modelo, VistaPersona vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);

        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        vista.getjDlogRegistro().setResizable(false);
        cargarPersonasTabla();
    }

    public void iniciarControl() {
        vista.getBtnCrear().addActionListener(l -> abrirDlgPersonas());
        vista.getBtnModificarPersona().addActionListener(l -> modificarPersona());
        vista.getBtnGuardarDocente().addActionListener(l -> crearPersonaYDocente());
        vista.getBtnGuardarProductor().addActionListener(l -> crearPersonaYProductor());
        vista.getBtnGuardarAdministrador().addActionListener(l -> crearPersonaYAdministrador());
        vista.getBtnActualizar().addActionListener(l -> cargarPersonasTabla());
        vista.getBtnModificar().addActionListener(l -> cargarDatosPersonaEnTXT());
        vista.getCheckDocente().addActionListener(l -> abrirDlgRegistroDocente());
        vista.getCheckAdministrador().addActionListener(l -> abrirDlgRegistroAdministrador());
        vista.getCheckProductor().addActionListener(l -> abrirDlgRegistroProductor());
        vista.getCheckEstudiante().addActionListener(l -> registroEstudiante());
        vista.getBtnCancelarPersona().addActionListener(l -> botonCancelarPersona());
        vista.getBtnCancelarAdministrador().addActionListener(l -> botonCancelarAdministrador());
        vista.getBtnCancelarDocente().addActionListener(l -> botonCancelarDocente());
        vista.getBtnCancelarProductor().addActionListener(l -> botonCancelarProductor());
        vista.getBtnImprimir().addActionListener(l -> imprimir());
        buscarPersona();
    }

    public void abrirDlgPersonas() {
        vista.getjDlogRegistro().setVisible(true);
        vista.getjDlogRegistro().setSize(886, 680);
        vista.getjDlogRegistro().setLocationRelativeTo(null);
        vista.getjDlogRegistro().setName("Crear nueva persona");
        vista.getjDlogRegistro().setTitle("Crear nueva persona");

        visibilidadCrear();
    }

    public void imprimir() {

        ConexionPG cpg = new ConexionPG();//Instanciar la conexion con esto abrimos la conexion a la BD
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/PersonaReporte.jasper"));

            //Hacer una vista previa
            //JasperPrint jp = JasperFillManager.fillReport(jr, null, cpg.getCon());//JasperFillManager.fillReport: Carga los datos de la BD.//JasperPrint: Hace la impresion del reporte. Puede ir 'null' si en el jasper no existen parametros caso contrario se envian los parametros necesarios
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("ImagenRuta", "src/imagenesJasper/persona.png"); //En donde esta 'titulo' tienen que ser igual al nombre que esta en el parametro del jasper

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cpg.getCon());//'parametros' es el Map recien creado que contiene los parametros que iran al jasper

            JasperViewer jv = new JasperViewer(jp, false); //Se pasa false para que no se cierre el sistema 
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrirDlgRegistroDocente() {

        vista.getjDlgRegistroDocente().setVisible(true);
        vista.getjDlgRegistroDocente().setSize(409, 310);
        vista.getjDlgRegistroDocente().setLocationRelativeTo(vista.getCheckDocente());
        vista.getjDlgRegistroDocente().setName("Registrar docente");
        vista.getjDlgRegistroDocente().setTitle("Registrar docente");

    }

    public void abrirDlgRegistroProductor() {
        vista.getjDlgRegistroProductor().setVisible(true);
        vista.getjDlgRegistroProductor().setSize(424, 330);
        vista.getjDlgRegistroProductor().setLocationRelativeTo(vista.getCheckProductor());
        vista.getjDlgRegistroProductor().setName("Registrar productor");
        vista.getjDlgRegistroProductor().setTitle("Registrar productor");

    }

    public void abrirDlgRegistroAdministrador() {

        vista.getjDlgRegistroAdministrador().setVisible(true);
        vista.getjDlgRegistroAdministrador().setSize(401, 405);
        vista.getjDlgRegistroAdministrador().setLocationRelativeTo(vista.getCheckAdministrador());
        vista.getjDlgRegistroAdministrador().setName("Registrar administrador");
        vista.getjDlgRegistroAdministrador().setTitle("Registrar administrador");

    }

    public void registroEstudiante() {

        if (validarDatosPersona()) {

            int response = JOptionPane.showConfirmDialog(vista, "¿Desea registrar a esta persona como estudiante?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                crearPersona();
                ModeloPersona persona = new ModeloPersona();
                ModeloEstudiante estudiante = new ModeloEstudiante();

                if (estudiante.traerEstadoDelEstudiante(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText())).equals("A")) {

                    JOptionPane.showMessageDialog(null, "Esta persona ya ha sido ha asignada como estudiante");
                } else {
                    if (estudiante.traerEstadoDelEstudiante(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText())).equals("I")) {
                        if (estudiante.crearEstudiante2(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText())) == null) {
                            JOptionPane.showMessageDialog(null, "Estudiante asignado correctamente");
                        }
                    } else {
                        //Setear Datos de estudiante
                        ModeloEstudiante estudiantes = new ModeloEstudiante();
                        estudiantes.setEst_codper(persona.traerCodigoDePersonaCrear(vista.getTxtCedula().getText()));

                        if (estudiantes.crearEstudiante() == null) {

                            JOptionPane.showMessageDialog(null, "Estudiante asignado correctamente");
                            cargarPersonasTabla();

                        } else {
                            JOptionPane.showMessageDialog(null, "Error al asignar el rol de estudiante");
                        }
                    }
                }
            }
        }
    }

    public void cargarPersonasTabla() {
        DefaultTableModel tblModel;
        tblModel = (DefaultTableModel) vista.getTblPersona().getModel();
        tblModel.setNumRows(0);//limpio filas de la tabla.

        List<Persona> listap = modelo.listaPersonasTabla();//Enlazo al Modelo y obtengo los datos
        Holder<Integer> i = new Holder<>(0);//Contador para las filas. 'i' funciona dentro de una expresion lambda

        listap.stream().forEach(pe -> {

            tblModel.addRow(new Object[9]);//Creo una fila vacia
            vista.getTblPersona().setValueAt(pe.getPer_cedula(), i.value, 0);
            vista.getTblPersona().setValueAt(pe.getPer_primernom(), i.value, 1);
            vista.getTblPersona().setValueAt(pe.getPer_apellidopater(), i.value, 2);
            vista.getTblPersona().setValueAt(pe.getPer_telefono(), i.value, 3);
            vista.getTblPersona().setValueAt(pe.getPer_email(), i.value, 4);

            i.value++;
        });
    }

    public void crearPersonaYDocente() {

        //Insertar persona
        if (crearPersona()) {
            //Insertar docente
            if (validarDatosDocenteYEmpleado()) {

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

                    Date fecha = vista.getFechaContratacionDocente().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    empleado.setEmp_fechacontratacion(fechaSQL);
                    empleado.setEmp_salario(Double.parseDouble(vista.getSpinnerSueldoDocente().getValue().toString()));

                    if (empleado.crearEmpleado() == null) {

                        //Setear Datos de Docente
                        docente.setDoc_codemp(empleado.traerCodigoDeEmpleadoCrear());
                        docente.setDoc_especialidad(vista.getTxtEspecialidad().getText());

                        if (docente.crearDocente() == null) {
                            JOptionPane.showMessageDialog(null, "Docente asignado correctamente");
                            vista.getjDlgRegistroDocente().setVisible(false);
                            cargarPersonasTabla();

                        } else {
                            JOptionPane.showConfirmDialog(null, "No se pudo asignar el rol");
                        }
                    } else {
                        System.out.println("Error al insertar empleado");
                    }
                }
                System.out.println("Asignar: " + asignar);
            }
        }
    }

    public void crearPersonaYProductor() {

        //Insertar persona
        if (crearPersona()) {
            //Insertar productor
            if (validarDatosProductorYEmpleado()) {
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

                    Date fecha = vista.getFechaContratacionProductor().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    empleado.setEmp_fechacontratacion(fechaSQL);
                    empleado.setEmp_salario(Double.parseDouble(vista.getSpinnerSueldoProductor().getValue().toString()));

                    if (empleado.crearEmpleado() == null) {

                        //Setear Datos de Productor
                        productor.setPro_codemp(empleado.traerCodigoDeEmpleadoCrear());
                        productor.setPro_expe(Integer.parseInt(vista.getSpinnerExperiencia().getValue().toString()));

                        if (productor.crearProductor() == null) {
                            JOptionPane.showMessageDialog(null, "Productor asignado correctamente");
                            vista.getjDlgRegistroProductor().setVisible(false);
                            cargarPersonasTabla();

                        } else {
                            JOptionPane.showConfirmDialog(null, "No se pudo asignar el rol");
                        }
                    } else {
                        System.out.println("Error al insertar empleado");
                    }
                }
                System.out.println("Asignar: " + asignar);
            }
        }
    }

    public void crearPersonaYAdministrador() {

        //Insertar Administrador
        if (validarDatosAdministradorYEmpleado()) {

            ModeloPersona persona = new ModeloPersona();
            ModeloEmpleado empleado = new ModeloEmpleado();
            ModeloAdministrador administrador = new ModeloAdministrador();

            if (administrador.validarUsuariosRepetidos(vista.getTxtUsuario().getText()) == 0) {

                //Insertar persona
                if (crearPersona()) {

                    asignar = false; //Siempre que ingrese a este metodo la variable sera false

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

                        Date fecha = vista.getFechaContratacionAdministrador().getDate();
                        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                        empleado.setEmp_fechacontratacion(fechaSQL);
                        empleado.setEmp_salario(Double.parseDouble(vista.getSpinnerSueldoAdministrador().getValue().toString()));

                        if (empleado.crearEmpleado() == null) {

                            //Setear Datos de administrador
                            administrador.setAdm_codemp(empleado.traerCodigoDeEmpleadoCrear());
                            administrador.setAdm_usuario(vista.getTxtUsuario().getText());
                            administrador.setAdm_clave(vista.getTxtContrasenia().getText());

                            if (administrador.crearAdministrador() == null) {
                                JOptionPane.showMessageDialog(null, "Administrador asignado correctamente");
                                vista.getjDlgRegistroAdministrador().setVisible(false);
                                cargarPersonasTabla();

                            } else {
                                JOptionPane.showConfirmDialog(null, "No se pudo asignar el rol");
                            }
                        } else {
                            System.out.println("Error al insertar empleado");
                        }
                    }
                    System.out.println("Asignar: " + asignar);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El nombre de usuario ya se encuentra registrado");
            }
        }
    }

    public void cargarDatosPersonaEnTXT() {
        int fila = vista.getTblPersona().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            vista.getjDlogRegistro().setVisible(true);
            vista.getjDlogRegistro().setSize(802, 622);
            vista.getjDlogRegistro().setLocationRelativeTo(null);
            vista.getjDlogRegistro().setName("Modificar persona");
            vista.getjDlogRegistro().setTitle("Modificar  persona");

            visibilidadModificar();

            //ModeloPersona modeloPersona = new ModeloPersona();
            List<Persona> listap = modelo.listaPersonasTabla();

            listap.stream().forEach(persona -> {

                if (persona.getPer_cedula().equals(vista.getTblPersona().getValueAt(fila, 0).toString())) {
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
        }
    }

    public boolean crearPersona() {

        boolean crearPersona = false;
        //Insertar persona
        if (modelo.validarRepetidos(vista.getTxtCedula().getText()) == 0) {

            if (validarDatosPersona()) {

                ModeloPersona persona = new ModeloPersona();

                persona.setPer_cedula(vista.getTxtCedula().getText());
                persona.setPer_primernom(vista.getTxtPrimerNombre().getText());
                persona.setPer_segundonom(vista.getTxtSegundoNombre().getText());
                persona.setPer_apellidopater(vista.getTxtPrimerApellido().getText());
                persona.setPer_apellidomater(vista.getTxtSegundoApellido().getText());

                Date fecha = vista.getFechaNacimiento().getDate();
                java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                persona.setPer_fechanac(fechaSQL);

                String genero;
                if (vista.getMasculino().isSelected()) {
                    genero = "M";
                } else {
                    if (vista.getFemenino().isSelected()) {
                        genero = "F";
                    } else {
                        genero = "B";
                    }
                }

                persona.setPer_genero(genero);
                persona.setPer_telefono(vista.getTxtTelefono().getText());
                persona.setPer_email(vista.getTxtEmail().getText());
                persona.setPer_direccion(vista.getTxtDireccion().getText());

                if (persona.crearPersona() == null) {

                    System.out.println("Persona creada satisfactoriamente");
                    crearPersona = true;
                } else {
                    System.out.println("La persona NO se pudo crear");
                }
            }
        } else {

            JOptionPane.showMessageDialog(null, "El numero de cedula ya se encuentra registrado");
        }

        return crearPersona;
    }

    public void modificarPersona() {
        if (validarDatosPersona()) {
            ModeloPersona persona = new ModeloPersona();

            //Setear los datos de persona
            persona.setPer_cedula(vista.getTxtCedula().getText());
            persona.setPer_primernom(vista.getTxtPrimerNombre().getText());
            persona.setPer_segundonom(vista.getTxtSegundoNombre().getText());
            persona.setPer_apellidopater(vista.getTxtPrimerApellido().getText());
            persona.setPer_apellidomater(vista.getTxtSegundoApellido().getText());

            Date fecha = vista.getFechaNacimiento().getDate();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            persona.setPer_fechanac(fechaSQL);

            String genero;
            if (vista.getMasculino().isSelected()) {
                genero = "M";
            } else {
                if (vista.getFemenino().isSelected()) {
                    genero = "F";
                } else {
                    genero = "B";
                }
            }

            persona.setPer_genero(genero);
            persona.setPer_telefono(vista.getTxtTelefono().getText());
            persona.setPer_email(vista.getTxtEmail().getText());
            persona.setPer_direccion(vista.getTxtDireccion().getText());

            if (persona.modificarPersona() == null) {
                JOptionPane.showMessageDialog(null, "Datos modificados exitosamente");
                vista.getjDlogRegistro().setVisible(false);
                cargarPersonasTabla();
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible modificar los datos");
            }
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

                DefaultTableModel tblModel;
                tblModel = (DefaultTableModel) vista.getTblPersona().getModel();
                tblModel.setNumRows(0);//limpio filas de la tabla.

                List<Persona> listap = modelo.buscarPersona(vista.getTxtBuscar().getText());//Enlazo al Modelo y obtengo los datos
                Holder<Integer> i = new Holder<>(0);//Contador para las filas. 'i' funciona dentro de una expresion lambda

                listap.stream().forEach(pe -> {

                    tblModel.addRow(new Object[9]);
                    vista.getTblPersona().setValueAt(pe.getPer_cedula(), i.value, 0);
                    vista.getTblPersona().setValueAt(pe.getPer_primernom(), i.value, 1);
                    vista.getTblPersona().setValueAt(pe.getPer_apellidopater(), i.value, 2);
                    vista.getTblPersona().setValueAt(pe.getPer_telefono(), i.value, 3);
                    vista.getTblPersona().setValueAt(pe.getPer_email(), i.value, 4);

                    i.value++;
                });
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    public boolean validarDatosPersona() {
        Validaciones mivalidacion = new Validaciones();

        boolean validar = true;

        /*if (vista.getTxtCedula().getText().isEmpty() || !mivalidacion.validarCedula(vista.getTxtCedula().getText())) {

            validar = false;
        }*/
        if (vista.getTxtPrimerNombre().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el primer nombre");
            validar = false;
        } else {
            if (!mivalidacion.validarTextoSinEspacio(vista.getTxtPrimerNombre().getText())) {
                JOptionPane.showMessageDialog(null, "Primer nombre incorrecto");
                validar = false;
            }
        }

        if (vista.getTxtSegundoNombre().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el segundo nombre");
            validar = false;
        } else {
            if (!mivalidacion.validarTextoSinEspacio(vista.getTxtSegundoNombre().getText())) {
                JOptionPane.showMessageDialog(null, "Segundo nombre incorrecto");
                validar = false;
            }
        }

        if (vista.getTxtPrimerApellido().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el primer apellido");
            validar = false;
        } else {
            if (!mivalidacion.validarTextoSinEspacio(vista.getTxtPrimerApellido().getText())) {
                JOptionPane.showMessageDialog(null, "Primer apellido incorrecto");
                validar = false;
            }
        }

        if (vista.getTxtSegundoApellido().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el segundo apellido");
            validar = false;
        } else {
            if (!mivalidacion.validarTextoSinEspacio(vista.getTxtSegundoApellido().getText())) {
                JOptionPane.showMessageDialog(null, "Segundo apellido incorrecto");
                validar = false;
            }
        }

        if (vista.getTxtTelefono().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el numero de telefono");
            validar = false;
        } else {
            if (!mivalidacion.validarTelefono(vista.getTxtTelefono().getText())) {
                JOptionPane.showMessageDialog(null, "Telefono incorrecto");
                validar = false;
            }
        }

        if (vista.getTxtEmail().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el correo");
            validar = false;
        } else {
            if (!mivalidacion.validarCorreo(vista.getTxtEmail().getText())) {
                JOptionPane.showMessageDialog(null, "Correo incorrecto");
                validar = false;
            }
        }

        if (vista.getTxtDireccion().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la direccion");
            validar = false;
        } else {
            if (!mivalidacion.validarDireccion(vista.getTxtDireccion().getText())) {
                JOptionPane.showMessageDialog(null, "Direccion incorrecta");
                validar = false;
            }
        }
        if (vista.getFechaNacimiento().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese la fecha fecha de nacimiento");
            validar = false;
        } else {

            LocalDate fechaNueva = vista.getFechaNacimiento().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Paso la fecha de nacimiento de tipo Date a tipo LocalDate

            if (Period.between(fechaNueva, LocalDate.now()).getYears() < 5) { //Comparo si la persona es mayor a 5 años
                JOptionPane.showMessageDialog(null, "Debe tener mas de 5 años");
                validar = false;
            } else {
                if (Period.between(fechaNueva, LocalDate.now()).getYears() > 70) { //Comparo si la persona tiene menos de 70 años
                    JOptionPane.showMessageDialog(null, "Fecha incorrecta. Usted sobrepasa los 70 años");
                    validar = false;
                }
            }
        }

        if (vista.getMasculino().isSelected() == false && vista.getFemenino().isSelected() == false && vista.getNoBinario().isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Seleccione el genero");
            validar = false;
        }

        return validar;
    }

    public boolean validarDatosDocenteYEmpleado() {
        Validaciones mivalidacion = new Validaciones();

        boolean validar = true;

        if (vista.getFechaContratacionDocente().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese la fecha de contratacion");
            validar = false;
        } else {

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");//Doy formato a la fecha
            String fechaContratacionT = formato.format(vista.getFechaContratacionDocente().getDate()); //Paso de la fecha de contratacion de tipo de Date a String con el formato especificado

            Date fechaConD = null;
            try {
                fechaConD = formato.parse(fechaContratacionT); //Paso la fecha de contratacion de String a Date

            } catch (ParseException ex) {
                Logger.getLogger(ControladorDocente.class
                        .getName()).log(Level.SEVERE, null, ex);
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

    public boolean validarDatosProductorYEmpleado() {

        boolean validar = true;

        if (vista.getFechaContratacionProductor().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese la fecha de contratacion");
            validar = false;
        } else {

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");//Doy formato a la fecha
            String fechaContratacionT = formato.format(vista.getFechaContratacionProductor().getDate()); //Paso de la fecha de contratacion de tipo de Date a String con el formato especificado

            Date fechaConD = null;
            try {
                fechaConD = formato.parse(fechaContratacionT); //Paso la fecha de contratacion de String a Date

            } catch (ParseException ex) {
                Logger.getLogger(ControladorDocente.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            Date fechaNueva = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()); //Paso la fecha actual de tipo LocalDate a Date
            if (fechaConD.after(fechaNueva)) {
                JOptionPane.showMessageDialog(null, "La fecha de contratacion no puede superar a la fecha actual");
                return false;
            }

        }

        return validar;
    }

    public boolean validarDatosAdministradorYEmpleado() {
        Validaciones mivalidacion = new Validaciones();

        boolean validar = true;

        if (vista.getFechaContratacionAdministrador().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese la fecha de contratacion");
            validar = false;
        } else {

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");//Doy formato a la fecha
            String fechaContratacionT = formato.format(vista.getFechaContratacionAdministrador().getDate()); //Paso de la fecha de contratacion de tipo de Date a String con el formato especificado

            Date fechaConD = null;
            try {
                fechaConD = formato.parse(fechaContratacionT); //Paso la fecha de contratacion de String a Date

            } catch (ParseException ex) {
                Logger.getLogger(ControladorAdministrador.class
                        .getName()).log(Level.SEVERE, null, ex);
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

    public static int obtenerEdad(LocalDate FNacimiento) {
        return Period.between(FNacimiento, LocalDate.now()).getYears();
    }

    public void visibilidadCrear() {
        vista.getBtnModificarPersona().setVisible(false);
        vista.getCheckDocente().setVisible(true);
        vista.getCheckProductor().setVisible(true);
        vista.getCheckAdministrador().setVisible(true);
        vista.getCheckEstudiante().setVisible(true);

        vista.getTxtCedula().setEditable((true));
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
        vista.getCheckDocente().setSelected(false);
        vista.getCheckProductor().setSelected(false);
        vista.getCheckAdministrador().setSelected(false);
        vista.getCheckEstudiante().setSelected(false);
    }

    public void visibilidadModificar() {
        vista.getBtnModificarPersona().setVisible(true);
        vista.getCheckDocente().setVisible(false);
        vista.getCheckProductor().setVisible(false);
        vista.getCheckAdministrador().setVisible(false);
        vista.getCheckEstudiante().setVisible(false);

        vista.getLblRol().setVisible(false);
        vista.getTxtCedula().setEditable(false);
    }

    public void botonCancelarPersona() {
        vista.getjDlogRegistro().setVisible(false);
    }

    public void botonCancelarAdministrador() {
        vista.getjDlgRegistroAdministrador().setVisible(false);
    }

    public void botonCancelarProductor() {
        vista.getjDlgRegistroProductor().setVisible(false);
    }

    public void botonCancelarDocente() {
        vista.getjDlgRegistroDocente().setVisible(false);
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
