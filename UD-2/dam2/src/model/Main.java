package model;

import conexiones.MySQLConnection;

/*
*	Clase Main
*	Punto de entrada de la aplicación
*/
public class Main {
	public static void main(String[] args) {
		MySQLConnection mySQLConnection = new MySQLConnection();

		// Comprobar conexión
		if (mySQLConnection.getConnection() == null) {
			System.out.println("No se ha podido conectar con la base de datos.");
		} else {
			System.out.print("Éxito conectando con la base de datos: dam2");
			AppStudents app = new AppStudents(mySQLConnection);
			app.showAllStudents();
		}
	}
}
