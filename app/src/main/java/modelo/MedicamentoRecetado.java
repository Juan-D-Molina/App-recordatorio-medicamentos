package modelo;

// Herencia: extiende de Medicamento
public class MedicamentoRecetado extends Medicamento {
    private boolean requiereReceta = true;

    public MedicamentoRecetado(String nombre, String dosis, String frecuencia) {
        super(nombre, dosis, frecuencia);
    }

    // Polimorfismo: redefine el comportamiento de mostrarInfo()
    @Override
    public void mostrarInfo() {
        System.out.println("Medicamento recetado: " + getNombre() +
                " | Dosis: " + getDosis() +
                " | Frecuencia: " + getFrecuencia() +
                " | Requiere receta: " + requiereReceta);
    }
}
