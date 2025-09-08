
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



    }

    private void inicializarUsuarios() {
        USUARIOS.add(new Usuario("daniel", "1111", "Daniel Lincopi"))
    }

    private void inicializarVentana() {
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        lblUsuario.setBounds(50, 80, 300, 25);
        txtUsuario.setBounds(50, 30, 100, 25);
        lblClave.setBounds(50, 80, 300, 25);
        btnIngresar.setBounds(270, 30, 100, 25);
        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(btnIngresar);
    }

    public void mostrarVentana() {


    }

    private void login() {
        btnIngresar.addActionListener(e -> {
            String datos = txtUsuario.getText();

        });
    }
    private String validarCredenciales(String u, String p) {
        return "";
    }
    void abrirRegistro() {

    }
}
