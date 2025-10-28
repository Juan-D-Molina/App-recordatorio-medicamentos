package modelo;

/**
 * Clase que representa un medicamento.
 * Composición: Recordatorio contiene un Medicamento.
 */
public class Medicamento {
    private String nombre;
    private String dosis;
    private String frecuencia; // Hora o frecuencia del medicamento

    public Medicamento(String nombre, String dosis, String frecuencia) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public String getDosis() { return dosis; }
    public String getFrecuencia() { return frecuencia; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDosis(String dosis) { this.dosis = dosis; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    // Polimorfismo: este método puede ser sobrescrito en MedicamentoRecetado
    public void mostrarInfo() {
        System.out.println("Medicamento: " + nombre + " | Dosis: " + dosis + " | Frecuencia: " + frecuencia);
    }
}
