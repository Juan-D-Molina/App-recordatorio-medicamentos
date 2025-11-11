package app;

import gui.VentanaPaciente;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        VentanaPaciente ventana = new VentanaPaciente(
                sistema.estadisticas.getPaciente1(),
                sistema.estadisticas.getPaciente2()
        );
        ventana.setVisible(true);
    }
}
