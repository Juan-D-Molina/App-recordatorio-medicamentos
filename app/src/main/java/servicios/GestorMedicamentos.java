package servicios;

import java.util.ArrayList;
import modelo.Medicamento;

// Agregación: almacena medicamentos sin poseer su ciclo de vida
public class GestorMedicamentos {
    private ArrayList<Medicamento> medicamentos;

    public GestorMedicamentos() {
        medicamentos = new ArrayList<>();
    }

    public void agregarMedicamento(Medicamento m) {
        medicamentos.add(m); // Agregación
    }

    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }
}
