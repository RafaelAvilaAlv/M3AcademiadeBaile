package Controlador;

import Modelo.AsiAsignatura;
import Modelo.Asignatura;
import Modelo.Docente;
import Modelo.ModeloAsiAsignatura;
import Modelo.ModeloAsignatura;
import Modelo.ModeloDocente;

import Vista.VistaAsiAsignatura;
import Vista.VistaPrincipal;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.ws.Holder;

public class ControladorAsiAsignatura {

    ModeloAsiAsignatura modelo;
    VistaAsiAsignatura vista;

    static boolean verificarAsignacion;

    VistaPrincipal p = new VistaPrincipal();

    public ControladorAsiAsignatura(ModeloAsiAsignatura modelo, VistaAsiAsignatura vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        vista.setVisible(true);
        cargarTablaAsignaciones();
    }

    public void iniciarControl() {

        vista.getBtnAsignar().addActionListener(l -> abrirjDialogAsignarAsignatura());
        vista.getBtnBuscarDocente().addActionListener(l -> abrirjDialogDocente());
        vista.getBtnCargarDocente().addActionListener(l -> cargarDatosDocenteEnTXT());
        vista.getBtnBuscarAsignatura().addActionListener(l -> abrirjDialogAsignatura());
        vista.getBtnCargarAsignatura().addActionListener(l -> cargarDatosAsignaturaEnTXT());
        vista.getBtnGuardar().addActionListener(l -> crearModificarAsignarAsignatura());
        vista.getBtnModificar().addActionListener(l -> cargarDatosAsignarAsignaturaEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarAsignacion());
        vista.getBtnCancelar().addActionListener(l -> botonEliminar());
        //vista.getBtnImprimir().addActionListener(l -> imprimir());
        buscarRegistros();
    }

    public void cargarTablaAsignaciones() {

        System.out.println("Entra a");
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblAsiAsignatura().getModel();
        tabla.setNumRows(0);

        ModeloDocente modeloDocente = new ModeloDocente();
        ModeloAsignatura modeloAsignatura = new ModeloAsignatura();

        List<AsiAsignatura> asignaciones = modelo.listaAsignacionesTabla();
        List<Docente> docentes = modeloDocente.listaDocentesTabla();
        List<Asignatura> asignaturas = modeloAsignatura.listaAsignaturaTabla();

        asignaciones.stream().forEach(as -> {
            System.out.println("Codigo docAsi : " + as.getAsig_coddoc());

            docentes.stream().forEach(d -> {

                if (as.getAsig_coddoc() == d.getDoc_codigo()) {

                    System.out.println("Codigo docAsi : " + as.getAsig_coddoc());
                    System.out.println("Codigo doc : " + d.getDoc_codigo());

                    asignaturas.stream().forEach(a -> {

                        if (as.getAsig_codasi() == a.getAsi_codigo()) {

                            String[] datos = {String.valueOf(as.getAsig_codigo()), String.valueOf(d.getDoc_codigo()), d.getPer_cedula(), d.getPer_primernom() + " " + d.getPer_apellidopater(), String.valueOf(a.getAsi_codigo()), String.valueOf(a.getAsi_nombre())};
                            tabla.addRow(datos);

                        }
                    });
                }
            });
        });
    }

    public void abrirjDialogAsignarAsignatura() {

        vista.getjDlgAsiAsignatura().setSize(892, 449);
        vista.getjDlgAsiAsignatura().setTitle("Asignar asignatura");
        vista.getjDlgAsiAsignatura().setName("Asignar asignatura");
        vista.getjDlgAsiAsignatura().setVisible(true);
        vista.getjDlgAsiAsignatura().setLocationRelativeTo(null);
        vista.getTxtCodigoDocente().setVisible(false);
        vista.getTxtCodigoAsignatura().setVisible(false);
        limpiarCampos();
        bloquearCampos();
        cargarFechaActual();
    }

    //Todo sobre el registro de Docentes en el jDialog
    public void abrirjDialogDocente() {

        vista.getjDlgBuscarDocente().setSize(685, 418);
        vista.getjDlgBuscarDocente().setTitle("Seleccione un docente");
        vista.getjDlgBuscarDocente().setVisible(true);
        vista.getjDlgBuscarDocente().setLocationRelativeTo(null);
        cargarRegistroDeDocentes();
        buscarDocente();
    }

    public void crearModificarAsignarAsignatura() {
        if ("Asignar asignatura".equals(vista.getjDlgAsiAsignatura().getName())) {

            verificarAsignacion = false;

            //INSERTAR
            if (validarDatos()) {

                List<AsiAsignatura> asignaciones = modelo.listaAsignacionesTabla();

                asignaciones.stream().forEach(as -> {

                    if (as.getAsig_coddoc() == Integer.parseInt(vista.getTxtCodigoDocente().getText())) {
                        if (as.getAsig_codasi() == Integer.parseInt(vista.getTxtCodigoAsignatura().getText())) {
                            verificarAsignacion = true;
                        }
                    }
                });

                if (verificarAsignacion) {
                    JOptionPane.showMessageDialog(vista, "El docente ya imparte esta asignatura");
                } else {

                    modelo.setAsig_coddoc(Integer.parseInt(vista.getTxtCodigoDocente().getText()));
                    modelo.setAsig_codasi(Integer.parseInt(vista.getTxtCodigoAsignatura().getText()));

                    Date fecha = vista.getFechaDeAsignacion().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    modelo.setAsig_fecha(fechaSQL);

                    if (modelo.asignarAsignatura() == null) {
                        JOptionPane.showMessageDialog(vista, "La asignacion se ha realizado satisfactoriamente");
                        vista.getjDlgAsiAsignatura().setVisible(false);
                        cargarTablaAsignaciones();
                    } else {
                        JOptionPane.showMessageDialog(vista, "No se pudo realizar la asignación");
                    }
                }
            }

        } else {
            //Editar
            verificarAsignacion = false;

            if (validarDatos()) {

                List<AsiAsignatura> asignaciones = modelo.listaAsignacionesTabla();

                asignaciones.stream().forEach(as -> {

                    if (as.getAsig_coddoc() == Integer.parseInt(vista.getTxtCodigoDocente().getText())) {
                        if (as.getAsig_codasi() == Integer.parseInt(vista.getTxtCodigoAsignatura().getText())) {
                            verificarAsignacion = true;
                        }
                    }
                });

                //Si el docente ya imparte la materia solo se mostrara un mensaje
                if (verificarAsignacion) {

                    modelo.setAsig_codigo(codigoAsiAsignatura);

                    Date fecha = vista.getFechaDeAsignacion().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    modelo.setAsig_fecha(fechaSQL);

                    if (modelo.modificarAsignacionFecha() == null) {
                        JOptionPane.showMessageDialog(vista, "Los datos fueron modificados satisfactoriamente");
                        vista.getjDlgAsiAsignatura().setVisible(false);
                        cargarTablaAsignaciones();
                    }

                } else {

                    modelo.setAsig_codigo(codigoAsiAsignatura);
                    modelo.setAsig_coddoc(Integer.parseInt(vista.getTxtCodigoDocente().getText()));
                    modelo.setAsig_codasi(Integer.parseInt(vista.getTxtCodigoAsignatura().getText()));

                    Date fecha = vista.getFechaDeAsignacion().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    modelo.setAsig_fecha(fechaSQL);

                    if (modelo.modificarAsignacionCompleto() == null) {
                        JOptionPane.showMessageDialog(vista, "Los datos fueron modificados satisfactoriamente");
                        vista.getjDlgAsiAsignatura().setVisible(false);
                        cargarTablaAsignaciones();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Los datos no pudieron ser modificados");
                    }
                }
            }
        }
    }

    int codigoAsiAsignatura;

    public void cargarDatosAsignarAsignaturaEnTXT() {
        int fila = vista.getTblAsiAsignatura().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            vista.getjDlgAsiAsignatura().setName("Modificar asignacion");
            vista.getjDlgAsiAsignatura().setLocationRelativeTo(null);
            vista.getjDlgAsiAsignatura().setSize(885, 433);
            vista.getjDlgAsiAsignatura().setTitle("Modificar  asignacion");
            vista.getjDlgAsiAsignatura().setVisible(true);
            vista.getTxtCodigoDocente().setVisible(false);
            vista.getTxtCodigoAsignatura().setVisible(false);

            bloquearCampos();
            vista.getFechaDeAsignacion().setEnabled(true);

            ModeloDocente modeloDocente = new ModeloDocente();
            ModeloAsignatura modeloAsignatura = new ModeloAsignatura();

            List<AsiAsignatura> asignaciones = modelo.listaAsignacionesTabla();
            List<Docente> docentes = modeloDocente.listaDocentesTabla();
            List<Asignatura> asignaturas = modeloAsignatura.listaAsignaturaTabla();

            asignaciones.stream().forEach(as -> {

                if (Integer.parseInt(vista.getTblAsiAsignatura().getValueAt(fila, 0).toString()) == as.getAsig_codigo()) {

                    codigoAsiAsignatura = Integer.parseInt(vista.getTblAsiAsignatura().getValueAt(fila, 0).toString());

                    docentes.stream().forEach(d -> {

                        if (as.getAsig_coddoc() == d.getDoc_codigo()) {

                            asignaturas.stream().forEach(a -> {

                                if (as.getAsig_codasi() == a.getAsi_codigo()) {

                                    vista.getTxtCodigoDocente().setText(String.valueOf(d.getDoc_codigo()));
                                    vista.getTxtCedula().setText(d.getPer_cedula());
                                    vista.getTxtNombreDocente().setText(d.getPer_primernom());
                                    vista.getTxtApellido().setText(d.getPer_apellidopater());
                                    vista.getTxtEspecialidad().setText(d.getDoc_especialidad());
                                    vista.getTxtCodigoAsignatura().setText(String.valueOf(a.getAsi_codigo()));
                                    vista.getTxtNombreAsignatura().setText(a.getAsi_nombre());
                                    vista.getFechaDeAsignacion().setDate(as.getAsig_fecha());

                                }
                            });
                        }
                    });
                }
            });
        }
    }

    public void eliminarAsignacion() {
        int fila = vista.getTblAsiAsignatura().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigo;
                codigo = Integer.parseInt(vista.getTblAsiAsignatura().getValueAt(fila, 0).toString());

                if (modelo.eliminarAsignacion(codigo) == null) {
                    JOptionPane.showMessageDialog(null, "El registro fue eliminado exitosamente");
                    cargarTablaAsignaciones();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Los datos no fueron eliminados");
                }
            }
        }
    }

    public void buscarRegistros() {

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

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblAsiAsignatura().getModel();

                //vista.getTablaconduccion().setAutoCreateRowSorter(true);
                filtrar = new TableRowSorter<>(tabla);
                vista.getTblAsiAsignatura().setRowSorter(filtrar);

                try {

                    filtrar.setRowFilter(RowFilter.regexFilter(vista.getTxtBuscar().getText())); //Se pasa como parametro el campo de donde se va a obtener la informacion y el (3) es la columna con la cual va a buscar las coincidencias
                } catch (Exception ex) {
                    System.out.println("Error: " + ex);
                }
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //Todo sobre los registros de docentes
    public void cargarRegistroDeDocentes() {

        ModeloDocente modeloDocente = new ModeloDocente();

        vista.getTblDlgDocente().setRowHeight(25);
        DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgDocente().getModel();
        estructuraTabla.setRowCount(0);

        List<Docente> listap = modeloDocente.listaDocentesTabla();

        Holder<Integer> i = new Holder<>(0);

        listap.stream().forEach(c -> {

            estructuraTabla.addRow(new Object[8]);

            vista.getTblDlgDocente().setValueAt(c.getPer_cedula(), i.value, 0);
            vista.getTblDlgDocente().setValueAt(c.getPer_primernom(), i.value, 1);
            vista.getTblDlgDocente().setValueAt(c.getPer_apellidopater(), i.value, 2);
            vista.getTblDlgDocente().setValueAt(c.getDoc_especialidad(), i.value, 3);
            vista.getTblDlgDocente().setValueAt(c.getEmp_salario(), i.value, 4);

            i.value++;
        });
    }

    public void cargarDatosDocenteEnTXT() {
        int fila = vista.getTblDlgDocente().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            ModeloDocente modeloDocente = new ModeloDocente();
            List<Docente> listap = modeloDocente.listaDocentesTabla();

            listap.stream().forEach(c -> {

                if (c.getPer_cedula().equals(vista.getTblDlgDocente().getValueAt(fila, 0).toString())) {

                    vista.getTxtCodigoDocente().setText(String.valueOf(c.getDoc_codigo()));
                    vista.getTxtCedula().setText(c.getPer_cedula());
                    vista.getTxtNombreDocente().setText(c.getPer_primernom());
                    vista.getTxtApellido().setText(c.getPer_apellidopater());
                    vista.getTxtEspecialidad().setText(String.valueOf(c.getDoc_especialidad()));
                }
            });

            vista.getjDlgBuscarDocente().dispose();
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

                ModeloDocente modeloDocente = new ModeloDocente();
                vista.getTblDlgDocente().setRowHeight(25);
                DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgDocente().getModel();
                estructuraTabla.setRowCount(0);

                List<Docente> listap = modeloDocente.buscarDocente(vista.getTxtBuscarDocente().getText());

                Holder<Integer> i = new Holder<>(0);

                listap.stream().forEach(c -> {

                    estructuraTabla.addRow(new Object[8]);

                    vista.getTblDlgDocente().setValueAt(c.getPer_cedula(), i.value, 0);
                    vista.getTblDlgDocente().setValueAt(c.getPer_primernom(), i.value, 1);
                    vista.getTblDlgDocente().setValueAt(c.getPer_apellidopater(), i.value, 2);
                    vista.getTblDlgDocente().setValueAt(c.getDoc_especialidad(), i.value, 3);
                    vista.getTblDlgDocente().setValueAt(c.getEmp_salario(), i.value, 4);

                    i.value++;
                });
            }
        };

        vista.getTxtBuscarDocente().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //Todo sobre el registro de asignatura en el jDialog
    public void abrirjDialogAsignatura() {

        vista.getjDlgBuscarAsignatura().setSize(708, 435);
        vista.getjDlgBuscarAsignatura().setTitle("Seleccione una asignatura");
        vista.getjDlgBuscarAsignatura().setVisible(true);
        vista.getjDlgBuscarAsignatura().setLocationRelativeTo(null);
        cargarRegistroDeAsignatura();
        buscarAsignatura();
    }

    public void cargarRegistroDeAsignatura() {

        ModeloAsignatura modeloAsignatura = new ModeloAsignatura();

        vista.getTblDlgjAsignatura().setRowHeight(25);
        DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgjAsignatura().getModel();
        estructuraTabla.setRowCount(0);

        List<Asignatura> listap = modeloAsignatura.listaAsignaturaTabla();

        Holder<Integer> i = new Holder<>(0);

        listap.stream().forEach(c -> {

            estructuraTabla.addRow(new Object[8]);

            vista.getTblDlgjAsignatura().setValueAt(c.getAsi_codigo(), i.value, 0);
            vista.getTblDlgjAsignatura().setValueAt(c.getAsi_nombre(), i.value, 1);

            i.value++;
        });
    }

    public void cargarDatosAsignaturaEnTXT() {
        int fila = vista.getTblDlgjAsignatura().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            ModeloAsignatura modeloAsignatura = new ModeloAsignatura();
            List<Asignatura> listap = modeloAsignatura.listaAsignaturaTabla();

            listap.stream().forEach(c -> {

                if (c.getAsi_codigo() == Integer.parseInt(vista.getTblDlgjAsignatura().getValueAt(fila, 0).toString())) {

                    vista.getTxtCodigoAsignatura().setText(String.valueOf(c.getAsi_codigo()));
                    vista.getTxtNombreAsignatura().setText(c.getAsi_nombre());
                }
            });

            vista.getjDlgBuscarAsignatura().dispose();
        }
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

                ModeloAsignatura modeloAsignatura = new ModeloAsignatura();
                vista.getTblDlgjAsignatura().setRowHeight(25);
                DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgjAsignatura().getModel();
                estructuraTabla.setRowCount(0);

                List<Asignatura> listap = modeloAsignatura.buscarAsignatura(vista.getTxtBuscarAsignatura().getText());

                Holder<Integer> i = new Holder<>(0);

                listap.stream().forEach(c -> {

                    estructuraTabla.addRow(new Object[8]);

                    vista.getTblDlgjAsignatura().setValueAt(c.getAsi_codigo(), i.value, 0);
                    vista.getTblDlgjAsignatura().setValueAt(c.getAsi_nombre(), i.value, 1);

                    i.value++;
                });
            }
        };

        vista.getTxtBuscarAsignatura().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    public boolean validarDatos() {

        boolean validar = true;

        if (vista.getTxtCedula().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un docente");
            validar = false;
        }

        if (vista.getTxtNombreAsignatura().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione una asignatura");
            validar = false;
        }

        if (vista.getFechaDeAsignacion().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese una fecha de asignación");
            validar = false;
        } else {

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");//Doy formato a la fecha
            String fechaContratacionT = formato.format(vista.getFechaDeAsignacion().getDate()); //Paso de la fecha de contratacion de tipo de Date a String con el formato especificado

            Date fechaConD = null;
            try {
                fechaConD = formato.parse(fechaContratacionT); //Paso la fecha de contratacion de String a Date

            } catch (ParseException ex) {
                Logger.getLogger(ControladorDocente.class.getName()).log(Level.SEVERE, null, ex);
            }

            Date fechaNueva = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()); //Paso la fecha actual de tipo LocalDate a Date
            if (fechaConD.after(fechaNueva)) {
                JOptionPane.showMessageDialog(null, "La fecha de asignacion no puede superar a la fecha actual");
                return false;
            }

        }

        return validar;
    }

    public void bloquearCampos() {
        vista.getTxtCedula().setEditable(false);
        vista.getTxtNombreDocente().setEditable(false);
        vista.getTxtApellido().setEditable(false);
        vista.getTxtEspecialidad().setEditable(false);
        vista.getTxtNombreAsignatura().setEditable(false);
    }

    public void limpiarCampos() {
        vista.getTxtCedula().setText("");
        vista.getTxtNombreDocente().setText("");
        vista.getTxtApellido().setText("");
        vista.getTxtEspecialidad().setText("");
        vista.getTxtNombreAsignatura().setText("");
    }

    public void cargarFechaActual() {

        vista.getFechaDeAsignacion().setEnabled(false);
        //Seteo la fecha actual en el jCalendar
        Date fecha = new Date();
        vista.getFechaDeAsignacion().setDate(fecha);
    }

    public void botonEliminar() {
        vista.getjDlgAsiAsignatura().setVisible(false);
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
