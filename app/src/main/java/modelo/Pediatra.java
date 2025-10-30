package modelo;

// Herencia: Pediatra hereda de Doctor
public class Pediatra extends Doctor {

    public Pediatra(String nombre, String id) {
        super(nombre, id);
    }

    // Polimorfismo: comportamiento distinto al mostrar recetas
    @Override
    public void mostrarRecetas() {
        System.out.println("Recetas del Pediatra " + getNombre() + ":");
        for (Medicamento m : getRecetas()) {
            System.out.print("(Dosis infantil) ");
            m.mostrarInfo();
        }
    }
}
