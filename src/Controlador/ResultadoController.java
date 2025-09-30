package Controlador;

import Modelo.Ruleta;
import Modelo.Resultado;

public class ResultadoController {
    public static String historialToString() {
        StringBuilder sb = new StringBuilder();
        for (Resultado r : Ruleta.getHistorial()) {
            sb.append("N°: ").append(r.getNumero())
                    .append(" | Tipo: ").append(r.getTipoApuesta())
                    .append(" | Monto: $").append(r.getMonto())
                    .append(" | Resultado: ").append(r.isAcierto() ? "Ganó" : "Perdió")
                    .append("\n");
        }
        return sb.length() == 0 ? "No hay partidas jugadas aún." : sb.toString();
    }
}
