package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CasinoDAO {

    public static int registrarJugador(String nombre, double saldoInicial) {
        String sql = "INSERT INTO jugadores (nombre, saldo) VALUES (?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, nombre);
            ps.setDouble(2, saldoInicial);
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar jugador: " + e.getMessage());
        }
        return -1;
    }

    public static List<Jugador> obtenerJugadores() {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT id_jugador, nombre, saldo FROM jugadores";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                int id = rs.getInt("id_jugador");
                String nombre = rs.getString("nombre");
                double saldo = rs.getDouble("saldo");
                lista.add(new Jugador(id, nombre, saldo));
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar jugadores: " + e.getMessage());
        }
        return lista;
    }

    public static void actualizarSaldo(int idJugador, double nuevoSaldo) {
        String sql = "UPDATE jugadores SET saldo = ? WHERE id_jugador = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setDouble(1, nuevoSaldo);
            ps.setInt(2, idJugador);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar saldo: " + e.getMessage());
        }
    }

    public static void registrarApuesta(int idJugador, int idJuego, double monto, String resultado, double ganancia) {
        String sql = "INSERT INTO historial_apuestas (id_jugador, id_juego, monto_apostado, resultado, ganancia) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idJugador);
            ps.setInt(2, idJuego);
            ps.setDouble(3, monto);
            ps.setString(4, resultado);
            ps.setDouble(5, ganancia);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar apuesta: " + e.getMessage());
        }
    }
}
