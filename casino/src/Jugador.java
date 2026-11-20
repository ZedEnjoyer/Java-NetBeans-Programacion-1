/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

/**
 *
 * @author samue
 */
public class Jugador {
    private int id;
    private String nombre;
    private double dinero;
    public Jugador(int id, String nombre, double dinero) {
        this.id = id;
        this.nombre = nombre;
        this.dinero = dinero;
    }
    public Jugador(String nombre, double dinero) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if (Double.isNaN(dinero) || Double.isInfinite(dinero) || dinero < 0) {
            throw new IllegalArgumentException("El saldo debe ser un numero valido y no negativo.");
        }
        this.nombre = nombre.trim();
        this.dinero = dinero;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void sumarDinero(double cantidad) {
        if (Double.isNaN(cantidad) || Double.isInfinite(cantidad) || cantidad < 0
                || Double.isInfinite(dinero + cantidad)) {
            throw new IllegalArgumentException("El premio debe ser un numero valido y no negativo.");
        }
        dinero += cantidad;
    }

    public void restarDinero(double cantidad) {
        if (Double.isNaN(cantidad) || Double.isInfinite(cantidad)
                || cantidad <= 0 || dinero < cantidad) {
            throw new IllegalArgumentException("La apuesta no es valida para el saldo disponible.");
        }
        dinero -= cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDinero() {
        return dinero;
    }

    public void getJugador(){
        System.out.println("jugador creado, nombre: " + nombre + " saldo disponible: " + dinero);
    }
}


