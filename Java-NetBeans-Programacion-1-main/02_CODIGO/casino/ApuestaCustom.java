public class ApuestaCustom implements EstrategiaRuleta {
    private int numeroElegido;

    public ApuestaCustom(int numeroElegido) {
        if (numeroElegido < 0 || numeroElegido > 36) {
            throw new IllegalArgumentException(
                "El número debe estar entre 0 y 36."
            );
        }

        this.numeroElegido = numeroElegido;
    }

    @Override
    public boolean esGanadora(int numero) {
        return numero == numeroElegido;
    }

    @Override
    public double calcularPremio(double apuesta) {
        return apuesta + apuesta * 10;
    }
}
