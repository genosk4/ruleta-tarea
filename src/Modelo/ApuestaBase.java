package Modelo;

public abstract class ApuestaBase {
    protected int monto;
    protected String etiqueta;

    public ApuestaBase(int monto, String etiqueta) {
        this.monto = monto;
        this.etiqueta = etiqueta;
    }

    public abstract boolean acierta(int numero);

    public int getMonto() { return monto; }
    public String getEtiqueta() { return etiqueta; }

    public int calcularGanancia() {
        return acierto() ? monto * 2 : 0;
    }

    protected boolean acierto() {
        return true;
    }
}
