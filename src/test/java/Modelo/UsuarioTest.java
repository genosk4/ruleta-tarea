package Modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void depositar() {
        IRepositorioResultados repo = new RepositorioEnMemoria();
        Usuario usuario = new Usuario("test", "123", "Test", repo);
        int saldoInicial = usuario.getSaldo();
        int montoDeposito = 500;

        usuario.depositar(montoDeposito);

        assertEquals(saldoInicial + montoDeposito, usuario.getSaldo(),
                "El saldo debería incrementarse exactamente con el monto depositado");
    }
}