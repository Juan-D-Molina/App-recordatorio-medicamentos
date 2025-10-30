package modelo;

import servicios.Notificable;
import java.util.ArrayList;

// Herencia: Paciente extiende de Persona
// Implementa interfaz Notificable (Interfaz)
// Composición: contiene Recordatorios (dependen del paciente)
public class Paciente extends Persona implements Notificable {
    private ArrayList<Recordatorio> recordatorios;

    public Paciente(String nombre, String id) {
        super(nombre, id);
        recordatorios = new ArrayList<>();
    }

    public void agregarRecordatorio(Recordatorio r) {
        recordatorios.add(r); // Composición
    }

    public void mostrarRecordatorios() {
        if (recordatorios.isEmpty()) {
            System.out.println("No hay recordatorios para " + getNombre());
        } else {
            for (Recordatorio r : recordatorios) {
                r.mostrarRecordatorio();
            }
        }
    }

    @Override
    public void enviarNotificacion(String mensaje) { // Interfaz
        System.out.println("Notificacion para " + getNombre() + ": " + mensaje);
    }
}
