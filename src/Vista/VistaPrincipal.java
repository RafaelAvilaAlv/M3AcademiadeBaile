/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;

/**
 *
 * @author Usuario
 */
public class VistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form pri
     */
    public VistaPrincipal() {
        initComponents();
        this.setTitle("DashBoard");
    }
public JButton getBtnAdministrador() {
        return btnAdministrador;
    }

    public void setBtnAdministrador(JButton btnAdministrador) {
        this.btnAdministrador = btnAdministrador;
    }

    public JButton getBtnAsignarAsignatura() {
        return btnAsignarAsignatura;
    }

    public void setBtnAsignarAsignatura(JButton btnAsignarAsignatura) {
        this.btnAsignarAsignatura = btnAsignarAsignatura;
    }

    public JButton getBtnAsignatura() {
        return btnAsignatura;
    }

    public void setBtnAsignatura(JButton btnAsignatura) {
        this.btnAsignatura = btnAsignatura;
    }

    public JButton getBtnAula() {
        return btnAula;
    }

    public void setBtnAula(JButton btnAula) {
        this.btnAula = btnAula;
    }

    public JButton getBtnCurso() {
        return btnCurso;
    }

    public void setBtnCurso(JButton btnCurso) {
        this.btnCurso = btnCurso;
    }

    public JButton getBtnDashboard() {
        return btnDashboard;
    }

    public void setBtnDashboard(JButton btnDashboard) {
        this.btnDashboard = btnDashboard;
    }

    public JButton getBtnDocente() {
        return btnDocente;
    }

    public void setBtnDocente(JButton btnDocente) {
        this.btnDocente = btnDocente;
    }

    public JButton getBtnEstudiante() {
        return btnEstudiante;
    }

    public void setBtnEstudiante(JButton btnEstudiante) {
        this.btnEstudiante = btnEstudiante;
    }

    public JButton getBtnProductor() {
        return btnProductor;
    }

    public void setBtnProductor(JButton btnProductor) {
        this.btnProductor = btnProductor;
    }

    public JButton getBtnRegistrarPersona() {
        return btnRegistrarPersona;
    }

    public void setBtnRegistrarPersona(JButton btnRegistrarPersona) {
        this.btnRegistrarPersona = btnRegistrarPersona;
    }

    public JDesktopPane getEscritorioPrincipal() {
        return escritorioPrincipal;
    }

    public void setEscritorioPrincipal(JDesktopPane escritorioPrincipal) {
        this.escritorioPrincipal = escritorioPrincipal;
    }

    public JButton getBtnInstrumento() {
        return btnInstrumento;
    }

    public void setBtnInstrumento(JButton btnInstrumento) {
        this.btnInstrumento = btnInstrumento;
    }

    public JButton getBtnSetGrab() {
        return btnSetGrab;
    }

    public void setBtnSetGrab(JButton btnSetGrab) {
        this.btnSetGrab = btnSetGrab;
    }

    public JButton getBtnHorario() {
        return btnHorario;
    }

    public void setBtnHorario(JButton btnHorario) {
        this.btnHorario = btnHorario;
    }

    public JButton getBtnMatricula() {
        return btnMatricula;
    }

    public void setBtnMatricula(JButton btnMatricula) {
        this.btnMatricula = btnMatricula;
    }

    public JButton getBtnAsiHorario() {
        return btnAsiHorario;
    }

    public void setBtnAsiHorario(JButton btnAsiHorario) {
        this.btnAsiHorario = btnAsiHorario;
    }

    public JButton getBtnReserva() {
        return btnReserva;
    }

    public void setBtnReserva(JButton btnReserva) {
        this.btnReserva = btnReserva;
    }

    public JButton getBtnAsignarAula() {
        return btnAsignarAula;
    }

    public void setBtnAsignarAula(JButton btnAsignarAula) {
        this.btnAsignarAula = btnAsignarAula;
    }

    public JButton getBtnDirigir() {
        return btnDirigir;
    }

    public void setBtnDirigir(JButton btnDirigir) {
        this.btnDirigir = btnDirigir;
    }
    
    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnCurso = new javax.swing.JButton();
        btnAsignatura = new javax.swing.JButton();
        btnAsignarAsignatura = new javax.swing.JButton();
        btnAula = new javax.swing.JButton();
        btnInstrumento = new javax.swing.JButton();
        btnSetGrab = new javax.swing.JButton();
        btnReserva = new javax.swing.JButton();
        btnHorario = new javax.swing.JButton();
        btnAsiHorario = new javax.swing.JButton();
        btnAsignarAula = new javax.swing.JButton();
        btnDirigir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnRegistrarPersona = new javax.swing.JButton();
        btnEstudiante = new javax.swing.JButton();
        btnProductor = new javax.swing.JButton();
        btnDocente = new javax.swing.JButton();
        btnAdministrador = new javax.swing.JButton();
        btnMatricula = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        escritorioPrincipal = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(46, 49, 49));
        jPanel4.setAutoscrolls(true);

        lblUsuario.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuariogrande.png"))); // NOI18N
        lblUsuario.setText("USUARIO");
        lblUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnDashboard.setBackground(new java.awt.Color(46, 49, 49));
        btnDashboard.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dash.png"))); // NOI18N
        btnDashboard.setText("Dashboard");
        btnDashboard.setAutoscrolls(true);
        btnDashboard.setBorder(null);
        btnDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnCurso.setBackground(new java.awt.Color(46, 49, 49));
        btnCurso.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnCurso.setForeground(new java.awt.Color(255, 255, 255));
        btnCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/curso.png"))); // NOI18N
        btnCurso.setText("Curso");
        btnCurso.setAutoscrolls(true);
        btnCurso.setBorder(null);
        btnCurso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCurso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnAsignatura.setBackground(new java.awt.Color(46, 49, 49));
        btnAsignatura.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnAsignatura.setForeground(new java.awt.Color(255, 255, 255));
        btnAsignatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/libros.png"))); // NOI18N
        btnAsignatura.setText("Asignatura");
        btnAsignatura.setAutoscrolls(true);
        btnAsignatura.setBorder(null);
        btnAsignatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAsignatura.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnAsignarAsignatura.setBackground(new java.awt.Color(46, 49, 49));
        btnAsignarAsignatura.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnAsignarAsignatura.setForeground(new java.awt.Color(255, 255, 255));
        btnAsignarAsignatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/asignacionasi.png"))); // NOI18N
        btnAsignarAsignatura.setText("Asignar Asignatura");
        btnAsignarAsignatura.setAutoscrolls(true);
        btnAsignarAsignatura.setBorder(null);
        btnAsignarAsignatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAsignarAsignatura.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnAula.setBackground(new java.awt.Color(46, 49, 49));
        btnAula.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnAula.setForeground(new java.awt.Color(255, 255, 255));
        btnAula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aulan.png"))); // NOI18N
        btnAula.setText("Aula");
        btnAula.setAutoscrolls(true);
        btnAula.setBorder(null);
        btnAula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAula.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnInstrumento.setBackground(new java.awt.Color(46, 49, 49));
        btnInstrumento.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnInstrumento.setForeground(new java.awt.Color(255, 255, 255));
        btnInstrumento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guitarra.png"))); // NOI18N
        btnInstrumento.setText("Instrumento");
        btnInstrumento.setAutoscrolls(true);
        btnInstrumento.setBorder(null);
        btnInstrumento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInstrumento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnSetGrab.setBackground(new java.awt.Color(46, 49, 49));
        btnSetGrab.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnSetGrab.setForeground(new java.awt.Color(255, 255, 255));
        btnSetGrab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/grabar.png"))); // NOI18N
        btnSetGrab.setText("Set de Grabacion");
        btnSetGrab.setAutoscrolls(true);
        btnSetGrab.setBorder(null);
        btnSetGrab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSetGrab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnReserva.setBackground(new java.awt.Color(46, 49, 49));
        btnReserva.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnReserva.setForeground(new java.awt.Color(255, 255, 255));
        btnReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reservaa.png"))); // NOI18N
        btnReserva.setText("Reserva");
        btnReserva.setAutoscrolls(true);
        btnReserva.setBorder(null);
        btnReserva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReserva.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnHorario.setBackground(new java.awt.Color(46, 49, 49));
        btnHorario.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnHorario.setForeground(new java.awt.Color(255, 255, 255));
        btnHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendario_1.png"))); // NOI18N
        btnHorario.setText("Horario");
        btnHorario.setAutoscrolls(true);
        btnHorario.setBorder(null);
        btnHorario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHorario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnAsiHorario.setBackground(new java.awt.Color(46, 49, 49));
        btnAsiHorario.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnAsiHorario.setForeground(new java.awt.Color(255, 255, 255));
        btnAsiHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eleccion.png"))); // NOI18N
        btnAsiHorario.setText("Asignar Horario");
        btnAsiHorario.setAutoscrolls(true);
        btnAsiHorario.setBorder(null);
        btnAsiHorario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnAsignarAula.setBackground(new java.awt.Color(46, 49, 49));
        btnAsignarAula.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnAsignarAula.setForeground(new java.awt.Color(255, 255, 255));
        btnAsignarAula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/diagrama.png"))); // NOI18N
        btnAsignarAula.setText("Asignar aula");
        btnAsignarAula.setAutoscrolls(true);
        btnAsignarAula.setBorder(null);
        btnAsignarAula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAsignarAula.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnDirigir.setBackground(new java.awt.Color(46, 49, 49));
        btnDirigir.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnDirigir.setForeground(new java.awt.Color(255, 255, 255));
        btnDirigir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/banda-sonora.png"))); // NOI18N
        btnDirigir.setText("Asignar director");
        btnDirigir.setAutoscrolls(true);
        btnDirigir.setBorder(null);
        btnDirigir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDirigir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAsignarAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addComponent(btnDirigir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAsignarAula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHorario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSetGrab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInstrumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAsiHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnHorario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnAsiHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnAsignarAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnAula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnAsignarAula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(btnInstrumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSetGrab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnDirigir, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setAutoscrolls(true);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nota-musical.png"))); // NOI18N
        jLabel1.setText("Academia Musical Rednote ");
        jLabel1.setToolTipText("");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnRegistrarPersona.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrarPersona.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnRegistrarPersona.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/personas.png"))); // NOI18N
        btnRegistrarPersona.setToolTipText("Registrar persona");
        btnRegistrarPersona.setBorder(null);
        btnRegistrarPersona.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        btnEstudiante.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnEstudiante.setForeground(new java.awt.Color(153, 0, 0));
        btnEstudiante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/estudi.png"))); // NOI18N
        btnEstudiante.setText("Estudiante");
        btnEstudiante.setToolTipText("Registrar estudiante");
        btnEstudiante.setBorder(null);
        btnEstudiante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnProductor.setBackground(new java.awt.Color(255, 255, 255));
        btnProductor.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnProductor.setForeground(new java.awt.Color(153, 0, 0));
        btnProductor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/productorn.png"))); // NOI18N
        btnProductor.setText("Productor");
        btnProductor.setToolTipText("Registrar productor");
        btnProductor.setBorder(null);
        btnProductor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnDocente.setBackground(new java.awt.Color(255, 255, 255));
        btnDocente.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnDocente.setForeground(new java.awt.Color(153, 0, 0));
        btnDocente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/docente.png"))); // NOI18N
        btnDocente.setText("Docente");
        btnDocente.setToolTipText("Registrar docente");
        btnDocente.setBorder(null);
        btnDocente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnAdministrador.setBackground(new java.awt.Color(255, 255, 255));
        btnAdministrador.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnAdministrador.setForeground(new java.awt.Color(153, 0, 0));
        btnAdministrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gerente.png"))); // NOI18N
        btnAdministrador.setText("Administrador");
        btnAdministrador.setToolTipText("Registrar administrador");
        btnAdministrador.setBorder(null);
        btnAdministrador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnMatricula.setBackground(new java.awt.Color(255, 255, 255));
        btnMatricula.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        btnMatricula.setForeground(new java.awt.Color(153, 0, 0));
        btnMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/matricula_1.png"))); // NOI18N
        btnMatricula.setText("Matrícula");
        btnMatricula.setToolTipText("Realizar matrícula");
        btnMatricula.setBorder(null);
        btnMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(421, 421, 421)
                .addComponent(btnProductor, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(btnDocente, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(btnAdministrador, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegistrarPersona, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addGap(48, 48, 48))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(842, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnProductor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDocente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdministrador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnRegistrarPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );

        jPanel2.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel5.setLayout(new java.awt.BorderLayout());

        escritorioPrincipal.setLayout(new java.awt.BorderLayout());
        jPanel5.add(escritorioPrincipal, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdministrador;
    private javax.swing.JButton btnAsiHorario;
    private javax.swing.JButton btnAsignarAsignatura;
    private javax.swing.JButton btnAsignarAula;
    private javax.swing.JButton btnAsignatura;
    private javax.swing.JButton btnAula;
    private javax.swing.JButton btnCurso;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDirigir;
    private javax.swing.JButton btnDocente;
    private javax.swing.JButton btnEstudiante;
    private javax.swing.JButton btnHorario;
    private javax.swing.JButton btnInstrumento;
    private javax.swing.JButton btnMatricula;
    private javax.swing.JButton btnProductor;
    private javax.swing.JButton btnRegistrarPersona;
    private javax.swing.JButton btnReserva;
    private javax.swing.JButton btnSetGrab;
    private javax.swing.JDesktopPane escritorioPrincipal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
