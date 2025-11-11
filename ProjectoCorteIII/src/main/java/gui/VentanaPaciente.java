package gui;

import modelo.Paciente;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPaciente extends JFrame {
    private JButton btnPaciente1, btnPaciente2;
    private Paciente paciente1, paciente2;

    public VentanaPaciente(Paciente p1, Paciente p2) {
        this.paciente1 = p1;
        this.paciente2 = p2;

        setTitle("Seleccionar Paciente");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        btnPaciente1 = new JButton(p1.getNombre());
        btnPaciente1.setBounds(50, 20, 200, 30);
        add(btnPaciente1);

        btnPaciente2 = new JButton(p2.getNombre());
        btnPaciente2.setBounds(50, 60, 200, 30);
        add(btnPaciente2);

        btnPaciente1.addActionListener(e -> abrirMenu(paciente1));
        btnPaciente2.addActionListener(e -> abrirMenu(paciente2));
    }

    private void abrirMenu(Paciente paciente) {
        VentanaMenu menu = new VentanaMenu(paciente);
        menu.setVisible(true);
        this.dispose();
    }
}
