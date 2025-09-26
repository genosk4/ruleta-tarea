package Vista;

import Modelo.Ruleta;

import javax.swing.*;

public class VentanaMenu {

    private final JFrame frame = new JFrame("Menú - Casino Black Cat");
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir = new JButton("Salir");
    private final JButton btnSaldo = new JButton("Saldo");
    private final JLabel lblSaldo = new JLabel();



    public VentanaMenu() {

        inicializarVentana();
        configurarEventos();
    }

    private void inicializarVentana() {
        frame.setSize(500, 350);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        btnJugar.setBounds(20, 80, 100, 30);
        btnHistorial.setBounds(20, 120, 100, 30);
        btnSalir.setBounds(20, 160, 100, 30);


        frame.add(btnJugar);
        frame.add(btnHistorial);
        frame.add(btnSalir);
    }

    private void configurarEventos() {
        btnJugar.addActionListener(e -> abrirJuego());
        btnHistorial.addActionListener(e -> mostrarHistorial());
        btnSalir.addActionListener(e -> salir());
    }

    private void abrirJuego() {
        frame.dispose();
        new VentanaRuleta().setVisible(true);
    }
    private void refrescarSaldo() {
        int saldo = ruleta.getSaldo();
        lblSaldo.setText("Saldo: $" + saldo);

    }

    private void salir() {
        frame.dispose();
        new VentanaLogin().mostrarVentana();
    }

    private void mostrarHistorial() {
        StringBuilder sb = new StringBuilder();
        sb.append("----- Historial -----\n");
        for (int i = 0; i < Ruleta.getHistorialSize(); i++) {
            sb.append("Ronda ").append(i + 1)
                    .append(": Número=").append(Ruleta.getNumeroHistorial(i))
                    .append(", Tipo=").append(Ruleta.getTipoHistorial(i))
                    .append(", Apuesta=").append(Ruleta.getApuestaHistorial(i))
                    .append(", Acierto=").append(Ruleta.getAciertoHistorial(i) ? "Sí" : "No")
                    .append("\n");
        }
        if (Ruleta.getHistorialSize() == 0) {
            sb.append("No hay partidas jugadas aún.");
        }

        JOptionPane.showMessageDialog(frame,
                sb.toString(),
                "Historial",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}



