package Controlador;

import Modelo.Resultado;
import Modelo.Ruleta;
import Modelo.TipoApuesta;
import Modelo.Usuario;

public class RuletaController {

    private final Ruleta ruleta = new Ruleta();


    public int girarRuleta() {
        return Ruleta.girarRuleta();
    }



    public boolean evaluarResultado(int numero, TipoApuesta tipo) {
        return Ruleta.evaluarResultado(numero, tipo);
    }


    public void registrarResultado(Usuario usuario, int numero, TipoApuesta tipo, int apuesta, boolean acierto) {

        Ruleta.registrarResultado(numero, tipo, apuesta, acierto);


        Resultado r = new Resultado(numero, tipo, apuesta, acierto);
        usuario.registrarResultado(r);
    }


    public String getEstadisticas() {
        return Ruleta.getEstadisticas();
    }


    public int getSaldo(Usuario usuario) {
        return usuario.getSaldo();
    }

    public void depositar(Usuario usuario, int monto) {
        usuario.depositar(monto);
    }

    public boolean retirar(Usuario usuario, int monto) {
        return usuario.retirar(monto);
    }
}





