package app;

import gui.VentanaPaciente;

public class Main {
    public static void main(String[] args) {
        // Inicializa el sistema, que crea pacientes, doctores y medicamentos
        Sistema sistema = new Sistema();
        
        // La ventana inicial permite seleccionar al paciente.
        // Utiliza el Singleton de Estadisticas para obtener los pacientes iniciales.
        VentanaPaciente ventana = new VentanaPaciente(
                sistema.getEstadisticas().getPaciente1(),
                sistema.getEstadisticas().getPaciente2(),
                sistema
        );
        ventana.setVisible(true);
    }
}