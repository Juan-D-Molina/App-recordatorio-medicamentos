package app;

import java.util.Scanner;
import modelo.*;
import servicios.*;

/**
 * Clase principal del sistema AppMedicamentos.
 * Demuestra herencia, composición, agregación, asociación e interfaces.
 */
public class AppMedicamentos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Agregación: gestores de datos
        GestorMedicamentos gestorMedicamentos = new GestorMedicamentos();
        GestorNotificaciones gestorNotificaciones = new GestorNotificaciones();

        // Composición: pacientes con recordatorios propios
        Paciente paciente1 = new Paciente("Noah Rodriguez", "001");
        Paciente paciente2 = new Paciente("Maria Hernandez", "002");

        // Asociación: pacientes se registran para recibir notificaciones
        gestorNotificaciones.registrar(paciente1);
        gestorNotificaciones.registrar(paciente2);

        // Herencia: doctores generales y pediatras
        Doctor doctor1 = new Doctor("Edward Jenner", "D01");
        Doctor doctor2 = new Doctor("Alexander Fleming", "D02");
        Doctor doctor3 = new Doctor("Marie Curie", "D03");
        Pediatra pediatra1 = new Pediatra("Louis Pasteur", "P01");
        Pediatra pediatra2 = new Pediatra("Joseph Lister", "P02");

        // Agregación / Composición: medicamentos
        MedicamentoRecetado m1 = new MedicamentoRecetado("Ibuprofeno", "200mg", "08:00");
        MedicamentoRecetado m2 = new MedicamentoRecetado("Paracetamol", "500mg", "12:00");
        MedicamentoRecetado m3 = new MedicamentoRecetado("Jarabe Infantil", "5ml", "09:00");
        MedicamentoRecetado m4 = new MedicamentoRecetado("Vitaminas Infantiles", "1 tableta", "10:00");

        // Asignación de medicamentos
        doctor1.agregarMedicamento(m1);
        doctor2.agregarMedicamento(m2);
        pediatra1.agregarMedicamento(m3);
        pediatra2.agregarMedicamento(m4);

        // --- Selección de paciente ---
        System.out.println("Seleccione el paciente:");
        System.out.println("1. Noah Rodriguez");
        System.out.println("2. Maria Hernandez");
        System.out.print("Opción: ");
        String op = sc.nextLine();

        Paciente pacienteSeleccionado = op.equals("1") ? paciente1 : paciente2;

        // --- Asociación: verificación de cuenta ---
        System.out.println("\n--- Verificación de Cuenta ---");
        System.out.print("Ingrese ID numérico: ");
        String id = sc.nextLine();
        System.out.print("Ingrese clave: ");
        String clave = sc.nextLine();

        VerificarCuenta verificador = new VerificarCuenta(pacienteSeleccionado);
        verificador.verificar(id, clave);

        // --- Opción para probar alarma ---
        System.out.println("\n¿Desea probar la alarma? (s/n): ");
        String probar = sc.nextLine();

        if (probar.equalsIgnoreCase("s")) {
            System.out.println("Alarma activada. Sonará en 5 segundos...");
            Alarma alarmaPrueba = new Alarma(5);
            alarmaPrueba.activar();
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                System.out.println("Error al esperar la alarma.");
            }
        }

        // --- Selección de doctor ---
        Doctor doctorSeleccionado = null;
        while (doctorSeleccionado == null) {
            System.out.println("\nSeleccione su doctor:");
            System.out.println("1. Edward Jenner");
            System.out.println("2. Alexander Fleming");
            System.out.println("3. Marie Curie");
            System.out.println("4. Louis Pasteur (Pediatra)");
            System.out.println("5. Joseph Lister (Pediatra)");
            System.out.print("Opción: ");
            String opcionDoc = sc.nextLine();

            switch (opcionDoc) {
                case "1" -> doctorSeleccionado = doctor1;
                case "2" -> doctorSeleccionado = doctor2;
                case "3" -> doctorSeleccionado = doctor3;
                case "4" -> doctorSeleccionado = pediatra1;
                case "5" -> doctorSeleccionado = pediatra2;
                default -> System.out.println("Opción inválida, intente de nuevo.");
            }
        }

        // Asignar medicamentos a pacientes
        for (Medicamento med : doctorSeleccionado.getRecetas()) {
            Recordatorio r1 = new Recordatorio(med.getFrecuencia(), med);
            pacienteSeleccionado.agregarRecordatorio(r1);
            gestorNotificaciones.registrar(r1);
            gestorMedicamentos.agregarMedicamento(med);
        }

        // --- Menú principal ---
        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Ver medicamentos del doctor");
            System.out.println("2. Ver recordatorios del paciente");
            System.out.println("3. Probar alarma nuevamente");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> {
                    System.out.println("\n--- MEDICAMENTOS DEL DOCTOR ---");
                    doctorSeleccionado.mostrarRecetas();
                }
                case 2 -> {
                    System.out.println("\n--- RECORDATORIOS DEL PACIENTE ---");
                    pacienteSeleccionado.mostrarRecordatorios();
                }
                case 3 -> {
                    System.out.println("Alarma activada. Sonará en 5 segundos...");
                    Alarma alarmaPrueba = new Alarma(5);
                    alarmaPrueba.activar();
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        System.out.println("Error al esperar la alarma.");
                    }
                }
                case 4 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 4);

        sc.close();
    }
}
