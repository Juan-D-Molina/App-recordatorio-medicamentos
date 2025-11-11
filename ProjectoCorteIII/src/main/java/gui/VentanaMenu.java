package gui;

import modelo.Paciente;
import modelo.Doctor;
import servicios.Alarma;
import modelo.Estadisticas;

import javax.swing.*;

public class VentanaMenu extends JFrame {
    private JButton btnDoctor, btnRecordatorios, btnProbarAlarma, btnEstadisticas, btnRegresar;
    private Paciente paciente;

    public VentanaMenu(Paciente paciente) {
        this.paciente = paciente;

        setTitle("Menú Principal - " + paciente.getNombre());
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        btnDoctor.addActionListener(e -> mostrarDoctor());
        btnRecordatorios.addActionListener(e -> mostrarRecordatorios());
        btnProbarAlarma.addActionListener(e -> probarAlarma());
        btnEstadisticas.addActionListener(e -> mostrarEstadisticas());
        btnRegresar.addActionListener(e -> regresar());
    }

    private void mostrarDoctor() {
        StringBuilder info = new StringBuilder();
        for (Doctor doc : paciente.getDoctoresConsultados()) {
            info.append(doc.getNombre()).append("\n");
        }
        if(info.length() == 0) info.append("No ha consultado ningún doctor.");
        JOptionPane.showMessageDialog(this, info.toString(), "Doctor Consultado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarRecordatorios() {
        StringBuilder sb = new StringBuilder();
        paciente.getRecordatorios().forEach(r -> sb.append(r.getMedicamento().getNombre())
                .append(" - ").append(r.getHora()).append("\n"));
        if(sb.length() == 0) sb.append("No hay recordatorios.");
        JOptionPane.showMessageDialog(this, sb.toString(), "Recordatorios", JOptionPane.INFORMATION_MESSAGE);
    }

    private void probarAlarma() {
        Alarma alarma = new Alarma(5, paciente);
        alarma.activar();
        Estadisticas.getInstance().registrarAlarma(paciente);
    }

    private void mostrarEstadisticas() {
        JOptionPane.showMessageDialog(this, Estadisticas.getInstance().mostrarEstadisticas(),
                "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void regresar() {
        VentanaPaciente vp = new VentanaPaciente(Estadisticas.getInstance().getPaciente1(),
                Estadisticas.getInstance().getPaciente2());
        vp.setVisible(true);
        this.dispose();
    }
}
