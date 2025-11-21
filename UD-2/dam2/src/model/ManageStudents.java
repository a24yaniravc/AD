package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexiones.MySQLConnection;

/*
*	Clase ManageStudentss
*	Ejecuta las secuencias SQL
*/
public class ManageStudents {
	private MySQLConnection mySQLConnection;

	public ManageStudents(MySQLConnection mySQLConnection) {
		this.mySQLConnection = mySQLConnection;
	}

	/*
	*	Método addStudent
	*	Ejecuta el SQL necesario para añadir un estudiante nuevo
	*	@param student Clase estudiante auxiliar para crear el estudiante en la BD
	*/
	public boolean addStudent(Student student) {
		try (Connection connection = mySQLConnection.getConnection()) {
			String sql = "INSERT INTO estudiante VALUES (?,?,?,?,?)";
			
			PreparedStatement sentence = connection.prepareStatement(sql);
			sentence.setString(1, student.getId());
			sentence.setString(2, student.getName());
			sentence.setString(3, student.getSurname());
			sentence.setInt(4, student.getAge());
			sentence.setString(5, student.getEmail());
			
			int rows = sentence.executeUpdate();

			sentence.close();
			return rows > 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/*
	*	Método getStudent
	*	Ejecuta SQL necesario para mostrar un estudiante a partir de su id
	*	
	*	@param id ID del estudiante a mostrar
	*/
	public Student getStudent(String id) {
		try (Connection connection = mySQLConnection.getConnection()) {
			String sql = "SELECT * FROM estudiante WHERE id LIKE ?";

			PreparedStatement query = connection.prepareStatement(sql);
			query.setString(1, id);
			ResultSet result = query.executeQuery();
			Student student = new Student();
			while (result.next()) {
				student.setId(result.getString("id"));
				student.setName(result.getString("nombre"));
				student.setSurname(result.getString("apellidos"));
				student.setAge(result.getInt("edad"));
				student.setEmail(result.getString("email"));
			}

			query.close();
			return student;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/*
	*	Método deleteStudent
	*	Ejecuta SQL necesario para eliminar un estudiante de la BD	
	*	
	*	@param id ID del estudiante a eliminar
	*/
	public boolean deleteStudent(String id) {
		try (Connection connection = mySQLConnection.getConnection()) {
			String sql = "DELETE FROM estudiante WHERE id=?";

			PreparedStatement query = connection.prepareStatement(sql);
			query.setString(1, id);
			int deletedRow = query.executeUpdate();
			
			query.close();
			return deletedRow == 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/*
	*	Método modifyStudent
	*	Ejecuta SQL necesario para modificar un estudiante de la BD
	*
	*	@param student Clase estudiante auxiliar para modificar el estudiante en la BD
	*/
	public boolean modifyStudent(Student student) {
		try (Connection connection = mySQLConnection.getConnection()) {
			String sql = "UPDATE estudiante SET nombre=?, apellidos=?, edad=?, email=?" + " WHERE id=?";

			PreparedStatement sentence = connection.prepareStatement(sql);
			sentence.setString(1, student.getName());
			sentence.setString(2, student.getSurname());
			sentence.setInt(3, student.getAge());
			sentence.setString(4, student.getEmail());
			sentence.setString(5, student.getId());

			int rowsUpdated = sentence.executeUpdate();
			
			sentence.close();
			return rowsUpdated == 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/*
	*	Método getStudentsList
	*	Ejecuta SQL necesario para obtener todos los estudiantes de la BD
	*	
	*	@return Lista de estudiantes
	*/
	public ArrayList<Student> getStudentsList() {
		try (Connection connection = mySQLConnection.getConnection()) {
			String sql = "SELECT * FROM estudiante";
			
			Statement query = connection.createStatement();
			ResultSet result = query.executeQuery(sql);
			ArrayList<Student> students = new ArrayList<Student>();
			while (result.next()) {
				Student student = new Student();
				student.setId(result.getString("id"));
				student.setName(result.getString("nombre"));
				student.setSurname(result.getString("apellidos"));
				student.setAge(result.getInt("edad"));
				student.setEmail(result.getString("email"));
				
				students.add(student);
			}

			query.close();
			return students;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
