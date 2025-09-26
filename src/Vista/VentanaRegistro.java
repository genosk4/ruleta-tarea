package Vista;

import Modelo.Usuario;

import javax.swing.*;

public class VentanaRegistro {
    private final JFrame frame = new JFrame("Registro - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Modelo.Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JLabel lblNombre = new JLabel("Nombre completo: ");
    private final JTextField txtNombre = new JTextField();
    private final JButton btnRegistrar = new JButton("Registrar");

    public VentanaRegistro() {
        inicializarVentana();
        configurarEventos();
    }

    private void inicializarVentana() {
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame. setLocationRelativeTo(null);

        lblUsuario.setBounds(30, 30, 120, 25);
        txtUsuario.setBounds(160, 30, 140, 25);
        lblClave.setBounds(30, 70, 120, 25);
        txtClave.setBounds(160, 70, 140, 25);
        lblNombre.setBounds(30, 110, 120, 25);
        txtNombre.setBounds(160, 110, 140, 25);
        btnRegistrar.setBounds(100, 160, 120, 30);

        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(lblNombre);
        frame.add(txtNombre);
        frame.add(btnRegistrar);

    }
    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrar());

    }
    public void mostrarVentana() {
        frame.setVisible(true);
    }
    private void registrar() {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword()).trim();
        String nombre = txtNombre.getText().trim();

        if (usuario.isEmpty() || clave.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Debe completar todos los campos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        VentanaLogin.USUARIOS.add(new Usuario(usuario, clave, nombre));

        JOptionPane.showMessageDialog(frame,
                "Modelo.Usuario registrado con éxito",
                "Registro exitoso",
                JOptionPane.INFORMATION_MESSAGE);

        frame.dispose();
        new VentanaLogin().mostrarVentana();
    }


}

