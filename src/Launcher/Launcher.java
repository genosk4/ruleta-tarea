package Launcher;

import Controlador.RuletaController;
import Controlador.SessionController;
import Vista.VentanaLogin;

public class Launcher {
    public static void main(String[] args) {
        boolean usarArchivo = true;

        SessionController sessionController = new SessionController(usarArchivo);

        RuletaController ruletaController = new RuletaController(sessionController.getRepositorio());

        System.out.println("Usando: " +
                (usarArchivo ? "RepositorioArchivo (CSV)" : "RepositorioEnMemoria"));

        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaLogin(sessionController).mostrarVentana();
        });
    }
}


