package Modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuletaTest {

    @Test
    void jugar() {
        IRepositorioResultados repo = new RepositorioEnMemoria();
        Ruleta ruleta = new Ruleta(repo);

        assertThrows(IllegalArgumentException.class, () -> {
            ruleta.jugar(null);
        }, "Se esperaba IllegalArgumentException para apuesta null");
    }

}