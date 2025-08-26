import java.util.Random;
import java.util.Scanner;

public class Ruleta {

    public static Random rng = new Random();


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



    }
    public static int girarRuleta() {
        return rng.nextInt(37);
    }




}