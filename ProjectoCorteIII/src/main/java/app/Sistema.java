package app;

import modelo.*;
import servicios.*;

import java.util.ArrayList;

public class Sistema {
    public ArrayList<Paciente> pacientes;
    public ArrayList<Doctor> doctores;
    public GestorMedicamentos gestorMedicamentos;
    public GestorNotificaciones gestorNotificaciones;
    public Estadisticas estadisticas;

    public Sistema() {
        pacientes = new ArrayList<>();
        doctores = new ArrayList<>();
        gestorMedicamentos = new GestorMedicamentos();
        gestorNotificaciones = new GestorNotificaciones();
        estadisticas = Estadisticas.getInstance();

        // Pacientes
        Paciente p1 = new Paciente("Noah Rodriguez", "001");
        Paciente p2 = new Paciente("Maria Hernandez", "002");
        pacientes.add(p1);
        pacientes.add(p2);
        estadisticas.setPacientes(p1, p2);
        gestorNotificaciones.registrar(p1);
        gestorNotificaciones.registrar(p2);

        // Doctores
        Doctor d1 = new Doctor("Edward Jenner", "D01", 50000);
        Doctor d2 = new Doctor("Alexander Fleming", "D02", 60000);
        Doctor d3 = new Doctor("Marie Curie", "D03", 70000);
        Pediatra pd1 = new Pediatra("Louis Pasteur", "P01", 45000);
        Pediatra pd2 = new Pediatra("Joseph Lister", "P02", 40000);
        doctores.add(d1);
        doctores.add(d2);
        doctores.add(d3);
        doctores.add(pd1);
        doctores.add(pd2);

        // Medicamentos
        MedicamentoRecetado m1 = new MedicamentoRecetado("Ibuprofeno", "200mg", "08:00", 1200);
        MedicamentoRecetado m2 = new MedicamentoRecetado("Paracetamol", "500mg", "12:00", 1500);
        MedicamentoRecetado m3 = new MedicamentoRecetado("Jarabe Infantil", "5ml", "09:00", 2000);
        MedicamentoRecetado m4 = new MedicamentoRecetado("Vitaminas Infantiles", "1 tableta", "10:00", 1800);

        d1.agregarMedicamento(m1);
        d2.agregarMedicamento(m2);
        pd1.agregarMedicamento(m3);
        pd2.agregarMedicamento(m4);
    }
}
