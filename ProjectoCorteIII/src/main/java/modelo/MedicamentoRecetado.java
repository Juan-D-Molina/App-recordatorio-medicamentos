package modelo;

public class MedicamentoRecetado extends Medicamento {
    private boolean requiereReceta = true;

    public MedicamentoRecetado(String nombre, String dosis, String frecuencia, double valor) {
        super(nombre, dosis, frecuencia, valor);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Medicamento recetado: " + getNombre() +
                " | Dosis: " + getDosis() +
                " | Frecuencia: " + getFrecuencia() +
                " | Requiere receta: " + requiereReceta +
                " | Valor: " + getValor() + " COP");
    }
}
