package gui;

import app.Sistema;
import modelo.Paciente;
import servicios.ReporteDoctoresMasConsultados;
import servicios.ReportePacientesMasAlarmas;
import servicios.ReportePacientesMasConsulta;
import gui.VentanaMenu; // Importación sugerida

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class VentanaPDF extends JFrame {

    private Paciente paciente;
    private Sistema sistema;
    private JButton btnReporteDoctor, btnReporteGasto, btnReporteAlarmas, btnRegresar;

    public VentanaPDF(Paciente paciente, Sistema sistema) {
        this.paciente = paciente;
        this.sistema = sistema;

        setTitle("Generar Reportes PDF");
        setSize(350, 650); // Formato de celular: 350x650 píxeles
        // ✅ CORRECCIÓN: Se usa DISPOSE_ON_CLOSE (solo cierra esta ventana)
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null); // Usamos layout nulo para el posicionamiento absoluto

        initComponents();
        addListeners();
    }
    
    /**
     * Carga el icono como recurso del proyecto y lo escala a 30x30 píxeles.
     */
    private Icon getIcon(String pathRuta) {
        final int ICON_SIZE = 30;
        try {
            URL imgURL = getClass().getClassLoader().getResource(pathRuta);
            
            if (imgURL != null) {
                ImageIcon originalIcon = new ImageIcon(imgURL);
                Image image = originalIcon.getImage();
                Image scaledImage = image.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            } else {
                System.err.println("Recurso de imagen no encontrado: " + pathRuta);
                return UIManager.getIcon("OptionPane.warningIcon"); 
            }
        } catch (Exception e) {
            System.err.println("Error al cargar y escalar el icono: " + pathRuta);
            return UIManager.getIcon("OptionPane.errorIcon"); 
        }
    }

    private void initComponents() {
        int btnWidth = 280;
        int btnX = (350 - btnWidth) / 2;
        int yStart = 150;
        int spacing = 60;

        // --- Título ---
        JLabel lblTitulo = new JLabel("📊 Generación de Reportes PDF", SwingConstants.CENTER);
        lblTitulo.setBounds(10, 50, 310, 25);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo);

        // --- Botón 1: Doctores Más Consultados ---
        Icon iconDoctor = getIcon("img/icono_doctor.png");
        btnReporteDoctor = new JButton("Doctores Más Consultados", iconDoctor);
        btnReporteDoctor.setBounds(btnX, yStart, btnWidth, 45);
        // 🚨 CAMBIOS APLICADOS: Fondo Blanco y Texto Negro
        btnReporteDoctor.setBackground(Color.WHITE);
        btnReporteDoctor.setForeground(Color.BLACK);
        btnReporteDoctor.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnReporteDoctor);

        // --- Botón 2: Pacientes con Mayor Gasto ---
        Icon iconGasto = getIcon("img/icono_dinero.png");
        btnReporteGasto = new JButton("Pacientes con Mayor Gasto", iconGasto);
        btnReporteGasto.setBounds(btnX, yStart + spacing, btnWidth, 45);
        // 🚨 CAMBIOS APLICADOS: Fondo Blanco y Texto Negro
        btnReporteGasto.setBackground(Color.WHITE);
        btnReporteGasto.setForeground(Color.BLACK);
        btnReporteGasto.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnReporteGasto);

        // --- Botón 3: Pacientes con Más Alarmas ---
        Icon iconAlarma = getIcon("img/icono_reloj.png");
        btnReporteAlarmas = new JButton("Pacientes con Más Recordatorios", iconAlarma);
        btnReporteAlarmas.setBounds(btnX, yStart + 2 * spacing, btnWidth, 45);
        // 🚨 CAMBIOS APLICADOS: Fondo Blanco y Texto Negro
        btnReporteAlarmas.setBackground(Color.WHITE);
        btnReporteAlarmas.setForeground(Color.BLACK);
        btnReporteAlarmas.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnReporteAlarmas);

        // --- Botón Regresar ---
        Icon iconRegresar = getIcon("img/icono_atras.png");
        btnRegresar = new JButton("Volver al Menú", iconRegresar);
        btnRegresar.setBounds(btnX, 550, btnWidth, 40);
        btnRegresar.setFont(new Font("Arial", Font.PLAIN, 14));
        // 🚨 CAMBIOS APLICADOS: Fondo Blanco y Texto Negro
        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setForeground(Color.BLACK);
        add(btnRegresar);
    }

    private void addListeners() {
        // Asegúrate de que la carpeta 'reportes/' exista o ajusta la ruta.
        final String RUTA_BASE = "reportes/"; 

        // 1. Reporte Doctores
        btnReporteDoctor.addActionListener(e -> {
            new ReporteDoctoresMasConsultados(RUTA_BASE + "Reporte_Doctores_Consultados.pdf").generar();
            JOptionPane.showMessageDialog(this, "Reporte de Doctores generado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        });

        // 2. Reporte Gasto
        btnReporteGasto.addActionListener(e -> {
            new ReportePacientesMasConsulta(RUTA_BASE + "Reporte_Pacientes_Mayor_Gasto.pdf").generar();
            JOptionPane.showMessageDialog(this, "Reporte de Mayor Gasto generado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        });

        // 3. Reporte Alarmas
        btnReporteAlarmas.addActionListener(e -> {
            new ReportePacientesMasAlarmas(RUTA_BASE + "Reporte_Pacientes_Mas_Recordatorios.pdf").generar();
            JOptionPane.showMessageDialog(this, "Reporte de Recordatorios generado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        });
        
        // 4. Regresar al Menú
        btnRegresar.addActionListener(e -> {
            // Asumiendo que VentanaMenu existe
            new VentanaMenu(paciente, sistema).setVisible(true);
            dispose();
        });
    }
}