package modelo;

import servicios.Notificable;
import java.util.ArrayList;

public class Paciente extends Persona implements Notificable {
    private ArrayList<Recordatorio> recordatorios;
    private ArrayList<Doctor> doctoresConsultados;
    private int totalAlarmas; // Mantenido para un posible uso interno

    public Paciente(String nombre, String id) {
        super(nombre, id);
        recordatorios = new ArrayList<>();
        doctoresConsultados = new ArrayList<>();
        totalAlarmas = 0;
    }

    public void agregarRecordatorio(Recordatorio r) {
        recordatorios.add(r);
    }

    public ArrayList<Recordatorio> getRecordatorios() { return recordatorios; }

    public void agregarDoctor(Doctor d) { 
        // Evita agregar el mismo doctor varias veces si ya fue consultado
        if (!doctoresConsultados.contains(d)) {
            doctoresConsultados.add(d);
        }
    }

    public ArrayList<Doctor> getDoctoresConsultados() { return doctoresConsultados; }

    public void aumentarAlarmas(int n) { 
        totalAlarmas += n; 
    }

    public int getTotalAlarmas() { return totalAlarmas; }

    @Override
    public void enviarNotificacion(String mensaje) {
        // Simulación de envío de notificación al paciente
        System.out.println("Notificación para " + getNombre() + ": " + mensaje);
        aumentarAlarmas(1); // Incrementa el contador interno
    }
}