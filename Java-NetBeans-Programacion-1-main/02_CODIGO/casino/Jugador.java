public class Jugador {
    private String nombre;
    private double dinero;

    public Jugador(String nombre, double saldo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (Double.isNaN(saldo) || Double.isInfinite(saldo) || saldo < 0) {
            throw new IllegalArgumentException("El saldo debe ser un número válido y no negativo.");
        }
        this.nombre = nombre.trim();
        this.dinero = saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDinero() {
        return dinero;
    }

    public void apostar(double cantidad) {
        if (Double.isNaN(cantidad) || Double.isInfinite(cantidad)
                || cantidad <= 0 || cantidad > dinero) {
            throw new IllegalArgumentException("La apuesta no es válida para el saldo disponible.");
        }
        this.dinero -= cantidad;
    }

    public void abonarPremio(double cantidad) {
        if (Double.isNaN(cantidad) || Double.isInfinite(cantidad) || cantidad < 0
                || Double.isInfinite(dinero + cantidad)) {
            throw new IllegalArgumentException("El premio debe ser un número válido y no negativo.");
        }
        this.dinero += cantidad;
    }
}
