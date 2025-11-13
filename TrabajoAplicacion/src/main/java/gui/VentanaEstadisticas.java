package gui;

import app.Sistema;
import modelo.Paciente;
import modelo.Estadisticas;
import javax.swing.*;
import java.awt.*;

// Esta clase es necesaria para que VentanaMenu compile correctamente.
public class VentanaEstadisticas extends JFrame {

    public VentanaEstadisticas(Paciente pacienteActual, Sistema sistema) {
        setTitle("Estadísticas Generales");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea areaEstadisticas = new JTextArea();
        areaEstadisticas.setEditable(false);
        // Usamos Monospaced para una mejor alineación de texto tabular
        areaEstadisticas.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        // Carga y muestra la cadena de estadísticas generada por el Singleton
        areaEstadisticas.setText(Estadisticas.getInstance().mostrarEstadisticas());

        JScrollPane scrollPane = new JScrollPane(areaEstadisticas);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
            new VentanaMenu(pacienteActual, sistema).setVisible(true);
            dispose();
        });
        add(btnVolver, BorderLayout.SOUTH);
    }
}