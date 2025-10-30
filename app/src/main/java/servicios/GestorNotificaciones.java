package servicios;

import java.util.ArrayList;
import java.util.List;

// Asociación: mantiene una lista de objetos Notificables (no los posee)
public class GestorNotificaciones {
    private List<Notificable> suscriptores;

    public GestorNotificaciones() {
        suscriptores = new ArrayList<>();
    }

    public void registrar(Notificable n) {
        if (n != null && !suscriptores.contains(n)) {
            suscriptores.add(n);
        }
    }

    public void enviarNotificacion(String mensaje) {
        for (Notificable n : suscriptores) {
            n.enviarNotificacion(mensaje); // Polimorfismo
        }
    }
}
