package gui;

import app.Sistema;
import modelo.EPS;
import modelo.Paciente;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {
    private Sistema sistema;
    private JTextField txtNombre, txtId, txtEps;
    private JButton btnIniciarSesion, btnCrearPerfil;

    public VentanaLogin(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Bienvenido a Ikigai");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Usamos layout nulo para posicionar

        // --- Etiquetas y Campos de Texto ---
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 20, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(110, 20, 200, 25);
        add(txtNombre);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(30, 60, 80, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(110, 60, 200, 25);
        add(txtId);

        JLabel lblEps = new JLabel("EPS:");
        lblEps.setBounds(30, 100, 80, 25);
        add(lblEps);

        txtEps = new JTextField();
        txtEps.setBounds(110, 100, 200, 25);
        add(txtEps);

        // --- Botones ---
        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setBounds(110, 140, 200, 30);
        add(btnIniciarSesion);

        btnCrearPerfil = new JButton("Crear Perfil");
        // Posicionado abajo a la derecha
        btnCrearPerfil.setBounds(200, 180, 110, 25); 
        btnCrearPerfil.setFont(new Font("Arial", Font.PLAIN, 10)); // Más pequeño
        add(btnCrearPerfil);

        // --- Lógica de Eventos ---

        btnIniciarSesion.addActionListener(e -> iniciarSesion());
        btnCrearPerfil.addActionListener(e -> crearPerfil());
    }

    private void iniciarSesion() {
        String id = txtId.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ID para iniciar sesión.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Paciente paciente = sistema.buscarPacientePorId(id);

        if (paciente != null) {
            abrirMenu(paciente);
        } else {
            JOptionPane.showMessageDialog(this, "Paciente no encontrado. Verifique el ID o cree un perfil.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearPerfil() {
        String nombre = txtNombre.getText();
        String id = txtId.getText();
        String epsNombre = txtEps.getText();

        if (nombre.isEmpty() || id.isEmpty() || epsNombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios para crear un perfil.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verifica si el paciente ya existe
        if (sistema.buscarPacientePorId(id) != null) {
            JOptionPane.showMessageDialog(this, "Ya existe un paciente con ese ID. Intente iniciar sesión.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crea los nuevos objetos
        EPS nuevaEps = new EPS(epsNombre);
        Paciente nuevoPaciente = new Paciente(nombre, id, nuevaEps);

        // Registra al paciente en el sistema
        sistema.registrarPaciente(nuevoPaciente);

        JOptionPane.showMessageDialog(this, "Perfil creado exitosamente. Bienvenido, " + nombre + ".", "Perfil Creado", JOptionPane.INFORMATION_MESSAGE);

        // Abre el menú
        abrirMenu(nuevoPaciente);
    }

    private void abrirMenu(Paciente paciente) {
        VentanaMenu menu = new VentanaMenu(paciente, sistema);
        menu.setVisible(true);
        this.dispose();
    }
}