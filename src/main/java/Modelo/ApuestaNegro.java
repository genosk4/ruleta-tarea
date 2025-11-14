package Modelo;

public class ApuestaNegro extends ApuestaBase {
    public ApuestaNegro(int monto) {
        super(monto, "Negro");
    }

    @Override
    public boolean acierta(int numero) {
        return numero != 0 && !esRojo(numero);
    }

    private boolean esRojo(int n) {
        int[] rojos = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
        for (int rojo : rojos) {
            if (rojo == n) return true;
        }
        return false;
    }
}
