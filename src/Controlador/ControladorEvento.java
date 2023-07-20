package Controlador;

import Conexion.ConexionPG;
import Modelo.ModeloEvento;
import Modelo.Evento;
import Vista.VistaPrincipal;
import Vista.VistaEvento;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControladorEvento {

    ModeloEvento modelo;
    VistaEvento vista;

    VistaPrincipal p = new VistaPrincipal();

    public ControladorEvento(ModeloEvento modelo, VistaEvento vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);

        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        vista.getjDlgSetGrab().setResizable(false);
        cargarTablaDeSetGrab();
    }

    public void iniciarControl() {
        vista.getBtnCrear().addActionListener(l -> abrirDialogCrear());
        vista.getBtnGuardar().addActionListener(l -> crearEditarSetGrab());
        vista.getBtnActualizar().addActionListener(l -> cargarTablaDeSetGrab());
        vista.getBtnModificar().addActionListener(l -> cargarDatosInstrumentoEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarInstrumento());
        vista.getBtnCancelar().addActionListener(l -> botonEliminar());
        vista.getBtnImprimir().addActionListener(l -> imprimir());
        buscarSetDeGrabacion();
    }
    public void imprimir() {

        ConexionPG cpg = new ConexionPG();//Instanciar la conexion con esto abrimos la conexion a la BD
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/SetReporte.jasper"));

            //Hacer una vista previa
            //JasperPrint jp = JasperFillManager.fillReport(jr, null, cpg.getCon());//JasperFillManager.fillReport: Carga los datos de la BD.//JasperPrint: Hace la impresion del reporte. Puede ir 'null' si en el jasper no existen parametros caso contrario se envian los parametros necesarios
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("ImagenRuta", "src/imagenesJasper/set.png"); //En donde esta 'titulo' tienen que ser igual al nombre que esta en el parametro del jasper

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cpg.getCon());//'parametros' es el Map recien creado que contiene los parametros que iran al jasper

            JasperViewer jv = new JasperViewer(jp, false); //Se pasa false para que no se cierre el sistema 
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void abrirDialogCrear() {
        vista.getjDlgSetGrab().setName("Crear nuevo Set de grabacion");        
        vista.getjDlgSetGrab().setSize(485, 411);
        vista.getjDlgSetGrab().setTitle("Crear nuevo set de grabacion");
        vista.getjDlgSetGrab().setVisible(true);
        vista.getjDlgSetGrab().setLocationRelativeTo(null);

        //Limpiar los datos del jDialog
        limpiarDatos();
    }

    public void cargarTablaDeSetGrab() {
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblSetGrab().getModel();
        tabla.setNumRows(0);

        List<Evento> instrumento = modelo.listaSetGrabTabla();
        instrumento.stream().forEach(p -> {
            String[] datos = {String.valueOf(p.getSet_codigo()), p.getSet_nombre(), p.getSet_ubicacion(), p.getSet_tamanio()};
            tabla.addRow(datos);
        });
    }

    private void crearEditarSetGrab() {
        if ("Crear nuevo Set de grabacion".equals(vista.getjDlgSetGrab().getName())) {
            //INSERTAR
            if (validarDatos()) {
                ModeloEvento set = new ModeloEvento();
                set.setSet_codigo(codigoSetGrab);
                set.setSet_nombre(vista.getTxtNombre().getText());
                set.setSet_tamanio(vista.getComboTamanio().getSelectedItem().toString());
                set.setSet_ubicacion(vista.getTxtUbicacion().getText());

                if (set.crearSetGrabacion() == null) {
                    vista.getjDlgSetGrab().setVisible(false);
                    JOptionPane.showMessageDialog(vista, "Set de grabacion creado satisfactoriamente");
                    cargarTablaDeSetGrab();
                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo crear el set de grabacion");
                }
            }

        } else {

            //EDITAR
            if (validarDatos()) {
                ModeloEvento set = new ModeloEvento();

                set.setSet_codigo(codigoSetGrab);
                set.setSet_nombre(vista.getTxtNombre().getText());
                set.setSet_tamanio(vista.getComboTamanio().getSelectedItem().toString());
                set.setSet_ubicacion(vista.getTxtUbicacion().getText());

                if (set.modificarSetGrabacion() == null) {
                    vista.getjDlgSetGrab().setVisible(false);
                    JOptionPane.showMessageDialog(vista, "Set de grabacion modificado satisfactoriamente");
                    cargarTablaDeSetGrab();
                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo modificar el set de grabacion");
                }
            }
        }

        cargarTablaDeSetGrab(); //Actualizo la tabla con los datos
    }
//

    public void eliminarInstrumento() {

        int fila = vista.getTblSetGrab().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigo;
                codigo = Integer.parseInt(vista.getTblSetGrab().getValueAt(fila, 0).toString());

                if (modelo.eliminarSetGrabacion(codigo) == null) {
                    JOptionPane.showMessageDialog(null, "El set de grabacion fue eliminado exitosamente");
                    cargarTablaDeSetGrab();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "Error: El set de grabacion no pudo ser eliminado");
                }
            }
        }
    }

    int codigoSetGrab;//Variable que almacenara el codigo del curso traido de la BD

    public void cargarDatosInstrumentoEnTXT() {
        int fila = vista.getTblSetGrab().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            vista.getjDlgSetGrab().setName("Modificar Set de grabacion");
            vista.getjDlgSetGrab().setLocationRelativeTo(null);
            vista.getjDlgSetGrab().setSize(464, 400);
            vista.getjDlgSetGrab().setTitle("Modificar set de grabacion");
            vista.getjDlgSetGrab().setVisible(true);

            List<Evento> listap = modelo.listaSetGrabTabla();

            listap.stream().forEach(setg -> {

                if (setg.getSet_codigo() == Integer.parseInt(vista.getTblSetGrab().getValueAt(fila, 0).toString())) {
                    codigoSetGrab = setg.getSet_codigo();

                    vista.getTxtNombre().setText(setg.getSet_nombre());
                    vista.getTxtUbicacion().setText(setg.getSet_ubicacion());

                    for (int j = 0; j < vista.getComboTamanio().getItemCount(); j++) {
                        if (vista.getComboTamanio().getItemAt(j).equalsIgnoreCase(setg.getSet_tamanio())) {
                            vista.getComboTamanio().setSelectedIndex(j);
                            j = vista.getComboTamanio().getItemCount();
                        }
                    }
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

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblSetGrab().getModel();
                tabla.setNumRows(0);

                List<Evento> instrumento = modelo.buscarSetGrabacion(vista.getTxtBuscar().getText());
                instrumento.stream().forEach(p -> {
                    String[] datos = {String.valueOf(p.getSet_codigo()), p.getSet_nombre(), p.getSet_ubicacion(), p.getSet_tamanio()};
                    tabla.addRow(datos);
                });
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    public boolean validarDatos() {
        Validaciones mivalidacion = new Validaciones();

        boolean validar = true;

        if (vista.getTxtNombre().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre");
            validar = false;
        } else {
            if (!mivalidacion.validarTextoConEspacio(vista.getTxtNombre().getText())) {
                JOptionPane.showMessageDialog(null, "Nombre incorrecto");
                validar = false;
            }
        }

        if (vista.getTxtUbicacion().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese una ubicacion");
            validar = false;
        } else {
            if (!mivalidacion.validarTextoConEspacio(vista.getTxtUbicacion().getText())) {
                JOptionPane.showMessageDialog(null, "Ubicacion incorrecta");
                validar = false;
            }
        }

        if (vista.getComboTamanio().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un tamaño");
            validar = false;
        }

        return validar;
    }

    public void limpiarDatos() {

        vista.getTxtNombre().setText("");
        vista.getTxtUbicacion().setText("");
        vista.getComboTamanio().setSelectedIndex(0);
    }
     public void botonEliminar() {
        vista.getjDlgSetGrab().setVisible(false);
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
