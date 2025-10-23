package modelo;

import servicios.Notificable;
import java.util.ArrayList;

public class Paciente implements Notificable {
    private String nombre;
    private String id;
    private ArrayList<Recordatorio> recordatorios;

    public Paciente(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.recordatorios = new ArrayList<>();
    }

    public void agregarRecordatorio(Recordatorio r) {
        recordatorios.add(r);
    }

    public void mostrarRecordatorios() {
        for (Recordatorio r : recordatorios) {
            r.mostrarRecordatorio();
        }
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("🔔 Notificación para " + nombre + ": " + mensaje);
    }
}
