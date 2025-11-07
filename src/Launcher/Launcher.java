package Launcher;

import Controlador.RuletaController;
import Controlador.SessionController;
import Vista.VentanaLogin;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Ruleta con Persistencia...");

        SessionController sessionController = new SessionController();
        RuletaController ruletaController = new RuletaController();

        ruletaController.inicializarSistema();

        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaLogin(sessionController).mostrarVentana();
        });

        agregarShutdownHook(sessionController);
    }

    private static void agregarShutdownHook(SessionController sessionController) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Cerrando aplicacion, guardando estado...");
            sessionController.guardarEstado();
            System.out.println("Estado guardado exitosamente");
        }));
    }
}


