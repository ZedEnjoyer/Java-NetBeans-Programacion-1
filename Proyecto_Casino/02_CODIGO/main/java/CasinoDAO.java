import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Acceso a datos: operaciones SQL y transacción del saldo + historial. */
public class CasinoDAO {
    public int registrarJugador(String nombre, double saldo) throws SQLException {
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO jugadores (nombre, saldo) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.setDouble(2, saldo);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            throw new SQLException("No se pudo obtener el ID del nuevo jugador.");
        }
    }

    public List<Jugador> obtenerJugadores() throws SQLException {
        List<Jugador> jugadores = new ArrayList<>();
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement("SELECT id_jugador, nombre, saldo FROM jugadores ORDER BY id_jugador");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                jugadores.add(new Jugador(rs.getInt("id_jugador"), rs.getString("nombre"), rs.getDouble("saldo")));
            }
        }
        return jugadores;
    }

    /** Una transacción: si falla el historial, también se revierte el saldo. */
    public void registrarRonda(int idJugador, int idJuego, double saldoAnterior,
                              double saldoNuevo, double apuesta, String resultado, double premio) throws SQLException {
        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);
            try {
                try (PreparedStatement ps = con.prepareStatement(
                        "UPDATE jugadores SET saldo = ? WHERE id_jugador = ? AND saldo = ?")) {
                    ps.setDouble(1, saldoNuevo);
                    ps.setInt(2, idJugador);
                    ps.setDouble(3, saldoAnterior);
                    if (ps.executeUpdate() != 1) throw new SQLException("No se pudo actualizar el saldo. Recarga los jugadores.");
                }
                try (PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO historial_apuestas (id_jugador, id_juego, monto_apostado, resultado, ganancia) VALUES (?, ?, ?, ?, ?)")) {
                    ps.setInt(1, idJugador);
                    ps.setInt(2, idJuego);
                    ps.setDouble(3, apuesta);
                    ps.setString(4, resultado);
                    ps.setDouble(5, premio);
                    ps.executeUpdate();
                }
                con.commit();
            } catch (SQLException | RuntimeException e) {
                try { con.rollback(); } catch (SQLException rollback) { e.addSuppressed(rollback); }
                throw e;
            }
        }
    }

    public int contarApuestas(int idJugador) throws SQLException {
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM historial_apuestas WHERE id_jugador = ?")) {
            ps.setInt(1, idJugador);
            try (ResultSet rs = ps.executeQuery()) { rs.next(); return rs.getInt(1); }
        }
    }
}
