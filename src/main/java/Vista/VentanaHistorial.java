package Vista;

import Modelo.Resultado;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;


public class VentanaHistorial extends JFrame {

    private final Usuario usuario;
    private JTextArea areaHistorial;

    public VentanaHistorial(Usuario usuario) {
        this.usuario = usuario;
        initComponents();
    }

    private void initComponents() {
        setTitle("Historial de Jugadas - " + usuario.getNombre());
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel panel = new JPanel(new BorderLayout());


        JLabel titulo = new JLabel("Historial de jugadas de " + usuario.getNombre(), JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titulo, BorderLayout.NORTH);


        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaHistorial);
        panel.add(scroll, BorderLayout.CENTER);


        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        panel.add(btnCerrar, BorderLayout.SOUTH);

        add(panel);
        cargarHistorial();
    }

    private void cargarHistorial() {
        StringBuilder sb = new StringBuilder();
        if (usuario.getHistorial().isEmpty()) {
            sb.append("No hay jugadas registradas.\n");
        } else {
            int i = 1;
            for (Resultado r : usuario.getHistorial()) {
                sb.append(i++).append(". Número: ").append(r.getNumero())
                        .append(" | Tipo: ").append(r.getTipoApuesta())
                        .append(" | Apuesta: $").append(r.getMonto())
                        .append(" | ").append(r.isAcierto() ? "Ganó " : "Perdió ")
                        .append("\n");
            }
        }
        areaHistorial.setText(sb.toString());
    }
}


