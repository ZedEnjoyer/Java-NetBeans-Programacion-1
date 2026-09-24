/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.com.lilliaenjoyer.programacioni.casino;

import java.util.Random;

/**
 *
 * @author samue
 */
public class Dado extends Juego{
    private Random random = new Random();
    private int dado1, dado2;

    public Dado(double apuesta) {
        super(apuesta);
    }
    
    @Override
    public double ganancia() {
        if (dado1 + dado2 == 7) {
            return apuesta * 5;
        }
        return 0;
    }

    @Override
    public String jugar(Jugador jugador) {
        dado1 = random.nextInt(6) + 1;
        dado2 = random.nextInt(6) + 1;
        int suma = dado1 + dado2;
        double premio = ganancia();
        
        if (errorApuesta(jugador) != null) {
            return errorApuesta(jugador);
        }

        repartirPremio(jugador, premio);

        if (suma == 7) {
            return "DADOS | ["+dado1+"] ["+dado2+"]"+"\n¡Ganaste! Premio: $" +premio;
        }

        return "DADOS | ["+dado1+"] ["+dado2+"]"+"\nPerdiste la apuesta :c";
    }
}
