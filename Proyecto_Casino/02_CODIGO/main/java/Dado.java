import java.util.Random;

public class Dado extends Juego {
    private final Random random = new Random();
    private int dado1;
    private int dado2;

    public Dado(double apuesta) {
        super(apuesta);
    }

    @Override
    protected double ejecutarRonda() {
        dado1 = random.nextInt(6) + 1;
        dado2 = random.nextInt(6) + 1;
        return (dado1 + dado2 == 7) ? apuesta * 5 : 0;
    }

    @Override
    protected String formatearResultado(double premio) {
        String encabezado = "DADOS | [" + dado1 + "] [" + dado2 + "]\n";
        return (premio > 0)
                ? encabezado + "¡Ganaste! Premio: $" + premio
                : encabezado + "Perdiste la apuesta :c";
    }
}