package gui; 

import app.Sistema; 
import modelo.Paciente; 
import modelo.Estadisticas; 
import modelo.Doctor;
import modelo.Pediatra;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import gui.VentanaMenu; // Importación sugerida

public class VentanaEstadisticas extends JFrame {

    private Paciente paciente;
    private Sistema sistema; // Necesitamos el sistema para obtener los costos de consulta
    private JButton btnRegresar;

    public VentanaEstadisticas(Paciente paciente, Sistema sistema) { 
        this.paciente = paciente;
        this.sistema = sistema;

        setTitle("Estadísticas - " + paciente.getNombre());
        setSize(350, 650);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));

        initComponents();
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
        // --- Título ---
        JLabel lblTitulo = new JLabel("📈 Tus Datos y Tendencias", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);
        
        // --- Contenido de Estadísticas ---
        JTextArea areaEstadisticas = new JTextArea(generarTextoEstadisticas());
        areaEstadisticas.setEditable(false);
        areaEstadisticas.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaEstadisticas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scroll = new JScrollPane(areaEstadisticas);
        add(scroll, BorderLayout.CENTER);

        // --- Panel para el Botón Regresar ---
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        Icon iconRegresar = getIcon("img/icono_atras.png"); 
        btnRegresar = new JButton("Volver al Menú", iconRegresar);
        btnRegresar.setPreferredSize(new Dimension(300, 40));
        
        // 🚨 CAMBIOS APLICADOS: Fondo Blanco y Texto Negro
        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setForeground(Color.BLACK);
        
        btnRegresar.addActionListener(e -> {
            // Asumiendo que VentanaMenu existe
            new VentanaMenu(paciente, sistema).setVisible(true); 
            dispose();
        });
        
        panelInferior.add(btnRegresar);
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    /**
     * Obtiene el costo de consulta para un tipo de doctor a partir de la lista de doctores del sistema.
     * @param esPediatra Si es true, busca el costo del pediatra; de lo contrario, del doctor general.
     * @return El valor de la consulta o 0.0 si no se encuentra.
     */
    private double obtenerCostoUnitario(boolean esPediatra) {
        List<Doctor> doctoresSistema = sistema.getDoctores();
        for (Doctor doc : doctoresSistema) {
            if (esPediatra && doc instanceof Pediatra) {
                return doc.getValorConsulta();
            }
            if (!esPediatra && !(doc instanceof Pediatra)) {
                return doc.getValorConsulta();
            }
        }
        return 0.0;
    }
    
    /**
     * Genera un texto con estadísticas reales del sistema, separando la información y mostrando ambos costos de consulta.
     */
    private String generarTextoEstadisticas() {
        // Se asume que estos métodos existen y retornan la información correcta
        int numConsultas = Estadisticas.getInstance().getConsultasRealizadas(paciente);
        int numRecordatorios = Estadisticas.getInstance().getRecordatoriosProgramados(paciente);
        String doctorFavorito = paciente.getDoctorMasConsultado() != null 
                                 ? paciente.getDoctorMasConsultado().getNombre()
                                 : "N/A";
        double costoTotal = Estadisticas.getInstance().getCostoTotal(paciente);
        
        double costoConsultaGeneral = obtenerCostoUnitario(false);
        double costoConsultaPediatra = obtenerCostoUnitario(true);
        
        // Creación del texto estructurado
        return String.format(
            "================================\n" +
            "👤 INFORMACIÓN DEL PACIENTE\n" +
            "================================\n" +
            "Nombre: %s\n" +
            "ID: %s\n" +
            "EPS: %s\n" +
            
            "\n================================\n" +
            "🩺 CONSULTAS MÉDICAS\n" +
            "================================\n" +
            "Total de Consultas: %d\n" +
            "Costo Unitario Doctor: $%,d COP\n" + // <-- Muestra costo Gral.
            "Costo Unitario Pediatra: $%,d COP\n" + // <-- Muestra costo Ped.
            "Doctor Más Consultado: %s\n" +
            
            "\n================================\n" +
            "⏰ RECORDATORIOS\n" +
            "================================\n" +
            "Total Programados: %d\n" +
            "Costo Unitario (COP): $5,000\n" + // Se mantiene el formato fijo de costo
            
            "\n================================\n" +
            "💸 RESUMEN FINANCIERO\n" +
            "================================\n" +
            "Costo Total Pagado: $%,d COP\n" +
            "================================",
            
            paciente.getNombre(),
            paciente.getId(),
            paciente.getEps().getNombre(),
            numConsultas,
            (int)costoConsultaGeneral, // <-- Usa costo General
            (int)costoConsultaPediatra, // <-- Usa costo Pediatra
            doctorFavorito,
            numRecordatorios,
            (int)costoTotal
        );
    }
}