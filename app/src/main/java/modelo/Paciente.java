package modelo;

import servicios.Notificable;
import java.util.ArrayList;

/**
 * Representa a un paciente.
 * Herencia: extiende de Persona.
 * Composición: un Paciente tiene múltiples Recordatorios.
 * Asociación: Paciente puede recibir notificaciones mediante Notificable.
 */
public class Paciente extends Persona implements Notificable {
    private ArrayList<Recordatorio> recordatorios; // Composición

    public Paciente(String nombre, String id) {
        super(nombre, id);
        recordatorios = new ArrayList<>();
    }

    // Composición: agregar recordatorio al paciente
    public void agregarRecordatorio(Recordatorio r) {
        recordatorios.add(r);
    }

    public void mostrarRecordatorios() {
        if (recordatorios.isEmpty()) {
            System.out.println("No hay recordatorios para " + getNombre());
        } else {
            for (Recordatorio r : recordatorios) {
                r.mostrarRecordatorio(); // Polimorfismo: Recordatorio puede mostrar info según su Medicamento
            }
        }
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        // Asociación: recibe mensajes del GestorNotificaciones
        System.out.println("🔔 Notificación para " + getNombre() + ": " + mensaje);
    }
}
