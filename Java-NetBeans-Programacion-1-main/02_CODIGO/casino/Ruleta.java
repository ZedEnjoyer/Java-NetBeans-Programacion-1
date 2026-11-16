import java.util.Random;

public class Ruleta extends Juego {

    private Random random = new Random();
    private EstrategiaRuleta estrategia;
    private boolean gana = false;

    public Ruleta(EstrategiaRuleta estrategia, double apuesta) {
        super(apuesta);
        this.estrategia = estrategia;
    }

    @Override
    public double ganancia() {
        return estrategia.calcularPremio(apuesta);
    }

    @Override
    public String jugar(Jugador jugador) {

        try {
            errorApuesta(jugador);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        gana = false;

        int numero = random.nextInt(37);
        double premio = 0;

        if (estrategia.esGanadora(numero)) {
            gana = true;
            premio = ganancia();
        }

        repartirPremio(jugador, premio);

        return "RULETA | Salió: " + numero + "\n"
                + (gana
                ? "¡Ganaste! Premio: $" + premio
                : "Perdiste la apuesta.");
    }
}
