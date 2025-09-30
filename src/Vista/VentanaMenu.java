package Vista;

import Controlador.SessionController;
import Modelo.Usuario;

import javax.swing.*;

public class VentanaMenu {

    private final JFrame frame = new JFrame("Menú - Casino Black Cat");
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir = new JButton("Salir");
    private final JButton btnPerfil = new JButton("Perfil");
    private final JLabel lblSaldo = new JLabel();
    private final Usuario usuario;
    private final SessionController sessionController;

    public VentanaMenu(Usuario usuario, SessionController sessionController) {
        this.usuario = usuario;
        this.sessionController = sessionController;
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
        btnPerfil.setBounds(20, 160, 100, 30);
        btnSalir.setBounds(20, 200, 100, 30);

        lblSaldo.setBounds(20, 30, 200, 30);
        refrescarSaldo();

        frame.add(btnJugar);
        frame.add(btnHistorial);
        frame.add(btnPerfil);
        frame.add(btnSalir);
        frame.add(lblSaldo);
    }

    private void configurarEventos() {
        btnJugar.addActionListener(e -> abrirJuego());
        btnHistorial.addActionListener(e -> mostrarHistorial());
        btnPerfil.addActionListener(e -> mostrarPerfil());
        btnSalir.addActionListener(e -> salir());
    }

    private void abrirJuego() {
        frame.dispose();
        new VentanaRuleta(usuario).setVisible(true);
    }

    private void refrescarSaldo() {
        lblSaldo.setText("Saldo: $" + usuario.getSaldo());
    }

    private void salir() {
        frame.dispose();
        new VentanaLogin(sessionController).mostrarVentana();
    }

    private void mostrarHistorial() {
        StringBuilder sb = new StringBuilder();
        sb.append("----- Historial -----\n");
        for (int i = 0; i < Modelo.Ruleta.getHistorialSize(); i++) {
            sb.append("Ronda ").append(i + 1)
                    .append(": Número=").append(Modelo.Ruleta.getNumeroHistorial(i))
                    .append(", Tipo=").append(Modelo.Ruleta.getTipoHistorial(i))
                    .append(", Apuesta=").append(Modelo.Ruleta.getApuestaHistorial(i))
                    .append(", Acierto=").append(Modelo.Ruleta.getAciertoHistorial(i) ? "Sí" : "No")
                    .append("\n");
        }
        if (Modelo.Ruleta.getHistorialSize() == 0) sb.append("No hay partidas jugadas aún.");
        JOptionPane.showMessageDialog(frame, sb.toString(), "Historial", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarPerfil() {
        String nuevoNombre = JOptionPane.showInputDialog(frame, "Nombre actual: " + usuario.getNombre() + "\nIngrese nuevo nombre:", usuario.getNombre());
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) usuario.setNombre(nuevoNombre);
        JOptionPane.showMessageDialog(frame,
                "Usuario: " + usuario.getUsername() + "\nNombre: " + usuario.getNombre() + "\nSaldo: $" + usuario.getSaldo(),
                "Perfil", JOptionPane.INFORMATION_MESSAGE);

        int opcion = JOptionPane.showConfirmDialog(frame, "¿Desea recargar saldo?", "Recarga de saldo", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            String montoStr = JOptionPane.showInputDialog(frame, "Ingrese monto a recargar:");
            try { int monto = Integer.parseInt(montoStr); usuario.depositar(monto); }
            catch (NumberFormatException ex) { JOptionPane.showMessageDialog(frame, "Monto inválido.", "Error", JOptionPane.ERROR_MESSAGE); }
        }
        refrescarSaldo();
    }

    public void mostrarVentana() { frame.setVisible(true); }
}





