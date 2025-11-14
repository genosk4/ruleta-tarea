package Modelo;

public class ResultadoJuego {
    private int numeroGanador;
    private boolean acierto;
    private ApuestaBase apuesta;

    public ResultadoJuego(int numeroGanador, boolean acierto, ApuestaBase apuesta) {
        this.numeroGanador = numeroGanador;
        this.acierto = acierto;
        this.apuesta = apuesta;
    }

    public int getGanancia() {
        return acierto ? apuesta.getMonto() * 2 : 0;
    }

    public int getNumeroGanador() { return numeroGanador; }
    public boolean isAcierto() { return acierto; }
    public ApuestaBase getApuesta() { return apuesta; }
    public String getTipoApuesta() { return apuesta.getEtiqueta(); }
    public int getMontoApostado() { return apuesta.getMonto(); }
}
