package modelo;

// Clase base para MedicamentoRecetado (Herencia)
public class Medicamento {
    private String nombre;
    private String dosis;
    private String frecuencia;

    public Medicamento(String nombre, String dosis, String frecuencia) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
    }

    public String getNombre() { return nombre; }
    public String getDosis() { return dosis; }
    public String getFrecuencia() { return frecuencia; }

    // Polimorfismo: puede ser sobrescrito
    public void mostrarInfo() {
        System.out.println("Medicamento: " + nombre + " | Dosis: " + dosis + " | Frecuencia: " + frecuencia);
    }
}
