package Controlador;

import Conexion.ConexionPG;
import Modelo.Horario;
import Modelo.ModeloHorario;
import Vista.VistaHorario;
import Vista.VistaPrincipal;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControladorHorario {

    ModeloHorario modelo;
    VistaHorario vista;

    VistaPrincipal p = new VistaPrincipal();

    public ControladorHorario(ModeloHorario modelo, VistaHorario vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);

        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        cargarTablaDeHorario();
    }

    public void iniciarControl() {
        vista.getBtnCrear().addActionListener(l -> abrirDialogCrear());
        vista.getBtnGuardar().addActionListener(l -> crearEditarCurso());
        vista.getBtnActualizar().addActionListener(l -> cargarTablaDeHorario());
        vista.getBtnModificar().addActionListener(l -> cargarDatosHorarioEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarHorario());
        vista.getBtnCancelar().addActionListener(l -> botonEliminar());
        vista.getBtnImprimir().addActionListener(l -> imprimir());
        buscarHorario();
    }

    public void abrirDialogCrear() {
        vista.getjDlgHorario().setName("Crear nuevo horario");
        vista.getjDlgHorario().setSize(615, 386);
        vista.getjDlgHorario().setTitle("Crear nuevo horario");
        vista.getjDlgHorario().setVisible(true);
        vista.getjDlgHorario().setLocationRelativeTo(null);

        //Limpiar los datos del jDialog
        limpiarDatos();
    }

    public void cargarTablaDeHorario() {
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblHorario().getModel();
        tabla.setNumRows(0);

        List<Horario> horarios = modelo.listaHorarioTabla();
        horarios.stream().forEach(p -> {
            String[] datos = {String.valueOf(p.getHor_codigo()), p.getHor_dia(), p.getHor_horaIni(), p.getHor_horaFin()};
            tabla.addRow(datos);
        });
    }

    public void imprimir() {

        ConexionPG cpg = new ConexionPG();//Instanciar la conexion con esto abrimos la conexion a la BD
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/HorarioReporte.jasper"));

            //Hacer una vista previa
            //JasperPrint jp = JasperFillManager.fillReport(jr, null, cpg.getCon());//JasperFillManager.fillReport: Carga los datos de la BD.//JasperPrint: Hace la impresion del reporte. Puede ir 'null' si en el jasper no existen parametros caso contrario se envian los parametros necesarios
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("ImagenRuta", "src/imagenesJasper/horario.png"); //En donde esta 'titulo' tienen que ser igual al nombre que esta en el parametro del jasper

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cpg.getCon());//'parametros' es el Map recien creado que contiene los parametros que iran al jasper

            JasperViewer jv = new JasperViewer(jp, false); //Se pasa false para que no se cierre el sistema 
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearEditarCurso() {
        if ("Crear nuevo horario".equals(vista.getjDlgHorario().getName())) {

            //INSERTAR
            if (validarDatos()) {

                modelo.setHor_dia(vista.getComboDia().getSelectedItem().toString());
                modelo.setHor_horaIni(vista.getComboHoraInicio().getSelectedItem().toString() + ":" + vista.getComboMinutosInicio().getSelectedItem().toString());
                modelo.setHor_horaFin(vista.getComboHoraFin().getSelectedItem().toString() + ":" + vista.getComboMinutosFin().getSelectedItem().toString());

                if (modelo.crearHorario() == null) {
                    vista.getjDlgHorario().setVisible(false);
                    JOptionPane.showMessageDialog(vista, "Horario creado satisfactoriamente");

                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo crear el horario");
                }
            }

        } else {

            //EDITAR
            if (validarDatos()) {

                modelo.setHor_codigo(codigoHorario);
                modelo.setHor_dia(vista.getComboDia().getSelectedItem().toString());
                modelo.setHor_horaIni(vista.getComboHoraInicio().getSelectedItem().toString() + ":" + vista.getComboMinutosInicio().getSelectedItem().toString());
                modelo.setHor_horaFin(vista.getComboHoraFin().getSelectedItem().toString() + ":" + vista.getComboMinutosFin().getSelectedItem().toString());

                if (modelo.modificarHorario() == null) {
                    vista.getjDlgHorario().setVisible(false);
                    JOptionPane.showMessageDialog(vista, "Horario modificado satisfactoriamente");

                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo modificar el horario");
                }
            }
        }

        cargarTablaDeHorario(); //Actualizo la tabla con los datos
    }

    public void eliminarHorario() {

        int fila = vista.getTblHorario().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigo;
                codigo = Integer.parseInt(vista.getTblHorario().getValueAt(fila, 0).toString());

                if (modelo.eliminarHorario(codigo) == null) {
                    JOptionPane.showMessageDialog(null, "El horario fue eliminado exitosamente");
                    cargarTablaDeHorario();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "El horario no pudo ser eliminado");
                }
            }
        }
    }

    int codigoHorario;//Variable que almacenara el codigo del horario traido de la BD

    public void cargarDatosHorarioEnTXT() {
        int fila = vista.getTblHorario().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            vista.getjDlgHorario().setName("Modificar horario");
            vista.getjDlgHorario().setLocationRelativeTo(null);
            vista.getjDlgHorario().setSize(640, 355);
            vista.getjDlgHorario().setTitle("Modificar horario");
            vista.getjDlgHorario().setVisible(true);

            List<Horario> listap = modelo.listaHorarioTabla();

            listap.stream().forEach(h -> {

                if (h.getHor_codigo() == Integer.parseInt(vista.getTblHorario().getValueAt(fila, 0).toString())) {

                    codigoHorario = h.getHor_codigo();
                    for (int j = 0; j < vista.getComboDia().getItemCount(); j++) {
                        if (vista.getComboDia().getItemAt(j).equalsIgnoreCase(h.getHor_dia())) {
                            vista.getComboDia().setSelectedIndex(j);
                            j = vista.getComboDia().getItemCount();
                        }
                    }

                    for (int j = 0; j < vista.getComboHoraInicio().getItemCount(); j++) {
                        if (vista.getComboHoraInicio().getItemAt(j).equalsIgnoreCase(h.getHor_horaIni().substring(0, 2))) {
                            vista.getComboHoraInicio().setSelectedIndex(j);
                            j = vista.getComboHoraInicio().getItemCount();
                        }
                    }

                    for (int j = 0; j < vista.getComboMinutosInicio().getItemCount(); j++) {
                        if (vista.getComboMinutosInicio().getItemAt(j).equalsIgnoreCase(h.getHor_horaIni().substring(3, 5))) {
                            vista.getComboMinutosInicio().setSelectedIndex(j);
                            j = vista.getComboMinutosInicio().getItemCount();
                        }
                    }

                    for (int j = 0; j < vista.getComboHoraFin().getItemCount(); j++) {
                        if (vista.getComboHoraFin().getItemAt(j).equalsIgnoreCase(h.getHor_horaFin().substring(0, 2))) {
                            vista.getComboHoraFin().setSelectedIndex(j);
                            j = vista.getComboHoraFin().getItemCount();
                        }
                    }

                    for (int j = 0; j < vista.getComboMinutosFin().getItemCount(); j++) {
                        if (vista.getComboMinutosFin().getItemAt(j).equalsIgnoreCase(h.getHor_horaFin().substring(3, 5))) {
                            vista.getComboMinutosFin().setSelectedIndex(j);
                            j = vista.getComboMinutosFin().getItemCount();
                        }
                    }
                }
            });
        }
    }

    public void buscarHorario() {

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

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblHorario().getModel();

                //vista.getTablaconduccion().setAutoCreateRowSorter(true);
                filtrar = new TableRowSorter<>(tabla);
                vista.getTblHorario().setRowSorter(filtrar);

                try {

                    filtrar.setRowFilter(RowFilter.regexFilter(vista.getTxtBuscar().getText())); //Se pasa como parametro el campo de donde se va a obtener la informacion y el (3) es la columna con la cual va a buscar las coincidencias
                } catch (Exception ex) {
                    System.out.println("Error: " + ex);
                }
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    public boolean validarDatos() {

        boolean validar = true;

        if (vista.getComboDia().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un dia de la semana");
            validar = false;
        }

        if (vista.getComboHoraInicio().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una hora de inicio");
            validar = false;
        }

        if (vista.getComboMinutosInicio().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione los minutos de inicio");
            validar = false;
        }

        if (vista.getComboHoraFin().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una hora de fin");
            validar = false;
        }

        if (vista.getComboMinutosFin().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione los minutos de fin");
            validar = false;
        }

        return validar;
    }

    public void limpiarDatos() {
        vista.getComboDia().setSelectedIndex(0);
        vista.getComboHoraInicio().setSelectedIndex(0);
        vista.getComboMinutosInicio().setSelectedIndex(0);
        vista.getComboHoraFin().setSelectedIndex(0);
        vista.getComboMinutosFin().setSelectedIndex(0);
    }

    public void botonEliminar() {
        vista.getjDlgHorario().setVisible(false);
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
