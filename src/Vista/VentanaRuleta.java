package Vista;

import Modelo.Ruleta;
import Modelo.TipoApuesta;
import Modelo.Usuario;

import javax.swing.*;

public class VentanaRuleta extends JFrame {

    private final JTextField txtMonto = new JTextField();
    private final JComboBox<TipoApuesta> comboTipo = new JComboBox<>(TipoApuesta.values());
    private final JTextArea txtResultado = new JTextArea();
    private final JTextArea txtHistorial = new JTextArea();
    private final Usuario usuario;

    public VentanaRuleta(Usuario usuario) {
        this.usuario = usuario;
        inicializarVentana();
    }

    private void inicializarVentana() {
        setTitle("Juego de la Ruleta");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);


        JLabel lblMonto = new JLabel("Monto:");
        lblMonto.setBounds(20, 20, 50, 25);
        txtMonto.setBounds(80, 20, 100, 25);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(20, 60, 50, 25);
        comboTipo.setBounds(80, 60, 100, 25);

        JButton btnApostar = new JButton("Apostar");
        btnApostar.setBounds(200, 20, 120, 25);
        JButton btnEstadisticas = new JButton("Ver estadísticas");
        btnEstadisticas.setBounds(200, 60, 150, 25);

        txtResultado.setBounds(20, 100, 330, 80);
        txtResultado.setEditable(false);
        txtResultado.setBorder(BorderFactory.createTitledBorder("Resultado"));

        txtHistorial.setBounds(20, 200, 630, 200);
        txtHistorial.setEditable(false);
        txtHistorial.setBorder(BorderFactory.createTitledBorder("Historial"));

        add(lblMonto);
        add(txtMonto);
        add(lblTipo);
        add(comboTipo);
        add(btnApostar);
        add(btnEstadisticas);
        add(txtResultado);
        add(txtHistorial);

        // Eventos
        btnApostar.addActionListener(e -> apostar());
        btnEstadisticas.addActionListener(e -> mostrarEstadisticas());
    }

    private void apostar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            TipoApuesta tipo = (TipoApuesta) comboTipo.getSelectedItem();

            if (!usuario.retirar(monto)) {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente.");
                return;
            }

            int numero = Ruleta.girarRuleta();
            boolean acierto = Ruleta.evaluarResultado(numero, tipo);
            Ruleta.registrarResultado(numero, tipo, monto, acierto);

            String mensaje = "Número obtenido: " + numero + "\n";
            mensaje += "Apuesta: " + tipo + " | Monto: $" + monto + "\n";
            mensaje += acierto ? "¡Ganaste!\n" : "Perdiste\n";
            txtResultado.setText(mensaje);

            actualizarHistorial();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto válido.");
        }
    }

    private void actualizarHistorial() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Ruleta.getHistorialSize(); i++) {
            sb.append("N°: ").append(Ruleta.getNumeroHistorial(i))
                    .append(" | Tipo: ").append(Ruleta.getTipoHistorial(i))
                    .append(" | Monto: $").append(Ruleta.getApuestaHistorial(i))
                    .append(" | Resultado: ").append(Ruleta.getAciertoHistorial(i) ? "Ganó" : "Perdió")
                    .append("\n");
        }
        txtHistorial.setText(sb.toString());
    }

    private void mostrarEstadisticas() {
        txtResultado.setText(Ruleta.getEstadisticas());
    }
}




