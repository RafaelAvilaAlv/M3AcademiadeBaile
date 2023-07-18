/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Dirigir;
import Modelo.ModeloDirigir;
import Modelo.ModeloProductor;
import Modelo.ModeloSetGrab;
import Modelo.Productor;
import Modelo.SetGrabacion;
import Vista.VistaDirigir;
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

/**
 *
 * @author Usuario
 */
public class ControladorDirigir {

    ModeloDirigir modelo;
    VistaDirigir vista;

    static boolean verificarDirigir;

    VistaPrincipal p = new VistaPrincipal();

    public ControladorDirigir(ModeloDirigir modelo, VistaDirigir vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        vista.setVisible(true);
        cargarTablaDirigir();
    }

    public void iniciarControl() {

        vista.getBtnAsignar().addActionListener(l -> abrirjDialogDirigir());
        //productor
        vista.getBtnBuscarProductor().addActionListener(l -> abrirjDialogProductor());
        vista.getBtnCargarProductor().addActionListener(l -> cargarDatosProductorEnTXT());
        //set grabacion
        vista.getBtnBuscarSetGrabacion().addActionListener(l -> abrirjDialogSetGrab());
        vista.getBtnCargarSetGrabacion().addActionListener(l -> cargarDatosSetGrabacionEnTXT());
        vista.getBtnGuardar().addActionListener(l -> crearModificarDirigir());
        vista.getBtnModificar().addActionListener(l -> cargarDatosDirigirEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarDirigir());
        vista.getBtnCancelar().addActionListener(l -> botonCancelar());
        //vista.getBtnImprimir().addActionListener(l -> imprimir());
        buscarRegistros();
    }

    public void cargarTablaDirigir() {

        DefaultTableModel tabla = (DefaultTableModel) vista.getTblDirigir().getModel();
        tabla.setNumRows(0);

        ModeloProductor modeloProductor = new ModeloProductor();
        ModeloSetGrab modeloSetGrab = new ModeloSetGrab();

        List<Dirigir> dirigi = modelo.listaDirigirTabla();
        List<Productor> productores = modeloProductor.listaProductorTabla();
        List<SetGrabacion> grabaciones = modeloSetGrab.listaSetGrabTabla();

        dirigi.stream().forEach(dr -> {
            System.out.println("Codigo prodir : " + dr.getDir_codpro());

            productores.stream().forEach(p -> {

                if (dr.getDir_codpro() == p.getPro_codigo()) {

                    System.out.println("Codigo dir_pro : " + dr.getDir_codpro());
                    System.out.println("Codigo pro : " + p.getPro_codigo());

                    grabaciones.stream().forEach(g -> {

                        if (dr.getDir_codset() == g.getSet_codigo()) {

                            String[] datos = {String.valueOf(dr.getDir_codigo()), String.valueOf(p.getPro_codigo()), p.getPer_cedula(), p.getPer_primernom() + " " + p.getPer_apellidopater(), String.valueOf(g.getSet_codigo()), String.valueOf(g.getSet_nombre())};
                            tabla.addRow(datos);

                        }
                    });
                }
            });
        });
    }

    public void abrirjDialogDirigir() {
        vista.getjDlgDirigir().setVisible(true);
        vista.getjDlgDirigir().setSize(872, 435);
        vista.getjDlgDirigir().setLocationRelativeTo(null);
        vista.getjDlgDirigir().setTitle("Crear Dirigir");
        vista.getjDlgDirigir().setName("Crear Dirigir");
        vista.getTxtCodigoProductor().setVisible(false);
        vista.getTxtCodigoSetgrabacion().setVisible(false);
        bloquearCampos();
        cargarFechaActual();
    }

    //Todo sobre el registro de Docentes en el jDialog
    public void abrirjDialogProductor() {
        vista.getjDlgBuscarProductor().setVisible(true);
        vista.getjDlgBuscarProductor().setSize(685, 418);
        vista.getjDlgBuscarProductor().setLocationRelativeTo(null);
        vista.getjDlgBuscarProductor().setTitle("Seleccione un productor");
        cargarRegistroDeProductores();
        buscarProductor();
    }

    public void crearModificarDirigir() {
        if ("Crear Dirigir".equals(vista.getjDlgDirigir().getName())) {

            verificarDirigir = false;

            //INSERTAR
            if (validarDatos()) {

                List<Dirigir> dirigi = modelo.listaDirigirTabla();

                dirigi.stream().forEach(dr -> {

                    if (dr.getDir_codpro() == Integer.parseInt(vista.getTxtCodigoProductor().getText())) {
                        if (dr.getDir_codset() == Integer.parseInt(vista.getTxtCodigoSetgrabacion().getText())) {
                            verificarDirigir = true;
                        }
                    }
                });

                if (verificarDirigir) {
                    JOptionPane.showMessageDialog(vista, "El productor ya se encuentra asignado al set de grabacion");
                } else {

                    modelo.setDir_codpro(Integer.parseInt(vista.getTxtCodigoProductor().getText()));
                    modelo.setDir_codset(Integer.parseInt(vista.getTxtCodigoSetgrabacion().getText()));

                    Date fecha = vista.getFechaDeAsignacion().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    modelo.setDir_fecharegistro(fechaSQL);

                    if (modelo.asignarDirigir() == null) {
                        JOptionPane.showMessageDialog(vista, "La asignacion se ha realizado satisfactoriamente");
                        vista.getjDlgDirigir().setVisible(false);
                        cargarTablaDirigir();
                    } else {
                        JOptionPane.showMessageDialog(vista, "No se pudo realizar la asignación");
                    }
                }
            }

        } else {
            //Editar
            verificarDirigir = false;

            if (validarDatos()) {

                List<Dirigir> diri = modelo.listaDirigirTabla();

                diri.stream().forEach(dr -> {

                    if (dr.getDir_codpro() == Integer.parseInt(vista.getTxtCodigoProductor().getText())) {
                        if (dr.getDir_codset() == Integer.parseInt(vista.getTxtCodigoSetgrabacion().getText())) {
                            verificarDirigir = true;
                        }
                    }
                });

                //Si el productor ya imparte la materia solo se modificara la fecha
                if (verificarDirigir) {

                    modelo.setDir_codigo(codigoDirigir);

                    Date fecha = vista.getFechaDeAsignacion().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    modelo.setDir_fecharegistro(fechaSQL);

                    if (modelo.modificarDirigirFecha() == null) {
                        JOptionPane.showMessageDialog(vista, "Los datos fueron modificados satisfactoriamente");
                        vista.getjDlgDirigir().setVisible(false);
                        cargarTablaDirigir();
                    }
                } else {

                    modelo.setDir_codigo(codigoDirigir);
                    modelo.setDir_codpro(Integer.parseInt(vista.getTxtCodigoProductor().getText()));
                    modelo.setDir_codset(Integer.parseInt(vista.getTxtCodigoSetgrabacion().getText()));

                    Date fecha = vista.getFechaDeAsignacion().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    modelo.setDir_fecharegistro(fechaSQL);

                    if (modelo.modificarDirigirCompleto() == null) {
                        JOptionPane.showMessageDialog(vista, "Los datos fueron modificados satisfactoriamente");
                        vista.getjDlgDirigir().setVisible(false);
                        cargarTablaDirigir();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Los datos no pudieron ser modificados");
                    }
                }
            }
        }
    }

    int codigoDirigir;

    public void cargarDatosDirigirEnTXT() {
        int fila = vista.getTblDirigir().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            vista.getjDlgDirigir().setVisible(true);
            vista.getjDlgDirigir().setSize(872, 435);
            vista.getjDlgDirigir().setLocationRelativeTo(null);
            vista.getjDlgDirigir().setName("Modificar dirigir");
            vista.getjDlgDirigir().setTitle("Modificar  dirigir");
            vista.getTxtCodigoProductor().setVisible(false);
            vista.getTxtCodigoSetgrabacion().setVisible(false);

            bloquearCampos();
                    
            ModeloProductor modeloProductor = new ModeloProductor();
            ModeloSetGrab modeloset = new ModeloSetGrab();

            List<Dirigir> dirig = modelo.listaDirigirTabla();
            List<Productor> productores = modeloProductor.listaProductorTabla();
            List<SetGrabacion> setgrab = modeloset.listaSetGrabTabla();

            dirig.stream().forEach(dr -> {

                if (Integer.parseInt(vista.getTblDirigir().getValueAt(fila, 0).toString()) == dr.getDir_codigo()) {

                    codigoDirigir = Integer.parseInt(vista.getTblDirigir().getValueAt(fila, 0).toString());

                    productores.stream().forEach(p -> {

                        if (dr.getDir_codpro() == p.getPro_codigo()) {

                            setgrab.stream().forEach(s -> {

                                if (dr.getDir_codset() == s.getSet_codigo()) {

                                    vista.getTxtCodigoProductor().setText(String.valueOf(p.getPro_codigo()));
                                    vista.getTxtCedula().setText(p.getPer_cedula());
                                    vista.getTxtNombreDocente().setText(p.getPer_primernom());
                                    vista.getTxtApellido().setText(p.getPer_apellidopater());
                                    vista.getTxtEspecialidad().setText(String.valueOf(p.getPro_expe()));
                                    vista.getTxtCodigoSetgrabacion().setText(String.valueOf(s.getSet_codigo()));
                                    vista.getTxtNombreAsignatura().setText(s.getSet_nombre());
                                    vista.getFechaDeAsignacion().setDate(dr.getDir_fecharegistro());

                                }
                            });
                        }
                    });
                }
            });
        }
    }

    public void eliminarDirigir() {
        int fila = vista.getTblDirigir().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigo;
                codigo = Integer.parseInt(vista.getTblDirigir().getValueAt(fila, 0).toString());

                if (modelo.eliminarDirigir(codigo) == null) {
                    JOptionPane.showMessageDialog(null, "El registro fue eliminado exitosamente");
                    cargarTablaDirigir();//Actualizo la tabla con los datos
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

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblDirigir().getModel();

                //vista.getTablaconduccion().setAutoCreateRowSorter(true);
                filtrar = new TableRowSorter<>(tabla);
                vista.getTblDirigir().setRowSorter(filtrar);

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
    public void cargarRegistroDeProductores() {

        ModeloProductor modeloProductor = new ModeloProductor();

        vista.getTblDlgProductor().setRowHeight(25);
        DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgProductor().getModel();
        estructuraTabla.setRowCount(0);

        List<Productor> listap = modeloProductor.listaProductorTabla();

        Holder<Integer> i = new Holder<>(0);

        listap.stream().forEach(c -> {

            estructuraTabla.addRow(new Object[8]);

            vista.getTblDlgProductor().setValueAt(c.getPer_cedula(), i.value, 0);
            vista.getTblDlgProductor().setValueAt(c.getPer_primernom(), i.value, 1);
            vista.getTblDlgProductor().setValueAt(c.getPer_apellidopater(), i.value, 2);
            vista.getTblDlgProductor().setValueAt(c.getPro_expe(), i.value, 3);
            vista.getTblDlgProductor().setValueAt(c.getEmp_salario(), i.value, 4);

            i.value++;
        });
    }

    public void cargarDatosProductorEnTXT() {
        int fila = vista.getTblDlgProductor().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            ModeloProductor modeloProductor = new ModeloProductor();
            List<Productor> listap = modeloProductor.listaProductorTabla();

            listap.stream().forEach(c -> {

                if (c.getPer_cedula().equals(vista.getTblDlgProductor().getValueAt(fila, 0).toString())) {

                    vista.getTxtCodigoProductor().setText(String.valueOf(c.getPro_codigo()));
                    vista.getTxtCedula().setText(c.getPer_cedula());
                    vista.getTxtNombreDocente().setText(c.getPer_primernom());
                    vista.getTxtApellido().setText(c.getPer_apellidopater());
                    vista.getTxtEspecialidad().setText(String.valueOf(c.getPro_expe()));
                }
            });

            vista.getjDlgBuscarProductor().dispose();
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

                ModeloProductor modeloProductor = new ModeloProductor();
                vista.getTblDlgProductor().setRowHeight(25);
                DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgProductor().getModel();
                estructuraTabla.setRowCount(0);

                List<Productor> listap = modeloProductor.buscarProductor(vista.getTxtBuscarProductor().getText());

                Holder<Integer> i = new Holder<>(0);

                listap.stream().forEach(c -> {

                    estructuraTabla.addRow(new Object[8]);

                    vista.getTblDlgProductor().setValueAt(c.getPer_cedula(), i.value, 0);
                    vista.getTblDlgProductor().setValueAt(c.getPer_primernom(), i.value, 1);
                    vista.getTblDlgProductor().setValueAt(c.getPer_apellidopater(), i.value, 2);
                    vista.getTblDlgProductor().setValueAt(c.getPro_expe(), i.value, 3);
                    vista.getTblDlgProductor().setValueAt(c.getEmp_salario(), i.value, 4);

                    i.value++;
                });
            }
        };

        vista.getTxtBuscarProductor().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //Todo sobre el registro de asignatura en el jDialog
    public void abrirjDialogSetGrab() {
        vista.getjDlgBuscarSetgrabacion().setVisible(true);
        vista.getjDlgBuscarSetgrabacion().setSize(619, 435);
        vista.getjDlgBuscarSetgrabacion().setLocationRelativeTo(null);
        vista.getjDlgBuscarSetgrabacion().setTitle("Seleccione un set de grabacion");
        cargarRegistroDeSetGrabacion();
        buscarSetGrabacion();
    }

    public void cargarRegistroDeSetGrabacion() {

        ModeloSetGrab modeloset = new ModeloSetGrab();

        vista.getTblDlgjSetGrabacion().setRowHeight(25);
        DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgjSetGrabacion().getModel();
        estructuraTabla.setRowCount(0);

        List<SetGrabacion> listap = modeloset.listaSetGrabTabla();

        Holder<Integer> i = new Holder<>(0);

        listap.stream().forEach(c -> {

            estructuraTabla.addRow(new Object[8]);

            vista.getTblDlgjSetGrabacion().setValueAt(c.getSet_codigo(), i.value, 0);
            vista.getTblDlgjSetGrabacion().setValueAt(c.getSet_nombre(), i.value, 1);

            i.value++;
        });
    }

    public void cargarDatosSetGrabacionEnTXT() {
        int fila = vista.getTblDlgjSetGrabacion().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            ModeloSetGrab modeloset = new ModeloSetGrab();
            List<SetGrabacion> listap = modeloset.listaSetGrabTabla();

            listap.stream().forEach(c -> {

                if (c.getSet_codigo() == Integer.parseInt(vista.getTblDlgjSetGrabacion().getValueAt(fila, 0).toString())) {

                    vista.getTxtCodigoSetgrabacion().setText(String.valueOf(c.getSet_codigo()));
                    vista.getTxtNombreAsignatura().setText(c.getSet_nombre());
                }
            });

            vista.getjDlgBuscarSetgrabacion().dispose();
        }
    }

    public void buscarSetGrabacion() {

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

                ModeloSetGrab modeloset = new ModeloSetGrab();
                vista.getTblDlgjSetGrabacion().setRowHeight(25);
                DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgjSetGrabacion().getModel();
                estructuraTabla.setRowCount(0);

                List<SetGrabacion> listap = modeloset.buscarSetGrabacion(vista.getTxtBuscarSetGrabacion().getText());

                Holder<Integer> i = new Holder<>(0);

                listap.stream().forEach(c -> {

                    estructuraTabla.addRow(new Object[8]);

                    vista.getTblDlgjSetGrabacion().setValueAt(c.getSet_codigo(), i.value, 0);
                    vista.getTblDlgjSetGrabacion().setValueAt(c.getSet_nombre(), i.value, 1);

                    i.value++;
                });
            }
        };

        vista.getTxtBuscarSetGrabacion().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    public boolean validarDatos() {

        boolean validar = true;

        if (vista.getTxtCedula().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un docente");
            validar = false;
        }

        if (vista.getTxtNombreAsignatura().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un set de grabacion");
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

    public void cargarFechaActual() {
        vista.getFechaDeAsignacion().setEnabled(false);

        //Seteo la fecha actual en el jCalendar
        Date fecha = new Date();
        vista.getFechaDeAsignacion().setDate(fecha);
    }

    public void bloquearCampos() {
        vista.getTxtCedula().setEditable(false);
        vista.getTxtNombreDocente().setEditable(false);
        vista.getTxtApellido().setEditable(false);
        vista.getTxtEspecialidad().setEditable(false);
        vista.getTxtCodigoProductor().setEditable(false);
        vista.getTxtCodigoSetgrabacion().setEnabled(false);
        vista.getTxtNombreAsignatura().setEnabled(false);
    }

    public void botonCancelar() {
        vista.getjDlgDirigir().setVisible(false);
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
