
import java.util.ArrayList;

public class CasinoFacade {

    private ArrayList<Jugador> jugadores = new ArrayList<>();

    public Jugador crearJugador(String nombre, double saldo) {
        Jugador jugador = new Jugador(nombre, saldo);
        jugadores.add(jugador);
        return jugador;
    }

    public Jugador obtenerJugador(int indice) {
        if (indice < 0 || indice >= jugadores.size()) {
            throw new IllegalArgumentException("Primero debes seleccionar un jugador.");
        }

        return jugadores.get(indice);
    }

    public String jugar(int indiceJugador, String tipoJuego,double apuesta, String tipoRuleta, int numero) {

        Jugador jugador = obtenerJugador(indiceJugador);
        Juego juego = JuegosFactory.crearJuego(tipoJuego, apuesta, tipoRuleta, numero);
        String resultado = juego.jugar(jugador);

        return resultado + "\nSaldo disponible: " + jugador.getDinero();
    }
}
