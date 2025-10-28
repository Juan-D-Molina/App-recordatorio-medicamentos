package modelo;

import servicios.Notificable;

/**
 * Representa un recordatorio de medicamento.
 * Composición: contiene un objeto Medicamento.
 * Asociación: puede recibir notificaciones mediante Notificable.
 */
public class Recordatorio implements Notificable {
    private String hora;
    private Medicamento medicamento; // Composición: Recordatorio "tiene un" Medicamento

    public Recordatorio(String hora, Medicamento medicamento) {
        this.hora = hora;
        this.medicamento = medicamento;
    }

    public void mostrarRecordatorio() {
        System.out.println("Recordatorio a las " + hora + ":");
        medicamento.mostrarInfo(); // Polimorfismo: si es MedicamentoRecetado, se muestra info extendida
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("📅 [" + hora + "] Recordatorio: " + mensaje);
    }
}
