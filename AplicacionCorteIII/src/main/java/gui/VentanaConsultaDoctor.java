package gui;

import app.Sistema;
import modelo.Doctor;
import modelo.Estadisticas;
import modelo.Medicamento;
import modelo.Paciente;
import modelo.Pediatra;
import modelo.Recordatorio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class VentanaConsultaDoctor extends JFrame {
    private Sistema sistema;
    private Paciente paciente;

    public VentanaConsultaDoctor(Sistema sistema, Paciente paciente) {
        this.sistema = sistema;
        this.paciente = paciente;

        setTitle("Consulta médica - " + paciente.getNombre());
        setSize(350, 650); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
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
        // --- Título Superior ---
        JLabel lblSeleccion = new JLabel("🧑‍⚕️ Seleccione un profesional:", SwingConstants.CENTER);
        lblSeleccion.setFont(new Font("Arial", Font.BOLD, 18));
        lblSeleccion.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(lblSeleccion, BorderLayout.NORTH);
        
        // --- Iconos (Asegúrate de que las rutas 'iconos/...' sean correctas) ---
        Icon iconDoc = getIcon("iconos/doctor.jpg");
        Icon iconVolver = getIcon("iconos/exit.png");

        // --- Panel para la lista de Doctores ---
        JPanel panelDoctores = new JPanel(new GridLayout(0, 1, 10, 10));
        panelDoctores.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); 

        for (Doctor doc : sistema.getDoctores()) {
            JButton btnDoc = new JButton(doc.getNombre() + " (" + doc.getClass().getSimpleName() + ")", iconDoc);
            btnDoc.setPreferredSize(new Dimension(200, 50));
            btnDoc.setFont(new Font("Arial", Font.PLAIN, 14));
            btnDoc.setHorizontalAlignment(SwingConstants.LEFT);
            
            // 🚨 CAMBIOS APLICADOS: Fondo Blanco y Texto Negro
            btnDoc.setBackground(Color.WHITE);
            btnDoc.setForeground(Color.BLACK);
            
            btnDoc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    realizarConsulta(doc);
                }
            });
            panelDoctores.add(btnDoc);
        }
        
        JScrollPane scrollPane = new JScrollPane(panelDoctores);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        // --- Panel Inferior para el botón Volver ---
        JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelVolver.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JButton btnVolver = new JButton("Volver al Menú", iconVolver);
        btnVolver.setPreferredSize(new Dimension(300, 40));
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        // Mantenemos el color rojo para la acción de volver/cerrar
        btnVolver.setBackground(new Color(255, 102, 102)); 
        btnVolver.setForeground(Color.WHITE);
        
        btnVolver.addActionListener(e -> {
            new VentanaMenu(paciente, sistema).setVisible(true);
            dispose();
        });
        panelVolver.add(btnVolver);
        add(panelVolver, BorderLayout.SOUTH);
    }

    private void realizarConsulta(Doctor doctor) {
        if (doctor.getRecetas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Este doctor no tiene medicamentos registrados.",
                    "Sin recetas", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener el primer medicamento (como ejemplo)
        Medicamento med = doctor.getRecetas().get(0); 
        
        // Determinar el tipo de doctor para el mensaje
        String tipoDoc = (doctor instanceof Pediatra) ? "Pediatra" : "Doctor";
        
        String mensaje = String.format(
                "De acuerdo a la consulta con el %s %s,\nse te recetó lo siguiente:\n\nMedicamento: %s\nDosis: %s\nHora: %s",
                tipoDoc, doctor.getNombre(), med.getNombre(), med.getDosis(), med.getFrecuencia()
        );

        JOptionPane.showMessageDialog(this, mensaje, "Receta médica", JOptionPane.INFORMATION_MESSAGE);

        // Lógica de modelo: Registrar la consulta, crear y añadir el recordatorio
        Recordatorio rec = new Recordatorio(med.getFrecuencia(), med, paciente);
        paciente.agregarRecordatorio(rec);
        paciente.agregarDoctor(doctor);
        Estadisticas.getInstance().registrarConsulta(this.paciente, doctor); 

        // Regresar al menú
        new VentanaMenu(paciente, sistema).setVisible(true);
        dispose();
    }
}