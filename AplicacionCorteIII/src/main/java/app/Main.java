package app;

import gui.VentanaLogin; // Import cambiado

public class Main {
    public static void main(String[] args) {
        // Inicializa el sistema 
        Sistema sistema = new Sistema();
        
        // La ventana inicial ahora es el Login.
        
        VentanaLogin ventana = new VentanaLogin(sistema);
        ventana.setVisible(true);
    }
}