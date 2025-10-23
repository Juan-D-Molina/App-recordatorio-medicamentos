package servicios;

import java.util.ArrayList;
import java.util.List;

public class GestorNotificaciones {
    private List<Notificable> suscriptores;

    public GestorNotificaciones() {
        suscriptores = new ArrayList<>();
    }

    // Agregar objetos que recibirán notificaciones
    public void registrar(Notificable n) {
        if (n != null && !suscriptores.contains(n)) {
            suscriptores.add(n);
        }
    }

    // Eliminar objetos si ya no deben recibir mensajes
    public void eliminar(Notificable n) {
        suscriptores.remove(n);
    }

    // Enviar notificación a todos los suscriptores
    public void enviarNotificacion(String mensaje) {
        for (Notificable n : suscriptores) {
            n.enviarNotificacion(mensaje);
        }
    }
}
