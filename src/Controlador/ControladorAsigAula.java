/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
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

/**
 *
 * @author Usuario
 */
public class ControladorAsigAula {

    ModeloAsigAula modelo;
    VistaAsigAula vista;
    VistaPrincipal p = new VistaPrincipal();
    static boolean verificarAsignacion;

    public ControladorAsigAula(ModeloAsigAula modelo, VistaAsigAula vista) {
        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
        cargarTablaAsignaciones();
    }

    public void iniciarControl() {

        vista.getBtnAsignar().addActionListener(l -> abrirjDlgAsigAula());
        vista.getBtnBuscarCurso().addActionListener(l -> abrirjDialogCurso());
        vista.getBtnCargarCurso().addActionListener(l -> cargarDatosCursoEnTXT());
        vista.getBtnBuscarAula().addActionListener(l -> abrirjDialogCargarAula());
        vista.getBtnCargarAula().addActionListener(l -> cargarDatosAulaEnTXT());
        vista.getBtnGuardar().addActionListener(l -> crearModificarAsignarAula());
        vista.getBtnModificar().addActionListener(l -> cargarDatosAsignarAulaEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarAsignacion());
        vista.getBtnActualizar().addActionListener(l -> cargarTablaAsignaciones());
        vista.getBtnCancelar().addActionListener(l -> botonEliminar());
        buscarRegistros();
    }

    public void cargarTablaAsignaciones() {

        System.out.println("Entra a");
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblAsigAula().getModel();
        tabla.setNumRows(0);

        ModeloCurso modeloCurso = new ModeloCurso();
        ModeloAula modeloAula = new ModeloAula();

        List<AsigAula> asignaciones = modelo.listaAsignacionesTabla();
        List<Curso> cursos = modeloCurso.listaCursoTabla();
        List<Aula> aulas = modeloAula.listaAulaTabla();

        asignaciones.stream().forEach(as -> {

            cursos.stream().forEach(d -> {

                if (as.getAsia_codcurso() == d.getCur_codigo()) {

                    aulas.stream().forEach(a -> {

                        if (as.getAsia_codaula() == a.getAul_codigo()) {

                            String[] datos = {String.valueOf(as.getAsia_codigo()), d.getCur_nombre(), a.getAul_nombre(), String.valueOf(as.getAsia_fecha().toString())};
                            tabla.addRow(datos);

                        }
                    });
                }
            });
        });
    }

    public void abrirjDlgAsigAula() {

        vista.getjDlgAsigAula().setVisible(true);
        vista.getjDlgAsigAula().setSize(858, 430);
        vista.getjDlgAsigAula().setLocationRelativeTo(null);
        vista.getjDlgAsigAula().setTitle("Asignar Aula");
        vista.getjDlgAsigAula().setName("Asignar Aula");
        vista.getTxtCodigocurso().setVisible(false);

        limpiarlabels();
        cargarFechaActual();
        bloquearTxtAsi();
    }

    public void abrirjDialogCargarAula() {

        vista.getjDlgCargarAula().setSize(742, 414);
        vista.getjDlgCargarAula().setTitle("Seleccione una aula");
        vista.getjDlgCargarAula().setVisible(true);
        vista.getjDlgCargarAula().setLocationRelativeTo(null);

        buscarAula();
        cargarRegistroDeAula();
    }

    public void crearModificarAsignarAula() {
        if ("Asignar Aula".equals(vista.getjDlgAsigAula().getName())) {

            verificarAsignacion = false;

            //INSERTAR
            if (validarDatos()) {

                List<AsigAula> asignaciones = modelo.listaAsignacionesTabla();

                asignaciones.stream().forEach(as -> {

                    if (as.getAsia_codaula() == Integer.parseInt(vista.getTxtCodigoAula().getText())) {
                        if (as.getAsia_codcurso() == Integer.parseInt(vista.getTxtCodigocurso().getText())) {
                            verificarAsignacion = true;
                        }
                    }
                });

                if (verificarAsignacion) {
                    JOptionPane.showMessageDialog(vista, "La asignacion ya existe");
                } else {

                    modelo.setAsia_codaula(Integer.parseInt(vista.getTxtCodigoAula().getText()));
                    modelo.setAsia_codcurso(Integer.parseInt(vista.getTxtCodigocurso().getText()));

                    Date fecha = vista.getFechaDeAsignacion().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    modelo.setAsia_fecha(fechaSQL);

                    if (modelo.asignarAula() == null) {
                        JOptionPane.showMessageDialog(vista, "La asignacion se ha realizado satisfactoriamente");
                        vista.getjDlgAsigAula().setVisible(false);
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

                List<AsigAula> asignaciones = modelo.listaAsignacionesTabla();

                asignaciones.stream().forEach(as -> {

                    if (as.getAsia_codaula() == Integer.parseInt(vista.getTxtCodigoAula().getText())) {
                        if (as.getAsia_codcurso() == Integer.parseInt(vista.getTxtCodigocurso().getText())) {
                            verificarAsignacion = true;
                        }
                    }
                });

                //Si el docente ya imparte la materia solo se modificara la fecha
                if (verificarAsignacion) {

                    modelo.setAsia_codigo(codigoAsigAula);

                    Date fecha = vista.getFechaDeAsignacion().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    modelo.setAsia_fecha(fechaSQL);

                    if (modelo.modificarAsignacionFecha() == null) {
                        JOptionPane.showMessageDialog(vista, "Los datos fueron modificados satisfactoriamente");
                        vista.getjDlgAsigAula().setVisible(false);
                        cargarTablaAsignaciones();
                    }

                } else {

                    modelo.setAsia_codigo(codigoAsigAula);
                    modelo.setAsia_codaula(Integer.parseInt(vista.getTxtCodigoAula().getText()));
                    modelo.setAsia_codcurso(Integer.parseInt(vista.getTxtCodigocurso().getText()));

                    Date fecha = vista.getFechaDeAsignacion().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    modelo.setAsia_fecha(fechaSQL);

                    if (modelo.modificarAsignacionCompleto() == null) {
                        JOptionPane.showMessageDialog(vista, "Los datos fueron modificados satisfactoriamente");
                        vista.getjDlgAsigAula().setVisible(false);
                        cargarTablaAsignaciones();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Los datos no pudieron ser modificados");
                    }
                }
            }
        }
    }

    int codigoAsigAula;

    public void cargarDatosAsignarAulaEnTXT() {
        int fila = vista.getTblAsigAula().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            vista.getjDlgAsigAula().setVisible(true);
            vista.getjDlgAsigAula().setSize(858, 430);
            vista.getjDlgAsigAula().setLocationRelativeTo(null);
            vista.getjDlgAsigAula().setName("Modificar Asignacion");
            vista.getjDlgAsigAula().setTitle("Modificar  Asignacion");

            bloquearTxtAsi();
            vista.getFechaDeAsignacion().setEnabled(true);
            vista.getTxtCodigocurso().setVisible(false);

            ModeloCurso modeloCurso = new ModeloCurso();
            ModeloAula modeloAula = new ModeloAula();

            List<AsigAula> asignaciones = modelo.listaAsignacionesTabla();
            List<Curso> curso = modeloCurso.listaCursoTabla();
            List<Aula> aula = modeloAula.listaAulaTabla();

            asignaciones.stream().forEach(as -> {

                if (Integer.parseInt(vista.getTblAsigAula().getValueAt(fila, 0).toString()) == as.getAsia_codigo()) {

                    codigoAsigAula = Integer.parseInt(vista.getTblAsigAula().getValueAt(fila, 0).toString());

                    curso.stream().forEach(d -> {

                        if (as.getAsia_codcurso() == d.getCur_codigo()) {

                            aula.stream().forEach(a -> {

                                if (as.getAsia_codaula() == a.getAul_codigo()) {

                                    vista.getTxtCodigoAula().setText(String.valueOf(a.getAul_codigo()));
                                    vista.getTxtNombreCurso().setText(d.getCur_nombre());
                                    vista.getTxtPrecio().setText(String.valueOf(d.getCur_precio()));
                                    vista.getTxtCodigocurso().setText(String.valueOf(d.getCur_codigo()));
                                    vista.getTxtCapacidad().setText(String.valueOf(a.getAul_capacidad()));
                                    vista.getFechaDeAsignacion().setDate(as.getAsia_fecha());
                                    vista.getTxtNombreAula().setText(a.getAul_nombre());
                                    vista.getTxtUbicacion().setText(a.getAul_ubicacion());

                                }
                            });
                        }
                    });
                }
            });
        }
    }

    public void eliminarAsignacion() {
        int fila = vista.getTblAsigAula().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigo;
                codigo = Integer.parseInt(vista.getTblAsigAula().getValueAt(fila, 0).toString());

                if (modelo.eliminarAsignacion(codigo) == null) {

                    JOptionPane.showMessageDialog(null, "El registro fue eliminado exitosamente");
                    cargarTablaAsignaciones();  //Actualizo la tabla con los datos
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

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblAsigAula().getModel();

                //vista.getTablaconduccion().setAutoCreateRowSorter(true);
                filtrar = new TableRowSorter<>(tabla);
                vista.getTblAsigAula().setRowSorter(filtrar);

                try {

                    filtrar.setRowFilter(RowFilter.regexFilter(vista.getTxtBuscar().getText())); //Se pasa como parametro el campo de donde se va a obtener la informacion y el (3) es la columna con la cual va a buscar las coincidencias
                } catch (Exception ex) {
                    System.out.println("Error: " + ex);
                }
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //Todo sobre los registros de cursos
    public void cargarDatosCursoEnTXT() {
        int fila = vista.getTblCargarCurso().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            ModeloCurso modeloCurso = new ModeloCurso();
            List<Curso> listap = modeloCurso.listaCursoTabla();

            listap.stream().forEach(c -> {

                if (c.getCur_codigo() == Integer.parseInt(vista.getTblCargarCurso().getValueAt(fila, 0).toString())) {

                    vista.getTxtCodigocurso().setText(String.valueOf(c.getCur_codigo()));
                    vista.getTxtNombreCurso().setText(c.getCur_nombre());
                    vista.getTxtPrecio().setText(String.valueOf(c.getCur_precio()));

                }
            });

            vista.getjDlgCargarCuso().dispose();
        }
    }

    public void cargarRegistroDeCursos() {

        ModeloCurso modeloCurso = new ModeloCurso();
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblCargarCurso().getModel();
        tabla.setNumRows(0);

        List<Curso> curso = modeloCurso.listaCursoTabla();
        curso.stream().forEach(p -> {
            String[] datos = {(String.valueOf(p.getCur_codigo())), p.getCur_nombre(), p.getCur_periodo()};
            tabla.addRow(datos);

        });
    }

    public void buscarCurso() {

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

                ModeloCurso modeloCurso = new ModeloCurso();
                DefaultTableModel tabla = (DefaultTableModel) vista.getTblCargarCurso().getModel();
                tabla.setNumRows(0);

                List<Curso> curso = modeloCurso.buscarCurso(vista.getTxtBuscarCurso().getText());
                curso.stream().forEach(p -> {
                    String[] datos = {(String.valueOf(p.getCur_codigo())), p.getCur_nombre(), p.getCur_periodo()};
                    tabla.addRow(datos);

                });
            }
        };

        vista.getTxtBuscarCurso().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //Todo sobre el registro de asignatura en el jDialog
    public void abrirjDialogCurso() {
        vista.getjDlgCargarCuso().setSize(694, 454);
        vista.getjDlgCargarCuso().setTitle("Seleccione un Curso");
        vista.getjDlgCargarCuso().setVisible(true);
        vista.getjDlgCargarCuso().setLocationRelativeTo(null);
        cargarRegistroDeCursos();
        buscarCurso();
    }

    public void cargarRegistroDeAula() {
        ModeloAula modeloAula = new ModeloAula();
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblCargarAula().getModel();
        tabla.setNumRows(0);

        List<Aula> aula = modeloAula.listaAulaTabla();
        aula.stream().forEach(p -> {
            String[] datos = {(String.valueOf(p.getAul_codigo())), p.getAul_nombre(), String.valueOf(p.getAul_capacidad())};
            tabla.addRow(datos);

        });

    }

    public void cargarDatosAulaEnTXT() {
        int fila = vista.getTblCargarAula().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            ModeloAula modeloAula = new ModeloAula();
            List<Aula> listap = modeloAula.listaAulaTabla();

            listap.stream().forEach(c -> {

                if (c.getAul_codigo() == Integer.parseInt(vista.getTblCargarAula().getValueAt(fila, 0).toString())) {

                    vista.getTxtCodigoAula().setText(String.valueOf(c.getAul_codigo()));
                    vista.getTxtNombreAula().setText(c.getAul_nombre());
                    vista.getTxtUbicacion().setText(c.getAul_ubicacion());
                    vista.getTxtCapacidad().setText(String.valueOf(c.getAul_capacidad()));

                }
            });

            vista.getjDlgCargarAula().dispose();
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

                ModeloAula modeloAula = new ModeloAula();
                DefaultTableModel tabla = (DefaultTableModel) vista.getTblCargarAula().getModel();
                tabla.setNumRows(0);

                List<Aula> aula = modeloAula.buscarAula(vista.getTxtBuscarAula().getText());
                aula.stream().forEach(p -> {
                    String[] datos = {(String.valueOf(p.getAul_codigo())), p.getAul_nombre(), String.valueOf(p.getAul_capacidad())};
                    tabla.addRow(datos);

                });
            }
        };

        vista.getTxtBuscarAula().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    public void bloquearTxtAsi() {
        vista.getTxtCodigoAula().setEnabled(false);
        vista.getTxtPrecio().setEnabled(false);
        vista.getTxtNombreCurso().setEnabled(false);
        vista.getTxtUbicacion().setEnabled(false);
        vista.getTxtNombreAula().setEnabled(false);
        vista.getTxtCapacidad().setEnabled(false);
    }

    public void habilitarTxtAsi() {
        vista.getTxtCodigoAula().setEnabled(true);
        vista.getTxtPrecio().setEnabled(true);
        vista.getTxtNombreCurso().setEnabled(true);
        vista.getTxtUbicacion().setEnabled(true);
        vista.getTxtNombreAula().setEnabled(true);
        vista.getTxtCapacidad().setEnabled(true);
    }

    public void limpiarlabels() {
        vista.getTxtCodigoAula().setText("");
        vista.getTxtPrecio().setText("");
        vista.getTxtNombreCurso().setText("");
        vista.getTxtUbicacion().setText("");
        vista.getTxtNombreAula().setText("");
        vista.getTxtCapacidad().setText("");
    }

    public boolean validarDatos() {

        boolean validar = true;

        if (vista.getTxtCodigocurso().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un docente");
            validar = false;
        }

        if (vista.getTxtCapacidad().getText().isEmpty()) {
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

    public void cargarFechaActual() {
        vista.getFechaDeAsignacion().setEnabled(false);
        //Seteo la fecha actual en el jCalendar
        Date fecha = new Date();
        vista.getFechaDeAsignacion().setDate(fecha);
    }

    public void botonEliminar() {
        vista.getjDlgAsigAula().setVisible(false);
    }
}
