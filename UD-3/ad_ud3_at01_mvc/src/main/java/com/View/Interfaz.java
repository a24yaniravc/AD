package com.View;

import java.awt.BorderLayout;

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

import com.Model.RepositorioTareas;

/*La vista debe permitir:
    Mostrar el menú de opciones.
    Mostrar listados de tareas.
    Pedir al usuario los datos para crear o modificar una tarea.
    Mostrar mensajes de error o confirmación */

public class Interfaz extends JFrame implements ActionListener {
    // Variables
    private JPanel contentPane;

    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtAge;

    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnUpdate;

    private JPanel panel;

    private JTable table;

    private RepositorioTareas app;

    /**
     * Crea el framework de la interfaz gráfica.
     * 
     * @param app
     */
    public Interfaz(RepositorioTareas app) {
        this.app = app;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 500, 450, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);

        // Título
        JLabel lblTitle = new JLabel("To-Do List Management System");
        lblTitle.setForeground(new Color(0, 0, 0));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitle.setBounds(20, 11, 387, 60);
        contentPane.add(lblTitle);

        // Panel secundario
        panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel.setBounds(20, 71, 387, 284);
        contentPane.add(panel);
        panel.setLayout(null);

        mostrarMenu();

        // Botones
        /*
         * btnUpdate = new JButton("Actualizar");
         * btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
         * btnUpdate.setBounds(177, 225, 89, 23);
         * btnUpdate.addActionListener(this);
         * panel.add(btnUpdate);
         * 
         * btnDelete = new JButton("Mostrar todos");
         * btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
         * btnDelete.setBounds(276, 225, 89, 23);
         * btnDelete.addActionListener(this);
         * panel.add(btnDelete);
         */
    }

    /**
     * Muestra un mensaje en la interfaz gráfica.
     * 
     * @param mensaje
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Muestra el menú de opciones en la interfaz gráfica.
     */
    public void mostrarMenu() {
        // Configuración del panel principal
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);

        // Vista del menú
        JLabel lblMenu = new JLabel("Menú de opciones:");
        lblMenu.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblMenu.setBounds(30, 15, 150, 24);
        panel.add(lblMenu);

        JLabel lblOption1 = new JLabel("1. Listar todas las tareas");
        lblOption1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOption1.setBounds(30, 40, 200, 24);
        panel.add(lblOption1);

        JLabel lblOption2 = new JLabel("2. Crear una nueva tarea");
        lblOption2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOption2.setBounds(30, 65, 200, 24);
        panel.add(lblOption2);

        JLabel lblOption3 = new JLabel("3. Marcar tarea como completada");
        lblOption3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOption3.setBounds(30, 90, 300, 24);
        panel.add(lblOption3);

        JLabel lblOption4 = new JLabel("4. Eliminar una tarea");
        lblOption4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOption4.setBounds(30, 115, 200, 24);
        panel.add(lblOption4);

        JLabel lblOption5 = new JLabel("5. Buscar tarea por ID");
        lblOption5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOption5.setBounds(30, 140, 200, 24);
        panel.add(lblOption5);

        JLabel lblOption6 = new JLabel("6. Salir");
        lblOption6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOption6.setBounds(30, 165, 200, 24);
        panel.add(lblOption6);

        // Campo de texto y botón para seleccionar opción
        JLabel lblSelect = new JLabel("Seleccionar:");
        lblSelect.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSelect.setBounds(30, 200, 120, 24);
        panel.add(lblSelect);

        JTextField txtOption = new JTextField();
        txtOption.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtOption.setBounds(30, 230, 50, 24);
        panel.add(txtOption);

        JButton btnSelect = new JButton("Seleccionar");
        btnSelect.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSelect.setBounds(90, 230, 150, 24);
        panel.add(btnSelect);

        // Acción del botón
        btnSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String textFieldValue = txtOption.getText();
                int option;
                try {
                    option = Integer.parseInt(textFieldValue);
                } catch (NumberFormatException e) {
                    mostrarMensaje("Por favor, ingrese un número válido.");
                    return;
                }

                // Acciones según la opción seleccionada
                switch (option) {
                    case 1:
                        formarTabla();
                        break;
                    case 2:
                        crearTarea();
                        break;
                    case 3:
                        marcarCompletada();
                        break;
                    case 4:
                        eliminarTarea();
                        break;
                    case 5:
                        buscarID();
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        mostrarMensaje("Opción no válida. Por favor, seleccione una opción del 1 al 6.");
                        break;
                }
            }
        });

    }

    /**
     * Muestra el formulario para crear una nueva tarea.
     */
    public void crearTarea() {
        panel.removeAll();

        // Configuración del panel principal
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);

        JLabel lblCreate = new JLabel("Crear nueva tarea");
        lblCreate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCreate.setBounds(30, 35, 200, 24);
        panel.add(lblCreate);

        // Etiquetas y campos de texto
        JLabel lblName = new JLabel("Titulo");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblName.setBounds(30, 95, 46, 24);
        panel.add(lblName);

        JLabel lblSurname = new JLabel("Descripcion");
        lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSurname.setBounds(30, 130, 90, 24);
        panel.add(lblSurname);

        // Campos de texto
        txtName = new JTextField();
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtName.setColumns(10);
        txtName.setBounds(150, 95, 200, 24);
        panel.add(txtName);

        txtSurname = new JTextField();
        txtSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtSurname.setColumns(10);
        txtSurname.setBounds(150, 130, 200, 24);
        panel.add(txtSurname);

        // Botones
        btnSave = new JButton("Guardar");
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSave.setBounds(30, 180, 320, 30);
        btnSave.addActionListener(this);
        panel.add(btnSave);

        JButton btnMenu = new JButton("Menú");
        btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnMenu.setBounds(270, 30, 80, 30);
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                panel.removeAll();
                mostrarMenu();

            }
        });
        panel.add(btnMenu);
    }

    /**
     * Forma la tabla para mostrar todas las tareas.
     */
    public void formarTabla() {
        panel.removeAll();

        // Configuración del panel principal
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);

        // Entrada
        JLabel lblCreate = new JLabel("Lista de Tareas");
        lblCreate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCreate.setBounds(30, 35, 240, 24);
        panel.add(lblCreate);

        JButton btnMenu = new JButton("Menú");
        btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnMenu.setBounds(270, 30, 80, 30);
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                panel.removeAll();
                mostrarMenu();

            }
        });
        panel.add(btnMenu);

        // Tabla
        String[] columnNames = { "ID", "Titulo", "Descripcion", "Completada" };
        Object[][] data = app.obtenerTodas().stream().map(t -> new Object[] {
                t.getId(),
                t.getTitulo(),
                t.getDescripcion(),
                t.isCompletada() ? "Sí" : "No"
        }).toArray(Object[][]::new);

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 80, 320, 180);
        panel.add(scrollPane);


    }

    /**
     * Marca una tarea como completada.
     */
    public void marcarCompletada() {
        panel.removeAll();

        // Configuración del panel principal
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);

        // Entrada
        JLabel lblCreate = new JLabel("Marcar tarea como completada");
        lblCreate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCreate.setBounds(30, 35, 240, 24);
        panel.add(lblCreate);

        JButton btnMenu = new JButton("Menú");
        btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnMenu.setBounds(270, 30, 80, 30);
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                panel.removeAll();
                mostrarMenu();

            }
        });
        panel.add(btnMenu);

        // Etiqueta y campo de texto
        JLabel lblID = new JLabel("ID de la tarea:");
        lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblID.setBounds(30, 120, 120, 24);
        panel.add(lblID);

        txtID = new JTextField();
        txtID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtID.setColumns(10);
        txtID.setBounds(150, 120, 200, 24);
        panel.add(txtID);

        // Botón
        JButton btnMark = new JButton("Marcar como completada");
        btnMark.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnMark.setBounds(30, 180, 320, 30);
        btnMark.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String idText = txtID.getText();
                int id;
                try {
                    id = Integer.parseInt(idText);
                } catch (NumberFormatException e) {
                    mostrarMensaje("Por favor, ingrese un ID válido.");
                    return;
                }
                // app.marcarCompletada(id);
                mostrarMensaje("Tarea con ID " + id + " marcada como completada.");
            }
        });
        panel.add(btnMark);
    }

    /**
     * Elimina una tarea por su ID.
     */
    public void eliminarTarea() {
        panel.removeAll();

        // Configuración del panel principal
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);

        // Entrada
        JLabel lblCreate = new JLabel("Eliminar tarea");
        lblCreate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCreate.setBounds(30, 35, 240, 24);
        panel.add(lblCreate);

        JButton btnMenu = new JButton("Menú");
        btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnMenu.setBounds(270, 30, 80, 30);
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                panel.removeAll();
                mostrarMenu();

            }
        });
        panel.add(btnMenu);

        // Etiqueta y campo de texto
        JLabel lblID = new JLabel("ID de la tarea");
        lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblID.setBounds(30, 120, 120, 24);
        panel.add(lblID);

        txtID = new JTextField();
        txtID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtID.setColumns(10);
        txtID.setBounds(150, 120, 200, 24);
        panel.add(txtID);

        // Botón
        JButton btnDelete = new JButton("Eliminar tarea");
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDelete.setBounds(30, 180, 320, 30);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String idText = txtID.getText();
                int id;
                try {
                    id = Integer.parseInt(idText);
                } catch (NumberFormatException e) {
                    mostrarMensaje("Por favor, ingrese un ID válido.");
                    return;
                }
                // app.eliminar(id);
                mostrarMensaje("Tarea con ID " + id + " eliminada.");
            }
        });
        panel.add(btnDelete);
    }

    /**
     * Busca una tarea por su ID.
     */
    public void buscarID() {
        panel.removeAll();

        // Configuración del panel principal
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);

        // Entrada
        JLabel lblCreate = new JLabel("Eliminar tarea");
        lblCreate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCreate.setBounds(30, 35, 240, 24);
        panel.add(lblCreate);

        JButton btnMenu = new JButton("Menú");
        btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnMenu.setBounds(270, 30, 80, 30);
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                panel.removeAll();
                mostrarMenu();

            }
        });
        panel.add(btnMenu);

        // Etiqueta y campo de texto
        JLabel lblID = new JLabel("ID de la tarea");
        lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblID.setBounds(30, 80, 120, 24);
        panel.add(lblID);

        txtID = new JTextField();
        txtID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtID.setColumns(10);
        txtID.setBounds(150, 80, 200, 24);
        panel.add(txtID);

        // Botón
        JButton btnSearch = new JButton("Buscar tarea");
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSearch.setBounds(30, 130, 320, 30);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String idText = txtID.getText();
                int id;
                try {
                    id = Integer.parseInt(idText);
                } catch (NumberFormatException e) {
                    mostrarMensaje("Por favor, ingrese un ID válido.");
                    return;
                }
                // app.buscarPorID(id);
                mostrarMensaje("Tarea con ID " + id + " encontrada.");
            }
        });
        panel.add(btnSearch);

        // Mostrar la tarea encontrada
        JLabel lblResult = new JLabel("Resultado de la búsqueda");
        lblResult.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblResult.setBounds(30, 190, 240, 24);
        panel.add(lblResult);

        JLabel lblTask = new JLabel("METER AQUI");
        lblTask.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTask.setBounds(30, 220, 320, 24);
        panel.add(lblTask);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public static void main(String[] args) {
        RepositorioTareas app = RepositorioTareas.getInstancia();
        Interfaz interfaz = new Interfaz(app);
        interfaz.setVisible(true);

    }

}
