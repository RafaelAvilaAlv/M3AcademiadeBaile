package Controlador;

import Conexion.ConexionPG;
import Modelo.Estudiante;
import Modelo.ModeloEstudiante;
import Modelo.ModeloReserva;
import Modelo.ModeloSetGrab;
import Modelo.Reserva;
import Modelo.SetGrabacion;
import Vista.VistaPrincipal;
import Vista.VistaReserva;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.ws.Holder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControladorReserva {
    
    ModeloReserva modelo;
    VistaReserva vista;
    
    VistaPrincipal p = new VistaPrincipal();
    
    public ControladorReserva(ModeloReserva modelo, VistaReserva vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        vista.setVisible(true);
        cargarTablaReservas();
    }
    
    public void iniciarControl() {
        
        vista.getBtnReservar().addActionListener(l -> abrirjDialogReserva());
        vista.getBtnBuscarEstudiante().addActionListener(l -> abrirjDialogEstudiante());
        vista.getBtnCargarEstudiante().addActionListener(l -> cargarDatosEstudianteEnTXT());
        vista.getBtnBuscarSet().addActionListener(l -> abrirjDialogSet());
        vista.getBtnCargarSet().addActionListener(l -> cargarDatosSetEnTXT());
        vista.getBtnGuardar().addActionListener(l -> crearModificarReserva());
        vista.getBtnModificar().addActionListener(l -> cargarDatosReservaEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarAsignacion());
        vista.getBtnCancelar().addActionListener(l -> botonCancelar());
        vista.getBtnImprimir().addActionListener(l -> imprimir());
        buscarRegistros();
    }
    public void imprimir() {

        ConexionPG cpg = new ConexionPG();//Instanciar la conexion con esto abrimos la conexion a la BD
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/ReservaReporte.jasper"));

            //Hacer una vista previa
            //JasperPrint jp = JasperFillManager.fillReport(jr, null, cpg.getCon());//JasperFillManager.fillReport: Carga los datos de la BD.//JasperPrint: Hace la impresion del reporte. Puede ir 'null' si en el jasper no existen parametros caso contrario se envian los parametros necesarios
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("ImagenRuta", "src/imagenesJasper/reserva.png"); //En donde esta 'titulo' tienen que ser igual al nombre que esta en el parametro del jasper

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cpg.getCon());//'parametros' es el Map recien creado que contiene los parametros que iran al jasper

            JasperViewer jv = new JasperViewer(jp, false); //Se pasa false para que no se cierre el sistema 
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cargarTablaReservas() {
        
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblReserva().getModel();
        tabla.setNumRows(0);
        
        ModeloEstudiante modeloEstudiante = new ModeloEstudiante();
        ModeloSetGrab modeloSetGrab = new ModeloSetGrab();
        
        List<Reserva> reservas = modelo.listaReservasTabla();
        List<Estudiante> estudiantes = modeloEstudiante.listaEstudiantesTabla();
        List<SetGrabacion> setsgrabacion = modeloSetGrab.listaSetGrabTabla();
        
        reservas.stream().forEach(as -> {
            
            estudiantes.stream().forEach(d -> {
                
                if (as.getRes_codestudiante() == d.getEst_codigo()) {
                    
                    setsgrabacion.stream().forEach(a -> {
                        
                        if (as.getRes_codset() == a.getSet_codigo()) {
                            
                            String[] datos = {String.valueOf(as.getRes_codigo()), d.getPer_cedula(), d.getPer_primernom() + " " + d.getPer_apellidopater(), a.getSet_nombre(), String.valueOf(as.getRes_fechaentra())};
                            tabla.addRow(datos);
                            
                        }
                    });
                }
            });
        });
    }
    
    public void abrirjDialogReserva() {
        vista.getjDlgReserva().setVisible(true);
        vista.getjDlgReserva().setSize(997, 652);
        vista.getjDlgReserva().setLocationRelativeTo(null);
        vista.getjDlgReserva().setTitle("Realizar reserva");
        vista.getjDlgReserva().setName("Realizar reserva");
        
        vista.getTxtCodigoEstudiante().setVisible(false);
        vista.getTxtCodigoSet().setVisible(false);
        limpiarCampos();
        bloquearCampos();
        cargarFechaActual();
    }
    
    public void crearModificarReserva() {
        
        if ("Realizar reserva".equals(vista.getjDlgReserva().getName())) {
            //INSERTAR
            if (validarDatos()) {
                
                Date fechaentra = vista.getFechaDeEntrada().getDate();
                java.sql.Date fechaentraSQL = new java.sql.Date(fechaentra.getTime());
                
                if (modelo.verificarFechaDisponible(fechaentraSQL, Integer.parseInt(vista.getTxtCodigoSet().getText())) != 0) {
                    JOptionPane.showMessageDialog(vista, "El set ya ha sido reservado para esta fecha");
                } else {
                    modelo.setRes_codestudiante(Integer.parseInt(vista.getTxtCodigoEstudiante().getText()));
                    modelo.setRes_codset(Integer.parseInt(vista.getTxtCodigoSet().getText()));
                    
                    Date fechareserva = vista.getFechaDeReserva().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fechareserva.getTime());
                    modelo.setRes_fechareser(fechaSQL);
                    
                    modelo.setRes_fechaentra(fechaentraSQL);
                    
                    modelo.setRes_especificacion(vista.getTxtAreaEspecificacion().getText());
                    
                    if (modelo.insertarReserva() == null) {
                        JOptionPane.showMessageDialog(vista, "La reservación se ha realizado satisfactoriamente");
                        vista.getjDlgReserva().setVisible(false);
                        cargarTablaReservas();
                    } else {
                        JOptionPane.showMessageDialog(vista, "No se pudo realizar la reserva");
                    }
                }
            }
        } else {
            //EDITAR

            if (validarDatos()) {
                
                Date fechaentra = vista.getFechaDeEntrada().getDate();
                java.sql.Date fechaentraSQL = new java.sql.Date(fechaentra.getTime());
                
                if (modelo.verificarFechaDisponible(fechaentraSQL, Integer.parseInt(vista.getTxtCodigoSet().getText())) != 0) {
                    
                    JOptionPane.showMessageDialog(vista, "Los datos de la reservación fueron modificados satisfactoriamente");
                    vista.getjDlgReserva().setVisible(false);
                    cargarTablaReservas();
                    
                } else {

                    //Reservacion
                    modelo.setRes_codigo(codigoReserva);
                    Date fechareserva = vista.getFechaDeReserva().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fechareserva.getTime());
                    
                    modelo.setRes_fechareser(fechaSQL);
                    modelo.setRes_fechaentra(fechaentraSQL);
                    modelo.setRes_especificacion(vista.getTxtAreaEspecificacion().getText());

                    //Estudiante
                    modelo.setRes_codestudiante(Integer.parseInt(vista.getTxtCodigoEstudiante().getText()));

                    //Set
                    modelo.setRes_codset(Integer.parseInt(vista.getTxtCodigoSet().getText()));
                    
                    if (modelo.modificarReserva() == null) {
                        JOptionPane.showMessageDialog(vista, "Los datos se modificaron satisfactoriamente");
                        vista.getjDlgReserva().setVisible(false);
                        cargarTablaReservas();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Los datos no pudieron ser modificados");
                    }
                }
            }
        }
    }
    
    int codigoReserva;
    
    public void cargarDatosReservaEnTXT() {
        int fila = vista.getTblReserva().getSelectedRow();
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {
            
            vista.getjDlgReserva().setVisible(true);
            vista.getjDlgReserva().setSize(997, 652);
            vista.getjDlgReserva().setLocationRelativeTo(null);
            vista.getjDlgReserva().setName("Modificar reservación");
            vista.getjDlgReserva().setTitle("Modificar reservación");
            
            bloquearCampos();
            vista.getFechaDeReserva().setEnabled(true);//Hago editable el jDate de la fecha en la que se realiza la reservacion
            vista.getTxtCodigoEstudiante().setVisible(false);
            vista.getTxtCodigoSet().setVisible(false);
            
            ModeloEstudiante modeloEstudiante = new ModeloEstudiante();
            ModeloSetGrab modeloSetGrab = new ModeloSetGrab();
            
            List<Reserva> reservas = modelo.listaReservasTabla();
            List<Estudiante> estudiantes = modeloEstudiante.listaEstudiantesTabla();
            List<SetGrabacion> setsgrabacion = modeloSetGrab.listaSetGrabTabla();
            
            reservas.stream().forEach(res -> {
                
                if (Integer.parseInt(vista.getTblReserva().getValueAt(fila, 0).toString()) == res.getRes_codigo()) {
                    
                    codigoReserva = Integer.parseInt(vista.getTblReserva().getValueAt(fila, 0).toString());
                    
                    estudiantes.stream().forEach(d -> {
                        
                        if (res.getRes_codestudiante() == d.getEst_codigo()) {
                            
                            setsgrabacion.stream().forEach(a -> {
                                
                                if (res.getRes_codset() == a.getSet_codigo()) {
                                    vista.getTxtCodigoEstudiante().setText(String.valueOf(d.getEst_codigo()));
                                    vista.getTxtCedula().setText(d.getPer_cedula());
                                    vista.getTxtNombreEstudiante().setText(d.getPer_primernom());
                                    vista.getTxtApellido().setText(d.getPer_apellidopater());
                                    vista.getTxtAreaEspecificacion().setText(res.getRes_especificacion());
                                    vista.getTxtCodigoSet().setText(String.valueOf(a.getSet_codigo()));
                                    vista.getTxtNombreSet().setText(a.getSet_nombre());
                                    vista.getFechaDeReserva().setDate(res.getRes_fechareser());
                                    vista.getFechaDeEntrada().setDate(res.getRes_fechaentra());
                                    
                                }
                            });
                        }
                    });
                }
            });
        }
    }
    
    public void eliminarAsignacion() {
        int fila = vista.getTblReserva().getSelectedRow();
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {
            
            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                
                int codigo;
                codigo = Integer.parseInt(vista.getTblReserva().getValueAt(fila, 0).toString());
                
                if (modelo.eliminarReserva(codigo) == null) {
                    JOptionPane.showMessageDialog(null, "El registro fue eliminado exitosamente");
                    cargarTablaReservas();//Actualizo la tabla con los datos
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
                
                DefaultTableModel tabla = (DefaultTableModel) vista.getTblReserva().getModel();

                //vista.getTablaconduccion().setAutoCreateRowSorter(true);
                filtrar = new TableRowSorter<>(tabla);
                vista.getTblReserva().setRowSorter(filtrar);
                
                try {
                    
                    filtrar.setRowFilter(RowFilter.regexFilter(vista.getTxtBuscar().getText())); //Se pasa como parametro el campo de donde se va a obtener la informacion y el (3) es la columna con la cual va a buscar las coincidencias
                } catch (Exception ex) {
                    System.out.println("Error: " + ex);
                }
            }
        };
        
        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //Todo sobre el registro de Estudiantes en el jDialog
    public void abrirjDialogEstudiante() {
        vista.getjDlgBuscarEstudiante().setVisible(true);
        vista.getjDlgBuscarEstudiante().setSize(685, 418);
        vista.getjDlgBuscarEstudiante().setLocationRelativeTo(vista.getBtnBuscarEstudiante());
        vista.getjDlgBuscarEstudiante().setTitle("Seleccione un estudiante");
        
        cargarRegistroDeEstudiantes();
        buscarEstudiante();
    }
    
    public void cargarRegistroDeEstudiantes() {
        
        ModeloEstudiante modeloEstudiante = new ModeloEstudiante();
        
        vista.getTblDlgEstudiante().setRowHeight(25);
        DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgEstudiante().getModel();
        estructuraTabla.setRowCount(0);
        
        List<Estudiante> listap = modeloEstudiante.listaEstudiantesTabla();
        
        Holder<Integer> i = new Holder<>(0);
        
        listap.stream().forEach(c -> {
            
            estructuraTabla.addRow(new Object[3]);
            
            vista.getTblDlgEstudiante().setValueAt(c.getPer_cedula(), i.value, 0);
            vista.getTblDlgEstudiante().setValueAt(c.getPer_primernom(), i.value, 1);
            vista.getTblDlgEstudiante().setValueAt(c.getPer_apellidopater(), i.value, 2);
            
            i.value++;
        });
    }
    
    public void cargarDatosEstudianteEnTXT() {
        int fila = vista.getTblDlgEstudiante().getSelectedRow();
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {
            
            ModeloEstudiante modeloEstudiante = new ModeloEstudiante();
            List<Estudiante> listap = modeloEstudiante.listaEstudiantesTabla();
            
            listap.stream().forEach(c -> {
                
                if (c.getPer_cedula().equals(vista.getTblDlgEstudiante().getValueAt(fila, 0).toString())) {
                    
                    vista.getTxtCodigoEstudiante().setText(String.valueOf(c.getEst_codigo()));
                    vista.getTxtCedula().setText(c.getPer_cedula());
                    vista.getTxtNombreEstudiante().setText(c.getPer_primernom());
                    vista.getTxtApellido().setText(c.getPer_apellidopater());
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
                vista.getTblDlgEstudiante().setRowHeight(25);
                DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgEstudiante().getModel();
                estructuraTabla.setRowCount(0);
                
                List<Estudiante> listap = modeloEstudiante.buscarEstudiante(vista.getTxtBuscarEstudiante().getText());
                
                Holder<Integer> i = new Holder<>(0);
                
                listap.stream().forEach(c -> {
                    
                    estructuraTabla.addRow(new Object[3]);
                    
                    vista.getTblDlgEstudiante().setValueAt(c.getPer_cedula(), i.value, 0);
                    vista.getTblDlgEstudiante().setValueAt(c.getPer_primernom(), i.value, 1);
                    vista.getTblDlgEstudiante().setValueAt(c.getPer_apellidopater(), i.value, 2);
                    i.value++;
                });
            }
        };
        
        vista.getTxtBuscarEstudiante().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //Todo sobre el registro de sets en el jDialog
    public void abrirjDialogSet() {
        vista.getjDlgBuscarSet().setVisible(true);
        vista.getjDlgBuscarSet().setSize(636, 398);
        vista.getjDlgBuscarSet().setLocationRelativeTo(vista.getBtnBuscarSet());
        vista.getjDlgBuscarSet().setTitle("Seleccione un set de grabación");
        
        cargarRegistroDeSet();
        buscarSet();
    }
    
    public void cargarRegistroDeSet() {
        
        ModeloSetGrab modeloSetgrab = new ModeloSetGrab();
        
        vista.getTblDlgjSet().setRowHeight(25);
        DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgjSet().getModel();
        estructuraTabla.setRowCount(0);
        
        List<SetGrabacion> listap = modeloSetgrab.listaSetGrabTabla();
        
        Holder<Integer> i = new Holder<>(0);
        
        listap.stream().forEach(c -> {
            
            estructuraTabla.addRow(new Object[2]);
            
            vista.getTblDlgjSet().setValueAt(c.getSet_codigo(), i.value, 0);
            vista.getTblDlgjSet().setValueAt(c.getSet_nombre(), i.value, 1);
            i.value++;
        });
    }
    
    public void cargarDatosSetEnTXT() {
        int fila = vista.getTblDlgjSet().getSelectedRow();
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {
            
            ModeloSetGrab modeloSetgrab = new ModeloSetGrab();
            List<SetGrabacion> listap = modeloSetgrab.listaSetGrabTabla();
            
            listap.stream().forEach(c -> {
                
                if (c.getSet_codigo() == Integer.parseInt(vista.getTblDlgjSet().getValueAt(fila, 0).toString())) {
                    
                    vista.getTxtCodigoSet().setText(String.valueOf(c.getSet_codigo()));
                    vista.getTxtNombreSet().setText(c.getSet_nombre());
                }
            });
            
            vista.getjDlgBuscarSet().dispose();
        }
    }
    
    public void buscarSet() {
        
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
                
                ModeloSetGrab modeloSetgrab = new ModeloSetGrab();
                
                DefaultTableModel tabla = (DefaultTableModel) vista.getTblDlgjSet().getModel();
                tabla.setNumRows(0);
                
                List<SetGrabacion> instrumento = modeloSetgrab.buscarSetGrabacion(vista.getTxtBuscarSet().getText());
                instrumento.stream().forEach(p -> {
                    String[] datos = {String.valueOf(p.getSet_codigo()), p.getSet_nombre(), p.getSet_ubicacion()};
                    tabla.addRow(datos);
                });
            }
        };
        
        vista.getTxtBuscarSet().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }
    
    public boolean validarDatos() {
        
        boolean validar = true;
        
        if (vista.getTxtCedula().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un estudiante");
            validar = false;
        }
        
        if (vista.getTxtNombreSet().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un set de grabación");
            validar = false;
        }
        
        if (vista.getFechaDeEntrada().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese la fecha para la cual desea apartar el set de grabación");
            validar = false;
        } else {
            
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");//Doy formato a la fecha
            String fechaReservaT = formato.format(vista.getFechaDeReserva().getDate()); //Paso de la fecha de RESERVACION de tipo de Date a String con el formato especificado
            String fechaEntradaT = formato.format(vista.getFechaDeEntrada().getDate()); //Paso de la fecha de ENTRADA de tipo de Date a String con el formato especificado

            Date fechaReser = null;
            Date fechaEntra = null;
            
            try {
                fechaReser = formato.parse(fechaReservaT); //Paso la fecha de RESERVACION de String a Date
                fechaEntra = formato.parse(fechaEntradaT); //Paso la fecha de ENTRADA de String a Date

            } catch (ParseException ex) {
                Logger.getLogger(ControladorReserva.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (fechaEntra.before(fechaReser) || fechaEntra.equals(fechaReser)) {
                JOptionPane.showMessageDialog(null, "La fecha para la que se desea reservar el set de grabación,\ndebe ser mayor a la fecha en que se registra la reservación");
                return false;
            }
        }
        
        if (vista.getFechaDeReserva().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese la fecha de apartado del set de grabación");
            validar = false;
        } else {
            
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");//Doy formato a la fecha
            String fechaReservaT = formato.format(vista.getFechaDeReserva().getDate()); //Paso de la fecha de RESERVACION de tipo de Date a String con el formato especificado
            String fechaEntradaT = formato.format(vista.getFechaDeEntrada().getDate()); //Paso de la fecha de ENTRADA de tipo de Date a String con el formato especificado

            Date fechaReser = null;
            Date fechaEntra = null;
            
            try {
                fechaReser = formato.parse(fechaReservaT); //Paso la fecha de RESERVACION de String a Date
                fechaEntra = formato.parse(fechaEntradaT); //Paso la fecha de ENTRADA de String a Date

            } catch (ParseException ex) {
                Logger.getLogger(ControladorReserva.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (fechaReser.after(fechaEntra)) {
                JOptionPane.showMessageDialog(null, "La fecha de reservación no debe superar a la fecha\npara la cual se desea apartar el set de grabación");
                return false;
            }
            
        }
        
        if (vista.getTxtAreaEspecificacion().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El area de la descripción no debe estar vacia");
            validar = false;
        }
        
        return validar;
    }
    
    public void bloquearCampos() {
        vista.getTxtCedula().setEditable(false);
        vista.getTxtNombreEstudiante().setEditable(false);
        vista.getTxtApellido().setEditable(false);
        vista.getTxtNombreSet().setEditable(false);
    }
    
    public void limpiarCampos() {
        vista.getTxtCedula().setText("");
        vista.getTxtNombreEstudiante().setText("");
        vista.getTxtApellido().setText("");
        vista.getTxtAreaEspecificacion().setText("");
        vista.getTxtNombreSet().setText("");
        vista.getFechaDeEntrada().setDate(null);
    }
    
    public void cargarFechaActual() {
        vista.getFechaDeReserva().setEnabled(false);

        //Seteo la fecha actual en el jCalendar
        Date fecha = new Date();
        vista.getFechaDeReserva().setDate(fecha);
    }
    
    public void botonCancelar() {
        vista.getjDlgReserva().setVisible(false);
    }
}
