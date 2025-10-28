package modelo;

import java.util.ArrayList;

/**
 * Representa a un doctor.
 * Herencia: extiende de Persona.
 */
public class Doctor extends Persona {
    // Agregación: un doctor puede tener múltiples medicamentos como recetas
    private ArrayList<Medicamento> recetas;

    public Doctor(String nombre, String id) {
        super(nombre, id);
        recetas = new ArrayList<>();
    }

    // Agregación: agregar un medicamento a la lista de recetas
    public void agregarMedicamento(Medicamento m) {
        recetas.add(m);
    }

    public void mostrarRecetas() {
        System.out.println("Recetas del Dr. " + getNombre() + ":");
        for (Medicamento m : recetas) {
            m.mostrarInfo(); // Polimorfismo: si es MedicamentoRecetado, mostrará info extendida
        }
    }

    public ArrayList<Medicamento> getRecetas() {
        return recetas;
    }
}
