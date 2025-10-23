package servicios;

import java.util.ArrayList;
import modelo.Medicamento;

public class GestorMedicamentos {
    private ArrayList<Medicamento> medicamentos;

    public GestorMedicamentos() {
        medicamentos = new ArrayList<>();
    }

    public void agregarMedicamento(Medicamento m) {
        medicamentos.add(m);
    }

    public void mostrarMedicamentos() {
        if (medicamentos.isEmpty()) {
            System.out.println("No hay medicamentos registrados.");
        } else {
            for (Medicamento m : medicamentos) {
                m.mostrarInfo(); // Polimorfismo
            }
        }
    }

    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(ArrayList<Medicamento> meds) {
        this.medicamentos = meds;
    }
}