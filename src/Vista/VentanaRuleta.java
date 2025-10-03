package Vista;

import Controlador.RuletaController;
import Modelo.Resultado;
import Modelo.TipoApuesta;
import Modelo.Usuario;

import javax.swing.*;

public class VentanaRuleta extends JFrame {

    private final JTextField txtMonto = new JTextField();
    private final JComboBox<TipoApuesta> comboTipo = new JComboBox<>(TipoApuesta.values());
    private final JTextArea txtResultado = new JTextArea();
    private final JTextArea txtHistorial = new JTextArea();

    private final Usuario usuario;
    private final RuletaController ruletaController = new RuletaController();

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
        String montoTexto = txtMonto.getText().trim();


        if (montoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto.");
            return;
        }


        boolean esNumero = true;
        for (char c : montoTexto.toCharArray()) {
            if (!Character.isDigit(c)) {
                esNumero = false;
                break;
            }
        }

        if (!esNumero) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto válido (solo números).");
            return;
        }


        int monto = Integer.parseInt(montoTexto);
        TipoApuesta tipo = (TipoApuesta) comboTipo.getSelectedItem();


        if (monto <= 0) {
            JOptionPane.showMessageDialog(this, "El monto debe ser mayor a 0.");
            return;
        }


        Resultado resultado = ruletaController.procesarApuesta(usuario, tipo, monto);


        String mensaje = "Número obtenido: " + resultado.getNumero() + "\n";
        mensaje += "Apuesta: " + resultado.getTipoApuesta() + " | Monto: $" + resultado.getMonto() + "\n";
        mensaje += resultado.isAcierto() ? "¡Ganaste!\n" : "Perdiste\n";
        mensaje += "Nuevo saldo: $" + ruletaController.getSaldo(usuario);
        txtResultado.setText(mensaje);


        actualizarHistorial();
    }

    private void actualizarHistorial() {
        StringBuilder sb = new StringBuilder();
        int ronda = 1;


        for (Resultado r : usuario.getHistorial()) {
            sb.append("Ronda ").append(ronda++)
                    .append(" | ").append(r.toString())
                    .append("\n");
        }
        txtHistorial.setText(sb.toString());
    }

    private void mostrarEstadisticas() {

        String estadisticas = ruletaController.getEstadisticasUsuario(usuario);
        txtResultado.setText(estadisticas);
    }
}





