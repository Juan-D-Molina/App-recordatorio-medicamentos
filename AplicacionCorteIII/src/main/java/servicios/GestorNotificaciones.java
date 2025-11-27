package servicios;

import java.util.ArrayList;
import java.util.List;

// Servicio para notificar a todos los objetos Notificable registrados (Observer Pattern)
public class GestorNotificaciones {
    private List<Notificable> suscriptores;

    public GestorNotificaciones() { suscriptores = new ArrayList<>(); }

    // Registra un objeto que puede ser notificado
    public void registrar(Notificable n) {
        if (n != null && !suscriptores.contains(n)) suscriptores.add(n);
    }

    // Envia el mismo mensaje a todos los suscriptores
    public void enviarNotificacion(String mensaje) {
        for (Notificable n : suscriptores) n.enviarNotificacion(mensaje);
    }
}