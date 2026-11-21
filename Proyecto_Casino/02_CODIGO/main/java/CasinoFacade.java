import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Facade: interfaz única para la GUI; conserva Factory y Strategy. */
public class CasinoFacade {
    private final List<Jugador> jugadores = new ArrayList<>();
    private final CasinoDAO dao;

    public CasinoFacade() { this(new CasinoDAO()); }
    public CasinoFacade(CasinoDAO dao) { this.dao = dao; }

    public List<Jugador> cargarJugadores() throws SQLException {
        List<Jugador> cargados = dao.obtenerJugadores();
        jugadores.clear();
        jugadores.addAll(cargados);
        return new ArrayList<>(jugadores);
    }

    public Jugador crearJugador(String nombre, double saldo) throws SQLException {
        Jugador validado = new Jugador(nombre, saldo);
        int id = dao.registrarJugador(validado.getNombre(), validado.getDinero());
        Jugador jugador = new Jugador(id, validado.getNombre(), validado.getDinero());
        jugadores.add(jugador);
        return jugador;
    }

    public Jugador obtenerJugador(int indice) {
        if (indice < 0 || indice >= jugadores.size())
            throw new IllegalArgumentException("Primero debes seleccionar un jugador.");
        return jugadores.get(indice);
    }

    public String jugar(int indiceJugador, String tipoJuego, double apuesta, String tipoRuleta, int numero) throws SQLException {
        Jugador jugador = obtenerJugador(indiceJugador);
        Juego juego = JuegosFactory.crearJuego(tipoJuego, apuesta, tipoRuleta, numero);
        double saldoAnterior = jugador.getDinero();
        String resultado = juego.jugar(jugador);
        double saldoNuevo = jugador.getDinero();
        double premio = saldoNuevo - saldoAnterior + apuesta;
        int idJuego;
        switch (tipoJuego) {
            case "Dado": idJuego = 1; break;
            case "Ruleta": idJuego = 2; break;
            case "Tragaperras": idJuego = 3; break;
            default: throw new IllegalArgumentException("Juego no válido.");
        }
        try {
            dao.registrarRonda(jugador.getId(), idJuego, saldoAnterior, saldoNuevo, apuesta,
                    premio > 0 ? "GANÓ" : "PERDIÓ", premio);
        } catch (SQLException | RuntimeException e) {
            // El cambio en memoria se descarta y se recupera desde la BD al siguiente intento.
            try { cargarJugadores(); } catch (SQLException recarga) { e.addSuppressed(recarga); }
            throw e;
        }
        return resultado + "\nSaldo disponible: " + jugador.getDinero();
    }

    public int contarApuestas(int indiceJugador) throws SQLException {
        return dao.contarApuestas(obtenerJugador(indiceJugador).getId());
    }
}
