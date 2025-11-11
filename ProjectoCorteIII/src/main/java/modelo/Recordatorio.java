package modelo;

import servicios.Notificable;

public class Recordatorio implements Notificable {
    private String hora;
    private Medicamento medicamento;
    private Paciente paciente;

    public Recordatorio(String hora, Medicamento medicamento, Paciente paciente) {
        this.hora = hora;
        this.medicamento = medicamento;
        this.paciente = paciente;
    }

    public String getHora() { return hora; }
    public Medicamento getMedicamento() { return medicamento; }

    public void mostrarRecordatorio() {
        System.out.println("Recordatorio a las " + hora + ":");
        medicamento.mostrarInfo();
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("[" + hora + "] Recordatorio: " + mensaje);
        if (paciente != null) {
            paciente.aumentarAlarmas(1);
        }
    }
}
