package Modelo;

public class ApuestaImpar extends ApuestaBase {
    public ApuestaImpar(int monto) {
        super(monto, "Impar");
    }

    @Override
    public boolean acierta(int numero) {
        return numero % 2 == 1;
    }
}