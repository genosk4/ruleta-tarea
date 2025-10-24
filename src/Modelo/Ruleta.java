package Modelo;

import java.util.Random;

public class Ruleta {

    private static final int MAX_HISTORIAL = 100;

    private static final ApuestaBase[] historialApuestas = new ApuestaBase[MAX_HISTORIAL];
    private static final int[] historialNumeros = new int[MAX_HISTORIAL];
    private static final boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    private static int historialSize = 0;

    private static final Random rng = new Random();


    public Ruleta(int saldoInicial) {
    }

    public Ruleta() {
        this(0);
    }

    public int girarRuleta() {
        return rng.nextInt(37);
    }

    public ResultadoJuego jugar(ApuestaBase apuesta) {

        int numeroGanador = girarRuleta();

        boolean acierto = apuesta.acierta(numeroGanador);

        registrarResultado(apuesta, numeroGanador, acierto);

        return new ResultadoJuego(numeroGanador, acierto, apuesta);
    }

    private static void registrarResultado(ApuestaBase apuesta, int numero, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialApuestas[historialSize] = apuesta;
            historialNumeros[historialSize] = numero;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }

    public static String getEstadisticas() {
        int totalJugadas = historialSize;
        int totalApostado = 0;
        int totalAciertos = 0;
        int ganancia = 0;

        for (int i = 0; i < historialSize; i++) {
            totalApostado += historialApuestas[i].getMonto();
            if (historialAciertos[i]) {
                totalAciertos++;
                ganancia += historialApuestas[i].getMonto();
            } else {
                ganancia -= historialApuestas[i].getMonto();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("----- Estadísticas -----\n");
        sb.append("Cantidad de rondas: ").append(totalJugadas).append("\n");
        sb.append("Total apostado: $").append(totalApostado).append("\n");
        sb.append("Total aciertos: ").append(totalAciertos).append("\n");
        if (totalJugadas > 0) {
            double porcentaje = (totalAciertos * 100.0) / totalJugadas;
            sb.append("% de acierto: ").append(String.format("%.2f", porcentaje)).append("%\n");
        }
        sb.append("Ganancia/Pérdida neta: $").append(ganancia).append("\n");
        return sb.toString();
    }

    public static int getHistorialSize() { return historialSize; }
    public static int getNumeroHistorial(int i) { return historialNumeros[i]; }
    public static ApuestaBase getApuestaHistorial(int i) { return historialApuestas[i]; }
    public static boolean getAciertoHistorial(int i) { return historialAciertos[i]; }

    public static String getTipoApuestaHistorial(int i) {
        return historialApuestas[i].getEtiqueta();
    }
}



