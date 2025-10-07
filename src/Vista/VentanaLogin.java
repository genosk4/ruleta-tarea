package Vista;

import Controlador.SessionController;
import Modelo.Usuario;

import javax.swing.*;

public class VentanaLogin {

    private final SessionController sessionController;
    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistrar = new JButton("Registrar");

    public VentanaLogin(SessionController controller) {
        this.sessionController = controller;
        inicializarVentana();
        configurarEventos();
    }

    private void inicializarVentana() {
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        lblUsuario.setBounds(30, 30, 80, 25);
        txtUsuario.setBounds(120, 30, 130, 25);
        lblClave.setBounds(30, 70, 80, 25);
        txtClave.setBounds(120, 70, 130, 25);
        btnIngresar.setBounds(40, 120, 100, 30);
        btnRegistrar.setBounds(150, 120, 100, 30);

        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(btnIngresar);
        frame.add(btnRegistrar);
    }

    private void configurarEventos() {
        btnIngresar.addActionListener(e -> login());
        btnRegistrar.addActionListener(e -> abrirRegistro());
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void login() {
        String usuario = txtUsuario.getText();
        String clave = new String(txtClave.getPassword());

        // USAR EXCLUSIVAMENTE EL CONTROLADOR
        if (sessionController.login(usuario, clave)) {
            Usuario u = sessionController.getUsuarioActual();
            JOptionPane.showMessageDialog(frame,
                    "Bienvenido " + u.getNombre(),
                    "Login exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

            frame.dispose();
            new VentanaMenu(u, sessionController).mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Usuario o clave incorrectos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirRegistro() {
        frame.dispose();
        new VentanaRegistro(sessionController).mostrarVentana();
    }
}


