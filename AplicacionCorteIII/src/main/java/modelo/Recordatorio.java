package modelo;

import servicios.Notificable;

public class Recordatorio implements Notificable {
    private String hora;
    private Medicamento medicamento;
    private Paciente paciente;
    
    // 🚨 CAMBIO APLICADO 1: Nuevo campo para rastrear el estado de programación/cobro
    private boolean activo; 

    public Recordatorio(String hora, Medicamento medicamento, Paciente paciente) {
        this.hora = hora;
        this.medicamento = medicamento;
        this.paciente = paciente;
        
        // Inicialmente, el recordatorio está PENDIENTE de ser programado/cobrado
        this.activo = false; 
    }

    public String getHora() { return hora; }
    public Medicamento getMedicamento() { return medicamento; }

    // 🚨 CAMBIO APLICADO 2: Getter para el nuevo estado
    public boolean isActivo() {
        return activo;
    }

    // 🚨 CAMBIO APLICADO 3: Setter para cambiar el estado a ACTIVO
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void mostrarRecordatorio() {
        // Agregamos el estado a la visualización de consola
        String estado = activo ? "(ACTIVO)" : "(PENDIENTE)";
        System.out.println("Recordatorio " + estado + " a las " + hora + ":");
        medicamento.mostrarInfo();
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        // Solo envía la notificación si el recordatorio está activo
        if (this.activo) {
             // Envia la notificación primero al sistema de consola
            System.out.println("[" + hora + "] Recordatorio ACTIVO: " + mensaje);
            
            // Luego notifica al paciente (que implementa Notificable)
            if (paciente != null) paciente.enviarNotificacion(mensaje);
        }
    }
}