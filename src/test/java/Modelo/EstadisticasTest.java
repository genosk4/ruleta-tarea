package Modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadisticasTest {

    @Test
    void testEstadisticasCalculanRachaYTipoMasJugado() {
        IRepositorioResultados repo = new RepositorioEnMemoria();
        Usuario usuario = new Usuario("test", "123", "Test", repo);
        Estadisticas stats = usuario.getEstadisticas();

        Resultado resultado1 = new Resultado(15, TipoApuesta.ROJO, 100, true);
        Resultado resultado2 = new Resultado(2, TipoApuesta.ROJO, 100, true);
        Resultado resultado3 = new Resultado(7, TipoApuesta.NEGRO, 100, false);
        Resultado resultado4 = new Resultado(4, TipoApuesta.PAR, 100, true);

        usuario.registrarResultado(resultado1);
        usuario.registrarResultado(resultado2);
        usuario.registrarResultado(resultado3);
        usuario.registrarResultado(resultado4);

        assertEquals(4, stats.getTotalJugadas(), "Total jugadas incorrecto");
        assertEquals(3, stats.getVictorias(), "Victorias incorrectas");
        assertEquals(2, stats.getRachaMaxima(), "Racha máxima incorrecta");
        assertEquals("ROJO", stats.getTipoMasJugado(), "Tipo más jugado incorrecto");
    }
}