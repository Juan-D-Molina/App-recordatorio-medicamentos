package gui;

import app.Sistema;
import modelo.*;
import javax.swing.*;
import java.awt.*;

public class VentanaRecordatorios extends JFrame {
    private Paciente paciente;
    private Sistema sistema;

    public VentanaRecordatorios(Sistema sistema, Paciente paciente) {
        this.sistema = sistema;
        this.paciente = paciente;

        setTitle("Recordatorios - " + paciente.getNombre());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JTextArea area = new JTextArea();
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        add(scroll, BorderLayout.CENTER);

        if (paciente.getRecordatorios().isEmpty()) {
            area.setText("No hay recordatorios disponibles. Consulte a un doctor para obtener uno.");
        } else {
            StringBuilder sb = new StringBuilder("Medicamentos programados:\n\n");
            for (Recordatorio r : paciente.getRecordatorios()) {
                sb.append(r.getMedicamento().getNombre())
                        .append(" - Dosis: ").append(r.getMedicamento().getDosis())
                        .append(" - Hora: ").append(r.getHora())
                        .append("\n");
            }
            area.setText(sb.toString());
        }

        // --- Configuración de botones a Izquierda/Derecha ---
        JPanel panelBotones = new JPanel(new BorderLayout()); 
        JPanel panelIzquierda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton btnProgramar = new JButton("Programar recordatorio");
        JButton btnVolver = new JButton("Volver"); 

        panelIzquierda.add(btnVolver);
        panelBotones.add(panelIzquierda, BorderLayout.WEST);

        panelDerecha.add(btnProgramar);
        panelBotones.add(panelDerecha, BorderLayout.EAST);
        
        add(panelBotones, BorderLayout.SOUTH);

        // --- Lógica de Eventos ---
        btnProgramar.addActionListener(e -> programarRecordatorio());
        
        btnVolver.addActionListener(e -> {
            new VentanaMenu(paciente, sistema).setVisible(true);
            dispose();
        });
    }

    private void programarRecordatorio() {
        if (paciente.getRecordatorios().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay recordatorios recetados para programar. Consulte a un doctor.",
                    "Sin recordatorios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // --- LÓGICA CORREGIDA: La GUI NO MANEJA EL COSTO ---
        // La GUI solo pide al Modelo (Estadisticas) que ejecute la operación de registro.
        Estadisticas.getInstance().registrarRecordatorio(paciente);
        
        double costo = Estadisticas.getCostoRecordatorio(); // Obtiene el costo solo para el mensaje de confirmación
        
        JOptionPane.showMessageDialog(this,
                "Recordatorio programado exitosamente.\nSe ha cobrado $" + (int)costo + " COP.",
                "Confirmación", JOptionPane.INFORMATION_MESSAGE);

        new VentanaMenu(paciente, sistema).setVisible(true);
        dispose();
    }
}