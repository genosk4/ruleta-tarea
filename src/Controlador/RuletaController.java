package Controlador;

import Modelo.Resultado;
import Modelo.Ruleta;
import Modelo.TipoApuesta;
import Modelo.Usuario;

public class RuletaController {

    private final Ruleta ruleta = new Ruleta();

    public int girarRuleta() {

        return ruleta.girarRuleta();
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


    public Resultado procesarApuesta(Usuario usuario, TipoApuesta tipo, int monto) {

        if (!retirar(usuario, monto)) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar la apuesta");
        }


        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipo);


        if (acierto) {
            depositar(usuario, monto * 2);
        }


        registrarResultado(usuario, numero, tipo, monto, acierto);

        return new Resultado(numero, tipo, monto, acierto);
    }


    public String getEstadisticasUsuario(Usuario usuario) {
        int totalJugadas = usuario.getHistorial().size();
        long ganadas = usuario.getHistorial().stream().filter(Resultado::isAcierto).count();
        long perdidas = totalJugadas - ganadas;

        return "Estadísticas del usuario:\n" +
                "Total de jugadas: " + totalJugadas + "\n" +
                "Ganadas: " + ganadas + "\n" +
                "Perdidas: " + perdidas + "\n";
    }
}





