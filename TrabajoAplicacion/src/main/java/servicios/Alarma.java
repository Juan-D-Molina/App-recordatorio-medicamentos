package servicios;

import modelo.Paciente;

public class Alarma implements Runnable {
    private int segundos;
    private Paciente paciente;

    public Alarma(int segundos, Paciente paciente) {
        this.segundos = segundos;
        this.paciente = paciente;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(segundos * 1000); // Espera el tiempo configurado
            
            // Simulación de sonido de alarma (requiere soporte AWT)
            java.awt.Toolkit.getDefaultToolkit().beep(); 
            
            System.out.println("\n*** ALARMA: Es hora de revisar su medicamento ***");
            
            // Registra el evento de alarma en el paciente
            if (paciente != null) paciente.aumentarAlarmas(1); 
        } catch (InterruptedException e) {
            System.out.println("La alarma fue interrumpida.");
        }
    }

    public void activar() {
        new Thread(this).start(); // Inicia la alarma en un nuevo hilo
    }
}