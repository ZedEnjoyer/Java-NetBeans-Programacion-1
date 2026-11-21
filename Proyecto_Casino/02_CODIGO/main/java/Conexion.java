import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Conexión MySQL (XAMPP); configuración modificable mediante variables de entorno. */
public final class Conexion {
    private Conexion() {}
    public static Connection conectar() throws SQLException {
        String url = System.getenv().getOrDefault("CASINO_DB_URL", "jdbc:mysql://localhost:3306/casino_db?serverTimezone=UTC");
        String usuario = System.getenv().getOrDefault("CASINO_DB_USER", "root");
        String clave = System.getenv().getOrDefault("CASINO_DB_PASSWORD", "");
        return DriverManager.getConnection(url, usuario, clave);
    }
}
