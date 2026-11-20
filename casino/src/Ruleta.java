/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

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
        validarTipoApuesta(tipoApuesta);
        this.tipoApuesta = tipoApuesta;
    }

    public Ruleta(String tipoApuesta, double apuesta, int numApuesta) {
        super(apuesta);
        validarTipoApuesta(tipoApuesta);
        if (numApuesta < 0 || numApuesta > 36) {
            throw new IllegalArgumentException("El numero debe estar entre 0 y 36.");
        }
        this.tipoApuesta = tipoApuesta;
        this.numApuesta = numApuesta;
    }

    private void validarTipoApuesta(String tipoApuesta) {
        if (!"par".equals(tipoApuesta) && !"impar".equals(tipoApuesta)
                && !"numero".equals(tipoApuesta)) {
            throw new IllegalArgumentException("Tipo de apuesta de ruleta no valido.");
        }
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
        try {
            errorApuesta(jugador); 
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        int numero = random.nextInt(37);
        double premio = 0;
        
        switch (tipoApuesta) {
            case "par":
                if (numero != 0 && numero % 2 == 0) {
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
