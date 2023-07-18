package Vista;

import com.toedter.calendar.JDateChooser;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VistaDocente extends javax.swing.JInternalFrame {

    public VistaDocente() {
        initComponents();
    }

    public ButtonGroup getGenero() {
        return genero;
    }

    public void setGenero(ButtonGroup genero) {
        this.genero = genero;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public void setBtnActualizar(JButton btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    public JButton getBtnAsignar() {
        return btnAsignar;
    }

    public void setBtnAsignar(JButton btnAsignar) {
        this.btnAsignar = btnAsignar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public JTable getTblDocente() {
        return tblDocente;
    }

    public void setTblDocente(JTable tblDocente) {
        this.tblDocente = tblDocente;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public JSpinner getSpinnerSueldo() {
        return spinnerSueldo;
    }

    public void setSpinnerSueldo(JSpinner spinnerSueldo) {
        this.spinnerSueldo = spinnerSueldo;
    }

    public JDialog getjDlgDocente() {
        return jDlgDocente;
    }

    public void setjDlgDocente(JDialog jDlgDocente) {
        this.jDlgDocente = jDlgDocente;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JButton getBtnCargarPer() {
        return btnCargarPer;
    }

    public void setBtnCargarPer(JButton btnCargarPer) {
        this.btnCargarPer = btnCargarPer;
    }

    public JDateChooser getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(JDateChooser fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public JDateChooser getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(JDateChooser fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public JRadioButton getFemenino() {
        return femenino;
    }

    public void setFemenino(JRadioButton femenino) {
        this.femenino = femenino;
    }

    public JDialog getjDlgBuscarPersonas() {
        return jDlgBuscarPersonas;
    }

    public void setjDlgBuscarPersonas(JDialog jDlgBuscarPersonas) {
        this.jDlgBuscarPersonas = jDlgBuscarPersonas;
    }

    public JRadioButton getMasculino() {
        return masculino;
    }

    public void setMasculino(JRadioButton masculino) {
        this.masculino = masculino;
    }

    public JRadioButton getNoBinario() {
        return noBinario;
    }

    public void setNoBinario(JRadioButton noBinario) {
        this.noBinario = noBinario;
    }

    public JTable getTblDlgPersonas() {
        return tblDlgPersonas;
    }

    public void setTblDlgPersonas(JTable tblDlgPersonas) {
        this.tblDlgPersonas = tblDlgPersonas;
    }

    public JTextField getTxtBuscarPer() {
        return txtBuscarPer;
    }

    public void setTxtBuscarPer(JTextField txtBuscarPer) {
        this.txtBuscarPer = txtBuscarPer;
    }

    public JTextField getTxtCedula() {
        return txtCedula;
    }

    public void setTxtCedula(JTextField txtCedula) {
        this.txtCedula = txtCedula;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(JTextField txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JTextField getTxtEspecialidad() {
        return txtEspecialidad;
    }

    public void setTxtEspecialidad(JTextField txtEspecialidad) {
        this.txtEspecialidad = txtEspecialidad;
    }

    public JTextField getTxtPrimerApellido() {
        return txtPrimerApellido;
    }

    public void setTxtPrimerApellido(JTextField txtPrimerApellido) {
        this.txtPrimerApellido = txtPrimerApellido;
    }

    public JTextField getTxtPrimerNombre() {
        return txtPrimerNombre;
    }

    public void setTxtPrimerNombre(JTextField txtPrimerNombre) {
        this.txtPrimerNombre = txtPrimerNombre;
    }

    public JTextField getTxtSegundoApellido() {
        return txtSegundoApellido;
    }

    public void setTxtSegundoApellido(JTextField txtSegundoApellido) {
        this.txtSegundoApellido = txtSegundoApellido;
    }

    public JTextField getTxtSegundoNombre() {
        return txtSegundoNombre;
    }

    public void setTxtSegundoNombre(JTextField txtSegundoNombre) {
        this.txtSegundoNombre = txtSegundoNombre;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public JButton getBtnBuscarPersona() {
        return btnBuscarPersona;
    }

    public void setBtnBuscarPersona(JButton btnBuscarPersona) {
        this.btnBuscarPersona = btnBuscarPersona;
    }

    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public void setBtnImprimir(JButton btnImprimir) {
        this.btnImprimir = btnImprimir;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDlgDocente = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        txtSegundoApellido = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fechaNacimiento = new com.toedter.calendar.JDateChooser();
        txtCedula = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        masculino = new javax.swing.JRadioButton();
        txtPrimerNombre = new javax.swing.JTextField();
        femenino = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        noBinario = new javax.swing.JRadioButton();
        txtSegundoNombre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtPrimerApellido = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnBuscarPersona = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtEspecialidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        spinnerSueldo = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        fechaContratacion = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jDlgBuscarPersonas = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtBuscarPer = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDlgPersonas = new javax.swing.JTable();
        btnCargarPer = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        genero = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocente = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnAsignar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        jtxtTituloMenu = new javax.swing.JLabel();

        jDlgDocente.setResizable(false);
        jDlgDocente.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(txtSegundoApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 366, 216, -1));

        jLabel12.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel12.setText("Direccion:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, -1, -1));

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(null);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 601, 96, -1));

        jLabel8.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel8.setText("Fecha de nacimiento:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));
        jPanel2.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, 180, -1));

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel2.setText("Cédula:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 92, -1, -1));
        jPanel2.add(fechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, 180, -1));
        jPanel2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 89, 218, -1));

        jLabel9.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel9.setText("Genero:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 460, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel4.setText("Primer nombre:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 151, -1, -1));

        genero.add(masculino);
        masculino.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        masculino.setText("M");
        jPanel2.add(masculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 452, -1, -1));
        jPanel2.add(txtPrimerNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 148, 216, -1));

        genero.add(femenino);
        femenino.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        femenino.setText("F");
        jPanel2.add(femenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 452, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel5.setText("Segundo nombre:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 223, -1, -1));

        genero.add(noBinario);
        noBinario.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        noBinario.setText("No binario");
        jPanel2.add(noBinario, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 452, -1, -1));
        jPanel2.add(txtSegundoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 220, 216, -1));

        jLabel10.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel10.setText("Telefono:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 523, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel6.setText("Primer apellido:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 293, -1, -1));
        jPanel2.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 520, 216, -1));
        jPanel2.add(txtPrimerApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 290, 216, -1));

        jLabel11.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel11.setText("Email:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel7.setText("Segundo apellido:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 369, -1, -1));
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, 180, -1));

        btnBuscarPersona.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarPersona.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnBuscarPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscarPersona.setText("Buscar");
        btnBuscarPersona.setBorder(null);
        btnBuscarPersona.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarPersona.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(btnBuscarPersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 88, -1, -1));

        jLabel13.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel13.setText("Especialidad:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, -1, -1));
        jPanel2.add(txtEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 290, 180, -1));

        jLabel3.setFont(new java.awt.Font("Roboto Black", 0, 28)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/profesor.png"))); // NOI18N
        jLabel3.setText("Datos de docente ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-x.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(null);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 601, 102, 31));

        spinnerSueldo.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 10000.0d, 1.0d));
        jPanel2.add(spinnerSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 360, 160, -1));

        jLabel14.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel14.setText("Sueldo");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, -1, -1));

        jLabel15.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel15.setText("$");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 360, -1, -1));
        jPanel2.add(fechaContratacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 440, 190, -1));

        jLabel16.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel16.setText("Fecha de contratacion:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 440, -1, -1));

        jDlgDocente.getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 889, 660));

        jDlgBuscarPersonas.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel17.setText("Buscar:");

        tblDlgPersonas.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        tblDlgPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cédula", "Nombre", "Apellido", "Teléfono", "Correo"
            }
        ));
        jScrollPane2.setViewportView(tblDlgPersonas);

        btnCargarPer.setBackground(new java.awt.Color(255, 255, 255));
        btnCargarPer.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnCargarPer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/asignar.png"))); // NOI18N
        btnCargarPer.setText("Cargar");
        btnCargarPer.setBorder(null);

        jLabel19.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/personas.png"))); // NOI18N
        jLabel19.setText("Cargar Personas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnCargarPer, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBuscarPer, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtBuscarPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarPer, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jDlgBuscarPersonasLayout = new javax.swing.GroupLayout(jDlgBuscarPersonas.getContentPane());
        jDlgBuscarPersonas.getContentPane().setLayout(jDlgBuscarPersonasLayout);
        jDlgBuscarPersonasLayout.setHorizontalGroup(
            jDlgBuscarPersonasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDlgBuscarPersonasLayout.setVerticalGroup(
            jDlgBuscarPersonasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
        );

        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setForeground(java.awt.Color.white);
        setResizable(true);
        setAutoscrolls(true);

        jScrollPane1.setAutoscrolls(true);

        tblDocente.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        tblDocente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cédula", "Nombre y apellido", "Código de empleado", "Salario", "Código de docente", "Especialidad"
            }
        ));
        jScrollPane1.setViewportView(tblDocente);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel1.setText("Buscar:");

        btnAsignar.setBackground(new java.awt.Color(255, 255, 255));
        btnAsignar.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnAsignar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/AñadirDetalles.png"))); // NOI18N
        btnAsignar.setText("Asignar");
        btnAsignar.setBorder(null);
        btnAsignar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnModificar.setBackground(new java.awt.Color(255, 255, 255));
        btnModificar.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setBorder(null);
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(null);
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/compartimiento.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(null);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnImprimir.setBackground(new java.awt.Color(255, 255, 255));
        btnImprimir.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setBorder(null);
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jtxtTituloMenu.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jtxtTituloMenu.setForeground(new java.awt.Color(155, 12, 27));
        jtxtTituloMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/docente.png"))); // NOI18N
        jtxtTituloMenu.setText("Docente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                        .addComponent(btnAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jtxtTituloMenu)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jtxtTituloMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEliminar)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModificar)
                        .addComponent(btnAsignar)
                        .addComponent(btnImprimir)))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnBuscarPersona;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCargarPer;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnModificar;
    private com.toedter.calendar.JDateChooser fechaContratacion;
    private com.toedter.calendar.JDateChooser fechaNacimiento;
    private javax.swing.JRadioButton femenino;
    private javax.swing.ButtonGroup genero;
    private javax.swing.JDialog jDlgBuscarPersonas;
    private javax.swing.JDialog jDlgDocente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jtxtTituloMenu;
    private javax.swing.JRadioButton masculino;
    private javax.swing.JRadioButton noBinario;
    private javax.swing.JSpinner spinnerSueldo;
    private javax.swing.JTable tblDlgPersonas;
    private javax.swing.JTable tblDocente;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarPer;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEspecialidad;
    private javax.swing.JTextField txtPrimerApellido;
    private javax.swing.JTextField txtPrimerNombre;
    private javax.swing.JTextField txtSegundoApellido;
    private javax.swing.JTextField txtSegundoNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
