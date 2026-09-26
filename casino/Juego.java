/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.com.lilliaenjoyer.programacioni.casino;

/**
 *
 * @author samue
 */
public abstract class Juego implements ErrorApuesta{

    protected double beneficio = 0;
    protected double apuesta = 0;

    public Juego(double apuesta) {
        this.apuesta = apuesta;
    }
    
    
    public double getBeneficio() {
        return beneficio;
    }

    @Override
    public void errorApuesta(Jugador jugador) {
        if (jugador == null){
            throw new IllegalArgumentException("Primero debes crear un jugador.");
        }
        if (apuesta <= 0){
            throw new IllegalArgumentException("La apuesta debe ser mayor que cero.");
        }
        if (apuesta > jugador.getDinero()){
            throw new IllegalArgumentException("No tienes suficiente dinero.");
        }
    }

    protected void repartirPremio(Jugador jugador,double premio) {
        jugador.restarDinero(apuesta);
        jugador.sumarDinero(premio);
        beneficio += apuesta - premio;
    }
    
    public abstract String jugar(Jugador jugador);
    
    public abstract double ganancia();
}


