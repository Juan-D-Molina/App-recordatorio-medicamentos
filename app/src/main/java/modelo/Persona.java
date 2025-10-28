package modelo;

/**
 * Clase base para representar personas en el sistema.
 * Herencia: Paciente y Doctor heredan de Persona.
 */
public class Persona {
    private String nombre;
    private String id;

    public Persona(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public String getId() { return id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setId(String id) { this.id = id; }

    // Método para mostrar información general de la persona
    // Polimorfismo potencial: puede ser sobrescrito en las clases hijas
    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre + " | ID: " + id);
    }
}
