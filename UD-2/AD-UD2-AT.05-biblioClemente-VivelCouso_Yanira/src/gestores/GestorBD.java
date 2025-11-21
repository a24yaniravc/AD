package gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import principales.ConfigLoader;

// Gestor para la creación de la BD (usuario)
public class GestorBD {
    public void crearUser() {
        String URL = ConfigLoader.get("oracle.url");
        String USER = ConfigLoader.get("oracle.userBase"); // SYSTEM o SYSDBA
        String NEW_USER = ConfigLoader.get("oracle.userNuevo");
        String PASSWORD = ConfigLoader.get("oracle.passwordBase");

        try (Connection sysConn = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement sysStmt = sysConn.createStatement()) {

            // Crear usuario si no existe
            if (!usuarioExiste(sysStmt, NEW_USER)) {
                String sqlCreateUser = "CREATE USER " + NEW_USER + " IDENTIFIED BY " + PASSWORD;
                sysStmt.execute(sqlCreateUser);
                System.out.println("Usuario creado: " + NEW_USER);

                String sqlGrant = "GRANT CONNECT, RESOURCE TO " + NEW_USER;
                sysStmt.execute(sqlGrant);
                System.out.println("Privilegios otorgados a: " + NEW_USER);
            } else {
                System.out.println("Usuario ya existe: " + NEW_USER);
            }

        } catch (SQLException e) {
            System.err.println("Error creando usuario: " + e.getMessage());
        }
    }

    // Verificar usuario con all_users
    private static boolean usuarioExiste(Statement stmt, String username) throws SQLException {
        String checkUser = "SELECT COUNT(*) FROM all_users WHERE username = '" + username.toUpperCase() + "'";
        try (ResultSet rs = stmt.executeQuery(checkUser)) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}
