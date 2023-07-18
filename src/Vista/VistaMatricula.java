package Vista;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VistaMatricula extends javax.swing.JInternalFrame {

    public VistaMatricula() {
        initComponents();
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public void setBtnActualizar(JButton btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    public JButton getBtnCargarCur() {
        return btnCargarCur;
    }

    public void setBtnCargarCur(JButton btnCargarCur) {
        this.btnCargarCur = btnCargarCur;
    }

    public JButton getBtnCargarEst() {
        return btnCargarEst;
    }

    public void setBtnCargarEst(JButton btnCargarEst) {
        this.btnCargarEst = btnCargarEst;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JButton getBtnMatricular() {
        return btnMatricular;
    }

    public void setBtnMatricular(JButton btnMatricular) {
        this.btnMatricular = btnMatricular;
    }

    public JDialog getjDlgBuscarCurso() {
        return jDlgBuscarCurso;
    }

    public void setjDlgBuscarCurso(JDialog jDlgBuscarCurso) {
        this.jDlgBuscarCurso = jDlgBuscarCurso;
    }

    public JDialog getjDlgBuscarEstudiante() {
        return jDlgBuscarEstudiante;
    }

    public void setjDlgBuscarEstudiante(JDialog jDlgBuscarEstudiante) {
        this.jDlgBuscarEstudiante = jDlgBuscarEstudiante;
    }

    public JTable getTblDlgEstudiantes() {
        return tblDlgEstudiantes;
    }

    public void setTblDlgEstudiantes(JTable tblDlgEstudiantes) {
        this.tblDlgEstudiantes = tblDlgEstudiantes;
    }

    public JTable getTblDlgjCurso() {
        return tblDlgjCurso;
    }

    public void setTblDlgjCurso(JTable tblDlgjCurso) {
        this.tblDlgjCurso = tblDlgjCurso;
    }

    public JTable getTblMatricula() {
        return tblMatricula;
    }

    public void setTblMatricula(JTable tblMatricula) {
        this.tblMatricula = tblMatricula;
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

    public JTextField getTxtBuscarEst() {
        return txtBuscarEst;
    }

    public void setTxtBuscarEst(JTextField txtBuscarEst) {
        this.txtBuscarEst = txtBuscarEst;
    }

    public JButton getBtnBuscarCurso() {
        return btnBuscarCurso;
    }

    public void setBtnBuscarCurso(JButton btnBuscarCurso) {
        this.btnBuscarCurso = btnBuscarCurso;
    }

    public JButton getBtnBuscarEstudiante() {
        return btnBuscarEstudiante;
    }

    public void setBtnBuscarEstudiante(JButton btnBuscarEstudiante) {
        this.btnBuscarEstudiante = btnBuscarEstudiante;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public JDateChooser getFechaDeMatricula() {
        return fechaDeMatricula;
    }

    public void setFechaDeMatricula(JDateChooser fechaDeMatricula) {
        this.fechaDeMatricula = fechaDeMatricula;
    }

    public JDialog getjDlgMatricula() {
        return jDlgMatricula;
    }

    public void setjDlgMatricula(JDialog jDlgMatricula) {
        this.jDlgMatricula = jDlgMatricula;
    }

    public JTextField getTxtCedulaAdministrador() {
        return txtCedulaAdministrador;
    }

    public void setTxtCedulaAdministrador(JTextField txtCedulaAdministrador) {
        this.txtCedulaAdministrador = txtCedulaAdministrador;
    }

    public JTextField getTxtCedulaEstudiante() {
        return txtCedulaEstudiante;
    }

    public void setTxtCedulaEstudiante(JTextField txtCedulaEstudiante) {
        this.txtCedulaEstudiante = txtCedulaEstudiante;
    }

    public JTextField getTxtCodigoCurso() {
        return txtCodigoCurso;
    }

    public void setTxtCodigoCurso(JTextField txtCodigoCurso) {
        this.txtCodigoCurso = txtCodigoCurso;
    }

    public JTextField getTxtNombreCurso() {
        return txtNombreCurso;
    }

    public void setTxtNombreCurso(JTextField txtNombreCurso) {
        this.txtNombreCurso = txtNombreCurso;
    }

    public JTextField getTxtNombreYApellidoAdministrador() {
        return txtNombreYApellidoAdministrador;
    }

    public void setTxtNombreYApellidoAdministrador(JTextField txtNombreYApellidoAdministrador) {
        this.txtNombreYApellidoAdministrador = txtNombreYApellidoAdministrador;
    }

    public JTextField getTxtNombreYApellidoEstudiante() {
        return txtNombreYApellidoEstudiante;
    }

    public void setTxtNombreYApellidoEstudiante(JTextField txtNombreYApellidoEstudiante) {
        this.txtNombreYApellidoEstudiante = txtNombreYApellidoEstudiante;
    }

    public JTextField getTxtCodigoAdministrador() {
        return txtCodigoAdministrador;
    }

    public void setTxtCodigoAdministrador(JTextField txtCodigoAdministrador) {
        this.txtCodigoAdministrador = txtCodigoAdministrador;
    }

    public JTextField getTxtCodigoEstudiantematri() {
        return txtCodigoEstudiantematri;
    }

    public void setTxtCodigoEstudiantematri(JTextField txtCodigoEstudiantematri) {
        this.txtCodigoEstudiantematri = txtCodigoEstudiantematri;
    }

    public JTextField getTxtCorreoEstudiante() {
        return txtCorreoEstudiante;
    }

    public void setTxtCorreoEstudiante(JTextField txtCorreoEstudiante) {
        this.txtCorreoEstudiante = txtCorreoEstudiante;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDlgBuscarEstudiante = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtBuscarEst = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDlgEstudiantes = new javax.swing.JTable();
        btnCargarEst = new javax.swing.JButton();
        jDlgBuscarCurso = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtBuscarCur = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDlgjCurso = new javax.swing.JTable();
        btnCargarCur = new javax.swing.JButton();
        jDlgMatricula = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        fechaDeMatricula = new com.toedter.calendar.JDateChooser();
        txtNombreYApellidoAdministrador = new javax.swing.JTextField();
        btnBuscarCurso = new javax.swing.JButton();
        txtCedulaAdministrador = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        txtNombreYApellidoEstudiante = new javax.swing.JTextField();
        txtCodigoCurso = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtCedulaEstudiante = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel = new javax.swing.JLabel();
        txtCodigoAdministrador = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        txtCodigoEstudiantematri = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtNombreCurso = new javax.swing.JTextField();
        btnBuscarEstudiante = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtCorreoEstudiante = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMatricula = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnMatricular = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jtxtTituloMenu = new javax.swing.JLabel();

        jDlgBuscarEstudiante.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/personas.png"))); // NOI18N
        jLabel19.setText("Cargar estudiantes");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel17.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel17.setText("Buscar:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 76, -1, -1));
        jPanel3.add(txtBuscarEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 73, 164, -1));

        tblDlgEstudiantes.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        tblDlgEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblDlgEstudiantes);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 134, 677, 260));

        btnCargarEst.setBackground(new java.awt.Color(255, 255, 255));
        btnCargarEst.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnCargarEst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/asignar.png"))); // NOI18N
        btnCargarEst.setText("Cargar");
        btnCargarEst.setBorder(null);
        jPanel3.add(btnCargarEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 65, 86, 39));

        javax.swing.GroupLayout jDlgBuscarEstudianteLayout = new javax.swing.GroupLayout(jDlgBuscarEstudiante.getContentPane());
        jDlgBuscarEstudiante.getContentPane().setLayout(jDlgBuscarEstudianteLayout);
        jDlgBuscarEstudianteLayout.setHorizontalGroup(
            jDlgBuscarEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );
        jDlgBuscarEstudianteLayout.setVerticalGroup(
            jDlgBuscarEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
        );

        jDlgBuscarCurso.setResizable(false);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/diploma (1).png"))); // NOI18N
        jLabel21.setText("Cargar Curso");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));
        jPanel4.add(txtBuscarCur, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 71, 164, -1));

        jLabel22.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel22.setText("Buscar:");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 74, -1, -1));

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
        jScrollPane4.setViewportView(tblDlgjCurso);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 141, 552, 260));

        btnCargarCur.setBackground(new java.awt.Color(255, 255, 255));
        btnCargarCur.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnCargarCur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/asignar.png"))); // NOI18N
        btnCargarCur.setText("Cargar");
        btnCargarCur.setBorder(null);
        jPanel4.add(btnCargarCur, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 63, 88, 39));

        javax.swing.GroupLayout jDlgBuscarCursoLayout = new javax.swing.GroupLayout(jDlgBuscarCurso.getContentPane());
        jDlgBuscarCurso.getContentPane().setLayout(jDlgBuscarCursoLayout);
        jDlgBuscarCursoLayout.setHorizontalGroup(
            jDlgBuscarCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
        );
        jDlgBuscarCursoLayout.setVerticalGroup(
            jDlgBuscarCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );

        jDlgMatricula.setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(fechaDeMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 180, -1));
        jPanel2.add(txtNombreYApellidoAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 166, -1));

        btnBuscarCurso.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarCurso.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnBuscarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscarCurso.setText("Buscar");
        btnBuscarCurso.setBorder(null);
        btnBuscarCurso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarCurso.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(btnBuscarCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 360, -1, -1));
        jPanel2.add(txtCedulaAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 162, -1));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-x.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(null);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, 96, 31));
        jPanel2.add(txtNombreYApellidoEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 179, 162, -1));
        jPanel2.add(txtCodigoCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 390, 42, -1));

        jLabel29.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel29.setText("Información del curso");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, -1, -1));
        jPanel2.add(txtCedulaEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 122, 162, -1));

        jLabel23.setFont(new java.awt.Font("Roboto Black", 0, 28)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/matricula.png"))); // NOI18N
        jLabel23.setText("Registrar matrícula");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 23, -1, -1));

        jLabel31.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel31.setText("Fecha de matrícula:");
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, -1, -1));

        jLabel.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel.setText("Correo: ");
        jPanel2.add(jLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));
        jPanel2.add(txtCodigoAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, 42, -1));

        jLabel27.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel27.setText("Información del estudiante");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 86, -1, -1));

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(null);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 510, 95, -1));
        jPanel2.add(txtCodigoEstudiantematri, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 42, -1));

        jLabel24.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel24.setText("Nombre y apellido:");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        jLabel25.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel25.setText("Nombre y apellido:");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 182, -1, -1));

        jLabel28.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel28.setText("Información del administrador");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));
        jPanel2.add(txtNombreCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 162, -1));

        btnBuscarEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarEstudiante.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnBuscarEstudiante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscarEstudiante.setText("Buscar");
        btnBuscarEstudiante.setBorder(null);
        btnBuscarEstudiante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarEstudiante.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(btnBuscarEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(337, 121, -1, -1));

        jLabel30.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel30.setText("Cédula:");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 125, -1, -1));

        jLabel26.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel26.setText("Cédula:");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));
        jPanel2.add(txtCorreoEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 162, -1));

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 360, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 790, 10));

        javax.swing.GroupLayout jDlgMatriculaLayout = new javax.swing.GroupLayout(jDlgMatricula.getContentPane());
        jDlgMatricula.getContentPane().setLayout(jDlgMatriculaLayout);
        jDlgMatriculaLayout.setHorizontalGroup(
            jDlgMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
        );
        jDlgMatriculaLayout.setVerticalGroup(
            jDlgMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
        );

        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblMatricula.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        tblMatricula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Número de matricula", "Cédula del estudiante", "Nombre y apellido del estudiante", "Cédula del administrador", "Nombre y apellido del administrador", "Nombre del curso"
            }
        ));
        jScrollPane1.setViewportView(tblMatricula);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel1.setText("Buscar:");

        btnMatricular.setBackground(new java.awt.Color(255, 255, 255));
        btnMatricular.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnMatricular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/AñadirDetalles.png"))); // NOI18N
        btnMatricular.setText("Matricular");
        btnMatricular.setBorder(null);
        btnMatricular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(null);
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/compartimiento.png"))); // NOI18N
        btnEliminar.setText("Anular");
        btnEliminar.setBorder(null);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jtxtTituloMenu.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jtxtTituloMenu.setForeground(new java.awt.Color(155, 12, 27));
        jtxtTituloMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/matricula_1.png"))); // NOI18N
        jtxtTituloMenu.setText("Matrícula");

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
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 440, Short.MAX_VALUE)
                        .addComponent(btnMatricular, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(184, 184, 184))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jtxtTituloMenu)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtxtTituloMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEliminar)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMatricular)))
                .addGap(23, 23, 23))
        );

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscarCurso;
    private javax.swing.JButton btnBuscarEstudiante;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCargarCur;
    private javax.swing.JButton btnCargarEst;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMatricular;
    private com.toedter.calendar.JDateChooser fechaDeMatricula;
    private javax.swing.JDialog jDlgBuscarCurso;
    private javax.swing.JDialog jDlgBuscarEstudiante;
    private javax.swing.JDialog jDlgMatricula;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jtxtTituloMenu;
    private javax.swing.JTable tblDlgEstudiantes;
    private javax.swing.JTable tblDlgjCurso;
    private javax.swing.JTable tblMatricula;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarCur;
    private javax.swing.JTextField txtBuscarEst;
    private javax.swing.JTextField txtCedulaAdministrador;
    private javax.swing.JTextField txtCedulaEstudiante;
    private javax.swing.JTextField txtCodigoAdministrador;
    private javax.swing.JTextField txtCodigoCurso;
    private javax.swing.JTextField txtCodigoEstudiantematri;
    private javax.swing.JTextField txtCorreoEstudiante;
    private javax.swing.JTextField txtNombreCurso;
    private javax.swing.JTextField txtNombreYApellidoAdministrador;
    private javax.swing.JTextField txtNombreYApellidoEstudiante;
    // End of variables declaration//GEN-END:variables
}
