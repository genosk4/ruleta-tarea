
import java.util.Random;
import java.util.Scanner;

public class Ruleta {

    public static final int MAX_HISTORIAL = 100;
    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;
    public static Random rng = new Random();
    public static int[] numerosRojos =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};


    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner in = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(in);
            ejecutarOpcion(opcion, in);
        } while (opcion != 3);
        in.close();
    }

    public static void mostrarMenu() {
        System.out.println(" Menú");
        System.out.println("1.- Iniciar ronda de ruleta");
        System.out.println("2.- Ver estadisticas ");
        System.out.println("3.- Salir");
        System.out.println("Elige una opcion: ");
    }
    public static int leerOpcion(Scanner in) {
        return in.nextInt();
    }

    public static void ejecutarOpcion(int opcion, Scanner in) {
        switch (opcion) {
            case 1 -> iniciarRonda(in);
            case 2 -> mostrarEstadisticas();
            case 3 -> System.out.println("Gracias por jugar...");
            default -> System.out.println("Selecciona una opcion válida.");
        }
    }
    public static char leerTipoApuesta(Scanner in) {
        char tipo;
        do {
            System.out.println("Elige tipo de apuesta (R=rojo, N=negro, P=par, I=impar)");
            tipo = in.next().toUpperCase().charAt(0);
        } while (tipo != 'R' && tipo != 'N' && tipo != 'P' && tipo != 'I');
        return tipo;
    }

    public static void iniciarRonda(Scanner in) {
        char tipo = leerTipoApuesta(in);
        System.out.println("Ingresa el monto apostado: ");
        int monto = in.nextInt();

        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipo);

        registrarResultado(numero, monto, acierto);
        mostrarResultado(numero, tipo, monto, acierto);
    }
    public static int girarRuleta() {
        return rng.nextInt(37);
    }

    public static boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) return true;
        } return false;
    }

    public static boolean evaluarResultado(int numero, char tipo) {
        switch (tipo) {
            case 'R': return esRojo(numero);
            case 'N': return numero != 0 && !esRojo(numero);
            case 'P': return numero != 0 && numero % 2 == 0;
            case 'I': return numero % 2 != 0;
            default: return false;
        }
    }

    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }

    public static void mostrarResultado(int numero, char tipo, int monto,boolean acierto) {
        System.out.println("Numero obtenido: " + numero);
        System.out.println("Tu apuesta fue: " + tipo + " con monto $" + monto);
        if (acierto) {
            System.out.println("Ganaste");
        } else {
            System.out.println("Perdiste");
        }
    }

    public static void mostrarEstadisticas() {
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
        System.out.println("----- Estadisticas -----");
        System.out.println("Cantidad de rondas: " + totalJugadas);
        System.out.println("Total apostado: $" + totalApostado);
        System.out.println("Total aciertos: " + totalAciertos);
        if (totalJugadas > 0) {
            double porcentaje = (totalAciertos * 100.0) / totalJugadas;
            System.out.println("% de acierto: " + String.format("%.2f", porcentaje) + "%");
        }
        System.out.println("Ganancia/Pérdida neta: $" + ganancia);
    }




}
