import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRuleta extends JFrame {

    private JTextField txtMonto;
    private JComboBox<String> comboTipo;
    private JTextArea txtResultado;
    private JTextArea txtHistorial;

    public VentanaRuleta() {
        inicializarVentana();
    }

    private void inicializarVentana() {
        setTitle("Juego de la Ruleta");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel(new BorderLayout(10, 10));
        add(panel);


        JPanel panelApuesta = new JPanel(new GridLayout(3, 2, 5, 5));
        panelApuesta.setBorder(BorderFactory.createTitledBorder("Nueva Apuesta"));

        panelApuesta.add(new JLabel("Monto:"));
        txtMonto = new JTextField();
        panelApuesta.add(txtMonto);

        panelApuesta.add(new JLabel("Tipo de apuesta:"));
        comboTipo = new JComboBox<>(new String[]{"Rojo (R)", "Negro (N)", "Par (P)", "Impar (I)"});
        panelApuesta.add(comboTipo);

        JButton btnApostar = new JButton("Apostar");
        panelApuesta.add(btnApostar);

        JButton btnEstadisticas = new JButton("Ver estadísticas");
        panelApuesta.add(btnEstadisticas);

        panel.add(panelApuesta, BorderLayout.NORTH);


        txtResultado = new JTextArea(5, 20);
        txtResultado.setEditable(false);
        txtResultado.setBorder(BorderFactory.createTitledBorder("Resultado"));
        panel.add(new JScrollPane(txtResultado), BorderLayout.CENTER);


        txtHistorial = new JTextArea(10, 20);
        txtHistorial.setEditable(false);
        txtHistorial.setBorder(BorderFactory.createTitledBorder("Historial"));
        panel.add(new JScrollPane(txtHistorial), BorderLayout.EAST);


        btnApostar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apostar();
            }
        });

        btnEstadisticas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEstadisticas();
            }
        });
    }

    private void apostar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            char tipo = switch (comboTipo.getSelectedIndex()) {
                case 0 -> 'R';
                case 1 -> 'N';
                case 2 -> 'P';
                case 3 -> 'I';
                default -> 'R';
            };

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


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaRuleta().setVisible(true);
        });
    }
}