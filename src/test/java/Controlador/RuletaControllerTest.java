package Controlador;

import Modelo.IRepositorioResultados;
import Modelo.RepositorioEnMemoria;
import Modelo.TipoApuesta;
import Modelo.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuletaControllerTest {

    @Test
    void procesarApuesta() {
        IRepositorioResultados repo = new RepositorioEnMemoria();
        RuletaController controller = new RuletaController(repo);
        Usuario usuario = new Usuario("test", "123", "Test", repo);

        assertThrows(IllegalArgumentException.class, () -> {
            controller.procesarApuesta(usuario, TipoApuesta.ROJO, 5000);
        }, "Se esperaba IllegalArgumentException para saldo insuficiente");
    }
}