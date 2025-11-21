package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private final String username;
    private String password;
    private String nombre;
    private int saldo;
    private Estadisticas estadisticas;


    private final List<Resultado> historial = new ArrayList<>();

    public Usuario(String username, String password, String nombre, IRepositorioResultados repositorio) {
        this.username = username;
        this.password = password;
        setNombre(nombre);
        this.saldo = 0;
        this.estadisticas = new Estadisticas(this, repositorio);
    }


    public String getUsername() { return username; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        }
    }
    public int getSaldo() { return saldo; }

    public String getPassword() {
        return this.password;
    }

    public void depositar(int monto) {
        if (monto > 0) saldo += monto;
    }

    public boolean retirar(int monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    public void registrarResultado(Resultado resultado) {
        historial.add(resultado);
        estadisticas.actualizarEstadisticas(resultado);
    }

    public List<Resultado> getHistorial() {
        return historial;
    }

    public boolean validarCredenciales(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }
}


