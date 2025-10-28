package servicios;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestión de notificaciones.
 * Asociación: mantiene suscriptores que implementan Notificable.
 */
public class GestorNotificaciones {
    private List<Notificable> suscriptores; // Asociación: puede ser Paciente o Recordatorio

    public GestorNotificaciones() {
        suscriptores = new ArrayList<>();
    }

    public void registrar(Notificable n) {
        if (n != null && !suscriptores.contains(n)) {
            suscriptores.add(n);
        }
    }

    public void eliminar(Notificable n) {
        suscriptores.remove(n);
    }

    public void enviarNotificacion(String mensaje) {
        for (Notificable n : suscriptores) {
            n.enviarNotificacion(mensaje); // Polimorfismo: cada suscriptor implementa su versión de enviarNotificacion
        }
    }
}
