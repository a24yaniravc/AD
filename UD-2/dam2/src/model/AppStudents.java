package model;

import java.util.ArrayList;

import conexiones.MySQLConnection;
import gui.StudentsView;

/*
Campo nuevo estudiante
*/

/*
*	Clase AppStudents
*	Gestor de cambios en la base de datos
*/
public class AppStudents {
	// Variables
	private ManageStudents manager;
	private StudentsView view;
	private MySQLConnection mySQLConnection;

	/*
	*	Constructor AppStudents
	*	Inicializa variables
	*/
	public AppStudents(MySQLConnection mySQLConnection) {
		this.mySQLConnection = mySQLConnection;
		this.manager = new ManageStudents(mySQLConnection);
		this.view = new StudentsView(this);
		this.view.setVisible(true);
	}

	/*
	*	Método enrollStudent
	*	Añade un nuevo estudiante
	*	
	*	@param id ID del nuevo estudiante
	*	@param name Nombre del nuevo estudiante
	*	@param surname Apellido del nuevo estudiante
	*	@param age Edad del nuevo estudiante
	*   @param email Correo electrónico del nuevo estudiante
	*/
	public void enrollStudent(String id, String name, String surname, int age, String email) {
		Student student = new Student(id, name, surname, age, email);
		boolean inserted = manager.addStudent(student);
		if (inserted) {
			view.showMessage("ESTUDIANTE MATRICULADO CORRECTAMENTE.");
			view.clear();
			view.addStudent(id, name, surname, age, email);

		} else {
			view.showMessage("NO SE HA PODIDO MATRICULAR AL ESTUDIANTE.");
		}
	}

	/*
	*	Método dropStudent
	*	Borrar un estudiante
	*
	*	@param id ID del estudiante a eliminar
	*/
	public void dropStudent(String id) {
		boolean deleted = manager.deleteStudent(id);
		if (deleted) {
			view.showMessage("SE HA BORRADO CON ÉXITO AL ESTUDIANTE.");
			view.refresh();
		} else {
			view.showMessage("NO SE HA PODIDO DESMATRICULAR AL ESTUDIANTE.");
		}
	}

	/*
	*	Método updateStudent
	*	Actualizar información de un estudiante
	*	
	*	@param id ID del nuevo estudiante
	*	@param name Nombre del nuevo estudiante
	*	@param surname Apellido del nuevo estudiante
	*	@param age Edad del nuevo estudiante
	*/
	public void updateStudent(String id, String name, String surname, int age, String email) {
		Student student = new Student(id, name, surname, age, email);
		boolean modified = manager.modifyStudent(student);
		if (modified) {
			view.showMessage("SE HA ACTUALIZADO CON ÉXITO AL ESTUDIANTE.");
			view.refresh();
		} else {
			view.showMessage("NO SE HA PODIDO ACTUALIZAR AL ESTUDIANTE.");
		}
	}

	/*
	*	Método showAllStudents
	*	Muestra todos los estudiantes insertados en la base de datos.
	*	También muestra sus datos.
	*/
	public void showAllStudents() {
		ArrayList<Student> students = manager.getStudentsList();
		for (Student student : students) {
			view.addStudent(student.getId(), student.getName(), student.getSurname(), student.getAge(), student.getEmail());
		}
	}
}
