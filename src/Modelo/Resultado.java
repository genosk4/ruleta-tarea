package Modelo;

public class Resultado {
    private final int numero;
    private final TipoApuesta tipoApuesta;
    private final int monto;
    private final boolean acierto;

    public Resultado(int numero, TipoApuesta tipoApuesta, int monto, boolean acierto) {
        this.numero = numero;
        this.tipoApuesta = tipoApuesta;
        this.monto = monto;
        this.acierto = acierto;
    }

    public int getNumero() { return numero; }
    public TipoApuesta getTipoApuesta() { return tipoApuesta; }
    public int getMonto() { return monto; }
    public boolean isAcierto() { return acierto; }

    @Override
    public String toString() {
        return "Resultado{" +
                "numero=" + numero +
                ", tipoApuesta=" + tipoApuesta +
                ", monto=" + monto +
                ", acierto=" + acierto +
                '}';
    }
}


