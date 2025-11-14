package Controlador;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionControllerTest {

    @Test
    void login() {
        SessionController controller = new SessionController(false);

        boolean resultadoLogin = controller.login("usuarioInexistente", "claveCualquiera");

        assertFalse(resultadoLogin, "El login debería fallar para usuario no registrado");
        assertNull(controller.getUsuarioActual(), "No debería haber usuario actual");
    }

    @Test
    void testInicioSesionConUsernameNulo() {
        SessionController controller = new SessionController(false);

        boolean resultadoLogin = controller.login(null, "claveCualquiera");

        assertFalse(resultadoLogin, "El login debería fallar para username null");
        assertNull(controller.getUsuarioActual(), "No debería haber usuario actual");
    }
}