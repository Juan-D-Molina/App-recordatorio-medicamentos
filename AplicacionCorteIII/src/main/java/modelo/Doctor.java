package modelo;

import java.util.ArrayList;

public class Doctor extends Persona {
    private ArrayList<Medicamento> recetas;
    private double valorConsulta;

    // --- Constructor Principal ---
    public Doctor(String nombre, String id, double valorConsulta) {
        super(nombre, id); 
        this.valorConsulta = valorConsulta;
        this.recetas = new ArrayList<>();
    }
    
    // --- Constructor para uso en Estadisticas (si Doctor solo necesita nombre/id) ---
    public Doctor(String nombre, String id) {
        super(nombre, id);
        this.valorConsulta = 0.0; // Valor por defecto
        this.recetas = new ArrayList<>();
    }

    // --- Métodos de Acceso (Getters) ---
    
    public ArrayList<Medicamento> getRecetas() { 
        return recetas; 
    }

    public double getValorConsulta() { 
        return valorConsulta; 
    }
    
    // --- Métodos Mutadores (Setters) ---
    
    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    // --- Lógica de Recetas ---
    
    public void agregarMedicamento(Medicamento m) {
        recetas.add(m);
    }
    
    /**
     * Muestra las recetas por consola (método original).
     */
    public void mostrarRecetasConsola() {
        System.out.println("Recetas del Dr. " + getNombre() + ":");
        if (recetas.isEmpty()) {
            System.out.println("No hay recetas.");
        } else {
            for (Medicamento m : recetas) {
                // Se asegura que se llama la función que imprime la info
                m.mostrarInfo(); 
            }
        }
    }

    /**
     * Retorna un String con la información de las recetas (útil para GUI).
     */
    public String obtenerRecetasString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Recetas del Dr. ").append(getNombre()).append(" ---\n");
        
        if (recetas.isEmpty()) {
            sb.append("No hay recetas programadas.");
        } else {
            for (Medicamento m : recetas) {
                sb.append("• ").append(m.getNombre())
                  .append(" (Dosis: ").append(m.getDosis())
                  .append(")\n");
            }
        }
        return sb.toString();
    }
}