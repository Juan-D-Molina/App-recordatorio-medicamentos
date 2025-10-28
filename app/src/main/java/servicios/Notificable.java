package servicios;

/**
 * Interfaz para objetos que pueden recibir notificaciones.
 * Polimorfismo: cualquier clase que implemente Notificable puede recibir mensajes.
 */
public interface Notificable {
    void enviarNotificacion(String mensaje);
}
