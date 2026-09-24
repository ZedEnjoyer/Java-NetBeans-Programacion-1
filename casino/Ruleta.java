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
public class Ruleta extends Juego{
    private Random random = new Random();
    private String tipoApuesta;
    private int numApuesta;
    boolean gana=false;
    
    public Ruleta(String tipoApuesta,double apuesta) {
        super(apuesta);
        this.tipoApuesta = tipoApuesta;
    }

    public Ruleta(String tipoApuesta,int apuesta, int numApuesta) {
        super(apuesta);
        this.tipoApuesta = tipoApuesta;
        this.numApuesta = numApuesta;
    }
    
    @Override
    public double ganancia() {
        switch (tipoApuesta) {
            case "par":
            case "impar":
                return apuesta+apuesta*.5;
            default:
                return apuesta+apuesta*10;
        }
    }
    
    @Override
    public String jugar(Jugador jugador) {
        gana=false;
        int numero = random.nextInt(37);
        double premio = 0;
        
        if (errorApuesta(jugador) != null) {
            return errorApuesta(jugador);
        }
        
        switch (tipoApuesta) {
            case "par":
                if(numero%2==0){
                    gana = true;
                    premio = ganancia();
                }
                break;
                
            case "impar":
                if(numero%2!=0){
                    gana = true;
                    premio = ganancia();
                }
                break;
                
            default:
                if(numero == numApuesta){
                    gana = true;
                    premio = ganancia();
                }
                break;
        }

       
        repartirPremio(jugador,premio);
        return "RULETA | Salió: " + numero + "\n"+ (gana ? "¡Ganaste! Premio: $" + premio : "Perdiste la apuesta.");

    }
}
