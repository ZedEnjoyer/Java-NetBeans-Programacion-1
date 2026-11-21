
public class JuegosFactory {

    public static Juego crearJuego(String tipo, double apuesta,String tipoRuleta, int numero) {

        switch (tipo) {
            case "Ruleta":
                EstrategiaRuleta estrategia;
                switch (tipoRuleta) {
                    case "Par":
                        estrategia = new ApuestaPar();
                        break;
                    case "Impar":
                        estrategia = new ApuestaImpar();
                        break;
                    case "Numero":
                        estrategia = new ApuestaCustom(numero);
                        break;
                    default:
                        throw new IllegalArgumentException(
                            "Tipo de apuesta no válido."
                        );
                }
                return new Ruleta(estrategia, apuesta);

            case "Tragaperras":
                return new Tragaperras(apuesta);

            case "Dado":
                return new Dado(apuesta);

            default:
                throw new IllegalArgumentException("Tipo de juego no válido: " + tipo);
        }
    }
}
