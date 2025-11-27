package gui;

import app.Sistema;
import modelo.Paciente;
import modelo.Recordatorio;
import modelo.Estadisticas;
import gui.VentanaMenu; 

import javax.swing.*;
import java.awt.*;
import java.net.URL; 
import java.util.List;
import java.util.ArrayList;

public class VentanaRecordatorios extends JFrame {
    private Paciente paciente;
    private Sistema sistema;

    public VentanaRecordatorios(Sistema sistema, Paciente paciente) {
        this.sistema = sistema;
        this.paciente = paciente;

        setTitle("Recordatorios - " + paciente.getNombre());
        setSize(350, 650); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));

        initComponents();
    }
    
    // ... (Método getIcon sin cambios) ...
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
        JLabel lblTitulo = new JLabel("⏰ Tus Recordatorios", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);
        
        // --- Área de Texto Principal (CENTRO) ---
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13)); 
        
        if (paciente.getRecordatorios().isEmpty()) {
            area.setText("No hay recordatorios disponibles.\nConsulte a un doctor para obtener una receta y programar uno.");
        } else {
            StringBuilder sb = new StringBuilder("Medicamentos programados:\n\n");
            for (Recordatorio r : paciente.getRecordatorios()) {
                // Muestra el estado (ACTIVO) o (PENDIENTE)
                String estado = r.isActivo() ? " (ACTIVO)" : " (PENDIENTE)"; 
                
                sb.append("• ").append(r.getMedicamento().getNombre())
                        .append(estado) // Agrega el estado
                        .append("\n  Dosis: ").append(r.getMedicamento().getDosis())
                        .append("\n  Hora: ").append(r.getHora())
                        .append("\n\n");
            }
            area.setText(sb.toString());
        }
        
        JScrollPane scroll = new JScrollPane(area);
        add(scroll, BorderLayout.CENTER);


        // --- Iconos y Botones (SUR) ---
        Icon iconProgramar = getIcon("img/icono_campana.png"); 
        Icon iconVolver = getIcon("img/icono_regresar.png");    
        
        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0); 
        gbc.ipadx = 50; 
        gbc.weightx = 1.0; 

        
        JButton btnProgramar = new JButton("Programar Recordatorio", iconProgramar);
        btnProgramar.setFont(new Font("Arial", Font.BOLD, 15));
        // Mantiene el color verde para destacar la acción principal de programar
        btnProgramar.setBackground(new Color(50, 205, 50)); 
        btnProgramar.setForeground(Color.WHITE);
        gbc.gridy = 0;
        panelBotones.add(btnProgramar, gbc);

        JButton btnVolver = new JButton("Volver al Menú", iconVolver);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 15));
        // 🚨 CAMBIO APLICADO: Fondo blanco y texto negro
        btnVolver.setBackground(Color.WHITE); 
        btnVolver.setForeground(Color.BLACK); 
        gbc.gridy = 1;
        panelBotones.add(btnVolver, gbc);
        
        add(panelBotones, BorderLayout.SOUTH);

        // --- Lógica de Eventos ---
        btnProgramar.addActionListener(e -> programarRecordatorio());
        
        btnVolver.addActionListener(e -> {
            new VentanaMenu(paciente, sistema).setVisible(true);
            dispose();
        });
    }

    private void programarRecordatorio() {
        List<Recordatorio> recordatoriosPendientes = new ArrayList<>();
        
        // 1. Filtrar solo los recordatorios que NO están activos
        for (Recordatorio r : paciente.getRecordatorios()) {
            if (!r.isActivo()) {
                recordatoriosPendientes.add(r);
            }
        }
        
        int cantidad = recordatoriosPendientes.size();

        if (cantidad == 0) {
            JOptionPane.showMessageDialog(this, "No hay recordatorios pendientes por programar.",
                    "Sin pendientes", JOptionPane.INFORMATION_MESSAGE);
            // Redirigir al menú ya que no hay acción
            new VentanaMenu(paciente, sistema).setVisible(true);
            dispose();
            return;
        }

        // 2. Calcular el costo total de los pendientes
        double costoUnitario = Estadisticas.getCostoRecordatorio(); 
        double costoTotal = cantidad * costoUnitario;
        
        // 3. Registrar, cobrar y MARCAR COMO ACTIVO cada recordatorio pendiente
        for (Recordatorio r : recordatoriosPendientes) {
             // Registrar y cobrar en estadísticas
             Estadisticas.getInstance().registrarRecordatorio(paciente);
             
             // Marcar como activo para evitar doble cobro
             r.setActivo(true); 
        }

        // 4. Mostrar el costo total
        JOptionPane.showMessageDialog(this,
                String.format("¡%d recordatorio(s) programado(s) exitosamente y activado(s)!\nSe ha cobrado $%,d COP.", 
                    cantidad, (int)costoTotal),
                "Confirmación", JOptionPane.INFORMATION_MESSAGE);

        // Volver al menú después de la acción
        new VentanaMenu(paciente, sistema).setVisible(true);
        dispose();
    }
}