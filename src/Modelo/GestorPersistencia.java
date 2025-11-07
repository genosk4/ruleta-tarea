package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorPersistencia {
    private static final String ARCHIVO_USUARIOS = "data/usuarios.dat";
    private static final String ARCHIVO_HISTORIAL = "data/historial_ruleta.dat";
    private static final String ARCHIVO_ESTADISTICAS = "data/estadisticas.dat";

    public GestorPersistencia() {
        crearDirectorioData();
    }

    private void crearDirectorioData() {
        File directorio = new File("data");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    }

    public void guardarUsuarios(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_USUARIOS))) {

            List<UsuarioDTO> usuariosDTO = new ArrayList<>();
            for (Usuario usuario : usuarios) {
                usuariosDTO.add(new UsuarioDTO(usuario));
            }

            oos.writeObject(usuariosDTO);
            System.out.println("Usuarios guardados: " + usuarios.size());

        } catch (IOException e) {
            System.err.println("Error guardando usuarios: " + e.getMessage());
        }
    }

    public List<Usuario> cargarUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ARCHIVO_USUARIOS))) {

            List<UsuarioDTO> usuariosDTO = (List<UsuarioDTO>) ois.readObject();
            List<Usuario> usuarios = new ArrayList<>();

            for (UsuarioDTO dto : usuariosDTO) {
                usuarios.add(dto.toUsuario());
            }

            System.out.println("Usuarios cargados: " + usuarios.size());
            return usuarios;

        } catch (FileNotFoundException e) {
            System.out.println("No existe archivo de usuarios previo");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error cargando usuarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void guardarHistorialRuleta() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_HISTORIAL))) {

            HistorialRuletaDTO historial = new HistorialRuletaDTO();
            oos.writeObject(historial);
            System.out.println("Historial de ruleta guardado");

        } catch (IOException e) {
            System.err.println("Error guardando historial de ruleta: " + e.getMessage());
        }
    }

    public void cargarHistorialRuleta() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ARCHIVO_HISTORIAL))) {

            HistorialRuletaDTO historial = (HistorialRuletaDTO) ois.readObject();
            historial.cargarEnRuleta();
            System.out.println("Historial de ruleta cargado");

        } catch (FileNotFoundException e) {
            System.out.println("No existe historial de ruleta previo");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error cargando historial de ruleta: " + e.getMessage());
        }
    }

    public void guardarEstadisticasGlobales() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_ESTADISTICAS))) {

            EstadisticasGlobalesDTO estadisticas = new EstadisticasGlobalesDTO();
            oos.writeObject(estadisticas);
            System.out.println("Estadisticas globales guardadas");

        } catch (IOException e) {
            System.err.println("Error guardando estadisticas: " + e.getMessage());
        }
    }

    public void guardarEstadoCompleto(List<Usuario> usuarios) {
        guardarUsuarios(usuarios);
        guardarHistorialRuleta();
        guardarEstadisticasGlobales();
        System.out.println("Estado completo del sistema guardado");
    }

    public List<Usuario> cargarEstadoCompleto() {
        List<Usuario> usuarios = cargarUsuarios();
        cargarHistorialRuleta();
        System.out.println("Estado completo del sistema cargado");
        return usuarios;
    }

    private static class UsuarioDTO implements Serializable {
        private String username;
        private String password;
        private String nombre;
        private int saldo;
        private List<ResultadoDTO> historial;

        public UsuarioDTO(Usuario usuario) {
            this.username = usuario.getUsername();
            this.password = "protected";
            this.nombre = usuario.getNombre();
            this.saldo = usuario.getSaldo();
            this.historial = new ArrayList<>();

            for (Resultado resultado : usuario.getHistorial()) {
                this.historial.add(new ResultadoDTO(resultado));
            }
        }

        public Usuario toUsuario() {
            Usuario usuario = new Usuario(username, "1234", nombre);
            usuario.depositar(saldo - 1000);

            for (ResultadoDTO resultadoDTO : historial) {
                usuario.registrarResultado(resultadoDTO.toResultado());
            }

            return usuario;
        }
    }

    private static class ResultadoDTO implements Serializable {
        private int numero;
        private String tipoApuesta;
        private int monto;
        private boolean acierto;

        public ResultadoDTO(Resultado resultado) {
            this.numero = resultado.getNumero();
            this.tipoApuesta = resultado.getTipoApuesta().name();
            this.monto = resultado.getMonto();
            this.acierto = resultado.isAcierto();
        }

        public Resultado toResultado() {
            TipoApuesta tipo = TipoApuesta.valueOf(tipoApuesta);
            return new Resultado(numero, tipo, monto, acierto);
        }
    }

    private static class HistorialRuletaDTO implements Serializable {
        private List<ApuestaHistorialDTO> apuestas;
        private List<Integer> numeros;
        private List<Boolean> aciertos;

        public HistorialRuletaDTO() {
            this.apuestas = new ArrayList<>();
            this.numeros = new ArrayList<>();
            this.aciertos = new ArrayList<>();

            int size = Ruleta.getHistorialSize();
            for (int i = 0; i < size; i++) {
                apuestas.add(new ApuestaHistorialDTO(
                        Ruleta.getApuestaHistorial(i)
                ));
                numeros.add(Ruleta.getNumeroHistorial(i));
                aciertos.add(Ruleta.getAciertoHistorial(i));
            }
        }

        public void cargarEnRuleta() {
            System.out.println("Historial de ruleta cargado: " + apuestas.size() + " registros");
        }
    }

    private static class ApuestaHistorialDTO implements Serializable {
        private String tipoApuesta;
        private int monto;
        private String etiqueta;

        public ApuestaHistorialDTO(ApuestaBase apuesta) {
            this.tipoApuesta = apuesta.getClass().getSimpleName();
            this.monto = apuesta.getMonto();
            this.etiqueta = apuesta.getEtiqueta();
        }
    }

    private static class EstadisticasGlobalesDTO implements Serializable {
        private int totalJugadas;
        private int totalAciertos;
        private int totalApostado;
        private String fechaUltimaActualizacion;

        public EstadisticasGlobalesDTO() {
            this.totalJugadas = Ruleta.getHistorialSize();
            this.totalAciertos = calcularTotalAciertos();
            this.totalApostado = calcularTotalApostado();
            this.fechaUltimaActualizacion = new java.util.Date().toString();
        }

        private int calcularTotalAciertos() {
            int aciertos = 0;
            for (int i = 0; i < Ruleta.getHistorialSize(); i++) {
                if (Ruleta.getAciertoHistorial(i)) aciertos++;
            }
            return aciertos;
        }

        private int calcularTotalApostado() {
            int total = 0;
            for (int i = 0; i < Ruleta.getHistorialSize(); i++) {
                total += Ruleta.getApuestaHistorial(i).getMonto();
            }
            return total;
        }
    }
}
