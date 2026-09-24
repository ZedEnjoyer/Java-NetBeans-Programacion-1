/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package github.com.lilliaenjoyer.programacioni.casino;

import java.util.Random;

public class Tragaperras extends Juego {
    private Random random = new Random();
    private int a, b, c;

    public Tragaperras(double apuesta) {
        super(apuesta);
    }
    
    @Override
    public double ganancia() {
        if (a == b && b == c) {
            return apuesta * 10;
        } else if ((a + 1 == b && b + 1 == c)|| (c + 1 == b && b + 1 == a)) {
            return apuesta * 5;
        }

        return 0;
    }

    @Override
    public String jugar(Jugador jugador) {

        if (errorApuesta(jugador) != null) {
            return errorApuesta(jugador);
        }

        a = random.nextInt(10);
        b = random.nextInt(10);
        c = random.nextInt(10);
        double premio = ganancia();
        repartirPremio(jugador, premio);
        
        if (a == b && b == c) {
            return "TRAGAPERRAS | [" + a + "] [" + b + "] [" + c + "]\n"+"JACKPOT!!!!! Premio: $"+premio;

        } else if ((a + 1 == b && b + 1 == c)|| (c + 1 == b && b + 1 == a)) {
            return "TRAGAPERRAS | [" + a + "] [" + b + "] [" + c + "]\n" + "SECUENCIA!!!!! Premio: $"+premio;
        }
        return "TRAGAPERRAS | [" + a + "] [" + b + "] [" + c + "]\n"+"perdiste la apuesta :c";

    }
}