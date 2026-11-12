public class Jugador {
    private String nombre;
    private double dinero;

    public Jugador(String nombre, double dinero) {
        this.nombre = nombre;
        this.dinero = dinero;
    }

    public void sumarDinero(double cantidad) {
        if (cantidad > 0){
            dinero += cantidad;
        }
    }

    public void restarDinero(double cantidad) {
        if (cantidad>0 && dinero>=cantidad){
            dinero -= cantidad;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public double getDinero() {
        return dinero;
    }

    public void getJugador(){
        System.out.println("jugador creado, nombre: "+nombre+" saldo disponible: "+dinero);
    }
}


