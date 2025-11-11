package modelo;

public class Pediatra extends Doctor {
    public Pediatra(String nombre, String id, double valorConsulta) {
        super(nombre, id, valorConsulta);
    }

    @Override
    public void mostrarRecetas() {
        System.out.println("Recetas del Pediatra " + getNombre() + ":");
        for (Medicamento m : getRecetas()) {
            System.out.print("(Dosis infantil) ");
            m.mostrarInfo();
        }
    }
}
