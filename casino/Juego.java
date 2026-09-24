/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.com.lilliaenjoyer.programacioni.casino;

/**
 *
 * @author samue
 */
public abstract class Juego {

    protected double beneficio = 0;
    protected double apuesta = 0;

    public Juego(double apuesta) {
        this.apuesta = apuesta;
    }
    
    
    public double getBeneficio() {
        return beneficio;
    }

    protected String errorApuesta(Jugador jugador) {
        if (jugador == null){
            return "Primero debes crear un jugador.";
        }
        if (apuesta <= 0){
            return "La apuesta debe ser mayor que cero.";
        }
        if (apuesta > jugador.getDinero()){
            return "No tienes suficiente dinero.";
        }
        
        return null;
    }

    protected void repartirPremio(Jugador jugador,double premio) {
        jugador.restarDinero(apuesta);
        jugador.sumarDinero(premio);
        beneficio += apuesta - premio;
    }
    
    public abstract String jugar(Jugador jugador);
    
    public abstract double ganancia();
}


