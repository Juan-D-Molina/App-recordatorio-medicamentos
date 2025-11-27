package gui;

import app.Sistema;
import modelo.EPS;
import modelo.Paciente;

import javax.swing.*;
import java.awt.*;
import java.net.URL; 
import gui.VentanaMenu; 

public class VentanaLogin extends JFrame {
    private Sistema sistema;
    private JTextField txtNombre, txtId, txtEps;
    private JButton btnIniciarSesion, btnCrearPerfil;

    // 🚨 Nueva constante para la ruta del icono debajo del título
    private static final String RUTA_LOGO_CENTRAL = "iconos/Ikigai.jpg"; // ¡Asegúrate de que esta ruta sea correcta!
    private static final int LOGO_CENTRAL_SIZE = 100; // Tamaño del icono central

    public VentanaLogin(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Bienvenido a Ikigai");
        setSize(350, 650); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null); 
        
        // 🚨 CAMBIO APLICADO: Fondo de la ventana a blanco
        this.getContentPane().setBackground(new Color (255, 255, 255));

        initComponents();
    }
    
    /**
     * Carga el icono como recurso del proyecto y lo escala a un tamaño dado.
     */
    private Icon getIcon(String pathRuta, int size) {
        try {
            URL imgURL = getClass().getClassLoader().getResource(pathRuta);
            
            if (imgURL != null) {
                ImageIcon originalIcon = new ImageIcon(imgURL);
                Image image = originalIcon.getImage();
                Image scaledImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
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

    /**
     * Carga el icono como recurso del proyecto y lo escala a 30x30 píxeles por defecto.
     */
    private Icon getIcon(String pathRuta) {
        return getIcon(pathRuta, 30);
    }


    private void initComponents() {
        int width = 280; 
        int x = (350 - width) / 2; // Centrado
        int yStart = 150;
        int spacing = 55;
        
        // --- Iconos de Botones (Asegúrate de que estas rutas sean correctas) ---
        Icon iconLogin = getIcon("iconos/login.png");
        Icon iconCrear = getIcon("iconos/crear.png");


        // --- Título y Subtítulo ---
        JLabel lblTitulo = new JLabel("IKIGAI", SwingConstants.CENTER);
        lblTitulo.setBounds(x, 50, width, 40);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(0, 0, 255));
        add(lblTitulo);
        
        JLabel lblSubtitulo = new JLabel("¡Tu salud en un solo lugar!", SwingConstants.CENTER);
        lblSubtitulo.setBounds(x, 90, width, 20);
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        add(lblSubtitulo);

        //Icono debajo del subtítulo
        Icon centralLogo = getIcon(RUTA_LOGO_CENTRAL, LOGO_CENTRAL_SIZE);
        if (centralLogo != null) {
            JLabel lblLogo = new JLabel(centralLogo);
            lblLogo.setBounds((350 - LOGO_CENTRAL_SIZE) / 2, 120, LOGO_CENTRAL_SIZE, LOGO_CENTRAL_SIZE);
            add(lblLogo);
            yStart = 120 + LOGO_CENTRAL_SIZE + 10; // Ajustar el inicio de los campos de texto
        } else {
            yStart = 120; // Si no hay logo, empezar antes
        }

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(x, yStart, width, 20);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(x, yStart + 20, width, 35);
        add(txtNombre);

        // ID
        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(x, yStart + spacing * 1 + 20, width, 20);
        add(lblId);
        txtId = new JTextField();
        txtId.setBounds(x, yStart + spacing * 1 + 40, width, 35);
        add(txtId);

        // EPS
        JLabel lblEps = new JLabel("EPS:");
        lblEps.setBounds(x, yStart + spacing * 2 + 40, width, 20);
        add(lblEps);
        txtEps = new JTextField();
        txtEps.setBounds(x, yStart + spacing * 2 + 60, width, 35);
        add(txtEps);

        //Botones 
        
        // Iniciar Sesión 
        btnIniciarSesion = new JButton("Iniciar Sesión", iconLogin);
        btnIniciarSesion.setBounds(x, yStart + spacing * 4, width, 45);
        btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 16));
        btnIniciarSesion.setBackground(new Color(0, 153, 204));
        btnIniciarSesion.setForeground(Color.WHITE);
        add(btnIniciarSesion);

        // Crear Perfil
        btnCrearPerfil = new JButton("Crear Perfil", iconCrear);
        btnCrearPerfil.setBounds(x, yStart + spacing * 5 + 15, width, 40);
        btnCrearPerfil.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Fondo Blanco y Texto Negro
        btnCrearPerfil.setBackground(new Color (220,220, 220));
        btnCrearPerfil.setForeground(new Color (255, 255, 255));
        add(btnCrearPerfil);

        // --- Lógica de Eventos ---
        btnIniciarSesion.addActionListener(e -> iniciarSesion());
        btnCrearPerfil.addActionListener(e -> crearPerfil());
    }

    private void iniciarSesion() {
        String id = txtId.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ID para iniciar sesión.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Paciente paciente = sistema.buscarPacientePorId(id);

        if (paciente != null) {
            abrirMenu(paciente);
        } else {
            JOptionPane.showMessageDialog(this, "Paciente no encontrado. Verifique el ID o cree un perfil.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearPerfil() {
        String nombre = txtNombre.getText();
        String id = txtId.getText();
        String epsNombre = txtEps.getText();

        if (nombre.isEmpty() || id.isEmpty() || epsNombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios para crear un perfil.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (sistema.buscarPacientePorId(id) != null) {
            JOptionPane.showMessageDialog(this, "Ya existe un paciente con ese ID. Intente iniciar sesión.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crea los nuevos objetos
        EPS nuevaEps = new EPS(epsNombre);
        Paciente nuevoPaciente = new Paciente(nombre, id, nuevaEps);

        // Registra al paciente en el sistema
        sistema.registrarPaciente(nuevoPaciente);

        JOptionPane.showMessageDialog(this, "Perfil creado exitosamente. Bienvenido, " + nombre + ".", "Perfil Creado", JOptionPane.INFORMATION_MESSAGE);

        // Abre el menú
        abrirMenu(nuevoPaciente);
    }

    private void abrirMenu(Paciente paciente) {
        VentanaMenu menu = new VentanaMenu(paciente, sistema);
        menu.setVisible(true);
        this.dispose();
    }
}