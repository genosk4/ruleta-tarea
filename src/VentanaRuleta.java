import javax.swing.*;

public class ventanaRuleta {

    private final JFrame frame = new JFrame("Menú - Casino Black Cat");
    private final JButton txtmonto = new JButton("Cerrar sesión");
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnEstadisticas = new JButton("Ver Estadísticas");
    private final JButton btnVolver = new JButton("Volver al Menú");
    private final JLabel lblApuesta = new JLabel("Apuesta: ");
    private final JLabel lblMonto = new JLabel("Monto: ");

    public VentanaMenu(String nombreUsuario) {
        inicializarVentana(nombreUsuario);
        configurarEventos();
    }

    private void inicializarVentana() {
        frame.setSize(400, 320);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame. setLocationRelativeTo(null);

        lblApuesta.setBounds(30, 30, 80, 25);
        txtmonto.setBounds(120, 30, 130, 25);
        lblMonto.setBounds(30, 70, 80, 25);
        btnJugar.setBounds(30, 30, 80, 25);
        btnVolver.setBounds(30, 70, 80, 25);
        btnEstadisticas.setBounds(30, 100, 80, 25);

        frame.add(btnJugar);
        frame.add(btnEstadisticas);
        frame.add(btnVolver);



    }
    private void configurarEventos() {

    }

    }

    private void iniciarVentana() {
        frame.setVisible(true);
    }


