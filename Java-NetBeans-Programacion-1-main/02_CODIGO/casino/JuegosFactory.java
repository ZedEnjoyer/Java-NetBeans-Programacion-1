public abstract class JuegosFactory {

    public static Juego crearJuego(String tipo, double apuesta, String tipoRuleta, int numero) {
        switch (tipo) {
            case "Ruleta":
                if (tipoRuleta.equals("Numero")) {
                    return new Ruleta("numero", (int) apuesta, numero);
                } else {
                    return new Ruleta(tipoRuleta.toLowerCase(), apuesta);
                }
            case "Tragaperras":
                return new Tragaperras(apuesta);
            case "Dado":
                return new Dado(apuesta);
            default:
                throw new IllegalArgumentException("Tipo de juego no válido: " + tipo);
        }
    }
}