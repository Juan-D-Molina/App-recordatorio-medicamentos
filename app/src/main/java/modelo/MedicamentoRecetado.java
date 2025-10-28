package modelo;

/**
 * Medicamento que requiere receta.
 * Herencia: extiende de Medicamento.
 * Polimorfismo: sobrescribe mostrarInfo().
 */
public class MedicamentoRecetado extends Medicamento {
    private boolean requiereReceta = true;

    public MedicamentoRecetado(String nombre, String dosis, String frecuencia) {
        super(nombre, dosis, frecuencia);
    }

    @Override
    public void mostrarInfo() {
        // Polimorfismo: se sobrescribe mostrarInfo() para añadir información de receta
        System.out.println("Medicamento recetado: " + getNombre() + 
                           " | Dosis: " + getDosis() +
                           " | Frecuencia: " + getFrecuencia() +
                           " | Requiere receta: " + requiereReceta);
    }
}
