package app;

import modelo.*;
import servicios.*;

import java.util.ArrayList;

public class Sistema {
    private ArrayList<Paciente> pacientes;
    private ArrayList<Doctor> doctores;
    private GestorMedicamentos gestorMedicamentos;
    private GestorNotificaciones gestorNotificaciones;
    private Estadisticas estadisticas;

    public Sistema() {
        pacientes = new ArrayList<>(); // Lista de pacientes inicia vacía
        doctores = new ArrayList<>();
        gestorMedicamentos = new GestorMedicamentos();
        gestorNotificaciones = new GestorNotificaciones();
        estadisticas = Estadisticas.getInstance();

        // 1. Inicialización de Pacientes (SE ELIMINA)
        // Paciente p1 = new Paciente("Noah Rodriguez", "001");
        // ...
        // estadisticas.setPacientes(p1, p2); 
        // gestorNotificaciones.registrar(p1);
        
        // 2. Inicialización de Doctores (sin cambios)
        Doctor d1 = new Doctor("Edward Jenner", "D01", 30000);
        Doctor d2 = new Doctor("Alexander Fleming", "D02", 30000);
        Doctor d3 = new Doctor("Marie Curie", "D03", 30000);
        Pediatra pd1 = new Pediatra("Louis Pasteur", "P01", 35000);
        Pediatra pd2 = new Pediatra("Joseph Lister", "P02", 35000);
        doctores.add(d1);
        doctores.add(d2);
        doctores.add(d3);
        doctores.add(pd1);
        doctores.add(pd2);

        // 3. Inicialización de Medicamentos (sin cambios)
        MedicamentoRecetado m1 = new MedicamentoRecetado("Ibuprofeno", "200mg", "08:00", 1200);
        MedicamentoRecetado m2 = new MedicamentoRecetado("Paracetamol", "500mg", "12:00", 1500);
        MedicamentoRecetado m3 = new MedicamentoRecetado("Jarabe Infantil", "5ml", "09:00", 2000);
        MedicamentoRecetado m4 = new MedicamentoRecetado("Vitaminas Infantiles", "1 tableta", "10:00", 1800);
        gestorMedicamentos.agregarMedicamento(m1);
        gestorMedicamentos.agregarMedicamento(m2);
        gestorMedicamentos.agregarMedicamento(m3);
        gestorMedicamentos.agregarMedicamento(m4);

        // 4. Asignar medicamentos (sin cambios)
        d1.agregarMedicamento(gestorMedicamentos.getMedicamentos().get(0));
        d2.agregarMedicamento(gestorMedicamentos.getMedicamentos().get(1));
        pd1.agregarMedicamento(gestorMedicamentos.getMedicamentos().get(2));
        pd2.agregarMedicamento(gestorMedicamentos.getMedicamentos().get(3));
    }
    
    // --- NUEVOS MÉTODOS ---
    
    /**
     * Registra un nuevo paciente en el sistema (en la lista, en notificaciones y en estadísticas).
     */
    public void registrarPaciente(Paciente p) {
        if (p != null && buscarPacientePorId(p.getId()) == null) {
            pacientes.add(p);
            gestorNotificaciones.registrar(p);
            estadisticas.registrarPaciente(p); // Registra en el singleton
        }
    }

    /**
     * Busca un paciente por su ID. Retorna null si no se encuentra.
     */
    public Paciente buscarPacientePorId(String id) {
        for (Paciente p : pacientes) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
    
    // --- Getters existentes ---
    
    public ArrayList<Doctor> getDoctores() {
        return doctores;
    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }
}