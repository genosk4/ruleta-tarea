package Controlador;

import Modelo.Ruleta;
import Modelo.TipoApuesta;

public class RuletaController {

    public static String jugar(int monto, TipoApuesta tipo) {
        var usuario = SessionController.getUsuarioActual();

        if (!usuario.retirar(monto)) {
            return "Saldo insuficiente.";
        }

        int numero = Ruleta.girarRuleta();
        boolean acierto = Ruleta.evaluarResultado(numero, tipo);
        Ruleta.registrarResultado(numero, tipo, monto, acierto);

        if (acierto) usuario.depositar(monto * 2);

        return "Número obtenido: " + numero +
                "\nApuesta: " + tipo + " | Monto: $" + monto +
                "\n" + (acierto ? "¡Ganaste!" : "Perdiste");
    }
}

