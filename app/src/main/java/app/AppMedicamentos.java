package app;

import java.util.Scanner;
import modelo.*;
import servicios.*;

public class AppMedicamentos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // -------------------------------
        // INSTANCIAS DE GESTORES
        // -------------------------------
        GestorMedicamentos gestorMedicamentos = new GestorMedicamentos(); // Agregación: lista de medicamentos
        GestorNotificaciones gestorNotificaciones = new GestorNotificaciones(); // Asociación: lista de suscriptores

        // -------------------------------
        // INSTANCIAS DE PACIENTES
        // -------------------------------
        Paciente paciente1 = new Paciente("Noah Rodriguez", "001");
        Paciente paciente2 = new Paciente("Maria Hernandez", "002");

        // Registramos pacientes como suscriptores de notificaciones
        gestorNotificaciones.registrar(paciente1);
        gestorNotificaciones.registrar(paciente2);

        // -------------------------------
        // INSTANCIAS DE DOCTORES
        // -------------------------------
        Doctor doctor1 = new Doctor("Edward Jenner", "D01");
        Doctor doctor2 = new Doctor("Alexander Fleming", "D02");
        Doctor doctor3 = new Doctor("Marie Curie", "D03");
        Doctor doctor4 = new Doctor("Louis Pasteur", "D04");
        Doctor doctor5 = new Doctor("Joseph Lister", "D05");

        // -------------------------------
        // MEDICAMENTOS PREDEFINIDOS PARA CADA DOCTOR
        // -------------------------------
        // Jóvenes-Adultos
        MedicamentoRecetado med1 = new MedicamentoRecetado("Ibuprofeno", "200mg", "08:00");
        MedicamentoRecetado med2 = new MedicamentoRecetado("Paracetamol", "500mg", "12:00");
        // Adultos
        MedicamentoRecetado med3 = new MedicamentoRecetado("Metformina", "850mg", "08:00");
        MedicamentoRecetado med4 = new MedicamentoRecetado("Lisinopril", "10mg", "20:00");
        // Adultos-Mayores
        MedicamentoRecetado med5 = new MedicamentoRecetado("Aspirina", "100mg", "08:00");
        MedicamentoRecetado med6 = new MedicamentoRecetado("Simvastatina", "20mg", "22:00");
        MedicamentoRecetado med7 = new MedicamentoRecetado("Omeprazol", "20mg", "07:00");

        // -------------------------------
        // ASIGNACIÓN DE MEDICAMENTOS A DOCTORES
        // Agregación: Doctor "tiene" varios medicamentos
        // -------------------------------
        doctor1.agregarMedicamento(med1);
        doctor1.agregarMedicamento(med3);

        doctor2.agregarMedicamento(med2);
        doctor2.agregarMedicamento(med4);

        doctor3.agregarMedicamento(med5);
        doctor3.agregarMedicamento(med6);

        doctor4.agregarMedicamento(med1);
        doctor4.agregarMedicamento(med7);

        doctor5.agregarMedicamento(med3);
        doctor5.agregarMedicamento(med5);

        // -------------------------------
        // MENÚ DE SELECCIÓN DE DOCTOR
        // -------------------------------
        Doctor doctorSeleccionado = null;
        while (doctorSeleccionado == null) {
            System.out.println("\nSeleccione su doctor de cabecera:");
            System.out.println("1. Edward Jenner");
            System.out.println("2. Alexander Fleming");
            System.out.println("3. Marie Curie");
            System.out.println("4. Louis Pasteur");
            System.out.println("5. Joseph Lister");
            System.out.print("Ingrese el número del doctor: ");
            String input = sc.nextLine().trim();
            switch (input) {
                case "1" -> doctorSeleccionado = doctor1;
                case "2" -> doctorSeleccionado = doctor2;
                case "3" -> doctorSeleccionado = doctor3;
                case "4" -> doctorSeleccionado = doctor4;
                case "5" -> doctorSeleccionado = doctor5;
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

        System.out.println("\nDoctor seleccionado: " + doctorSeleccionado.getNombre());

        // -------------------------------
        // ASIGNAR RECORDATORIOS AUTOMÁTICOS A PACIENTES
        // -------------------------------
        for (Medicamento med : doctorSeleccionado.getRecetas()) {
            // Creamos recordatorios para paciente1
            Recordatorio r1 = new Recordatorio(med.getFrecuencia(), med); // Composición: Recordatorio "tiene un" Medicamento
            paciente1.agregarRecordatorio(r1); // Composición: Paciente "tiene muchos" Recordatorios
            gestorNotificaciones.registrar(r1); // Asociación: Recordatorio recibe notificaciones

            // Creamos recordatorios para paciente2
            Recordatorio r2 = new Recordatorio(med.getFrecuencia(), med);
            paciente2.agregarRecordatorio(r2);
            gestorNotificaciones.registrar(r2);

            // Agregamos medicamentos al gestor para visualización
            gestorMedicamentos.agregarMedicamento(med);
        }

        // -------------------------------
        // MENÚ PRINCIPAL
        // -------------------------------
        int opcion;
        do {
            System.out.println("\n--- MENÚ PACIENTE ---");
            System.out.println("1. Mostrar medicamentos del doctor");
            System.out.println("2. Mostrar recordatorios de Noah Rodriguez");
            System.out.println("3. Mostrar recordatorios de Maria Hernandez");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(sc.nextLine().trim());

            switch (opcion) {
                case 1 -> {
                    System.out.println("\n--- MEDICAMENTOS DEL DOCTOR ---");
                    doctorSeleccionado.mostrarRecetas(); // Polimorfismo: Medicamento o MedicamentoRecetado
                }
                case 2 -> {
                    System.out.println("\n--- RECORDATORIOS DE NOAH RODRIGUEZ ---");
                    paciente1.mostrarRecordatorios();
                }
                case 3 -> {
                    System.out.println("\n--- RECORDATORIOS DE MARIA HERNANDEZ ---");
                    paciente2.mostrarRecordatorios();
                }
                case 4 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 4);

        sc.close();
    }
}
