package Controlador;

import Modelo.Ruleta;
import Modelo.TipoApuesta;

public class RuletaController {

    private final Ruleta ruleta = new Ruleta();

    public int girarRuleta() { return Ruleta.girarRuleta(); }

    public boolean evaluarResultado(int numero, TipoApuesta tipo) { return ruleta.evaluarResultado(numero, tipo); }

    public void registrarResultado(int numero, TipoApuesta tipo, int apuesta, boolean acierto) { ruleta.registrarResultado(numero, tipo, apuesta, acierto); }

    public String getEstadisticas() { return ruleta.getEstadisticas(); }

    public int getSaldo() { return ruleta.getSaldo(); }

    public void depositar(int monto) { ruleta.depositar(monto); }

    public boolean retirar(int monto) { return ruleta.retirar(monto); }
}




