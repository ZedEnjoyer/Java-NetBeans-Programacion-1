public class ApuestaImpar implements EstrategiaRuleta {
    @Override
    public boolean esGanadora(int numero) {
        return numero % 2 != 0;
    }

    @Override
    public double calcularPremio(double apuesta) {
        return apuesta + apuesta * 0.5;
    }
}
