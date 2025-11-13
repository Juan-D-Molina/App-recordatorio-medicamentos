package servicios;

import modelo.Medicamento;
import java.util.ArrayList;

// Servicio centralizado para manejar el catálogo de medicamentos
public class GestorMedicamentos {
    private ArrayList<Medicamento> medicamentos;

    public GestorMedicamentos() {
        medicamentos = new ArrayList<>();
    }

    public void agregarMedicamento(Medicamento m) { medicamentos.add(m); }

    public ArrayList<Medicamento> getMedicamentos() { return medicamentos; }
}