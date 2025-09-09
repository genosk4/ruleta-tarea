
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {
    public static void main(String[] args) {
        new VentanaLogin().mostrarVentana();
    }

    public static final List<Usuario> USUARIOS = new ArrayList<>();

    private final JFrame frame = new JFrame("login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistrar = new JButton("Registrar");

    public VentanaLogin() {
        inicializarUsuarios();
        inicializarVentana();
        configurarEventos();
    }

    private void inicializarUsuarios() {
        USUARIOS.add(new Usuario("daniel", "1111", "Daniel Lincopi"));
    }

    private void inicializarVentana() {
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame. setLocationRelativeTo(null);

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

    }
    private void configurarEventos() {
        btnIngresar.addActionListener(e -> login());
        btnRegistrar.addActionListener(e -> abrirRegistro());

    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void login() {
        String usuarioIngresado = txtUsuario.getText();
        String claveIngresada = new String(txtClave.getPassword());

        String nombre = validarCredenciales(usuarioIngresado, claveIngresada);

        if (!nombre.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Bienvenido " + nombre,
                    "a ingresado exitosamente",
                    JOptionPane.INFORMATION_MESSAGE);

            frame.dispose();
            Ruleta.main(new String[]{});
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Usuario o clave incorrectos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private String validarCredenciales(String u, String p) {
        for (Usuario user : USUARIOS) {
            if (user.validarCredenciales(u, p)) {
                return user.getNombre();
            }
        }
        return "";
    }
    void abrirRegistro() {
        frame.dispose();
        new VentanaRegistro().mostrarVentana();


    }
}
