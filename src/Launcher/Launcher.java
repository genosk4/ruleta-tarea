package Launcher;

import Controlador.RuletaController;
import Controlador.SessionController;
import Vista.VentanaLogin;

public class Launcher {
    public static void main(String[] args) {
        SessionController sessionController = new SessionController();
        RuletaController ruletaController = new RuletaController();


        sessionController.registrarUsuario("daniel", "1234", "Daniel Lincopi");

        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaLogin(sessionController).mostrarVentana();
        });
    }
}


