package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import model.AppStudents;

/*
	*	Clase StudentsView
	*	Ejecuta un entorno visual de la aplicación
	*	@param app Pasa la lógica de la aplicación
	*/
public class StudentsView extends JFrame implements ActionListener {
	// Variables
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtAge;
	private JTextField txtEmail;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JTable table;
	private AppStudents app;

	/*
	*	Constructor StudentsView
	*	Crea el entorno visual	
	*/
	public StudentsView(AppStudents app) {
		this.app = app;

		// Configuración de la ventana
		setTitle("Students App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Configuración del panel principal
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		// Título
		JLabel lblTitle = new JLabel("Student Management System");
		lblTitle.setForeground(new Color(0, 0, 0));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(20, 11, 387, 60);
		contentPane.add(lblTitle);

		// Panel secundario
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(20, 71, 387, 284);
		contentPane.add(panel);
		panel.setLayout(null);

		// Etiquetas y campos de texto
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(31, 46, 36, 24);
		panel.add(lblId);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(21, 81, 46, 24);
		panel.add(lblName);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSurname.setBounds(10, 116, 74, 24);
		panel.add(lblSurname);

		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAge.setBounds(21, 154, 46, 24);
		panel.add(lblAge);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(17, 189, 74, 24);
		panel.add(lblEmail);

		// Campos de texto
		txtID = new JTextField();
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtID.setBounds(102, 46, 263, 24);
		panel.add(txtID);
		txtID.setColumns(10);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtName.setColumns(10);
		txtName.setBounds(102, 81, 263, 24);
		panel.add(txtName);

		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSurname.setColumns(10);
		txtSurname.setBounds(102, 120, 263, 24);
		panel.add(txtSurname);

		txtAge = new JTextField();
		txtAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAge.setColumns(10);
		txtAge.setBounds(102, 155, 263, 24);
		panel.add(txtAge);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(102, 190, 263, 24);
		panel.add(txtEmail);

		// Botones
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBounds(78, 225, 89, 23);
		btnSave.addActionListener(this);
		panel.add(btnSave);

		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(177, 225, 89, 23);
		btnUpdate.addActionListener(this);
		panel.add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(276, 225, 89, 23);
		btnDelete.addActionListener(this);
		panel.add(btnDelete);

		// Tabla
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(417, 71, 467, 284);
		contentPane.add(scrollPane);

		// Modelo de la tabla
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Name", "Surname", "Age", "Email" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, Integer.class, String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		// Configuración de la tabla
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
	}

	/*
	*	Método actionPerformed
	*	Ejecuta la acción escogida
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSave) {
			String id = txtID.getText();
			String name = txtName.getText();
			String surname = txtSurname.getText();
			String age = txtAge.getText();
			String email = txtEmail.getText();
			if (id.isEmpty()|| name.isEmpty() || surname.isEmpty() || age.isEmpty() || email.isEmpty()) {
				showMessage("POR FAVOR COMPLETA TODOS LOS CAMPOS");
				return;
			}
			app.enrollStudent(id, name, surname, Integer.parseInt(age), email);
		} else if (e.getSource() == btnDelete) {
			String id = txtID.getText();
			app.dropStudent(id);
		} else if (e.getSource() == btnUpdate) {
			String id = txtID.getText();
			String name = txtName.getText();
			String surname = txtSurname.getText();
			String age = txtAge.getText();
			String email = txtEmail.getText();
			if (id.isEmpty()|| name.isEmpty() || surname.isEmpty() || age.isEmpty() || email.isEmpty()) {
				showMessage("POR FAVOR COMPLETA TODOS LOS CAMPOS");
				return;
			}
			app.updateStudent(id, name, surname, Integer.parseInt(age), email);
		}
	}

	/*
	*	Método showMessage
	*	Enseña un mensaje
	*/
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	/*
	*	Método clear
	*	Limpia los textos
	*/
	public void clear() {
		txtID.setText("");
		txtName.setText("");
		txtSurname.setText("");
		txtAge.setText("");
		txtEmail.setText("");
	}

	/*
	*	Método load
	*	Enseña todos los estudiantes
	*/
	public void load() {
		app.showAllStudents();
	}

	/*
	*	Método refresh
	*	Limpia y caraga en la pantalla
	*/
	public void refresh() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		clear();
		load();
	}

	/*
	*	Método addStudent
	*	Añade un nuevo estudiante
	*	
	*	@param id ID del nuevo estudiante
	*	@param name Nombre del nuevo estudiante
	*	@param surname Apellido del nuevo estudiante 
	*	@param age Edad del nuevo estudiante
	*   @param email Correo electrónico del nuevo estudiante
	*/
	public void addStudent(String id, String name, String surname, int age, String email) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.addRow(new Object[] { id, name, surname, age, email });
	}
}
