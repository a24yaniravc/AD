package principales;

import conexiones.MyOracle_Connection;
import gestores.GestorBD;
import gestores.GestorLibro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Principal {
    // Gestores
    private static GestorBD gestorBD = new GestorBD();
    private static MyOracle_Connection connManager = new MyOracle_Connection();
    private static GestorLibro gestorLibro;

    public static void main(String[] args) {
        gestorBD.crearUser();

        try (Connection conn = connManager.getConnection()) {
            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos Oracle!");

                // Probar con una consulta simple
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT sysdate FROM dual");
                if (rs.next()) {
                    System.out.println("Fecha del servidor Oracle: " + rs.getString(1));
                }
            } else {
                System.err.println("No se pudo conectar.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Tabla de Libros
        gestorLibro = new GestorLibro(connManager);
    }
}
