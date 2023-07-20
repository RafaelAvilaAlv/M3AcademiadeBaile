package Controlador;

import Conexion.ConexionPG;
import Modelo.ModeloEvento;
import Modelo.Indumentaria;
import Modelo.ModeloIndumentaria;
import Modelo.Evento;
import Vista.VistaIndumentaria;
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

public class ControladorIndumentaria {

    ModeloIndumentaria modelo;
    VistaIndumentaria vista;

    VistaPrincipal p = new VistaPrincipal();

    public ControladorIndumentaria(ModeloIndumentaria modelo, VistaIndumentaria vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);

        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        vista.getjDlgInstrumento().setResizable(false);
        cargarTablaDeInstrumento();
    }

    public void iniciarControl() {
        vista.getBtnCrear().addActionListener(l -> abrirDialogCrear());
        vista.getBtnGuardar().addActionListener(l -> crearEditarInstrumento());
        vista.getBtnActualizar().addActionListener(l -> cargarTablaDeInstrumento());
        vista.getBtnModificar().addActionListener(l -> cargarDatosInstrumentoEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarInstrumento());
        vista.getBtnCargar().addActionListener(l -> abrirjDlgCargarSet());
        vista.getBtnCargarSetGrba().addActionListener(l -> cargarDatosSetGrabEnTXT());
        vista.getBtnCancelar().addActionListener(l -> botonCancelar());
        vista.getBtnImprimir().addActionListener(l -> imprimir());
        buscarInstrumento();
    }

    public void imprimir() {

        ConexionPG cpg = new ConexionPG();//Instanciar la conexion con esto abrimos la conexion a la BD
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/InstrumentoReporte.jasper"));

            //Hacer una vista previa
            //JasperPrint jp = JasperFillManager.fillReport(jr, null, cpg.getCon());//JasperFillManager.fillReport: Carga los datos de la BD.//JasperPrint: Hace la impresion del reporte. Puede ir 'null' si en el jasper no existen parametros caso contrario se envian los parametros necesarios
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("ImagenRuta", "src/imagenesJasper/instrumento.png"); //En donde esta 'titulo' tienen que ser igual al nombre que esta en el parametro del jasper

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cpg.getCon());//'parametros' es el Map recien creado que contiene los parametros que iran al jasper

            JasperViewer jv = new JasperViewer(jp, false); //Se pasa false para que no se cierre el sistema 
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrirDialogCrear() {
        vista.getjDlgInstrumento().setVisible(true);
        vista.getjDlgInstrumento().setSize(813, 488);
        vista.getjDlgInstrumento().setLocationRelativeTo(vista);
        vista.getjDlgInstrumento().setName("Crear nueva Indumentaria");
        vista.getjDlgInstrumento().setTitle("Crear nueva Indumentaria");

        vista.getTxtnombreset().setEnabled(false);
        vista.getTxtUbicacionSet().setEnabled(false);
        vista.getTxtTamanio().setEnabled(false);
        vista.getTxtCodigoSet().setVisible(false);

        //Limpiar los datos del jDialog
        limpiarDatos();
    }

    public void cargarTablaDeInstrumento() {
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblInstrumento().getModel();
        tabla.setNumRows(0);

        List<Indumentaria> instrumento = modelo.listaInstumentoTabla();
        instrumento.stream().forEach(p -> {
            String[] datos = {String.valueOf(p.getIns_codigo()), p.getIns_nombre(), p.getIns_marca(), p.getIns_tipo(), String.valueOf(p.getIns_valor())};
            tabla.addRow(datos);
        });
    }

    private void crearEditarInstrumento() {
        if ("Crear nueva Indumentaria".equals(vista.getjDlgInstrumento().getName())) {

            //INSERTAR
            if (validarDatos()) {
                ModeloIndumentaria ins = new ModeloIndumentaria();
                ins.setIns_nombre(vista.getTxtnombre().getText());
                ins.setIns_setcodigo(Integer.parseInt(vista.getTxtCodigoSet().getText()));
                ins.setIns_marca(vista.getTxtMarca().getText());
                ins.setIns_tipo(vista.getTxtTipo().getText());
                ins.setIns_valor(Double.parseDouble(vista.getjSpinnerValor().getValue().toString()));

                if (ins.crearInstrumento() == null) {
                    vista.getjDlgInstrumento().setVisible(false);
                    JOptionPane.showMessageDialog(vista, "Indumentaria creada satisfactoriamente");
                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo crear la Indumentaria");
                }
            }

        } else {
            //EDITAR
            if (validarDatos()) {
                ModeloIndumentaria inst = new ModeloIndumentaria();

                inst.setIns_codigo(codigoInstrumento);
                inst.setIns_setcodigo(Integer.parseInt(vista.getTxtCodigoSet().getText()));

                inst.setIns_nombre(vista.getTxtnombre().getText());
                inst.setIns_marca(vista.getTxtMarca().getText());
                inst.setIns_tipo(vista.getTxtTipo().getText());
                inst.setIns_valor(Double.parseDouble(vista.getjSpinnerValor().getValue().toString()));

                if (inst.modificarInstrumento() == null) {
                    vista.getjDlgInstrumento().setVisible(false);
                    JOptionPane.showMessageDialog(vista, "Instrumento modificado satisfactoriamente");

                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo modificar la Indumentaria");
                }
            }
        }

        cargarTablaDeInstrumento(); //Actualizo la tabla con los datos
    }

    public void eliminarInstrumento() {

        int fila = vista.getTblInstrumento().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigo;
                codigo = Integer.parseInt(vista.getTblInstrumento().getValueAt(fila, 0).toString());

                if (modelo.eliminarInstrumento(codigo) == null) {
                    JOptionPane.showMessageDialog(null, "La Indumentaria fue eliminado exitosamente");
                    cargarTablaDeInstrumento();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "La Indumentaria no pudo ser eliminado");
                }
            }
        }
    }

    int codigoInstrumento;//Variable que almacenara el codigo del instrumento traido de la BD

    public void cargarDatosInstrumentoEnTXT() {
        int fila = vista.getTblInstrumento().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            vista.getTxtCodigoSet().setVisible(false);
            ModeloEvento modeloSet = new ModeloEvento();

            vista.getjDlgInstrumento().setVisible(true);
            vista.getjDlgInstrumento().setSize(813, 488);
            vista.getjDlgInstrumento().setLocationRelativeTo(null);
            vista.getjDlgInstrumento().setName("Modificar Indumentaria");
            vista.getjDlgInstrumento().setTitle("Modificar Indumentaria");

            List<Indumentaria> listai = modelo.listaInstumentoTabla();
            List<Evento> listas = modeloSet.listaSetGrabTabla();

            listai.stream().forEach(instrumento -> {

                if (instrumento.getIns_codigo() == Integer.parseInt(vista.getTblInstrumento().getValueAt(fila, 0).toString())) {

                    listas.stream().forEach(s -> {

                        if (instrumento.getIns_setcodigo() == s.getSet_codigo()) {
                            codigoInstrumento = instrumento.getIns_codigo();
                            vista.getTxtnombre().setText(instrumento.getIns_nombre());
                            vista.getTxtMarca().setText(instrumento.getIns_marca());
                            vista.getTxtTipo().setText(instrumento.getIns_tipo());
                            vista.getjSpinnerValor().setValue(instrumento.getIns_valor());
                            vista.getTxtnombreset().setText(s.getSet_nombre());
                            vista.getTxtTamanio().setText(s.getSet_tamanio());
                            vista.getTxtUbicacionSet().setText(s.getSet_ubicacion());
                            vista.getTxtCodigoSet().setText(String.valueOf(s.getSet_codigo()));
                        }
                    });
                }
            });
        }
    }

    public void buscarInstrumento() {

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

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblInstrumento().getModel();
                tabla.setNumRows(0);

                List<Indumentaria> instrumento = modelo.buscarInstrumento(vista.getTxtBuscar().getText());
                instrumento.stream().forEach(p -> {
                    String[] datos = {String.valueOf(p.getIns_codigo()), p.getIns_nombre(), p.getIns_marca(), p.getIns_tipo(), String.valueOf(p.getIns_valor())};
                    tabla.addRow(datos);
                });
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //INFORMACION SOBRE EL SET DE GRABACION
    public void abrirjDlgCargarSet() {
        vista.getjDlgCargarSet().setVisible(true);
        vista.getjDlgCargarSet().setSize(701, 436);
        vista.getjDlgCargarSet().setLocationRelativeTo(vista);
        vista.getjDlgCargarSet().setTitle("Seleccionar un evento");

        cargarDatosDeSetGrab();
        buscarSetDeGrabacion();

    }

    public void cargarDatosDeSetGrab() {

        ModeloEvento modeloCliente = new ModeloEvento();
        vista.getTblSet().setRowHeight(25);
        DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblSet().getModel();
        estructuraTabla.setRowCount(0);

        List<Evento> listap = modeloCliente.listaSetGrabTabla();

        Holder<Integer> i = new Holder<>(0);

        listap.stream().forEach(setgr -> {

            estructuraTabla.addRow(new Object[4]);

            vista.getTblSet().setValueAt(setgr.getSet_codigo(), i.value, 0);
            vista.getTblSet().setValueAt(setgr.getSet_nombre(), i.value, 1);
            vista.getTblSet().setValueAt(setgr.getSet_ubicacion(), i.value, 2);
            vista.getTblSet().setValueAt(setgr.getSet_tamanio(), i.value, 3);

            i.value++;
        });
    }

    public void cargarDatosSetGrabEnTXT() {
        int fila = vista.getTblSet().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            ModeloEvento modeloSet = new ModeloEvento();
            List<Evento> sets = modeloSet.listaSetGrabTabla();
            sets.stream().forEach(s -> {

                if (s.getSet_codigo() == Integer.parseInt(vista.getTblSet().getValueAt(fila, 0).toString())) {

                    vista.getTxtCodigoSet().setText(String.valueOf(s.getSet_codigo()));
                    vista.getTxtnombreset().setText(s.getSet_nombre());
                    vista.getTxtTamanio().setText(s.getSet_tamanio());
                    vista.getTxtUbicacionSet().setText(s.getSet_ubicacion());
                    vista.getjDlgCargarSet().dispose();//Cierro la ventana luego de cargar los datos
                }
            });
        }
    }

    public void buscarSetDeGrabacion() {

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

                ModeloEvento modeloSet = new ModeloEvento();

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblSet().getModel();
                tabla.setNumRows(0);

                List<Evento> set = modeloSet.buscarSetGrabacion(vista.getTxtBuscarSet().getText());
                set.stream().forEach(p -> {
                    String[] datos = {String.valueOf(p.getSet_codigo()), p.getSet_nombre(), p.getSet_ubicacion(), p.getSet_tamanio()};
                    tabla.addRow(datos);
                });
            }
        };

        vista.getTxtBuscarSet().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    public boolean validarDatos() {
        Validaciones mivalidacion = new Validaciones();

        boolean validar = true;

        if (vista.getTxtnombre().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre");
            validar = false;
        } else {
            if (!mivalidacion.validarTextoConEspacio(vista.getTxtnombre().getText())) {
                JOptionPane.showMessageDialog(null, "Nombre incorrecto");
                validar = false;
            }
        }

        if (vista.getTxtMarca().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la marca");
            validar = false;
        } else {
            if (!mivalidacion.validarTextoConEspacio(vista.getTxtMarca().getText())) {
                JOptionPane.showMessageDialog(null, "Marca incorrecta");
                validar = false;
            }
        }

        if (vista.getTxtTipo().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el tipo de Indumentaria");
            validar = false;
        } else {
            if (!mivalidacion.validarTextoConEspacio(vista.getTxtTipo().getText())) {
                JOptionPane.showMessageDialog(null, "Tipo de Indumentaria incorrecto");
                validar = false;
            }
        }

        if (vista.getTxtnombreset().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un Evento");
            validar = false;
        }

        return validar;
    }

    public void limpiarDatos() {

        vista.getTxtnombre().setText("");
        vista.getTxtMarca().setText("");
        vista.getTxtTipo().setText("");
        vista.getTxtnombreset().setText("");
        vista.getTxtTamanio().setText("");
        vista.getTxtUbicacionSet().setText("");
        vista.getjSpinnerValor().setValue(0);
    }

    public void botonCancelar() {
        vista.getjDlgInstrumento().setVisible(false);
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
