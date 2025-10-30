package modelo;

import java.util.ArrayList;

// Herencia: Doctor extiende de Persona
// Agregación: contiene lista de Medicamentos (no son destruidos al eliminar el doctor)
public class Doctor extends Persona {
    private ArrayList<Medicamento> recetas;

    public Doctor(String nombre, String id) {
        super(nombre, id);
        recetas = new ArrayList<>();
    }

    public void agregarMedicamento(Medicamento m) {
        recetas.add(m); // Agregación
    }

    public void mostrarRecetas() {
        System.out.println("Recetas del Dr. " + getNombre() + ":");
        for (Medicamento m : recetas) {
            m.mostrarInfo(); // Polimorfismo
        }
    }

    public ArrayList<Medicamento> getRecetas() {
        return recetas;
    }
}
