package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioArchivo implements IRepositorioResultados {
    private static final String ARCHIVO_RESULTADOS = "data/resultados_ruleta.csv";
    private static final String ARCHIVO_USUARIOS = "data/usuarios.csv";
    private final List<ResultadoJuego> cache;

    public RepositorioArchivo() {
        this.cache = new ArrayList<>();
        crearDirectorioData();
        cargarDesdeArchivo();
    }

    private void crearDirectorioData() {
        File directorio = new File("data");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    }

    private void cargarDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_RESULTADOS))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = reader.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                ResultadoJuego resultado = parsearLineaCSV(linea);
                if (resultado != null) {
                    cache.add(resultado);
                }
            }
            System.out.println("Resultados cargados desde archivo: " + cache.size());

        } catch (FileNotFoundException e) {
            System.out.println("No existe archivo de resultados previo");
        } catch (IOException e) {
            System.err.println("Error cargando resultados: " + e.getMessage());
        }
    }

    private void guardarEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_RESULTADOS))) {
            writer.println("numero,acierto,tipo_apuesta,monto");

            for (ResultadoJuego resultado : cache) {
                writer.println(String.format("%d,%s,%s,%d",
                        resultado.getNumeroGanador(),
                        resultado.isAcierto() ? "true" : "false",
                        resultado.getTipoApuesta(),
                        resultado.getMontoApostado()
                ));
            }

        } catch (IOException e) {
            System.err.println("Error guardando resultados: " + e.getMessage());
        }
    }

    private ResultadoJuego parsearLineaCSV(String linea) {
        try {
            String[] campos = linea.split(",");
            if (campos.length >= 4) {
                int numero = Integer.parseInt(campos[0]);
                boolean acierto = Boolean.parseBoolean(campos[1]);
                String tipoApuesta = campos[2];
                int monto = Integer.parseInt(campos[3]);

                ApuestaBase apuesta = crearApuestaDesdeTipo(tipoApuesta, monto);
                return new ResultadoJuego(numero, acierto, apuesta);
            }
        } catch (Exception e) {
            System.err.println("Error parseando linea CSV: " + linea);
        }
        return null;
    }

    private ApuestaBase crearApuestaDesdeTipo(String tipo, int monto) {
        switch (tipo) {
            case "Rojo": return new ApuestaRojo(monto);
            case "Negro": return new ApuestaNegro(monto);
            case "Par": return new ApuestaPar(monto);
            case "Impar": return new ApuestaImpar(monto);
            default: return new ApuestaRojo(monto);
        }
    }

    @Override
    public void guardarResultado(ResultadoJuego resultado) {
        cache.add(resultado);
        guardarEnArchivo();
    }

    @Override
    public List<ResultadoJuego> obtenerTodos() {
        return new ArrayList<>(cache);
    }

    @Override
    public List<ResultadoJuego> obtenerPorTipoApuesta(String tipoApuesta) {
        return cache.stream()
                .filter(r -> r.getTipoApuesta().equalsIgnoreCase(tipoApuesta))
                .collect(Collectors.toList());
    }

    @Override
    public int contarTotalResultados() {
        return cache.size();
    }

    @Override
    public int contarAciertos() {
        return (int) cache.stream()
                .filter(ResultadoJuego::isAcierto)
                .count();
    }

    @Override
    public void limpiar() {
        cache.clear();
        guardarEnArchivo();
    }
}