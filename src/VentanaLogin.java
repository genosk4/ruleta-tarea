
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {

    public static final List<Usuario> USUARIOS = new ArrayList<>();

    private final JFrame frame = new JFrame("login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");

    public VentanaLogin() {

        frame. setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        lblUsuario.setBounds(50, 80, 300, 25);
        txtUsuario.setBounds(50, 30, 100, 25);
        lblClave.setBounds(50, 80, 300, 25);
        btnIngresar.setBounds(270, 30, 100, 25);

    }

    public void mostrarVentana() {


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



    }

    private void login() {

    }
    private String validarCredenciales(String u, String p) {
        return "";
    }
    void abrirRegistro() {

    }
}
