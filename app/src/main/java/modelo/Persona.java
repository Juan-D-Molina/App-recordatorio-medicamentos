package modelo;

// Clase base: será heredada por Doctor y Paciente (Herencia)
public class Persona {
    private String nombre;
    private String id;

    public Persona(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() { return nombre; }
    public String getId() { return id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setId(String id) { this.id = id; }

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre + " | ID: " + id);
    }
}
