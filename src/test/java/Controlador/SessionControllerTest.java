package Controlador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionControllerTest {
    private SessionController sessionController;
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

    @BeforeEach
    void setUp() {
        sessionController = new SessionController(false);
    }
    @Test
    void testRegistroUsuarioValido() {
        String username = "usuario_test";
        String password = "clave123";
        String nombre = "Usuario Test";

        boolean resultado = sessionController.registrarUsuario(username, password, nombre);

        assertTrue(resultado, "El registro debería ser exitoso con datos válidos");
        assertTrue(sessionController.existeUsuario(username), "El usuario debería existir después del registro");
    }

    @Test
    void testRegistroUsuarioDuplicadoLanzaExcepcion() {
        String username = "usuario_duplicado";
        String password = "clave123";
        String nombre = "Usuario Duplicado";

        sessionController.registrarUsuario(username, password, nombre);

        //  debería fallar
        boolean segundoRegistro = sessionController.registrarUsuario(username, "otraclave", "Otro Nombre");
        assertFalse(segundoRegistro, "El segundo registro con mismo username debería fallar");
    }


    @Test
    void testLogin() {
    }
}