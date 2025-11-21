package gestores;

import java.sql.Statement;

import conexiones.MyOracle_Connection;

import java.sql.Connection;
import java.sql.SQLException;

// Gestor para la tabla de Libros
public class GestorLibro {
    // Crea la tabla
    public GestorLibro(MyOracle_Connection connManager) {
        try (Connection appConn = connManager.getConnection();
                Statement appStmt = appConn.createStatement()) {

            String sqlCreateTable = "CREATE TABLE clientes (" +
                    "id NUMBER PRIMARY KEY, " +
                    "nombre VARCHAR2(50), " +
                    "email VARCHAR2(50))";

            // Evitar error si la tabla ya existe
            try {
                appStmt.execute(sqlCreateTable);
                System.out.println("Tabla 'clientes' creada con éxito.");
            } catch (SQLException e) {
                System.out.println("Tabla 'clientes' ya existe.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
