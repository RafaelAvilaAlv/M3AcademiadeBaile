package Controlador;

import Modelo.AsiHorario;
import Modelo.Curso;
import Modelo.Horario;
import Modelo.ModeloAsiHorario;
import Modelo.ModeloCurso;
import Modelo.ModeloHorario;
import Vista.VistaAsiHorario;
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

public class ControladorAsiHorario {

    ModeloAsiHorario modelo;
    VistaAsiHorario vista;

    static boolean verificarAsignacion;//Verificar si a un curso ya se le ha asignado un horario. No se puede asignar el mismo horario al mismo curso

    VistaPrincipal p = new VistaPrincipal();

    public ControladorAsiHorario(ModeloAsiHorario modelo, VistaAsiHorario vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
        vista.setSize(p.getEscritorioPrincipal().getWidth(), p.getEscritorioPrincipal().getHeight());
        cargarTablaAsignaciones();
    }

    public void iniciarControl() {
        //Asignar horario
        vista.getBtnAsignar().addActionListener(l -> abrirjDialogAsiHorario());

        //Curso
        vista.getBtnBuscarCurso().addActionListener(l -> abrirjDialogCurso());
        vista.getBtnCargarCur().addActionListener(l -> cargarDatosCursoEnTXT());

        //Horario
        vista.getBtnBuscarHorario().addActionListener(l -> abrirjDialogHorario());
        vista.getBtnCargarHor().addActionListener(l -> cargarDatosHorarioEnTXT());
        vista.getBtnGuardar().addActionListener(l -> crearModificarAsignarHorario());
        vista.getBtnModificar().addActionListener(l -> cargarDatosAsignarHorarioEnTXT());
        vista.getBtnEliminar().addActionListener(l -> eliminarAsignacion());
        vista.getBtnCancelar().addActionListener(l -> cancelar());
        buscarRegistros();
    }

    //Asignar horario
    public void abrirjDialogAsiHorario() {
        vista.getjDlgAsignarHorario().setVisible(true);
        vista.getjDlgAsignarHorario().setSize(809, 540);
        vista.getjDlgAsignarHorario().setLocationRelativeTo(null);
        vista.getjDlgAsignarHorario().setTitle("Asignar horario");
        vista.getjDlgAsignarHorario().setName("Asignar horario");
        //Bloquear y limpiar los campos
        limpiarCampos();
        bloquearCampos();
        //Quito la visibilidad de los txt de los codigos
        vista.getTxtCodigoCurso().setVisible(false);
        vista.getTxtCodigoHorario().setVisible(false);
        cargarFechaActual();
    }

    public void cargarTablaAsignaciones() {

        DefaultTableModel tabla = (DefaultTableModel) vista.getTblAsiHorario().getModel();
        tabla.setNumRows(0);

        ModeloCurso modeloCurso = new ModeloCurso();
        ModeloHorario modeloHorario = new ModeloHorario();

        List<AsiHorario> asignaciones = modelo.listaAsignacionesTabla();
        List<Curso> cursos = modeloCurso.listaCursoTabla();
        List<Horario> horarios = modeloHorario.listaHorarioTabla();

        asignaciones.stream().forEach(as -> {

            cursos.stream().forEach(c -> {

                if (as.getAsih_codcurso() == c.getCur_codigo()) {

                    horarios.stream().forEach(h -> {

                        if (as.getAsih_codhorario() == h.getHor_codigo()) {

                            String[] datos = {String.valueOf(as.getAsih_codigo()), c.getCur_nombre(), h.getHor_dia(), h.getHor_horaIni(), h.getHor_horaFin()};
                            tabla.addRow(datos);

                        }
                    });
                }
            });
        });
    }

    public void crearModificarAsignarHorario() {
        if ("Asignar horario".equals(vista.getjDlgAsignarHorario().getName())) {

            verificarAsignacion = false;

            //INSERTAR
            if (validarDatos()) {

                List<AsiHorario> asignaciones = modelo.listaAsignacionesTabla();

                asignaciones.stream().forEach(as -> {

                    if (as.getAsih_codcurso() == Integer.parseInt(vista.getTxtCodigoCurso().getText())) {
                        if (as.getAsih_codhorario() == Integer.parseInt(vista.getTxtCodigoHorario().getText())) {
                            verificarAsignacion = true;
                        }
                    }
                });

                if (verificarAsignacion) {
                    JOptionPane.showMessageDialog(vista, "Este horario ya ha sido asignado a este curso");
                } else {

                    modelo.setAsih_codcurso(Integer.parseInt(vista.getTxtCodigoCurso().getText()));
                    modelo.setAsih_codhorario(Integer.parseInt(vista.getTxtCodigoHorario().getText()));

                    Date fecha = vista.getFechaDeAsignacion().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    modelo.setAsih_fecha(fechaSQL);

                    if (modelo.asignarHorario() == null) {
                        JOptionPane.showMessageDialog(vista, "La asignacion se ha realizado satisfactoriamente");
                        vista.getjDlgAsignarHorario().setVisible(false);
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

                List<AsiHorario> asignaciones = modelo.listaAsignacionesTabla();

                asignaciones.stream().forEach(as -> {

                    if (as.getAsih_codcurso() == Integer.parseInt(vista.getTxtCodigoCurso().getText())) {
                        if (as.getAsih_codhorario() == Integer.parseInt(vista.getTxtCodigoHorario().getText())) {
                            verificarAsignacion = true;
                        }
                    }
                });

                //Si el docente ya imparte la materia solo se modificara la fecha
                if (verificarAsignacion) {
                    modelo.setAsih_codigo(codigoAsiHorario);

                    Date fecha = vista.getFechaDeAsignacion().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    modelo.setAsih_fecha(fechaSQL);

                    if (modelo.modificarAsignacionFecha() == null) {
                        JOptionPane.showMessageDialog(vista, "Los datos fueron modificados satisfactoriamente");
                        vista.getjDlgAsignarHorario().setVisible(false);
                        cargarTablaAsignaciones();
                    }

                } else {

                    modelo.setAsih_codigo(codigoAsiHorario);
                    modelo.setAsih_codcurso(Integer.parseInt(vista.getTxtCodigoCurso().getText()));
                    modelo.setAsih_codhorario(Integer.parseInt(vista.getTxtCodigoHorario().getText()));

                    Date fecha = vista.getFechaDeAsignacion().getDate();
                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    modelo.setAsih_fecha(fechaSQL);

                    if (modelo.modificarAsignacionCompleto() == null) {
                        JOptionPane.showMessageDialog(vista, "Los datos fueron modificados satisfactoriamente");
                        vista.getjDlgAsignarHorario().setVisible(false);
                        cargarTablaAsignaciones();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Los datos no pudieron ser modificados");
                    }
                }
            }
        }
    }

    int codigoAsiHorario;

    public void cargarDatosAsignarHorarioEnTXT() {
        int fila = vista.getTblAsiHorario().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            vista.getjDlgAsignarHorario().setVisible(true);
            vista.getjDlgAsignarHorario().setSize(809, 540);
            vista.getjDlgAsignarHorario().setLocationRelativeTo(null);
            vista.getjDlgAsignarHorario().setName("Modificar asignacion");
            vista.getjDlgAsignarHorario().setTitle("Modificar asignacion");

            //Bloquear los campos
            bloquearCampos();
            vista.getFechaDeAsignacion().setEnabled(true);

            //Quito la visibilidad de los txt de los codigos
            vista.getTxtCodigoCurso().setVisible(false);
            vista.getTxtCodigoHorario().setVisible(false);

            ModeloCurso modeloCurso = new ModeloCurso();
            ModeloHorario modeloHorario = new ModeloHorario();

            List<AsiHorario> asignaciones = modelo.listaAsignacionesTabla();
            List<Curso> cursos = modeloCurso.listaCursoTabla();
            List<Horario> horarios = modeloHorario.listaHorarioTabla();

            asignaciones.stream().forEach(as -> {

                if (Integer.parseInt(vista.getTblAsiHorario().getValueAt(fila, 0).toString()) == as.getAsih_codigo()) {

                    codigoAsiHorario = Integer.parseInt(vista.getTblAsiHorario().getValueAt(fila, 0).toString());

                    cursos.stream().forEach(c -> {

                        if (as.getAsih_codcurso() == c.getCur_codigo()) {

                            horarios.stream().forEach(h -> {

                                if (as.getAsih_codhorario() == h.getHor_codigo()) {

                                    //Curso
                                    vista.getTxtCodigoCurso().setText(String.valueOf(c.getCur_codigo()));
                                    vista.getTxtNombreCurso().setText(c.getCur_nombre());
                                    vista.getTxtPeriodoCurso().setText(c.getCur_periodo());
                                    //Horario
                                    vista.getTxtCodigoHorario().setText(String.valueOf(h.getHor_codigo()));
                                    vista.getTxtDiaHorario().setText(h.getHor_dia());
                                    vista.getTxtHoraDeInicio().setText(h.getHor_horaIni());
                                    vista.getTxtHoraDeFin().setText(h.getHor_horaFin());
                                    //Asignacion de horario
                                    vista.getFechaDeAsignacion().setDate(as.getAsih_fecha());

                                }
                            });
                        }
                    });
                }
            });
        }
    }

    public void eliminarAsignacion() {
        int fila = vista.getTblAsiHorario().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigo;
                codigo = Integer.parseInt(vista.getTblAsiHorario().getValueAt(fila, 0).toString());

                if (modelo.eliminarAsignacion(codigo) == null) {
                    JOptionPane.showMessageDialog(null, "El registro fue eliminado exitosamente");
                    cargarTablaAsignaciones();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "Los datos no fueron eliminados");
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

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblAsiHorario().getModel();

                //vista.getTablaconduccion().setAutoCreateRowSorter(true);
                filtrar = new TableRowSorter<>(tabla);
                vista.getTblAsiHorario().setRowSorter(filtrar);

                try {

                    filtrar.setRowFilter(RowFilter.regexFilter(vista.getTxtBuscar().getText())); //Se pasa como parametro el campo de donde se va a obtener la informacion y el (3) es la columna con la cual va a buscar las coincidencias
                } catch (Exception ex) {
                    System.out.println("Error: " + ex);
                }
            }
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //Todo sobre el registro de cursos en el jDialog
    public void abrirjDialogCurso() {
        vista.getjDlgBuscarCurso().setVisible(true);
        vista.getjDlgBuscarCurso().setSize(515, 388);
        vista.getjDlgBuscarCurso().setLocationRelativeTo(null);
        vista.getjDlgBuscarCurso().setTitle("Seleccionar curso");

        //Quito la visibilidad del txt del codigo del curso
        // vista.getTxtCodigoCurso().setVisible(false);
        cargarRegistroDeCursos();
        buscarCurso();
    }

    public void cargarRegistroDeCursos() {

        ModeloCurso modeloCurso = new ModeloCurso();
        vista.getTblDlgjCurso().setRowHeight(25);
        DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgjCurso().getModel();
        estructuraTabla.setRowCount(0);

        List<Curso> listap = modeloCurso.listaCursoTabla();

        Holder<Integer> i = new Holder<>(0);

        listap.stream().forEach(c -> {

            estructuraTabla.addRow(new Object[8]);

            vista.getTblDlgjCurso().setValueAt(c.getCur_codigo(), i.value, 0);
            vista.getTblDlgjCurso().setValueAt(c.getCur_nombre(), i.value, 1);
            vista.getTblDlgjCurso().setValueAt(c.getCur_periodo(), i.value, 2);

            i.value++;
        });
    }

    public void cargarDatosCursoEnTXT() {
        int fila = vista.getTblDlgjCurso().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            ModeloCurso modeloCurso = new ModeloCurso();
            List<Curso> listap = modeloCurso.listaCursoTabla();

            listap.stream().forEach(c -> {

                if (c.getCur_codigo() == Integer.parseInt(vista.getTblDlgjCurso().getValueAt(fila, 0).toString())) {

                    vista.getTxtCodigoCurso().setText(String.valueOf(c.getCur_codigo()));
                    vista.getTxtNombreCurso().setText(c.getCur_nombre());
                    vista.getTxtPeriodoCurso().setText(c.getCur_periodo());

                }
            });

            vista.getjDlgBuscarCurso().dispose();
        }
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
                vista.getTblDlgjCurso().setRowHeight(25);
                DefaultTableModel estructuraTabla = (DefaultTableModel) vista.getTblDlgjCurso().getModel();
                estructuraTabla.setRowCount(0);

                List<Curso> listap = modeloCurso.buscarCurso(vista.getTxtBuscarCur().getText());

                Holder<Integer> i = new Holder<>(0);

                listap.stream().forEach(c -> {

                    estructuraTabla.addRow(new Object[8]);

                    vista.getTblDlgjCurso().setValueAt(c.getCur_codigo(), i.value, 0);
                    vista.getTblDlgjCurso().setValueAt(c.getCur_nombre(), i.value, 1);
                    vista.getTblDlgjCurso().setValueAt(c.getCur_periodo(), i.value, 2);

                    i.value++;
                });
            }
        };

        vista.getTxtBuscarCur().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    //Todo sobre Horario
    public void abrirjDialogHorario() {
        vista.getjDlgBuscarHorario().setVisible(true);
        vista.getjDlgBuscarHorario().setSize(515, 388);
        vista.getjDlgBuscarHorario().setLocationRelativeTo(null);
        vista.getjDlgBuscarHorario().setTitle("Seleccionar horario");
        //Quito la visibilidad del txt del codigo del horario
        //vista.getTxtCodigoHorario().setVisible(false);
        cargarRegistroDeHorarios();
        buscarHorario();
    }

    public void cargarRegistroDeHorarios() {

        ModeloHorario modeloHorario = new ModeloHorario();
        vista.getTblDlgjHorario().setRowHeight(25);
        DefaultTableModel tabla = (DefaultTableModel) vista.getTblDlgjHorario().getModel();
        tabla.setNumRows(0);

        List<Horario> horarios = modeloHorario.listaHorarioTabla();
        horarios.stream().forEach(p -> {
            String[] datos = {String.valueOf(p.getHor_codigo()), p.getHor_dia(), p.getHor_horaIni(), p.getHor_horaFin()};
            tabla.addRow(datos);
        });
    }

    public void cargarDatosHorarioEnTXT() {
        int fila = vista.getTblDlgjHorario().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            ModeloHorario modeloHorario = new ModeloHorario();

            List<Horario> listap = modeloHorario.listaHorarioTabla();

            listap.stream().forEach(c -> {

                if (c.getHor_codigo() == Integer.parseInt(vista.getTblDlgjHorario().getValueAt(fila, 0).toString())) {

                    vista.getTxtCodigoHorario().setText(String.valueOf(c.getHor_codigo()));
                    vista.getTxtDiaHorario().setText(c.getHor_dia());
                    vista.getTxtHoraDeInicio().setText(c.getHor_horaIni());
                    vista.getTxtHoraDeFin().setText(c.getHor_horaFin());
                }
            });

            vista.getjDlgBuscarHorario().dispose();
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

                DefaultTableModel tabla = (DefaultTableModel) vista.getTblDlgjHorario().getModel();

                //vista.getTablaconduccion().setAutoCreateRowSorter(true);
                filtrar = new TableRowSorter<>(tabla);
                vista.getTblDlgjHorario().setRowSorter(filtrar);

                try {

                    filtrar.setRowFilter(RowFilter.regexFilter(vista.getTxtBuscarHor().getText())); //Se pasa como parametro el campo de donde se va a obtener la informacion y el (3) es la columna con la cual va a buscar las coincidencias
                } catch (Exception ex) {
                    System.out.println("Error: " + ex);
                }
            }
        };

        vista.getTxtBuscarHor().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    public boolean validarDatos() {

        boolean validar = true;

        if (vista.getTxtNombreCurso().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un curso");
            validar = false;
        }

        if (vista.getTxtDiaHorario().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un horario");
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
        vista.getTxtNombreCurso().setEditable(false);
        vista.getTxtPeriodoCurso().setEditable(false);
        vista.getTxtDiaHorario().setEditable(false);
        vista.getTxtHoraDeInicio().setEditable(false);
        vista.getTxtHoraDeFin().setEditable(false);

    }

    public void limpiarCampos() {
        vista.getTxtNombreCurso().setText("");
        vista.getTxtPeriodoCurso().setText("");
        vista.getTxtDiaHorario().setText("");
        vista.getTxtHoraDeInicio().setText("");
        vista.getTxtHoraDeFin().setText("");
    }

    public void cargarFechaActual() {
        vista.getFechaDeAsignacion().setEnabled(false);
        //Seteo la fecha actual en el jCalendar
        Date fecha = new Date();
        vista.getFechaDeAsignacion().setDate(fecha);
    }

    public void cancelar() {
        vista.getjDlgAsignarHorario().setVisible(false);
    }
}
