package modelo;

public class Medicamento {
    private String nombre;
    private String dosis;

    public Medicamento(String nombre, String dosis) {
        this.nombre = nombre;
        this.dosis = dosis;
    }

    public String getNombre() { return nombre; }
    public String getDosis() { return dosis; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDosis(String dosis) { this.dosis = dosis; }

    public void mostrarInfo() {
        System.out.println("Medicamento: " + nombre + " | Dosis: " + dosis);
    }
}