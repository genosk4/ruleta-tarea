package Controlador;

import Modelo.*;
import java.util.List;
import java.util.stream.Collectors;

public class RuletaController {
    private final Ruleta ruleta = new Ruleta();

    public Resultado procesarApuesta(Usuario usuario, TipoApuesta tipo, int monto) {

        ApuestaBase apuesta = crearApuestaDesdeTipo(tipo, monto);

        if (usuario.getSaldo() < apuesta.getMonto()) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar la apuesta");
        }

        // Retirar monto del usuario
        if (!usuario.retirar(apuesta.getMonto())) {
            throw new IllegalArgumentException("Error al retirar fondos para la apuesta");
        }

        ResultadoJuego resultadoJuego = ruleta.jugar(apuesta);

        if (resultadoJuego.isAcierto()) {
            usuario.depositar(resultadoJuego.getGanancia());
        }

        Resultado resultado = new Resultado(
                resultadoJuego.getNumeroGanador(),
                tipo,
                monto,
                resultadoJuego.isAcierto()
        );


        usuario.registrarResultado(resultado);

        return resultado;
    }

    private ApuestaBase crearApuestaDesdeTipo(TipoApuesta tipo, int monto) {
        switch (tipo) {
            case ROJO: return new ApuestaRojo(monto);
            case NEGRO: return new ApuestaNegro(monto);
            case PAR: return new ApuestaPar(monto);
            case IMPAR: return new ApuestaImpar(monto);
            default: throw new IllegalArgumentException("Tipo de apuesta no válido: " + tipo);
        }
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

    public String getEstadisticasUsuario(Usuario usuario) {
        List<Resultado> historial = usuario.getHistorial();
        int totalJugadas = historial.size();
        long ganadas = historial.stream().filter(Resultado::isAcierto).count();
        long perdidas = totalJugadas - ganadas;

        int totalApostado = historial.stream()
                .mapToInt(Resultado::getMonto)
                .sum();

        int gananciaNeta = historial.stream()
                .mapToInt(r -> r.isAcierto() ? r.getMonto() : -r.getMonto())
                .sum();

        return "Estadísticas del usuario:\n" +
                "Total de jugadas: " + totalJugadas + "\n" +
                "Ganadas: " + ganadas + "\n" +
                "Perdidas: " + perdidas + "\n" +
                "Total apostado: $" + totalApostado + "\n" +
                "Ganancia/Pérdida neta: $" + gananciaNeta + "\n" +
                "Saldo actual: $" + usuario.getSaldo();
    }
}