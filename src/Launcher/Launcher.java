package Launcher;

import Controlador.SessionController;
import Vista.VentanaLogin;

public class Launcher {
    public static void main(String[] args) {
        boolean usarArchivo = true;
        SessionController sessionController = new SessionController(usarArchivo);

        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaLogin(sessionController).mostrarVentana();
        });

        agregarShutdownHook(sessionController);
    }

    private static void agregarShutdownHook(SessionController sessionController) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            sessionController.guardarEstado();
            System.out.println("Estado guardado exitosamente");
        }));
    }
}


