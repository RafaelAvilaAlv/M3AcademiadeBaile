package Controlador;

import Modelo.Aula;
import Conexion.ConexionPG;
import Modelo.ModeloAula;
import Vista.VistaAula;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControladorAula {

    ModeloAula modelo;
    VistaAula vista;

    VistaPrincipal p = new VistaPrincipal();

    public ControladorAula(ModeloAula modelo, VistaAula vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        cargarTablaDeAula();
    }

    public void iniciarControl() {
        vista.getBtnCrear().addActionListener(l -> abrirDialogCrear());
        vista.getBtnGuardar().addActionListener(l -> crearEditarCurso());
        vista.getBtnActualizar().addActionListener(l -> cargarTablaDeAula());
        vista.getBtnModificar().addActionListener(l -> cargarDatosCursoEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarCurso());
        vista.getBtnCancelar().addActionListener(l-> botonCancelar());
        vista.getBtnImprimir().addActionListener(l-> imprimir());
        buscarAula();
    }

    public void abrirDialogCrear() {
        vista.getjDlgAula().setVisible(true);
        vista.getjDlgAula().setSize(680, 498);
        vista.getjDlgAula().setLocationRelativeTo(vista);
        vista.getjDlgAula().setName("Crear nueva aula");
        vista.getjDlgAula().setTitle("Crear nueva aula");

        //Limpiar los datos del jDialog
        limpiarDatos();
    }
    public void imprimir() {

        ConexionPG cpg = new ConexionPG();//Instanciar la conexion con esto abrimos la conexion a la BD
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/AulaReporte.jasper"));

            //Hacer una vista previa
            //JasperPrint jp = JasperFillManager.fillReport(jr, null, cpg.getCon());//JasperFillManager.fillReport: Carga los datos de la BD.//JasperPrint: Hace la impresion del reporte. Puede ir 'null' si en el jasper no existen parametros caso contrario se envian los parametros necesarios
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("ImagenRuta", "src/imagenesJasper/aula.png"); //En donde esta 'titulo' tienen que ser igual al nombre que esta en el parametro del jasper

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cpg.getCon());//'parametros' es el Map recien creado que contiene los parametros que iran al jasper

            JasperViewer jv = new JasperViewer(jp, false); //Se pasa false para que no se cierre el sistema 
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarTablaDeAula() {
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblAula().getModel();
        tabla.setNumRows(0);

        List<Aula> aulas = modelo.listaAulaTabla();
        aulas.stream().forEach(p -> {
            String[] datos = {String.valueOf(p.getAul_codigo()), p.getAul_nombre(), String.valueOf(p.getAul_capacidad()), p.getAul_ubicacion()};
            tabla.addRow(datos);
        });
    }

    private void crearEditarCurso() {
        if ("Crear nueva aula".equals(vista.getjDlgAula().getName())) {

            //INSERTAR
            if (validarDatos()) {
                ModeloAula aula = new ModeloAula();

                aula.setAul_nombre(vista.getTxtNombre().getText());
                aula.setAul_ubicacion(vista.getTxtUbicacion().getText());
                aula.setAul_descripcion(vista.getDescripcion().getText());
                aula.setAul_capacidad(Integer.parseInt(vista.getSpinnerCapacidad().getValue().toString()));

                if (aula.crearAula() == null) {
                    vista.getjDlgAula().setVisible(false);
                    JOptionPane.showMessageDialog(vista, "Aula creada satisfactoriamente");
                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo crear la nueva aula");
                }
            }

        } else {

            //EDITAR
            if (validarDatos()) {
                ModeloAula aula = new ModeloAula();

                aula.setAul_codigo(codigoAula);
                aula.setAul_nombre(vista.getTxtNombre().getText());
                aula.setAul_ubicacion(vista.getTxtUbicacion().getText());
                aula.setAul_descripcion(vista.getDescripcion().getText());
                aula.setAul_capacidad(Integer.parseInt(vista.getSpinnerCapacidad().getValue().toString()));

                if (aula.modificarAula() == null) {
                    vista.getjDlgAula().setVisible(false);
                    JOptionPane.showMessageDialog(vista, "Aula modificada satisfactoriamente");
                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo modificar el aula");
                }
            }
        }

        cargarTablaDeAula(); //Actualizo la tabla con los datos
    }

    public void eliminarCurso() {

        int fila = vista.getTblAula().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigo;
                codigo = Integer.parseInt(vista.getTblAula().getValueAt(fila, 0).toString());

                if (modelo.eliminarAula(codigo) == null) {
                    JOptionPane.showMessageDialog(null, "El aula fue eliminada exitosamente");
                    cargarTablaDeAula();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "Error: El aula no pudo ser eliminada");
                }
            }
        }
    }

    int codigoAula;//Variable que almacenara el codigo del aula traido de la BD

    public void cargarDatosCursoEnTXT() {
        int fila = vista.getTblAula().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            //Abrir jDialog de campos de Aula
            vista.getjDlgAula().setVisible(true);
            vista.getjDlgAula().setSize(680, 498);
            vista.getjDlgAula().setLocationRelativeTo(null);
            vista.getjDlgAula().setName("Modificar aula");
            vista.getjDlgAula().setTitle("Modificar aula");

            List<Aula> listap = modelo.listaAulaTabla();

            listap.stream().forEach(aula -> {

                if (aula.getAul_codigo() == Integer.parseInt(vista.getTblAula().getValueAt(fila, 0).toString())) {
                    codigoAula = aula.getAul_codigo();
                    vista.getTxtNombre().setText(aula.getAul_nombre());
                    vista.getTxtUbicacion().setText(aula.getAul_ubicacion());
                    vista.getDescripcion().setText(aula.getAul_descripcion());
                    vista.getSpinnerCapacidad().setValue(aula.getAul_capacidad());
                }
            });
        }
    }

    public void buscarAula() {

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

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblAula().getModel();
                tabla.setNumRows(0);

                List<Aula> aulas = modelo.buscarAula(vista.getTxtBuscar().getText());
                aulas.stream().forEach(p -> {
                    String[] datos = {String.valueOf(p.getAul_codigo()), p.getAul_nombre(), String.valueOf(p.getAul_capacidad()), p.getAul_ubicacion()};
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
            JOptionPane.showMessageDialog(null, "Ingrese la ubicacion");
            validar = false;
        } else {
            if (!mivalidacion.validarDireccion(vista.getTxtUbicacion().getText())) {
                JOptionPane.showMessageDialog(null, "Ubicacion incorrecta");
                validar = false;
            }
        }

        if (vista.getDescripcion().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No puede estar vacio el campo de la descripcion");
            validar = false;
        }

        return validar;
    }

    public void limpiarDatos() {
        vista.getTxtNombre().setText("");
        vista.getTxtUbicacion().setText("");
        vista.getDescripcion().setText("");
        vista.getSpinnerCapacidad().setValue(1);
    }

    public void botonCancelar() {
        vista.getjDlgAula().setVisible(false);
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
