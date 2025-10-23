package modelo;

public class MedicamentoRecetado extends Medicamento {
    private boolean requiereReceta = true;

    public MedicamentoRecetado(String nombre, String dosis) {
        super(nombre, dosis);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Medicamento recetado: " + getNombre() + 
                           " | Dosis: " + getDosis() + 
                           " | Requiere receta: " + requiereReceta);
    }
}