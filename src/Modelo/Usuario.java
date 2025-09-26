package Modelo;

public class Usuario {
    private  String username;
    private  String password;
    private  String nombre;

    public Usuario(String username,String password, String nombre) {
        this.username = username;
        this.password = password;
        setNombre(nombre);
    }
    public Usuario() {
        this.username = "invitado";
        this.password = "1234";
        this.nombre = "Invitado";
    }

    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //  evita nombre vacío
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            System.out.println("El nombre no puede estar vacío. Se mantiene el anterior.");
        }
    }

}

