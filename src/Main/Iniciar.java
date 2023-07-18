package Main;

import Controlador.ControladorLogin;
import Vista.VistaLogin;

public class Iniciar {

    public static void main(String[] args) {
        
      VistaLogin vistaLogin = new VistaLogin();
        ControladorLogin control = new ControladorLogin(vistaLogin);
        control.iniciarControl();
    }
}
