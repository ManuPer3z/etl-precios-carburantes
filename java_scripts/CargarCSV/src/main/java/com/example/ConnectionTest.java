import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/estaciones_db"; // O estaciones_db
        String user = "root"; // O nicke
        String password = "mysql";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión exitosa a MySQL");
        } catch (SQLException e) {
            System.err.println("Error al conectar a MySQL");
            e.printStackTrace();
        }
    }
}
