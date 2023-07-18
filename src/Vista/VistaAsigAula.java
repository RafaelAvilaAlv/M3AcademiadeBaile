/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Usuario
 */
public class VistaAsigAula extends javax.swing.JInternalFrame {

    /**
     * Creates new form VistaAsigAula
     */
    public VistaAsigAula() {
        initComponents();
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

    public JButton getBtnBuscarAula() {
        return btnBuscarAula;
    }

    public void setBtnBuscarAula(JButton btnBuscarAula) {
        this.btnBuscarAula = btnBuscarAula;
    }

    public JButton getBtnBuscarCurso() {
        return btnBuscarCurso;
    }

    public void setBtnBuscarCurso(JButton btnBuscarCurso) {
        this.btnBuscarCurso = btnBuscarCurso;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JButton getBtnCargarAula() {
        return btnCargarAula;
    }

    public void setBtnCargarAula(JButton btnCargarAula) {
        this.btnCargarAula = btnCargarAula;
    }

    public JButton getBtnCargarCurso() {
        return btnCargarCurso;
    }

    public void setBtnCargarCurso(JButton btnCargarCurso) {
        this.btnCargarCurso = btnCargarCurso;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public JDateChooser getFechaDeAsignacion() {
        return fechaDeAsignacion;
    }

    public void setFechaDeAsignacion(JDateChooser fechaDeAsignacion) {
        this.fechaDeAsignacion = fechaDeAsignacion;
    }

    public JDialog getjDlgAsigAula() {
        return jDlgAsigAula;
    }

    public void setjDlgAsigAula(JDialog jDlgAsigAula) {
        this.jDlgAsigAula = jDlgAsigAula;
    }

    public JDialog getjDlgCargarAula() {
        return jDlgCargarAula;
    }

    public void setjDlgCargarAula(JDialog jDlgCargarAula) {
        this.jDlgCargarAula = jDlgCargarAula;
    }

    public JDialog getjDlgCargarCuso() {
        return jDlgCargarCuso;
    }

    public void setjDlgCargarCuso(JDialog jDlgCargarCuso) {
        this.jDlgCargarCuso = jDlgCargarCuso;
    }

    public JTable getTblAsigAula() {
        return tblAsigAula;
    }

    public void setTblAsigAula(JTable tblAsigAula) {
        this.tblAsigAula = tblAsigAula;
    }

    public JTable getTblCargarAula() {
        return tblCargarAula;
    }

    public void setTblCargarAula(JTable tblCargarAula) {
        this.tblCargarAula = tblCargarAula;
    }

    public JTable getTblCargarCurso() {
        return tblCargarCurso;
    }

    public void setTblCargarCurso(JTable tblCargarCurso) {
        this.tblCargarCurso = tblCargarCurso;
    }

    public JTextField getTxtApellido() {
        return txtUbicacion;
    }

    public void setTxtApellido(JTextField txtApellido) {
        this.txtUbicacion = txtApellido;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JTextField getTxtBuscarAula() {
        return txtBuscarAula;
    }

    public void setTxtBuscarAula(JTextField txtBuscarAula) {
        this.txtBuscarAula = txtBuscarAula;
    }

    public JTextField getTxtBuscarCurso() {
        return txtBuscarCurso;
    }

    public void setTxtBuscarCurso(JTextField txtBuscarCurso) {
        this.txtBuscarCurso = txtBuscarCurso;
    }

    public JTextField getTxtCodigoAsignatura() {
        return txtCodigocurso;
    }

    public void setTxtCodigoAsignatura(JTextField txtCodigoAsignatura) {
        this.txtCodigocurso = txtCodigoAsignatura;
    }

    public JTextField getTxtCodigoAula() {
        return txtCodigoAula;
    }

    public void setTxtCodigoAula(JTextField txtCodigoAula) {
        this.txtCodigoAula = txtCodigoAula;
    }

    public JTextField getTxtEspecialidad() {
        return txtCapacidad;
    }

    public void setTxtEspecialidad(JTextField txtEspecialidad) {
        this.txtCapacidad = txtEspecialidad;
    }

    public JTextField getTxtNombreAula() {
        return txtNombreAula;
    }

    public void setTxtNombreAula(JTextField txtNombreAula) {
        this.txtNombreAula = txtNombreAula;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JTextField getTxtCodigocurso() {
        return txtCodigocurso;
    }

    public void setTxtCodigocurso(JTextField txtCodigocurso) {
        this.txtCodigocurso = txtCodigocurso;
    }

    public JTextField getTxtNombreCurso() {
        return txtNombreCurso;
    }

    public void setTxtNombreCurso(JTextField txtNombreCurso) {
        this.txtNombreCurso = txtNombreCurso;
    }

    public JTextField getTxtCapacidad() {
        return txtCapacidad;
    }

    public void setTxtCapacidad(JTextField txtCapacidad) {
        this.txtCapacidad = txtCapacidad;
    }

    public JTextField getTxtUbicacion() {
        return txtUbicacion;
    }

    public void setTxtUbicacion(JTextField txtUbicacion) {
        this.txtUbicacion = txtUbicacion;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDlgCargarAula = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtBuscarAula = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCargarAula = new javax.swing.JTable();
        btnCargarAula = new javax.swing.JButton();
        jDlgCargarCuso = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtBuscarCurso = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCargarCurso = new javax.swing.JTable();
        btnCargarCurso = new javax.swing.JButton();
        jDlgAsigAula = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigoAula = new javax.swing.JTextField();
        btnBuscarAula = new javax.swing.JButton();
        txtNombreAula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCapacidad = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNombreCurso = new javax.swing.JTextField();
        txtCodigocurso = new javax.swing.JTextField();
        fechaDeAsignacion = new com.toedter.calendar.JDateChooser();
        btnBuscarCurso = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnAsignar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jtxtTituloMenu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsigAula = new javax.swing.JTable();

        jDlgCargarAula.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aula_1.png"))); // NOI18N
        jLabel23.setText("Cargar Aula");

        jLabel24.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel24.setText("Buscar:");

        tblCargarAula.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        tblCargarAula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Capacidad"
            }
        ));
        jScrollPane5.setViewportView(tblCargarAula);

        btnCargarAula.setBackground(new java.awt.Color(255, 255, 255));
        btnCargarAula.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnCargarAula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/asignar.png"))); // NOI18N
        btnCargarAula.setText("Cargar");
        btnCargarAula.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscarAula, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 400, Short.MAX_VALUE)
                        .addComponent(btnCargarAula, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtBuscarAula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarAula, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jDlgCargarAulaLayout = new javax.swing.GroupLayout(jDlgCargarAula.getContentPane());
        jDlgCargarAula.getContentPane().setLayout(jDlgCargarAulaLayout);
        jDlgCargarAulaLayout.setHorizontalGroup(
            jDlgCargarAulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDlgCargarAulaLayout.setVerticalGroup(
            jDlgCargarAulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDlgCargarCuso.setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/diploma (1).png"))); // NOI18N
        jLabel21.setText("Cargar Curso");

        jLabel22.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel22.setText("Buscar:");

        tblCargarCurso.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        tblCargarCurso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Periodo"
            }
        ));
        jScrollPane4.setViewportView(tblCargarCurso);

        btnCargarCurso.setBackground(new java.awt.Color(255, 255, 255));
        btnCargarCurso.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnCargarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/asignar.png"))); // NOI18N
        btnCargarCurso.setText("Cargar");
        btnCargarCurso.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 352, Short.MAX_VALUE)
                        .addComponent(btnCargarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtBuscarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jDlgCargarCusoLayout = new javax.swing.GroupLayout(jDlgCargarCuso.getContentPane());
        jDlgCargarCuso.getContentPane().setLayout(jDlgCargarCusoLayout);
        jDlgCargarCusoLayout.setHorizontalGroup(
            jDlgCargarCusoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDlgCargarCusoLayout.setVerticalGroup(
            jDlgCargarCusoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDlgAsigAula.setResizable(false);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto Black", 0, 28)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aula_1.png"))); // NOI18N
        jLabel3.setText("Asignar Aula");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 23, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel6.setText("Codigo Aula:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 97, -1, -1));
        jPanel4.add(txtCodigoAula, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 94, 162, -1));

        btnBuscarAula.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarAula.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnBuscarAula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscarAula.setText("Buscar");
        btnBuscarAula.setBorder(null);
        btnBuscarAula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarAula.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel4.add(btnBuscarAula, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, -1, -1));
        jPanel4.add(txtNombreAula, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 162, -1));

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 153, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel4.setText("Ubicacion:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 222, -1, -1));
        jPanel4.add(txtUbicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 219, 162, -1));

        jLabel5.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel5.setText("Capacidad:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 287, -1, -1));
        jPanel4.add(txtCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 284, 162, -1));

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(null);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 95, -1));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-x.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(null);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, 96, 31));

        jLabel13.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel13.setText("Fecha de asignación:");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 222, -1, -1));

        jLabel12.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel12.setText("Nombre del curso:");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, -1, -1));
        jPanel4.add(txtNombreCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 166, -1));
        jPanel4.add(txtCodigocurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, 38, -1));
        jPanel4.add(fechaDeAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 166, -1));

        btnBuscarCurso.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarCurso.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnBuscarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscarCurso.setText("Buscar");
        btnBuscarCurso.setBorder(null);
        btnBuscarCurso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarCurso.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel4.add(btnBuscarCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 90, -1, -1));

        jLabel14.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel14.setText("Precio:");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, -1, -1));
        jPanel4.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 166, -1));

        javax.swing.GroupLayout jDlgAsigAulaLayout = new javax.swing.GroupLayout(jDlgAsigAula.getContentPane());
        jDlgAsigAula.getContentPane().setLayout(jDlgAsigAulaLayout);
        jDlgAsigAulaLayout.setHorizontalGroup(
            jDlgAsigAulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
        );
        jDlgAsigAulaLayout.setVerticalGroup(
            jDlgAsigAulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);

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

        jtxtTituloMenu.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jtxtTituloMenu.setForeground(new java.awt.Color(155, 12, 27));
        jtxtTituloMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/diagrama.png"))); // NOI18N
        jtxtTituloMenu.setText("Asignar aulas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
                .addComponent(btnAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtxtTituloMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtxtTituloMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
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
                        .addComponent(btnAsignar)))
                .addGap(28, 28, 28))
        );

        tblAsigAula.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        tblAsigAula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Asignacion", "Curso", "Aula", "Fecha de asignacion"
            }
        ));
        jScrollPane1.setViewportView(tblAsigAula);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1135, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnBuscarAula;
    private javax.swing.JButton btnBuscarCurso;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCargarAula;
    private javax.swing.JButton btnCargarCurso;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private com.toedter.calendar.JDateChooser fechaDeAsignacion;
    private javax.swing.JDialog jDlgAsigAula;
    private javax.swing.JDialog jDlgCargarAula;
    private javax.swing.JDialog jDlgCargarCuso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel jtxtTituloMenu;
    private javax.swing.JTable tblAsigAula;
    private javax.swing.JTable tblCargarAula;
    private javax.swing.JTable tblCargarCurso;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarAula;
    private javax.swing.JTextField txtBuscarCurso;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtCodigoAula;
    private javax.swing.JTextField txtCodigocurso;
    private javax.swing.JTextField txtNombreAula;
    private javax.swing.JTextField txtNombreCurso;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
