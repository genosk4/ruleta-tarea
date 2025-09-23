import javax.swing.*;

public class VentanaRuleta {

    private final JFrame frame = new JFrame("Menú - Casino Black Cat");
    private final JButton btnInicio = new JButton("Inicio");
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir = new JButton("Salir");

    private final String nombreUsuario;

    public VentanaMenu(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        configurarEventos();
    }

    private void inicializarVentana() {
        frame.setSize(500, 350);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame. setLocationRelativeTo(null);

        btnInicio.setBounds(20, 40, 100, 30);
        btnJugar.setBounds(20, 80, 100, 30);
        btnHistorial.setBounds(20, 120, 100, 30);
        btnSalir.setBounds(20, 160, 100, 30);

        frame.add(btnInicio);
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
        new VentanaRuleta(nombreUsuario).mostrarVentana(); // <-- Ventana de juego
    }

    }

    private void mostrarVentana() {
        frame.setVisible(true);
    }


