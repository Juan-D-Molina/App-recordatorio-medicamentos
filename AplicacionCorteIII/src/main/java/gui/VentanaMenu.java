package gui;

import app.Sistema;
import modelo.Paciente;
import servicios.Alarma;
// Necesitamos importar VentanaLogin para el botón Regresar
import gui.VentanaLogin; 

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class VentanaMenu extends JFrame {
    // btnReportePDF
    private JButton btnDoctor, btnRecordatorios, btnProbarAlarma, btnEstadisticas, btnReportePDF, btnRegresar; 
    private Paciente paciente;
    private Sistema sistema;

    public VentanaMenu(Paciente paciente, Sistema sistema) {
        this.paciente = paciente;
        this.sistema = sistema;

        setTitle("Menú Principal - " + paciente.getNombre());
        setSize(350, 650); 
      
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        this.getContentPane().setBackground(new Color (44, 222, 242));
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
                // Fallback: Retorna un icono estándar si la imagen no existe
                if (pathRuta.contains("pdf")) {
                    return UIManager.getIcon("FileView.fileIcon");
                }
                return UIManager.getIcon("OptionPane.warningIcon"); 
            }
        } catch (Exception e) {
            System.err.println("Error al cargar y escalar el icono: " + pathRuta);
            return UIManager.getIcon("OptionPane.errorIcon"); 
        }
    }

    private void initComponents() {
        //Mensaje de Bienvenida 
        JLabel lblBienvenida = new JLabel("👋 Bienvenido a Ikigai, " + paciente.getNombre(), SwingConstants.CENTER);
        lblBienvenida.setBounds(10, 30, 310, 25);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblBienvenida);

        //Iconos
        Icon iconDoctor = getIcon("iconos/doctor.jpg");
        Icon iconRecordatorios = getIcon("iconos/reloj.png");
        Icon iconAlarma = getIcon("iconos/reloj.png");
        Icon iconEstadisticas = getIcon("iconos/grafica.png");
        Icon iconPDF = getIcon("img/icono_pdf.png"); 
        Icon iconCerrarSesion = getIcon("iconos/exit.png");

        // Configuración y Posicionamiento de Botones 
        int btnWidth = 250;
        int btnX = (350 - btnWidth) / 2;
        int yStart = 100;
        int spacing = 65; 

        // Botón Consultar Doctor
        btnDoctor = new JButton("Consultar Doctor", iconDoctor);
        btnDoctor.setBounds(btnX, yStart, btnWidth, 40);
        btnDoctor.setFont(new Font("Arial", Font.BOLD, 14));
        btnDoctor.setBackground(Color.WHITE); // 🚨 CAMBIO APLICADO
        btnDoctor.setForeground(Color.BLACK); // 🚨 CAMBIO APLICADO para buen contraste
        add(btnDoctor);

        // Botón Ver Recordatorios
        btnRecordatorios = new JButton("Ver Recordatorios", iconRecordatorios);
        btnRecordatorios.setBounds(btnX, yStart + spacing, btnWidth, 40);
        btnRecordatorios.setFont(new Font("Arial", Font.BOLD, 14));
        btnRecordatorios.setBackground(Color.WHITE); // 🚨 CAMBIO APLICADO
        btnRecordatorios.setForeground(Color.BLACK); // 🚨 CAMBIO APLICADO
        add(btnRecordatorios);

        // Botón Probar Alarma
        btnProbarAlarma = new JButton("Probar Alarma", iconAlarma);
        btnProbarAlarma.setBounds(btnX, yStart + 2 * spacing, btnWidth, 40);
        btnProbarAlarma.setFont(new Font("Arial", Font.BOLD, 14));
        btnProbarAlarma.setBackground(Color.WHITE); // 🚨 CAMBIO APLICADO
        btnProbarAlarma.setForeground(Color.BLACK); // 🚨 CAMBIO APLICADO
        add(btnProbarAlarma);

        // Botón Ver Estadísticas
        btnEstadisticas = new JButton("Ver Estadísticas", iconEstadisticas);
        btnEstadisticas.setBounds(btnX, yStart + 3 * spacing, btnWidth, 40);
        btnEstadisticas.setFont(new Font("Arial", Font.BOLD, 14));
        btnEstadisticas.setBackground(Color.WHITE); // 🚨 CAMBIO APLICADO
        btnEstadisticas.setForeground(Color.BLACK); // 🚨 CAMBIO APLICADO
        add(btnEstadisticas);

        // Botón Generar Reporte PDF 
        btnReportePDF = new JButton("Generar Reportes PDF", iconPDF);
        btnReportePDF.setBounds(btnX, yStart + 4 * spacing, btnWidth, 40);
        btnReportePDF.setFont(new Font("Arial", Font.BOLD, 14));
        btnReportePDF.setBackground(new Color(25, 25, 112)); // Azul Oscuro
        btnReportePDF.setForeground(Color.WHITE); 
        add(btnReportePDF);

        // Botón 6: Cerrar Sesión (al final)
        btnRegresar = new JButton("Cerrar Sesión", iconCerrarSesion);
        btnRegresar.setBounds(btnX, 550, btnWidth, 40);
        btnRegresar.setBackground(Color.WHITE); // 🚨 CAMBIO APLICADO
        btnRegresar.setForeground(Color.BLACK); // 🚨 CAMBIO APLICADO
        add(btnRegresar);
    }
    
    private void addListeners() {
        // --- 1. Consultar Doctor ---
        btnDoctor.addActionListener(e -> {
            try {
                // Asumiendo que VentanaConsultaDoctor está implementada
                new VentanaConsultaDoctor(sistema, paciente).setVisible(true);
                dispose();
            } catch (NoClassDefFoundError | Exception ex) {
                JOptionPane.showMessageDialog(this, "La ventana 'VentanaConsultaDoctor' aún no ha sido implementada.", "Falta Implementación", JOptionPane.WARNING_MESSAGE);
            }
        });

        // --- 2. Ver Recordatorios ---
        btnRecordatorios.addActionListener(e -> {
            new VentanaRecordatorios(sistema, paciente).setVisible(true);
            dispose();
        });

        // --- 3. Probar Alarma ---
        btnProbarAlarma.addActionListener(e -> {
            Alarma alarma = new Alarma(5, paciente);
            alarma.activar();
            JOptionPane.showMessageDialog(this, "Alarma activada para sonar en 5 segundos.", "Alarma", JOptionPane.INFORMATION_MESSAGE);
        });

        // --- 4. Ver Estadísticas ---
        btnEstadisticas.addActionListener(e -> {
            try {
                // Asumiendo que VentanaEstadisticas está implementada
                new VentanaEstadisticas(paciente, sistema).setVisible(true);
                dispose();
            } catch (NoClassDefFoundError | Exception ex) {
                JOptionPane.showMessageDialog(this, "La ventana 'VentanaEstadisticas' aún no ha sido implementada.", "Falta Implementación", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        // --- 5. Generar Reporte PDF ---
        btnReportePDF.addActionListener(e -> {
            // Asumiendo que VentanaPDF está implementada
            try {
                new VentanaPDF(paciente, sistema).setVisible(true);
                dispose();
            } catch (NoClassDefFoundError | Exception ex) {
                JOptionPane.showMessageDialog(this, "La ventana 'VentanaPDF' aún no ha sido implementada.", "Falta Implementación", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        // --- 6. Cerrar Sesión (Regresar a Login) ---
        btnRegresar.addActionListener(e -> {
            // Asumiendo que VentanaLogin existe y está en el paquete gui
            try {
                 new VentanaLogin(sistema).setVisible(true);
                 dispose();
            } catch (NoClassDefFoundError | Exception ex) {
                JOptionPane.showMessageDialog(this, "La ventana 'VentanaLogin' no se encuentra para cerrar sesión.", "Error de Clase", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}