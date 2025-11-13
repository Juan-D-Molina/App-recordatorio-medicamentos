package app;

import modelo.*;
import servicios.*;

import java.util.ArrayList;

public class Sistema {
    // Se convierten a private para mejorar la encapsulación (Buena Práctica)
    private ArrayList<Paciente> pacientes;
    private ArrayList<Doctor> doctores;
    private GestorMedicamentos gestorMedicamentos;
    private GestorNotificaciones gestorNotificaciones;
    private Estadisticas estadisticas;

    public Sistema() {
        pacientes = new ArrayList<>();
        doctores = new ArrayList<>();
        gestorMedicamentos = new GestorMedicamentos();
        gestorNotificaciones = new GestorNotificaciones();
        // Obtiene la única instancia del Singleton de Estadisticas
        estadisticas = Estadisticas.getInstance();

        // 1. Inicialización de Pacientes
        Paciente p1 = new Paciente("Noah Rodriguez", "001");
        Paciente p2 = new Paciente("Maria Hernandez", "002");
        pacientes.add(p1);
        pacientes.add(p2);
        
        // Vincula los pacientes al Singleton de Estadisticas
        estadisticas.setPacientes(p1, p2); 
        
        // Registra a los pacientes en el Gestor de Notificaciones
        gestorNotificaciones.registrar(p1);
        gestorNotificaciones.registrar(p2);

        // 2. Inicialización de Doctores (y Pediatras, que extienden de Doctor)
        Doctor d1 = new Doctor("Edward Jenner", "D01", 30000);
        Doctor d2 = new Doctor("Alexander Fleming", "D02", 30000);
        Doctor d3 = new Doctor("Marie Curie", "D03", 30000);
        Pediatra pd1 = new Pediatra("Louis Pasteur", "P01", 35000); // Costo diferente
        Pediatra pd2 = new Pediatra("Joseph Lister", "P02", 35000); // Costo diferente
        doctores.add(d1);
        doctores.add(d2);
        doctores.add(d3);
        doctores.add(pd1);
        doctores.add(pd2);

        // 3. Inicialización de Medicamentos Centralizados
        MedicamentoRecetado m1 = new MedicamentoRecetado("Ibuprofeno", "200mg", "08:00", 1200);
        MedicamentoRecetado m2 = new MedicamentoRecetado("Paracetamol", "500mg", "12:00", 1500);
        MedicamentoRecetado m3 = new MedicamentoRecetado("Jarabe Infantil", "5ml", "09:00", 2000);
        MedicamentoRecetado m4 = new MedicamentoRecetado("Vitaminas Infantiles", "1 tableta", "10:00", 1800);

        gestorMedicamentos.agregarMedicamento(m1);
        gestorMedicamentos.agregarMedicamento(m2);
        gestorMedicamentos.agregarMedicamento(m3);
        gestorMedicamentos.agregarMedicamento(m4);

        // 4. Asignar un medicamento a cada doctor/pediatra para que puedan "recetar"
        d1.agregarMedicamento(gestorMedicamentos.getMedicamentos().get(0)); // Ibuprofeno
        d2.agregarMedicamento(gestorMedicamentos.getMedicamentos().get(1)); // Paracetamol
        pd1.agregarMedicamento(gestorMedicamentos.getMedicamentos().get(2)); // Jarabe Infantil
        pd2.agregarMedicamento(gestorMedicamentos.getMedicamentos().get(3)); // Vitaminas Infantiles
    }
    
    // Getters para acceder a los componentes esenciales del sistema
    public ArrayList<Doctor> getDoctores() {
        return doctores;
    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }
}