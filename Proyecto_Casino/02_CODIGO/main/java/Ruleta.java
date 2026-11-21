import java.util.Random;

public class Ruleta extends Juego {
    private final Random random = new Random();
    private final EstrategiaRuleta estrategia;
    private int ultimoNumero;

    public Ruleta(EstrategiaRuleta estrategia, double apuesta) {
        super(apuesta);
        if (estrategia == null) {
            throw new IllegalArgumentException("Debes seleccionar un tipo de apuesta.");
        }
        this.estrategia = estrategia;
    }

    @Override
    protected double ejecutarRonda() {
        ultimoNumero = random.nextInt(37);
        return estrategia.esGanadora(ultimoNumero)
                ? estrategia.calcularPremio(apuesta)
                : 0;
    }

    @Override
    protected String formatearResultado(double premio) {
        String encabezado = "RULETA | Salió: " + ultimoNumero + "\n";
        return (premio > 0)
                ? encabezado + "¡Ganaste! Premio: $" + premio
                : encabezado + "Perdiste la apuesta.";
    }
}