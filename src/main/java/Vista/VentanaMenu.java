package Vista;

import Controlador.SessionController;
import Controlador.RuletaController;
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
    private final RuletaController ruletaController;
    private final JButton btnEstadisticas = new JButton("Estadísticas");
    private final JButton btnRespaldo = new JButton("Respaldar Datos");

    public VentanaMenu(Usuario usuario, SessionController sessionController, RuletaController ruletaController) {
        this.usuario = usuario;
        this.sessionController = sessionController;
        this.ruletaController = ruletaController;
        inicializarVentana();
        configurarEventos();
    }

    private void inicializarVentana() {
        frame.setSize(500, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        btnJugar.setBounds(20, 80, 100, 30);
        btnHistorial.setBounds(20, 120, 100, 30);
        btnPerfil.setBounds(20, 160, 100, 30);
        btnEstadisticas.setBounds(20, 200, 100, 30);
        btnSalir.setBounds(20, 240, 100, 30);

        lblSaldo.setBounds(20, 30, 200, 30);
        refrescarSaldo();

        frame.add(btnJugar);
        frame.add(btnHistorial);
        frame.add(btnPerfil);
        frame.add(btnEstadisticas);
        frame.add(btnSalir);
        frame.add(lblSaldo);

    }

    private void configurarEventos() {
        btnJugar.addActionListener(e -> abrirJuego());
        btnHistorial.addActionListener(e -> mostrarHistorial());
        btnPerfil.addActionListener(e -> mostrarPerfil());
        btnSalir.addActionListener(e -> salir());
        btnEstadisticas.addActionListener(e -> mostrarEstadisticas());

    }

    private void mostrarEstadisticas() {
        new VentanaEstadisticas(usuario).setVisible(true);
    }

    private void abrirJuego() {
        frame.dispose();
        new VentanaRuleta(usuario, ruletaController, sessionController).setVisible(true);
    }

    private void refrescarSaldo() {
        lblSaldo.setText("Saldo: $" + ruletaController.getSaldo(usuario));
    }

    private void salir() {
        sessionController.logout();
        frame.dispose();
        new VentanaLogin(sessionController).mostrarVentana();
    }

    private void mostrarHistorial() {
        new VentanaHistorial(usuario).setVisible(true);
    }

    private void mostrarPerfil() {

        if (usuario == null) {
            JOptionPane.showMessageDialog(frame,
                    "No hay usuario autenticado",
                    "Error de sesión",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nuevoNombre = JOptionPane.showInputDialog(frame, "Nombre actual: " + usuario.getNombre() + "\nIngrese nuevo nombre:", usuario.getNombre());
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            if (nuevoNombre.trim().length() < 2) {
                JOptionPane.showMessageDialog(frame,
                        "El nombre debe tener al menos 2 caracteres",
                        "Error de validación",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                usuario.setNombre(nuevoNombre.trim());
            }
        }
        JOptionPane.showMessageDialog(frame,
                "Usuario: " + usuario.getUsername() + "\nNombre: " + usuario.getNombre() + "\nSaldo: $" + ruletaController.getSaldo(usuario),
                "Perfil", JOptionPane.INFORMATION_MESSAGE);

        int opcion = JOptionPane.showConfirmDialog(frame, "¿Desea recargar saldo?", "Recarga de saldo", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            String montoStr = JOptionPane.showInputDialog(frame, "Ingrese monto a recargar:");
            try {
                if (montoStr == null || montoStr.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Debe ingresar un monto",
                            "Error de validación",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!montoStr.matches("\\d+")) {
                    JOptionPane.showMessageDialog(frame,
                            "El monto debe contener solo números",
                            "Error de validación",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int monto = Integer.parseInt(montoStr);

                if (monto <= 0) {
                    JOptionPane.showMessageDialog(frame,
                            "El monto debe ser mayor a 0",
                            "Error de validación",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ruletaController.depositar(usuario, monto);
                refrescarSaldo();
                JOptionPane.showMessageDialog(frame,
                        "Saldo recargado exitosamente",
                        "Recarga exitosa",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Monto inválido: " + ex.getMessage(),
                        "Error de validación",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,
                        "Error excepcional al recargar saldo: " + ex.getMessage(),
                        "Error del sistema",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void mostrarVentana() { frame.setVisible(true); }


}





