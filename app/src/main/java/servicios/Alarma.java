package servicios;

// Clase independiente. Usada por AppMedicamentos (Asociación)
public class Alarma implements Runnable {
    private int segundos;

    public Alarma(int segundos) {
        this.segundos = segundos;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(segundos * 1000);
            java.awt.Toolkit.getDefaultToolkit().beep();
            System.out.println("\n*** ALARMA: Es hora de revisar su medicamento ***");
        } catch (InterruptedException e) {
            System.out.println("La alarma fue interrumpida.");
        }
    }

    public void activar() {
        Thread hilo = new Thread(this);
        hilo.start();
    }
}
