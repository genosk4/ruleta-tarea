import java.util.Random;

public class Ruleta {

    public static final int MAX_HISTORIAL = 100;
    private static final int[] historialNumeros = new int[MAX_HISTORIAL];
    private static final int[] historialApuestas = new int[MAX_HISTORIAL];
    private static final char[] historialTipos = new char[MAX_HISTORIAL];
    private static final boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    private static int historialSize = 0;

    private static final Random rng = new Random();

    private static final int[] numerosRojos =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    public static int girarRuleta() {
        return rng.nextInt(37);
    }


    public static boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) return true;
        }
        return false;
    }


    public static boolean evaluarResultado(int numero, char tipo) {
        switch (Character.toUpperCase(tipo)) {
            case 'R': return esRojo(numero);
            case 'N': return numero != 0 && !esRojo(numero);
            case 'P': return numero != 0 && numero % 2 == 0;
            case 'I': return numero % 2 != 0;
            default: return false;
        }
    }

    public static void registrarResultado(int numero, char tipo, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialTipos[historialSize] = tipo;
            historialApuestas[historialSize] = apuesta;
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
            totalApostado += historialApuestas[i];
            if (historialAciertos[i]) {
                totalAciertos++;
                ganancia += historialApuestas[i];
            } else {
                ganancia -= historialApuestas[i];
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
    public static char getTipoHistorial(int i) { return historialTipos[i]; }
    public static int getApuestaHistorial(int i) { return historialApuestas[i]; }
    public static boolean getAciertoHistorial(int i) { return historialAciertos[i]; }
}
