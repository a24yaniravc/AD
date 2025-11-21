package model;

/*
*	Clase Student
*	Modelo de datos de un estudiante
*/
public class Student {
	// Variables
	private String id;
	private String name;
	private String surname;
	private int age;
	private String email;

	// Constructores

	/*
	*	Constructor Student
	*	Inicializa variables
	*
	*	@param id ID del estudiante
	*	@param name Nombre del estudiante
	*	@param surname Apellido del estudiante
	*	@param age Edad del estudiante
	*	@param email Correo electrónico del estudiante
	*/
	public Student(String id, String name, String surname, int age, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
	}


	// Constructor Student vacío

	public Student(){}

	// Getters y Setters
	
	/*
	*	Método get y set ID
	*	Obtienen y establecen los valores de las variables
	*/
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/*
	*	Método get y set NAME
	*	Obtienen y establecen los valores de las variables
	*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	*	Método get y set SURNAME
	*	Obtienen y establecen los valores de las variables
	*/
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	/*
	*	Método get y set AGE
	*	Obtienen y establecen los valores de las variables
	*/
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/*
	*	Método get y set EMAIL
	*	Obtienen y establecen los valores de las variables
	*/
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	// Método toString
	@Override
	public String toString() {
		return "Student id: " + id + "\n" + "Student name: " + name + "\n" + "Student surname: " + surname + "\n"
				+ "Student age: " + age + "\n" + "Student email: " + email + "\n";
	}
}
