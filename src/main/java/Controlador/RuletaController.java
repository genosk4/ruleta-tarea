package Controlador;

import Modelo.*;
import java.util.List;
import java.util.stream.Collectors;

public class RuletaController {
    private Ruleta ruleta;
    private final IRepositorioResultados repositorio;

    public RuletaController(IRepositorioResultados repositorio) {
        this.repositorio = repositorio;
        this.ruleta = new Ruleta(repositorio);
    }

    public Resultado procesarApuesta(Usuario usuario, TipoApuesta tipo, int monto) {

        if (usuario == null) {
            throw new IllegalStateException("No hay usuario autenticado para realizar la apuesta");
        }

        ApuestaBase apuesta = crearApuestaDesdeTipo(tipo, monto);

        if (usuario.getSaldo() < apuesta.getMonto()) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar la apuesta");
        }

        if (!usuario.retirar(apuesta.getMonto())) {
            throw new IllegalArgumentException("Error al retirar fondos para la apuesta");
        }

        try {

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
        }catch (Exception e) {
            usuario.depositar(monto);
            throw new RuntimeException("Error excepcional durante el juego: " + e.getMessage(), e);
        }
    }

    private ApuestaBase crearApuestaDesdeTipo(TipoApuesta tipo, int monto) {
        switch (tipo) {
            case ROJO: return new ApuestaRojo(monto);
            case NEGRO: return new ApuestaNegro(monto);
            case PAR: return new ApuestaPar(monto);
            case IMPAR: return new ApuestaImpar(monto);
            default: throw new IllegalArgumentException("Tipo de apuesta no valido: " + tipo);
        }
    }


    public String getEstadisticas() {
        return Ruleta.getEstadisticas(repositorio);
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

        return "Estadisticas del usuario:\n" +
                "Total de jugadas: " + totalJugadas + "\n" +
                "Ganadas: " + ganadas + "\n" +
                "Perdidas: " + perdidas + "\n" +
                "Total apostado: $" + totalApostado + "\n" +
                "Ganancia/Perdida neta: $" + gananciaNeta + "\n" +
                "Saldo actual: $" + usuario.getSaldo();
    }

    public IRepositorioResultados getRepositorio() {
        return repositorio;
    }
}