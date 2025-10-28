package servicios;

import java.util.ArrayList;
import modelo.Medicamento;

/**
 * Gestión de medicamentos.
 * Agregación: mantiene una lista de medicamentos.
 */
public class GestorMedicamentos {
    private ArrayList<Medicamento> medicamentos; // Agregación: lista de objetos

    public GestorMedicamentos() {
        medicamentos = new ArrayList<>();
    }

    public void agregarMedicamento(Medicamento m) {
        medicamentos.add(m); // Agregación: añadimos referencia a Medicamento
    }

    public void mostrarMedicamentos() {
        if (medicamentos.isEmpty()) {
            System.out.println("No hay medicamentos registrados.");
        } else {
            for (Medicamento m : medicamentos) {
                m.mostrarInfo(); // Polimorfismo: método según tipo real del objeto
            }
        }
    }

    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }
}
