package gui;

import app.Sistema;
import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaConsultaDoctor extends JFrame {
    private Sistema sistema;
    private Paciente paciente;

    public VentanaConsultaDoctor(Sistema sistema, Paciente paciente) {
        this.sistema = sistema;
        this.paciente = paciente;

        setTitle("Consulta médica - " + paciente.getNombre());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Mejor usar DISPOSE_ON_CLOSE

        JLabel lblSeleccion = new JLabel("Seleccione un profesional para consultar:", SwingConstants.CENTER);
        add(lblSeleccion);

        // Usa el getter para acceder a la lista de doctores
        for (Doctor doc : sistema.getDoctores()) {
            JButton btnDoc = new JButton(doc.getNombre());
            btnDoc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    realizarConsulta(doc);
                }
            });
            add(btnDoc);
        }

        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.addActionListener(e -> {
            new VentanaMenu(paciente, sistema).setVisible(true);
            dispose();
        });
        add(btnVolver);
    }

    private void realizarConsulta(Doctor doctor) {
        // Obtener el medicamento recetado por el doctor
        if (doctor.getRecetas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Este doctor no tiene medicamentos registrados.",
                    "Sin recetas", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Medicamento med = doctor.getRecetas().get(0); // Se asume uno específico por doctor
        String tipoDoc = (doctor instanceof Pediatra) ? "Pediatra" : "Doctor";
        
        String mensaje = String.format(
                "De acuerdo a la consulta con el %s %s,\nse te recetó lo siguiente:\n\nMedicamento: %s\nDosis: %s\nHora: %s",
                tipoDoc, doctor.getNombre(), med.getNombre(), med.getDosis(), med.getFrecuencia()
        );

        JOptionPane.showMessageDialog(this, mensaje, "Receta médica", JOptionPane.INFORMATION_MESSAGE);

        // 1. Crear y guardar recordatorio en el paciente
        Recordatorio rec = new Recordatorio(med.getFrecuencia(), med, paciente);
        paciente.agregarRecordatorio(rec);
        paciente.agregarDoctor(doctor);

        // 2. Registrar estadísticas (USO CORREGIDO)
        // Se llama al método que registra la consulta, el gasto y el doctor consultado.
        Estadisticas.getInstance().registrarConsulta(this.paciente, doctor); 

        // 3. Volver al menú
        new VentanaMenu(paciente, sistema).setVisible(true);
        dispose();
    }
}