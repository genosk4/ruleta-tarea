import java.util.Random;
import java.util.Scanner;

public class Ruleta {

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
            case 3 -> System.out.println("Gracias por jugar...");
            default -> System.out.println("Selecciona una opcion válida.");
        }
    }




}