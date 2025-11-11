package modelo;

import java.util.ArrayList;

public class Doctor extends Persona {
    private ArrayList<Medicamento> recetas;
    private double valorConsulta;

    public Doctor(String nombre, String id, double valorConsulta) {
        super(nombre, id);
        this.valorConsulta = valorConsulta;
        recetas = new ArrayList<>();
    }

    public void agregarMedicamento(Medicamento m) {
        recetas.add(m);
    }

    public void mostrarRecetas() {
        System.out.println("Recetas del Dr. " + getNombre() + ":");
        for (Medicamento m : recetas) {
            m.mostrarInfo();
        }
    }

    public ArrayList<Medicamento> getRecetas() { return recetas; }

    public double getValorConsulta() { return valorConsulta; }
}
