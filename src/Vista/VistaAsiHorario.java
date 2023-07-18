package Vista;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VistaAsiHorario extends javax.swing.JInternalFrame {

    public VistaAsiHorario() {
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

    public JButton getBtnBuscarCurso() {
        return btnBuscarCurso;
    }

    public void setBtnBuscarCurso(JButton btnBuscarCurso) {
        this.btnBuscarCurso = btnBuscarCurso;
    }

    public JButton getBtnBuscarHorario() {
        return btnBuscarHorario;
    }

    public void setBtnBuscarHorario(JButton btnBuscarHorario) {
        this.btnBuscarHorario = btnBuscarHorario;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JButton getBtnCargarCur() {
        return btnCargarCur;
    }

    public void setBtnCargarCur(JButton btnCargarCur) {
        this.btnCargarCur = btnCargarCur;
    }

    public JButton getBtnCargarHor() {
        return btnCargarHor;
    }

    public void setBtnCargarHor(JButton btnCargarHor) {
        this.btnCargarHor = btnCargarHor;
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

    public JDialog getjDlgAsignarHorario() {
        return jDlgAsignarHorario;
    }

    public void setjDlgAsignarHorario(JDialog jDlgAsignarHorario) {
        this.jDlgAsignarHorario = jDlgAsignarHorario;
    }

    public JDialog getjDlgBuscarCurso() {
        return jDlgBuscarCurso;
    }

    public void setjDlgBuscarCurso(JDialog jDlgBuscarCurso) {
        this.jDlgBuscarCurso = jDlgBuscarCurso;
    }

    public JDialog getjDlgBuscarHorario() {
        return jDlgBuscarHorario;
    }

    public void setjDlgBuscarHorario(JDialog jDlgBuscarHorario) {
        this.jDlgBuscarHorario = jDlgBuscarHorario;
    }

    public JTable getTblDlgjCurso() {
        return tblDlgjCurso;
    }

    public void setTblDlgjCurso(JTable tblDlgjCurso) {
        this.tblDlgjCurso = tblDlgjCurso;
    }

    public JTable getTblDlgjHorario() {
        return tblDlgjHorario;
    }

    public void setTblDlgjHorario(JTable tblDlgjHorario) {
        this.tblDlgjHorario = tblDlgjHorario;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JTextField getTxtBuscarCur() {
        return txtBuscarCur;
    }

    public void setTxtBuscarCur(JTextField txtBuscarCur) {
        this.txtBuscarCur = txtBuscarCur;
    }

    public JTextField getTxtBuscarHor() {
        return txtBuscarHor;
    }

    public void setTxtBuscarHor(JTextField txtBuscarHor) {
        this.txtBuscarHor = txtBuscarHor;
    }

    public JTextField getTxtCodigoCurso() {
        return txtCodigoCurso;
    }

    public void setTxtCodigoCurso(JTextField txtCodigoCurso) {
        this.txtCodigoCurso = txtCodigoCurso;
    }

    public JTextField getTxtCodigoHorario() {
        return txtCodigoHorario;
    }

    public void setTxtCodigoHorario(JTextField txtCodigoHorario) {
        this.txtCodigoHorario = txtCodigoHorario;
    }

    public JTextField getTxtDiaHorario() {
        return txtDiaHorario;
    }

    public void setTxtDiaHorario(JTextField txtDiaHorario) {
        this.txtDiaHorario = txtDiaHorario;
    }

    public JTextField getTxtHoraDeFin() {
        return txtHoraDeFin;
    }

    public void setTxtHoraDeFin(JTextField txtHoraDeFin) {
        this.txtHoraDeFin = txtHoraDeFin;
    }

    public JTextField getTxtHoraDeInicio() {
        return txtHoraDeInicio;
    }

    public void setTxtHoraDeInicio(JTextField txtHoraDeInicio) {
        this.txtHoraDeInicio = txtHoraDeInicio;
    }

    public JTextField getTxtNombreCurso() {
        return txtNombreCurso;
    }

    public void setTxtNombreCurso(JTextField txtNombreCurso) {
        this.txtNombreCurso = txtNombreCurso;
    }

    public JTextField getTxtPeriodoCurso() {
        return txtPeriodoCurso;
    }

    public void setTxtPeriodoCurso(JTextField txtPeriodoCurso) {
        this.txtPeriodoCurso = txtPeriodoCurso;
    }

    public JTable getTblAsiHorario() {
        return tblAsiHorario;
    }

    public void setTblAsiHorario(JTable tblAsiHorario) {
        this.tblAsiHorario = tblAsiHorario;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDlgBuscarCurso = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        btnCargarCur = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtBuscarCur = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDlgjCurso = new javax.swing.JTable();
        jDlgBuscarHorario = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtBuscarHor = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDlgjHorario = new javax.swing.JTable();
        btnCargarHor = new javax.swing.JButton();
        jDlgAsignarHorario = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        txtPeriodoCurso = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fechaDeAsignacion = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        txtNombreCurso = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCodigoCurso = new javax.swing.JTextField();
        btnBuscarCurso = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDiaHorario = new javax.swing.JTextField();
        txtCodigoHorario = new javax.swing.JTextField();
        btnBuscarHorario = new javax.swing.JButton();
        txtHoraDeInicio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtHoraDeFin = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnAsignar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jtxtTituloMenu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsiHorario = new javax.swing.JTable();

        jDlgBuscarCurso.setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnCargarCur.setBackground(new java.awt.Color(255, 255, 255));
        btnCargarCur.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnCargarCur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/asignar.png"))); // NOI18N
        btnCargarCur.setText("Cargar");
        btnCargarCur.setBorder(null);

        jLabel19.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/diploma (1).png"))); // NOI18N
        jLabel19.setText("Cargar curso");

        jLabel17.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel17.setText("Buscar:");

        tblDlgjCurso.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        tblDlgjCurso.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblDlgjCurso);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addContainerGap(342, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(30, 30, 30)
                        .addComponent(txtBuscarCur, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCargarCur, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtBuscarCur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarCur))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDlgBuscarCursoLayout = new javax.swing.GroupLayout(jDlgBuscarCurso.getContentPane());
        jDlgBuscarCurso.getContentPane().setLayout(jDlgBuscarCursoLayout);
        jDlgBuscarCursoLayout.setHorizontalGroup(
            jDlgBuscarCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDlgBuscarCursoLayout.setVerticalGroup(
            jDlgBuscarCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDlgBuscarHorario.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendario.png"))); // NOI18N
        jLabel20.setText("Cargar horario");

        jLabel18.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel18.setText("Buscar:");

        tblDlgjHorario.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        tblDlgjHorario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Dia de la semana", "Hora de inicio", "Hora fin"
            }
        ));
        jScrollPane3.setViewportView(tblDlgjHorario);

        btnCargarHor.setBackground(new java.awt.Color(255, 255, 255));
        btnCargarHor.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnCargarHor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/asignar.png"))); // NOI18N
        btnCargarHor.setText("Cargar");
        btnCargarHor.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addGap(28, 28, 28)
                            .addComponent(txtBuscarHor, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCargarHor, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCargarHor)
                    .addComponent(txtBuscarHor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDlgBuscarHorarioLayout = new javax.swing.GroupLayout(jDlgBuscarHorario.getContentPane());
        jDlgBuscarHorario.getContentPane().setLayout(jDlgBuscarHorarioLayout);
        jDlgBuscarHorarioLayout.setHorizontalGroup(
            jDlgBuscarHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDlgBuscarHorarioLayout.setVerticalGroup(
            jDlgBuscarHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDlgAsignarHorario.setResizable(false);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(txtPeriodoCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 174, 168, -1));

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel2.setText("Periodo:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 177, -1, -1));
        jPanel4.add(fechaDeAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(617, 70, 166, -1));

        jLabel3.setFont(new java.awt.Font("Roboto Black", 0, 28)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/profesor.png"))); // NOI18N
        jLabel3.setText("Asignar horario ");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 13, -1, -1));

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(null);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 462, 95, -1));

        jLabel6.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel6.setText("Nombre:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 112, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-x.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(null);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, 96, 31));
        jPanel4.add(txtNombreCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 109, 168, -1));

        jLabel13.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel13.setText("Fecha de asignación:");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, -1, -1));
        jPanel4.add(txtCodigoCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 145, 42, -1));

        btnBuscarCurso.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarCurso.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnBuscarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscarCurso.setText("Buscar");
        btnBuscarCurso.setBorder(null);
        btnBuscarCurso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarCurso.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel4.add(btnBuscarCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 108, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel7.setText("Información del curso");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 153, -1));

        jLabel8.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel8.setText("Información del horario");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 229, 153, -1));

        jLabel9.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel9.setText("Dia:");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 277, -1, -1));
        jPanel4.add(txtDiaHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 274, 168, -1));
        jPanel4.add(txtCodigoHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 311, 42, -1));

        btnBuscarHorario.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarHorario.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnBuscarHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscarHorario.setText("Buscar");
        btnBuscarHorario.setBorder(null);
        btnBuscarHorario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarHorario.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel4.add(btnBuscarHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 273, -1, -1));
        jPanel4.add(txtHoraDeInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 339, 168, -1));

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel4.setText("Hora de inicio:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 342, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel5.setText("Hora fin:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 402, -1, -1));
        jPanel4.add(txtHoraDeFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 399, 168, -1));

        javax.swing.GroupLayout jDlgAsignarHorarioLayout = new javax.swing.GroupLayout(jDlgAsignarHorario.getContentPane());
        jDlgAsignarHorario.getContentPane().setLayout(jDlgAsignarHorarioLayout);
        jDlgAsignarHorarioLayout.setHorizontalGroup(
            jDlgAsignarHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDlgAsignarHorarioLayout.setVerticalGroup(
            jDlgAsignarHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        jtxtTituloMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eleccion.png"))); // NOI18N
        jtxtTituloMenu.setText("Asignar horarios");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addComponent(btnAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
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
                .addContainerGap())
        );

        tblAsiHorario.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        tblAsiHorario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código de asignación", "Nombre del curso", "Dia", "Hora de inicio", "Hora fin"
            }
        ));
        jScrollPane1.setViewportView(tblAsiHorario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnBuscarCurso;
    private javax.swing.JButton btnBuscarHorario;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCargarCur;
    private javax.swing.JButton btnCargarHor;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private com.toedter.calendar.JDateChooser fechaDeAsignacion;
    private javax.swing.JDialog jDlgAsignarHorario;
    private javax.swing.JDialog jDlgBuscarCurso;
    private javax.swing.JDialog jDlgBuscarHorario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jtxtTituloMenu;
    private javax.swing.JTable tblAsiHorario;
    private javax.swing.JTable tblDlgjCurso;
    private javax.swing.JTable tblDlgjHorario;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarCur;
    private javax.swing.JTextField txtBuscarHor;
    private javax.swing.JTextField txtCodigoCurso;
    private javax.swing.JTextField txtCodigoHorario;
    private javax.swing.JTextField txtDiaHorario;
    private javax.swing.JTextField txtHoraDeFin;
    private javax.swing.JTextField txtHoraDeInicio;
    private javax.swing.JTextField txtNombreCurso;
    private javax.swing.JTextField txtPeriodoCurso;
    // End of variables declaration//GEN-END:variables

}
