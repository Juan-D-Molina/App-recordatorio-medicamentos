package modelo;

public class Medicamento {
    private String nombre;
    private String dosis;
    private String frecuencia;
    private double valor;

    public Medicamento(String nombre, String dosis, String frecuencia, double valor) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.valor = valor;
    }

    public String getNombre() { return nombre; }
    public String getDosis() { return dosis; }
    public String getFrecuencia() { return frecuencia; }
    public double getValor() { return valor; }

    public void mostrarInfo() {
        System.out.println("Medicamento: " + nombre + " | Dosis: " + dosis +
                " | Frecuencia: " + frecuencia + " | Valor: " + valor + " COP");
    }
}
