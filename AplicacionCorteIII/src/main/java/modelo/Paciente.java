package modelo;

import servicios.Notificable;
import java.util.ArrayList;

public class Paciente extends Persona implements Notificable {
    private ArrayList<Recordatorio> recordatorios;
    private ArrayList<Doctor> doctoresConsultados;
    private int totalAlarmas; // Mantenido para un posible uso interno
    private EPS eps; // Campo añadido

    // Constructor modificado
    public Paciente(String nombre, String id, EPS eps) {
        super(nombre, id);
        this.eps = eps; // EPS añadida
        recordatorios = new ArrayList<>();
        doctoresConsultados = new ArrayList<>();
        totalAlarmas = 0;
    }

    public EPS getEps() {
        return eps;
    }

    public void agregarRecordatorio(Recordatorio r) {
        recordatorios.add(r);
    }

    public ArrayList<Recordatorio> getRecordatorios() { return recordatorios; }

    public void agregarDoctor(Doctor d) { 
        if (!doctoresConsultados.contains(d)) {
            doctoresConsultados.add(d);
        }
    }

    public ArrayList<Doctor> getDoctoresConsultados() { return doctoresConsultados; }

    public void aumentarAlarmas(int n) { 
        totalAlarmas += n; 
    }

    public int getTotalAlarmas() { return totalAlarmas; }

    /**
     * Obtiene el doctor más consultado llamando al Singleton de Estadísticas.
     */
    public Doctor getDoctorMasConsultado() {
        return Estadisticas.getInstance().getDoctorMasConsultado(this);
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Notificación para " + getNombre() + ": " + mensaje);
        aumentarAlarmas(1);
    }
}