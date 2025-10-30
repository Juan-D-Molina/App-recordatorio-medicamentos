package modelo;

import servicios.Notificable;

// Implementa interfaz Notificable (Interfaz)
// Composición: contiene un Medicamento (parte de su ciclo de vida)
public class Recordatorio implements Notificable {
    private String hora;
    private Medicamento medicamento;

    public Recordatorio(String hora, Medicamento medicamento) {
        this.hora = hora;
        this.medicamento = medicamento;
    }

    public void mostrarRecordatorio() {
        System.out.println("Recordatorio a las " + hora + ":");
        medicamento.mostrarInfo(); // Polimorfismo
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("[" + hora + "] Recordatorio: " + mensaje);
    }
}
