package gui;

import app.Sistema;
import modelo.Paciente;
import servicios.Alarma;
import javax.swing.*;
import java.awt.Font; // Import necesario

public class VentanaMenu extends JFrame {
    private JButton btnDoctor, btnRecordatorios, btnProbarAlarma, btnEstadisticas, btnRegresar;
    private Paciente paciente;
    private Sistema sistema;

    public VentanaMenu(Paciente paciente, Sistema sistema) {
        this.paciente = paciente;
        this.sistema = sistema;

        setTitle("Menú Principal - " + paciente.getNombre());
        setSize(350, 320); // Tamaño aumentado ligeramente
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // --- Mensaje de Bienvenida Añadido ---
        JLabel lblBienvenida = new JLabel("Bienvenido a Ikigai, " + paciente.getNombre(), SwingConstants.CENTER);
        lblBienvenida.setBounds(10, 10, 310, 25);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblBienvenida);

        // --- Botones (coordenadas 'y' ajustadas) ---
        int yOffset = 40; // Espacio para el saludo

        btnDoctor = new JButton("Consultar Doctor");
        btnDoctor.setBounds(80, 20 + yOffset, 180, 30);
        add(btnDoctor);

        btnRecordatorios = new JButton("Ver Recordatorios");
        btnRecordatorios.setBounds(80, 60 + yOffset, 180, 30);
        add(btnRecordatorios);

        btnProbarAlarma = new JButton("Probar Alarma");
        btnProbarAlarma.setBounds(80, 100 + yOffset, 180, 30);
        add(btnProbarAlarma);

        btnEstadisticas = new JButton("Ver Estadísticas");
        btnEstadisticas.setBounds(80, 140 + yOffset, 180, 30);
        add(btnEstadisticas);

        btnRegresar = new JButton("Regresar"); // Botón renombrado a "Cerrar Sesión" o "Regresar"
        btnRegresar.setBounds(80, 180 + yOffset, 180, 30);
        add(btnRegresar);

        // --- Lógica de Eventos (sin cambios excepto btnRegresar) ---

        btnDoctor.addActionListener(e -> {
            new VentanaConsultaDoctor(sistema, paciente).setVisible(true);
            dispose();
        });

        btnRecordatorios.addActionListener(e -> {
            new VentanaRecordatorios(sistema, paciente).setVisible(true);
            dispose();
        });

        btnProbarAlarma.addActionListener(e -> {
            Alarma alarma = new Alarma(5, paciente);
            alarma.activar();
            JOptionPane.showMessageDialog(this, "Alarma activada para sonar en 5 segundos.", "Alarma", JOptionPane.INFORMATION_MESSAGE);
        });

        btnEstadisticas.addActionListener(e -> {
            new VentanaEstadisticas(paciente, sistema).setVisible(true);
            dispose();
        });
        
        // --- Botón Regresar (LÓGICA CORREGIDA) ---
        btnRegresar.addActionListener(e -> {
            // Regresa a la pantalla de selección de login
            new VentanaLogin(sistema).setVisible(true);
            dispose();
        });
    }
}