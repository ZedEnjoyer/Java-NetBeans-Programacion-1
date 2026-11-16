public abstract class Juego implements ErrorApuesta {
    protected final double apuesta;

    public Juego(double apuesta) {
        this.apuesta = apuesta;
    }

    // Contrato único (LSP): ejecuta la ronda y retorna el premio obtenido (0 si pierde)
    protected abstract double ejecutarRonda();

    // Contrato único (SRP): cada juego describe únicamente su resultado
    protected abstract String formatearResultado(double premio);

    public final String jugar(Jugador jugador) {
        errorApuesta(jugador); // Propaga IllegalArgumentException hacia InterfazGrafica
        double premio = ejecutarRonda();
        repartirPremio(jugador, premio);
        return formatearResultado(premio);
    }

    @Override
    public void errorApuesta(Jugador jugador) throws IllegalArgumentException {
        if (jugador == null) {
            throw new IllegalArgumentException("Primero debes crear un jugador.");
        }
        if (Double.isNaN(apuesta) || Double.isInfinite(apuesta) || apuesta <= 0) {
            throw new IllegalArgumentException("La apuesta debe ser mayor a 0.");
        }
        if (jugador.getDinero() < apuesta) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar la apuesta.");
        }
    }

    protected void repartirPremio(Jugador jugador, double premio) {
        if (Double.isNaN(premio) || Double.isInfinite(premio) || premio < 0
                || Double.isInfinite(jugador.getDinero() - apuesta + premio)) {
            throw new IllegalArgumentException("El premio calculado no es válido.");
        }
        jugador.apostar(apuesta);
        jugador.abonarPremio(premio);
    }
}