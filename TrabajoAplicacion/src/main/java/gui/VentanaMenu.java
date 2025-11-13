package gui;

import app.Sistema;
import modelo.Paciente;
import servicios.Alarma;
import javax.swing.*;

public class VentanaMenu extends JFrame {
    private JButton btnDoctor, btnRecordatorios, btnProbarAlarma, btnEstadisticas, btnRegresar;
    private Paciente paciente;
    private Sistema sistema;

    public VentanaMenu(Paciente paciente, Sistema sistema) {
        this.paciente = paciente;
        this.sistema = sistema;

        setTitle("Menú Principal - " + paciente.getNombre());
        setSize(350, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        btnDoctor = new JButton("Consultar Doctor");
        btnDoctor.setBounds(80, 20, 180, 30);
        add(btnDoctor);

        btnRecordatorios = new JButton("Ver Recordatorios");
        btnRecordatorios.setBounds(80, 60, 180, 30);
        add(btnRecordatorios);

        btnProbarAlarma = new JButton("Probar Alarma");
        btnProbarAlarma.setBounds(80, 100, 180, 30);
        add(btnProbarAlarma);

        btnEstadisticas = new JButton("Ver Estadísticas");
        btnEstadisticas.setBounds(80, 140, 180, 30);
        add(btnEstadisticas);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(80, 180, 180, 30);
        add(btnRegresar);

        btnDoctor.addActionListener(e -> {
            new VentanaConsultaDoctor(sistema, paciente).setVisible(true);
            dispose();
        });

        btnRecordatorios.addActionListener(e -> {
            new VentanaRecordatorios(sistema, paciente).setVisible(true);
            dispose();
        });

        btnProbarAlarma.addActionListener(e -> {
            // Crea y activa un hilo de alarma
            Alarma alarma = new Alarma(5, paciente); // Alarma de 5 segundos
            alarma.activar();
             
            JOptionPane.showMessageDialog(this, "Alarma activada para sonar en 5 segundos.", "Alarma", JOptionPane.INFORMATION_MESSAGE);
        });

        btnEstadisticas.addActionListener(e -> {
            // Abre la ventana de estadísticas
            new VentanaEstadisticas(paciente, sistema).setVisible(true);
            dispose();
        });
        
        btnRegresar.addActionListener(e -> {
            // Regresa a la pantalla de selección de paciente
            new VentanaPaciente(sistema.getEstadisticas().getPaciente1(), sistema.getEstadisticas().getPaciente2(), sistema).setVisible(true);
            dispose();
        });
    }
}