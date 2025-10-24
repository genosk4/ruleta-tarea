package Modelo;

public class ApuestaRojo extends ApuestaBase {
    private static final int[] numerosRojos =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    public ApuestaRojo(int monto) {
        super(monto, "Rojo");
    }

    @Override
    public boolean acierta(int numero) {
        for (int rojo : numerosRojos) {
            if (rojo == numero) return true;
        }
        return false;
    }
}
