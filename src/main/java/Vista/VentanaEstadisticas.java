package Vista;

import Modelo.Usuario;

import javax.swing.*;

public class VentanaEstadisticas extends JFrame {
    public VentanaEstadisticas(Usuario usuario) {
        setTitle("Estadisticas de Jugador");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JTextArea txtEstadisticas = new JTextArea();
        txtEstadisticas.setEditable(false);

        String stats = "=== ESTADÍSTICAS ===\n" +
                "Total Jugadas: " + usuario.getEstadisticas().getTotalJugadas() + "\n" +
                "Victorias: " + usuario.getEstadisticas().getVictorias() + "\n" +
                "Porcentaje: " + String.format("%.2f", usuario.getEstadisticas().getPorcentajeVictorias()) + "%\n" +
                "Racha Máxima: " + usuario.getEstadisticas().getRachaMaxima() + "\n" +
                "Tipo Más Jugado: " + usuario.getEstadisticas().getTipoMasJugado();

        txtEstadisticas.setText(stats);
        add(new JScrollPane(txtEstadisticas));
    }
}
