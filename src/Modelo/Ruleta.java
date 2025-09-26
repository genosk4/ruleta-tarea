package Modelo;

import java.util.Random;


public class Ruleta {

    private int saldo;
    private static final int MAX_HISTORIAL = 100;
    private int[] historialNumeros = new int[MAX_HISTORIAL];
    private static final int[] historialApuestas = new int[MAX_HISTORIAL];
    private final TipoApuesta[] historialTipos = new TipoApuesta[MAX_HISTORIAL];
    private final boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    private int historialSize = 0;

    private static final Random rng = new Random();

    private static final int[] numerosRojos =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    public Ruleta(int saldoIncial) {
        if (saldoIncial >= 0) {
            this.saldo = saldoIncial;
        } else {
            this.saldo = 0;
        }

    }

    public Ruleta() {
        this.saldo = 0;
    }

    public int getSaldo() { return saldo; }

    public int girarRuleta() {
        return rng.nextInt(37);
    }

    public void depositar(int monto) {
        if (monto > 0) {
            saldo += monto;
        } else {
            System.out.println("El monto a depositar debe ser mayor a 0.");
        }
    }

    public boolean retirar(int monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            return true;
        }
        System.out.println("No se pudo realizar el retiro.");
        return false;
    }


    public boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) return true;
        }
        return false;
    }


    public boolean evaluarResultado(int numero, TipoApuesta tipo) {
        return switch (tipo) {
            case ROJO -> esRojo(numero);
            case NEGRO -> numero != 0 && !esRojo(numero);
            case PAR -> numero != 0 && numero % 2 == 0;
            case IMPAR -> numero % 2 != 0;

        };
    }

    public void registrarResultado(int numero, TipoApuesta tipo, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialTipos[historialSize] = tipo;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;

            if(acierto) saldo += apuesta;
            else saldo -= apuesta;
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



    public  int getHistorialSize() { return historialSize; }

    public  int getNumeroHistorial(int i) { return historialNumeros[i]; }
    public  TipoApuesta getTipoHistorial(int i) { return historialTipos[i]; }
    public  int getApuestaHistorial(int i) { return historialApuestas[i]; }
    public  boolean getAciertoHistorial(int i) { return historialAciertos[i]; }

    public void setSaldo(int saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        }
    }
}
