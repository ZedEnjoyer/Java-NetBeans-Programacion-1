import java.util.Random;

public class Tragaperras extends Juego {
    private final Random random = new Random();
    private int a;
    private int b;
    private int c;

    public Tragaperras(double apuesta) {
        super(apuesta);
    }

    @Override
    protected double ejecutarRonda() {
        a = random.nextInt(10);
        b = random.nextInt(10);
        c = random.nextInt(10);

        if (a == b && b == c) {
            return apuesta * 10;
        }
        if ((a + 1 == b && b + 1 == c) || (c + 1 == b && b + 1 == a)) {
            return apuesta * 5;
        }
        return 0;
    }

    @Override
    protected String formatearResultado(double premio) {
        String encabezado = "TRAGAPERRAS | [" + a + "] [" + b + "] [" + c + "]\n";

        if (a == b && b == c) {
            return encabezado + "JACKPOT!!!!! Premio: $" + premio;
        }
        if (premio > 0) {
            return encabezado + "SECUENCIA!!!!! Premio: $" + premio;
        }
        return encabezado + "perdiste la apuesta :c";
    }
}