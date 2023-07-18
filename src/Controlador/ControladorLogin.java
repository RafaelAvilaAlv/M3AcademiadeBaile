package Controlador;

import Modelo.Administrador;
import Modelo.ModeloAdministrador;
import Vista.VistaLogin;
import Vista.VistaPrincipal;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ControladorLogin {

    VistaLogin vista;

    static boolean encontrar;//Variable para saber si el usuario y la contraseña estan en la BD
    static String usuario;

    public ControladorLogin(VistaLogin vista) {
        this.vista = vista;
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        crarPrincip();
        Cargarpinci();

    }

    public void iniciarControl() {
        vista.getBtnIniciarSesion().addActionListener(l -> login());
        vista.getjButton1().addActionListener(l -> crarImagen1());
        vista.getjButton2().addActionListener(l -> crarImagen2());
        vista.getjButton3().addActionListener(l -> crarImagen3());
        vista.getTxtUsuario().addActionListener(l -> crarPrincip());
        vista.getTxtContrasenia().addActionListener(l -> crarPrincip());
        vista.getTxtContrasenia().setText("");
        vista.getLblOcultar().setVisible(true);
        vista.getLblMostrar().setVisible(false);
        verContrasenia();
        ocultarContrasenia();
    }

    public void login() {
        encontrar = false;

        ModeloAdministrador modeloAdministrador = new ModeloAdministrador();
        List<Administrador> listAdmin = modeloAdministrador.listaAdministradoresTabla();

        listAdmin.stream().forEach(a -> {

            if (a.getAdm_usuario().equals(vista.getTxtUsuario().getText()) && a.getAdm_clave().equals(vista.getTxtContrasenia().getText())) {
                vista.setVisible(false);//Cierro la ventana del login y abro la ventana principal 
                encontrar = true;//El usuario y la contraseña ingresados por el usuario son iguales a los que estan en la BD

                usuario = vista.getTxtUsuario().getText();//Guardo el usuario para luego usarlo en la matricula

                VistaPrincipal vistaPrincipal = new VistaPrincipal();
                ControladorPrincipal control = new ControladorPrincipal(vistaPrincipal);
                control.iniciarControl();
            }
        });

        if (!encontrar) {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto");
        }
    }

    public void verContrasenia() {
        MouseListener evento = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                vista.getTxtContrasenia().setEchoChar('●');
                vista.getLblOcultar().setVisible(true);
                vista.getLblMostrar().setVisible(false);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        vista.getLblMostrar().addMouseListener(evento);
        vista.getTxtContrasenia().addMouseListener(evento);
    }

    public void ocultarContrasenia() {
        MouseListener evento = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {

                vista.getTxtContrasenia().setEchoChar((char) 0);
                vista.getLblMostrar().setVisible(true);
                vista.getLblOcultar().setVisible(false);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        vista.getLblOcultar().addMouseListener(evento);
    }

    public void Cargarpinci() {
        MouseListener evento = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                crarPrincip();

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        vista.getTxtUsuario().addMouseListener(evento);
        vista.getTxtContrasenia().addMouseListener(evento);
    }

    public ImageIcon EscalarImagen(ImageIcon imagenATransformar, JLabel tamañoPanel) {
        ImageIcon imagenEscalada = new ImageIcon(imagenATransformar.getImage().getScaledInstance(tamañoPanel.getWidth(),
                tamañoPanel.getHeight(), Image.SCALE_AREA_AVERAGING));
        return imagenEscalada;

    }
    private ImageIcon iSlider1 = new ImageIcon("src\\imagenes\\slider1.jpg"),
            iSlider2 = new ImageIcon("src\\imagenes\\slider2.jpg"),
            iSlider3 = new ImageIcon("src\\imagenes\\slider3.jpg"),
            Principal = new ImageIcon("src\\imagenes\\imagenlogin.png");

    public void crarImagen1() {
        vista.getLblImagen1().setIcon(EscalarImagen(iSlider2, vista.getLblImagen1()));
    }

    public void crarImagen2() {
        vista.getLblImagen1().setIcon(EscalarImagen(iSlider3, vista.getLblImagen1()));
    }

    public void crarImagen3() {
        vista.getLblImagen1().setIcon(EscalarImagen(iSlider1, vista.getLblImagen1()));
    }

    public void crarPrincip() {
        vista.getLblImagen1().setIcon(EscalarImagen(Principal, vista.getLblImagen1()));
    }

}
